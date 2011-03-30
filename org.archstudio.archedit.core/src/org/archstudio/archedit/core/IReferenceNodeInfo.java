package org.archstudio.archedit.core;

import org.archstudio.xarchadt.common.ObjRef;

interface IReferenceNodeInfo extends INodeInfo {

	public String getFeatureName();
	public ObjRef getParentRef();
	public Class<?> getFeatureClass();
	
	public boolean isMultiple();
	public int getIndex();
}