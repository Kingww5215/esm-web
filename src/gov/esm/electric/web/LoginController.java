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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			req.getSession().setAttribute(Constant.SESSION_KEY_USER, user);

			// setp 2.获得用户权限集合

			// step 2.1.得到用户的角色id集合
			List<Role> roles = userRoleRelationService.getRolesByUserId(user
					.getId());
			req.getSession().setAttribute(Constant.SESSION_KEY_ROLES, roles);
			List<Integer> roleIds = new LinkedList<Integer>();
			for (Role role : roles) {
				roleIds.add(role.getId());
			}

			// step 2.2.得到该用户的所有权限
			List<Permission> permissions = rolePermissionRelationService
					.getPermissions(roleIds);

			List<Map<String, Object>> trees = new LinkedList<Map<String, Object>>();
			for (Permission p : permissions) {
				buildTree(p, permissions, trees);
			}
			req.getSession().setAttribute(Constant.SESSION_KEY_PERMISSIONS,
					permissions);
			req.getSession()
					.setAttribute(Constant.SESSION_KEY_MENU_TREE, trees);
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

	@RequestMapping(value = "/getPermissions.do")
	@ResponseBody
	public Object getPermissions(HttpServletRequest req,
			HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Object permissions = session
				.getAttribute(Constant.SESSION_KEY_MENU_TREE);
		return permissions;
	}

	private void buildTree(Permission node, List<Permission> permissions,
			List<Map<String, Object>> trees) {
		if (node.getLeaderId() == 0) {
			Map<String, Object> m = toMap(node);
			trees.add(m);
		} else if (node.getLeaderId() > 0) {
			for (Map<String, Object> p : trees) {
				if (node.getId() == (Integer) p.get("id")) {
					continue;
				}
				if (node.getLeaderId() == (Integer) p.get("id")) {
					Map<String, Object> mapNode = this.toMap(node);
					List<Map<String, Object>> children = new LinkedList<Map<String, Object>>();
					children.add(mapNode);
					p.put("children", children);
				}
			}
		}
	}

	private Map<String, Object> toMap(Permission p) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", p.getId());
		if (p.getUrl().trim().length() < 1) {
			// permission.text = permission.name;
			m.put("text", p.getName());
		} else {
			m.put("text",
					"<a href=\"javascript:esm.common.tab.create('"
							+ p.getName() + "','" + p.getUrl()
							+ "');\" title=\"" + p.getDescription() + "\">"
							+ p.getName() + "</a>");
		}
		m.put("state", "closed");
		return m;
	}
}
