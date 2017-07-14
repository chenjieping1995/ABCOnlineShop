package com.ABC.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.ABC.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体类对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  下午8:04:03
 */
public class Category {

	private Integer cid;
	private String cname;
	
	// 一级分类中二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
