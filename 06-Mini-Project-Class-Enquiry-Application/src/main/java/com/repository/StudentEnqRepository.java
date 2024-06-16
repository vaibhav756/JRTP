package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.StudentEnqEntity;
import com.entity.UserEntity;

@Repository
public interface StudentEnqRepository extends JpaRepository<StudentEnqEntity, Integer> {

	List<StudentEnqEntity> findByUser(Optional<UserEntity> user);

}
