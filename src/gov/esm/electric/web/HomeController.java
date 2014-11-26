package gov.esm.electric.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author XueLiang
 * @date 2014年11月26日
 */
@Controller
public class HomeController {
	/**
	 * 显示登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	/**
	 * 登录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		return "/electric/diagram";
	}

}
