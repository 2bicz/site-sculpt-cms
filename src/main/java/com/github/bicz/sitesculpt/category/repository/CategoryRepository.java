package com.github.bicz.sitesculpt.category.repository;

import com.github.bicz.sitesculpt.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
