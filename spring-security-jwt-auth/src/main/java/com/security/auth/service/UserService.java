package com.security.auth.service;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.security.auth.entity.UserMobile;
import com.security.auth.repository.UserRepository;

public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public void savetheEnteredmobileNumber(UserMobile userMobile)
	{
		userRepository.save(userMobile);
	}
	
	public String generateOtp(int mobileNumber)
	{
	   Optional<UserMobile> validNumber =userRepository.findbyMobileNumber(mobileNumber);
	   if(validNumber.isPresent())
	   {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entered number is not valid Otp cannot be generated");

	   }
	   UserMobile validMobileNumber = validNumber.get();
	   String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
		return otp;
	   
	}
}
