package com.mb.demo.business.service;

import java.util.List;

import com.mb.demo.persistance.entity.User;
import com.mb.demo.web.model.UserModel;

public interface UserService {

	 String saveUserInformation(UserModel userModel);

	String updateUserInformation(UserModel userModel);
	List<User> getUserInformation();
	String deleteUserInformation(Integer id);
	void softDeleteUserInformation(UserModel userModel);

}
