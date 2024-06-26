package com.github.bicz.sitesculpt.media.model;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Builder
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
    @NonNull
    private String filePath;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NonNull
    private MediaType type;

    @Column(name = "uploaded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;

    @OneToMany(mappedBy = "media")
    List<PageComponent> components;
}
