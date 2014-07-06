package org.archstudio.archipelago.statechart.core.logics;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.statechart.core.Activator;
import org.archstudio.archipelago.statechart.core.StatechartConstants;
import org.archstudio.archipelago.statechart.core.utils.StatechartAssemblies;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.shapes.CurvedSplineThing;
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
import org.eclipse.swt.graphics.RGB;

public class MapTransitionLogic extends AbstractXADLToBNAPathLogic<CurvedSplineThing> implements
		IPropertyChangeListener {

	protected final SynchronizeThingIDAndObjRefLogic syncLogic;
	protected final DynamicStickPointLogic stickLogic;

	FontData defaultFont;
	int defaultLineWidth;

	public MapTransitionLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath, String description) {
		super(world, xarch, rootObjRef, objRefPath);
		syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.BOUNDED_TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		syncValue("from", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		syncValue("to", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);

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
		defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				StatechartConstants.PREF_TRANSITION_FONT);
		defaultLineWidth = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore()
				.getInt(ArchipelagoConstants.PREF_LINE_WIDTH);
	}

	@Override
	synchronized public void propertyChange(PropertyChangeEvent event) {
		loadPreferences();

		for (CurvedSplineThing thing : getAddedThings()) {
			AnchoredLabelThing label = Assemblies.ANCHORED_TEXT_KEY.get(thing, model);
			label.set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
			label.set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
			label.set(IHasFontData.FONT_STYLE_KEY, FontStyle.fromSWT(defaultFont.getStyle()));
			thing.setLineWidth(defaultLineWidth);
		}
	}

	@Override
	protected CurvedSplineThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		CurvedSplineThing transition = StatechartAssemblies.createTransition(world, null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(world);
		transition.setEndpoint1(new Point2D.Double(newPointSpot.x - 50, newPointSpot.y + 50));
		transition.setEndpoint2(new Point2D.Double(newPointSpot.x + 50, newPointSpot.y - 50));
		transition.setArrowhead2Color(new RGB(0, 0, 0));
		transition.setArrowhead2EdgeColor(new RGB(0, 0, 0));

		UserEditableUtils.addEditableQualities(transition, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_1, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2,
				IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_2, IHasMutableAlpha.USER_MAY_CHANGE_ALPHA);

		transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.EDGE_FROM_CENTER);
		transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), StickyMode.EDGE_FROM_CENTER);

		AnchoredLabelThing label = Assemblies.ANCHORED_TEXT_KEY.get(transition, model);
		label.set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
		label.set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
		label.set(IHasFontData.FONT_STYLE_KEY, FontStyle.fromSWT(defaultFont.getStyle()));
		transition.setLineWidth(defaultLineWidth);

		UserEditableUtils.addEditableQualities(transition, HighlightLogic.USER_MAY_HIGHLIGHT);
		UserEditableUtils.addEditableQualities(label, IHasMutableText.USER_MAY_EDIT_TEXT);

		return transition;
	}
}
