package org.archstudio.archipelago.statechart.core.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasArrowheads;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.OrientTextLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.shapes.CurvedSplineThing;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jdt.annotation.Nullable;

public class StatechartAssemblies {

	public static CurvedSplineThing createTransition(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		CurvedSplineThing bkg = model.addThing(new CurvedSplineThing(id),
				parent != null ? parent : Assemblies.getLayer(model, Assemblies.Layer.SPLINE));
		AnchoredLabelThing labelThing = model.addThing(new AnchoredLabelThing(null), bkg);

		Assemblies.markPart(bkg, Assemblies.ANCHORED_TEXT_KEY, labelThing);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		OrientTextLogic otl = tlm.addThingLogic(OrientTextLogic.class);

		mvl.mirrorValue(bkg, IHasEdgeColor.EDGE_COLOR_KEY, labelThing, IHasArrowheads.ARROWHEAD_2_EDGE_COLOR_KEY);
		mvl.mirrorValue(bkg, IHasAnchorPoint.ANCHOR_POINT_KEY, labelThing);
		otl.orientText(bkg, labelThing);

		return bkg;
	}
}
