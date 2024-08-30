package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.UserEntity;

@Repository()
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	public UserEntity findByEmail(String email);

	public UserEntity findByEmailAndPwd(String username, String pwd);

}
