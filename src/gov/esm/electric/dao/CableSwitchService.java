package gov.esm.electric.dao;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.CableSwitch;

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
 * @date 2014年11月27日
 */
@Service
public class CableSwitchService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final RowMapper<CableSwitch> rowMapper = new BeanPropertyRowMapper<CableSwitch>(CableSwitch.class);

	private static final String sql_insert = "insert into cable_switch(code,name,properties,status) values (?,?,?,?)";

	@Transactional(propagation = Propagation.SUPPORTS)
	public void insert(CableSwitch entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getCode(),
						entity.getName(), entity.getProperties(),
						entity.getStatus());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_update = "update cable_switch set code=?,name=?,properties=?,status=? where id=?";

	@Transactional(propagation = Propagation.SUPPORTS)
	public int update(CableSwitch entity) {
		return this.jdbcTemplate.update(sql_update, entity.getCode(),
				entity.getName(), entity.getProperties(), entity.getStatus(),
				entity.getId());
	}

	private static final String sql_updateStatus = "update cable_switch set status=? where id=?";

	@Transactional(propagation = Propagation.SUPPORTS)
	public int updateStatus(int status, int id) {
		return this.jdbcTemplate.update(sql_updateStatus, status, id);
	}

	private static final String sql_getCableSwitch = "select id,code,name,properties,status from cable_switch where id=?";

	@Transactional(propagation = Propagation.SUPPORTS)
	public CableSwitch getCableSwitch(int id) {
		return this.jdbcTemplate.queryForObject(sql_getCableSwitch, rowMapper,
				id);
	}
}
