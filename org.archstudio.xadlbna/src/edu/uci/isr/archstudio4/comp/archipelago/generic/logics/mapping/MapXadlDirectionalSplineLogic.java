package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.mapping;

import org.archstudio.archipelago.core.structure.mapping.MapXadlSplineLogic;

public abstract class MapXadlDirectionalSplineLogic extends MapXadlSplineLogic {

	public MapXadlDirectionalSplineLogic(XArchFlatInterface xarch, ObjRef rootObjRef, String relativePath,
			String fromLinkName, String toLinkName, ThingPropertyTrackingLogic tptl, IThing parentThing, Object kind) {
		super(xarch, rootObjRef, relativePath, tptl, parentThing, kind);
		automapXLinkToStuckPoint(fromLinkName, "glass", IHasEndpoints.ENDPOINT_1_PROPERTY_NAME, true);
		automapXLinkToStuckPoint(toLinkName, "glass", IHasEndpoints.ENDPOINT_2_PROPERTY_NAME, true);
	}

	@Override
	protected SplineAssembly addAssembly(IBNAModel model, ObjRef objRef, ObjRef[] relativeAncestorRefs) {

		SplineAssembly assembly = super.addAssembly(model, objRef, relativeAncestorRefs);
		assembly.getEndpoint2ArrowheadThing().setArrowheadShape(ArrowheadShape.WEDGE);

		return assembly;
	}
}
