package org.fhw.asta.kasse.server.component.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.common.io.Closeables;
import com.google.common.io.Resources;

// TODO: bisschen weniger hack bitte
public class DevConfigProvider implements ConfigProvider {

	//private static final Logger LOGGER = LoggerFactory.getLogger(DevConfigProvider.class);
	
	private static final String ENV_PROPERTIES_FILENAME = "env-dev.properties";
	
	private final Properties properties;
	
	public DevConfigProvider() {
		this.properties = loadProperties();
	}

	private Properties loadProperties() {
	
		//LOGGER.info("Loading " + ENV_PROPERTIES_FILENAME);
		
		Properties properties = new Properties();
		BufferedInputStream is = null;
		
		try {
			is = new BufferedInputStream(Resources.getResource(this.getClass(), ENV_PROPERTIES_FILENAME).openStream());
			properties.load(is);
		} catch (IOException e) {
			//LOGGER.error("Could not load " + ENV_PROPERTIES_FILENAME);
			throw new RuntimeException(e);
		} finally {
			Closeables.closeQuietly(is);
		}		
		
		return properties;
	}
	
	@Override
	public String get(String configKey, String defaltResult) {
		return properties.getProperty(configKey, defaltResult);
	}

	@Override
	public String get(String configKey) {
		return properties.getProperty(configKey);
	}

}
