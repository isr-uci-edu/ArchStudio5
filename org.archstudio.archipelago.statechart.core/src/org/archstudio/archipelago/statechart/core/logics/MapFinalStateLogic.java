package org.archstudio.archipelago.statechart.core.logics;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.statechart.core.things.shapes.FinalStateThing;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasToolTip;
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
	protected final MirrorValueLogic mirrorLogic;
	protected final MirrorBoundingBoxLogic boundsLogic;
	protected final Services services;
	protected final Dimension defaultSize;

	public static final IThingRefKey<IHasEdgeColor> CENTER_KEY = ThingRefKey.create("assembly-center");

	public MapFinalStateLogic(IBNAWorld world, Services services, IXArchADT xarch, ObjRef rootObjRef,
			String objRefPath, Dimension defaultSize, String description) {
		super(world, xarch, rootObjRef, objRefPath);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
		boundsLogic = logics.addThingLogic(MirrorBoundingBoxLogic.class);
		this.services = services;
		this.defaultSize = defaultSize;

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);

		setProgressInfo(description);
	}

	@Override
	protected FinalStateThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(world);

		FinalStateThing thing = model.addThing(new FinalStateThing(null),
				Assemblies.getLayer(model, Assemblies.Layer.MIDDLE));
		thing.setBoundingBox(new Rectangle(newPointSpot.x - defaultSize.width / 2, newPointSpot.y - defaultSize.height
				/ 2, defaultSize.width, defaultSize.height));

		Assemblies.markRoot(thing);

		mirrorLogic.mirrorValue(thing, IHasColor.COLOR_KEY, thing, IHasSecondaryColor.SECONDARY_COLOR_KEY,
				new Function<RGB, RGB>() {

					@Override
					@Nullable
					public RGB apply(@Nullable RGB input) {
						return BNAUtils.adjustBrightness(input, 1.5f);
					}
				});

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableAlpha.USER_MAY_CHANGE_ALPHA);

		return thing;
	}

}
