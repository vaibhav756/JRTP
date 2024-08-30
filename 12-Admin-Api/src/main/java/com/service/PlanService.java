package com.service;

import java.util.List;

import com.binding.PlanForm;

public interface PlanService {

	public Boolean createPlan(PlanForm form);
	public List<PlanForm> getAllPlans();
	public PlanForm getPlanById(Integer id);
	public Boolean updatePlan(PlanForm form);
	public Boolean actDeactPlan(Integer id);
	
}
