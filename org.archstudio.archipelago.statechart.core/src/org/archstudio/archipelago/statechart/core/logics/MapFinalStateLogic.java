package org.archstudio.archipelago.statechart.core.logics;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasCount;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasLocalInsets;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.glass.EllipseGlassThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.Assemblies.ThingAssemblyKey;
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

public class MapFinalStateLogic extends AbstractXADLToBNAPathLogic<EllipseGlassThing> {
	protected final Services services;
	protected final Dimension defaultSize;
	protected final int defaultCount;
	protected MirrorValueLogic mvl = null;

	public static final IThingRefKey<IHasEdgeColor> EDGE_KEY = ThingAssemblyKey.create("assembly-edge");

	public MapFinalStateLogic(Services services, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
			Dimension defaultSize, int defaultCount) {
		super(xarch, rootObjRef, objRefPath);
		this.services = services;
		this.defaultSize = defaultSize;
		this.defaultCount = defaultCount;
	}

	@Override
	public void init() {
		super.init();
		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);

		mvl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorValueLogic.class);
	}

	@Override
	protected EllipseGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		EllipseThing edge = getBNAModel().addThing(new EllipseThing(null));
		edge.setLineWidth(2);
		edge.setColor(null);

		EllipseGlassThing thing = Assemblies.createEllipse(getBNAWorld(), null, edge);
		thing.setBoundingBox(new Rectangle(newPointSpot.x, newPointSpot.y, defaultSize.width, defaultSize.height));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, new RGB(32, 32, 32));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasCount.COUNT_KEY, defaultCount);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLocalInsets.LOCAL_INSETS_KEY,
				new Insets(4, 4, 4, 4));
		Assemblies.markPart(thing, EDGE_KEY, edge);

		mvl.mirrorValue(Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()), IHasColor.COLOR_KEY,
				Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()), IHasSecondaryColor.SECONDARY_COLOR_KEY,
				new Function<RGB, RGB>() {

					@Override
					@Nullable
					public RGB apply(@Nullable RGB input) {
						return BNAUtils.adjustBrightness(input, 1.5f);
					}
				});
		mvl.mirrorValue(thing, IHasBoundingBox.BOUNDING_BOX_KEY, edge);

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE);

		return thing;
	}

}
