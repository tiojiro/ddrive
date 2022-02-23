package com.dswiss.api.ddrive.mapper;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;

import com.dswiss.api.ddrive.model.UserDto;
import com.dswiss.api.ddrive.model.entity.File;
import com.dswiss.api.ddrive.model.entity.User;

public class UserMapper {

	public static User toUser(UserDto userDto) {
		var user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setFiles(new ArrayList<File>());
		return user;
	}
}
