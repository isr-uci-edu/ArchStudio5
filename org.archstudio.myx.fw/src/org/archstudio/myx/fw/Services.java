package org.archstudio.myx.fw;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.google.common.collect.Maps;

public class Services {
	
	Map<Class<?>, Object> services = Maps.newHashMap();

	public <T> T add(T service){
		add(service.getClass(), service);
		return service;
	}
	
	public void addAll(Object ... services){
		for(Object o : services){
			add(o);
		}
	}
	
	private void add(Class<?> c, Object o){
		if(c == null)
			return;
		
		services.put(c, o);
		for(Class<?> i : c.getInterfaces()){
			add(i, o);
		}
		add(c.getSuperclass(), o);
	}
	
	public boolean has(Class<?> serviceClass){
		return services.containsKey(serviceClass);
	}
	
	@SuppressWarnings("unchecked")
	public <C> C get(Class<C> serviceClass){
		return checkNotNull((C) services.get(serviceClass));
	}
}
