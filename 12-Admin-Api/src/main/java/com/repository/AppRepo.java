package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.AppEntity;

@Repository
public interface AppRepo extends JpaRepository<AppEntity,Long>{

}
