package com.blog.api.payload;

import java.util.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<CommentDto>();
}