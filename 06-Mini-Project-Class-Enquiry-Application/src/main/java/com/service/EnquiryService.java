package com.service;

import java.util.List;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;
import com.entity.StudentEnqEntity;

public interface EnquiryService {

	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public DashboardResponseDto getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryFormDto dto);
	
	public List<StudentEnqEntity> getEnquiries(Integer userId,EnquirySearchCriteria criteria);
	
	public List<StudentEnqEntity> getEnquiryByUserId(Integer userId);
	
	public EnquiryFormDto getEnquiryByEnqId(Integer enqid);
	
}
