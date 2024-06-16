package com.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;
import com.entity.StudentEnqEntity;
import com.entity.UserEntity;
import com.repository.StudentEnqRepository;
import com.repository.UserRepository;
import com.service.EnquiryService;
@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private StudentEnqRepository studenquiry;
	
	@Autowired
	private UserRepository userrepo;
	
	@Override
	public List<String> getCourseName() {
		return null;
	}

	@Override
	public Map<String,Integer> getEnqStatus(Integer userId) {
		
		Optional<UserEntity> user = userrepo.findById(userId);
		Map<String,Integer> enqdetails=new HashMap<>();
		if(user.isPresent())
		{
			List<StudentEnqEntity> studEnqList = studenquiry.findByUser(user);
			if(studEnqList.size()>0)
			{
				enqdetails.put("total", studEnqList.size());
				enqdetails.put("enrolled", studEnqList.stream().filter(enq->enq.getEnqStatus().equals("enrolled")).collect(Collectors.toList()).size());
				enqdetails.put("lost", studEnqList.stream().filter(enq->enq.getEnqStatus().equals("lost")).collect(Collectors.toList()).size());
			}else
			{
				enqdetails.put("total", 0);
				enqdetails.put("enrolled", 0);
				enqdetails.put("lost", 0);
			}
		}
		return enqdetails;
	}

	@Override
	public DashboardResponseDto getDashboardData(Integer userId) {
		return null;
	}

	@Override
	public String upsertEnquiry(EnquiryFormDto dto) {
		return null;
	}

	@Override
	public List<EnquiryFormDto> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		return null;
	}

	@Override
	public EnquiryFormDto getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
