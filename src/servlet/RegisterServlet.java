package servlet;

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
import net.sf.json.JSONObject;
import util.MD5;
import util.Sendmail;



/**
 * 该servlet返回的是注册时的Cid的校验结果。若已存在，提醒；不存在，启动邮件发送功能，并跳转到次相应的提醒页面
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String Uaccount = request.getParameter("Uaccount");
		String UserID = request.getParameter("UserID");
		String email = request.getParameter("email");
		String password = MD5.MD5(request.getParameter("password"));
		int newStatus=2;
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		
		int rs;
		try {
			rs = customerDAO.RegisterInfo(Uaccount,UserID,email,password,newStatus);
			switch (rs) {
			case 0:
				map.put("success", 0);
				map.put("msg", "Invalid identity id number.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 1:
				map.put("success", 0);
				map.put("msg", "The user account has existed.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 2:
				map.put("success", 1);
				jsonObject = JSONObject.fromObject(map);
				request.getSession().setAttribute("userAccount", Uaccount);
				break;
			case 3:
				map.put("success", 0);
				map.put("msg", "Sorry,register fail.");
				jsonObject = JSONObject.fromObject(map);
				break;
			}
			
			if(rs == 2){
				String codeRster = customerDAO.codeEn(Uaccount,"codeRster");
				customer cs=new customer();
				cs.setUaccount(Uaccount);
				cs.setUrlKey("activate");
				cs.setCodeRster(codeRster);
				cs.setEmail(email);
				//发送邮件是一件非常耗时的事情，因此这里开辟了另一个线程来专门发送邮件
				Sendmail send = new Sendmail(cs);
				//启动线程，线程启动之后就会执行run方法来发送邮件
				send.start();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();

	}

}
