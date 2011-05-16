package org.archstudio.bna.logics.hints.synchronizers;

import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.hints.IHintRepository;

public class BooleanHintSynchronizer extends PropertyHintSynchronizer {

	public BooleanHintSynchronizer(Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		this(null, thingInterface, propertyName, userProperties);
	}

	public BooleanHintSynchronizer(String hintSuffix, Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		super(hintSuffix, thingInterface, propertyName, userProperties);
	}

	@Override
	protected void storeHint(IHintRepository repository, Object context, String hintName, IThing thing, Object propertyName, Object value) {
		if (Boolean.TRUE.equals(value) ^ Boolean.TRUE.equals(repository.getHint(context, hintName))) {
			super.storeHint(repository, context, hintName, thing, propertyName, Boolean.TRUE.equals(value));
		}
	}

	@Override
	protected void restoreHint(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName, String hintName,
	        Object hintValue) {
		super.restoreHint(repository, context, partPath, parts, thing, propertyName, hintName, Boolean.TRUE.equals(hintValue));
	}
}
