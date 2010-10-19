package org.archstudio.bna.things.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;

public class SWTTextThingPeer extends AbstractThingPeer {
	protected SWTTextThing lt;
	protected Text control = null;

	public static final int DEFAULT_CONTROL_WIDTH = 120;
	public static final int DEFAULT_CONTROL_HEIGHT = 20;

	protected int controlWidth = DEFAULT_CONTROL_WIDTH;
	protected int controlHeight = DEFAULT_CONTROL_HEIGHT;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public SWTTextThingPeer(IThing t) {
		super(t);
		if (!(t instanceof SWTTextThing)) {
			throw new IllegalArgumentException("SWTTextThingPeer can only peer for SWTTextThing");
		}
		this.lt = (SWTTextThing) t;

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
		localBoundingBox = new Rectangle(lp.x - (controlWidth / 2), lp.y - (controlHeight / 2), controlWidth, controlHeight);
		lt.setProperty("#boundingBox", BNAUtils.localToWorld(cm, localBoundingBox));
		//localBoundingBox =  BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

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
			control = new Text(composite, flags);

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

			control.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (e.character == SWT.CR) {
						lt.setText(control.getText());
						lt.setCompletionStatus(CompletionStatus.OK);
						lt.setEditing(false);
					}
					else if (e.character == SWT.ESC) {
						lt.setCompletionStatus(CompletionStatus.CANCEL);
						lt.setEditing(false);
					}
				}
			});

			//Set initial properties on the control
			String initialText = lt.getText();
			if (initialText == null)
				initialText = "";

			Font oldFont = g.getFont();
			g.setFont(f);
			int textWidth = g.textExtent(initialText).x + 20;
			if (textWidth > controlWidth) {
				controlWidth = textWidth;
			}
			g.setFont(oldFont);

			control.setBounds(localBoundingBox);
			control.setText(initialText);
			if (composite.isFocusControl())
				control.forceFocus();
			control.selectAll();
		}
		else if (!lt.isEditing() && (control != null)) {
			//It has been made invisible but we are still showing the control
			control.dispose();
			control = null;
		}

		//Update the control
		if ((control != null) && !control.isDisposed()) {
			control.setBounds(localBoundingBox);
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
