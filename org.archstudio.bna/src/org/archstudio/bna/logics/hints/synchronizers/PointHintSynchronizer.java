package org.archstudio.bna.logics.hints.synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class PointHintSynchronizer extends PropertyHintSynchronizer {

	private static final String STUCK_INFO_HINT_SUFFIX = "-StuckInfo";
	private static final String MOVE_WITH_INFO_HINT_SUFFIX = "-MoveWithInfo";

	protected static float[] getFractionOffsets(Rectangle r, Point p) {
		assert r != null;
		assert p != null;

		float fx = ((float) p.x - r.x) / r.width;
		float fy = ((float) p.y - r.y) / r.height;
		return new float[] { fx, fy };
	}

	protected static Point getPoint(Rectangle r, float[] fractionOffsets) {
		assert r != null;
		assert fractionOffsets != null && fractionOffsets.length == 2;

		int x = r.x + Math.round(r.width * fractionOffsets[0]);
		int y = r.y + Math.round(r.height * fractionOffsets[1]);
		return new Point(x, y);
	}

	protected final Object stuckToThingIdPropertyName;
	protected final Object stickyModePropertyName;
	protected final String stuckInfoHintSuffix;
	protected final int stuckInfoHintSuffixLength;
	protected final List<String> oldStuckHintNames = new ArrayList<String>();

	protected final Object moveWithThingIdPropertyName;
	protected final String moveWithInfoHintSuffix;
	protected final int moveWithInfoHintSuffixLength;
	protected final List<String> oldMovesWithHintNames = new ArrayList<String>();

	public PointHintSynchronizer(String hintSuffix, Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		super(hintSuffix, thingInterface, propertyName, userProperties);

		this.stuckToThingIdPropertyName = MaintainStickyPointLogic.getReferenceName(propertyName);
		this.stickyModePropertyName = MaintainStickyPointLogic.getStickyModeName(propertyName);
		this.stuckInfoHintSuffix = this.hintSuffix + STUCK_INFO_HINT_SUFFIX;
		this.stuckInfoHintSuffixLength = stuckInfoHintSuffix.length();

		this.moveWithThingIdPropertyName = MoveWithLogic.MOVES_WITH_THING_ID_KEY;
		this.moveWithInfoHintSuffix = this.hintSuffix + MOVE_WITH_INFO_HINT_SUFFIX;
		this.moveWithInfoHintSuffixLength = moveWithInfoHintSuffix.length();
	}

	public PointHintSynchronizer(Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		this(null, thingInterface, propertyName, userProperties);
	}

	public PointHintSynchronizer addOldStuckHintName(String oldStuckHintName, String oldStuckHintPath) {
		this.oldStuckHintNames.add(oldStuckHintName);
		this.oldHintNamesMap.put(oldStuckHintName, oldStuckHintPath + stuckInfoHintSuffix);
		return this;
	}

	public PointHintSynchronizer addOldMovesWithHintName(String oldMovesWithHintName, String oldMovesWithHintPath) {
		this.oldMovesWithHintNames.add(oldMovesWithHintName);
		this.oldHintNamesMap.put(oldMovesWithHintName, oldMovesWithHintPath + stuckInfoHintSuffix);
		return this;
	}

	protected Object cleanupStuckInfoHint(Object value) {
		/*
		 * This translates old forms of the stuck info hint to the current form
		 */
		if (value instanceof float[]) {
			// hint is: float[]{fraction.x, fraction.y}
			float[] f = (float[]) value;
			if (f.length == 2) {
				if (0.49f < f[0] && f[0] < 0.51f && 0.49f < f[1] && f[1] < 0.51f) {
					value = new Object[] { StickyMode.EDGE_FROM_CENTER };
				}
				else {
					value = new Object[] { StickyMode.EDGE, f };
				}
			}
		}
		if (value instanceof Object[]) {
			Object[] v = (Object[]) value;
			if (v.length >= 1 && v[0] instanceof StickyMode) {
				// hint is: Object[]{ StickyMode, ... ? }
				StickyMode sm = (StickyMode) v[0];
				switch (sm) {
				case CENTER:
				case EDGE_FROM_CENTER:
					value = new Object[] { sm };
					break;
				}
			}
		}
		return value;
	}

	protected List<String> getStuckInfoHintNames(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		List<String> hintNames = new ArrayList<String>();
		hintNames.add(partPath + stuckInfoHintSuffix);
		hintNames.addAll(oldStuckHintNames);
		return hintNames;
	}

	protected List<String> getMovesWithHintNames(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		List<String> hintNames = new ArrayList<String>();
		hintNames.add(partPath + moveWithInfoHintSuffix);
		hintNames.addAll(oldMovesWithHintNames);
		return hintNames;
	}

	@Override
	protected List<String> getHintNames(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		List<String> hintNames = new ArrayList<String>();
		if (thing.getProperty(stuckToThingIdPropertyName) != null) {
			hintNames.addAll(getStuckInfoHintNames(repository, context, partPath, parts, thing));
		}
		else if (thing.getProperty(moveWithThingIdPropertyName) != null) {
			hintNames.addAll(getMovesWithHintNames(repository, context, partPath, parts, thing));
		}
		hintNames.addAll(super.getHintNames(repository, context, partPath, parts, thing));
		return hintNames;
	}

	@Override
	protected String getPartPath(String hintName) {
		if (hintName.endsWith(stuckInfoHintSuffix)) {
			return hintName.substring(0, hintName.length() - stuckInfoHintSuffixLength);
		}
		if (hintName.endsWith(moveWithInfoHintSuffix)) {
			return hintName.substring(0, hintName.length() - moveWithInfoHintSuffixLength);
		}
		return super.getPartPath(hintName);
	}

	@Override
	protected void restoreHint(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName, String hintName,
	        Object hintValue) {
		if (hintName.endsWith(stuckInfoHintSuffix)) {
			IThing stickyThing = world.getBNAModel().getThing((String) thing.getProperty(stuckToThingIdPropertyName));
			if (stickyThing != null) {
				Lock lock = thing.getPropertyLock();
				lock.lock();
				try {
					hintValue = cleanupStuckInfoHint(hintValue);
					if (hintValue instanceof Object[]) {
						Object[] v = (Object[]) hintValue;
						if (v.length >= 1 && v[0] instanceof StickyMode) {
							// hint is: Object[]{ StickyMode, ... ? }
							StickyMode sm = (StickyMode) v[0];
							switch (sm) {
							case CENTER:
							case EDGE_FROM_CENTER:
								thing.setProperty(stickyModePropertyName, sm);
								break;

							case EDGE:
								// hint is: Object[]{ EDGE, float[]{fraction.x, fraction.y} }
								thing.setProperty(stickyModePropertyName, sm);
								if (v.length >= 2 && v[1] instanceof float[]) {
									float[] f = (float[]) v[1];
									if (f.length == 2 && stickyThing instanceof IHasBoundingBox) {
										Point p = thing.getProperty(this.propertyName);
										Point np = getPoint(((IHasBoundingBox) stickyThing).getBoundingBox(), f);
										if (!p.equals(np)) {
											thing.setProperty(this.propertyName, np);
										}
									}
								}
							}
						}
					}
				}
				finally {
					lock.unlock();
				}
			}
			return;
		}
		else if (hintName.endsWith(moveWithInfoHintSuffix)) {
			IThing moveWithThing = world.getBNAModel().getThing((String) thing.getProperty(moveWithThingIdPropertyName));
			if (moveWithThing != null) {
				Lock lock = thing.getPropertyLock();
				lock.lock();
				try {
					if (hintValue instanceof Object[]) {
						Object[] v = (Object[]) hintValue;
						if (v.length >= 1 && v[0] instanceof Point) {
							// hint is: Object[]{ Point, ... ? }
							Point op = (Point) v[0];
							Point np = null;
							if (moveWithThing instanceof IHasAnchorPoint) {
								Point ap = ((IHasAnchorPoint) moveWithThing).getAnchorPoint();
								np = new Point(ap.x + op.x, ap.y + op.y);
							}
							else if (moveWithThing instanceof IHasBoundingBox) {
								Rectangle r = ((IHasBoundingBox) moveWithThing).getBoundingBox();
								Point ap = new Point(r.x + r.width / 2, r.y + r.height / 2);
								np = new Point(ap.x + op.x, ap.y + op.y);
							}
							if (np != null) {
								thing.setProperty(this.propertyName, np);
							}
						}
					}
				}
				finally {
					lock.unlock();
				}
			}
			return;
		}
		else if (hintValue instanceof Point) {
			super.restoreHint(repository, context, partPath, parts, thing, propertyName, hintName, hintValue);
		}
	}

	@Override
	public void filteredThingChanged(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName,
	        Object oldValue, Object newValue) {
		try {
			if (stuckToThingIdPropertyName.equals(propertyName)) {
				// the thing has been stuck to something else, restore the stuck info
				IThing stuckThing = world.getBNAModel().getThing((String) thing.getProperty(stuckToThingIdPropertyName));
				if (stuckThing != null) {
					filteredRestoreHints(repository, context, partPath, parts, thing);
				}
				return;
			}
			if (this.propertyName.equals(propertyName) || stickyModePropertyName.equals(propertyName)) {
				storeStickyThingHint(repository, context, partPath, parts, thing);
			}

			if (moveWithThingIdPropertyName.equals(propertyName)) {
				// the thing has been moved to something else, restore the moveWith info
				IThing moveWithThing = world.getBNAModel().getThing((String) thing.getProperty(moveWithThingIdPropertyName));
				if (moveWithThing != null) {
					filteredRestoreHints(repository, context, partPath, parts, thing);
				}
				return;
			}
			if (this.propertyName.equals(propertyName)) {
				storeMoveWithHint(repository, context, partPath, parts, thing);
			}
		}
		finally {
			// we still want to store the absolute position 
			super.filteredThingChanged(repository, context, partPath, parts, thing, propertyName, oldValue, newValue);
		}
	}

	@Override
	protected void filteredRestoreHints(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		super.filteredRestoreHints(repository, context, partPath, parts, thing);
		storeMoveWithHint(repository, context, partPath, parts, thing);
		storeStickyThingHint(repository, context, partPath, parts, thing);
	}

	protected void storeStickyThingHint(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		IThing stickyThing = world.getBNAModel().getThing((String) thing.getProperty(stuckToThingIdPropertyName));
		if (stickyThing != null) {
			Object oldHint = cleanupStuckInfoHint(getFirstHint(repository, context, getStuckInfoHintNames(repository, context, partPath, parts, thing)));
			Object newHint = null;

			StickyMode s = thing.getProperty(stickyModePropertyName);
			if (s != null) {
				switch (s) {
				case CENTER:
				case EDGE_FROM_CENTER:
					newHint = new Object[] { s };
					break;

				case EDGE:
					if (stickyThing instanceof IHasBoundingBox) {
						Rectangle r = ((IHasBoundingBox) stickyThing).getBoundingBox();
						Point p = thing.getProperty(propertyName);
						newHint = new Object[] { s, getFractionOffsets(r, p) };
						if (oldHint instanceof Object[]) {
							Object[] v = (Object[]) oldHint;
							if (v.length >= 2 && v[0] instanceof StickyMode) {
								if (s.equals(v[0]) && v[1] instanceof float[]) {
									float[] f = (float[]) v[1];
									if (f.length == 2) {
										// only update the hint if it produces a change in the resulting point
										Point np = getPoint(r, f);
										if (p.equals(np)) {
											newHint = null;
										}
									}
								}
							}
						}
					}
				}

				if (newHint != null && !SystemUtils.deepEquals(oldHint, newHint)) {
					repository.storeHint(context, partPath + stuckInfoHintSuffix, newHint);
				}
			}
		}
	}

	protected void storeMoveWithHint(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		IThing moveWithThing = world.getBNAModel().getThing((String) thing.getProperty(moveWithThingIdPropertyName));
		if (moveWithThing != null) {
			Object oldHint = repository.getHint(context, partPath + moveWithInfoHintSuffix);
			Object newHint = null;

			if (moveWithThing instanceof IHasAnchorPoint) {
				Point ap = ((IHasAnchorPoint) moveWithThing).getAnchorPoint();
				Point op = thing.getProperty(propertyName);
				newHint = new Object[] { new Point(op.x - ap.x, op.y - ap.y) };
			}
			else if (moveWithThing instanceof IHasBoundingBox) {
				Rectangle r = ((IHasBoundingBox) moveWithThing).getBoundingBox();
				Point ap = new Point(r.x + r.width / 2, r.y + r.height / 2);
				Point op = thing.getProperty(propertyName);
				newHint = new Object[] { new Point(op.x - ap.x, op.y - ap.y) };
			}

			if (newHint != null && !SystemUtils.deepEquals(oldHint, newHint)) {
				repository.storeHint(context, partPath + moveWithInfoHintSuffix, newHint);
			}
		}
	}

}
