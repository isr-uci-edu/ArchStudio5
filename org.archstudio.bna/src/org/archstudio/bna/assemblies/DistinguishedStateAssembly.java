package org.archstudio.bna.assemblies;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.DistinguishedStateGlassThing;
import org.archstudio.bna.things.shapes.DistinguishedStateThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class DistinguishedStateAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = DistinguishedStateAssembly.class.getName();

	protected DistinguishedStateThing distinguishedStateThing = null;
	protected DistinguishedStateGlassThing distinguishedStateGlassThing = null;

	protected DistinguishedStateAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof DistinguishedStateThing) {
				distinguishedStateThing = (DistinguishedStateThing) t;
			}
			else if (t instanceof DistinguishedStateGlassThing) {
				distinguishedStateGlassThing = (DistinguishedStateGlassThing) t;
			}
		}
	}

	public DistinguishedStateThing getDistinguishedStateThing() {
		reindexIfNeeded();
		return distinguishedStateThing;
	}

	public DistinguishedStateGlassThing getDistinguishedStateGlassThing() {
		reindexIfNeeded();
		return distinguishedStateGlassThing;
	}

	public static DistinguishedStateAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		DistinguishedStateThing distinguishedStateThing = new DistinguishedStateThing();
		m.addThing(distinguishedStateThing, rootThing);

		DistinguishedStateGlassThing distinguishedStateGlassThing = new DistinguishedStateGlassThing();
		m.addThing(distinguishedStateGlassThing, rootThing);

		//Set up connections
		distinguishedStateThing.setAnchorPointMasterThingID(distinguishedStateGlassThing.getID());
		distinguishedStateThing.setAnchorPointMirrorOffsets(new Point(0, 0));

		m.endBulkChange();

		return new DistinguishedStateAssembly(m, rootThing);
	}

	public static DistinguishedStateAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new DistinguishedStateAssembly(m, rootThing);
	}
}
