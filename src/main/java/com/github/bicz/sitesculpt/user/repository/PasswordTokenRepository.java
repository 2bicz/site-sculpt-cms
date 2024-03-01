package com.github.bicz.sitesculpt.user.repository;

import com.github.bicz.sitesculpt.user.model.password_reset.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {}
