package com.blog.api.repo;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.api.entity.*;
import java.util.*;

public interface PostRepo extends JpaRepository<Post, Integer> {
	Page<Post> findByUser(User user,Pageable pageable);
	Page<Post> findByCategory(Category category,Pageable pageable);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String keyword);           
}
