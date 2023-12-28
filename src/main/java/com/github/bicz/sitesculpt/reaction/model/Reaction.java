package com.github.bicz.sitesculpt.reaction.model;

import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Long reactionId;

    @Column(name = "type")
    @NonNull
    private String type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "given_by")
    private User givenBy;
}
