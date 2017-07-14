package com.ABC.user.service;

import com.ABC.user.dao.UserDao;
import com.ABC.user.vo.User;
import com.ABC.utils.MailUtils;
import com.ABC.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  上午8:42:46
 */
public class UserService {
	// 注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 调用按用户名查询用户的方法
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	// 业务层完成用户注册的方法
	public void save(User user) {
		// 将数据存入到数据库中去
		user.setState(0); // 0：用户未激活，      1：用户已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		
		// 调用dao完成插入的操作
		userDao.save(user);
		
		// 发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	// 业务层根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 业务层修改用户状态的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	public User login(User user) {
		return userDao.login(user);
	}
}
