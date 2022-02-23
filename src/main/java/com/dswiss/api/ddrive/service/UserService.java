package com.dswiss.api.ddrive.service;

import com.dswiss.api.ddrive.model.UserDto;
import com.dswiss.api.ddrive.model.entity.User;

public interface UserService {
	
	User getUser(String userName);
	
	User save(UserDto userDto); 
}
