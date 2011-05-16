package org.archstudio.bna.logics.hints;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.assemblies.IAssembly;

public interface IHintSynchronizer {

	public void setBNAWorld(IBNAWorld world);

	public void restoreHints(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing);

	public void thingChanged(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName, Object oldValue,
	        Object newValue);

	public void repositoryChanged(IHintRepository repository, Object context, IAssembly[] assemblies, String hintName);
}
