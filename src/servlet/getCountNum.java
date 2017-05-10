package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.customer;
import dao.customerDAO;

/**
 * Servlet implementation class getCountNum
 */
@WebServlet("/getCountNum")
public class getCountNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCountNum() {
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
		String AccountNumber = request.getParameter("AccountNumber");
		
		Boolean existId=customerDAO.getId(AccountNumber,"Anumber");
		
		String json = "";
		if(existId){
			json="{\"success\": 1}";	
		}else{
			json="{\"success\": 0,\"msg\":\"The User Account Number doesn't exist\"}";
		}
		
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
