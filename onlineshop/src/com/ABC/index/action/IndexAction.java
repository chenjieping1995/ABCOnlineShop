package com.ABC.index.action;

import java.util.List;

import com.ABC.category.service.CategoryService;
import com.ABC.category.vo.Category;
import com.ABC.product.service.ProductService;
import com.ABC.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 首页访问的action
 * @author: 陈
 */
public class IndexAction extends ActionSupport {
	
	// 注入一级分类的service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 注入商品的service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	/**
	 * 执行访问首页的方法
	 */
	public String execute(){
		
		// 查询一级分类
		List<Category> cList = categoryService.findAll();
		// 将一级分类存入到session范围
		ActionContext.getContext().getSession().put("cList", cList);
		
		// 查询热门商品
		List<Product> hList = productService.findHot();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		// 查询最新商品
		List<Product> nList = productService.findNew();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		
		// 返回的值与struts中的类名呼应
		return "index";
	}
}
