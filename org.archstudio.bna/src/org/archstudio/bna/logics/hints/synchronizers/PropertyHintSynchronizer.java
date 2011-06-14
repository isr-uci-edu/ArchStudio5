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
	protected final String[] editableQualities;

	@SuppressWarnings("unchecked")
	public PropertyHintSynchronizer(String hintName, IThingKey<?> propertyName, String... editableQualities) {
		this.hintName = hintName;
		this.propertyName = (IThingKey<Object>) propertyName;
		this.editableQualities = editableQualities;
	}

	@Override
	public void restoreHints(IHintRepository repository, Object context, IThing thing) {
		if (UserEditableUtils.isEditableForAllQualities(thing, editableQualities)) {
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
	public <ET extends IThing, EK extends IThing.IThingKey<EV>, EV> void storeHints(IHintRepository repository,
			Object context, ET thing, BNAModelEvent<ET, EK, EV> evt) {

		// ignore property changes other than those that we are interested in
		if (evt != null) {
			ThingEvent<ET, EK, EV> te = evt.getThingEvent();
			if (te != null) {
				if (!propertyName.equals(te.getPropertyName())) {
					return;
				}
			}
		}
		if (UserEditableUtils.isEditableForAllQualities(thing, editableQualities)) {
			Object value = thing.get(propertyName);
			if (value != null && value instanceof Serializable) {
				repository.storeHint(context, hintName, (Serializable) value);
			}
		}
	}
}
