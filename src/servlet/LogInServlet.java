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

import bean.administrator;
import dao.administratorDAO;
import dao.customerDAO;
import net.sf.json.JSONObject;
import util.MD5;
import util.NumberRandom;


/*
 * LoginServlet完成登录页面跳转功能
 * 实现：查询数据库administrator_info，返回Aname和pwd.
 * 与输入信息比较，相同则跳转到mainPage,不相同提示错误信息
 */
/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //对请求进行处理
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			String username = request.getParameter("username");
			String password = MD5.MD5(request.getParameter("password"));
			
			String json = "";
			administrator ad=administratorDAO.get(username,password);  
			if(null==ad){
//				request.setAttribute("msg", "wrong account or password");
				json="{\"success\": 0,\"msg\":\"Something wrong with account or password\"}";	
			}else
			{
			//获得会话session
				request.getSession().setAttribute("admin", ad);
				request.getSession().setAttribute("account", ad.getAccount());
				request.getSession().setAttribute("code", ad.getCode());
				json="{\"success\": 1}";
			}
			
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			
		}
		catch(Exception ex){
			ex.printStackTrace();	

		}	
		
	}

}