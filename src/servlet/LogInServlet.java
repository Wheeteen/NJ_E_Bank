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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			administrator ad=administratorDAO.get(username,password);   //数据库获取uname,psd,返回bean
			
			
			
			/*
			//尝试将数据库中数据以json形式返回
			String json=ad.toString();  //将获得的bean对象以string形式返回
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			*/
			
			//问题：不确定数据库查询数据是否成功，还有查询成功后和输入信息比较不了，跳转不到相应页面
			//判断是否存在这样的bean
			if(null==ad){
				request.setAttribute("msg", "账号密码错误");
				 
				response.sendRedirect("http://localhost:8080/DE_bank/view/index.jsp"); 	
			}else
			{
			//获得会话session
			request.getSession().setAttribute("administrator", ad);
			response.sendRedirect("http://localhost:8080/DE_bank/view/mainPage.jsp"); 
			
			//取出bean中数据给前端使用
			String account=ad.getAccount();
			int id=ad.getId();
			String Password=ad.getPassword();
			String json=id+account+Password;
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();	

		}	
		
	}

}
