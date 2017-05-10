package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;
import bean.customer;
import dao.administratorDAO;
import dao.customerDAO;
import net.sf.json.JSONObject;
/*
 * 怎么去实现表示不同账户的状态这个还没想到。不是很理解文档里面的意思。。。
 * 
 */
import util.DBConnection;


/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/InquiryServlet")
public class InquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryServlet() {
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
		String userID = request.getParameter("CustomerID");
		customer cs=customerDAO.getAN(userID);   //根据userid返回带有Customer_info所有信息的bean
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject;
		
		if(null==cs){
			map.put("success", 0);
			map.put("msg", "The customer’s ID number doesn't exist");
			jsonObject = JSONObject.fromObject(map);
	
		}else{
			//获得会话session
//			request.getSession().setAttribute("customer", cs);
			
			String status = "Normal";
			switch(cs.getStatus()){
				case "0":
					status="Normal";
					break;
				case "1":
					status="Locked";
					break;
				case "2":
					status="Not activated";
					break;
				case "3":
					status="Not Available";
					break;
			}
			map.put("success", 1);
			map.put("accountNum", cs.getAccountnumber());
			map.put("cardNum", cs.getCardnumber());
			map.put("name", cs.getUserName());
			map.put("status", status);
			jsonObject = JSONObject.fromObject(map);
		}
		
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
		
	}

}