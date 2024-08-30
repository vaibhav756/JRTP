package com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.AppEntity;
import com.entities.UserEntity;

@Repository
public interface AppRepository extends JpaRepository<AppEntity,Long>{

	public List<AppEntity> findByUser(UserEntity user);
	
}
