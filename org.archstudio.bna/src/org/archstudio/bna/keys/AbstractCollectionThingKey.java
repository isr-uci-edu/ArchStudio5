package org.archstudio.bna.keys;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public abstract class AbstractCollectionThingKey<D, C extends Collection<V>, V> extends AbstractCloneThingKey<D, C> {

	public static final <V> Function<Collection<V>, List<V>> list(final Function<V, V> cloneFunction) {
		return new Function<Collection<V>, List<V>>() {

			@Override
			public List<V> apply(Collection<V> input) {
				return Lists.newArrayList(input != null ? cloneFunction != null ? Collections2.transform(input,
						cloneFunction) : input : Collections.<V> emptyList());
			}
		};
	}

	public static final <V> Function<Collection<V>, Set<V>> set(final Function<V, V> cloneFunction) {
		return new Function<Collection<V>, Set<V>>() {

			@Override
			public Set<V> apply(Collection<V> input) {
				return Sets.newHashSet(input != null ? cloneFunction != null ? Collections2.transform(input,
						cloneFunction) : input : Collections.<V> emptySet());
			}
		};
	}

	protected AbstractCollectionThingKey(D keyData, boolean isFireEventOnChange, Function<? super C, C> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
