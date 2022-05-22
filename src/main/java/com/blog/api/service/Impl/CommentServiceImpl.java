package com.blog.api.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.api.entity.Comment;
import com.blog.api.entity.Post;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payload.CommentDto;
import com.blog.api.repo.CommentRepo;
import com.blog.api.repo.PostRepo;
import com.blog.api.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id",postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id",commentId));
		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).get();
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> comment = this.commentRepo.findAll();
		List<CommentDto> commentDto=comment.stream().map(com->this.modelMapper.map(com, CommentDto.class)).collect(Collectors.toList());
		return commentDto;
	}

}