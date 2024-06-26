package com.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.constant.AppConstants;
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
	
	private Logger logger=LoggerFactory.getLogger(EnquiryServiceImpl.class);
	
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
		String result=AppConstants.ERROR_MSG;
		Integer userId=(Integer)session.getAttribute(AppConstants.USER_ID);
		Optional<UserEntity> user = userrepo.findById(userId);
		StudentEnqEntity entity=new StudentEnqEntity();
		entity.setStudName(dto.getStudName());
		entity.setStudPhno(dto.getStudPhno());
		entity.setClassMode(dto.getClassMode());
		entity.setCourseName(dto.getCourseName());
		entity.setEnqStatus(dto.getEnqStatus());
		entity.setUser(user.get());
		if(null!=dto.getEnqId())
		{
			entity.setEnqId(dto.getEnqId());
		}
		StudentEnqEntity studresult = studenquiry.save(entity);
		if(null!=studresult.getEnqId())
		{
			result=AppConstants.SUCCESS_MSG;
		}else
		{
			result=AppConstants.ERROR_MSG;
		}
		return result;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		
		Optional<UserEntity> user = userrepo.findById(userId);
		List<StudentEnqEntity> studEnqList=null;
		if(null!=criteria)
		{
			//if(null!=criteria)
			StudentEnqEntity entity=new StudentEnqEntity();
			entity.setClassMode(criteria.getClassmode());
			entity.setCourseName(criteria.getCourse());
			entity.setEnqStatus(criteria.getEnqstatus());
			entity.setUser(user.get());
			Example ex=Example.of(entity);
			studEnqList = studenquiry.findAll(ex);
		}else
		{
			studEnqList = studenquiry.findByUser(user);
		}
		return studEnqList;
	}

	@Override
	public List<StudentEnqEntity> getEnquiryByUserId(Integer userId) {
		Optional<UserEntity> user = userrepo.findById(userId);
		List<StudentEnqEntity> studEnqList=null;
		if(user.isPresent())
		{
			studEnqList=studenquiry.findByUser(user);
		}
		return studEnqList;
	}

	@Override
	public EnquiryFormDto getEnquiryByEnqId(Integer enqid) {
		EnquiryFormDto dto=new EnquiryFormDto();
		try
		{
			Optional<StudentEnqEntity> optent = studenquiry.findById(enqid);
			if(optent.isPresent())
			{
				StudentEnqEntity ent=optent.get();
				dto.setEnqId(ent.getEnqId());
				dto.setStudName(ent.getStudName());
				dto.setStudPhno(ent.getStudPhno());
				dto.setClassMode(ent.getClassMode());
				dto.setCourseName(ent.getCourseName());
				dto.setEnqStatus(ent.getEnqStatus());
			}
		}catch(Exception e)
		{
			logger.error("Error occured while retrieving data from StudentEnquiry for enqid : "+enqid);
		}
		return dto;
	}
	
}
