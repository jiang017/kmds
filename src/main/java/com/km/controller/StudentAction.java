package com.km.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.km.model.User;
import com.km.redis.RedisUtil;
import com.km.service.UserService;

@Controller
public class StudentAction {
	
    @Autowired
    private UserService userServiceImpl;//持有一个业务层对象
    
    @RequestMapping("/getUserInfo")
    public ModelAndView getUserInfoByName(String userName) {
    	ModelAndView modelAndView = new ModelAndView();
    	System.out.println("请求数据userName为:" + userName);
    	String str = RedisUtil.getJedisInstall().get("userList" + userName);
    	if (str == null) {
    		str = userServiceImpl.getUserInfo(userName).toString();
    		System.out.print("该数据已经不在缓存中，需要从数据库获取");
    		RedisUtil.getJedisInstall().set("userList" + userName, str);
    		RedisUtil.getJedisInstall().expire("userList" + userName, 120);
    		System.out.println("数据已存入redis，有效时长20秒");
		} else {
			System.out.print("该数据缓存在redis中，直接获取");
		} 
    	System.out.println("获取数据结果为:" + str);
    	modelAndView.addObject("userList", str);
//        modelAndView.setViewName("show");
        return modelAndView;
    }
    
    @RequestMapping("/add")
    public ModelAndView add(String userName, int age) {
    	ModelAndView modelAndView = new ModelAndView();
    	System.out.println("请求数据userName为:" + userName + " age:" + age);
    	User user = new User();
    	user.setUserName(userName);
    	user.setAge(age);
    	userServiceImpl.addUser(user);
    	String str = userServiceImpl.getUserInfo(userName).toString();
    	RedisUtil.getJedisInstall().set("userList" + userName, str);
		RedisUtil.getJedisInstall().expire("userList" + userName, 20);
		System.out.println("新增成功");
//        modelAndView.setViewName("show");
        return modelAndView;
    }

}