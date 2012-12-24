package org.archstudio.bna.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

public class BNAPath {

	public static BNAPath create(IThingRefKey<?>... thingRefKeys) {
		return BNAPath.create(Arrays.asList(thingRefKeys));
	}

	public static BNAPath create(Iterable<IThingRefKey<?>> thingRefKeys) {
		return new BNAPath(thingRefKeys);
	}

	private static final BNAPath EMPTY_PATH = BNAPath.create();

	public static BNAPath emptyPath() {
		return EMPTY_PATH;
	}

	public static IThing resolve(IBNAModel bnaModel, IThing rootThing, BNAPath targetThingPath) {
		for (Iterator<IThingRefKey<?>> i = targetThingPath.keyPath.iterator(); rootThing != null && i.hasNext();) {
			IThingRefKey<?> thingRefKey = i.next();
			rootThing = thingRefKey.get(rootThing, bnaModel);
		}
		return rootThing;
	}

	private final List<IThingRefKey<?>> keyPath;

	protected BNAPath(Iterable<IThingRefKey<?>> keyPath) {
		this.keyPath = Lists.newArrayList(keyPath);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (keyPath == null ? 0 : keyPath.hashCode());
		return result;
	}

	public boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BNAPath other = (BNAPath) obj;
		if (keyPath == null) {
			if (other.keyPath != null) {
				return false;
			}
		}
		else if (!keyPath.equals(other.keyPath)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "BNAPath [keyPath=" + keyPath + "]";
	}
}
