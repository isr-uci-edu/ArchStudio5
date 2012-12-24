package org.archstudio.xarchadt.variability;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public interface ChangeSetTransform {

	public void transform(IXArchADT xarch, ObjRef documentRootRef);

}
