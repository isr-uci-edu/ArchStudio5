package org.archstudio.myx.fw;

import java.util.Properties;

public class MyxBasicBrickInitializationData implements IMyxBrickInitializationData {

	private static final long serialVersionUID = 9081710857385596658L;
	
	protected final Properties properties;

	public MyxBasicBrickInitializationData(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}
}
