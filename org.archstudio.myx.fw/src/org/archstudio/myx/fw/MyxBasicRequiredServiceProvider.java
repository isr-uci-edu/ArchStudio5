package org.archstudio.myx.fw;

import java.util.*;

class MyxBasicRequiredServiceProvider implements IMyxRequiredServiceProvider {

	//Maps interface names to Lists of service objects
	protected Map<IMyxName, List<Object>> serviceMap = Collections.synchronizedMap(new HashMap<IMyxName, List<Object>>());

	public MyxBasicRequiredServiceProvider() {
	}

	public void addService(IMyxName interfaceName, Object serviceObject) {
		synchronized (serviceMap) {
			List<Object> l = (List<Object>) serviceMap.get(interfaceName);
			if (l == null) {
				l = new ArrayList<Object>();
				serviceMap.put(interfaceName, l);
			}
			l.add(serviceObject);
		}
	}

	public void removeService(IMyxName interfaceName, Object serviceObject) {
		synchronized (serviceMap) {
			List<Object> l = (List<Object>) serviceMap.get(interfaceName);
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
		return (IMyxName[]) serviceMap.keySet().toArray(new IMyxName[serviceMap.keySet().size()]);
	}

	public Collection<? extends Object> getServiceObjects(IMyxName interfaceName) {
		synchronized (serviceMap) {
			List<Object> l = (List<Object>) serviceMap.get(interfaceName);
			if (l == null) {
				return Collections.emptySet();
			}
			else {
				return Collections.unmodifiableList(new ArrayList<Object>(l));
			}
		}
	}

}
