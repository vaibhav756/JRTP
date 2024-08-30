package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binding.PlanForm;
import com.constant.AppConstants;
import com.entity.PlanEntity;
import com.repository.PlanRepository;
import com.service.PlanService;
@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository planrepo;
	
	@Override
	public Boolean createPlan(PlanForm form) {
		PlanEntity entity = new PlanEntity();
		BeanUtils.copyProperties(form, entity);
		entity.setActiveSw(AppConstants.ACTIVATE_ACCOUNT);
		PlanEntity planentity = planrepo.save(entity);
		return null!=planentity.getPlanId()?true:false;
	}

	@Override
	public List<PlanForm> getAllPlans() {
		List<PlanEntity> allplans = planrepo.findAll();
		List<PlanForm> allplanforms=new ArrayList<>();
		
		allplans.forEach(entity->{
			PlanForm form=new PlanForm();
			BeanUtils.copyProperties(entity, form);
			allplanforms.add(form);
		});
		return allplanforms;
	}

	@Override
	public PlanForm getPlanById(Integer id) {
		PlanForm form=new PlanForm();
		Optional<PlanEntity> optplanentity = planrepo.findById(id);
		if(optplanentity.isPresent())
		{
			BeanUtils.copyProperties(optplanentity.get(),form);
		}
		return form;
	}

	@Override
	public Boolean updatePlan(PlanForm form) {

		
		
		return null;
	}

	@Override
	public Boolean actDeactPlan(Integer id) {
		Optional<PlanEntity> optplanentity = planrepo.findById(id);
		Boolean result=false;
		if(optplanentity.isPresent())
		{
			PlanEntity entity = optplanentity.get();
			if(AppConstants.ACTIVATE_ACCOUNT.equals(entity.getActiveSw()))
			{
				entity.setActiveSw(AppConstants.INACTIVATE_ACCOUNT);
			}else
			{
				entity.setActiveSw(AppConstants.ACTIVATE_ACCOUNT);
			}
			planrepo.save(entity);
			result=true;
		}
		return result;
	}

}
