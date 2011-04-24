package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.facets.IMovesWith;
import org.archstudio.bna.things.borders.BoxBorderThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.things.shapes.BoxThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class EndpointAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = EndpointAssembly.class.getName();

	protected BoxThing boxThing = null;
	protected BoxBorderThing boxBorderThing = null;
	protected DirectionalLabelThing directionalLabelThing = null;
	protected TagThing tagThing = null;
	protected EndpointGlassThing endpointGlassThing = null;

	protected EndpointAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof BoxThing) {
				boxThing = (BoxThing) t;
			}
			else if (t instanceof BoxBorderThing) {
				boxBorderThing = (BoxBorderThing) t;
			}
			else if (t instanceof DirectionalLabelThing) {
				directionalLabelThing = (DirectionalLabelThing) t;
			}
			else if (t instanceof TagThing) {
				tagThing = (TagThing) t;
			}
			else if (t instanceof EndpointGlassThing) {
				endpointGlassThing = (EndpointGlassThing) t;
			}
		}
	}

	public BoxBorderThing getBoxBorderThing() {
		reindexIfNeeded();
		return boxBorderThing;
	}

	public BoxThing getBoxThing() {
		reindexIfNeeded();
		return boxThing;
	}

	public TagThing getTagThing() {
		reindexIfNeeded();
		return tagThing;
	}

	public DirectionalLabelThing getDirectionalLabelThing() {
		reindexIfNeeded();
		return directionalLabelThing;
	}

	public EndpointGlassThing getEndpointGlassThing() {
		reindexIfNeeded();
		return endpointGlassThing;
	}

	public static EndpointAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		BoxThing boxThing = new BoxThing();
		m.addThing(boxThing, rootThing);

		BoxBorderThing boxBorderThing = new BoxBorderThing();
		m.addThing(boxBorderThing, rootThing);

		DirectionalLabelThing directionalLabelThing = new DirectionalLabelThing();
		m.addThing(directionalLabelThing, rootThing);

		TagThing tagThing = new TagThing();
		m.addThing(tagThing, rootThing);

		EndpointGlassThing endpointGlassThing = new EndpointGlassThing();
		m.addThing(endpointGlassThing, rootThing);

		//Set up connections
		boxThing.setBoundingBoxMasterThingID(endpointGlassThing.getID());
		boxBorderThing.setBoundingBoxMasterThingID(endpointGlassThing.getID());
		directionalLabelThing.setBoundingBoxMasterThingID(endpointGlassThing.getID());
		tagThing.setTargetThingID(endpointGlassThing.getID());
		tagThing.setMovesWithMode(IMovesWith.TRACK_ANCHOR_POINT_FIRST);
		tagThing.setMovesWithThingID(endpointGlassThing.getID());
		//directionalLabelThing.setBoundingBoxMirrorOffsets(new Rectangle(1, 1, -2, -2));
		m.endBulkChange();

		return new EndpointAssembly(m, rootThing);
	}

	public static EndpointAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new EndpointAssembly(m, rootThing);
	}

}
