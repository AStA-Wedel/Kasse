package org.fhw.asta.kasse.server.component.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DummyUserComponent implements UserComponent {

	@Inject
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public String show() {
		
		return jdbcTemplate.query("SELECT * FROM person", new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				return null;
			}
			
		});
		
		
	}
	
}
