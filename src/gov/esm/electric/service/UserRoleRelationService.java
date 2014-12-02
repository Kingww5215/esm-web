package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.UserRoleRelation;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author XueLiang
 * @date 2014年12月2日
 */
@Service
public class UserRoleRelationService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void insert(int userId, int[] roleIds) {
		UserRoleRelation entity = new UserRoleRelation();
		for (int i = 0; i < roleIds.length; i++) {
			entity.setUserId(userId);
			entity.setRoleId(roleIds[i]);
			this.insert(entity);
		}
	}

	private static final String sql_insert = "insert into user_role_relation(userId,roleId)values(?,?)";

	@Transactional(propagation = Propagation.SUPPORTS)
	public void insert(UserRoleRelation entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getUserId(),
						entity.getRoleId());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_delete = "delete from user_role_relation where userId=?";

	@Transactional(propagation = Propagation.SUPPORTS)
	public int delete(int userId) {
		return this.jdbcTemplate.update(sql_delete, userId);
	}

}
