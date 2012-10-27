package org.fhw.asta.kasse.server.inject.modules;

import org.fhw.asta.kasse.server.dao.UserDao;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDao.class).in(Singleton.class);
	}
	
}
