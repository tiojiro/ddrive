package com.dswiss.api.ddrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dswiss.api.ddrive.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
