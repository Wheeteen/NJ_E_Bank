package servlet;

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
import util.Date;

/**需要把balance插入record_info,然后更新customer_info的balance字段
 * Servlet implementation class WithdrawServlet2
 */
@WebServlet("/WithdrawServlet2")
public class WithdrawServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//以下是对数据库操作前需要准备的所有数据
		String AccountNumber = request.getParameter("AccountNumber");
		String WithdrawAmount = request.getParameter("WithdrawAmount");
		String type="Withdraw";
		java.util.Date tdate=new java.util.Date();   //获取系统时间并转为timestamp便于插入数据库
		Date.d2t(tdate);  //时间转化工具类d2t
		//int balance=0;
		int depositAmount=Integer.parseInt(WithdrawAmount);
		//String AuthenticationCode="0";  //ACode默认为0，已放到AD数据表
		String currency="CNY";
		int balance=0;
		
		//返回customer_info所有信息bean
		customer cs=customerDAO.getAll(AccountNumber);  //根据accountNumber拿到cs
		
		//返回的bean有问题
		//customer cs2=customerDAO.getBlance(AccountNumber);
		
		
		if(null==cs){
			System.out.println("请先开户！该账户不存在！");
		}else{   //账户已经存在！
			
			System.out.println("该账户存在！请继续对它进行存款操作！");
			System.out.println(cs.getBalance());
			System.out.println(cs.getCardnumber());
			System.out.println(cs.getPIN());
			
			if(Integer.parseInt(cs.getStatus())==0){   //账户状态正常
				if(cs.getBalance()==0){  //余额为0，直接插入depositAmount
					System.out.println("余额为零，无法进行取款操作！");
					
				}else{  //余额不为0
					if((cs.getBalance()-depositAmount)<0){
						System.out.println("余额不足，无法进行取款操作！");
					}else{
						System.out.println("余额足够，请继续取款操作！");
						//把balance insert到两张表中record_info和customer_info
						if(Integer.parseInt(WithdrawAmount)<50000){  //<5000,直接减
							int rd1;
							try {
								rd1 = recordDAO.insert(AccountNumber,depositAmount,cs.getBalance()-depositAmount,currency,type,Date.d2t(tdate));
								if(rd1>0){
									System.out.println("存钱操作成功，输出存款信息如下：");
									System.out.println("存款前账户余额为："+"0");
									System.out.println("目前账户余额为："+(cs.getBalance()-depositAmount));
									
									
									
								}
								else{
									System.out.println("存钱操作失败，请重试！");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							//更新customer_info里的balance字段
							try {
								int rs=customerDAO.UpdateBalance(AccountNumber,cs.getBalance());
							    if(rs>0){
							    	System.out.println("customer_info的balance字段更新成功！");
							    }else{
							    	System.out.println("customer_info的balance字段更新失败！");
							    }
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//将balance插入customer_info
							try {
								int rd2=customerDAO.insertBalance(AccountNumber,cs.getBalance()-depositAmount);
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
								rd1 = recordDAO.insert(AccountNumber,depositAmount,cs.getBalance()-depositAmount,currency,type,Date.d2t(tdate));
								if(rd1>0){
									System.out.println("存钱操作成功，输出存款信息如下：");
									System.out.println("存款前账户余额为："+"0");
									System.out.println("目前账户余额为："+(cs.getBalance()-depositAmount));
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
								int rd2=customerDAO.insertBalance(AccountNumber,cs.getBalance()-depositAmount);
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
