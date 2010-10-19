package org.archstudio.myx.fw;

import java.util.List;

public interface IMyxWeld extends java.io.Serializable {

	public List<? extends IMyxName> getRequiredPath();

	public IMyxName getRequiredBrickName();

	public IMyxName getRequiredInterfaceName();

	public List<? extends IMyxName> getProvidedPath();

	public IMyxName getProvidedBrickName();

	public IMyxName getProvidedInterfaceName();
}
