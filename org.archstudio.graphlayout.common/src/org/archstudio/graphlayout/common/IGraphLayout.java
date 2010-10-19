package org.archstudio.graphlayout.common;

import org.archstudio.xarchadt.common.ObjRef;

public interface IGraphLayout {
	public String[] getEngineIDs();

	public String getEngineDescription(String engineID);

	public GraphLayout layoutGraph(String engineID, ObjRef rootRef, GraphLayoutParameters params) throws GraphLayoutException;
}
