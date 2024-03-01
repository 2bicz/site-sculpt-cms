package com.github.bicz.sitesculpt.component.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionCardComponentTransfer {
    private String imageUrl;
    private String title;
    private String titleColor;
    private String description;
    private String descriptionColor;
}
