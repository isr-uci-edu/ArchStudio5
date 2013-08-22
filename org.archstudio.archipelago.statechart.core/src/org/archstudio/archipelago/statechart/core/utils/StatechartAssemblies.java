package org.archstudio.archipelago.statechart.core.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.archipelago.statechart.core.logics.OrientTextLogic;
import org.archstudio.archipelago.statechart.core.things.shapes.CurvedSplineThing;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jdt.annotation.Nullable;

public class StatechartAssemblies {

	public static CurvedSplineThing createTransition(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		CurvedSplineThing bkg = model.addThing(new CurvedSplineThing(id),
				parent != null ? parent : Assemblies.getLayer(model, Assemblies.SPLINE_LAYER_THING_ID));
		Assemblies.addArrowhead(world, bkg, IHasEndpoints.ENDPOINT_1_KEY, null, null);
		AnchoredLabelThing labelThing = model.addThing(new AnchoredLabelThing(null), bkg);

		Assemblies.markPart(bkg, Assemblies.TEXT_KEY, labelThing);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		OrientTextLogic otl = tlm.addThingLogic(OrientTextLogic.class);

		mvl.mirrorValue(bkg, IHasAnchorPoint.ANCHOR_POINT_KEY, labelThing);
		otl.orientText(bkg, labelThing);

		return bkg;
	}
}
