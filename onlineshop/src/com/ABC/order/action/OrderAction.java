package com.ABC.order.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.ABC.cart.vo.Cart;
import com.ABC.cart.vo.CartItem;
import com.ABC.order.service.OrderService;
import com.ABC.order.vo.Order;
import com.ABC.order.vo.OrderItem;
import com.ABC.user.vo.User;
import com.ABC.utils.PageBean;
import com.ABC.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单Action类
 * 
 * @author 陈 e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月27日 下午5:17:37
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	// 模型驱动使用的对象
	private Order order = new Order();

	public Order getModel() {
		return order;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 注入OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

//	// 接收支付通道编码: 
//	private String pd_FrpId;
//	// 接收付款成功后的参数:
//		private String r3_Amt;
//		private String r6_Order;
//		
//		public void setR3_Amt(String r3_Amt) {
//			this.r3_Amt = r3_Amt;
//		}
//
//		public void setR6_Order(String r6_Order) {
//			this.r6_Order = r6_Order;
//		}
	
	// 生成订单的方法
	public String save() {
		// 1.保存数据到数据库
		Date date = new Date();  
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
        Timestamp newdate = Timestamp.valueOf(nowTime);//把时间转换
		order.setOrdertime(newdate); // 订单数据补全操作
		order.setState(1); // 1.未付款 2.已经付款未发货 3.已经发货未确认 4.交易完成
		// 总计的数据是购物车中的信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲！您还没有选择商品，请先去购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 设置订单所属的用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲，您还没有登录！请登录后重试。");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.将订单对象显示到页面上

		// 通过值栈的方式来进行显示

		// 清空购物车
		cart.clearCart();
		return "saveSuccess";
	}

	// 查询我的订单:
	public String findByUid() {
		// 获得用户的id.
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		// 根据用户的id查询订单:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// 将PageBean数据带到页面上.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	// 根据订单id查询订单的方法
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
//	// 为订单付款:
//	public String payOrder() throws IOException {
//		// 1.修改数据:
//		Order currOrder = orderService.findByOid(order.getOid());
//		currOrder.setAddr(order.getAddr());
//		currOrder.setName(order.getName());
//		currOrder.setPhone(order.getPhone());
//		// 修改订单
//		orderService.update(currOrder);
//		// 2.完成付款:
//		// 付款需要的参数:
//		String p0_Cmd = "Buy"; // 业务类型:
//		String p1_MerId = "10001126856";// 商户编号:
//		String p2_Order = order.getOid().toString();// 订单编号:
//		String p3_Amt = "0.01"; // 付款金额:
//		String p4_Cur = "CNY"; // 交易币种:
//		String p5_Pid = ""; // 商品名称:
//		String p6_Pcat = ""; // 商品种类:
//		String p7_Pdesc = ""; // 商品描述:
//		String p8_Url = "http://localhost:8080/onlineshop/order_callBack.action"; // 商户接收支付成功数据的地址:
//		String p9_SAF = ""; // 送货地址:
//		String pa_MP = ""; // 商户扩展信息:
//		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
//		String pr_NeedResponse = "1"; // 应答机制:
//		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
//		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
//				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
//				pd_FrpId, pr_NeedResponse, keyValue); // hmac
//		// 向易宝发送请求:
//		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
//		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
//		sb.append("p1_MerId=").append(p1_MerId).append("&");
//		sb.append("p2_Order=").append(p2_Order).append("&");
//		sb.append("p3_Amt=").append(p3_Amt).append("&");
//		sb.append("p4_Cur=").append(p4_Cur).append("&");
//		sb.append("p5_Pid=").append(p5_Pid).append("&");
//		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
//		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
//		sb.append("p8_Url=").append(p8_Url).append("&");
//		sb.append("p9_SAF=").append(p9_SAF).append("&");
//		sb.append("pa_MP=").append(pa_MP).append("&");
//		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
//		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
//		sb.append("hmac=").append(hmac);
//			
//		// 重定向:向易宝出发:
//		ServletActionContext.getResponse().sendRedirect(sb.toString());
//		return NONE;
//	}
	
	// 确认收货：修改订单的状态:
	public String updateState(){
		// 根据订单id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		// 修改订单状态
		currOrder.setState(4);
		// 更新数据库
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
	// 付款成功后跳转回来的路径:
	public String callBack(){
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+currOrder.getOid() +" 付款金额为: "+currOrder.getTotal());
		return "msg";
	}
}
