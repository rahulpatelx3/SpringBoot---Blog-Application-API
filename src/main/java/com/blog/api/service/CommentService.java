package com.blog.api.service;

import java.util.*;

import com.blog.api.payload.CommentDto;

public interface CommentService {
	public CommentDto createComment(CommentDto commentDto,Integer postId);
	public void deleteComment(Integer commentId);
	public CommentDto getCommentById(Integer commentId);
	public List<CommentDto> getAllComment();
}