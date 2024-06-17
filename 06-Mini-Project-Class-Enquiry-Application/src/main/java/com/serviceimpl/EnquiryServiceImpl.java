package com.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DashboardResponseDto;
import com.dto.EnquiryFormDto;
import com.dto.EnquirySearchCriteria;
import com.entity.CourseEntity;
import com.entity.EnqStatusEntity;
import com.entity.StudentEnqEntity;
import com.entity.UserEntity;
import com.repository.CourseRepository;
import com.repository.EnqStatusRepository;
import com.repository.StudentEnqRepository;
import com.repository.UserRepository;
import com.service.EnquiryService;
@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private StudentEnqRepository studenquiry;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private CourseRepository courserepo;
	
	@Autowired
	private EnqStatusRepository enqstatusrepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<String> getCourseName() {
		List<CourseEntity> courses = courserepo.findAll();
		return courses.stream().map(course->course.getCourseName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getEnqStatus() {
		List<EnqStatusEntity> enqstatus = enqstatusrepo.findAll();
		return enqstatus.stream().map(status->status.getStatusName()).collect(Collectors.toList());
	}

	@Override
	public DashboardResponseDto getDashboardData(Integer userId) {
		Optional<UserEntity> user = userrepo.findById(userId);
		Map<String,Integer> enqdetails=new HashMap<>();
		DashboardResponseDto dto=new DashboardResponseDto();
		if(user.isPresent())
		{
			List<StudentEnqEntity> studEnqList = studenquiry.findByUser(user);
			if(studEnqList.size()>0)
			{
				dto.setTotalEnqCnt(studEnqList.size());
				dto.setEnrolledCnt(studEnqList.stream().filter(enq->enq.getEnqStatus().equals("Enrolled")).collect(Collectors.toList()).size());
				dto.setLost(studEnqList.stream().filter(enq->enq.getEnqStatus().equals("Lost")).collect(Collectors.toList()).size());
			}else
			{
				dto.setTotalEnqCnt(0);
				dto.setEnrolledCnt(0);
				dto.setLost(0);
			}
		}
		return dto;
	}

	@Override
	public String upsertEnquiry(EnquiryFormDto dto) {
		String result="fail";
		Integer userId=(Integer)session.getAttribute("userid");
		Optional<UserEntity> user = userrepo.findById(userId);
		StudentEnqEntity entity=new StudentEnqEntity();
		entity.setStudName(dto.getStudName());
		entity.setStudPhno(dto.getStudPhno());
		entity.setClassMode(dto.getClassMode());
		entity.setCourseName(dto.getCourseName());
		entity.setEnqStatus(dto.getEnqStatus());
		entity.setUser(user.get());
		StudentEnqEntity studresult = studenquiry.save(entity);
		if(null!=studresult.getEnqId())
		{
			result="success";
		}else
		{
			result="fail";
		}
		return result;
	}

	@Override
	public List<EnquiryFormDto> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		
		Optional<UserEntity> user = userrepo.findById(userId);
		List<StudentEnqEntity> studEnqList=null;
		if(null!=criteria)
		{
			//if(null!=criteria)
		}else
		{
			studEnqList = studenquiry.findByUser(user);
		}
		return null;
	}

	@Override
	public List<StudentEnqEntity> getEnquiry(Integer enqId) {
		Optional<UserEntity> user = userrepo.findById(enqId);
		List<StudentEnqEntity> studEnqList=null;
		if(user.isPresent())
		{
			studEnqList=studenquiry.findByUser(user);
		}
		return studEnqList;
	}

}
