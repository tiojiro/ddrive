package com.dswiss.api.ddrive.mapper;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dswiss.api.ddrive.exception.ResourceNotFoundException;
import com.dswiss.api.ddrive.model.FileDto;
import com.dswiss.api.ddrive.model.entity.File;
import com.dswiss.api.ddrive.model.entity.User;

public class FileMapper {
	
	private static final String FILE_ERROR = "File data access error";
	
	public static File toFile(FileDto fileDto, MultipartFile fileData, File file) {
		BeanUtils.copyProperties(fileDto, file);
		try {
			file.setFileData(fileData.getBytes());
		} catch (IOException e) {
			throw new ResourceNotFoundException(FILE_ERROR);
		}
		file.setLastUpdated(LocalDateTime.now());
		return file;
	}

	public static File toFile(FileDto fileDto, MultipartFile fileData, User user) {
		var file = new File();
		BeanUtils.copyProperties(fileDto, file);
		try {
			file.setFileData(fileData.getBytes());
		} catch (IOException e) {
			throw new ResourceNotFoundException(FILE_ERROR);
		}
		file.setLastUpdated(LocalDateTime.now());
		file.setUser(user);
		return file;
	}
	
	public static File toFile(MultipartFile fileData, File file) {
		file.setName(fileData.getResource().getFilename());
		try {
			file.setFileData(fileData.getBytes());
		} catch (IOException e) {
			throw new ResourceNotFoundException(FILE_ERROR);
		}
		file.setLastUpdated(LocalDateTime.now());
		return file;
	}
}
