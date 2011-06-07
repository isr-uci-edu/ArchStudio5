package edu.uci.isr.archstudio4.comp.archipelago.archstructure.logics.mapping;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.xadlbna.generic.logics.mapping.BNAPath;
import org.archstudio.xadlbna.generic.logics.mapping.MapXadlNondirectionalSplineLogic;
import org.archstudio.xarchadt.ObjRef;


public class MapXadlLinkLogic extends MapXadlNondirectionalSplineLogic {

	public MapXadlLinkLogic(XArchFlatInterface xarch, ObjRef rootObjRef, String relativePath, IThing parentThing,
			Object kind) {
		super(xarch, rootObjRef, relativePath, tptl, parentThing, kind);
		automapLinkPointsToSplineEndpoints("glass", true);
		addTagsOnlyPrefixMapping("optional", new ISingleAssemblyMapping<SplineAssembly>() {
			public void updateAssembly(IBNAModel model, ObjRef objRef, SplineAssembly assembly, XArchFlatEvent evt,
					XArchPath relativeSourceTargetPath) {
				assembly.getSplineThing().setLineStyle(
						OptionsUtils.isOptional(MapXadlLinkLogic.this.xarch, objRef) ? SWT.LINE_DASH : SWT.LINE_SOLID);
			}

			public void storeAssemblyData(IBNAModel model, ObjRef objRef, SplineAssembly assembly, BNAModelEvent evt,
					BNAPath relativeBNAPath) {
			}
		});
	}
}
