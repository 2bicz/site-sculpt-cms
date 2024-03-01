package com.github.bicz.sitesculpt.component.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoComponentTransfer {
    private String src;
    private String alt;
    private String maxWidth;
    private String maxHeight;
    private Boolean controls;
    private Boolean autoPlay;
    private Boolean loop;
    private Boolean muted;
}
