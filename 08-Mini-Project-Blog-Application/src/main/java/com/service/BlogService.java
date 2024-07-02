package com.service;

import java.util.List;

import com.dto.BlogDto;

public interface BlogService {
	List<BlogDto> getAllBlogs();
	String addBlog(BlogDto dto);
	BlogDto editBlog(Integer id);
	String updateBlog(BlogDto dto);
	String deleteBlog(Integer id);
	List<BlogDto> getBlogsByName(String blogname);
}
