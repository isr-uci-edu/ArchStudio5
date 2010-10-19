package org.archstudio.bna.assemblies;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.borders.BoxBorderThing;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.labels.BoxedLabelThing;
import org.archstudio.bna.things.layouts.LayoutRootThing;
import org.archstudio.bna.things.shapes.BoxThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;
import org.archstudio.bna.things.utility.WorldThing;

public class ClassBoxAssembly extends AbstractAssembly {

	public static final String ASSEMBLY_KIND = ClassBoxAssembly.class.getName();

	protected BoxThing boxThing = null;
	protected BoxBorderThing boxBorderThing = null;
	protected BoxedLabelThing boxedLabelThing = null;
	protected BoxGlassThing boxGlassThing = null;
	protected WorldThing viewThing = null;
	protected LayoutRootThing layoutThing = null;

	protected ClassBoxAssembly(IBNAModel m, IHasAssemblyData rootThing) {
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
			else if (t instanceof LayoutRootThing) {
				layoutThing = (LayoutRootThing) t;
			}
			else if (t instanceof WorldThing) {
				viewThing = (WorldThing) t;
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

	public WorldThing getViewThing() {
		reindexIfNeeded();
		return viewThing;
	}

	public LayoutRootThing getLayoutThing() {
		reindexIfNeeded();
		return layoutThing;
	}

	public static ClassBoxAssembly create(IBNAModel m, IThing parentThing) {
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

		LayoutRootThing layoutThing = new LayoutRootThing();
		m.addThing(layoutThing, boxGlassThing);

		//View thing goes even on top of the glass thing
		WorldThing viewThing = new WorldThing();
		m.addThing(viewThing, rootThing);

		//Set up connections
		// TODO Add connection between layoutThing and boxThing???
		boxThing.setBoundingBoxMasterThingID(boxGlassThing.getID());

		boxBorderThing.setBoundingBoxMasterThingID(boxThing.getID());

		boxedLabelThing.setBoundingBoxMasterThingID(boxThing.getID());
		boxedLabelThing.setBoundingBoxMirrorOffsets(new Rectangle(5, 5, -10, -10));

		viewThing.setBoundingBoxMasterThingID(boxGlassThing.getID());
		viewThing.setBoundingBoxMirrorOffsets(new Rectangle(5, 5, -10, -10));

		// Bulk Change model
		m.endBulkChange();

		return new ClassBoxAssembly(m, rootThing);
	}

}
