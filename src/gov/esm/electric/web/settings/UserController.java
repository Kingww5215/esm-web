package gov.esm.electric.web.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XueLiang
 *
 * @date:2014年12月8日
 */
@Controller
@RequestMapping(value = "/settings")
public class UserController {
	@RequestMapping(value = "/addUser.do")
	public void addUser(HttpServletRequest req, HttpServletResponse resp) {

	}
}
