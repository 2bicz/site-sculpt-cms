package com.github.bicz.sitesculpt.media.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {
    private Long mediaId;
    private String filename;
    private String type;
    private Date uploadedAt;
    private Long userId;
}
