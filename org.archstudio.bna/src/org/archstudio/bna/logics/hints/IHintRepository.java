package org.archstudio.bna.logics.hints;

import java.io.Serializable;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.eclipse.jdt.annotation.Nullable;

public interface IHintRepository {

	public @Nullable
	Object getContextForThing(IBNAWorld world, IThing thing);

	public void storeHint(Object context, String name, @Nullable Serializable value);

	public @Nullable
	Object getHint(Object context, String name) throws PropertyDecodeException;

	public void addHintRepositoryChangeListener(IHintRepositoryChangeListener l);

	public void removeHintRepositoryChangeListener(IHintRepositoryChangeListener l);
}
