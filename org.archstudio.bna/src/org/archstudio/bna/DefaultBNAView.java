package org.archstudio.bna;

import java.util.ListIterator;

import org.eclipse.swt.widgets.Composite;

public class DefaultBNAView implements IBNAView {
	protected Composite parentComposite = null;
	protected IBNAView parentView = null;
	protected IBNAWorld bnaWorld = null;
	protected ICoordinateMapper cm = null;
	protected transient PeerCache peerCache = null;

	public DefaultBNAView(IBNAView parentView, IBNAWorld bnaWorld, ICoordinateMapper cm) {
		this.parentView = parentView;
		this.bnaWorld = bnaWorld;
		this.cm = cm;
		this.peerCache = new PeerCache();
	}

	public void setParentComposite(Composite parent) {
		this.parentComposite = parent;
	}

	public Composite getParentComposite() {
		return parentComposite;
	}

	public IBNAView getParentView() {
		return parentView;
	}

	public IBNAWorld getWorld() {
		return bnaWorld;
	}

	public ICoordinateMapper getCoordinateMapper() {
		return cm;
	}

	public synchronized IThing getThingAt(int lx, int ly) {
		int wx = cm.localXtoWorldX(lx);
		int wy = cm.localYtoWorldY(ly);

		IBNAModel model = getWorld().getBNAModel();
		synchronized (model.getLock()) {
			//GC gc = new GC(BNAUtils.getBNAComposite(parentContext));
			for (ListIterator<IThing> it = model.getThingListIterator(model.getNumThings()); it.hasPrevious();) {
				IThing th = (IThing) it.previous();
				IThingPeer peer = getPeer(th);
				if (peer.isInThing(this, wx, wy)) {
					return th;
				}
			}
		}
		return null;
	}

	public PeerCache getPeerCache() {
		return peerCache;
	}

	public IThingPeer createPeer(IThing th) {
		return peerCache.createPeer(th);
	}

	public IThingPeer getPeer(IThing th) {
		return peerCache.getPeer(th);
	}

}
