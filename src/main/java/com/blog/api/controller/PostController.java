package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.api.config.Constant;
import com.blog.api.payload.APIResponse;
import com.blog.api.payload.PostDto;
import com.blog.api.payload.PostResponse;
import com.blog.api.service.Impl.PostServiceImpl;
import java.util.*;

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
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = Constant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = Constant.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = Constant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = Constant.SORT_DIRECTION,required = false) String sortDirection){
		PostResponse postResponse=this.postService.getAllPostByUser(userId,pageSize,pageNumber,sortBy,sortDirection);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = Constant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = Constant.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = Constant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = Constant.SORT_DIRECTION,required = false) String sortDirection){
		PostResponse postResponse=this.postService.getAllPostByCategory(categoryId,pageSize,pageNumber,sortBy,sortDirection);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = Constant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = Constant.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = Constant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = Constant.SORT_DIRECTION,required = false) String sortDirection){
		
		PostResponse postResponse=this.postService.getAllPost(pageSize,pageNumber,sortBy,sortDirection);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
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
	
	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword){
		List<PostDto> postDto=this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
}
