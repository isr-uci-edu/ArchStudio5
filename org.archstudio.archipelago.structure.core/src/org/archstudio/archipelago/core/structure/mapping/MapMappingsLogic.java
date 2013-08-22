package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasInternalWorldEndpoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
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

	DynamicStickPointLogic stickLogic = null;
	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	ReparentToThingIDLogic reparentLogic = null;

	public MapMappingsLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		reparentLogic = addThingLogic(ReparentToThingIDLogic.class);
		// we use this logic to maintain mappings
		addThingLogic(MaintainMappingLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		// take the inner world objRef and interface objRef, set them on the MappingSplineThing
		syncValue("innerInterfaceLink", null, null, BNAPath.create(), MaintainMappingLogic.INTERNAL_OBJREF_KEY, false);
		setAncestorObjRef(BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY), 1);

		// take the outer interface objRef, find the thing with that objRef, and stick the anchor point to it
		setValue(BNAPath.create(), stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY), StickyMode.CENTER);
		syncValue("outerInterfaceLink", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasAnchorPoint.ANCHOR_POINT_KEY)),
				true);

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void destroy() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);

		super.destroy();
	}

	@Override
	protected MappingThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		int defaultLineWidth = Math.max(1, org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH));

		MappingThing thing = Assemblies.createMapping(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.getNewThingSpot(getBNAWorld().getBNAModel());
		thing.setAnchorPoint(new Point(newPointSpot.x - 50, newPointSpot.y + 50));
		thing.setInternalEndpoint(new Point(newPointSpot.x + 50, newPointSpot.y - 50));
		thing.setLineWidth(defaultLineWidth);

		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableSelected.USER_MAY_SELECT, HighlightLogic.USER_MAY_HIGHLIGHT);

		//stack above the world thing
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(reparentLogic.getReparentToThingIDKey()),
				Lists.reverse(relLineageRefs).get(1));

		return thing;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		int defaultLineWidth = Math.max(1, org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH));

		for (MappingThing thing : getAddedThings()) {
			thing.setLineWidth(defaultLineWidth);
		}
	}
}
