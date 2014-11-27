package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.CableSwitch;

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
 * @date 2014年11月27日
 */
@Service
public class CableSwitchService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final RowMapper<CableSwitch> rowMapper = new BeanPropertyRowMapper<CableSwitch>();

	private static final String sql_insert = "insert into cable_switch(code,name,properties,status) values (?,?,?,?)";

	public void insert(CableSwitch entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getCode(),
						entity.getName(), entity.getProperties(),
						entity.getStatus());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_getCableSwitch = "select id,code,name,properties,status from cable_switch where id=?";

	public CableSwitch getCableSwitch(int id) {
		return this.jdbcTemplate.queryForObject(sql_getCableSwitch, rowMapper,
				id);
	}
}
