package org.archstudio.bna.logics.swt;

import java.util.HashMap;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMinimumSize;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SWTBNASynchLogic extends AbstractThingLogic implements ControlListener, IBNAModelListener {

	ReferenceTrackingLogic rtl;

	DefaultCoordinateMapper cm = new DefaultCoordinateMapper();

	public SWTBNASynchLogic(ReferenceTrackingLogic rtl) {
		this.rtl = rtl;
	}

	boolean initialized = false;

	Object lock = new Object();

	private class Mapping {

		final String thingID;

		final Control control;

		public Mapping(String thingID, Control control) {
			this.thingID = thingID;
			this.control = control;
		}

		boolean shouldUpdateThing() {
			Control parent = control.getParent();
			if (parent instanceof Composite) {
				Composite parentComposite = (Composite) parent;
				return parentComposite.getLayout() != null;
			}
			return false;
		}

		boolean shouldUpdateControl() {
			return !shouldUpdateThing();
		}
	}

	HashMap<String, Mapping> thingMappings = null;

	HashMap<Control, Mapping> controlMappings = null;

	@Override
	protected void init() {
		super.init();
		synchronized (lock) {
			thingMappings = new HashMap<String, Mapping>();
			controlMappings = new HashMap<Control, Mapping>();
			initialized = true;
		}
	}

	public void reset() {
		synchronized (lock) {
			destroy();
			init();
		}
	}

	@Override
	protected void destroy() {
		synchronized (lock) {
			if (!initialized) {
				return;
			}

			initialized = false;
			for (final Map.Entry<Control, Mapping> entry : controlMappings.entrySet()) {
				SWTWidgetUtils.async(entry.getKey(), new Runnable() {

					public void run() {
						try {
							entry.getKey().removeControlListener(SWTBNASynchLogic.this);
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				});
			}
			thingMappings.clear();
			controlMappings.clear();

			thingMappings = null;
			controlMappings = null;

			super.destroy();
		}
	}

	public void addSWTToBNAMapping(final Control control, String thingID) {
		synchronized (lock) {
			if (!initialized) {
				return;
			}
			Mapping mapping = thingMappings.get(thingID);
			if (mapping == null) {
				mapping = new Mapping(thingID, control);
				thingMappings.put(thingID, mapping);
				controlMappings.put(control, mapping);
			}
			control.addControlListener(this);
		}

		// do initial mapping
		final IThing thing = bnaWorld.getBNAModel().getThing(thingID);
		SWTWidgetUtils.async(control, new Runnable() {

			public void run() {
				try {
					updateThingBoundingBox(control, thing);
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		});
	}

	public void addBNAToSWTMapping(String thingID, final Control control) {
		synchronized (lock) {
			if (!initialized) {
				return;
			}
			Mapping mapping = thingMappings.get(thingID);
			if (mapping == null) {
				mapping = new Mapping(thingID, control);
				thingMappings.put(thingID, mapping);
				controlMappings.put(control, mapping);
			}
			control.addControlListener(this);
		}

		// do initial mapping
		final IThing thing = bnaWorld.getBNAModel().getThing(thingID);
		SWTWidgetUtils.sync(control, new Runnable() {

			public void run() {
				try {
					updateControlBoundingBox(control, thing);
				}
				catch (Throwable t) {
					t.printStackTrace();
				}

			}
		});
	}

	protected void updateThingBoundingBox(Control control, IThing thing) {
		assert control != null;
		assert thing != null;

		if (control != null && thing instanceof IHasMinimumSize) {
			Control parentControl = control.getParent();
			Rectangle parentControlR = BNAUtils.toRectangle(parentControl.getBounds());
			IHasMutableMinimumSize thingBB = (IHasMutableMinimumSize) thing;
			Rectangle controlR = BNAUtils.toRectangle(control.getBounds());
			controlR.x += parentControlR.x;
			controlR.y += parentControlR.y;
			controlR.intersect(parentControlR);
			if (cm != null) {
				BNAUtils.convertLocalToWorld(cm, controlR);
				BNAUtils.convertLocalToWorld(cm, parentControlR);
			}
			IThing parentThing = bnaWorld.getBNAModel().getThing(
					(String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY));
			if (parentThing != null && parentControl != null) {
				if (parentThing instanceof IHasBoundingBox) {
					IHasBoundingBox parentThingBB = (IHasBoundingBox) parentThing;
					Rectangle parentR = parentThingBB.getBoundingBox();
					controlR.x = controlR.x - parentControlR.x + parentR.x;
					controlR.y = controlR.y - parentControlR.y + parentR.y;
				}
			}
			thingBB.setBoundingBox(controlR);
		}
	}

	protected void updateControlBoundingBox(Control control, IThing thing) {
		assert control != null;
		assert thing != null;

		if (control != null && thing instanceof IHasBoundingBox) {
			IHasBoundingBox thingBB = (IHasBoundingBox) thing;
			Rectangle thingR = thingBB.getBoundingBox();
			IThing parentThing = bnaWorld.getBNAModel().getThing(
					(String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY));
			if (parentThing != null) {
				if (parentThing instanceof IHasBoundingBox) {
					IHasBoundingBox parentThingBB = (IHasBoundingBox) parentThing;
					Rectangle parentR = parentThingBB.getBoundingBox();
					thingR.x -= parentR.x;
					thingR.y -= parentR.y;
				}
			}
			if (cm != null) {
				BNAUtils.convertWorldToLocal(cm, thingR);
			}
			control.setBounds(BNAUtils.toSwtRectangle(thingR));
		}
	}

	protected void updateThingChildrenBoundingBoxes(Control parentControl, IThing parentThing) {
		assert parentControl != null;
		assert parentThing != null;

		if (parentThing instanceof IHasBoundingBox) {
			IHasBoundingBox parentThingBB = (IHasBoundingBox) parentThing;
			Rectangle parentThingR = parentThingBB.getBoundingBox();
			Rectangle parentControlR = BNAUtils.toRectangle(parentControl.getBounds());
			for (String childThingID : rtl.getThingsReferencing(parentThing.getID(),
					IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY)) {
				final Control control;
				final IThing thing = bnaWorld.getBNAModel().getThing(childThingID);
				final Mapping mapping;
				synchronized (lock) {
					if (!initialized) {
						return;
					}
					mapping = thingMappings.get(thing.getID());
					control = mapping == null ? null : mapping.control;
				}
				if (control != null && thing != null) {
					updateThingBoundingBox(control, thing);
					updateThingChildrenBoundingBoxes(control, thing);
				}
				// if (thing instanceof IHasMutableBoundingBox) {
				// IHasMutableBoundingBox childThingBB = (IHasMutableBoundingBox) thing;
				// if (control != null) {
				// Rectangle childThingR = control.getBounds();
				// childThingR.x += parentControlR.x;
				// childThingR.y += parentControlR.y;
				// if (cm != null)
				// BNAUtils.convertLocalToWorld(cm, childThingR);
				// childThingBB.setBoundingBox(childThingR);
				// }
				// }
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {

		switch (evt.getEventType()) {
		case THING_CHANGED:
			if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(evt.getThingEvent().getPropertyName())) {
				final Control control;
				final IThing thing = evt.getThingEvent().getTargetThing();
				final Mapping mapping;
				synchronized (lock) {
					if (!initialized) {
						return;
					}
					mapping = thingMappings.get(thing.getID());
					control = mapping == null ? null : mapping.control;
					if (control == null) {
						return;
					}
				}
				if (control != null) {
					SWTWidgetUtils.async(control, new Runnable() {

						public void run() {
							try {
								if (mapping.shouldUpdateControl()) {
									updateControlBoundingBox(control, thing);
								}
								updateThingChildrenBoundingBoxes(control, thing);
							}
							catch (Throwable t) {
								t.printStackTrace();
							}

						}
					});
				}
			}
			break;

		default:
		}

	}

	public void controlMoved(ControlEvent e) {
		if (bnaWorld != null) {
			final Control control = (Control) e.getSource();
			final IThing thing;
			final Mapping mapping;
			synchronized (lock) {
				if (!initialized) {
					return;
				}
				mapping = controlMappings.get(control);
				thing = mapping == null ? null : bnaWorld.getBNAModel().getThing(mapping.thingID);
				if (thing == null) {
					return;
				}
			}

			IBNAModel model = bnaWorld.getBNAModel();
			model.beginBulkChange();
			try {
				if (mapping.shouldUpdateThing()) {
					updateThingBoundingBox(control, thing);
				}
				updateThingChildrenBoundingBoxes(control, thing);
			}
			finally {
				model.endBulkChange();
			}
		}
	}

	public void controlResized(ControlEvent e) {
		if (bnaWorld != null) {
			final Control control = (Control) e.getSource();
			final IThing thing;
			final Mapping mapping;
			synchronized (lock) {
				if (!initialized) {
					return;
				}
				mapping = controlMappings.get(control);
				thing = mapping == null ? null : bnaWorld.getBNAModel().getThing(mapping.thingID);
				if (thing == null) {
					return;
				}
			}

			IBNAModel model = bnaWorld.getBNAModel();
			model.beginBulkChange();
			try {
				if (mapping.shouldUpdateThing()) {
					updateThingBoundingBox(control, thing);
				}
			}
			finally {
				model.endBulkChange();
			}
		}
	}

	public Control getControl(String thingID) {
		synchronized (lock) {
			if (!initialized) {
				return null;
			}
			Mapping mapping = thingMappings.get(thingID);
			return mapping == null ? null : mapping.control;
		}
	}

	public Composite getComposite(String thingID) {
		synchronized (lock) {
			if (!initialized) {
				return null;
			}
			Mapping mapping = thingMappings.get(thingID);
			return mapping == null ? null : (Composite) mapping.control;
		}
	}
}
