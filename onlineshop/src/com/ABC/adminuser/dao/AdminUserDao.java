package com.ABC.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ABC.adminuser.vo.AdminUser;

/**
 * 后台管理员登录的Dao类
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月28日  下午12:24:38
 */
public class AdminUserDao extends HibernateDaoSupport{

	// Dao完成登录的代码
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
