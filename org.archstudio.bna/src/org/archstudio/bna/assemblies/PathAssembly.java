package org.archstudio.bna.assemblies;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.facets.IMirrorsPathData;
import org.archstudio.bna.things.borders.PathBorderThing;
import org.archstudio.bna.things.glass.PathGlassThing;
import org.archstudio.bna.things.labels.BoxedLabelThing;
import org.archstudio.bna.things.shapes.PathThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;

public class PathAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = PathAssembly.class.getName();

	protected PathThing pathThing = null;

	protected PathBorderThing pathBorderThing = null;

	protected BoxedLabelThing boxedLabelThing = null;

	protected PathGlassThing pathGlassThing = null;

	protected PathAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected void reindex() {
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof PathThing) {
				pathThing = (PathThing) t;
				for (IThing t1 : model.getChildThings(pathThing)) {
					if (t instanceof PathBorderThing) {
						pathBorderThing = (PathBorderThing) t;
					}
					else if (t1 instanceof BoxedLabelThing) {
						boxedLabelThing = (BoxedLabelThing) t1;
					}
				}
			}
			else if (t instanceof PathGlassThing) {
				pathGlassThing = (PathGlassThing) t;
			}
		}
	}

	public PathThing getPathThing() {
		reindexIfNeeded();
		return pathThing;
	}

	public PathBorderThing getPathBorderThing() {
		reindexIfNeeded();
		return pathBorderThing;
	}

	public BoxedLabelThing getBoxedLabelThing() {
		reindexIfNeeded();
		return boxedLabelThing;
	}

	public PathGlassThing getPathGlassThing() {
		reindexIfNeeded();
		return pathGlassThing;
	}

	public static PathAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();
		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		PathThing pathThing = new PathThing(null);
		m.addThing(pathThing, rootThing);

		PathBorderThing pathBorderThing = new PathBorderThing(null);
		m.addThing(pathBorderThing, pathThing);

		BoxedLabelThing boxedLabelThing = new BoxedLabelThing();
		m.addThing(boxedLabelThing, pathThing);

		PathGlassThing pathGlassThing = new PathGlassThing(null);
		m.addThing(pathGlassThing, rootThing);

		//Set up connections
		pathThing.setPathDataMasterThingID(pathGlassThing.getID());
		pathBorderThing.setPathDataMasterThingID(pathThing.getID());
		// TODO determine boxedLabelThing's setBoundingBoxMasterThingID value
		m.endBulkChange();

		return new PathAssembly(m, rootThing);
	}

	public static PathAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new PathAssembly(m, rootThing);
	}

}
