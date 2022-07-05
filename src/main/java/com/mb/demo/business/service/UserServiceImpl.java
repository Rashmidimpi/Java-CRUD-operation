package com.mb.demo.business.service;

import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.demo.business.constant.DemoConstant;
import com.mb.demo.persistance.entity.UserEntity;
import com.mb.demo.persistance.repository.UserRepository;
import com.mb.demo.web.model.UserModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private Mapper mapper;
	
	
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
	
	

}
