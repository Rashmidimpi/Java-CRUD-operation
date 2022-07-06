package com.mb.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.demo.business.service.MyUserDetailsService;
import com.mb.demo.business.service.UserService;
import com.mb.demo.web.jwt.JwtUtil;
import com.mb.demo.web.model.AuthenticationResponse;
import com.mb.demo.web.model.LoginRequest;

@RestController
@RequestMapping(path = "user")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
		System.out.println("logs");
		return userService.createAuthenticationToken(authenticationRequest);
		
	}
	
	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}

}
