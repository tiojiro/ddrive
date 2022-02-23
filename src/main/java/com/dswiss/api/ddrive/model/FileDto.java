package com.dswiss.api.ddrive.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class FileDto {
	
	@NotBlank(message = "Empty name")
	private String name;

	private String description;

	@NotBlank(message = "Empty path")
	private String path;

}
