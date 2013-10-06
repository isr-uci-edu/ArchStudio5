package org.archstudio.bna.constants;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DNDData {

	private DNDActionType dropType = DNDActionType.NONE;
	private final Map<Class<?>, Object> data = Maps.newHashMap();

	public DNDActionType getDropType() {
		return dropType;
	}

	public void setDropType(DNDActionType dropType) {
		this.dropType = dropType;
	}

	public Set<Class<?>> getDataTypes() {
		return Sets.newHashSet(data.keySet());
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> ofType) {
		return (T) data.get(ofType);
	}

	public <T> void addData(Object data) {
		if (data != null) {
			this.data.put(data.getClass(), data);
		}
	}

	public void removeData(Class<?> ofType) {
		data.remove(ofType);
	}
}