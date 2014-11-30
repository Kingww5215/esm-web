package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.assistor.StringAssistor;
import gov.esm.electric.domain.Role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author XueLiang
 * @date 2014年11月30日
 */
@Service
public class RoleService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<Role> rowMapper = new BeanPropertyRowMapper<Role>(
			Role.class);

	private static final String sql_insert = "insert into role (name)values(?)";

	public void insert(Role entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getName());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_getRole = "select id,name from role where id=?";

	public Role getRole(int id) {
		return this.jdbcTemplate.queryForObject(sql_getRole, rowMapper, id);
	}

	private static final String sql_getRoles = "select id,name from role ";

	public List<Role> getRoles(List<Integer> ids) {
		StringBuilder sql = new StringBuilder(64);
		sql.append(sql_getRoles);
		if (ids != null && ids.size() > 0) {
			sql.append(" where id in(");
			String in = StringAssistor.join(ids, ",");
			sql.append(in).append(")");
		}
		return this.jdbcTemplate.query(sql.toString(), rowMapper);
	}

}
