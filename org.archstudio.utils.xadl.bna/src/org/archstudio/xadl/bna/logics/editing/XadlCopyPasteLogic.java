package org.archstudio.xadl.bna.logics.editing;

import java.util.List;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.GridUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTCopyPaste;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class XadlCopyPasteLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener {

	protected final IXArchADT xarch;
	protected final IActionBars actionBars;
	protected final XArchADTCopyPaste copyPaste = new XArchADTCopyPaste();
	protected final Map<ObjRef, Integer> selectAndMoveObjRefs = Maps.newHashMap();
	protected int pasteOffset = 0;

	public XadlCopyPasteLogic(IBNAWorld world, IXArchADT xarch, final IActionBars actionBars) {
		super(world);
		this.xarch = xarch;
		this.actionBars = actionBars;

		SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
			@Override
			public void run() {
				actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), new BNAAction() {

					@Override
					public void runWithEventAndLock(@Nullable Event event) {
						copy();
						delete();
					}
				});
				actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), new BNAAction() {
					@Override
					public void runWithEventAndLock(@Nullable Event event) {
						copy();
					}
				});
				actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), new BNAAction() {
					@Override
					public void runWithEventAndLock(@Nullable Event event) {
						paste();
					}
				});
				actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), new BNAAction() {
					@Override
					public void runWithEventAndLock(@Nullable Event event) {
						delete();
					}
				});
				actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), new BNAAction() {
					@Override
					public void runWithEventAndLock(@Nullable Event event) {
						selectAll();
					}
				});
				actionBars.updateActionBars();
			}
		});
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
			@Override
			public void run() {
				actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), null);
				actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), null);
				actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), null);
				actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), null);
				actionBars.updateActionBars();
			}
		});
		super.dispose();
	}

	List<ObjRef> getSelectedObjRefs() {
		List<ObjRef> objRefs = Lists.newArrayList();
		for (IThing t : model.getAllThings()) {
			if (t.has(IHasSelected.SELECTED_KEY, true)) {
				ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					objRefs.add(objRef);
				}
			}
		}
		return objRefs;
	}

	public void selectAll() {
		BNAUtils.checkLock();

		for (IThing t : model.getAllThings()) {
			if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableSelected.USER_MAY_SELECT)) {
				t.set(IHasSelected.SELECTED_KEY, true);
			}
		}
	}

	public void copy() {
		BNAUtils.checkLock();

		pasteOffset = 0;

		copyPaste.copy(xarch, getSelectedObjRefs());
	}

	public void paste() {
		BNAUtils.checkLock();

		EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
		ObjRef rootRef = ept.get(IHasObjRef.OBJREF_KEY);
		if (rootRef != null) {
			XArchADTOperations xarch = new XArchADTOperations(this.xarch);

			// unselect everything
			for (IThing t : model.getAllThings()) {
				t.set(IHasSelected.SELECTED_KEY, false);
			}
			selectAndMoveObjRefs.clear();

			// keep track of the new ObjRefs to select and move them
			pasteOffset += GridUtils.getGridSpacing(world);
			for (ObjRef objRef : copyPaste.paste(xarch, rootRef)) {
				selectAndMoveObjRefs.put(objRef, pasteOffset);
			}

			xarch.done("Paste");
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		// select the new ObjRefs added by paste()
		switch (evt.getEventType()) {
		case THING_CHANGED:
			IThing ct = evt.getTargetThing();
			if (ct != null) {
				if (selectAndMoveObjRefs.containsKey(ct.get(IHasObjRef.OBJREF_KEY))) {
					int move = selectAndMoveObjRefs.remove(ct.get(IHasObjRef.OBJREF_KEY));
					if (UserEditableUtils.isEditableForAllQualities(ct, IHasMutableSelected.USER_MAY_SELECT)) {
						ct.set(IHasSelected.SELECTED_KEY, true);
					}
					if (ct instanceof IHasMutableReferencePoint) {
						Point p = ((IHasMutableReferencePoint) ct).getReferencePoint();
						p.x += move;
						p.y += move;
						((IHasMutableReferencePoint) ct).setReferencePoint(p);
					}
				}
			}
			break;
		default:
			// do nothing
		}
	}

	public void delete() {
		BNAUtils.checkLock();

		XArchADTOperations xarch = new XArchADTOperations(this.xarch);

		for (ObjRef objRef : getSelectedObjRefs()) {
			ObjRef parentRef = xarch.getParent(objRef);
			IXArchADTTypeMetadata parentType = xarch.getTypeMetadata(parentRef);
			String featureName = xarch.getContainingFeatureName(objRef);
			IXArchADTFeature feature = parentType.getFeatures().get(featureName);
			switch (feature.getType()) {
			case ATTRIBUTE:
				throw new IllegalArgumentException();
			case ELEMENT_SINGLE:
				xarch.clear(parentRef, featureName);
				break;
			case ELEMENT_MULTIPLE:
				xarch.remove(parentRef, featureName, objRef);
				break;
			}
		}

		xarch.done("Delete");
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		BNAUtils.checkLock();

		menu.add(new BNAAction("Cut") {

			@Override
			public void runWithLock() {
				copy();
				delete();
			}
		});
		menu.add(new BNAAction("Copy") {

			@Override
			public void runWithLock() {
				copy();
			}
		});
		menu.add(new BNAAction("Paste") {

			@Override
			public void runWithLock() {
				paste();
			}
		});

	}

}
