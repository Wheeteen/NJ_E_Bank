package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 页面注销请求处理,remove session
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
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
		HttpSession session = request.getSession(false);//防止创建Session。如果session存在即返回当前session,不存在的话创建一个session返回
		
		String json;
		if(session == null){   //如果session不存在，提示已退出，需要重定向到登录页面
			json="{\"success\": 1}";
		}else{
			session.removeAttribute("admin");
			session.removeAttribute("account");
			session.removeAttribute("code");
			json="{\"success\": 1}";
		}
//		System.out.println(session.getAttribute("code"));
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}


