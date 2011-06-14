package org.archstudio.xadl;

import java.util.List;

import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

public interface IXArchRelativePathTrackerListener {

	public void processAdd(List<ObjRef> relLineageRefs, ObjRef objRef);

	public void processUpdate(List<ObjRef> relLineageRefs, XArchADTPath relPath, ObjRef objRef, XArchADTModelEvent evt);

	public void processRemove(List<ObjRef> relLineageRefs, ObjRef objRef);

}
