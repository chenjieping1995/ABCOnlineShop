package com.ABC.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.ABC.adminuser.dao.AdminUserDao;
import com.ABC.adminuser.vo.AdminUser;

/**
 * 后台管理员的业务层类
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月28日  下午12:24:12
 */
@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
