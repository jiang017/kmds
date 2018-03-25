package com.km.service;

import java.util.List;

import com.km.model.User;

public interface UserService {
	
	List<User> getUserInfo(String userName);
	
	void addUser(User user);
	
}