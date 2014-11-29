package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.LineSwitchRelation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author XueLiang
 * @date 2014年11月29日
 */
public class LineSwitchRelationService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<LineSwitchRelation> rowMapper = new BeanPropertyRowMapper<LineSwitchRelation>(
			LineSwitchRelation.class);

	@Transactional(propagation = Propagation.SUPPORTS)
	public void insert(LineSwitchRelation entity) {
		int id = this.insert(entity.getLinePartId(), entity.getSwitchId());
		entity.setId(id);
	}

	private static final String sql_insert = "insert into line_switch_relation(linePartId,switchId)values(?,?)";

	@Transactional(propagation = Propagation.SUPPORTS)
	public int insert(int linePartId, int switchId) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, linePartId, switchId);
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		return holder.getKey().intValue();
	}

	private static final String sql_getRelationsBySwitchId = "select linePartId,switchId from line_switch_relation where switchId=?";

	public List<LineSwitchRelation> getRelationsBySwitchId(int switchId) {
		return this.jdbcTemplate.query(sql_getRelationsBySwitchId, rowMapper,
				switchId);
	}

	private static final String sql_getRelationsByLinePartId = "select linePartId,switchId from line_switch_relation where linePartId=?";

	public List<LineSwitchRelation> getRelationsByLinePartId(int linePartId) {
		return this.jdbcTemplate.query(sql_getRelationsByLinePartId, rowMapper,
				linePartId);
	}

	private static final String sql_deleteByLineId = "delete from line_switch_relation where lineId=?";

	public int deleteByLineId(int lineId) {
		return this.jdbcTemplate.update(sql_deleteByLineId, lineId);
	}

	private static final String sql_deleteBySwitchId = "delete from line_switch_relation where switchId=?";

	public int deleteBySwitchId(int switchId) {
		return this.jdbcTemplate.update(sql_deleteBySwitchId, switchId);
	}
}
