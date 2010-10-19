package org.archstudio.archipelago.core;

import org.archstudio.xarchadt.common.ObjRef;

public interface IArchipelagoTreeNodeDataCache{

	public void setData(ObjRef documentRootRef, Object treeNode, String key, Object data);

	public Object getData(ObjRef documentRootRef, Object treeNode, String key);

	public void clear();

}