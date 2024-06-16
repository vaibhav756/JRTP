package com.service;

import java.util.List;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;

public interface EnquiryService {

	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public DashboardResponseDto getDashboardData(Integer userId);
	
	public String addEnquiry(EnquiryFormDto dto);
	
	public List<EnquiryFormDto> getEnquiries(Integer userId,EnquirySearchCriteria criteria);
	
	public EnquiryFormDto getEnquiry(Integer enqId);
	
}
