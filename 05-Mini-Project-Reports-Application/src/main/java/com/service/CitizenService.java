package com.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dto.CitizenDto;

public interface CitizenService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenDto> getCitizenData(CitizenDto dto);
	
	public boolean exportExcel(CitizenDto dto,HttpServletResponse response);
	
	public boolean exportPdf(CitizenDto dto,HttpServletResponse response);
	
	public boolean sendEmail(String subject,String body,String toAddress);
	
}
