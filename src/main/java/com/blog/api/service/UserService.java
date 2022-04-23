package com.blog.api.service;

import com.blog.api.payload.*;
import java.util.*;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer id);
	void deleteUser(Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
}
