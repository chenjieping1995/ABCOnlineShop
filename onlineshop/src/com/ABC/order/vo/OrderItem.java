package com.ABC.order.vo;

import com.ABC.product.vo.Product;

/**
 * 订单项的实体类
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月27日  下午5:22:27
 */
public class OrderItem {
	private Integer itemid;	// 自身的id
	private Integer count;	// 某商品的购买数量
	private Double subtotal;	// 小计
	private Product product;	// 商品外键:对象
	private Order order;	// 订单外键:对象
	
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
