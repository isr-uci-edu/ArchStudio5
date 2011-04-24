package org.archstudio.bna.things.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;

public class SWTComboThingPeer extends AbstractThingPeer {

	protected SWTComboThing lt;
	protected Combo control = null;

	public static final int CONTROL_WIDTH = 160;
	public static final int CONTROL_HEIGHT = 22;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public SWTComboThingPeer(IThing t) {
		super(t);
		if (!(t instanceof SWTComboThing)) {
			throw new IllegalArgumentException("SWTComboThingPeer can only peer for SWTComboThing");
		}
		this.lt = (SWTComboThing) t;
		lt.addThingListener(new IThingListener() {
			public void thingChanged(ThingEvent thingEvent) {
				if (!lt.isEditing() && (control != null)) {
					if (!control.isDisposed()) {
						control.dispose();
						control = null;
					}
				}
			}
		});
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Point p = lt.getAnchorPoint();
		Point lp = BNAUtils.worldToLocal(cm, p);
		localBoundingBox = new Rectangle(lp.x - (CONTROL_WIDTH / 2), lp.y - (CONTROL_HEIGHT / 2), CONTROL_WIDTH, CONTROL_HEIGHT);
		lt.setProperty("#boundingBox", BNAUtils.localToWorld(cm, localBoundingBox));
		//localBoundingBox =  BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	/* Hack to fix a swing bug about setting bounds */
	private Rectangle lastBounds = null;

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());

		if (lt.isEditing() && (control == null)) {
			//It has been made visible but we have no corresponding control
			Composite composite = BNAUtils.getParentComposite(view);
			if (composite == null) {
				return;
			}

			int flags = SWT.BORDER | SWT.FLAT | SWT.SINGLE;
			HorizontalAlignment ha = lt.getHorizontalAlignment();
			switch (ha) {
			case LEFT:
				flags |= SWT.LEFT;
				break;
			case CENTER:
				flags |= SWT.CENTER;
				break;
			case RIGHT:
				flags |= SWT.RIGHT;
				break;
			}

			if (!lt.getAllowsArbitraryInput()) {
				flags |= SWT.READ_ONLY;
			}

			control = new Combo(composite, flags);
			lastBounds = null;

			//Set initial properties on the control

			String[] initialItems = lt.getOptions();
			String initialText = lt.getText();
			if (lt.getAllowsArbitraryInput()) {
				if (initialText != null) {
					int foundIndex = -1;
					for (int i = 0; i < initialItems.length; i++) {
						if (initialText.equals(initialItems[i])) {
							foundIndex = i;
							break;
						}
					}
					if (foundIndex == -1) {
						String[] newInitialItems = new String[initialItems.length + 1];
						newInitialItems[0] = initialText;
						System.arraycopy(initialItems, 0, newInitialItems, 1, initialItems.length);
						initialItems = newInitialItems;
						foundIndex = 0;
					}
					control.setItems(initialItems);
					control.select(foundIndex);
				}
			}
			else {
				control.setItems(initialItems);
				if (initialText != null) {
					for (int i = 0; i < initialItems.length; i++) {
						if (initialText.equals(initialItems[i])) {
							control.select(i);
							break;
						}
					}
				}
			}

			if (composite.isFocusControl())
				control.forceFocus();

			String fontName = lt.getFontName();
			int fontSize = lt.getFontSize();
			FontStyle fontStyle = lt.getFontStyle();
			Font f = ResourceUtils.getFont(getDisplay(), fontName, fontSize, fontStyle);
			control.setFont(f);

			RGB rgb = lt.getColor();
			if (rgb != null) {
				Color c = ResourceUtils.getColor(getDisplay(), rgb);
				control.setForeground(c);
			}
			else {
				control.setForeground(g.getDevice().getSystemColor(SWT.COLOR_BLACK));
			}

			control.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (!lt.getAllowsArbitraryInput()) {
						finish();
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					if (lt.getAllowsArbitraryInput()) {
						finish();
					}
				}

				protected void finish() {
					String text = control.getText();
					lt.setText(text);
					lt.setCompletionStatus(CompletionStatus.OK);
					lt.setEditing(false);
				}
			});
			control.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (e.character == SWT.ESC) {
						lt.setCompletionStatus(CompletionStatus.CANCEL);
						lt.setEditing(false);
					}
				}
			});
		}
		else if (!lt.isEditing() && (control != null) && !control.isDisposed()) {
			//It has been made invisible but we are still showing the control
			control.dispose();
			control = null;
			lastBounds = null;
		}

		//Update the control
		if ((control != null) && !control.isDisposed()) {
			if (!BNAUtils.nulleq(localBoundingBox, lastBounds)) {
				control.setBounds(localBoundingBox);
			}
			lastBounds = localBoundingBox;
		}

		//if(!g.getClipping().intersects(localBoundingBox)) return;
	}

	/*
	 * public Rectangle getLocalBoundingBox(IBNAContext context, GC g,
	 * ICoordinateMapper cm){ updateLocalBoundingBox(cm); return
	 * localBoundingBox; }
	 */

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
