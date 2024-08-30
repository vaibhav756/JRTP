package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.PlanEntity;

@Repository()
public interface PlanRepository extends JpaRepository<PlanEntity,Integer>{

}
