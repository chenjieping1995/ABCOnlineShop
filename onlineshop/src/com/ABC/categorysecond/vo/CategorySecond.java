package com.ABC.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import com.ABC.category.vo.Category;
import com.ABC.product.vo.Product;


/**
 * 二级分类的实体对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月26日  下午12:11:48
 */
public class CategorySecond {
	
	// 二级分类的id
	private Integer csid;
	// 二级分类名称
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Category category;
	// 配置商品集合
	private Set<Product> products = new HashSet<Product>();
	
	// get，set方法
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
