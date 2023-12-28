package com.github.bicz.sitesculpt.comment.model;

import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "last_modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NonNull
    private Post post;

    @Column(name = "status")
    @NonNull
    private CommentStatus status;
}
