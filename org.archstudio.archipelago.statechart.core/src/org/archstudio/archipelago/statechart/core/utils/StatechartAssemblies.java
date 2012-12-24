package org.archstudio.archipelago.statechart.core.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.archipelago.statechart.core.things.glass.CurvedSplineGlassThing;
import org.archstudio.archipelago.statechart.core.things.shapes.CurvedSplineThing;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jdt.annotation.Nullable;

public class StatechartAssemblies extends Assemblies {

	public static CurvedSplineGlassThing createCurvedSpline(IBNAWorld world, @Nullable Object id,
			@Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		CurvedSplineThing bkg = model.addThing(new CurvedSplineThing(null),
				parent != null ? parent : getLayer(model, SPLINE_LAYER_THING_ID));
		CurvedSplineGlassThing glass = model.addThing(new CurvedSplineGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_1_KEY, bkg);
		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_2_KEY, bkg);

		return glass;
	}
}
