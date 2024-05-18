package com.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dto.CitizenDto;
import com.entity.CitizenPlan;
import com.repo.CitizenPlanRepository;
import com.service.CitizenService;
import com.utils.EmailUtils;
import com.utils.ExcelGenerator;
import com.utils.PDFGenerator;

import lombok.extern.slf4j.Slf4j;
@Service()
@Slf4j
public class CitizenServiceImpl implements CitizenService {

	
	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private ExcelGenerator excelgenerator;
	
	@Autowired
	private PDFGenerator pdfgenerator;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Override
	public List<String> getPlanNames() {
		return repo.getPlans();
	}
	
	@Override
	public List<String> getPlanStatus() {
		return repo.getStatus();
	}
	
	@Override
	public List<CitizenDto> getCitizenData(CitizenDto dto) {
		log.info("Inside getCitizenData method of CitizenServiceImpl with dto : "+dto);
		CitizenPlan plan=new CitizenPlan();
		List<CitizenDto> dtolist=new ArrayList<CitizenDto>();
		BeanUtils.copyProperties(dto, plan);
		List<CitizenPlan> planlist=new ArrayList<CitizenPlan>();
		Pageable page = Pageable.ofSize(10);
		if(null==dto)
			planlist=repo.findAll(page).getContent();
		else
		{
			planlist=repo.findAll(Example.of(plan),page).getContent();
		}
		planlist.stream().forEach(item->{
			CitizenDto dtocopy=new CitizenDto();
			BeanUtils.copyProperties(item, dtocopy);
			dtolist.add(dtocopy);
			});	
		return dtolist;
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean exportExcel(CitizenDto dto,HttpServletResponse response) {
		List<CitizenPlan> citizendata = repo.findAll();
		boolean result = excelgenerator.generateExcelFile(response, citizendata);
		String subject="Citizen Plan Data";
		String body="<h6><i>Hi User,Please find attached excel file for Citizen plan.</i></h6>";
		String toAddress="yvaibhav7890@gmail.com";
		File file=new File("Report.xls");
		emailutils.sendEmail(subject, body, toAddress, file);
		//Remove file after sending email
		file.delete();
		return result;
	}
	
	@Override
	public boolean exportPdf(CitizenDto dto,HttpServletResponse response) {
		List<CitizenPlan> citizendata = repo.findAll();
		boolean result = pdfgenerator.generatePdfFile(response, citizendata);
		String subject="Citizen Plan Data in PDF format";
		String body="<h3><i>Hi User,Please find attached pdf file for citizen plan.</i></h3>";
		String toAddress="yvaibhav7890@gmail.com";
		File file=new File("CitizenPlan.pdf");
		emailutils.sendEmail(subject, body, toAddress, file);
		//Remove file after use
		file.delete();
		return result;
	}

	@Override
	public boolean sendEmail(String subject, String body, String toAddress) {
		return emailutils.sendEmail(subject, body, toAddress, null);
	}
	
}
