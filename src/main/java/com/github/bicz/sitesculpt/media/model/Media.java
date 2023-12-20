package com.github.bicz.sitesculpt.media.model;

import com.github.bicz.sitesculpt.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "medias", uniqueConstraints = @UniqueConstraint(columnNames = "file_path"))
@AllArgsConstructor
@RequiredArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "uploaded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;
}
