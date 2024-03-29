package com.mahesh.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.blog.payloads.ApiResponse;
import com.mahesh.blog.payloads.UserDto;
import com.mahesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//  for creating user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}

	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId  ){
		
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
		
		
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
	return ResponseEntity.ok(this.userService.getAllUser());
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		
	return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	}


