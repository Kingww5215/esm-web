package gov.esm.electric.service;

import gov.esm.electric.domain.Permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author XueLiang
 * @date 2014年11月30日
 */
@Service
public class RolePermissionRelation {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final String sql_getPermissionsByRoleId = "select permission.* from role_permission_relation as relation inner join permission on relation.permissionId=permission.id and relation.roleId=?";

	public List<Permission> getPermissionsByRoleId(int roleId) {
		return this.jdbcTemplate.query(sql_getPermissionsByRoleId,
				PermissionService.rowMapper, roleId);
	}
}
