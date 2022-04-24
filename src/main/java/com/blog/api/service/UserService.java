package com.blog.api.service;

import com.blog.api.payload.*;
import java.util.*;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userId);
	void deleteUser(Integer userId);
	UserDto getUserById(Integer userid);
	List<UserDto> getAllUser();
}
