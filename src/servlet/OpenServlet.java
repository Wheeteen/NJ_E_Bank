package servlet;
//澶勭悊寮�鎴风殑璇锋眰
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
		
		//鑾峰彇寮�鎴烽〉闈㈣緭鍏ョ殑鐢ㄦ埛淇℃伅鍜岃处鎴稰IN鐮�
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String PIN2 = request.getParameter("PIN2");
		
		//random闅忔剰浣嶆暟瀛楃粰璐︽埛鍙风爜鍜屽崱鍙凤紝灏氭湭棰勯槻鏄惁涓嬩竴娆′細random鍒板悓涓�鏍锋暟瀛楋紝杩欎袱涓彿鐮佸繀椤绘槸鍞竴鐨勪笉鍙浉鍚�
		NumberRandom RN=new NumberRandom();
		String accountnumber=RN.generateString(14);
		String cardnumber=RN.generateString(16);
		String status="0";
		
		String json = "";
		//鍒ゆ柇杩斿洖鐨剅s缁撴灉闆嗘槸鍚�>0,澶т簬0琛ㄧず鎻掑叆鎿嶄綔鎴愬姛
		//灏氭湭鍒ゆ柇杈撳叆缁撴灉鏄惁鍜屾暟鎹簱閭ｈ竟鏈夋棤閲嶅璁板綍锛屽啀琛ュ厖
		//浠ヤ笅椤甸潰閮芥槸鐢ㄦ潵娴嬭瘯鐢ㄧ殑锛屻�傘�傝蹇借
		int rs;
		
		try {
			rs = customerDAO.insert(userid,username,PIN2,accountnumber,cardnumber,status);
			if(rs>0){
				json="{\"success\": 1,\"accountNum\":"+accountnumber+"\"cardNum\":"+cardnumber+"}";
				//request.getSession().setAttribute("Open", "rs");
//				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenSuccess.jsp");
	        }else{
	        	json="{\"success\": 0,\"msg\":\"Sorry,the account exists\"}";
	        	request.setAttribute("msg", "the account exists"); 
//				response.sendRedirect("http://localhost:8080/DE_bank/view/OpenFail.jsp");
	        }
			
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
		
			 	
		
		}
	}