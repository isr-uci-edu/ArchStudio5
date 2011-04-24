package org.archstudio.graphlayout.core;

import org.eclipse.jface.preference.IPreferenceStore;

import org.archstudio.graphlayout.common.GraphLayout;
import org.archstudio.graphlayout.common.GraphLayoutException;
import org.archstudio.graphlayout.common.GraphLayoutParameters;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.ObjRef;

public interface ILayoutEngine {
	public String getID();

	public String getDescription();

	public GraphLayout layoutGraph(IXArchADT xarch, IPreferenceStore prefs, ObjRef rootRef, GraphLayoutParameters params) throws GraphLayoutException;
}
