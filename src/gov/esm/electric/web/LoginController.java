package gov.esm.electric.web;

import gov.esm.electric.domain.Permission;
import gov.esm.electric.domain.Role;
import gov.esm.electric.domain.User;
import gov.esm.electric.service.PermissionService;
import gov.esm.electric.service.RolePermissionRelationService;
import gov.esm.electric.service.RoleService;
import gov.esm.electric.service.UserRoleRelationService;
import gov.esm.electric.service.UserService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	@Resource
	RoleService roleService;

	@Resource
	UserRoleRelationService userRoleRelationService;

	@Resource
	PermissionService permissionService;

	@Resource
	RolePermissionRelationService rolePermissionRelationService;

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
			// setp 1.用户
			req.getSession().setAttribute("user", user);

			// setp 2.获得用户权限集合

			// step 2.1.得到用户的角色id集合
			List<Role> roles = userRoleRelationService.getRolesByUserId(user
					.getId());
			req.getSession().setAttribute("roles", roles);
			List<Integer> roleIds = new LinkedList<Integer>();
			for (Role role : roles) {
				roleIds.add(role.getId());
			}

			// step 2.2.得到该用户的所有权限
			Set<Permission> permissions = rolePermissionRelationService
					.getPermissions(roleIds);
			req.getSession().setAttribute("permissions", permissions);
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
