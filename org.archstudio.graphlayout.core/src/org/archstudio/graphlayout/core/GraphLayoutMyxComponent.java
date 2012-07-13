package org.archstudio.graphlayout.core;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.graphlayout.GraphLayout;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.graphlayout.GraphLayoutParameters;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.graphlayout.core.graphviz.DotLayoutEngine;
import org.archstudio.xarchadt.ObjRef;

/**
 * Myx brick: "Graph Layout Impl"
 * 
 * @see org.archstudio.graphlayout.core.GraphLayoutMyxComponentStub
 * @generated
 */
public class GraphLayoutMyxComponent extends org.archstudio.graphlayout.core.GraphLayoutMyxComponentStub {

	public GraphLayoutMyxComponent() {
	}

	@Override
	public void init() {
		graphLayout = new GraphLayoutImpl();
		((GraphLayoutImpl) graphLayout).addLayoutEngine(new DotLayoutEngine());
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
			for (ILayoutEngine engine : engines) {
				if (engine.getID().equals(id)) {
					return engine;
				}
			}
			return null;
		}

		@Override
		public String[] getEngineIDs() {
			ILayoutEngine[] engines = getAllLayoutEngines();
			String[] engineIDs = new String[engines.length];
			for (int i = 0; i < engines.length; i++) {
				engineIDs[i] = engines[i].getID();
			}
			return engineIDs;
		}

		@Override
		public String getEngineDescription(String engineID) {
			ILayoutEngine engine = getLayoutEngine(engineID);
			if (engine == null) {
				return null;
			}
			return engine.getDescription();
		}

		@Override
		public GraphLayout layoutGraph(String engineID, ObjRef rootRef, GraphLayoutParameters params)
				throws GraphLayoutException {
			ILayoutEngine engine = getLayoutEngine(engineID);
			if (engine == null) {
				throw new GraphLayoutException("No graph layout engine with ID: " + engineID + " exists.");
			}
			return engine.layoutGraph(xarch, Activator.getDefault().getPreferenceStore(), rootRef, params);
		}
	}

}