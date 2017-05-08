package servlet;

//�������login����
// 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.administrator;
import dao.administratorDAO;


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
			String password = request.getParameter("password");
			
			administrator ad=administratorDAO.get(username,password);   //���ݿ��ȡuname,psd,����bean
			
			
			
			/*
			//���Խ����ݿ���������json��ʽ����
			String json=ad.toString();  //����õ�bean������string��ʽ����
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			*/
			
			//���⣺��ȷ�����ݿ��ѯ�����Ƿ�ɹ������в�ѯ�ɹ����������Ϣ�Ƚϲ��ˣ���ת������Ӧҳ��
			//�ж��Ƿ����������bean
			if(null==ad){
				request.setAttribute("msg", "�˺��������");
				 
				response.sendRedirect("http://localhost:8080/DE_bank/view/index.jsp"); 	
			}else
			{
			//��ûỰsession
			request.getSession().setAttribute("administrator", ad);
			response.sendRedirect("http://localhost:8080/DE_bank/view/mainPage.jsp"); 
			
			//ȡ��bean�����ݸ�ǰ��ʹ��
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
