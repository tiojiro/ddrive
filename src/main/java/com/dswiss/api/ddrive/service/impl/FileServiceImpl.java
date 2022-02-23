package com.dswiss.api.ddrive.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dswiss.api.ddrive.exception.ResourceNotFoundException;
import com.dswiss.api.ddrive.mapper.FileMapper;
import com.dswiss.api.ddrive.model.FileDto;
import com.dswiss.api.ddrive.model.entity.File;
import com.dswiss.api.ddrive.model.entity.User;
import com.dswiss.api.ddrive.repository.FileRepository;
import com.dswiss.api.ddrive.repository.UserRepository;
import com.dswiss.api.ddrive.service.FileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
	
	private FileRepository repository;
	private UserRepository userRepository;

	@Override
	public List<File> getAllFiles() {
		return repository.findAll();
	}
	
	@Override
	public File getFile(Long id) {
		File file = findFile(id);
		return file;
	}
	
	@Override
	public List<File> getFilesByUser(String userName) {
		if(!userRepository.existsById(userName)) {
			throw new ResourceNotFoundException("User '" + userName + "' not found");
		}
		return repository.findByUserUserName(userName);
	}

	@Override
	public File save(FileDto fileDto, MultipartFile fileData, String userName) {
		if(!userRepository.existsById(userName)) {
			throw new ResourceNotFoundException("User '" + userName + "' not found");
		}
		User user = userRepository.getById(userName);
		return repository.save(FileMapper.toFile(fileDto, fileData, user));
	}
	
	@Override
	public File upload(MultipartFile fileData, Long id) {
		File file = findFile(id);
		return repository.save(FileMapper.toFile(fileData, file));
	}

	@Override
	public File update(FileDto fileDto, MultipartFile fileData, Long id) {
		File file = findFile(id);
		return repository.save(FileMapper.toFile(fileDto, fileData, file));
	}
	
	@Override
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("File id '" + id + "' not found");
		}
		repository.deleteById(id);
	}

	private File findFile(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File id '" + id + "' not found"));
	}

}
