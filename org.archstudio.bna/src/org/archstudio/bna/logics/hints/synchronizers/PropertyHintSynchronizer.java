package org.archstudio.bna.logics.hints.synchronizers;

import java.io.Serializable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.archstudio.bna.utils.UserEditableUtils;

public class PropertyHintSynchronizer extends AbstractHintSynchronizer {

	protected final String hintName;
	protected final IThingKey<Object> propertyName;
	protected final Class<?> requiredClass;
	protected final String[] editableQualities;

	@SuppressWarnings("unchecked")
	public PropertyHintSynchronizer(String hintName, IThingKey<?> propertyName, Class<?> requiredClass,
			String... editableQualities) {
		this.hintName = hintName;
		this.propertyName = (IThingKey<Object>) propertyName;
		this.requiredClass = requiredClass;
		this.editableQualities = editableQualities;
	}

	@Override
	public void restoreHints(IHintRepository repository, Object context, IThing thing) {
		if (UserEditableUtils.isEditableForAnyQualities(thing, editableQualities)) {
			try {
				Object value = repository.getHint(context, hintName);
				if (value != null) {
					thing.set(propertyName, value);
				}
			}
			catch (PropertyDecodeException e) {
			}
		}
	}

	@Override
	public void storeHints(IHintRepository repository, Object context, IThing thing, BNAModelEvent evt) {

		// ignore property changes other than those that we are interested in
		if (evt != null) {
			ThingEvent te = evt.getThingEvent();
			if (te != null) {
				if (!propertyName.equals(te.getPropertyName())) {
					return;
				}
			}
		}
		if (requiredClass.isInstance(thing) && UserEditableUtils.isEditableForAnyQualities(thing, editableQualities)) {
			Object value = thing.get(propertyName);
			if (value != null && value instanceof Serializable) {
				repository.storeHint(context, hintName, (Serializable) value);
			}
		}
	}
}
