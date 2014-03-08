package org.archstudio.bna.keys;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.sysutils.AbstractGenericKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

@NonNullByDefault
public abstract class AbstractThingKey<D, V> extends AbstractGenericKey<D, V> implements IThing.IThingKey<V> {

	private static final AtomicInteger keyUIDGenerator = new AtomicInteger();

	private static final LoadingCache<Object, Integer> keyDataToUID = CacheBuilder.newBuilder().build(
			new CacheLoader<Object, Integer>() {
				@Override
				public Integer load(Object key) throws Exception {
					return (int) keyUIDGenerator.getAndIncrement();
				};
			});

	private static final Map<Integer, IThingKey<?>> uidToKey = Maps.newHashMap();

	public static final IThingKey<?> getKey(int uid) {
		synchronized (uidToKey) {
			return checkNotNull(uidToKey.get(uid));
		}
	}

	private final int uid;
	private final boolean isFireEventOnChange;

	protected AbstractThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData);
		this.uid = keyDataToUID.getUnchecked(keyData);
		this.isFireEventOnChange = isFireEventOnChange;
		synchronized (uidToKey) {
			uidToKey.put(uid, this);
		}
	}

	@Override
	public Object getID() {
		return getKeyData();
	}

	@Override
	public final boolean isFireEventOnChange() {
		return isFireEventOnChange;
	}

	@Override
	public @Nullable
	V preWrite(@Nullable V value) {
		return value;
	}

	@Override
	public @Nullable
	V postRead(@Nullable V value) {
		return value;
	}

}