package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.things.shapes.MappingThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class MappingAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = MappingAssembly.class.getName();

	protected MappingThing mappingThing = null;
	protected MappingGlassThing mappingGlassThing = null;

	protected MappingAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof MappingThing) {
				mappingThing = (MappingThing) t;
			}
			else if (t instanceof MappingGlassThing) {
				mappingGlassThing = (MappingGlassThing) t;
			}
		}
	}

	public MappingGlassThing getMappingGlassThing() {
		reindexIfNeeded();
		return mappingGlassThing;
	}

	public MappingThing getMappingThing() {
		reindexIfNeeded();
		return mappingThing;
	}

	public static MappingAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		MappingThing mappingThing = new MappingThing();
		m.addThing(mappingThing, rootThing);

		MappingGlassThing mappingGlassThing = new MappingGlassThing();
		m.addThing(mappingGlassThing, rootThing);

		//Set up connections
		mappingThing.setEndpointsMasterThingID(mappingGlassThing.getID());
		mappingThing.setMidpointsMasterThingID(mappingGlassThing.getID());
		m.endBulkChange();

		return new MappingAssembly(m, rootThing);
	}

	public static MappingAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new MappingAssembly(m, rootThing);
	}
}
