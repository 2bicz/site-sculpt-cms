package com.github.bicz.sitesculpt.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAdminRequest {
    private Long websiteId;
    private String username;
}
