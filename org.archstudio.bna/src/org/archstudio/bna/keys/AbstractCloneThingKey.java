package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Functions;

@NonNullByDefault
public abstract class AbstractCloneThingKey<D, V> extends AbstractThingKey<D, V> {

	public static final Function<IThingKey<?>, IThingKey<?>> iThingKey() {
		return Functions.identity();
	}

	protected final Function<? super V, V> cloneFunction;

	protected AbstractCloneThingKey(D keyData, boolean isFireEventOnChange, Function<? super V, V> cloneFunction) {
		super(keyData, isFireEventOnChange);
		this.cloneFunction = cloneFunction;
	}

	@Override
	public final @Nullable
	V preWrite(@Nullable V value) {
		return cloneFunction.apply(value);
	}

	@Override
	public final @Nullable
	V postRead(@Nullable V value) {
		return cloneFunction.apply(value);
	}
}
