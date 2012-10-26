package org.fhw.asta.kasse.server.component.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.common.io.Closeables;
import com.google.common.io.Resources;

// FIXME: In externe Conf-File auslagern!!!! (Java Properties oder so)
public class DevConfigProvider implements ConfigProvider {

	private static final String envFilename = "env-dev.properties";
	
	private final Properties properties;
	
	public DevConfigProvider() {
		properties = new Properties();
		
		BufferedInputStream is = null;
		
		try {
			is = new BufferedInputStream(Resources.getResource(this.getClass(), envFilename).openStream());
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Could not load env.properties");
		} finally {
			Closeables.closeQuietly(is);
		}
		
	}

	@Override
	public String get(String configKey, String defaltResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String configKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
