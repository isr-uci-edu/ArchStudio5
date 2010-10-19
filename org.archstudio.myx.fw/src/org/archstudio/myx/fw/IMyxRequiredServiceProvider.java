package org.archstudio.myx.fw;

import java.util.Collection;

public interface IMyxRequiredServiceProvider {
	public Collection<? extends Object> getServiceObjects(IMyxName interfaceName);
}
