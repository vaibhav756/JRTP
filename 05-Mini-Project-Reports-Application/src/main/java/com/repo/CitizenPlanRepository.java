package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.CitizenCompositeKey;
import com.entity.CitizenPlan;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan,CitizenCompositeKey>{

	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlans();
	
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getStatus();
	
}
