package org.archstudio.myx.fw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MyxBasicRequiredServiceProvider implements IMyxRequiredServiceProvider {

	//Maps interface names to Lists of service objects
	protected Map<IMyxName, List<Object>> serviceMap = Collections
			.synchronizedMap(new HashMap<IMyxName, List<Object>>());

	public MyxBasicRequiredServiceProvider() {
	}

	public void addService(IMyxName interfaceName, Object serviceObject) {
		synchronized (serviceMap) {
			List<Object> l = serviceMap.get(interfaceName);
			if (l == null) {
				l = new ArrayList<Object>();
				serviceMap.put(interfaceName, l);
			}
			l.add(serviceObject);
		}
	}

	public void removeService(IMyxName interfaceName, Object serviceObject) {
		synchronized (serviceMap) {
			List<Object> l = serviceMap.get(interfaceName);
			if (l == null) {
				return;
			}
			for (int i = 0; i < l.size(); i++) {
				Object so = l.get(i);
				if (so.equals(serviceObject)) {
					l.remove(i);
					if (l.size() == 0) {
						serviceMap.remove(interfaceName);
					}
					return;
				}
			}
		}
	}

	public IMyxName[] getAllInterfaceNames() {
		return serviceMap.keySet().toArray(new IMyxName[serviceMap.keySet().size()]);
	}

	@Override
	public Collection<? extends Object> getServiceObjects(IMyxName interfaceName) {
		synchronized (serviceMap) {
			List<Object> l = serviceMap.get(interfaceName);
			if (l == null) {
				return Collections.emptySet();
			}
			else {
				return Collections.unmodifiableList(new ArrayList<Object>(l));
			}
		}
	}

}
