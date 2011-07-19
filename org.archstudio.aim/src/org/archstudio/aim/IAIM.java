package org.archstudio.aim;

import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.archstudio.xarchadt.ObjRef;

public interface IAIM {

	public void instantiate(String name, ObjRef documentRootRef, ObjRef structureRef, IMyxProgressMonitor monitor)
			throws ArchitectureInstantiationException;

	//public void init(IMyxRuntime myx, String name);
	public void begin(String name, IMyxProgressMonitor monitor);

	public void end(String name, IMyxProgressMonitor monitor);

	public void destroy(String name, IMyxProgressMonitor monitor);
}
