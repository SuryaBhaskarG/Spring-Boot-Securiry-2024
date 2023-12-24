package com.surya.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.surya.entity.UserInfo;
import com.surya.repository.UserInfoRepository;
import com.surya.service.UserInfoService;



@Service
public class UserInfoServiceImpl implements UserInfoService 
{

	
	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserInfo saveUser(UserInfo userInfo)
	{    
		String rawPassword = userInfo.getPassword(); // Get the raw (plain text) password
	    String encodedPassword = passwordEncoder.encode(rawPassword); // Encode the password using BCrypt
	    userInfo.setPassword(encodedPassword); // Set the encoded password back to the user object
	    return repository.save(userInfo);
	}

}
