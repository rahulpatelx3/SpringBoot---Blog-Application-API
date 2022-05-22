package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blog.api.payload.APIResponse;
import com.blog.api.payload.CommentDto;
import com.blog.api.service.Impl.CommentServiceImpl;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId){
		CommentDto createdCommentDto=this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createdCommentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<APIResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<APIResponse>(new APIResponse("Comment deleted successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") Integer commentId){
		CommentDto commentDto=this.commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}
	
	@GetMapping("/comment")
	public ResponseEntity<List<CommentDto>> getAllComments(){
		List<CommentDto> commentDto=this.commentService.getAllComment();
		return new ResponseEntity<List<CommentDto>>(commentDto,HttpStatus.OK);
	}
	
}