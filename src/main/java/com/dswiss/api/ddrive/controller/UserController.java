package com.dswiss.api.ddrive.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dswiss.api.ddrive.model.UserDto;
import com.dswiss.api.ddrive.model.entity.User;
import com.dswiss.api.ddrive.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ddrive/user")
@AllArgsConstructor
public class UserController {
	
	UserService service;
	
	@GetMapping("/{userName}")
	public ResponseEntity<User> getUser(@PathVariable(name = "userName") String userName) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getUser(userName));
	}

	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDto));
	}
	
}
