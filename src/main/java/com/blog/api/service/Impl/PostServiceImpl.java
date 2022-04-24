package com.blog.api.service.Impl;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.api.entity.*;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payload.*;
import com.blog.api.repo.*;
import com.blog.api.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	private Post dtoToPost(PostDto postDto){
		return this.modelMapper.map(postDto, Post.class);
	}
	
	private PostDto postToDto(Post post){
		return this.modelMapper.map(post, PostDto.class);
	}
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		Post post=dtoToPost(postDto);
		post.setImageName("image.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost=this.postRepo.save(post);
		return postToDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		return postToDto(post);
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto> listDto=posts.stream().map((post)-> 
			postToDto(post)
			).collect(Collectors.toList());
		return listDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> listDto=posts.stream().map((post)-> 
			postToDto(post)
			).collect(Collectors.toList());
		return listDto;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts=this.postRepo.findAll();
		List<PostDto> listDtos=posts.stream().map((post)->
			postToDto(post)
			).collect(Collectors.toList());
		return listDtos;
	}
}
