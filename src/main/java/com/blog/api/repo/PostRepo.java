package com.blog.api.repo;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.api.entity.*;

public interface PostRepo extends JpaRepository<Post, Integer> {
	Page<Post> findByUser(User user,Pageable pageable);
	Page<Post> findByCategory(Category category,Pageable pageable);
}
