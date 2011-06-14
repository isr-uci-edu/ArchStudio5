package org.archstudio.bna.logics.hints;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

public interface IHintSynchronizer {

	public void setBNAWorld(IBNAWorld world);

	public void restoreHints(IHintRepository repository, Object context, IThing thing);

	public <ET extends IThing, EK extends IThingKey<EV>, EV> void storeHints(IHintRepository repository,
			Object context, ET thing, BNAModelEvent<ET, EK, EV> evt);

	//	public void repositoryChanged(IHintRepository repository, Object context, IAssembly[] assemblies, String hintName);
}
