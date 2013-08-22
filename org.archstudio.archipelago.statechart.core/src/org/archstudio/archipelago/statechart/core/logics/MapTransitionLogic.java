package org.archstudio.archipelago.statechart.core.logics;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.statechart.core.Activator;
import org.archstudio.archipelago.statechart.core.StatechartConstants;
import org.archstudio.archipelago.statechart.core.things.shapes.CurvedSplineThing;
import org.archstudio.archipelago.statechart.core.utils.StatechartAssemblies;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;

public class MapTransitionLogic extends AbstractXADLToBNAPathLogic<CurvedSplineThing> implements
		IPropertyChangeListener {

	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	DynamicStickPointLogic stickLogic = null;
	ArrowheadLogic arrowheadLogic = null;
	MirrorValueLogic mvl = null;
	OrientTextLogic otl = null;

	public MapTransitionLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		arrowheadLogic = addThingLogic(ArrowheadLogic.class);
		mvl = addThingLogic(MirrorValueLogic.class);
		otl = addThingLogic(OrientTextLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		syncValue("from", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		syncValue("to", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);

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
	public void propertyChange(PropertyChangeEvent event) {

		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				StatechartConstants.PREF_TRANSITION_FONT);
		int defaultLineWidth = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH);

		for (CurvedSplineThing thing : getAddedThings()) {
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
					FontStyle.fromSWT(defaultFont.getStyle()));
			thing.setLineWidth(defaultLineWidth);
		}
	}

	@Override
	protected CurvedSplineThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				StatechartConstants.PREF_TRANSITION_FONT);
		int defaultLineWidth = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH);

		CurvedSplineThing transition = StatechartAssemblies.createTransition(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		transition.setEndpoint1(new Point(newPointSpot.x - 50, newPointSpot.y + 50));
		transition.setEndpoint2(new Point(newPointSpot.x + 50, newPointSpot.y - 50));

		UserEditableUtils.addEditableQualities(transition, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);

		transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.EDGE_FROM_CENTER);
		transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), StickyMode.EDGE_FROM_CENTER);

		Assemblies.TEXT_KEY.get(transition, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
		Assemblies.TEXT_KEY.get(transition, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
		Assemblies.TEXT_KEY.get(transition, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
				FontStyle.fromSWT(defaultFont.getStyle()));
		transition.setLineWidth(defaultLineWidth);

		UserEditableUtils.addEditableQualities(transition, HighlightLogic.USER_MAY_HIGHLIGHT);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(transition, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);

		return transition;
	}
}
