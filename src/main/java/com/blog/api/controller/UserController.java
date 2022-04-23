package com.blog.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payload.APIResponse;
import com.blog.api.payload.UserDto;
import com.blog.api.service.Impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	//Post - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto dto=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(dto,HttpStatus.CREATED);
	}
	
	//Put - update user
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("id") int id){
		UserDto dto=this.userService.updateUser(userDto,id);
		return ResponseEntity.ok(dto);
	}
	
	
	//Delete - delete user
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable int id){
		this.userService.deleteUser(id);
		return new ResponseEntity<APIResponse>(new APIResponse("user deleted successfully",true),HttpStatus.OK);
	}
	
	//Get - get user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getAllUser(@PathVariable int id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	//Get - get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
}
