package com.dswiss.api.ddrive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dswiss.api.ddrive.model.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

	@Transactional
	List<File> findByUserUserName(String userName);

}
