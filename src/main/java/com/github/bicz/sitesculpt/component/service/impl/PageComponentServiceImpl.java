package com.github.bicz.sitesculpt.component.service.impl;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.dto.mapper.PageComponentDtoMapper;
import com.github.bicz.sitesculpt.component.dto.transfer.*;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.PageComponentType;
import com.github.bicz.sitesculpt.component.model.components.*;
import com.github.bicz.sitesculpt.component.repository.*;
import com.github.bicz.sitesculpt.component.service.PageComponentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.ComponentType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PageComponentServiceImpl implements PageComponentService {
    private final PageComponentRepository pageComponentRepository;
    private final PageSectionRepository pageSectionRepository;
    private final ImageComponentRepository imageComponentRepository;
    private final ImageGalleryComponentRepository imageGalleryComponentRepository;
    private final TextComponentRepository textComponentRepository;
    private final TimelineComponentRepository timelineComponentRepository;
    private final TimelineComponentEntryRepository timelineComponentEntryRepository;
    private final VideoComponentRepository videoComponentRepository;
    private final DescriptionCardComponentRepository descriptionCardComponentRepository;
    private final PageComponentRequestValidator requestValidator;
    private final PageComponentDtoMapper mapper;

    @Override
    public List<PageComponentResponse> getAllByPageSection(Long pageSectionId) {
        if (Objects.isNull(pageSectionId)) {
            throw new RequestNotCorrectException("Provided page section id is empty");
        }
        ArrayList<PageComponentResponse> result = new ArrayList<>();

        PageSection pageSection = obtainExistingPageSection(pageSectionId);
        ArrayList<PageComponent> pageComponents = (ArrayList<PageComponent>) pageComponentRepository.findAllByPageSection(pageSection);

        for (PageComponent pageComponent : pageComponents) {
            if (!Objects.isNull(pageComponent)) {
                result.add(mapper.mapPageComponentToPageComponentResponse(pageComponent));
            }
        }

        result.sort(Comparator.comparingInt(PageComponentResponse::getOrder));

        return result;
    }

    @Override
    public PageComponentResponse getComponentById(Long pageComponentId) {
        if (Objects.isNull(pageComponentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        PageComponent pageComponent = obtainExistingPageComponent(pageComponentId);

        return mapper.mapPageComponentToPageComponentResponse(pageComponent);
    }

    @Override
    public Long createComponent(PageComponentRequest request) throws RequestNotCorrectException {
        requestValidator.validatePageComponentRequest(request);

        PageSection pageSection = obtainExistingPageSection(request.getPageSectionId());
        for (PageComponent pageComponent : pageSection.getComponents()) {
            if (pageComponent.getOrder().equals(request.getOrder())) {
                throw new RequestNotCorrectException(String.format("Page component with order %d already exists for page section %d", request.getOrder(), request.getPageSectionId()));
            }
        }

        PageComponent pageComponent = mapper.mapPageComponentRequestToPageComponent(request);

        if (Objects.nonNull(request.getTextComponent()) && Objects.equals(request.getType(), PageComponentType.TEXT.toString())) {
            pageComponent.setTextComponent(createAndGetTextComponent(request.getTextComponent()));
        }

        if (Objects.nonNull(request.getImageComponent()) && Objects.equals(request.getType(), PageComponentType.IMAGE.toString())) {
            pageComponent.setImageComponent(createAndGetImageComponent(request.getImageComponent()));
        }

        if (Objects.nonNull(request.getImageGalleryComponent()) && Objects.equals(request.getType(), PageComponentType.GALLERY.toString())) {
            pageComponent.setImageGalleryComponent(createAndGetImageGalleryComponent(request.getImageGalleryComponent()));
        }

        if (Objects.nonNull(request.getTimelineComponent()) && Objects.equals(request.getType(), PageComponentType.TIMELINE.toString())) {
            pageComponent.setTimelineComponent(createAndGetTimelineComponent(request.getTimelineComponent()));
        }

        if (Objects.nonNull(request.getVideoComponent()) && Objects.equals(request.getType(), PageComponentType.VIDEO.toString())) {
            pageComponent.setVideoComponent(createAndGetVideoComponent(request.getVideoComponent()));
        }

        if (Objects.nonNull(request.getDescriptionCardComponent()) && Objects.equals(request.getType(), PageComponentType.DESCRIPTION_CARD.toString())) {
            pageComponent.setDescriptionCardComponent(createAndGetDescriptionCardComponent(request.getDescriptionCardComponent()));
        }

        return pageComponentRepository.save(pageComponent).getComponentId();
    }

    public ImageComponent createAndGetImageComponent(ImageComponentTransfer transfer) throws ResourceNotFoundException {
        Long imageComponentId = imageComponentRepository.save(mapper.mapImageComponentTransferToImageComponent(transfer)).getImageComponentId();

        Optional<ImageComponent> optionalImageComponent = imageComponentRepository.findById(imageComponentId);
        if (optionalImageComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Image component with id %d does not exist", imageComponentId));
        }
        return optionalImageComponent.get();
    }

    public ImageGalleryComponent createAndGetImageGalleryComponent(ImageGalleryComponentTransfer transfer) throws ResourceNotFoundException {
        Long imageGalleryComponentId = imageGalleryComponentRepository.save(mapper.mapImageGalleryComponentTransferToImageGalleryComponent(transfer)).getImageGalleryComponentId();

        Optional<ImageGalleryComponent> optionalImageGalleryComponent = imageGalleryComponentRepository.findById(imageGalleryComponentId);
        if (optionalImageGalleryComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Image gallery component with id %d does not exist", imageGalleryComponentId));
        }
        return optionalImageGalleryComponent.get();
    }

    public TextComponent createAndGetTextComponent(TextComponentTransfer transfer) throws ResourceNotFoundException {
        Long textComponentId = textComponentRepository.save(mapper.mapTextComponentTransferToTextComponent(transfer)).getTextComponentId();

        Optional<TextComponent> optionalTextComponent = textComponentRepository.findById(textComponentId);
        if (optionalTextComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Text component with id %d does not exist", textComponentId));
        }
        return optionalTextComponent.get();
    }

    public TimelineComponent createAndGetTimelineComponent(TimelineComponentTransfer transfer) throws ResourceNotFoundException {
        Long timelineComponentId = timelineComponentRepository.save(mapper.mapTimelineComponentTransferToTimelineComponent(transfer)).getTimelineComponentId();

        Optional<TimelineComponent> optionalTimelineComponent = timelineComponentRepository.findById(timelineComponentId);
        if (optionalTimelineComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Timeline component with id %d does not exist", timelineComponentId));
        }
        return optionalTimelineComponent.get();
    }

    public VideoComponent createAndGetVideoComponent(VideoComponentTransfer transfer) throws ResourceNotFoundException {
        Long videoComponentId = videoComponentRepository.save(mapper.mapVideoComponentTransferToVideoComponent(transfer)).getVideoComponentId();

        Optional<VideoComponent> optionalVideoComponent = videoComponentRepository.findById(videoComponentId);
        if (optionalVideoComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Video component with id %d does not exist", videoComponentId));
        }
        return optionalVideoComponent.get();
    }

    public DescriptionCardComponent createAndGetDescriptionCardComponent(DescriptionCardComponentTransfer transfer) throws ResourceNotFoundException {
        Long descriptionCardComponentId = descriptionCardComponentRepository.save(mapper.mapDescriptionCardComponentTransferToDescriptionCardComponent(transfer)).getDescriptionCardComponentId();

        Optional<DescriptionCardComponent> optionalDescriptionCardComponent = descriptionCardComponentRepository.findById(descriptionCardComponentId);
        if (optionalDescriptionCardComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Description card component with id %d does not exist", descriptionCardComponentId));
        }
        return optionalDescriptionCardComponent.get();
    }

    @Override
    public Long updateComponent(Long componentId, PageComponentRequest request) throws RequestNotCorrectException {
        if (Objects.isNull(componentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        requestValidator.validatePageComponentRequest(request);

        PageComponent pageComponent = obtainExistingPageComponent(componentId);

        if (!pageComponent.getOrder().equals(request.getOrder())) {
            PageSection pageSection = obtainExistingPageSection(request.getPageSectionId());
            for (PageComponent pageSectionComponent : pageSection.getComponents()) {
                if (pageSectionComponent.getOrder().equals(request.getOrder())) {
                    throw new RequestNotCorrectException(String.format("Page component with order %d already exists for page section %d", request.getOrder(), request.getPageSectionId()));
                }
            }
        }

        PageComponent pageComponentUpdate = mapper.mapPageComponentRequestToPageComponent(request);

        pageComponent.setType(pageComponentUpdate.getType());
        pageComponent.setPageSection(pageComponentUpdate.getPageSection());
        pageComponent.setOrder(pageComponentUpdate.getOrder());

        cleanUpComponents(pageComponent);

        if (Objects.nonNull(request.getTextComponent()) && Objects.equals(request.getType(), PageComponentType.TEXT.toString())) {
            pageComponent.setTextComponent(createAndGetTextComponent(request.getTextComponent()));
        }

        if (Objects.nonNull(request.getImageComponent()) && Objects.equals(request.getType(), PageComponentType.IMAGE.toString())) {
            pageComponent.setImageComponent(createAndGetImageComponent(request.getImageComponent()));
        }

        if (Objects.nonNull(request.getImageGalleryComponent()) && Objects.equals(request.getType(), PageComponentType.GALLERY.toString())) {
            pageComponent.setImageGalleryComponent(createAndGetImageGalleryComponent(request.getImageGalleryComponent()));
        }

        if (Objects.nonNull(request.getTimelineComponent()) && Objects.equals(request.getType(), PageComponentType.TIMELINE.toString())) {
            pageComponent.setTimelineComponent(createAndGetTimelineComponent(request.getTimelineComponent()));
        }

        if (Objects.nonNull(request.getVideoComponent()) && Objects.equals(request.getType(), PageComponentType.VIDEO.toString())) {
            pageComponent.setVideoComponent(createAndGetVideoComponent(request.getVideoComponent()));
        }

        if (Objects.nonNull(request.getDescriptionCardComponent()) && Objects.equals(request.getType(), PageComponentType.DESCRIPTION_CARD.toString())) {
            pageComponent.setDescriptionCardComponent(createAndGetDescriptionCardComponent(request.getDescriptionCardComponent()));
        }

        return pageComponentRepository.save(pageComponent).getComponentId();
    }

    private void cleanUpComponents(PageComponent pageComponent) {
        if (Objects.nonNull(pageComponent.getTextComponent())) {
            textComponentRepository.deleteById(pageComponent.getTextComponent().getTextComponentId());
        }
        if (Objects.nonNull(pageComponent.getImageComponent())) {
            imageComponentRepository.deleteById(pageComponent.getImageComponent().getImageComponentId());
        }
        if (Objects.nonNull(pageComponent.getVideoComponent())) {
            videoComponentRepository.deleteById(pageComponent.getVideoComponent().getVideoComponentId());
        }
        if (Objects.nonNull(pageComponent.getImageGalleryComponent())) {
            imageGalleryComponentRepository.deleteById(pageComponent.getImageGalleryComponent().getImageGalleryComponentId());
        }
        if (Objects.nonNull(pageComponent.getDescriptionCardComponent())) {
            descriptionCardComponentRepository.deleteById(pageComponent.getDescriptionCardComponent().getDescriptionCardComponentId());
        }
        if (Objects.nonNull(pageComponent.getTimelineComponent())) {
            timelineComponentRepository.deleteById(pageComponent.getTimelineComponent().getTimelineComponentId());
        }
    }

    @Override
    public void deleteComponentById(Long componentId) throws RequestNotCorrectException {
        if (Objects.isNull(componentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        PageComponent pageComponent = obtainExistingPageComponent(componentId);
        cleanUpComponents(pageComponent);
        pageComponentRepository.deleteById(pageComponent.getComponentId());
    }

    private PageComponent obtainExistingPageComponent(Long pageComponentId) throws ResourceNotFoundException {
        Optional<PageComponent> optionalPageComponent = pageComponentRepository.findById(pageComponentId);
        if (optionalPageComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page component with id %d does not exist", pageComponentId));
        }
        return optionalPageComponent.get();
    }

    private PageSection obtainExistingPageSection(Long pageSectionId) throws ResourceNotFoundException {
        Optional<PageSection> optionalPageSection = pageSectionRepository.findById(pageSectionId);
        if (optionalPageSection.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page section with id %d does not exist", pageSectionId));
        }
        return optionalPageSection.get();
    }
}
