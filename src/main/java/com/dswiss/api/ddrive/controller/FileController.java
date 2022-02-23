package com.dswiss.api.ddrive.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dswiss.api.ddrive.model.FileDto;
import com.dswiss.api.ddrive.model.entity.File;
import com.dswiss.api.ddrive.service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ddrive")
@AllArgsConstructor
public class FileController {

	FileService service;
	
	@GetMapping("/file")
	public ResponseEntity<List<File>> getAllFiles() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllFiles());
	}
	
	@GetMapping("/file/{id}")
	public ResponseEntity<File> getFile(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getFile(id));
	}

	@GetMapping("/user/{userName}/file")
	public ResponseEntity<List<File>> getFilesByUser(@PathVariable(value = "userName") String userName) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getFilesByUser(userName));
	}

	@PostMapping("/user/{userName}/file")
	public ResponseEntity<File> save(@RequestPart(name = "fileDto") @Valid FileDto fileDto, @RequestPart(name = "file") MultipartFile fileData, @PathVariable(name = "userName") String userName) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(fileDto, fileData, userName));
	}
	
	@PatchMapping("/file/{id}")
	public ResponseEntity<File> upload(@RequestParam("file") MultipartFile fileData, @PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.upload(fileData, id));
	}
	
	@PutMapping("/file/{id}")
	public ResponseEntity<File> update(@RequestPart(name = "fileDto") @Valid FileDto fileDto, @RequestPart(name = "file") MultipartFile fileData, @PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(fileDto, fileData, id));
	}
	
	@DeleteMapping("/file/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
