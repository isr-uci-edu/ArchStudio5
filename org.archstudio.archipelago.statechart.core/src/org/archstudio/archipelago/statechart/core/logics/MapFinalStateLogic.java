package org.archstudio.archipelago.statechart.core.logics;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.statechart.core.things.shapes.FinalStateThing;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

public class MapFinalStateLogic extends AbstractXADLToBNAPathLogic<FinalStateThing> {
	protected final Services services;
	protected final Dimension defaultSize;
	protected MirrorValueLogic mvl = null;
	protected MirrorBoundingBoxLogic mbbl = null;

	public static final IThingRefKey<IHasEdgeColor> CENTER_KEY = ThingRefKey.create("assembly-center");

	public MapFinalStateLogic(Services services, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
			Dimension defaultSize) {
		super(xarch, rootObjRef, objRefPath);
		this.services = services;
		this.defaultSize = defaultSize;
	}

	@Override
	public void init() {
		super.init();
		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);

		mvl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorValueLogic.class);
		mbbl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorBoundingBoxLogic.class);
	}

	@Override
	protected FinalStateThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		FinalStateThing thing = getBNAModel().addThing(new FinalStateThing(null),
				Assemblies.getLayer(getBNAModel(), Assemblies.MIDDLE_LAYER_THING_ID));
		thing.setBoundingBox(new Rectangle(newPointSpot.x - defaultSize.width / 2, newPointSpot.y - defaultSize.height
				/ 2, defaultSize.width, defaultSize.height));

		Assemblies.markRoot(thing);

		mvl.mirrorValue(thing, IHasColor.COLOR_KEY, thing, IHasSecondaryColor.SECONDARY_COLOR_KEY,
				new Function<RGB, RGB>() {

					@Override
					@Nullable
					public RGB apply(@Nullable RGB input) {
						return BNAUtils.adjustBrightness(input, 1.5f);
					}
				});

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE);

		return thing;
	}

}
