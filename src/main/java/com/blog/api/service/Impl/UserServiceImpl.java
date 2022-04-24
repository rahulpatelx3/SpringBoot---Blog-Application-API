package com.blog.api.service.Impl;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entity.User;
import com.blog.api.payload.UserDto;
import com.blog.api.repo.UserRepo;
import com.blog.api.service.UserService;
import com.blog.api.exceptions.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		return userToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		this.userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> userList=this.userRepo.findAll();
		List<UserDto> userDtoList=userList.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return userDtoList;
	}
	
	private User dtoToUser(UserDto userDto) {
		
		// 1. Source Object 
		// 2. Destination Class Literal
		User user=this.modelMapper.map(userDto, User.class);
		
		
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDto userToUserDto(User user) {
		
		// 1. Source Object 
		// 2. Destination Class Literal
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
