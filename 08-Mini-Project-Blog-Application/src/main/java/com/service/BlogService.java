package com.service;

import java.util.List;

import com.dto.BlogDto;
import com.dto.CommentDto;

public interface BlogService {
	List<BlogDto> getAllBlogs(Integer userId);
	String addBlog(BlogDto dto);
	BlogDto editBlog(Integer id);
	String updateBlog(BlogDto dto);
	String deleteBlog(Integer id);
	List<BlogDto> getBlogsByName(String blogname);
	BlogDto getBlogById(Integer blogid);
	List<CommentDto> getBlogCommentsByBlogId(Integer blogid);
	void submitComment(CommentDto comment);
	List<CommentDto> getBlogCommentsByUserId(Integer userid);
	Integer deleteComment(Integer commentid);
	
}
