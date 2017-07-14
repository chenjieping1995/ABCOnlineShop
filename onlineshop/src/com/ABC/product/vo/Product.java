package com.ABC.product.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ABC.categorysecond.vo.CategorySecond;


/**
 * 商品的实体对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月26日  上午8:21:03
 */
public class Product {
	private Integer pid;
	private String pname;
	private Double market_price;	// 市场价
	private Double shop_price;	// 商场价
	private String image;	// 图片所存放的路径
	private String pdesc;	// 描述
	private Integer is_hot;
	private Date pdate;
	
	// 二级分类的外键:使用二级分类的对象.
	private CategorySecond categorySecond;
	// 配置商品的集合
	private Set<Product> products = new HashSet<>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

}
