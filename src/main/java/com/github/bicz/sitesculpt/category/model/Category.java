package com.github.bicz.sitesculpt.category.model;

import com.github.bicz.sitesculpt.post.model.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;
}
