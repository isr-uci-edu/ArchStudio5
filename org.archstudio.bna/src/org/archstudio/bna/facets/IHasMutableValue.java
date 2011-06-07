package org.archstudio.bna.facets;

public interface IHasMutableValue<V> extends IHasValue<V> {
	public void setValue(V value);
}
