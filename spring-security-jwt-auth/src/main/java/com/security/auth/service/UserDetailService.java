package com.security.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.auth.repository.UserRepository;

public class UserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	  @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	        return new User("foo", "foo",
	                new ArrayList<>());
	    } 
	

}
