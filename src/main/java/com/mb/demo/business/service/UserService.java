package com.mb.demo.business.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mb.demo.persistance.entity.UserEntity;
import com.mb.demo.web.model.LoginRequest;
import com.mb.demo.web.model.UserModel;

public interface UserService {

	 String saveUserInformation(UserModel userModel);

	String updateUserInformation(UserModel userModel);
	List<UserEntity> getUserInformation();
	String deleteUserInformation(Integer id);
	void softDeleteUserInformation(UserModel userModel);

	ResponseEntity<?> createAuthenticationToken(LoginRequest authenticationRequest) throws Exception;

}
