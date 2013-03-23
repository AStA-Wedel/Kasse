package org.fhw.asta.kasse.server.dao;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.common.base.Optional;
import com.google.inject.Inject;

public abstract class GenericDao {
	@Inject
	protected JdbcTemplate template;

	@Inject
	protected DataSource dataSource;

	protected <T> Optional<T> queryForObject(String sql, Object[] objects,
			RowMapper<T> rowMapper) {
		
		try {
			T result = template.queryForObject(sql, objects, rowMapper);
			return Optional.of(result);
		} catch (EmptyResultDataAccessException e) {
			return Optional.absent();
		}
	}

}
