package com.github.bicz.sitesculpt.component.dto;

import com.github.bicz.sitesculpt.component.dto.transfer.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedPageComponentResponse {
    private Long componentId;
    private Integer order;
    private String type;
    private String content;
    private TextComponentTransfer textComponent;
    private ImageComponentTransfer imageComponent;
    private ImageGalleryComponentTransfer imageGalleryComponent;
    private VideoComponentTransfer videoComponent;
    private TimelineComponentTransfer timelineComponent;
    private DescriptionCardComponentTransfer descriptionCardComponent;
}
