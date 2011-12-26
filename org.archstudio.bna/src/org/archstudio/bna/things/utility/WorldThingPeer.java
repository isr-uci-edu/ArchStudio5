package org.archstudio.bna.things.utility;

import javax.media.opengl.GL2;

import org.archstudio.bna.CoordinateMapperEvent;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.ICoordinateMapperListener;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class WorldThingPeer<T extends WorldThing> extends AbstractRectangleThingPeer<T> implements IThingPeer<T>,
		IHasInnerViewPeer, ICoordinateMapperListener {

	public static final IThingKey<Integer> COORDINATE_MAPPER_CHANGE_TICKER = ThingKey
			.create("CoordinateMapperChangeTicker");

	protected IBNAView innerView = null;

	public WorldThingPeer(T thing) {
		super(thing);
	}

	@Override
	public IBNAView getInnerView() {
		return innerView;
	}

	private Rectangle lastModelBounds = new Rectangle(0, 0, 0, 0);
	private Rectangle lastLocalBounds = new Rectangle(0, 0, 0, 0);

	@Override
	public void updateCache(IBNAView view, ICoordinateMapper cm) {
		super.updateCache(view, cm);

		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return;
		}

		if (innerView != null && !innerWorld.equals(innerView.getBNAWorld())) {
			innerView.getCoordinateMapper().removeCoordinateMapperListener(this);
			innerView = null;
		}
		if (innerView == null) {
			innerView = new DefaultBNAView(view, t.getWorld(), new LinearCoordinateMapper());
			innerView.getCoordinateMapper().addCoordinateMapperListener(this);
		}

		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (lbb.height < 5 || lbb.width < 5) {
			return;
		}

		ModelBoundsTrackingLogic mbtl = innerWorld.getThingLogicManager().addThingLogic(ModelBoundsTrackingLogic.class);
		Rectangle modelBounds = mbtl.getModelBounds();

		if (modelBounds.x == Integer.MIN_VALUE || modelBounds.width == 0 || modelBounds.height == 0) {
			Rectangle worldBounds = innerView.getCoordinateMapper().getWorldBounds();
			modelBounds.x = worldBounds.x + worldBounds.width / 2;
			modelBounds.y = worldBounds.y + worldBounds.height / 2;
			modelBounds.width = 100;
			modelBounds.height = 100;
		}
		modelBounds.x -= 1;
		modelBounds.y -= 1;
		modelBounds.width += 2;
		modelBounds.height += 2;

		ICoordinateMapper icm = innerView.getCoordinateMapper();

		if (icm instanceof IMutableCoordinateMapper) {
			if (!lastModelBounds.equals(modelBounds) || !lastLocalBounds.equals(lbb)) {
				IMutableCoordinateMapper imcm = (IMutableCoordinateMapper) icm;
				double sx = (double) lbb.width / modelBounds.width;
				double sy = (double) lbb.height / modelBounds.height;
				double parentScale = view.getCoordinateMapper().getLocalScale();
				double scale = Math.min(parentScale, Math.min(sx, sy));
				int dx = BNAUtils.round((lbb.width - modelBounds.width * scale) / 2);
				int dy = BNAUtils.round((lbb.height - modelBounds.height * scale) / 2);
				lastModelBounds = modelBounds;
				lastLocalBounds = lbb;
				imcm.setLocalScaleAndAlign(scale, new Point(lbb.x + dx, lbb.y + dy), new Point(modelBounds.x,
						modelBounds.y));
			}
		}
	}

	@Override
	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		t.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Integer ticker = t.get(COORDINATE_MAPPER_CHANGE_TICKER);
				if (ticker == null) {
					ticker = Integer.valueOf(0);
				}
				t.set(COORDINATE_MAPPER_CHANGE_TICKER, ticker + 1);
			}
		});
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return;
		}

		for (IThing thing : innerWorld.getBNAModel().getAllThings()) {
			IThingPeer<?> peer = innerView.getThingPeer(thing);
			if (clip.intersects(peer.getLocalBounds(innerView, innerView.getCoordinateMapper()))) {
				peer.draw(innerView, innerView.getCoordinateMapper(), gl, clip, r);
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate coordinate) {
		if (t.getWorld() != null && innerView != null) {
			return super.isInThing(view, cm, coordinate);
		}
		return false;
	}

}
