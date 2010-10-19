package org.archstudio.myx.fw;

import java.util.Properties;

public class MyxBasicBrickInitializationData implements IMyxBrickInitializationData {
	protected final Properties properties;

	public MyxBasicBrickInitializationData(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}
}
