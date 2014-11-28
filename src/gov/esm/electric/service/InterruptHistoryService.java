package gov.esm.electric.service;

import gov.esm.assistor.SpringJdbcAssistor;
import gov.esm.electric.domain.InterruptHistory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @date 2014年11月28日
 */
@Service
public class InterruptHistoryService {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final RowMapper<InterruptHistory> rowMapper = new BeanPropertyRowMapper<InterruptHistory>(InterruptHistory.class);

	private static final DateFormat formater = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final String sql_insert = "insert into interrupt_history(switchId,linePartId,interruptTime,operater) values (?,?,?,?)";

	public void insert(InterruptHistory entity) {
		PreparedStatementCreator creator = SpringJdbcAssistor
				.getPreparedStatementCreator(sql_insert, entity.getSwitchId(),
						entity.getLinePartId(), entity.getInterruptTime(),
						entity.getOperater());
		KeyHolder holder = SpringJdbcAssistor.getGeneratedKeyHolder();
		this.jdbcTemplate.update(creator, holder);
		entity.setId(holder.getKey().intValue());
	}

	private static final String sql_getHistories = "select switchId,linePartId,interruptTime,operater from interrupt_history ";

	public List<InterruptHistory> getHistories(Date start, Date end, int index,
			int size) {
		StringBuilder sql = new StringBuilder(128);
		sql.append(sql_getHistories);
		if (start != null && end != null) {
			sql.append(" where interruptTime>=").append(formater.format(start))
					.append(" and ").append("interruptTime<=")
					.append(formater.format(end));
		}
		sql.append(" limit ").append((index - 1) * size).append(",")
				.append(size);
		return this.jdbcTemplate.query(sql.toString(), rowMapper);
	}
}
