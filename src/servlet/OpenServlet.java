package servlet;
//处理开户的请求
import java.io.IOException;
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
import util.MD5;
import util.NumberRandom;
/**
 * Servlet implementation class OpenServlet
 */
@WebServlet("/OpenServlet")
public class OpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenServlet() {
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
		
		//获取开户页面输入的用户信息和账户PIN码
		String username = request.getParameter("username");
		String userid = request.getParameter("userid"); 
		String PIN2 = MD5.MD5(request.getParameter("PIN2"));
		
		//random随意位数字给账户号码和卡号，尚未预防是否下一次会random到同一样数字，这两个号码必须是唯一的不可相同
		NumberRandom RN=new NumberRandom();
		String accountnumber=RN.generateString(14);
		String cardnumber=RN.generateString(16);
		int status=3;
		int balance=0;
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject;
		
		int rs;
		try {
			rs = customerDAO.insert(userid,username,PIN2,accountnumber,cardnumber,status,balance);
			if(rs>0){
				map.put("success", 1);
				map.put("accountNum", accountnumber);
				map.put("cardNum", cardnumber);
				jsonObject = JSONObject.fromObject(map);
//				request.getSession().setAttribute("Open", "rs");
	        }else{
	        	map.put("success", 0);
				map.put("msg", "Sorry,the account exists");
				jsonObject = JSONObject.fromObject(map);
//	        	request.setAttribute("msg", "the account exists"); 
	        }
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");  
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
			response.getWriter().close();		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
       
	}