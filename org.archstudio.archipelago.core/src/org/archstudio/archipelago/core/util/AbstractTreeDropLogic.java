package org.archstudio.archipelago.core.util;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.borders.PulsingBorderThing;
import org.archstudio.bna.utils.IBNADragAndDropListener;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

public abstract class AbstractTreeDropLogic extends AbstractThingLogic implements IBNADragAndDropListener {
	protected Services services = null;
	protected ObjRef documentRootRef = null;
	protected PulsingBorderThing pulser = null;

	public AbstractTreeDropLogic(IBNAWorld world, Services services, ObjRef documentRootRef) {
		super(world);
		this.services = services;
		this.documentRootRef = documentRootRef;
	}

	protected static Object getDataFromSelection(Object selection) {
		if (selection != null) {
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				Object[] allSelected = ss.toArray();
				if (allSelected.length == 0) {
					return null;
				}
				else if (allSelected.length == 1) {
					return allSelected[0];
				}
				else {
					return allSelected;
				}
			}
		}
		return null;
	}

	protected boolean acceptDrop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		//Two possibilities: either the platform we are on can give us the data
		//or it can't.  If it can, we can make a more educated decision here.

		Object data = null;
		LocalSelectionTransfer transfer = LocalSelectionTransfer.getInstance();
		if (transfer.isSupportedType(event.currentDataType)) {
			Object o = transfer.nativeToJava(event.currentDataType);
			data = getDataFromSelection(o);
		}
		return acceptDrop(view, event, ts, location, data);
	}

	protected abstract boolean acceptDrop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location,
			Object data);

	@Override
	synchronized public void dragEnter(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (acceptDrop(view, event, ts, location)) {
			event.detail = DND.DROP_LINK;
		}
	}

	@Override
	synchronized public void dragLeave(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (pulser != null) {
			view.getBNAWorld().getBNAModel().removeThing(pulser);
			pulser = null;
		}
	}

	@Override
	synchronized public void dragOver(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (acceptDrop(view, event, ts, location)) {
			IThing t = firstOrNull(ts);
			event.detail = DND.DROP_LINK;
			if (pulser == null && t != null && t instanceof IHasBoundingBox) {
				pulser = new PulsingBorderThing(null);
				pulser.setBoundingBox(((IHasBoundingBox) t).getBoundingBox());
				view.getBNAWorld().getBNAModel().addThing(pulser, t);
			}
			else if (pulser != null && t != null && t instanceof IHasBoundingBox) {
				pulser.setBoundingBox(((IHasBoundingBox) t).getBoundingBox());
			}
			else if (pulser != null) {
				view.getBNAWorld().getBNAModel().removeThing(pulser);
				pulser = null;
			}
		}
		else {
			if (pulser != null) {
				view.getBNAWorld().getBNAModel().removeThing(pulser);
				pulser = null;
			}
		}
	}

	@Override
	synchronized public void dropAccept(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (acceptDrop(view, event, ts, location)) {
			event.detail = DND.DROP_LINK;
			LocalSelectionTransfer transfer = LocalSelectionTransfer.getInstance();
			for (TransferData dataType : event.dataTypes) {
				if (transfer.isSupportedType(dataType)) {
					event.currentDataType = dataType;
					break;
				}
			}
		}
	}

	@Override
	synchronized public void dragOperationChanged(IBNAView view, DropTargetEvent event, List<IThing> ts,
			ICoordinate location) {
	}

	@Override
	synchronized public void drop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
	}

}
