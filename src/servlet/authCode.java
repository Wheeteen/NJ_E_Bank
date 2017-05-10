package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;

/**
 * Servlet implementation class authCode
 */
@WebServlet("/authCode")
public class authCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authCode() {
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
//		administrator admin = new administrator();
		String code = (String) request.getSession().getAttribute("code");
		
		System.out.println(code);
		String getCode = request.getParameter("authCode");
		String json="";
		if(getCode.equals(code)){
			json="{\"success\": 1}";
		}else{
			json="{\"success\": 0}";
		}
		
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
		
	}
	

}
