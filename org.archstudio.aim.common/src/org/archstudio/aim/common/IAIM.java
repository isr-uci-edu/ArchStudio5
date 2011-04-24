package org.archstudio.aim.common;

import org.archstudio.xarchadt.common.ObjRef;

public interface IAIM {

	public void instantiate(String name, ObjRef documentRootRef, ObjRef structureRef) throws ArchitectureInstantiationException;

	//public void init(IMyxRuntime myx, String name);
	public void begin(String name);
	public void end(String name);
	public void destroy(String name);
}
