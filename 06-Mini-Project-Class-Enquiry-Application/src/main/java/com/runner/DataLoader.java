package com.runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.CourseEntity;
import com.entity.EnqStatusEntity;
import com.repository.CourseRepository;
import com.repository.EnqStatusRepository;

@Component
public class DataLoader {

	@Autowired
	private CourseRepository courserepo;
	
	@Autowired
	private EnqStatusRepository statusrepo;
	
	@PostConstruct
	public void LoadCourseAndStatus()
	{
		CourseEntity c1=new CourseEntity(1,"");
		CourseEntity c2=new CourseEntity(2,"");
		CourseEntity c3=new CourseEntity(3,"");
		CourseEntity c4=new CourseEntity(4,"");
		CourseEntity c5=new CourseEntity(5,"");
		courserepo.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
		
		EnqStatusEntity s1=new EnqStatusEntity(1,"");
		EnqStatusEntity s2=new EnqStatusEntity(2,"");
		EnqStatusEntity s3=new EnqStatusEntity(3,"");
		statusrepo.saveAll(Arrays.asList(s1,s2,s3));
		
	}
	
}
