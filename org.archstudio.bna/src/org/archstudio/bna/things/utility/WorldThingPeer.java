package org.archstudio.bna.things.utility;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.CoordinateMapperEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class WorldThingPeer<T extends WorldThing> extends AbstractRectangleThingPeer<T> implements IThingPeer<T>,
		IHasInnerViewPeer, ICoordinateMapperListener, IBNAModelListener {

	public static final IThingKey<Integer> COORDINATE_MAPPER_CHANGE_TICKER = ThingKey
			.create("CoordinateMapperChangeTicker");

	/*
	 * Records where each model is being rendered. If we discover that we are
	 * trying to render a model in the same size and location, then we would
	 * recurse rendering that model forever. So, instead we cut it off when we
	 * hit that situation.
	 */
	static Multimap<IBNAModel, Rectangle> modelsBeingRendered = HashMultimap.create();

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
	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		Integer ticker = t.get(COORDINATE_MAPPER_CHANGE_TICKER);
		if (ticker == null) {
			ticker = Integer.valueOf(0);
		}
		t.set(COORDINATE_MAPPER_CHANGE_TICKER, ticker + 1);
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == EventType.THING_REMOVING) {
			if (innerView != null) {
				innerView.disposePeer(evt.getTargetThing());
			}
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return;
		}

		if (innerView != null && !innerWorld.equals(innerView.getBNAWorld())) {
			innerView.getCoordinateMapper().removeCoordinateMapperListener(this);
			innerView.getBNAWorld().getBNAModel().removeBNAModelListener(this);
			innerView = null;
		}
		if (innerView == null) {
			innerView = new DefaultBNAView(view, t.getWorld(), new LinearCoordinateMapper());
			innerView.getBNAWorld().getBNAModel().addBNAModelListener(this);
			innerView.getCoordinateMapper().addCoordinateMapperListener(this);
		}

		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (lbb.height < 5 || lbb.width < 5) {
			return;
		}
		IBNAModel model = innerWorld.getBNAModel();
		try {
			if (!modelsBeingRendered.put(model, lbb) || modelsBeingRendered.size() > 50) {
				return;
			}

			ModelBoundsTrackingLogic mbtl = innerWorld.getThingLogicManager().addThingLogic(
					ModelBoundsTrackingLogic.class);
			Rectangle modelBounds = mbtl.getModelBounds();
			if (modelBounds.width == 0 || modelBounds.height == 0) {
				modelBounds = innerView.getCoordinateMapper().getWorldBounds();
			}

			ICoordinateMapper icm = innerView.getCoordinateMapper();

			if (icm instanceof IMutableCoordinateMapper) {
				if (!lastModelBounds.equals(modelBounds) || !lastLocalBounds.equals(lbb)) {
					IMutableCoordinateMapper imcm = (IMutableCoordinateMapper) icm;
					double xScale = (double) lbb.width / modelBounds.width;
					double yScale = (double) lbb.height / modelBounds.height;
					double parentScale = view.getCoordinateMapper().getLocalScale();
					double scale = Math.min(parentScale, Math.min(xScale, yScale));
					int dx = BNAUtils.round((lbb.width - modelBounds.width * scale) / 2);
					int dy = BNAUtils.round((lbb.height - modelBounds.height * scale) / 2);
					lastModelBounds = modelBounds;
					lastLocalBounds = lbb;
					imcm.setLocalScaleAndAlign(scale, new Point(lbb.x + dx, lbb.y + dy), new Point(modelBounds.x,
							modelBounds.y));
				}
			}

			for (IThing thing : model.getAllThings()) {
				//gl.glPushMatrix();
				gl.glPushAttrib(GL2.GL_TRANSFORM_BIT | GL2.GL_LINE_BIT | GL2.GL_CURRENT_BIT | GL.GL_COLOR_BUFFER_BIT);
				try {
					IThingPeer<?> peer = innerView.getThingPeer(thing);
					peer.draw(innerView, innerView.getCoordinateMapper(), gl, clip, r);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					gl.glPopAttrib();
					//gl.glPopMatrix();
				}
			}
		}
		finally {
			modelsBeingRendered.remove(model, lbb);
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
