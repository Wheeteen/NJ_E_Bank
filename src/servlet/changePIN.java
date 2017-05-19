package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.customerDAO;
import net.sf.json.JSONObject;
import util.MD5;

/**
 * Servlet implementation class changePIN
 */
@WebServlet("/changePIN")
public class changePIN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePIN() {
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
		String cardNum = request.getParameter("cardNum");
		String id = request.getParameter("ID");
		String oldPIN = MD5.MD5(request.getParameter("oldPIN"));
		String newPIN = MD5.MD5(request.getParameter("newPIN"));
		
		int rs = customerDAO.ChangePIN(cardNum,id,oldPIN,newPIN);
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		
		switch(rs){
		  case 0:
			map.put("success", 0);
			map.put("msg", "Incorrect Identity ID number.");
			jsonObject = JSONObject.fromObject(map);
			break;
		  case 1:
			map.put("success", 0);
			map.put("msg", "Incorrect card number.");
			jsonObject = JSONObject.fromObject(map);
			break;
		  case 2:
			map.put("success", 0);
			map.put("msg", "Incorrect old PIN");
			jsonObject = JSONObject.fromObject(map);
			break;
		  case 3:
			map.put("success", 0);
			map.put("msg", "The new PIN should not be identical to the old one.");
			jsonObject = JSONObject.fromObject(map);
			break; 
		  case 4:
			map.put("success", 0);
			map.put("msg", "Changing password unsuccessful");
			jsonObject = JSONObject.fromObject(map);
			break;
		  case 5:
			map.put("success", 1);
			jsonObject = JSONObject.fromObject(map);
			break; 
		}
		System.out.println(jsonObject.toString());
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
