package org.archstudio.bna;

import static org.archstudio.sysutils.SystemUtils.simpleName;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.CoordinateMapperEvent.EventType;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Lists;

public abstract class AbstractCoordinateMapper implements IMutableCoordinateMapper, Cloneable {

	public static final Rectangle getDefaultBounds() {
		return new Rectangle(-100000, -100000, 200000, 200000);
	}

	protected Rectangle worldBounds = getDefaultBounds();
	protected double localScale = 1.0d;
	protected Point localOrigin = new Point(worldBounds.x + worldBounds.width / 2, worldBounds.y + worldBounds.height
			/ 2);

	protected List<ICoordinateMapperListener> listeners = new CopyOnWriteArrayList<ICoordinateMapperListener>();
	protected int synchronizedUpdateCount = 0;
	protected List<CoordinateMapperEvent> synchronizedUpdateEvents = null;

	public AbstractCoordinateMapper() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		AbstractCoordinateMapper t = (AbstractCoordinateMapper) super.clone();
		t.worldBounds = new Rectangle(t.worldBounds.x, t.worldBounds.y, t.worldBounds.width, t.worldBounds.height);
		t.localOrigin = new Point(t.localOrigin.x, t.localOrigin.y);
		t.listeners = new CopyOnWriteArrayList<ICoordinateMapperListener>();
		return t;
	}

	@Override
	public void addCoordinateMapperListener(ICoordinateMapperListener l) {
		listeners.add(l);
	}

	@Override
	public void removeCoordinateMapperListener(ICoordinateMapperListener l) {
		listeners.remove(l);
	}

	protected synchronized void fireCoordinateMapperEvent(EventType eventtType) {
		fireCoordinateMapperEvent(new CoordinateMapperEvent(this, eventtType, worldBounds, getLocalOrigin(),
				getLocalScale()));
	}

	protected synchronized void fireCoordinateMapperEvent(CoordinateMapperEvent evt) {
		if (synchronizedUpdateEvents != null) {
			synchronizedUpdateEvents.add(evt);
			return;
		}
		for (ICoordinateMapperListener l : listeners) {
			l.coordinateMappingsChanged(evt);
		}
	}

	@Override
	public synchronized Rectangle getWorldBounds() {
		return BNAUtils.clone(worldBounds);
	}

	@Override
	public synchronized void setWorldBounds(Rectangle worldBounds) {
		this.worldBounds = BNAUtils.clone(worldBounds);
		fireCoordinateMapperEvent(EventType.WORLD_BOUNDS);
	}

	@Override
	public Point getLocalOrigin() {
		return BNAUtils.clone(localOrigin);
	}

	@Override
	public void setLocalOrigin(Point localOrigin) {
		this.localOrigin = BNAUtils.clone(localOrigin);
		fireCoordinateMapperEvent(EventType.LOCAL_ORIGIN);
	}

	@Override
	public double getLocalScale() {
		return localScale;
	}

	@Override
	public void setLocalScale(double localScale) {
		this.localScale = localScale;
		fireCoordinateMapperEvent(EventType.LOCAL_SCALE);
	}

	@Override
	public void align(Point localPoint, Point worldPoint) {
		Point oldLocalPoint = worldToLocal(new Point(worldPoint.x, worldPoint.y));
		Point localDelta = new Point(localPoint.x - oldLocalPoint.x, localPoint.y - oldLocalPoint.y);
		this.localOrigin.x -= localDelta.x;
		this.localOrigin.y -= localDelta.y;
		fireCoordinateMapperEvent(EventType.LOCAL_ORIGIN);
	}

	@Override
	public void setLocalScaleAndAlign(final double localScale, final Point localPoint, final Point worldPoint) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				AbstractCoordinateMapper.this.localScale = localScale;
				align(localPoint, worldPoint);
			}
		});
	}

	@Override
	public synchronized void synchronizedUpdate(Runnable r) {
		List<CoordinateMapperEvent> updateEvents = null;
		try {
			if (synchronizedUpdateCount++ == 0) {
				updateEvents = Lists.newArrayList();
				synchronizedUpdateEvents = updateEvents;
			}
			r.run();
		}
		finally {
			if (--synchronizedUpdateCount != 0) {
				return;
			}
			synchronizedUpdateEvents = null;
		}
		for (CoordinateMapperEvent evt : updateEvents) {
			fireCoordinateMapperEvent(evt);
		}
	}

	@Override
	public String toString() {
		return simpleName(this.getClass()) + "["//
				+ "worldBounds=" + worldBounds + ","//
				+ "localScale=" + localScale + ","//
				+ "localOrigin=" + localOrigin + "]";
	}

	@Override
	public Rectangle localToWorld(Rectangle localRectangle) {
		return new Rectangle(//
				localToWorld(localRectangle.getTopLeft()),//
				localToWorld(localRectangle.getBottomRight()));
	}

	@Override
	public Rectangle worldToLocal(Rectangle worldRectangle) {
		return new Rectangle(//
				worldToLocal(worldRectangle.getTopLeft()),//
				worldToLocal(worldRectangle.getBottomRight()));
	}
}