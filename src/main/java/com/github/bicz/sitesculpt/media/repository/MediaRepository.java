package com.github.bicz.sitesculpt.media.repository;

import com.github.bicz.sitesculpt.media.model.Media;
import com.github.bicz.sitesculpt.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {}
