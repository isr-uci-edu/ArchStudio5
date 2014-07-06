package org.archstudio.xadl.bna.logics;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasBackground;
import org.archstudio.bna.facets.IHasMutableGlow;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
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

	protected final ThingValueTrackingLogic valueLogic;
	protected final MirrorValueLogic mirrorLogic;
	protected final IXArchADTVariability xarch;

	public DecorateChangesLogic(IBNAWorld world, IXArchADTVariability xarch) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
		this.xarch = xarch;
	}

	@Override
	synchronized public void handleXArchADTVariabilityEvent(final XArchADTVariabilityEvent evt) {
		if (evt.getType() == Type.STATUS) {
			SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
				@Override
				public void run() {
					for (IThing t : valueLogic.getThings(IHasObjRef.OBJREF_KEY, evt.getChangedObjRef())) {
						updateDecoration(t, evt.getChangeStatus());
					}
				}
			});
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
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

	protected void updateDecoration(IThing t, ChangeStatus changeStatus) {
		switch (changeStatus) {
		case ATTACHED:
			removeDecoration(t);
			break;
		case EXPLICITLY_ADDED:
			updateDecoration(t, new RGB(0, 255, 255), 1f, true);
			break;
		case EXPLICITLY_ADDED_BUT_REALLY_REMOVED:
			updateDecoration(t, new RGB(0, 255, 255), 0.5f, false);
			break;
		case EXPLICITLY_MODIFIED:
			updateDecoration(t, new RGB(255, 0, 255), 1f, true);
			break;
		case EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED:
			updateDecoration(t, new RGB(255, 0, 255), 0.5f, false);
			break;
		case EXPLICITLY_REMOVED:
			updateDecoration(t, new RGB(255, 0, 0), 0.5f, false);
			break;
		case EXPLICITLY_REMOVED_BUT_REALLY_ADDED:
			updateDecoration(t, new RGB(255, 0, 0), 1f, true);
			break;
		case OVERVIEW:
			updateDecoration(t, null, 0.5f, false);
			break;
		default:
			// do nothing
		}
	}

	private void updateDecoration(IThing t, RGB rgb, float alpha, boolean editable) {
		removeDecoration(t);
		updateAttributes(t, alpha, editable);
		if (t instanceof IHasMutableGlow) {
			((IHasMutableGlow) t).setGlowColor(rgb);
			((IHasMutableGlow) t).setGlowAlpha(alpha);
		}
	}

	protected void removeDecoration(IThing t) {
		if (t instanceof IHasMutableGlow) {
			((IHasMutableGlow) t).setGlowColor(null);
		}
		t.remove(CHANGE_STATUS_KEY);
		updateAttributes(t, 1f, true);
	}

	private void updateAttributes(IThing t, float alpha, boolean editable) {
		t.set(IHasAlpha.ALPHA_KEY, alpha);
		t.set(IHasBackground.BACKGROUND_KEY, !editable);
		if (!editable && t.has(IHasSelected.SELECTED_KEY, true)) {
			t.set(IHasSelected.SELECTED_KEY, false);
		}
		for (IThing p : Assemblies.getParts(model, t).values()) {
			if (!p.has(IHasObjRef.OBJREF_KEY) || p.has(IHasObjRef.OBJREF_KEY, t.get(IHasObjRef.OBJREF_KEY))) {
				updateAttributes(p, alpha, editable);
			}
		}
	}
}
