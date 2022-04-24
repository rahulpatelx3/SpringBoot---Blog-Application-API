package com.blog.api.service;

import java.util.List;
import com.blog.api.payload.*;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	void deleteAllPost();
	PostDto getPostById(Integer postId);
	PostResponse getAllPostByCategory(Integer categoryId,Integer pageSize,Integer pageNumber,String sortBy,String sortDirection);
	PostResponse getAllPostByUser(Integer userId,Integer pageSize,Integer pageNumber,String sortBy,String sortDirection);
	PostResponse getAllPost(Integer pageSize,Integer pageNumber,String sortBy,String sortDirection);
	List<PostDto> searchPost(String keyword);
}
