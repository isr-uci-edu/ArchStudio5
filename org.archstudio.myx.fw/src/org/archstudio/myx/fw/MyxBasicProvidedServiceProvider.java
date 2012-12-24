package org.archstudio.myx.fw;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class MyxBasicProvidedServiceProvider implements IMyxProvidedServiceProvider {

	protected Map<IMyxName, Object> serviceMap = Collections.synchronizedMap(new HashMap<IMyxName, Object>());

	public MyxBasicProvidedServiceProvider() {
	}

	public MyxBasicProvidedServiceProvider(IMyxName[] interfaceNames, Object[] serviceObjects) {
		if (interfaceNames.length != serviceObjects.length) {
			throw new IllegalArgumentException("Length mismatch.");
		}
		for (int i = 0; i < interfaceNames.length; i++) {
			putService(interfaceNames[i], serviceObjects[i]);
		}
	}

	public void putService(IMyxName interfaceName, Object serviceObject) {
		serviceMap.put(interfaceName, serviceObject);
	}

	public void removeService(IMyxName interfaceName) {
		serviceMap.remove(interfaceName);
	}

	public IMyxName[] getAllInterfaceNames() {
		return serviceMap.keySet().toArray(new IMyxName[serviceMap.keySet().size()]);
	}

	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return serviceMap.get(interfaceName);
	}

}
