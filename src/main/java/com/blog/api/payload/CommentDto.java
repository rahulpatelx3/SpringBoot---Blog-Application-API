package com.blog.api.payload;

import lombok.*;

@Getter
@Setter
public class CommentDto {
	private Integer commentId;
	private String content;
}