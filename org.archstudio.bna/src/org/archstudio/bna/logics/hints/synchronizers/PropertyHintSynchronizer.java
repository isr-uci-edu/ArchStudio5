package org.archstudio.bna.logics.hints.synchronizers;

import java.io.Serializable;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class PropertyHintSynchronizer extends AbstractHintSynchronizer {

	protected final String hintNameSuffix;
	protected final IThingKey<Object> propertyName;
	protected final Class<?> requiredClass;
	protected final String[] editableQualities;

	@SuppressWarnings("unchecked")
	public PropertyHintSynchronizer(String hintNameSuffix, IThingKey<?> propertyName, Class<?> requiredClass,
			String... editableQualities) {
		this.hintNameSuffix = hintNameSuffix;
		this.propertyName = (IThingKey<Object>) propertyName;
		this.requiredClass = requiredClass;
		this.editableQualities = editableQualities;
	}

	@Override
	public void restoreHints(IHintRepository repository, Object context, IThing thing, @Nullable String name) {
		if (requiredClass.isInstance(thing) && UserEditableUtils.isEditableForAnyQualities(thing, editableQualities)) {
			try {
				Object value = repository.getHint(context, getHintName(thing));
				if (value != null) {
					thing.set(propertyName, value);
				}
			}
			catch (PropertyDecodeException e) {
			}
		}
	}

	@Override
	public void storeHints(IHintRepository repository, Object context, IThing thing, @Nullable BNAModelEvent evt) {

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
				repository.storeHint(context, getHintName(thing), (Serializable) value);
			}
		}
	}

	private String getHintName(IThing thing) {
		IBNAModel model = bnaWorld.getBNAModel();
		List<String> path = Lists.newArrayList();
		while (thing != null) {
			IThingRefKey<?> partKey = Assemblies.getPartKey(thing);
			if (partKey == null)
				break;
			path.add(0, partKey.getKeyData().toString());
			thing = Assemblies.getAssemblyWithPart(model, thing);
		}
		path.add(hintNameSuffix);
		return Joiner.on("/").join(path);
	}
}
