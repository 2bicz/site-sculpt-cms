package com.github.bicz.sitesculpt.component.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageComponentTransfer {
    private String src;
    private String alt;
    private String maxWidth;
    private String maxHeight;
}
