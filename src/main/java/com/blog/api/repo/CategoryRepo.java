package com.blog.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.api.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
