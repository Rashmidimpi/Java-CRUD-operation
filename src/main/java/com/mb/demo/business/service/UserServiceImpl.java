package com.mb.demo.business.service;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mb.demo.business.constant.DemoConstant;
import com.mb.demo.persistance.entity.UserEntity;
import com.mb.demo.persistance.repository.UserRepository;
import com.mb.demo.web.jwt.JwtUtil;
import com.mb.demo.web.model.AuthenticationResponse;
import com.mb.demo.web.model.LoginRequest;
import com.mb.demo.web.model.UserModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private Mapper mapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	@Override
	public String saveUserInformation(UserModel userModel) {
		// TODO Auto-generated method stub
		
		UserEntity user = mapper.map(userModel, UserEntity.class);
		userRepository.save(user);
	     return DemoConstant.MSG_SUCCESS;
	}
	
//	public UserModel saveUserInformation(UserModel userModel) {
//		return userRepository.save(userModel);
//	}


	@Override
	public String updateUserInformation(UserModel userModel) {
		// TODO Auto-generated method stub

		UserEntity user = mapper.map(userModel, UserEntity.class);
		userRepository.save(user);
		return DemoConstant.MSG_SUCCESS;
	}


	@Override
	public List<UserEntity> getUserInformation() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}


	@Override
	public String deleteUserInformation(Integer id) {
		// TODO Auto-generated method stub
//		User user = mapper.map(userModel, User.class);
		Optional<UserEntity> user = userRepository.findById(id);
		if(user.isPresent()) {
		userRepository.delete(user.get());
		return DemoConstant.MSG_SUCCESS;
		} else {
			return DemoConstant.MSG_ERROR;
		}
	}


	@Override
	public void softDeleteUserInformation(UserModel userModel) {
		// TODO Auto-generated method stub
		UserEntity user = mapper.map(userModel, UserEntity.class);
		user.setDeleted(true);
		userRepository.save(user);
		
	}

	@Override
	public ResponseEntity<?> createAuthenticationToken(LoginRequest authenticationRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println("logs try");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		System.out.println("logs final");
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	

}
