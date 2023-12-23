package com.github.bicz.sitesculpt.media.service;

import com.github.bicz.sitesculpt.media.dto.MediaRequest;
import com.github.bicz.sitesculpt.media.dto.MediaResponse;
import com.github.bicz.sitesculpt.media.model.Media;

import java.util.List;

public interface MediaService {
    List<MediaResponse> getMediasByUser(Long userId);
    MediaResponse getMediaById(Long mediaId);
    Long createMedia(MediaRequest request);
}
