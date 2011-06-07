package org.archstudio.xadl;

import java.util.List;

import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

public interface IXArchRelativePathTrackerListener {

	public void processAdd(ObjRef objRef, List<ObjRef> relativeAncestorRefs);

	public void processUpdate(ObjRef objRef, List<ObjRef> relativeAncestorRefs, XArchADTModelEvent evt,
			XArchADTPath relativeSourceTargetPath);

	public void processRemove(ObjRef objRef, List<ObjRef> relativeAncestorRefs);
}
