package com.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BlogDao;
import com.dto.BlogDto;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.entity.UserEntity;
import com.repo.BlogRepository;
import com.repo.CommentRepository;
import com.repo.UserRepository;

@Repository
public class BlogDaoImpl implements BlogDao {

	private Logger logger=LoggerFactory.getLogger(BlogDaoImpl.class);
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private BlogRepository blogrepo;
	
	@Autowired
	private CommentRepository commentrepo;
	
	@Override
	public List<BlogEntity> getAllBlogs(Integer userId) {
		if(null!=userId)
		{
			UserEntity user=userrepo.findById(userId).get();
			return blogrepo.findByUser(user);
		}else
		{
			return blogrepo.findAll();
		}
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

	@Override
	public Optional<BlogEntity> getBlogById(Integer blogid) {
		return blogrepo.findById(blogid);
	}
	
	@Override
	public List<CommentEntity> getBlogCommentsByBlogId(Integer blogid) {
		
		BlogEntity entity = blogrepo.findById(blogid).get();
		return commentrepo.findByBlog(entity);
	}
	@Override
	public void addComment(CommentEntity commententity) {
		commentrepo.save(commententity);
	}
	
	@Override
	@Transactional
	public Integer deleteblog(Integer id) {
		Integer result=0;
		try
		{
			blogrepo.delete(blogrepo.findById(id).get());
			result=1;
		}catch(Exception e)
		{
			logger.error("Error occured while deleting blog with blogid : "+id);
		}
		return result;
	}
	
	@Override
	public Integer updateBlog(BlogDto dto)
	{
		Integer result=0;
		BlogEntity entity=new BlogEntity();
		try
		{
			Optional<BlogEntity> optentity = blogrepo.findById(dto.getBlogId());
			if(optentity.isPresent())
			{
				entity = optentity.get();
				entity.setBlogTitle(dto.getBlogTitle());
				entity.setBlogShortDesc(dto.getBlogShortDesc());
				entity.setBlogContent(dto.getBlogContent());
				blogrepo.save(entity);
				result=1;
			}
		}catch(Exception e)
		{
			logger.error("Error occured while updating blog with blogId : "+entity.getBlogId());
		}
		return result;
	}
	
	@Override
	public List<CommentEntity> getCommentsByUserId(Integer userid) {
		List<BlogEntity> entitylist = getAllBlogs(userid);
		List<CommentEntity> commentlist=new ArrayList<>();
		
		entitylist.forEach(entity->{
			List<CommentEntity> comments = commentrepo.findByBlog(entity);
			comments.forEach(comment->{
				commentlist.add(comment);
			});
		});
		return commentlist;
	}
	
	@Override
	public Integer deleteComment(Integer commentid) {
		Integer result=0;
		try {
			commentrepo.deleteById(commentid);
			result=1;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
}
