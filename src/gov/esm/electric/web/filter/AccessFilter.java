package gov.esm.electric.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 访问控制，未登录跳转到登录页面
 * 
 * @author xue
 *
 */
public class AccessFilter implements Filter {

	@Override
	public void init(FilterConfig fc) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		if (session != null) {
			Object user = session.getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/login.do").forward(req, resp);
			}
		} else {
			request.getRequestDispatcher("/login.do").forward(req, resp);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {

	}
}
