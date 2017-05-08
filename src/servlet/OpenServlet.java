package servlet;
//������������
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
		
		//��ȡ����ҳ��������û���Ϣ���˻�PIN��
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String PIN1 = request.getParameter("PIN1");
		String PIN2 = request.getParameter("PIN2");
		
		//random����λ���ָ��˻�����Ϳ��ţ���δԤ���Ƿ���һ�λ�random��ͬһ�����֣����������������Ψһ�Ĳ�����ͬ
		NumberRandom RN=new NumberRandom();
		String accountnumber=RN.generateString(14);
		String cardnumber=RN.generateString(16);
		

		//�жϷ��ص�rs������Ƿ�>0,����0��ʾ��������ɹ�
		//��δ�ж��������Ƿ�����ݿ��Ǳ������ظ���¼���ٲ���
		//����ҳ�涼�����������õģ����������
		int rs;
		
		try {
			rs = customerDAO.insert(userid,username,PIN2,accountnumber,cardnumber);
			if(rs>0){
				//request.getSession().setAttribute("Open", "rs");
				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenSuccess.jsp");
	        }else{
	        	request.setAttribute("msg", "�˺��������"); 
				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenFail.jsp");
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
		
			 	
		
		}
	}


