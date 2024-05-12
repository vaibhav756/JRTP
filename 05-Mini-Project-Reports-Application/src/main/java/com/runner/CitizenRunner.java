package com.runner;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.entity.CitizenCompositeKey;
import com.entity.CitizenPlan;
import com.repo.CitizenPlanRepository;

@Component
public class CitizenRunner implements ApplicationRunner{
	
	@Autowired
	private CitizenPlanRepository repo;
	
	private Logger logger=LoggerFactory.getLogger(CitizenRunner.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
/*		CitizenCompositeKey g1=new CitizenCompositeKey();
		g1.setCitizenName("Roshni");
		g1.setPlanName("Food");
		CitizenPlan c1=new CitizenPlan();
		c1.setCitizenId(g1.getCitizenId());
		c1.setCitizenName(g1.getCitizenName());
		c1.setPlanName(g1.getPlanName());
		c1.setGender("Fale");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(1000000.00);
		c1.setDenialReason(null);
		c1.setTerminationDate(null);
		c1.setTerminationReason(null);
		
		CitizenCompositeKey g2=new CitizenCompositeKey();
		g2.setCitizenName("Kedar");
		g2.setPlanName("Food");
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenId(g2.getCitizenId());
		c2.setCitizenName(g2.getCitizenName());
		c2.setPlanName(g2.getPlanName());
		c2.setGender("Male");
		c2.setPlanStatus("Denied");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		c2.setBenefitAmt(1000000.00);
		c2.setDenialReason("Denied due to unsufficient documents.");
		c2.setTerminationDate(LocalDate.now());
		c2.setTerminationReason(null);
		
		CitizenCompositeKey g3=new CitizenCompositeKey();
		g3.setCitizenName("Kedar");
		g3.setPlanName("Food");
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenId(g3.getCitizenId());
		c3.setCitizenName(g3.getCitizenName());
		c3.setPlanName(g3.getPlanName());
		c3.setGender("Male");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now());
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(1000000.00);
		c3.setDenialReason(null);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Terminated due to personal reason.");*/
		
		/*CitizenCompositeKey g1=new CitizenCompositeKey();
		g1.setCitizenName("Amar");
		g1.setPlanName("Medical");
		CitizenPlan c1=new CitizenPlan();
		c1.setCitizenId(g1.getCitizenId());
		c1.setCitizenName(g1.getCitizenName());
		c1.setPlanName(g1.getPlanName());
		c1.setGender("Male");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(1000000.00);
		c1.setDenialReason(null);
		c1.setTerminationDate(null);
		c1.setTerminationReason(null);
		
		CitizenCompositeKey g2=new CitizenCompositeKey();
		g2.setCitizenName("Ramesh");
		g2.setPlanName("Medical");
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenId(g2.getCitizenId());
		c2.setCitizenName(g2.getCitizenName());
		c2.setPlanName(g2.getPlanName());
		c2.setGender("Male");
		c2.setPlanStatus("Denied");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		//c2.setBenefitAmt(1000000.00);
		c2.setDenialReason("Denied due to unsufficient documents.");
		c2.setTerminationDate(LocalDate.now());
		c2.setTerminationReason(null);
		
		CitizenCompositeKey g3=new CitizenCompositeKey();
		g3.setCitizenName("Sneha");
		g3.setPlanName("Medical");
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenId(g3.getCitizenId());
		c3.setCitizenName(g3.getCitizenName());
		c3.setPlanName(g3.getPlanName());
		c3.setGender("Female");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now());
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(1000000.00);
		c3.setDenialReason(null);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Terminated due to personal reason.");*/

		/*CitizenCompositeKey g1=new CitizenCompositeKey();
		g1.setCitizenName("Rohit");
		g1.setPlanName("Employment");
		CitizenPlan c1=new CitizenPlan();
		c1.setCitizenId(g1.getCitizenId());
		c1.setCitizenName(g1.getCitizenName());
		c1.setPlanName(g1.getPlanName());
		c1.setGender("Male");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(3000000.00);
		c1.setDenialReason(null);
		c1.setTerminationDate(null);
		c1.setTerminationReason(null);
		
		CitizenCompositeKey g2=new CitizenCompositeKey();
		g2.setCitizenName("Shramesh");
		g2.setPlanName("Employment");
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenId(g2.getCitizenId());
		c2.setCitizenName(g2.getCitizenName());
		c2.setPlanName(g2.getPlanName());
		c2.setGender("Male");
		c2.setPlanStatus("Denied");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		//c2.setBenefitAmt(1000000.00);
		c2.setDenialReason("Denied due to documents not provided.");
		c2.setTerminationDate(LocalDate.now());
		c2.setTerminationReason(null);
		
		CitizenCompositeKey g3=new CitizenCompositeKey();
		g3.setCitizenName("Sonal");
		g3.setPlanName("Employment");
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenId(g3.getCitizenId());
		c3.setCitizenName(g3.getCitizenName());
		c3.setPlanName(g3.getPlanName());
		c3.setGender("Female");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now());
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(1000000.00);
		c3.setDenialReason(null);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Terminated due to emergency.");*/
		
		try
		{
			//repo.save(c1);
			//repo.save(c2);
			//repo.save(c3);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Error occured while storing data in runner class.");
		}
		
	}
}
