package org.archstudio.bna.things.swt;

import org.archstudio.bna.facets.IHasMutableValue;

public abstract class AbstractSWTTreeThing extends AbstractSWTThing implements IHasMutableValue {

	public AbstractSWTTreeThing(Object id) {
		super(id);
	}

	public void setValue(Object value) {
		set(VALUE_KEY, value);
	}

	public Object getValue() {
		return get(VALUE_KEY);
	}
}
