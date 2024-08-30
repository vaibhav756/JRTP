package com.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.binding.PlanForm;
import com.constant.AppConstants;
import com.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	private PlanService planservice;
	
	@PostMapping("/creteplan")
	public ResponseEntity<String> createPlan(@RequestBody PlanForm form)
	{
		Boolean result = planservice.createPlan(form);
		String msg=null;
		if(result)
		{
			msg=AppConstants.PLAN_CREATED_SUCCESSFULLY;
		}else
		{
			msg=AppConstants.PLAN_CREATION_FAILED;
		}
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/getallplans")
	public ResponseEntity<List<PlanForm>> getAllPlans()
	{
		List<PlanForm> allPlans = planservice.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	@GetMapping("/getplanbyid/{id}")
	public ResponseEntity<PlanForm> getPlanById(@PathVariable Integer id)
	{
		PlanForm plan = planservice.getPlanById(id);
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}
	
	@GetMapping("/actdeactplan/{id}")
	public ResponseEntity<List<PlanForm>> actDeactPlan(@PathVariable Integer id)
	{
		Boolean plan = planservice.actDeactPlan(id);
		List<PlanForm> allPlans = planservice.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
}