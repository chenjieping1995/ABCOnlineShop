package com.ABC.category.service;

import java.util.List;

import com.ABC.category.dao.CategoryDao;
import com.ABC.category.vo.Category;

/**
 * 一级分类的业务层对象
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  下午8:18:20
 */
public class CategoryService {
	
	// 注入CategoryDao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// 业务层查询所有一级分类的方法
	public List<Category> findAll() {
		return categoryDao.findALL();
	}

	// 业务层保存一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);
	}

	// 业务层删除一级分类的方法
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	// 业务层根据一级分类id查询一级分类的方法
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
