package org.archstudio.bna.assemblies;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.borders.BoxBorderThing;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.labels.BoxedLabelThing;
import org.archstudio.bna.things.shapes.BoxThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;
import org.archstudio.bna.things.utility.NoThing;
import org.archstudio.bna.things.utility.WorldThing;

public class BoxAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = BoxAssembly.class.getName();

	protected BoxThing boxThing = null;
	protected BoxBorderThing boxBorderThing = null;
	protected BoxedLabelThing boxedLabelThing = null;
	protected BoxGlassThing boxGlassThing = null;
	protected NoThing mappingRootThing = null;
	protected NoThing endpointRootThing = null;
	protected WorldThing worldThing = null;

	protected BoxAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof BoxThing) {
				boxThing = (BoxThing) t;
				for (IThing bct : model.getChildThings(boxThing)) {
					if (bct instanceof BoxBorderThing) {
						boxBorderThing = (BoxBorderThing) bct;
					}
					else if (bct instanceof BoxedLabelThing) {
						boxedLabelThing = (BoxedLabelThing) bct;
					}
				}
			}
			else if (t instanceof BoxGlassThing) {
				boxGlassThing = (BoxGlassThing) t;
			}
			else if (t instanceof NoThing) {
				String rootThingKind = t.getProperty("rootThingKind");
				if (rootThingKind != null) {
					if (rootThingKind.equals("mapping")) {
						mappingRootThing = (NoThing) t;
					}
					else if (rootThingKind.equals("endpoint")) {
						endpointRootThing = (NoThing) t;
					}
				}
			}
			else if (t instanceof WorldThing) {
				worldThing = (WorldThing) t;
			}
		}
	}

	public BoxThing getBoxThing() {
		reindexIfNeeded();
		return boxThing;
	}

	public BoxBorderThing getBoxBorderThing() {
		reindexIfNeeded();
		return boxBorderThing;
	}

	public BoxedLabelThing getBoxedLabelThing() {
		reindexIfNeeded();
		return boxedLabelThing;
	}

	public BoxGlassThing getBoxGlassThing() {
		reindexIfNeeded();
		return boxGlassThing;
	}

	public NoThing getEndpointRootThing() {
		reindexIfNeeded();
		return endpointRootThing;
	}

	public NoThing getMappingRootThing() {
		reindexIfNeeded();
		return mappingRootThing;
	}

	public WorldThing getWorldThing() {
		reindexIfNeeded();
		return worldThing;
	}

	public static BoxAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		BoxThing boxThing = new BoxThing();
		m.addThing(boxThing, rootThing);

		BoxBorderThing boxBorderThing = new BoxBorderThing();
		m.addThing(boxBorderThing, boxThing);

		BoxedLabelThing boxedLabelThing = new BoxedLabelThing();
		m.addThing(boxedLabelThing, boxThing);

		BoxGlassThing boxGlassThing = new BoxGlassThing();
		m.addThing(boxGlassThing, rootThing);

		//World thing goes even on top of the glass thing
		WorldThing worldThing = new WorldThing();
		m.addThing(worldThing, rootThing);

		NoThing mappingRootThing = new NoThing();
		mappingRootThing.setProperty("rootThingKind", "mapping");
		m.addThing(mappingRootThing, rootThing);

		NoThing endpointRootThing = new NoThing();
		endpointRootThing.setProperty("rootThingKind", "endpoint");
		m.addThing(endpointRootThing, rootThing);

		//Set up connections
		boxThing.setBoundingBoxMasterThingID(boxGlassThing.getID());
		boxBorderThing.setBoundingBoxMasterThingID(boxThing.getID());
		boxedLabelThing.setBoundingBoxMasterThingID(boxThing.getID());
		boxedLabelThing.setBoundingBoxMirrorOffsets(new Rectangle(5, 5, -10, -10));
		worldThing.setBoundingBoxMasterThingID(boxGlassThing.getID());
		worldThing.setBoundingBoxMirrorOffsets(new Rectangle(15, 15, -30, -30));
		m.endBulkChange();

		return new BoxAssembly(m, rootThing);
	}

	public static BoxAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new BoxAssembly(m, rootThing);
	}
}
