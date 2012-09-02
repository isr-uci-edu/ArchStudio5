package org.archstudio.bna.logics.hints;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;

public interface IHintSynchronizer {

	public void setBNAWorld(IBNAWorld world);

	public void restoreHints(IHintRepository repository, Object context, IThing thing);

	public void storeHints(IHintRepository repository, Object context, IThing thing, BNAModelEvent evt);

	//	public void repositoryChanged(IHintRepository repository, Object context, IAssembly[] assemblies, String hintName);
}
