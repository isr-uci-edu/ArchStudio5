package org.archstudio.archedit.core;

import org.archstudio.xarchadt.ObjRef;

interface IReferenceNodeInfo extends INodeInfo {
	public String getFeatureName();

	public ObjRef getParentRef();

	public boolean isMultiple();

	public int getIndex();
}
