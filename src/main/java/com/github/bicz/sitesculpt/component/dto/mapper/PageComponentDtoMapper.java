package com.github.bicz.sitesculpt.component.dto.mapper;

import com.github.bicz.sitesculpt.component.dto.GeneratedPageComponentResponse;
import com.github.bicz.sitesculpt.component.dto.transfer.*;
import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.PageComponentType;
import com.github.bicz.sitesculpt.component.model.components.*;
import com.github.bicz.sitesculpt.component.model.components.TextComponent;
import com.github.bicz.sitesculpt.component.repository.*;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PageComponentDtoMapper {
    private final PageSectionRepository pageSectionRepository;
    private final ImageComponentRepository imageComponentRepository;
    private final ImageGalleryComponentRepository imageGalleryComponentRepository;
    private final TextComponentRepository textComponentRepository;
    private final TimelineComponentRepository timelineComponentRepository;
    private final TimelineComponentEntryRepository timelineComponentEntryRepository;
    private final VideoComponentRepository videoComponentRepository;
    private final DescriptionCardComponentRepository descriptionCardComponentRepository;

    public PageComponent mapPageComponentRequestToPageComponent(PageComponentRequest request) {
        PageComponent pageComponent = new PageComponent();
        pageComponent.setType(PageComponentType.valueOf(request.getType()));
        pageComponent.setOrder(request.getOrder());

        if (Objects.nonNull(request.getPageSectionId())) {
            pageComponent.setPageSection(pageSectionRepository.findById(request.getPageSectionId()).get());
        }

        return pageComponent;
    }

    public PageComponentResponse mapPageComponentToPageComponentResponse(PageComponent pageComponent) {
        PageComponentResponse response = new PageComponentResponse();
        response.setComponentId(pageComponent.getComponentId());
        response.setType(pageComponent.getType().toString());
        response.setOrder(pageComponent.getOrder());

        response.setPageSectionId(pageComponent.getPageSection().getPageSectionId());

        Optional<ImageComponent> optionalImageComponent = imageComponentRepository.findByPageComponent(pageComponent);
        optionalImageComponent.ifPresent(imageComponent -> response.setImageComponent(mapImageComponentToImageComponentTransfer(imageComponent)));

        Optional<ImageGalleryComponent> optionalImageGalleryComponent = imageGalleryComponentRepository.findByPageComponent(pageComponent);
        optionalImageGalleryComponent.ifPresent(imageGalleryComponent -> response.setImageGalleryComponent(mapImageGalleryComponentToImageGalleryComponentTransfer(imageGalleryComponent)));

        Optional<TextComponent> optionalTextComponent = textComponentRepository.findByPageComponent(pageComponent);
        optionalTextComponent.ifPresent(textComponent -> response.setTextComponent(mapTextComponentToTextComponentTransfer(textComponent)));

        Optional<TimelineComponent> optionalTimelineComponent = timelineComponentRepository.findByPageComponent(pageComponent);
        optionalTimelineComponent.ifPresent(timelineComponent -> response.setTimelineComponent(mapTimelineComponentToTimelineComponentTransfer(timelineComponent)));

        Optional<VideoComponent> optionalVideoComponent = videoComponentRepository.findByPageComponent(pageComponent);
        optionalVideoComponent.ifPresent(videoComponent -> response.setVideoComponent(mapVideoComponentToVideoComponentTransfer(videoComponent)));

        Optional<DescriptionCardComponent> optionalDescriptionCardComponent = descriptionCardComponentRepository.findByPageComponent(pageComponent);
        optionalDescriptionCardComponent.ifPresent(descriptionCardComponent -> response.setDescriptionCardComponent(mapDescriptionCardComponentToDescriptionCardComponentTransfer(descriptionCardComponent)));

        return response;
    }

    public GeneratedPageComponentResponse mapPageComponentToGeneratedPageComponentResponse(PageComponent pageComponent) {
        GeneratedPageComponentResponse response = new GeneratedPageComponentResponse();
        response.setComponentId(pageComponent.getComponentId());
        response.setOrder(pageComponent.getOrder());
        response.setType(pageComponent.getType().toString());

        Optional<ImageComponent> optionalImageComponent = imageComponentRepository.findByPageComponent(pageComponent);
        optionalImageComponent.ifPresent(imageComponent -> response.setImageComponent(mapImageComponentToImageComponentTransfer(imageComponent)));

        Optional<ImageGalleryComponent> optionalImageGalleryComponent = imageGalleryComponentRepository.findByPageComponent(pageComponent);
        optionalImageGalleryComponent.ifPresent(imageGalleryComponent -> response.setImageGalleryComponent(mapImageGalleryComponentToImageGalleryComponentTransfer(imageGalleryComponent)));

        Optional<TextComponent> optionalTextComponent = textComponentRepository.findByPageComponent(pageComponent);
        optionalTextComponent.ifPresent(textComponent -> response.setTextComponent(mapTextComponentToTextComponentTransfer(textComponent)));

        Optional<TimelineComponent> optionalTimelineComponent = timelineComponentRepository.findByPageComponent(pageComponent);
        optionalTimelineComponent.ifPresent(timelineComponent -> response.setTimelineComponent(mapTimelineComponentToTimelineComponentTransfer(timelineComponent)));

        Optional<VideoComponent> optionalVideoComponent = videoComponentRepository.findByPageComponent(pageComponent);
        optionalVideoComponent.ifPresent(videoComponent -> response.setVideoComponent(mapVideoComponentToVideoComponentTransfer(videoComponent)));

        Optional<DescriptionCardComponent> optionalDescriptionCardComponent = descriptionCardComponentRepository.findByPageComponent(pageComponent);
        optionalDescriptionCardComponent.ifPresent(descriptionCardComponent -> response.setDescriptionCardComponent(mapDescriptionCardComponentToDescriptionCardComponentTransfer(descriptionCardComponent)));

        return response;
    }

    public ImageComponent mapImageComponentTransferToImageComponent(ImageComponentTransfer transfer) {
        ImageComponent result = new ImageComponent();

        result.setSrc(transfer.getSrc());
        result.setAlt(transfer.getAlt());
        result.setMaxWidth(transfer.getMaxWidth());
        result.setMaxHeight(transfer.getMaxHeight());

        return result;
    }

    public ImageComponentTransfer mapImageComponentToImageComponentTransfer(ImageComponent component) {
        ImageComponentTransfer result = new ImageComponentTransfer();

        result.setSrc(component.getSrc());
        result.setAlt(component.getAlt());
        result.setMaxWidth(component.getMaxWidth());
        result.setMaxHeight(component.getMaxHeight());

        return result;
    }

    public ImageGalleryComponent mapImageGalleryComponentTransferToImageGalleryComponent(ImageGalleryComponentTransfer transfer) {
        ImageGalleryComponent result = new ImageGalleryComponent();

        result.setImageUrls(transfer.getImageUrls());

        return result;
    }

    public ImageGalleryComponentTransfer mapImageGalleryComponentToImageGalleryComponentTransfer(ImageGalleryComponent component) {
        ImageGalleryComponentTransfer result = new ImageGalleryComponentTransfer();

        result.setImageUrls(component.getImageUrls());

        return result;
    }

    public TextComponent mapTextComponentTransferToTextComponent(TextComponentTransfer transfer) {
        TextComponent result = new TextComponent();

        result.setTitle(transfer.getTitle());
        result.setTitleColor(transfer.getTitleColor());
        result.setDescription(transfer.getDescription());
        result.setDescriptionColor(transfer.getDescriptionColor());

        return result;
    }

    public TextComponentTransfer mapTextComponentToTextComponentTransfer(TextComponent component) {
        TextComponentTransfer result = new TextComponentTransfer();

        result.setTitle(component.getTitle());
        result.setTitleColor(component.getTitleColor());
        result.setDescription(component.getDescription());
        result.setDescriptionColor(component.getDescriptionColor());

        return result;
    }

    public TimelineComponent mapTimelineComponentTransferToTimelineComponent(TimelineComponentTransfer transfer) {
        TimelineComponent result = new TimelineComponent();
        ArrayList<TimelineComponentEntry> resultEntries = new ArrayList<>();

        ArrayList<TimelineComponentEntryTransfer> transferEntries = (ArrayList<TimelineComponentEntryTransfer>) transfer.getEntries();
        for (TimelineComponentEntryTransfer transferEntry : transferEntries) {
            resultEntries.add(mapTimelineComponentEntryTransferToTimelineComponentEntry(transferEntry));
        }
        result.setEntries(resultEntries);

        return result;
    }

    public TimelineComponentTransfer mapTimelineComponentToTimelineComponentTransfer(TimelineComponent component) {
        TimelineComponentTransfer result = new TimelineComponentTransfer();
        ArrayList<TimelineComponentEntryTransfer> resultEntries = new ArrayList<>();

        ArrayList<TimelineComponentEntry> componentEntries = (ArrayList<TimelineComponentEntry>) timelineComponentEntryRepository.findAllByTimelineComponent(component);
        for (TimelineComponentEntry componentEntry : componentEntries) {
            resultEntries.add(mapTimelineComponentEntryToTimelineComponentEntryTransfer(componentEntry));
        }
        result.setEntries(resultEntries);

        return result;
    }

    public TimelineComponentEntry mapTimelineComponentEntryTransferToTimelineComponentEntry(TimelineComponentEntryTransfer transfer) {
        TimelineComponentEntry result = new TimelineComponentEntry();

        result.setLabel(transfer.getLabel());
        result.setContent(transfer.getContent());

        return result;
    }

    public TimelineComponentEntryTransfer mapTimelineComponentEntryToTimelineComponentEntryTransfer(TimelineComponentEntry component) {
        TimelineComponentEntryTransfer result = new TimelineComponentEntryTransfer();

        result.setLabel(component.getLabel());
        result.setContent(component.getContent());

        return result;
    }

    public VideoComponent mapVideoComponentTransferToVideoComponent(VideoComponentTransfer transfer) {
        VideoComponent result = new VideoComponent();

        result.setSrc(transfer.getSrc());
        result.setAlt(transfer.getAlt());
        result.setMaxWidth(transfer.getMaxWidth());
        result.setMaxHeight(transfer.getMaxHeight());
        result.setControls(transfer.getControls());
        result.setAutoPlay(transfer.getAutoPlay());
        result.setLoop(transfer.getLoop());
        result.setMuted(transfer.getMuted());

        return result;
    }

    public VideoComponentTransfer mapVideoComponentToVideoComponentTransfer(VideoComponent component) {
        VideoComponentTransfer result = new VideoComponentTransfer();

        result.setSrc(component.getSrc());
        result.setAlt(component.getAlt());
        result.setMaxWidth(component.getMaxWidth());
        result.setMaxHeight(component.getMaxHeight());
        result.setControls(component.getControls());
        result.setAutoPlay(component.getAutoPlay());
        result.setLoop(component.getLoop());
        result.setMuted(component.getMuted());

        return result;
    }

    public DescriptionCardComponent mapDescriptionCardComponentTransferToDescriptionCardComponent(DescriptionCardComponentTransfer transfer) {
        DescriptionCardComponent result = new DescriptionCardComponent();

        result.setTitle(transfer.getTitle());
        result.setTitleColor(transfer.getTitleColor());
        result.setDescription(transfer.getDescription());
        result.setDescriptionColor(transfer.getDescriptionColor());
        result.setImageUrl(transfer.getImageUrl());

        return result;
    }

    public DescriptionCardComponentTransfer mapDescriptionCardComponentToDescriptionCardComponentTransfer(DescriptionCardComponent component) {
        DescriptionCardComponentTransfer result = new DescriptionCardComponentTransfer();

        result.setTitle(component.getTitle());
        result.setTitleColor(component.getTitleColor());
        result.setDescription(component.getDescription());
        result.setDescriptionColor(component.getDescriptionColor());
        result.setImageUrl(component.getImageUrl());

        return result;
    }
}
