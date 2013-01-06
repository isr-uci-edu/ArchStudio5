package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.ArchipelagoStructureConstants;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasLineWidth;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;

public class MapLinkLogic extends AbstractXADLToBNAPathLogic<SplineGlassThing> implements IPropertyChangeListener {

	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	DynamicStickPointLogic stickLogic = null;

	public MapLinkLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		stickLogic = addThingLogic(DynamicStickPointLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		syncValue("point1", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		syncValue("point2", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void destroy() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);

		super.destroy();
	}

	@Override
	protected SplineGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		int defaultLineWidth = Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoStructureConstants.PREF_LINE_WIDTH);

		SplineGlassThing thing = Assemblies.createSpline(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setPoint(0, new Point(newPointSpot.x - 50, newPointSpot.y + 50));
		thing.setPoint(-1, new Point(newPointSpot.x + 50, newPointSpot.y - 50));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, defaultLineWidth);
		
		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
				IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);

		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.EDGE_FROM_CENTER);
		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), StickyMode.EDGE_FROM_CENTER);

		return thing;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		int defaultLineWidth = Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoStructureConstants.PREF_LINE_WIDTH);

		for (SplineGlassThing thing : getAddedThings()) {
			Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, defaultLineWidth);
		}
	}
}
