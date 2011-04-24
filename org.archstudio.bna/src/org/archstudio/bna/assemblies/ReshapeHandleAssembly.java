package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class ReshapeHandleAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = ReshapeHandleAssembly.class.getName();

	protected ReshapeHandleThing reshapeHandleThing = null;
	protected ReshapeHandleGlassThing reshapeHandleGlassThing = null;

	protected ReshapeHandleAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof ReshapeHandleThing) {
				reshapeHandleThing = (ReshapeHandleThing) t;
			}
			else if (t instanceof ReshapeHandleGlassThing) {
				reshapeHandleGlassThing = (ReshapeHandleGlassThing) t;
			}
		}
	}

	public ReshapeHandleThing getReshapeHandleThing() {
		reindexIfNeeded();
		return reshapeHandleThing;
	}

	public ReshapeHandleGlassThing getReshapeHandleGlassThing() {
		reindexIfNeeded();
		return reshapeHandleGlassThing;
	}

	public static ReshapeHandleAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		ReshapeHandleThing reshapeHandleThing = new ReshapeHandleThing();
		m.addThing(reshapeHandleThing, rootThing);

		ReshapeHandleGlassThing glassThing = new ReshapeHandleGlassThing();
		m.addThing(glassThing, rootThing);

		//Set up connections
		reshapeHandleThing.setAnchorPointMasterThingID(glassThing.getID());
		m.endBulkChange();

		return new ReshapeHandleAssembly(m, rootThing);
	}

	public static ReshapeHandleAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new ReshapeHandleAssembly(m, rootThing);
	}

}
