package org.archstudio.graphlayout.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import org.archstudio.graphlayout.GraphLayout;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.graphlayout.GraphLayoutParameters;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.graphlayout.core.graphviz.DotLayoutEngine;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class GraphLayoutMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_IN_GRAPHLAYOUT = MyxUtils.createName("graphlayout");

	protected IXArchADT xarch = null;
	protected IPreferenceStore prefs = null;

	protected GraphLayoutImpl graphLayoutImpl = null;

	public GraphLayoutMyxComponent() {
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = (IXArchADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			prefs = (IPreferenceStore) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			prefs = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public void init() {
		graphLayoutImpl = new GraphLayoutImpl();
		graphLayoutImpl.addLayoutEngine(new DotLayoutEngine());
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_GRAPHLAYOUT)) {
			return graphLayoutImpl;
		}
		return null;
	}

	public class GraphLayoutImpl implements IGraphLayout {
		protected List<ILayoutEngine> engineList = new ArrayList<ILayoutEngine>();

		public void addLayoutEngine(ILayoutEngine engine) {
			engineList.add(engine);
		}

		public void removeLayoutEngine(ILayoutEngine engine) {
			engineList.remove(engine);
		}

		public ILayoutEngine[] getAllLayoutEngines() {
			return engineList.toArray(new ILayoutEngine[engineList.size()]);
		}

		public ILayoutEngine getLayoutEngine(String id) {
			ILayoutEngine[] engines = getAllLayoutEngines();
			for (int i = 0; i < engines.length; i++) {
				if (engines[i].getID().equals(id)) {
					return engines[i];
				}
			}
			return null;
		}

		public String[] getEngineIDs() {
			ILayoutEngine[] engines = getAllLayoutEngines();
			String[] engineIDs = new String[engines.length];
			for (int i = 0; i < engines.length; i++) {
				engineIDs[i] = engines[i].getID();
			}
			return engineIDs;
		}

		public String getEngineDescription(String engineID) {
			ILayoutEngine engine = getLayoutEngine(engineID);
			if (engine == null) {
				return null;
			}
			return engine.getDescription();
		}

		public GraphLayout layoutGraph(String engineID, ObjRef rootRef, GraphLayoutParameters params) throws GraphLayoutException {
			ILayoutEngine engine = getLayoutEngine(engineID);
			if (engine == null) {
				throw new GraphLayoutException("No graph layout engine with ID: " + engineID + " exists.");
			}
			return engine.layoutGraph(xarch, prefs, rootRef, params);
		}
	}
}
