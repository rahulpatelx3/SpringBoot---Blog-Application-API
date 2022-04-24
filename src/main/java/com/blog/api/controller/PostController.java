package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.APIResponse;
import com.blog.api.payload.PostDto;
import com.blog.api.service.Impl.PostServiceImpl;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
												@PathVariable Integer userId,
												@PathVariable Integer categoryId){
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> upatePost(@RequestBody PostDto postDto,
												@PathVariable Integer postId){
		PostDto createPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> listDtos=this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(listDtos,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> listDtos=this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(listDtos,HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> listDtos=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(listDtos,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<APIResponse> deletePostById(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<APIResponse>(new APIResponse("post deleted", true),HttpStatus.OK);
	}
	
	@DeleteMapping("/post/all")
	public ResponseEntity<APIResponse> deleteAllPost(){
		this.postService.deleteAllPost();
		return new ResponseEntity<APIResponse>(new APIResponse("All post deleted", true),HttpStatus.OK);
	}
}
