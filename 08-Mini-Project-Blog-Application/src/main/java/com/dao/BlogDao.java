package com.dao;

import java.util.List;

import com.entity.BlogEntity;

public interface BlogDao {
	
	List<BlogEntity> getAllBlogs();
	
	Integer addBlog(BlogEntity entity);

	List<BlogEntity> getBlogByName(String blogname);
	
	
}
