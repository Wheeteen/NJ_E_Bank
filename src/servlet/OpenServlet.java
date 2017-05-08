package servlet;
//处理开户的请求
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;
import bean.customer;
import dao.administratorDAO;
import dao.customerDAO;
import util.NumberRandom;
/**
 * Servlet implementation class OpenServlet
 */
@WebServlet("/OpenServlet")
public class OpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenServlet() {
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
		
		//获取开户页面输入的用户信息和账户PIN码
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String PIN1 = request.getParameter("PIN1");
		String PIN2 = request.getParameter("PIN2");
		
		//random随意位数字给账户号码和卡号，尚未预防是否下一次会random到同一样数字，这两个号码必须是唯一的不可相同
		NumberRandom RN=new NumberRandom();
		String accountnumber=RN.generateString(14);
		String cardnumber=RN.generateString(16);
		

		//判断返回的rs结果集是否>0,大于0表示插入操作成功
		//尚未判断输入结果是否和数据库那边有无重复记录，再补充
		//以下页面都是用来测试用的，。。请忽视
		int rs;
		
		try {
			rs = customerDAO.insert(userid,username,PIN2,accountnumber,cardnumber);
			if(rs>0){
				//request.getSession().setAttribute("Open", "rs");
				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenSuccess.jsp");
	        }else{
	        	request.setAttribute("msg", "账号密码错误"); 
				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenFail.jsp");
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
		
			 	
		
		}
	}


