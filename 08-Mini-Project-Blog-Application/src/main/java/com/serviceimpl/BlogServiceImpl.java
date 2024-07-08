package com.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.AppConstants;
import com.dao.BlogDao;
import com.dto.BlogDto;
import com.dto.CommentDto;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.repo.BlogRepository;
import com.service.BlogService;
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogrepo;
	
	@Autowired
	private BlogDao blogdao;
	
	@Override
	public List<BlogDto> getAllBlogs(Integer userid) {
		List<BlogEntity> allentity = blogdao.getAllBlogs(userid);
		List<BlogDto> alldto=new ArrayList<BlogDto>();
		allentity.forEach(entity->{
			BlogDto dto=new BlogDto();
			BeanUtils.copyProperties(entity,dto);
			alldto.add(dto);
		});
		return alldto;
	}

	@Override
	public String addBlog(BlogDto dto) {
		dto.setBlogFlag(1);
		dto.setCrtnTime(LocalDate.now());
		BlogEntity entity=new BlogEntity();
		BeanUtils.copyProperties(dto, entity);
		return blogdao.addBlog(entity)>0?AppConstants.SUCCESS_MSG:AppConstants.ERROR_MSG;
	}

	@Override
	public BlogDto editBlog(Integer blogid) {
		BlogDto dto=new BlogDto();
		Optional<BlogEntity> blogentity = blogdao.getBlogById(blogid);
		if(blogentity.isPresent())
		{
			BeanUtils.copyProperties(blogentity.get(), dto);
		}
		return dto;
	}

	@Override
	public String updateBlog(BlogDto dto) {
		return blogdao.updateBlog(dto)==1?AppConstants.SUCCESS_MSG:AppConstants.ERROR_MSG;
	}

	@Override
	public String deleteBlog(Integer id) {
		return blogdao.deleteblog(id)>0?AppConstants.SUCCESS_MSG:AppConstants.ERROR_MSG;
	}	
	
	@Override
	public List<BlogDto> getBlogsByName(String blogname) {
		List<BlogEntity> allentity=blogdao.getBlogByName(blogname);
		List<BlogDto> alldto=new ArrayList<>();
		if(!allentity.isEmpty())
		{
			allentity.forEach(entity->{
				BlogDto dto=new BlogDto();
				BeanUtils.copyProperties(entity,dto);
				alldto.add(dto);
			});
		}
		return alldto;
	}

	@Override
	public BlogDto getBlogById(Integer blogid) {
		Optional<BlogEntity> entity=blogdao.getBlogById(blogid);
		BlogDto dto=new BlogDto();
		if(entity.isPresent())
		{
			BeanUtils.copyProperties(entity.get(),dto);
		}
		return dto;
	}
	
	@Override
	public List<CommentDto> getBlogCommentsByBlogId(Integer blogid) {
		List<CommentDto> dtolist=new ArrayList<CommentDto>();
		List<CommentEntity> entitylist = blogdao.getBlogCommentsByBlogId(blogid);
		if(!entitylist.isEmpty())
		{
			entitylist.forEach(entity->{
				CommentDto dto=new CommentDto();
				if(entity.getDeleteFlag()==0)
				{
					BeanUtils.copyProperties(entity, dto);
					dtolist.add(dto);					
				}
			});
		}
		return dtolist;
	}
	
	@Override
	public void submitComment(CommentDto comment) {
		BlogEntity blogentity = blogdao.getBlogById(comment.getBlogid()).get();
		CommentEntity commententity=new CommentEntity();
		commententity.setName(comment.getName());
		commententity.setEmail(comment.getEmail());
		commententity.setComment(comment.getComment());
		commententity.setBlog(blogentity);
		commententity.setCrtnTime(LocalDate.now());
		blogdao.addComment(commententity);
	}
	
	@Override
	public List<CommentDto> getBlogCommentsByUserId(Integer userid) {
		List<CommentDto> dtolist=new ArrayList<>();
		List<CommentEntity> commentlist = blogdao.getCommentsByUserId(userid);
		if(commentlist.size()>0)
		{
			commentlist.forEach(comment->{
				CommentDto dto=new CommentDto();
				BeanUtils.copyProperties(comment, dto);
				dtolist.add(dto);
			});
		}
		return dtolist;
	}
	
	@Override
	public Integer deleteComment(Integer commentid) {
		return blogdao.deleteComment(commentid);
	}
	
}
