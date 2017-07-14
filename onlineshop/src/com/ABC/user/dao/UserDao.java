package com.ABC.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ABC.user.vo.User;

/**
 * 用户模块持久层代码
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  上午8:43:05
 */
public class UserDao extends HibernateDaoSupport{

	// 按用户名来查询是否有该用户
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size()>0) {
			// 查询到同名用户
			return list.get(0);
		}
		return null;
	}

	// 注册用户信息存入数据库的实现
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if (list != null && list.size()>0) {
			// 查询到该用户的信息
			return list.get(0);
		}
		return null;
	}

	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/**
	 * 用户登录的方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
