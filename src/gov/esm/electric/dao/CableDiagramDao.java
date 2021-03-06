package gov.esm.electric.dao;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.CableDiagram;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author XueLiang
 * @date 2014年11月29日
 */
@Component
public class CableDiagramDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<CableDiagram> rowMapper = new BeanPropertyRowMapper<CableDiagram>(
			CableDiagram.class);

	private static final String sql_insert = "insert into cable_diagram(hmtl,createTime,operater)values(?,?,?)";

	public void insert(CableDiagram entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getHtml(),
						entity.getCreateTime(), entity.getOperater());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_update = "update cable_diagram set html=?,createTime=?,operater=? where id=?";

	public int update(CableDiagram entity) {
		return this.jdbcTemplate.update(sql_update, entity.getHtml(),
				entity.getCreateTime(), entity.getOperater(), entity.getId());
	}

	private static final String sql_getCableDiagram = "select id,hmtl,createTime,operater from  cable_diagram where id=?";

	public CableDiagram getCableDiagram(int id) {
		return this.jdbcTemplate.queryForObject(sql_getCableDiagram, rowMapper,
				id);
	}

	private static final String sql_getRecentCableDiagram = "select id,hmtl,createTime,operater from  cable_diagram order by id desc limit 0,1";

	/**
	 * 得到最新的电路图实体
	 * 
	 * @return
	 */
	public CableDiagram getRecentCableDiagram() {
		return this.jdbcTemplate.queryForObject(sql_getRecentCableDiagram,
				rowMapper);
	}
}
