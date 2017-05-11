package servlet;

import util.Date;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.customer;
import dao.customerDAO;
import dao.recordDAO;
import net.sf.json.JSONObject;

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
		//以下是对数据库操作前需要准备的所有数据
		String AccountNumber = request.getParameter("AccountNumber");
		String DepositAmount = request.getParameter("Amount");
		String type="Deposit";
		java.util.Date tdate=new java.util.Date();   //获取系统时间并转为timestamp便于插入数据库
		Date.d2t(tdate);  //时间转化工具类d2t
		int depositAmount=Integer.parseInt(DepositAmount);
		String currency="CNY";
		int balance=0;
		
		//返回customer_info所有信息bean
		customer cs=customerDAO.getAll(AccountNumber); 
		int amount = cs.getBalance()+depositAmount; 
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		
		if(cs == null){
			map.put("success", 0);
			map.put("msg", "The account doesn't exist");
			jsonObject = JSONObject.fromObject(map);
		}else{
			//用户状态显示正常: normal
			if(Integer.parseInt(cs.getStatus())==0){
				int rd1;
				//将数据插入record_info table
				try {
					rd1 = recordDAO.insert(AccountNumber,depositAmount,amount,currency,type,Date.d2t(tdate));
					if(rd1>0){
						map.put("success", 1);
						map.put("oriBalance", cs.getBalance());
						map.put("exiBalance", amount);
						jsonObject = JSONObject.fromObject(map);
						
					}
					else{
						map.put("success", 0);
						map.put("msg", "Please try again,the deposit action failed");
						jsonObject = JSONObject.fromObject(map);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//将balance插入customer_info
				try {
					int rd2=customerDAO.UpdateBalance(AccountNumber,amount);
					if(rd2>0){
						System.out.println("balance已成功更新到customer_info表！");
					}else{
						System.out.println("balance更新到customer_info表失败！！！");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//status：  为1  2  3 状态  “Locked”, “Not activated”, “Not Available” 
				System.out.println("账户不正常状态，不进行存款操作");
				map.put("success", 0);
				map.put("msg", "The user account status is not normal");
				jsonObject = JSONObject.fromObject(map);
			}
		}
		
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
		
	}
		
				
				
}

	
