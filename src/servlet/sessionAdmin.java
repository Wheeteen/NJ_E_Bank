package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class sessionAdmin
 */
@WebServlet("/sessionAdmin")
public class sessionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sessionAdmin() {
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
HttpSession session = request.getSession(false);//防止创建Session。如果session存在即返回当前session,不存在的话创建一个session返回
		
		//return json
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject= new JSONObject();
		if(session == null){   //如果session不存在，提示已退出，需要重定向到登录页面
			map.put("success", 0);
			jsonObject = JSONObject.fromObject(map);
		}else{
			String name = (String) session.getAttribute("account");
			if(name !=null){
				map.put("success", 1);
				map.put("msg", name);
				jsonObject = JSONObject.fromObject(map);
			}else{
				map.put("success", 0);
				jsonObject = JSONObject.fromObject(map);
			}
			
		}
//		System.out.println(session.getAttribute("code"));
		response.getWriter().write(jsonObject.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
