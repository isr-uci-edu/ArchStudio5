package org.archstudio.sysutils;

import java.util.Map;

public interface BidirectionalMap<K, V>
	extends Map<K, V>{

	K getKey(Object value);

	K removeValue(Object value);
}