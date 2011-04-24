package org.archstudio.bna.assemblies;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;
import org.archstudio.swtutils.constants.Orientation;

public class BoxReshapeHandlesAssembly extends AbstractAssembly {
	public static final String ASSEMBLY_KIND = BoxReshapeHandlesAssembly.class.getName();

	protected Map<Orientation, ReshapeHandleAssembly> handleMap;

	protected BoxReshapeHandlesAssembly(IBNAModel m, IHasAssemblyData rootThing) {
		super(m, rootThing);
	}

	public IHasAssemblyData getRootThing() {
		return rootThing;
	}

	protected synchronized void reindex() {
		if (handleMap == null) {
			handleMap = new HashMap<Orientation, ReshapeHandleAssembly>();
		}
		else {
			handleMap.clear();
		}
		for (IThing t : model.getChildThings(rootThing)) {
			if (t instanceof IHasAssemblyData) {
				if (BNAUtils.isAssemblyRoot(t, ReshapeHandleAssembly.ASSEMBLY_KIND)) {
					ReshapeHandleAssembly rh = new ReshapeHandleAssembly(model, (IHasAssemblyData) t);
					ReshapeHandleGlassThing gt = rh.getReshapeHandleGlassThing();
					if (gt != null) {
						Orientation o = gt.getOrientation();
						if (o != null) {
							handleMap.put(o, rh);
						}
					}
				}
			}
		}
	}

	public ReshapeHandleAssembly getReshapeHandleAssembly(Orientation o) {
		reindexIfNeeded();
		return handleMap.get(o);
	}

	public ReshapeHandleAssembly[] getAllReshapeHandleAssemblies() {
		reindexIfNeeded();
		return handleMap.values().toArray(new ReshapeHandleAssembly[handleMap.size()]);
	}

	public static BoxReshapeHandlesAssembly create(IBNAModel m, IThing parentThing) {
		m.beginBulkChange();

		AssemblyRootThing rootThing = new AssemblyRootThing();
		m.addThing(rootThing, parentThing);

		rootThing.setAssemblyKind(ASSEMBLY_KIND);

		//Create sub-things
		for (Orientation o : EnumSet.allOf(Orientation.class)) {
			if (!o.equals(Orientation.NONE)) {
				ReshapeHandleAssembly rh = ReshapeHandleAssembly.create(m, rootThing);
				rh.getReshapeHandleGlassThing().setOrientation(o);
				rh.getReshapeHandleThing().setOrientation(o);
			}
		}
		m.endBulkChange();
		return new BoxReshapeHandlesAssembly(m, rootThing);
	}

	public static BoxReshapeHandlesAssembly attach(IBNAModel m, IHasAssemblyData rootThing) {
		return new BoxReshapeHandlesAssembly(m, rootThing);
	}

}
