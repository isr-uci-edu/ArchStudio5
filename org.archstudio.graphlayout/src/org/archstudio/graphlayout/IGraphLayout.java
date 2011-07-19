package org.archstudio.graphlayout;

import org.archstudio.xarchadt.ObjRef;

public interface IGraphLayout {
	public String[] getEngineIDs();

	public String getEngineDescription(String engineID);

	public GraphLayout layoutGraph(String engineID, ObjRef rootRef, GraphLayoutParameters params)
			throws GraphLayoutException;
}
