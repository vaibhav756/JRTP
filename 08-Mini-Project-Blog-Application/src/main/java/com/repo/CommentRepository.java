package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BlogEntity;
import com.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

	List<CommentEntity> findByBlog(BlogEntity entity);

}
