package gov.esm.electric.web;

import gov.esm.electric.domain.User;
import gov.esm.electric.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author XueLiang
 * @date 2014年11月26日
 */
@Controller
public class LoginController {
	@Resource
	UserService userService;

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
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		User user = this.userService.getUser(userName, password);
		if (user != null) {
			req.getSession().setAttribute("user", user);
		}
		map.put("logined", user != null);
		return map;
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:/login";
	}

}
