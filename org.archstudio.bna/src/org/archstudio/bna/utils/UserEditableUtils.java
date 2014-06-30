package org.archstudio.bna.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IIsBackground;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class UserEditableUtils {

	private static final IThingKey<Set<String>> USER_EDITABLE_QUALITIES_KEY = ThingKey.create("userEditableQualities",
			ThingKey.<String> set(null));

	public static void addEditableQualities(final IThing thing, final String... qualities) {
		Set<String> newEditableQualities = Sets.newHashSet(thing.get(USER_EDITABLE_QUALITIES_KEY,
				Sets.<String> newHashSet()));
		newEditableQualities.addAll(Arrays.asList(qualities));
		thing.set(USER_EDITABLE_QUALITIES_KEY, newEditableQualities);
	}

	public static void removeEditableQualities(final IThing thing, final String... qualities) {
		Set<String> newEditableQualities = Sets.newHashSet(thing.get(USER_EDITABLE_QUALITIES_KEY,
				Collections.<String> emptySet()));
		newEditableQualities.removeAll(Arrays.asList(qualities));
		thing.set(USER_EDITABLE_QUALITIES_KEY, newEditableQualities);
	}

	public static boolean isEditable(IThing thing) {
		if (thing.has(IIsHidden.HIDDEN_KEY, true)) {
			return false;
		}
		if (thing.has(IIsBackground.BACKGROUND_KEY, true)) {
			return false;
		}
		return true;
	}

	public static boolean isEditableForAllQualities(IThing thing, final String... editableQualities) {
		if (!isEditable(thing)) {
			return false;
		}
		if (editableQualities.length == 0) {
			return true;
		}
		Set<String> thingEditableQualities = thing.get(USER_EDITABLE_QUALITIES_KEY, Collections.<String> emptySet());
		for (String editableQuality : editableQualities) {
			if (!thingEditableQualities.contains(editableQuality)) {
				return false;
			}
		}
		return true;
	}

	public static <T extends IThing> List<T> getEditableForAllQualities(Iterable<T> things,
			final String... editableQualities) {
		return Lists.newArrayList(Iterables.filter(things, new Predicate<IThing>() {

			@Override
			public boolean apply(IThing input) {
				return isEditableForAllQualities(input, editableQualities);
			}
		}));
	}

	public static <T extends IThing> List<T> getEditableForAllQualities(Iterable<IThing> things, Class<T> ofType,
			final String... editableQualities) {
		return Lists.newArrayList(Iterables.filter(getEditableForAllQualities(things, editableQualities), ofType));
	}

	public static boolean isEditableForAnyQualities(IThing thing, final String... editableQualities) {
		if (!isEditable(thing)) {
			return false;
		}
		if (editableQualities.length == 0) {
			return true;
		}
		Set<String> thingEditableQualities = thing.get(USER_EDITABLE_QUALITIES_KEY, Collections.<String> emptySet());
		for (String editableQuality : editableQualities) {
			if (thingEditableQualities.contains(editableQuality)) {
				return true;
			}
		}
		return false;
	}

	public static List<IThing> getEditableForAnyQualities(Iterable<IThing> things, final String... editableQualities) {
		return Lists.newArrayList(Iterables.filter(things, new Predicate<IThing>() {

			@Override
			public boolean apply(IThing input) {
				return isEditableForAnyQualities(input, editableQualities);
			}
		}));
	}

	public static <T extends IThing> List<T> getEditableForAnyQualities(List<IThing> things, Class<T> ofType,
			final String... editableQualities) {
		return Lists.newArrayList(Iterables.filter(getEditableForAnyQualities(things, editableQualities), ofType));
	}
}
