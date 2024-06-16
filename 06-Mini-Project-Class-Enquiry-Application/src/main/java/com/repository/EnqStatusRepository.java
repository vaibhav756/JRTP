package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.EnqStatusEntity;

@Repository
public interface EnqStatusRepository extends JpaRepository<EnqStatusEntity, Integer> {

}
