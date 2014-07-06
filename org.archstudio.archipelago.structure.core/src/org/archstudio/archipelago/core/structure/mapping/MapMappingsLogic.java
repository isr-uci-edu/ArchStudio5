package org.archstudio.archipelago.core.structure.mapping;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasInternalWorldPoint;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.logics.events.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.things.shapes.MappingThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.MaintainMappingLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

public class MapMappingsLogic extends AbstractXADLToBNAPathLogic<MappingThing> implements IPropertyChangeListener {

	protected final DynamicStickPointLogic stickLogic;
	protected final SynchronizeThingIDAndObjRefLogic syncLogic;
	protected final ReparentToThingIDLogic reparentLogic;

	protected int defaultLineWidth;

	public MapMappingsLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath, String description) {
		super(world, xarch, rootObjRef, objRefPath);
		stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);
		syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		reparentLogic = logics.addThingLogic(ReparentToThingIDLogic.class);
		logics.addThingLogic(MaintainMappingLogic.class);
		logics.addThingLogic(WorldThingInternalEventsLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), Assemblies.BOUNDED_TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		// take the inner world objRef and interface objRef, set them on the MappingSplineThing
		syncValue("innerInterfaceLink", null, null, BNAPath.create(), MaintainMappingLogic.INTERNAL_OBJREF_KEY, false);
		setAncestorObjRef(BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(IHasInternalWorldPoint.INTERNAL_WORLD_KEY), 1);

		// take the outer interface objRef, find the thing with that objRef, and stick the anchor point to it
		setValue(BNAPath.create(), stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY), StickyMode.CENTER);
		syncValue("outerInterfaceLink", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasAnchorPoint.ANCHOR_POINT_KEY)),
				true);

		loadPreferences();

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);

		setProgressInfo(description);
	}

	@Override
	synchronized public void dispose() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);

		super.dispose();
	}

	protected void loadPreferences() {
		defaultLineWidth = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH);
	}

	@Override
	synchronized public void propertyChange(PropertyChangeEvent event) {
		loadPreferences();

		for (MappingThing thing : getAddedThings()) {
			thing.setLineWidth(defaultLineWidth);
		}
	}

	@Override
	protected MappingThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		MappingThing thing = Assemblies.createMapping(world, null, null);
		Point newPointSpot = ArchipelagoUtils.getNewThingSpot(world);
		thing.setAnchorPoint(new Point2D.Double(newPointSpot.x - 50, newPointSpot.y + 50));
		thing.setInternalPoint(new Point2D.Double(newPointSpot.x + 50, newPointSpot.y - 50));
		thing.setLineWidth(defaultLineWidth);

		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableSelected.USER_MAY_SELECT, HighlightLogic.USER_MAY_HIGHLIGHT,
				IHasMutableAlpha.USER_MAY_CHANGE_ALPHA);

		//stack above the world thing
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(reparentLogic.getReparentToThingKey()),
				Lists.reverse(relLineageRefs).get(1));

		return thing;
	}
}
