package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.Address;

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
 * @date 2014年11月26日
 */
@Service
public class AddressService {

	@Resource
	JdbcTemplate jdbcTemplate;

	private static RowMapper<Address> rowMapper = new BeanPropertyRowMapper<Address>(
			Address.class);

	private static final String sql_insert = "insert into address(name)value(?)";

	public void insert(Address entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getAddress());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_getAddress = "select id,name from address where id=?";

	public Address getAddress(int id) {
		return this.jdbcTemplate.queryForObject(sql_getAddress, rowMapper, id);
	}

	private static final String sql_getAddresses = "select id,name, from address limit ?,?";

	public List<Address> getAddresses(int index, int size) {
		return this.jdbcTemplate.query(sql_getAddresses, rowMapper, (index - 1)
				* size, size);
	}
}
