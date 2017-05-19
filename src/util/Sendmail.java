package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.customer;

public class Sendmail extends Thread {
	// 用于给用户发送邮件的邮箱
	private String from = "13450424735@163.com";
	// 邮箱的用户名
	private String username = "13450424735";
	// 邮箱的客户端授权码
	private String password = "LGB725713";
	// 发送邮件的服务器地址
	private String host = "smtp.163.com";
	private customer cs;

	public Sendmail(customer cs) {
		this.cs = cs;
	}

	/*
	 * 重写run方法的实现，在run方法中发送邮件给指定的用户
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session, cs);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Method: createEmail
	 * @Description: 创建要发送的邮件
	 * @Anthor:孤傲苍狼
	 *
	 * @param session
	 * @param cs2
	 * @return
	 * @throws Exception
	 */
	public Message createEmail(Session session, customer cs2) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(cs2.getEmail()));
		String subject=null;
		String msg1 = null;
		String msg2 = null;
		String code = null;
		if(cs2.getUrlKey().equals("activate")){
			subject = "Activation Notification";
			msg1 = "! Congratulations ! Your application of Giant Online Bank has been approved!";
			msg2 = "Please click the following link to confirm your registeration!Thanks for your cooperation!";
			code = cs2.getCodeRster();
		}else{
			subject = "Password-Reset Notification";
			msg1="! You are now retrieving your password.";
			msg2="Please click the following link to reset your password !Thanks for your cooperation!";
			code = cs2.getCodePwd();
		}
		message.setSubject(subject);
		String info = "Hello," + cs2.getUaccount() + msg1 
				+ "<br/>"+msg2+"<br/>"
				+ "http://localhost:8080/DE_bank/view/userLogin.html?"+cs2.getUrlKey()+"=" + code;
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
}