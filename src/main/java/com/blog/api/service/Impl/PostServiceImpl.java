package com.blog.api.service.Impl;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("image.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatePost=this.postRepo.save(post);
		return this.modelMapper.map(updatePost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		this.postRepo.delete(post);
	}
	
	@Override
	public void deleteAllPost() {
		this.postRepo.deleteAll();
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public PostResponse getAllPostByCategory(Integer categoryId,Integer pageSize,
			Integer pageNumber,String sortBy,String sortDirection) {
		
		Sort sort=null;
		if(sortDirection.equalsIgnoreCase("Asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		Page<Post> posts=this.postRepo.findByCategory(category,pageable);
		
		List<PostDto> listDtos=posts.stream().map((post)-> 
			this.modelMapper.map(post,PostDto.class)
			).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listDtos);
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setTotalPage(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getAllPostByUser(Integer userId,Integer pageSize,Integer pageNumber,String sortBy,String sortDirection) {
		
		Sort sort=null;
		if(sortDirection.equalsIgnoreCase("Asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		
		Page<Post> posts=this.postRepo.findByUser(user,pageable);
		
		List<PostDto> listDtos=posts.stream().map((post)-> 
			this.modelMapper.map(post,PostDto.class)
			).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listDtos);
		postResponse.setPageNumber(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElement(posts.getTotalElements());
		postResponse.setTotalPage(posts.getTotalPages());
		postResponse.setLastPage(posts.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getAllPost(Integer pageSize,Integer pageNumber,String sortBy,String sortDirection) {
		Sort sort=null;
		if(sortDirection.equalsIgnoreCase("Asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page=this.postRepo.findAll(pageable);
		
		List<Post> posts=page.getContent();
		
		List<PostDto> listDtos=posts.stream().map((post)->
			this.modelMapper.map(post,PostDto.class)
			).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(listDtos);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setTotalPage(page.getTotalPages());
		postResponse.setLastPage(page.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDto=posts.stream().map((post)->
			this.modelMapper.map(post,PostDto.class)
			).collect(Collectors.toList());
		return postDto;
	}
}