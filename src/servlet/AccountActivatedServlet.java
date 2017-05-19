package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.customerDAO;
import net.sf.json.JSONObject;

/**
 * 根据link传过来的Uaccount来更新status状态：Not available-Normal
 */
@WebServlet("/AccountActivatedServlet")
public class AccountActivatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountActivatedServlet() {
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
		String code=request.getParameter("Uaccount");//codeRster
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		try {
			int rs=customerDAO.updateStatus(code);
			
			switch (rs) {
			case 0:
				map.put("success", 0);
				map.put("msg", "Code hasn't exsited.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 1:
				map.put("success", 1);
				map.put("msg", "update fail.");
				jsonObject = JSONObject.fromObject(map);
				break;
			case 2:
				map.put("success", 2); //success
				jsonObject = JSONObject.fromObject(map);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();	
	}

}
