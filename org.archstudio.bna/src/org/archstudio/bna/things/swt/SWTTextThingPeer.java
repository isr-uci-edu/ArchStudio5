package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SWTTextThingPeer<T extends SWTTextThing> extends AbstractSWTControlThingPeer<T, Text> {

	public SWTTextThingPeer(T thing) {
		super(thing);
	}

	/* Hack to fix a swing bug about setting bounds */
	private org.eclipse.swt.graphics.Rectangle lastBounds = null;

	@Override
	protected void createControl(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res, Composite composite) {
		lastBounds = null;
		control = new Text(composite, SWT.BORDER | SWT.FLAT | SWT.SINGLE);

		String text = t.getText();
		if (text == null) {
			text = "";
		}
		control.setText(text);
		control.selectAll();
		control.pack();

		updateControl(view, g, clip, res, composite);

		control.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				t.setText(control.getText());
			}
		});
		control.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.character == SWT.CR) {
					t.setCompletionStatus(CompletionStatus.OK);
					t.setEditing(false);
				}
				else if (e.character == SWT.ESC) {
					t.setCompletionStatus(CompletionStatus.CANCEL);
					t.setEditing(false);
				}
			}
		});
	}

	@Override
	protected void updateControl(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res, Composite composite) {
		Point lap = BNAUtils.worldToLocal(view.getCoordinateMapper(), t.getAnchorPoint());
		GC gc = null;
		try {
			gc = new GC(control.getDisplay());
			org.eclipse.swt.graphics.Rectangle bounds = lastBounds != null ? lastBounds : control.getBounds();
			gc.setFont(control.getFont());
			int minHeight = gc.getFontMetrics().getHeight();
			int minWidth = gc.textExtent(control.getText()).x + gc.getFontMetrics().getAverageCharWidth() * 4;
			org.eclipse.swt.graphics.Rectangle newBounds = control.computeTrim(lap.x, lap.y, minWidth, minHeight);
			newBounds.width = Math.max(newBounds.width, bounds.width);
			newBounds.height = Math.max(newBounds.height, bounds.height);
			newBounds.x -= newBounds.width / 2;
			newBounds.y -= newBounds.height / 2;
			if (!BNAUtils.nulleq(lastBounds, newBounds)) {
				lastBounds = newBounds;
				control.setBounds(newBounds);
			}
		}
		finally {
			if (gc != null) {
				gc.dispose();
				gc = null;
			}
		}
	}
}
