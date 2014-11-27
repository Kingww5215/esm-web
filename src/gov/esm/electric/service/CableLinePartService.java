package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.assistor.StringAssistor;
import gov.esm.electric.domain.CableLinePart;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

/**
 * 
 * @author XueLiang
 * @date 2014年11月27日
 */
public class CableLinePartService {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final RowMapper<CableLinePart> rowMapper = new BeanPropertyRowMapper<CableLinePart>();

	private static final String sql_insert = "insert into cable_line_part(code,name,status) values (?,?,?)";

	public void insert(CableLinePart entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getCode(),
						entity.getName(), entity.getStatus());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_update = "update cable_line_part set code=?,name=?,status=? where id=?";

	public int update(CableLinePart entity) {
		return this.jdbcTemplate.update(sql_update, entity.getCode(),
				entity.getName(), entity.getStatus(), entity.getId());
	}

	private static final String sql_updateStatus = "update cable_line_part set status=? where id in (?)";

	public int updateStatus(int status, int[] ids) {
		String numbers = StringAssistor.join(ids, ",");
		return this.jdbcTemplate.update(sql_updateStatus, numbers);
	}

	private static final String sql_getCableLineParts = "select id,code,name,status from cable_line_part where  code=?";

	public List<CableLinePart> getCableLinePartsByCode(String code) {
		return this.jdbcTemplate.query(sql_getCableLineParts, rowMapper, code);
	}
}