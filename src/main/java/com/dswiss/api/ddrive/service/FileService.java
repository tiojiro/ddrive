package com.dswiss.api.ddrive.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dswiss.api.ddrive.model.FileDto;
import com.dswiss.api.ddrive.model.entity.File;

public interface FileService {
	
	List<File> getAllFiles();
	
	File getFile(Long id);
	
	List<File> getFilesByUser(String userName);
	
	File save(FileDto fileDto, MultipartFile fileData, String userName);
	
	File upload(MultipartFile fileData, Long id);
	
	File update(FileDto fileDto, MultipartFile fileData, Long id);
	
	void delete(Long id);
}
