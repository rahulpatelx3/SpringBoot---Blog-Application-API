package com.blog.api.payload;

import java.util.Date;
import lombok.*;
import com.blog.api.entity.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private User user;
	private CategoryDto category;
}
