package org.archstudio.bna.utils;

import java.util.Arrays;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.SetThingKey;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class UserEditableUtils {

	private static final IThingKey<Set<String>> USER_EDITABLE_QUALITIES_KEY = SetThingKey
			.create("userEditableQualities");

	public static void addEditableQualities(final IThing thing, final String... qualities) {
		thing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Set<String> newEditableQualities = Sets.newHashSet(thing.get(USER_EDITABLE_QUALITIES_KEY));
				newEditableQualities.addAll(Arrays.asList(qualities));
				thing.set(USER_EDITABLE_QUALITIES_KEY, newEditableQualities);
			}
		});
	}

	public static void removeEditableQualities(final IThing thing, final String... qualities) {
		thing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Set<String> newEditableQualities = Sets.newHashSet(thing.get(USER_EDITABLE_QUALITIES_KEY));
				newEditableQualities.removeAll(Arrays.asList(qualities));
				thing.set(USER_EDITABLE_QUALITIES_KEY, newEditableQualities);
			}
		});
	}

	public static boolean isEditable(IThing thing) {
		if (Boolean.TRUE.equals(thing.get(IIsHidden.HIDDEN_KEY))) {
			return false;
		}
		return true;
	}

	public static boolean isEditableForAllQualities(IThing thing, final String... editableQualities) {
		if (!isEditable(thing)) {
			return false;
		}
		Set<String> thingEditableQualities = thing.get(USER_EDITABLE_QUALITIES_KEY);
		for (String editableQuality : editableQualities) {
			if (!thingEditableQualities.contains(editableQuality)) {
				return false;
			}
		}
		return true;
	}

	public static <T extends IThing> Iterable<T> getEditableForAllQualities(Iterable<T> things,
			final String... editableQualities) {
		return Iterables.filter(things, new Predicate<IThing>() {
			@Override
			public boolean apply(IThing input) {
				return isEditableForAllQualities(input, editableQualities);
			}
		});
	}

	public static <T extends IThing> Iterable<T> getEditableForAllQualities(Iterable<IThing> things, Class<T> ofType,
			final String... editableQualities) {
		return Iterables.filter(getEditableForAllQualities(things, editableQualities), ofType);
	}

	public static boolean isEditableForAnyQualities(IThing thing, final String... editableQualities) {
		if (!isEditable(thing)) {
			return false;
		}
		Set<String> thingEditableQualities = thing.get(USER_EDITABLE_QUALITIES_KEY);
		for (String editableQuality : editableQualities) {
			if (thingEditableQualities.contains(editableQuality)) {
				return true;
			}
		}
		return false;
	}

	public static Iterable<IThing> getEditableForAnyQualities(Iterable<IThing> things,
			final String... editableQualities) {
		return Iterables.filter(things, new Predicate<IThing>() {
			@Override
			public boolean apply(IThing input) {
				return isEditableForAnyQualities(input, editableQualities);
			}
		});
	}

	public static <T extends IThing> Iterable<T> getEditableForAnyQualities(Iterable<IThing> things, Class<T> ofType,
			final String... editableQualities) {
		return Iterables.filter(getEditableForAnyQualities(things, editableQualities), ofType);
	}
}
