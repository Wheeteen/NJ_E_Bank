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

/**先判断用户是否存在
 *再比较输入新密码是否与旧密码相同
 */
@WebServlet("/NewPwdConfirmServlet")
public class NewPwdConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPwdConfirmServlet() {
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
		String code=request.getParameter("Uaccount");
		String newPwd=MD5.MD5(request.getParameter("newPwd"));
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		
		int rs;
		try {
			rs =  customerDAO.resetPwd(code,newPwd);
			switch (rs) {
			case 0:
				map.put("success", 0);
				map.put("msg", "Invalid user account.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 1:
				map.put("success", 0);
				map.put("msg", "The new password should not be identical to the old one.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 2:
				map.put("success", 1);
				jsonObject = JSONObject.fromObject(map);
				break;
			case 3:
				map.put("success", 0);
				map.put("msg", "Sorry,the operation failed.");
				jsonObject = JSONObject.fromObject(map);
				break;
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
