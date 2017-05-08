package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;
import bean.customer;
import dao.administratorDAO;
import dao.customerDAO;

/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/InquiryServlet")
public class InquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryServlet() {
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
		String userID = request.getParameter("CustomerID");
		customer cs=customerDAO.get(userID);
		if(null==cs){
			request.setAttribute("msg", "查询操作失败！");
			 
			response.sendRedirect("http://localhost:8080/DE_bank/view/InquiryFail.jsp"); 	
		}else
		{
		//获得会话session
		request.getSession().setAttribute("customer", cs);
		response.sendRedirect("http://localhost:8080/DE_bank/view/SatusPage.jsp"); 
		
		//把查询到的cs.bean里的信息全部打印出来！供前端显示使用
		String alldataInCs=cs.getUserId()+" "+cs.getUserName()+" "+cs.getAccountnumber()+" "+cs.getCardnumber()+" "+cs.getStatus();
		System.out.println(alldataInCs);
		
		}
	}

}
