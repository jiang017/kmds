package com.km.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.km.dao.UserDao;
import com.km.model.User;
import com.km.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Resource
	private UserDao userDao;

	@Override
	public List<User> getUserInfo(String userName) {
		return userDao.getUserInfo(userName);
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

}