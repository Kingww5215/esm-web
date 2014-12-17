package gov.esm.electric.web.settings;

import gov.esm.assistor.StringAssistor;
import gov.esm.electric.domain.User;
import gov.esm.electric.domain.UserRoleRelation;
import gov.esm.electric.service.RoleService;
import gov.esm.electric.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XueLiang
 *
 * @date:2014年12月8日
 */
@Controller
@RequestMapping(value = "/settings")
public class UserController {
	@Resource
	private UserService userServiceImpl;

	@Resource
	private RoleService roleServiceImpl;

	@RequestMapping(value = "/addUser.do", method = RequestMethod.GET)
	public String addUser(HttpServletRequest req) {
		return "/settings/addUser";
	}

	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addUser(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "realName", defaultValue = "") String realName,
			@RequestParam(value = "roleId", defaultValue = "") int roleId) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setRealName(realName);
		user.setPassword(password);
		user.setPhone(phone);

		UserRoleRelation userRoleRelation = new UserRoleRelation();
		userRoleRelation.setRoleId(roleId);

		int check = this.checkUser(user, userRoleRelation);
		map.put("check", check);
		if (check > 0) {
			userServiceImpl.insert(user, userRoleRelation);
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
	private int checkUser(User user, UserRoleRelation userRoleRelation) {
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
		} else if (userRoleRelation == null) {
			return -4;
		} else if (userRoleRelation.getRoleId() < 1) {
			return -40;
		} else {
			return 1;
		}
	}
}
