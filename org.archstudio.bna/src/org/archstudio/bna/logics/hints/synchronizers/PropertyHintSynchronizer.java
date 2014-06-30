package org.archstudio.bna.logics.hints.synchronizers;

import java.io.Serializable;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.IHintRepository.HintValue;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class PropertyHintSynchronizer extends AbstractHintSynchronizer {

	public boolean DEBUG = false;

	protected final String hintNameSuffix;
	protected final IThingKey<Object> propertyName;
	protected final Class<?> requiredClass;
	protected final String[] editableQualities;

	@SuppressWarnings("unchecked")
	public PropertyHintSynchronizer(IBNAWorld world, String hintNameSuffix, IThingKey<?> propertyName,
			Class<?> requiredClass, String... editableQualities) {
		super(world);
		this.hintNameSuffix = hintNameSuffix;
		this.propertyName = (IThingKey<Object>) propertyName;
		this.requiredClass = requiredClass;
		this.editableQualities = editableQualities;
	}

	@Override
	synchronized public void restoreHints(IHintRepository repository, Object context, IThing thing,
			@Nullable String name) {
		String ourName = getHintName(thing);
		if (name == null) {
			name = ourName;
		}
		if (ourName.equals(name)) {
			if (!wasIgnored(context, name)) {
				if (requiredClass.isInstance(thing)
						&& UserEditableUtils.isEditableForAnyQualities(thing, editableQualities)) {
					try {
						HintValue value = repository.getHint(context, name);
						if (value.isPresent()) {
							if (DEBUG) {
								System.err.println("Restoring: " + name);
							}
							if (value.getValue() != null) {
								thing.set(propertyName, value.getValue());
							}
							else {
								thing.remove(propertyName);
							}
						}
					}
					catch (PropertyDecodeException e) {
					}
				}
			}
		}
	}

	@Override
	synchronized public void storeHints(IHintRepository repository, Object context, IThing thing,
			@Nullable BNAModelEvent evt) {

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
			String name = getHintName(thing);
			if (DEBUG) {
				System.err.println("  Storing: " + name);
			}
			if (value != null && value instanceof Serializable) {
				if (repository.storeHint(context, name, (Serializable) value)) {
					ignore(context, name);
				}
			}
			else {
				if (repository.removeHint(context, name)) {
					ignore(context, name);
				}
			}
		}
	}

	private String getHintName(IThing thing) {
		List<String> path = Lists.newArrayList();
		while (thing != null) {
			IThingRefKey<?> partKey = Assemblies.getPartName(thing);
			if (partKey == null) {
				break;
			}
			path.add(0, partKey.toString());
			thing = Assemblies.getRootWithPart(model, thing);
		}
		path.add(hintNameSuffix);
		return Joiner.on("/").join(path);
	}
}
