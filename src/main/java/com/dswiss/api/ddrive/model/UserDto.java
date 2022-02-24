package com.dswiss.api.ddrive.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotBlank(message = "Empty userName")
	private String userName;
	
	@NotBlank(message = "Empty name")
	private String name;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Empty email")
	private String email;
	
	@NotNull(message = "Empty birth")
	@PastOrPresent(message = "Invalid birth")
	private LocalDate birth;

}
