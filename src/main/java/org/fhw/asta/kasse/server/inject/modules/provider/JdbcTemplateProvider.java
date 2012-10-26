package org.fhw.asta.kasse.server.inject.modules.provider;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JdbcTemplateProvider implements Provider<JdbcTemplate> {

	@Inject
	private DataSource dataSource;
	
	@Override
	public JdbcTemplate get() {
		return new JdbcTemplate(dataSource);
	}

}
