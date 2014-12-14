package gov.esm.electric.web.settings;

import gov.esm.assistor.StringAssistor;
import gov.esm.electric.domain.User;
import gov.esm.electric.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author XueLiang
 *
 * @date:2014年12月8日
 */
@Controller
@RequestMapping(value = "/settings")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping(value = "/addUser.do")
	public String addUser() {
		return "/settings/addUser";
	}

	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public Object addUser(User user) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		int check = this.checkUser(user);
		map.put("check", check);
		if (check > 0) {
			userService.insert(user);
			map.put("success", user.getId() > 0);
		}
		return map;
	}

	/**
	 * 基本的数据有效性检查
	 * 
	 * @param user
	 * @return
	 */
	private int checkUser(User user) {
		if (user == null) {
			return 0;
		} else if (StringAssistor.isBlank(user.getName())) {
			return -1;
		} else if (user.getName().length() > 32) {
			return -10;
		} else if (StringAssistor.isBlank(user.getPassword())) {
			return -2;
		} else if (user.getPassword().length() > 32) {
			return -20;
		} else if (StringAssistor.isBlank(user.getPhone())) {
			return -3;
		} else if (user.getPhone().length() > 15) {
			return -30;
		} else {
			return 1;
		}
	}
}
