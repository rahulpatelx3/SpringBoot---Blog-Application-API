package com.blog.api.payload;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private int categoryId;
	
	@NotBlank
	@Size(min = 4,max = 10,message = "Title size shouls be between 4 to 10")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 4,max = 20,message = "Description size shouls be between 4 to 20")
	private String categoryDescription;
}
