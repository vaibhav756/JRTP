package com.dao;

import java.util.List;
import java.util.Optional;

import com.dto.BlogDto;
import com.entity.BlogEntity;
import com.entity.CommentEntity;

public interface BlogDao {
	
	List<BlogEntity> getAllBlogs(Integer userid);
	
	Integer addBlog(BlogEntity entity);

	List<BlogEntity> getBlogByName(String blogname);

	Optional<BlogEntity> getBlogById(Integer blogid);

	List<CommentEntity> getBlogCommentsByBlogId(Integer blogid);

	void addComment(CommentEntity commententity);

	Integer deleteblog(Integer id);

	Integer updateBlog(BlogDto entity);
	
	List<CommentEntity> getCommentsByUserId(Integer userid);
	
	Integer deleteComment(Integer commentid);
	
}
