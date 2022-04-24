package com.blog.api.payload;

import javax.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int userId;
	
	@NotEmpty
	@Size(min = 4,max = 20,message = "user name must have min 4 char and max 20 char")
	private String name;
	
	@Email(message ="invalid email")
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 10,message = "password must be min of 4 char and max be of 10 char")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{4,10}$", 
		message = "It contains at least 8 characters and at most 20 characters.\r\n"
			+ "It contains at least one digit.\r\n"
			+ "It contains at least one upper case alphabet.\r\n"
			+ "It contains at least one lower case alphabet.\r\n"
			+ "It contains at least one special character which includes !@#$%&*()-+=^.\r\n"
			+ "It doesn’t contain any white space.")
	private String password;
	
	@NotEmpty
	private String about;
}
