package com.dswiss.api.ddrive.service.impl;

import org.springframework.stereotype.Service;

import com.dswiss.api.ddrive.exception.ResourceNotFoundException;
import com.dswiss.api.ddrive.mapper.UserMapper;
import com.dswiss.api.ddrive.model.UserDto;
import com.dswiss.api.ddrive.model.entity.User;
import com.dswiss.api.ddrive.repository.UserRepository;
import com.dswiss.api.ddrive.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;

	@Override
	public User getUser(String userName) {
		return repository.findById(userName).orElseThrow(() -> new ResourceNotFoundException("UserName '" + userName + "' not found"));
	}
	
	@Override
	public User save(UserDto userDto) {
		return repository.save(UserMapper.toUser(userDto));
	}

}
