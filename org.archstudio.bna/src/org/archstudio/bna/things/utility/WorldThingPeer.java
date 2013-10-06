package org.archstudio.bna.things.utility;

import java.util.Set;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;

public class WorldThingPeer<T extends WorldThing> extends AbstractRectangleThingPeer<T> implements IHasInnerViewPeer<T> {

	/*
	 * This records where each model is being rendered. If we discover that we are trying to render a model in the same
	 * location twice, then we are trying to recurse forever. So, instead we cut it off when we hit that situation.
	 */
	private static final LoadingCache<IBNAModel, Set<Rectangle>> modelsBoundsBeingRendered = CacheBuilder.newBuilder()
			.weakKeys().build(new CacheLoader<IBNAModel, Set<Rectangle>>() {
				@Override
				public Set<Rectangle> load(IBNAModel key) throws Exception {
					return Sets.newHashSet();
				}
			});

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
				if (this.iView != null && this.iView.getBNAWorld() == iWorld) {
					iView = this.iView;
				}
				else {
					iView = new DefaultBNAView(view, iWorld, new LinearCoordinateMapper());
					iView.setBNAUI(view.getBNAUI());
				}

				ICoordinateMapper iCM = iView.getCoordinateMapper();
				if (iCM instanceof IMutableCoordinateMapper) {
					IMutableCoordinateMapper iMCM = (IMutableCoordinateMapper) iCM;
					ModelBoundsTrackingLogic iBoundsLogic = iWorld.getThingLogicManager().addThingLogic(
							ModelBoundsTrackingLogic.class);
					Rectangle iBounds = iBoundsLogic.getModelBounds();
					if (iBounds.width == 0 || iBounds.height == 0) {
						iBounds = iView.getCoordinateMapper().getWorldBounds();
					}
					double xScale = (double) lbb.width / iBounds.width;
					double yScale = (double) lbb.height / iBounds.height;
					double parentScale = cm.getLocalScale();
					double iScale = Math.min(parentScale, Math.min(xScale, yScale));
					int dx = BNAUtils.round((lbb.width - iBounds.width * iScale) / 2);
					int dy = BNAUtils.round((lbb.height - iBounds.height * iScale) / 2);
					iMCM.setLocalScaleAndAlign(iScale, new Point(lbb.x + dx, lbb.y + dy), new Point(iBounds.x,
							iBounds.y));
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
		if (localBounds.intersects(lbb) && lbb.height >= 5 && lbb.width >= 5) {
			IBNAView iView = getInnerView();
			if (iView != null) {
				IBNAModel iModel = iView.getBNAWorld().getBNAModel();
				Set<Rectangle> boundsBeingRendered = modelsBoundsBeingRendered.getUnchecked(iModel);
				if (boundsBeingRendered.add(lbb)) {
					try {
						r.renderThings(iView, localBounds);
					}
					finally {
						boundsBeingRendered.remove(lbb);
					}
				}
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
