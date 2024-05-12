package com.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CitizenDto;
import com.dto.CitizenFormBindingDto;
import com.service.CitizenService;

@Controller
public class CitizenController {

	@Autowired
	public CitizenService service;
	
	@GetMapping("/index")
	public String onloadmethod(Model model)
	{
		init(model);
		//model.addAttribute("dto", new CitizenDto());
		return "index";
	}
	
	@PostMapping("/getcitizendata")
	public ModelAndView getCitizenData(@ModelAttribute("dto") CitizenFormBindingDto bindingdto,Model model)
	{
		CitizenDto dto=new CitizenDto();
		if(!"".equals(bindingdto.getPlanName()))
			dto.setPlanName(bindingdto.getPlanName());
		if(!"".equals(bindingdto.getPlanStatus()))
			dto.setPlanStatus(bindingdto.getPlanStatus());
		if(!"".equals(bindingdto.getPlanStartDate()))
		{
			dto.setPlanStartDate(LocalDate.parse(bindingdto.getPlanStartDate()));
		}
		if(!"".equals(bindingdto.getPlanEndDate()))
		{
			dto.setPlanEndDate(LocalDate.parse(bindingdto.getPlanEndDate()));
		}
		dto.setGender(bindingdto.getGender());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		mv.addObject("citizendtolist",service.getCitizenData(dto));
		init(model);
		return mv;
	}

	private void init(Model model) {
		model.addAttribute("plans", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
	
	@ModelAttribute()
	public void setCitizenDto(Model model)
	{
		model.addAttribute("dto", new CitizenFormBindingDto());
	}
	
	@GetMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response)
	{
		//For Excel containtType=application/octet-stream and for PDF it is appilcation/PDF
		response.setContentType("application/octet-stream");
		//Content-Disposition indicate when we click on downloaded file it should open .xls file as attachment
		response.addHeader("Content-Disposition", "attachment;filename=Report.xls");
		boolean exportExcel = service.exportExcel(new CitizenDto(), response);
	}
	
	@GetMapping("exportpdf")
	public void exportPdf(HttpServletResponse response)
	{
		//For PDF ContentType would be application/pdf
		response.setContentType("application/pdf");
		//Content-Disposition indicate what would be the file type.
		response.addHeader("Content-Disposition", "attachment;filename=Report.pdf");
		
		service.exportPdf(new CitizenDto(),response);
	}
	
}
