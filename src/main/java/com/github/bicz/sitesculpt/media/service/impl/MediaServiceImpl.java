package com.github.bicz.sitesculpt.media.service.impl;

import com.github.bicz.sitesculpt.media.dto.MediaRequest;
import com.github.bicz.sitesculpt.media.dto.MediaResponse;
import com.github.bicz.sitesculpt.media.model.Media;
import com.github.bicz.sitesculpt.media.repository.MediaRepository;
import com.github.bicz.sitesculpt.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public List<MediaResponse> getMediasByUser(Long userId) {
        ArrayList<MediaResponse> result = new ArrayList<>();
        List<Media> mediaOfUser = mediaRepository.findAll();

        for (Media media : mediaOfUser) {
            if (!Objects.isNull(media)) {
                result.add(new MediaResponse(media.getMediaId(), media.getFilePath(), media.getType().toString(), media.getUploadedAt(), media.getUploadedBy().getUserId()));
            }
        }

        return result;
    }

    @Override
    public MediaResponse getMediaById(Long mediaId) {
        return null;
    }

    @Override
    public Long createMedia(MediaRequest request) {
        return null;
    }
}
