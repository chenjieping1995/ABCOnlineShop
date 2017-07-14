package com.ABC.product.action;

import java.util.List;

import com.ABC.category.service.CategoryService;
import com.ABC.category.vo.Category;
import com.ABC.product.service.ProductService;
import com.ABC.product.vo.Product;
import com.ABC.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的Action对象
 * @author 陈 
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月26日 上午8:30:24
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	
	// 用于接收数据的模型驱动.
	private Product product = new Product();
	// 注入商品的Service
	private ProductService productService;
	// 接收分类cid
	private Integer cid;
	// 接收二级分类id
	private Integer csid;
	
	// 注入一级分类的Service
	private CategoryService categoryService;
	// 接收当前页数:
	private int page;
	
	// 实现模型驱动
	public Product getModel() {
		return product;
	}
	
	// 根据商品的ID进行查询商品:执行方法:
	public String findByPid() {
		// 调用Service的方法完成查询.
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	// 根据分类id查询商品
	public String findByCid() {
		// 从session中获取分类信息
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);	// 根据一级分类查询商品，带分页查询
		// 将pagebean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	// 根据二级分类id查询商品
	public String findByCsid() {
		// 调用service层获取商品页
		PageBean<Product> pageBean = productService.findByCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}


	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



}
