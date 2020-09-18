package com.security.auth.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.auth.entity.AuthenticationRequest;
import com.security.auth.entity.AuthenticationResponse;
import com.security.auth.entity.UserMobile;
import com.security.auth.security.JwtToken;
import com.security.auth.service.UserDetailService;
import com.security.auth.service.UserService;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtToken jwtToken;

	@Autowired
	private UserService userService;

	HashMap<String, Integer> mob = new HashMap<>();

	@Autowired
	private UserDetailService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtToken.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/phonenumber")
	public String enterRequiredNumber(@RequestBody UserMobile userMobile) {
		userService.savetheEnteredmobileNumber(userMobile);
		return "Valid mobile number has been entered";
	}

	@GetMapping("/otp")
	public String generateOtp(int mobileNumber) {

		return userService.generateOtp(mobileNumber);

	}

}
