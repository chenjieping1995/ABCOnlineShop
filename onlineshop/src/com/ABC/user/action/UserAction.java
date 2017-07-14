package com.ABC.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ABC.user.service.UserService;
import com.ABC.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块的Action类
 * @author 陈
 * 实现模型驱动
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 模型驱动要使用的对象
	 */
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	/**
	 * 接收验证码
	 */
	private String checkcode;
	// struts2里面的属性驱动
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * 业务层userService属性
	 */
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {
		return "registPage";
	}
	
	/**
	 * AJAX进行异步校验用户名的执行方法
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		// 调用service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置输出字符集的编码格式
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser!=null){
			// 查询到了该用户的记录，用户名已经存在
			response.getWriter().println("<font color='red'>该用户名已经存在！</font>");
		} else {
			// 未查询到用户，该用户名可以使用
			response.getWriter().println("<font color='green'>该用户名可以使用！</font>");
		}
		return NONE;
	}
	
	/**
	 * 用户注册的方法
	 */
	public String regist() {
		// 判断验证码程序
		// 从session中获得验证码的随机值
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode1.equalsIgnoreCase(checkcode)) {
			this.addActionError("验证码输入错误！");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("账号注册成功，请前往邮箱激活账号！");
		return "msg";
	}
	
	/**
	 * 用户激活的方法
	 */
	public String active() {
		// 根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			// 激活码是错误的
			this.addActionMessage("激活失败");
		} else {
			// 激活成功
			// 修改用户状态
			user.setState(1);
			user.setCode(null); // 一次激活，永久有效
			userService.update(existUser);
			this.addActionMessage("激活成功！请前往登录。");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录页面
	 */
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * 登录的方法
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// 登录失败
			this.addActionError("登录失败：用户名与密码不匹配或用户未激活！");
			return LOGIN;
		} else {
			// 登录成功
			// 将用户信息存入到session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			// 完成页面跳转
			return "loginSuccess";
		}
	}
	
	/**
	 * 用户退出的方法
	 */
	public String quit() {
		// 销毁Session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
}
