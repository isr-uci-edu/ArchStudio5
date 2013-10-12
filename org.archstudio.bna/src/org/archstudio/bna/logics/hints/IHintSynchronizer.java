package org.archstudio.bna.logics.hints;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.eclipse.jdt.annotation.Nullable;

public interface IHintSynchronizer {

	public void restoreHints(IHintRepository repository, Object context, IThing thing, @Nullable String name);

	public void storeHints(IHintRepository repository, Object context, IThing thing, @Nullable BNAModelEvent evt);

}
