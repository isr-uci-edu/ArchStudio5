package org.archstudio.bna.logics.hints;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;

public interface IHintRepository {

	public @Nullable
	Object getContextForThing(IBNAWorld world, IThing thing);

	public Iterable<IThing> getThingsForContext(IBNAWorld world, Object context);

	public List<String> getStoredHintNames(Object context);

	public void storeHint(Object context, String hintName, Serializable hintValue);

	public @Nullable
	Object getHint(Object context, String hintName) throws PropertyDecodeException;

	public void addHintRepositoryChangeListener(IHintRepositoryChangeListener l);

	public void removeHintRepositoryChangeListener(IHintRepositoryChangeListener l);
}
