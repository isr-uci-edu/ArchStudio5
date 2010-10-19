package org.archstudio.bna.assemblies;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.utility.AssemblyRootThing;

import org.archstudio.swtutils.constants.Orientation;

public class SplineReshapeHandlesAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = SplineReshapeHandlesAssembly.class.getName();

	protected List<ReshapeHandleAssembly> handleList;

	protected SplineReshapeHandlesAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected synchronized void reindex() {
		if (handleList == null) {
			handleList = new ArrayList<ReshapeHandleAssembly>();
		}
		else {
			handleList.clear();
		}
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof IHasAssemblyData) {
				if (BNAUtils.isAssemblyRoot(t, ReshapeHandleAssembly.ASSEMBLY_KIND)) {
					ReshapeHandleAssembly rh = new ReshapeHandleAssembly(model, (IHasAssemblyData) t);
					handleList.add(rh);
				}
			}
		}
	}

	public ReshapeHandleAssembly[] getAllReshapeHandleAssemblies() {
		reindexIfNeeded();
		return handleList.toArray(new ReshapeHandleAssembly[handleList.size()]);
	}

	public static SplineReshapeHandlesAssembly create(IBNAModel m, IThing parentThing, int handleCount) {
		m.beginBulkChange();

		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		for (int i = 0; i < handleCount; i++) {
			ReshapeHandleAssembly rh = ReshapeHandleAssembly.create(m, rootThing);
			rh.getReshapeHandleGlassThing().setOrientation(Orientation.NONE);
			rh.getReshapeHandleThing().setOrientation(Orientation.NONE);
		}
		m.endBulkChange();
		return new SplineReshapeHandlesAssembly(m, rootThing);
	}

	public static SplineReshapeHandlesAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new SplineReshapeHandlesAssembly(m, rootThing);
	}

}
