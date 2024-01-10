package com.github.bicz.sitesculpt.post.model;

import com.github.bicz.sitesculpt.category.model.Category;
import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.reaction.model.Reaction;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.website.model.Website;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "website_id")
    private Website website;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "content")
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
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private PostStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "posts_categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<Reaction> reactions;
}
