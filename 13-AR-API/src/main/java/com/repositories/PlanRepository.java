package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.PlanEntity;

@Repository()
public interface PlanRepository extends JpaRepository<PlanEntity,Integer>{

}
