package org.archstudio.archipelago.core.util;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.borders.PulsingBorderThing;
import org.archstudio.bna.ui.IBNADragAndDropListener;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.ObjRef;

public abstract class AbstractTreeDropLogic extends AbstractThingLogic implements IBNADragAndDropListener {

	protected final Services services;
	protected final ObjRef documentRootRef;

	private PulsingBorderThing pulser = null;

	public AbstractTreeDropLogic(IBNAWorld world, Services services, ObjRef documentRootRef) {
		super(world);
		this.services = services;
		this.documentRootRef = documentRootRef;
	}

	protected abstract boolean acceptDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things,
			ICoordinate location);

	protected abstract void doDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things,
			ICoordinate location);

	@Override
	public void dragEnter(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location) {
	}

	@Override
	public void dragExit(IBNAView view, DNDType type) {
		BNAUtils.checkLock();

		if (pulser != null) {
			model.removeThing(pulser);
			pulser = null;
		}
	}

	@Override
	public void drag(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location) {
		BNAUtils.checkLock();

		if (acceptDrop(view, type, data, things, location)) {
			IThing t = firstOrNull(things);
			if (pulser == null && t != null && t instanceof IHasBoundingBox) {
				pulser = new PulsingBorderThing(null);
				pulser.setBoundingBox(((IHasBoundingBox) t).getBoundingBox());
				model.addThing(pulser, t);
			}
			else if (pulser != null && t != null && t instanceof IHasBoundingBox) {
				pulser.setBoundingBox(((IHasBoundingBox) t).getBoundingBox());
			}
			else if (pulser != null) {
				model.removeThing(pulser);
				pulser = null;
			}
		}
		else {
			if (pulser != null) {
				model.removeThing(pulser);
				pulser = null;
			}
		}
	}

	@Override
	public void drop(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location) {
		BNAUtils.checkLock();

		if (pulser != null) {
			model.removeThing(pulser);
			pulser = null;
		}

		if (acceptDrop(view, type, data, things, location)) {
			doDrop(view, type, data, things, location);
		}
	}
}
