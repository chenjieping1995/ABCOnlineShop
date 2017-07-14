package com.ABC.adminuser.vo;

/**
 * 后台管理员实体类
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月28日  下午12:20:15
 */
public class AdminUser {
	private Integer uid;
	private String username;
	private String password;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
