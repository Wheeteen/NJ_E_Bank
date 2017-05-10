package servlet;

//�������login����
// 
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;

import bean.administrator;
import dao.administratorDAO;
import util.MD5;

//��servet����DAOȡ�����ݣ�����jspҳ������ʾ����
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
    
    //��������д���
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
			
			administrator ad=administratorDAO.get(username,password);   //���ݿ��ȡuname,psd,����bean
			
			String json = "";
			//���⣺��ȷ�����ݿ��ѯ�����Ƿ�ɹ������в�ѯ�ɹ����������Ϣ�Ƚϲ��ˣ���ת������Ӧҳ��
			//�ж��Ƿ����������bean
			if(null==ad){
				request.setAttribute("msg", "�˺��������");
				json="{\"success\": 0,\"msg\":\"Something wrong with account or password\"}";
//				response.sendRedirect("view/index.jsp"); 	
			}else
			{
			//��ûỰsession
			request.getSession().setAttribute("administrator", ad);
			json="{\"success\": 1}";
//			response.sendRedirect("view/mainPage.jsp"); 
		
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
