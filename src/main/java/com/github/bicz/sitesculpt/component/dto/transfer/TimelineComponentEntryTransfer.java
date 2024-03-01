package com.github.bicz.sitesculpt.component.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimelineComponentEntryTransfer {
    private String label;
    private String content;
}
