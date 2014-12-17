package gov.esm.electric.dao;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.User;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author XueLiang
 * @date 2014年11月26日
 */
@Service
public class UserService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(
			User.class);

	private static final String sql_insert = "insert into user(name,password,email,phone,realName,statusId)values(?,md5(?),?,?,?,?)";

	@Transactional(propagation = Propagation.SUPPORTS)
	public void insert(User entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getName(),
						entity.getPassword(), entity.getEmail(),
						entity.getPhone(), entity.getRealName(),
						entity.getStatusId());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	public static final String sql_getUser = "select id,name,password,email,phone,realName,statusId from user where name=? and password=md5(?)";

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUser(String name, String password) {
		List<User> users = this.jdbcTemplate.query(sql_getUser, rowMapper,
				name, password);
		return users == null ? null : (users.size() > 0 ? users.get(0) : null);
	}

	private static final String sql_getUsers = "id,name,password,email,phone,realName,statusId from user order by id desc limit ?,? ";

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> getUsers(int index, int size) {
		return this.jdbcTemplate.query(sql_getUsers, rowMapper, (index - 1)
				* size, size);
	}
}
