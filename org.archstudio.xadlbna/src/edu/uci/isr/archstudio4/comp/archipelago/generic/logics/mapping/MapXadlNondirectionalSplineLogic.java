package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.mapping;

import org.archstudio.archipelago.core.structure.mapping.MapXadlSplineLogic;

public class MapXadlNondirectionalSplineLogic extends MapXadlSplineLogic {

	public MapXadlNondirectionalSplineLogic(XArchFlatInterface xarch, ObjRef rootObjRef, String relativePath,
			ThingPropertyTrackingLogic tptl, IThing parentThing, Object kind) {
		super(xarch, rootObjRef, relativePath, tptl, parentThing, kind);
		automapLinkPointsToSplineEndpoints("glass", true);
	}
}
