package org.archstudio.bna.logics.hints;

import org.eclipse.jdt.annotation.Nullable;

public interface IHintRepositoryChangeListener {

	public void hintRepositoryChanged(IHintRepository repository, Object context, @Nullable String name);

}
