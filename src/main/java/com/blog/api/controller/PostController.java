package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.config.Constant;
import com.blog.api.payload.*;
import com.blog.api.service.Impl.FileServiceImpl;
import com.blog.api.service.Impl.PostServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@Value("${project.image}")
	private String path;
	
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
	
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {
		
		PostDto postDto=this.postService.getPostById(postId);
		
		String fileName=this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		InputStream resourceInputStream=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourceInputStream, response.getOutputStream());
	}
}