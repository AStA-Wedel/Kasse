package org.fhw.asta.kasse.server.component.config;

public interface ConfigProvider {

	String get(String configKey, String defaltResult);
	
	String get(String configKey);
	
}
