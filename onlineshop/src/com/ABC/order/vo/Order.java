package com.ABC.order.vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.ABC.user.vo.User;

/**
 * 订单模块的实体类对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月27日  下午5:18:35
 */
public class Order {
	private Integer oid;
	private Double total;	// 总金额
	private Timestamp ordertime;	// 订单时间
	private Integer state;	// 订单状态   1:未付款   2:订单已经付款   3:已经发货   4:订单结束
	private String name;	// 收货人姓名
	private String phone;	// 收货人电话
	private String addr;	// 收货人地址
	private User user;	// 用户的外键:对象
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();	// 配置订单项的集合
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp string) {
		this.ordertime = string;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
