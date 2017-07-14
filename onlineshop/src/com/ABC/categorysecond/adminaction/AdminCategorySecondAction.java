package com.ABC.categorysecond.adminaction;

import java.util.List;

import com.ABC.category.service.CategoryService;
import com.ABC.category.vo.Category;
import com.ABC.categorysecond.service.CategorySecondService;
import com.ABC.categorysecond.vo.CategorySecond;
import com.ABC.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台的二级分类管理
 * 
 * @author 陈 e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月28日 下午5:35:50
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	// 模型驱动使用的对象
	private CategorySecond categorySecond = new CategorySecond();
	// 接收page参数:
	private Integer page;
	// 注入二级Service
	private CategorySecondService categorySecondService;
	// 注入一级分类的Service
	private CategoryService categoryService;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

	// 带有分页的查询所有二级分类的操作:
	public String findAllByPage() {
		// 调用Service进行查询.
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// 将pageBean的数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 跳转到添加页面的方法:
	public String addPage() {
		// 查询所有一级分类.
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "addPage";
	}

	// 添加二级分类的方法:
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	// 删除二级分类的方法:
	public String delete() {
		// 根据id查询二级分类:
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	// 编辑二级分类的方法:
	public String edit() {
		// 根据id查询二级分类:
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// 查询所有一级分类:
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "editSuccess";
	}

	// 修改二级分类的方法:
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
