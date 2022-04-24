package com.blog.api.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.api.entity.*;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
