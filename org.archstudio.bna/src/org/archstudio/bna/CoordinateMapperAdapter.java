package org.archstudio.bna;

import static org.archstudio.sysutils.SystemUtils.simpleName;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.CoordinateMapperEvent.EventType;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Lists;

public abstract class CoordinateMapperAdapter implements IMutableCoordinateMapper, Cloneable {

	protected Rectangle worldBounds = new Rectangle(0, 0, 20000, 20000);
	protected double localScale = 1.0d;
	protected Point localOrigin = new Point(worldBounds.getCenter());

	protected List<ICoordinateMapperListener> listeners = new CopyOnWriteArrayList<ICoordinateMapperListener>();
	protected int synchronizedUpdateCount = 0;
	protected List<CoordinateMapperEvent> synchronizedUpdateEvents = null;

	public CoordinateMapperAdapter() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CoordinateMapperAdapter t = (CoordinateMapperAdapter) super.clone();
		t.worldBounds = new Rectangle(t.worldBounds);
		t.localOrigin = new Point(t.localOrigin);
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
		fireCoordinateMapperEvent(new CoordinateMapperEvent(this, eventtType, worldBounds, getLocalOrigin(new Point()),
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
	public synchronized Rectangle getWorldBounds(Rectangle result) {
		return result.setBounds(worldBounds);
	}

	@Override
	public synchronized void setWorldBounds(Rectangle worldBounds) {
		this.worldBounds.setBounds(worldBounds);
		fireCoordinateMapperEvent(EventType.WORLD_BOUNDS);
	}

	@Override
	public Point getLocalOrigin(Point result) {
		return result.setLocation(localOrigin);
	}

	@Override
	public void setLocalOrigin(Point localOrigin) {
		this.localOrigin.setLocation(localOrigin);
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
		Point oldLocalPoint = worldToLocal(worldPoint.getCopy());
		Point d = localPoint.getTranslated(oldLocalPoint.getNegated());
		this.localOrigin.translate(-d.x, -d.y);
		fireCoordinateMapperEvent(EventType.LOCAL_ORIGIN);
	}

	@Override
	public void setLocalScaleAndAlign(final double localScale, final Point localPoint, final Point worldPoint) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				setLocalScale(localScale);
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
	public ICoordinateMapper copy() {
		try {
			return (ICoordinateMapper) clone();
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
}