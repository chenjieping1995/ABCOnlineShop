package com.ABC.product.service;

import java.util.List;

import org.apache.struts2.components.template.BaseTemplateEngine;
import org.springframework.transaction.annotation.Transactional;

import com.ABC.product.dao.ProductDao;
import com.ABC.product.vo.Product;
import com.ABC.utils.PageBean;

/**
 * 商品业务层代码
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月26日  上午8:27:37
 */
@Transactional
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		// 根据cid查询该类商品的总个数
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if (totalCount % limit == 0){
			totalPage = totalCount/limit;
		} else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page-1)*limit;	// 从哪儿开始
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据二级分类查询商品信息
	public PageBean<Product> findByCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		// 根据cid查询该类商品的总个数
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if (totalCount % limit == 0){
			totalPage = totalCount/limit;
		} else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page-1)*limit;	// 从哪儿开始
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层查询商品 带分页的
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<>();
		// 设置当前的页数
		pageBean.setPage(page);
		// 设置每页显示的记录数
		int limit=10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage=0;
		if (totalCount%limit==0){
			totalPage=totalCount/limit;
		} else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层保存商品的方法
	public void save(Product product) {
		productDao.save(product);
	}

	// 业务层删除商品的方法
	public void delete(Product product) {
		productDao.delete(product);
	}

	// 业务层更新商品信息的方法
	public void update(Product product) {
		productDao.update(product);
	}



	
	
}
