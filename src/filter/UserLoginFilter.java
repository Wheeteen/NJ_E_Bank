package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class UserLoginFilter
 */
@WebFilter("/UserLoginFilter")
public class UserLoginFilter implements Filter {
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		if(request.getSession().getAttribute("cid")==null){//判断Session是否登录
			response.sendRedirect("./login.html");//没有登录的用户转到登录界面
		}else{
			arg2.doFilter(request, response);//传递请求和相应
		}
	}
	
	public void init(FilterConfig arg0) throws ServletException {
	}
}
