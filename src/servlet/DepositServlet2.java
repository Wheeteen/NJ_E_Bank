package servlet;

import util.Date;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.customer;
import dao.customerDAO;
import dao.recordDAO;

/*
 * 只是将balance插入了record_info，还没插入customer_info的“balance”列
 * 
 * 
 */

/**
 * Servlet implementation class DepositServlet2
 */
@WebServlet("/DepositServlet2")
public class DepositServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//以下是对数据库操作前需要准备的所有数据
				String AccountNumber = request.getParameter("AccountNumber");
				String DepositAmount = request.getParameter("DepositAmount");
				String type="Deposit";
				java.util.Date tdate=new java.util.Date();   //获取系统时间并转为timestamp便于插入数据库
				Date.d2t(tdate);  //时间转化工具类d2t
				//int balance=0;
				int depositAmount=Integer.parseInt(DepositAmount);
				//String AuthenticationCode="0";  //ACode默认为0，已放到AD数据表
				String currency="CNY";
				int balance=0;
				
				//返回customer_info所有信息bean
				customer cs=customerDAO.getAll(AccountNumber); 
				
				if(null==cs){
					System.out.println("请先开户！该账户不存在！");
				}else{   //账户已经存在！
					System.out.println("该账户存在！请继续对它进行存款操作！"); 
					if(Integer.parseInt(cs.getStatus())==0){   //账户状态正常
						if(cs.getBalance()==0){  //余额为0，直接插入depositAmount
							if(Integer.parseInt(DepositAmount)<50000){
								int rd1;
								try {
									rd1 = recordDAO.insert(AccountNumber,depositAmount,depositAmount,currency,type,Date.d2t(tdate));
									
									if(rd1>0){
										System.out.println("存钱操作成功，输出存款信息如下：");
										System.out.println("存款前账户余额为："+"0");
										System.out.println("目前账户余额为："+Integer.parseInt(DepositAmount));
									}
									else{
										System.out.println("存钱操作失败，请重试！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								//同时插入customer_info的"balance"类，根据UserAccountNumber
								try {
									int rd2=customerDAO.insertBalance(AccountNumber,depositAmount);
									if(rd2>0){
										System.out.println("balance已成功插入customer_info表！");
									}else{
										System.out.println("balance插入customer_info表失败！！！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else{
								int rd1;
								try {
									rd1 = recordDAO.insert(AccountNumber,depositAmount,cs.getBalance()+depositAmount,currency,type,Date.d2t(tdate));
									if(rd1>0){
										System.out.println("存钱操作成功，输出存款信息如下：");
										System.out.println("存款前账户余额为："+"0");
										System.out.println("目前账户余额为："+Integer.parseInt(DepositAmount));
									}
									else{
										System.out.println("存钱操作失败，请重试！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								//将balance插入customer_info
								try {
									int rd2=customerDAO.insertBalance(AccountNumber,cs.getBalance()+depositAmount);
									if(rd2>0){
										System.out.println("balance已成功插入customer_info表！");
									}else{
										System.out.println("balance插入customer_info表失败！！！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
						}else{  //余额不为0
							if(Integer.parseInt(DepositAmount)<50000){
								int rd1;
								try {
									rd1 = recordDAO.insert(AccountNumber,depositAmount,cs.getBalance()+depositAmount,currency,type,Date.d2t(tdate));
									if(rd1>0){
										System.out.println("存钱操作成功，输出存款信息如下：");
										System.out.println("存款前账户余额为："+"0");
										System.out.println("目前账户余额为："+Integer.parseInt(DepositAmount));
										
										
										
									}
									else{
										System.out.println("存钱操作失败，请重试！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								//将balance插入customer_info
								try {
									int rd2=customerDAO.insertBalance(AccountNumber,cs.getBalance()+depositAmount);
									if(rd2>0){
										System.out.println("balance已成功插入customer_info表！");
									}else{
										System.out.println("balance插入customer_info表失败！！！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else{
								int rd1;
								try {
									rd1 = recordDAO.insert(AccountNumber,depositAmount,cs.getBalance()+depositAmount,currency,type,Date.d2t(tdate));
									if(rd1>0){
										System.out.println("存钱操作成功，输出存款信息如下：");
										System.out.println("存款前账户余额为："+"0");
										System.out.println("目前账户余额为："+Integer.parseInt(DepositAmount));
									}
									else{
										System.out.println("存钱操作失败，请重试！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//将balance插入customer_info
								try {
									int rd2=customerDAO.insertBalance(AccountNumber,cs.getBalance()+depositAmount);
									if(rd2>0){
										System.out.println("balance已成功插入customer_info表！");
									}else{
										System.out.println("balance插入customer_info表失败！！！");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							
						}
						//账户正常余额不为0的操作结束
						//账户不正常操作开始
						
						
						
					}else{
						System.out.println("账户不正常状态，不进行存款操作");
									}
					//账户不正常操作结束
							
							
							
							
						}
				//账户存在情况结束
					}
				
				
				
				
				
				
		}

	
