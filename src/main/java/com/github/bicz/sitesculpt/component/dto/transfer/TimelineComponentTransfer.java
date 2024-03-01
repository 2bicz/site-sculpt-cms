package com.github.bicz.sitesculpt.component.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimelineComponentTransfer {
    private List<TimelineComponentEntryTransfer> entries;
}