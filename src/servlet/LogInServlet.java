package servlet;

//我完成了login功能
// 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;
import dao.administratorDAO;


//用servet调用DAO取得数据，后在jsp页面上显示出来
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
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			administrator ad=administratorDAO.get(username,password);   //数据库获取uname,psd,返回bean
			
			//判断是否存在这样的bean
			if(null==ad){
				request.setAttribute("msg", "账号密码错误");
				 
				response.sendRedirect("http://localhost:8080/DE_bank/view/mainPage.jsp"); 	
			}
			
			//获得会话session
			request.getSession().setAttribute("user", ad);
			//response.sendRedirect("http://localhost:8080/DE_bank/view/mainPage.jsp"); 
		
			
		}
	
			
		catch(Exception ex){
			ex.printStackTrace();	

		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
