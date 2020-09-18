package com.security.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.auth.entity.UserMobile;

public interface UserRepository extends JpaRepository<UserMobile,Long >
{
	
	Optional<UserMobile> findbyMobileNumber(int mobileNumber);
	
	

}
