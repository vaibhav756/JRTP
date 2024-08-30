package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.EligEntity;

@Repository()
public interface EligibilityRepository extends JpaRepository<EligEntity,Integer>{

}
