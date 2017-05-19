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
import util.Sendmail;

/**
 * 还没写更新数据库密码的操作
 */
@WebServlet("/PasswordRetrieveServlet")
public class PasswordRetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordRetrieveServlet() {
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
		String UserAccount=request.getParameter("Uaccount");
		String CustomerID=request.getParameter("Cid");
		String Email=request.getParameter("email");
		
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		
		int rs;
		try {
			rs =  customerDAO.getThreeElement(UserAccount,CustomerID,Email);
			switch (rs) {
			case 0:
				map.put("success", 0);
				map.put("msg", "Invalid user account.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 1:
				map.put("success", 0);
				map.put("msg", "Invalid identity id number.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 2:
				map.put("success", 0);
				map.put("msg", "Invalid email address.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 3:
				map.put("success", 1);
				jsonObject = JSONObject.fromObject(map);
				break;
			}
			
			if(rs == 3){
				String codePwd = customerDAO.codeEn(UserAccount,"codePwd");
				customer cs=new customer();
				cs.setUaccount(UserAccount);
				cs.setUrlKey("resetPwd");
				cs.setCodePwd(codePwd);
				cs.setEmail(Email);
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
