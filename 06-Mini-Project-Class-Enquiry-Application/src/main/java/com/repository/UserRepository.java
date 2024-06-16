package com.repository;

import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

}
