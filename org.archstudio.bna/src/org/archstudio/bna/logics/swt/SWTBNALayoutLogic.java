package org.archstudio.bna.logics.swt;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasSWTLayout;
import org.archstudio.bna.facets.IHasSWTLayoutData;

public class SWTBNALayoutLogic extends AbstractThingLogic implements IBNAModelListener {

	class ThingComposite extends Composite implements PaintListener {
		IThing thing;

		public ThingComposite(Composite parent, int style, IThing thing) {
			super(parent, style);
			this.thing = thing;
			this.addPaintListener(this);
		}

		@Override
		public Point computeSize(int wHint, int hHint, boolean changed) {
			return super.computeSize(wHint, hHint, changed);
		}

		@Override
		public Rectangle computeTrim(int x, int y, int width, int height) {
			return super.computeTrim(x, y, width, height);
		}

		public void paintControl(PaintEvent e) {
			GC g = e.gc;
			g.setForeground(g.getDevice().getSystemColor(SWT.COLOR_BLACK));
			g.drawRectangle(1, 1, getSize().x - 3, getSize().y - 3);
			g.drawText(Boolean.toString(bnaWorld.getBNAModel().getThing(thing.getID()) != null) + " " + thing.getID(), 2, 2);
		}
	}

	private static final String RELAYOUT = "relayout";

	Composite layoutComposite;

	SWTBNASynchLogic synchLogic;

	public SWTBNALayoutLogic(SWTBNASynchLogic synchLogic) {
		this.synchLogic = synchLogic;
	}

	@Override
	public void init() {
		super.init();

		Shell shell = new Shell();
		layoutComposite = new Composite(shell, SWT.NONE);
		//shell.setLayout(new FillLayout());
		//shell.setSize(400, 400);
		//shell.open();

		BNAUtils.asyncExec(layoutComposite, new Runnable() {
			public void run() {
				relayout();
			}
		});
	}

	private void relayout() {
		for (Control child : layoutComposite.getChildren())
			child.dispose();

		Set<String> layedOut = new HashSet<String>();

		for (IThing thing : bnaWorld.getBNAModel().getAllThings()) {
			relayout(thing, layedOut);
		}
	}

	private void relayout(IThing thing, Set<String> layedOut) {
		if (!layedOut.add(thing.getID()))
			return;

		String targetThingID = (String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME);

		if (targetThingID != null) {
			relayout(bnaWorld.getBNAModel().getThing(targetThingID), layedOut);
		}

		if (targetThingID != null) {
			updateLayoutContent(targetThingID, thing, (Object) thing.getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME), true);
		}
		if (thing.hasProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME)) {
			updateLayoutComposite(thing, (Layout) thing.getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME), true);
		}
	}

	public void bnaModelChanged(final BNAModelEvent evt) {
		switch (evt.getEventType()) {

		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			if (thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME) != null) {
				updateLayoutContent((String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME), thing, (Object) thing
				        .getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME), false);
			}
			if (thing.getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME) != null) {
				updateLayoutComposite(thing, (Layout) thing.getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME), false);
			}
		}
			break;

		case THING_CHANGED:
			if (IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME.equals(evt.getThingEvent().getPropertyName())) {
				IThing thing = evt.getTargetThing();
				updateLayoutComposite(thing, (Layout) thing.getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME), false);
			}
			else if (IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME.equals(evt.getThingEvent().getPropertyName())) {
				IThing thing = evt.getTargetThing();
				updateLayoutContent((String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME), thing, (Object) thing
				        .getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME), false);
			}
			else if (IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME.equals(evt.getThingEvent().getPropertyName())) {
				IThing thing = evt.getTargetThing();
				updateLayoutContent((String) thing.getProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME), thing, (Object) thing
				        .getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME), false);
			}
			break;

		case THING_REMOVED: {
			IThing thing = evt.getTargetThing();
			updateLayoutComposite(thing, null, false);
		}
			break;

		case STREAM_NOTIFICATION_EVENT:
			if (RELAYOUT.equals(evt.getStreamNotification())) {
				try {
					synchLogic.reset();
					BNAUtils.asyncExec(layoutComposite, new Runnable() {
						public void run() {
							relayout();
						}
					});
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}

		default:
		}
	}

	protected void updateLayoutComposite(final IThing thing, final Layout layout, boolean synch) {
		Runnable r = new Runnable() {
			public void run() {
				Composite composite = synchLogic.getComposite(thing.getID());
				if (layout == null) {
					if (composite != null && !composite.isDisposed())
						composite.dispose();
				}
				else {
					if (composite == null || composite.isDisposed()) {
						composite = new ThingComposite(layoutComposite, SWT.NONE, thing);
						synchLogic.addBNAToSWTMapping(thing.getID(), composite);
					}
					composite.setLayout(layout);
					composite.layout();
				}
			}
		};
		if (synch)
			BNAUtils.syncExec(layoutComposite, r);
		else
			BNAUtils.asyncExec(layoutComposite, r);
	}

	protected void updateLayoutContent(final String layoutTargetThingID, final IThing thing, final Object layoutData, boolean synch) {
		Runnable r = new Runnable() {
			public void run() {
				if (layoutComposite.isDisposed())
					return;
				if (layoutTargetThingID == null) {
					Control control = synchLogic.getControl(thing.getID());
					if (control != null && !control.isDisposed()) {
						//						Composite parentComposite = control.getParent();
						control.dispose();
						//						if (parentComposite != null)
						//							parentComposite.layout();
					}
				}
				else {
					Composite parentComposite = synchLogic.getComposite(layoutTargetThingID);
					if (parentComposite == null || parentComposite.isDisposed())
						return;
					if (parentComposite != null) {
						Control control = synchLogic.getControl(thing.getID());
						if (control == null || control.isDisposed()) {
							control = new ThingComposite(parentComposite, SWT.NONE, thing);
							synchLogic.addSWTToBNAMapping(control, thing.getID());
						}
						if (control.getParent() != parentComposite) {
							if (control.isReparentable() && control.setParent(parentComposite)) {
								Composite oldParent = control.getParent();
								control.setLayoutData(layoutData);
								parentComposite.layout();
								oldParent.layout();
							}
							else {
								// we must do this the hard way!
								synchLogic.destroy();
								BNAUtils.asyncExec(layoutComposite, new Runnable() {
									public void run() {
										if (bnaWorld != null)
											bnaWorld.getBNAModel().fireStreamNotificationEvent(RELAYOUT);
									}
								});
							}
						}
						else {
							control.setLayoutData(layoutData);
							parentComposite.layout();
						}
					}
				}
			}
		};
		if (synch)
			BNAUtils.syncExec(layoutComposite, r);
		else
			BNAUtils.asyncExec(layoutComposite, r);
	}
}
