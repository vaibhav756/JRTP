package com.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BlogDao;
import com.entity.BlogEntity;
import com.repo.BlogRepository;

@Repository
public class BlogDaoImpl implements BlogDao {

	private Logger logger=LoggerFactory.getLogger(BlogDaoImpl.class);
	
	@Autowired
	private BlogRepository blogrepo;
	
	@Override
	public List<BlogEntity> getAllBlogs() {
		return blogrepo.findAll();
	}
	
	@Override
	public Integer addBlog(BlogEntity entity) {
		Integer result=0;
		try
		{
			blogrepo.save(entity);
			result=1;
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Error occured while storing blog with blog title : "+entity.getBlogTitle(),e);
		}
		return result;
	}
	
	@Override
	public List<BlogEntity> getBlogByName(String blogname) {
		return blogrepo.findByBlogName(blogname);
	}

}
