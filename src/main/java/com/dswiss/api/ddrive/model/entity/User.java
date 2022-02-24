package com.dswiss.api.ddrive.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private LocalDate birth;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<File> files;
}
