package org.archstudio.archipelago.statechart.core.logics;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.statechart.core.Activator;
import org.archstudio.archipelago.statechart.core.StatechartConstants;
import org.archstudio.archipelago.statechart.core.things.glass.CurvedSplineGlassThing;
import org.archstudio.archipelago.statechart.core.utils.StatechartAssemblies;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.labels.ArrowheadThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.Assemblies.ThingAssemblyKey;
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

public class MapTransitionLogic extends AbstractXADLToBNAPathLogic<CurvedSplineGlassThing> implements
		IPropertyChangeListener {

	public static final IThingRefKey<ArrowheadThing> ARROWHEAD_KEY = ThingAssemblyKey.create("assembly-arrowhead");

	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	DynamicStickPointLogic stickLogic = null;
	ArrowheadLogic arrowheadLogic = null;
	MirrorValueLogic mvl = null;

	public MapTransitionLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	public void init() {
		super.init();

		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		arrowheadLogic = addThingLogic(ArrowheadLogic.class);
		mvl = addThingLogic(MirrorValueLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		syncValue("from", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		syncValue("to", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);

		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	public void destroy() {
		Activator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.destroy();
	}

	public void propertyChange(PropertyChangeEvent event) {
		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				StatechartConstants.PREF_TRANSITION_FONT);

		for (CurvedSplineGlassThing thing : getAddedThings()) {
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
			Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
					FontStyle.fromSWT(defaultFont.getStyle()));
		}
	}

	protected CurvedSplineGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {
		FontData defaultFont = PreferenceConverter.getFontData(Activator.getDefault().getPreferenceStore(),
				StatechartConstants.PREF_TRANSITION_FONT);

		CurvedSplineGlassThing thing = StatechartAssemblies.createCurvedSpline(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setPoint(0, new Point(newPointSpot.x - 50, newPointSpot.y + 50));
		thing.setPoint(-1, new Point(newPointSpot.x + 50, newPointSpot.y - 50));

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1,
				IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);

		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.EDGE_FROM_CENTER);
		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), StickyMode.EDGE_FROM_CENTER);

		ArrowheadThing arrowheadThing = getBNAWorld().getBNAModel().addThing(new ArrowheadThing(null), thing);
		arrowheadLogic.point(arrowheadThing, thing, IHasEndpoints.ENDPOINT_1_KEY);
		Assemblies.markPart(thing, ARROWHEAD_KEY, arrowheadThing);

		AnchoredLabelThing labelThing = getBNAWorld().getBNAModel().addThing(new AnchoredLabelThing(null),
				arrowheadThing);
		mvl.mirrorValue(thing, IHasAnchorPoint.ANCHOR_POINT_KEY, labelThing);
		Assemblies.markPart(thing, Assemblies.TEXT_KEY, labelThing);

		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_NAME_KEY, defaultFont.getName());
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_SIZE_KEY, defaultFont.getHeight());
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY,
				FontStyle.fromSWT(defaultFont.getStyle()));

		UserEditableUtils.addEditableQualities(labelThing, IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
