package com.ABC.cart.vo;

import com.ABC.product.vo.Product;

/**
 * 购物项的对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月27日  上午8:47:26
 */
public class CartItem {
	private Product product;	// 购物项中商品信息
	private int count;			// 购买某种商品数量
	private double subtotal;	// 购买某种商品小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	// 小计应该是自动计算的.
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	*/
	
}
