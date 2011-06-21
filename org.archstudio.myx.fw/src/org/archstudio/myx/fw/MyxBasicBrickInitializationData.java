package org.archstudio.myx.fw;

import java.util.Collections;
import java.util.Map;

public class MyxBasicBrickInitializationData implements IMyxBrickInitializationData {

	private static final long serialVersionUID = 9081710857385596658L;

	protected final Map<String, String> properties;

	public MyxBasicBrickInitializationData(Map<String, String> properties) {
		this.properties = Collections.unmodifiableMap(properties);
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
