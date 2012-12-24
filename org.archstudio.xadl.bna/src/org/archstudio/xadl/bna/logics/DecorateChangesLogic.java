package org.archstudio.xadl.bna.logics;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IHasTint;
import org.archstudio.bna.facets.IIsBackground;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.IXArchADTVariability.ChangeStatus;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent.Type;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

@NonNullByDefault
public class DecorateChangesLogic extends AbstractThingLogic implements IXArchADTVariabilityListener, IBNAModelListener {

	private static final IThingKey<ChangeStatus> CHANGE_STATUS_KEY = ThingKey.create("ChangeStatus");
	private static final IThingRefKey<IThing> CHANGE_DECORATION_KEY = ThingRefKey.create("ChangeStatusDecoration");

	protected final IXArchADTVariability xarch;
	protected ThingValueTrackingLogic tvtl;

	public DecorateChangesLogic(IXArchADTVariability xarch) {
		this.xarch = xarch;
	}

	protected void init() {
		super.init();
		tvtl = addThingLogic(ThingValueTrackingLogic.class);
	}

	public void handleXArchADTVariabilityEvent(final XArchADTVariabilityEvent evt) {
		if (evt.getType() == Type.STATUS) {
			final IBNAModel model = getBNAModel();
			if (model != null) {
				SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
					public void run() {
						for (IThing t : model.getThingsByID(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY,
								evt.getChangedObjRef()))) {
							updateDecoration(t, evt.getChangeStatus());
						}
					}
				});
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			if (t != null) {
				ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					updateDecoration(t, xarch.getChangeStatus(objRef));
				}
			}
		}
		case THING_REMOVED: {
			IThing t = evt.getTargetThing();
			if (t != null) {
				removeDecoration(t);
			}
		}
		case THING_CHANGED: {
			ThingEvent te = evt.getThingEvent();
			if (te != null) {
				if (IHasObjRef.OBJREF_KEY.equals(te.getPropertyName())) {
					ObjRef objRef = (ObjRef) te.getNewPropertyValue();
					if (objRef != null) {
						updateDecoration(te.getTargetThing(), xarch.getChangeStatus(objRef));
					}
					else {
						removeDecoration(te.getTargetThing());
					}
				}
			}
		}
		default: // do nothing
		}
	}

	protected final static int TINT = 128;

	protected void updateDecoration(IThing t, ChangeStatus changeStatus) {
		switch (changeStatus) {
		case ATTACHED:
			removeDecoration(t);
			break;
		case EXPLICITLY_ADDED:
			updateDecoration(t, new RGB(0, 255, 255), new RGB(0, TINT, TINT), 1f, true);
			break;
		case EXPLICITLY_ADDED_BUT_REALLY_REMOVED:
			updateDecoration(t, new RGB(0, 255, 255), new RGB(0, TINT, TINT), 0.5f, false);
			break;
		case EXPLICITLY_MODIFIED:
			updateDecoration(t, new RGB(255, 0, 255), new RGB(TINT, 0, TINT), 1f, true);
			break;
		case EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED:
			updateDecoration(t, new RGB(255, 0, 255), new RGB(TINT, 0, TINT), 0.5f, false);
			break;
		case EXPLICITLY_REMOVED:
			updateDecoration(t, new RGB(255, 0, 0), new RGB(TINT, 0, 0), 0.5f, false);
			break;
		case EXPLICITLY_REMOVED_BUT_REALLY_ADDED:
			updateDecoration(t, new RGB(255, 0, 0), new RGB(TINT, 0, 0), 1f, true);
			break;
		case OVERVIEW:
			updateDecoration(t, null, new RGB(0, 0, 0), 0.5f, false);
			break;
		default:
			// do nothing
		}
	}

	private void updateDecoration(IThing t, RGB rgb, RGB tint, float alpha, boolean editable) {
		updateAttributes(t, tint, alpha, editable);
		//		IBNAModel model = getBNAModel();
		//		if (model != null) {
		//			IThing decoration = null;
		//			if (t instanceof IHasBoundingBox) {
		//				decoration = model.addThing(new RectangleThing(null), t);
		//				((RectangleThing) decoration).setColor(null);
		//				((RectangleThing) decoration).setEdgeColor(rgb);
		//			}
		//		}
	}

	protected void removeDecoration(IThing t) {
		if (t.get(CHANGE_STATUS_KEY) != null) {
			IBNAModel model = getBNAModel();
			if (model != null) {
				IThing d = CHANGE_DECORATION_KEY.get(t, model);
				if (d != null) {
					model.removeThingAndChildren(d);
				}
				t.remove(CHANGE_STATUS_KEY);
				t.remove(CHANGE_DECORATION_KEY);
			}
		}
		updateAttributes(t, new RGB(0, 0, 0), 1f, true);
	}

	private void updateAttributes(IThing t, RGB tint, float alpha, boolean editable) {
		t.set(IHasTint.TINT_KEY, tint);
		t.set(IHasAlpha.ALPHA_KEY, alpha);
		t.set(IIsBackground.BACKGROUND_KEY, !editable);
		if (!editable && Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			t.set(IHasSelected.SELECTED_KEY, false);
		}
		for (IThing p : Assemblies.getParts(getBNAModel(), t)) {
			if (!p.has(IHasObjRef.OBJREF_KEY) || p.has(IHasObjRef.OBJREF_KEY, t.get(IHasObjRef.OBJREF_KEY))) {
				updateAttributes(p, tint, alpha, editable);
			}
		}
	}
}
