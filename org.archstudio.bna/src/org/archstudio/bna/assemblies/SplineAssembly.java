package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.things.labels.ArrowheadThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class SplineAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = SplineAssembly.class.getName();

	protected SplineThing splineThing = null;
	protected ArrowheadThing endpoint1ArrowheadThing = null;
	protected ArrowheadThing endpoint2ArrowheadThing = null;
	protected SplineGlassThing splineGlassThing = null;

	protected SplineAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof SplineThing) {
				splineThing = (SplineThing) t;
				for (IThing t2 : model.getChildThings(splineThing)) {
					if (t2 instanceof ArrowheadThing) {
						ArrowheadThing arrowheadThing = (ArrowheadThing) t2;
						switch (arrowheadThing.getEndpointNumber()) {
						case 1:
							endpoint1ArrowheadThing = arrowheadThing;
							break;
						case 2:
							endpoint2ArrowheadThing = arrowheadThing;
							break;
						}
					}
				}
			}
			else if (t instanceof SplineGlassThing) {
				splineGlassThing = (SplineGlassThing) t;
			}
		}
	}

	public ArrowheadThing getEndpoint1ArrowheadThing() {
		reindexIfNeeded();
		return endpoint1ArrowheadThing;
	}

	public ArrowheadThing getEndpoint2ArrowheadThing() {
		reindexIfNeeded();
		return endpoint2ArrowheadThing;
	}

	public SplineGlassThing getSplineGlassThing() {
		reindexIfNeeded();
		return splineGlassThing;
	}

	public SplineThing getSplineThing() {
		reindexIfNeeded();
		return splineThing;
	}

	public static SplineAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		SplineThing splineThing = new SplineThing();
		m.addThing(splineThing, rootThing);

		ArrowheadThing arrowheadThing1 = new ArrowheadThing();
		arrowheadThing1.setEndpointNumber(1);
		m.addThing(arrowheadThing1, splineThing);

		ArrowheadThing arrowheadThing2 = new ArrowheadThing();
		arrowheadThing2.setEndpointNumber(2);
		m.addThing(arrowheadThing2, splineThing);

		SplineGlassThing splineGlassThing = new SplineGlassThing();
		m.addThing(splineGlassThing, rootThing);

		//Set up connections
		splineThing.setEndpointsMasterThingID(splineGlassThing.getID());
		splineThing.setMidpointsMasterThingID(splineGlassThing.getID());
		arrowheadThing1.setEndpointMasterThingID(splineThing.getID());
		arrowheadThing2.setEndpointMasterThingID(splineThing.getID());
		m.endBulkChange();

		return new SplineAssembly(m, rootThing);
	}

	public static SplineAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new SplineAssembly(m, rootThing);
	}

}
