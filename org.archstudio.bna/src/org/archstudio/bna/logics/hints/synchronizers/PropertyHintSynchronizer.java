package org.archstudio.bna.logics.hints.synchronizers;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.IHintRepository.HintValue;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class PropertyHintSynchronizer extends AbstractHintSynchronizer {

	public boolean DEBUG = false;

	protected final IThingKey<Object> propertyName;
	protected final Function<Object, Object> propertyTranslator;
	protected final Class<?> requiredThingClass;
	protected final Predicate<IThing> filter;
	protected final String[] editableQualities;

	public <T> PropertyHintSynchronizer(IBNAWorld world, String hintName, IThingKey<T> propertyName,
			Function<Object, Object> propertyTranslator, Class<?> requiredThingClass, String... editableQualities) {
		this(world, hintName, propertyName, propertyTranslator, requiredThingClass, Predicates.<IThing> alwaysTrue(),
				editableQualities);
	}

	@SuppressWarnings("unchecked")
	public <T> PropertyHintSynchronizer(IBNAWorld world, String hintName, IThingKey<T> propertyName,
			Function<Object, Object> propertyTranslator, Class<?> requiredThingClass, Predicate<IThing> filter,
			String... editableQualities) {
		super(world, hintName);
		this.propertyName = (IThingKey<Object>) propertyName;
		this.propertyTranslator = propertyTranslator;
		this.requiredThingClass = requiredThingClass;
		this.filter = filter;
		this.editableQualities = editableQualities;
	}

	@Override
	public Collection<IThingKey<?>> getThingPropertiesOfInterest() {
		return Collections.<IThingKey<?>> singleton(propertyName);
	}

	@Override
	public void restoreHints(IHintRepository repository, Object context, IThing thing, String hintName,
			HintValue hintValue) {
		BNAUtils.checkLock();
		checkArgument(this.hintName.equals(hintName));

		if (wasIgnored(context)) {
			return;
		}

		if (filter.apply(thing) && requiredThingClass.isInstance(thing)
				&& UserEditableUtils.isEditableForAllQualities(thing, editableQualities)) {
			if (hintValue.isPresent()) {
				if (DEBUG) {
					System.err.println("Restoring: " + propertyName);
				}
				Object o = hintValue.getValue();
				if (propertyTranslator != null) {
					o = propertyTranslator.apply(o);
				}
				if (o != null) {
					thing.set(propertyName, o);
				}
				else {
					thing.remove(propertyName);
				}
			}
		}
	}

	@Override
	public void storeHints(IHintRepository repository, Object context, IThing thing, IThingKey<?> key) {
		BNAUtils.checkLock();

		if (filter.apply(thing) && requiredThingClass.isInstance(thing)
				&& UserEditableUtils.isEditableForAllQualities(thing, editableQualities)) {
			Object value = thing.get(propertyName);
			if (DEBUG) {
				System.err.println("  Storing: " + hintName);
			}
			if (value != null && value instanceof Serializable) {
				if (repository.storeHint(context, hintName, (Serializable) value)) {
					ignore(context);
				}
			}
			else {
				if (repository.removeHint(context, hintName)) {
					ignore(context);
				}
			}
		}
	}

	@Override
	public String toString() {
		return hintName + "=" + propertyName;
	}

}
