package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.BlogEntity;
import com.entity.UserEntity;

public interface BlogRepository extends JpaRepository<BlogEntity,Integer>{

	@Query("from BlogEntity where blogTitle like %:blogtitle%")
	public List<BlogEntity> findByBlogName(String blogtitle);

	public List<BlogEntity> findByUser(UserEntity user);

}
