package com.ABC.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ABC.category.vo.Category;

/**
 * 一级分类的持久层对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  下午8:18:09
 */
public class CategoryDao extends HibernateDaoSupport{

	// Dao层查询所有一级分类的方法
	public List<Category> findALL() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// DAO层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	// Dao层查询一级分类的方法
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	// Dao层删除一级分类的方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	// Dao层修改一级分类的方法
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	// 注入session factory
}
