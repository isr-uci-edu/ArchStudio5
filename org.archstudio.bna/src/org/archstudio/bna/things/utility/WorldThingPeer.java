package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.LinearCoordinateMapper;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class WorldThingPeer<T extends WorldThing> extends AbstractRectangleThingPeer<T> implements IHasInnerViewPeer<T> {

	private IBNAView iView = null;

	public WorldThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void dispose() {
		if (iView != null) {
			iView.dispose();
		}
		super.dispose();
	}

	@Override
	public IBNAView getInnerView() {
		IBNAView iView = null;
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (lbb.height >= 5 && lbb.width >= 5) {
			IBNAWorld iWorld = t.getWorld();
			if (iWorld != null) {

				// determine the offset and scale for the inner view
				ModelBoundsTrackingLogic iBoundsLogic = iWorld.getThingLogicManager().addThingLogic(
						ModelBoundsTrackingLogic.class);
				ICoordinateMapper iCM = this.iView != null ? this.iView.getCoordinateMapper()
						: new LinearCoordinateMapper();
				Rectangle iBounds = iBoundsLogic.getModelBounds();
				if (iBounds.width == 0 || iBounds.height == 0) {
					iBounds = iCM.getWorldBounds();
				}
				if (iCM instanceof IMutableCoordinateMapper) {
					IMutableCoordinateMapper iMCM = (IMutableCoordinateMapper) iCM;
					double xScale = (double) lbb.width / iBounds.width;
					double yScale = (double) lbb.height / iBounds.height;
					double parentScale = cm.getLocalScale();
					double iScale = Math.min(parentScale, Math.min(xScale, yScale));
					int dx = BNAUtils.round((lbb.width - iBounds.width * iScale) / 2);
					int dy = BNAUtils.round((lbb.height - iBounds.height * iScale) / 2);
					iMCM.setLocalScaleAndAlign(iScale, new Point(lbb.x + dx, lbb.y + dy), new Point(iBounds.x,
							iBounds.y));
				}

				// check for infinite recursion
				Rectangle outerLBB = cm.worldToLocal(iBounds);
				Rectangle innerLBB = iCM.worldToLocal(iBounds);
				if (!outerLBB.equals(innerLBB)) {
					if (this.iView != null && this.iView.getBNAWorld() == iWorld) {
						iView = this.iView;
					}
					else {
						iView = new DefaultBNAView(view, iWorld, iCM);
						iView.setBNAUI(view.getBNAUI());
					}
				}
			}
		}
		if (this.iView != iView) {
			if (this.iView != null) {
				this.iView.dispose();
				this.iView = null;
			}
			this.iView = iView;
		}
		return this.iView;
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (localBounds.intersects(lbb)) {
			IBNAView iView = getInnerView();
			if (iView != null) {
				r.renderThings(iView, localBounds);
			}
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate coordinate) {
		IBNAView iView = getInnerView();
		if (iView != null) {
			return super.isInThing(coordinate);
		}
		return false;
	}

}
