package com.ABC.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * 
 * @author 陈 e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日 下午12:00:38
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * 
	 * @param to:收件人
	 * @param code:激活码
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1. 获得一个Session对象 2. 创建一个代表邮件的对象Message 3. 发送邮件transport
		 */

		// 获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@abconlineshop.com", "service");
			}
		});

		// 创建一个邮件的对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("service@abconlineshop.com"));
			// 设置明收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送:CC 密送:BCC
			// 设置标题
			message.setSubject("ABC在线商城账号官方激活邮件");
			// 设置正文
			message.setContent(
					"<h1>ABC在线商城官方激活邮件！请点击下面的链接完成激活操作</h1><h3><a href='http://localhost:8080/onlineshop/user_active.action?code="
							+ code + "'>http://localhost:8080/onlineshop/user_active.action?code=" + code + "</a></h3>",
					"text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试用的main方法
	 */
//	public static void main(String[] args) {
//		sendMail("chen@abc.com", "19951126");
//	}
}
