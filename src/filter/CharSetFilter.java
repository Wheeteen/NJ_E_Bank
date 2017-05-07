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

/**
 * Servlet Filter implementation class CharSetFilter
 */
@WebFilter("/CharSetFilter")
public class CharSetFilter implements Filter{
	
	String charset = null;
	
	public void init(FilterConfig config)throws ServletException{
		charset = config.getInitParameter("encode");//在配置文件中读取参数
	}
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
		HttpServletRequest req = (HttpServletRequest)request;//过滤请求
		req.setCharacterEncoding(charset);//设置编码格式
		chain.doFilter(request, response);//传递请求相应
	}
	
	public void destroy(){
		
	}
}
