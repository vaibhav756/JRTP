package com.service;

import java.util.List;
import java.util.Map;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;

public interface EnquiryService {

	public List<String> getCourseName();
	
	public Map<String,Integer> getEnqStatus(Integer userId);
	
	public DashboardResponseDto getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryFormDto dto);
	
	public List<EnquiryFormDto> getEnquiries(Integer userId,EnquirySearchCriteria criteria);
	
	public EnquiryFormDto getEnquiry(Integer enqId);
	
}
