package com.km.dao;

import java.util.List;

import com.km.model.User;

public interface UserDao {

	List<User> getUserInfo(String userName);
	
	void addUser(User user);

}