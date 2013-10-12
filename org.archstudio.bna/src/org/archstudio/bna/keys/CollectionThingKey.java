package org.archstudio.bna.keys;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.archstudio.bna.IThing.IThingKey;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CollectionThingKey<D, C extends Collection<V>, V> extends AbstractCollectionThingKey<D, C, V> {

	public static final <V> Function<Collection<V>, List<V>> list(final Function<V, V> cloneFunction) {
		return new Function<Collection<V>, List<V>>() {

			@Override
			public List<V> apply(Collection<V> input) {
				return input == null ? null : Lists.newArrayList(cloneFunction == null ? input : Collections2
						.transform(input, cloneFunction));
			}
		};
	}

	public static final <V> Function<Collection<V>, Set<V>> set(final Function<V, V> cloneFunction) {
		return new Function<Collection<V>, Set<V>>() {

			@Override
			public Set<V> apply(Collection<V> input) {
				return input == null ? null : Sets.newHashSet(cloneFunction == null ? input : Collections2.transform(
						input, cloneFunction));
			}
		};
	}

	public static <D, C extends Collection<V>, V> IThingKey<C> create(D keyData,
			final Function<Collection<V>, C> cloneFunction) {
		return new CollectionThingKey<D, C, V>(keyData, true, cloneFunction);
	}

	public CollectionThingKey(D keyData, boolean isFireEventOnChange, Function<? super C, C> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
