package org.archstudio.bna.things.swt;

import java.util.Arrays;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class SWTComboThingPeer<T extends SWTComboThing> extends AbstractSWTControlThingPeer<T, Combo> {

	public SWTComboThingPeer(T thing) {
		super(thing);
	}

	/* Hack to fix a swing bug about setting bounds */
	private org.eclipse.swt.graphics.Rectangle lastBounds = null;

	@Override
	protected void createControl(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res, Composite composite) {
		lastBounds = null;
		control = new Combo(composite, SWT.BORDER | SWT.FLAT | SWT.SINGLE
				| (t.getAllowsArbitraryInput() ? 0 : SWT.READ_ONLY));

		String[] options = t.getOptions();
		String text = t.getText();
		int selectedIndex = Arrays.asList(options).indexOf(text);
		if (t.getAllowsArbitraryInput()) {
			if (text != null) {
				if (selectedIndex == -1) {
					String[] newOptions = new String[options.length + 1];
					newOptions[0] = text;
					System.arraycopy(options, 0, newOptions, 1, options.length);
					options = newOptions;
					selectedIndex = 0;
				}
			}
		}
		control.setItems(options);
		if (selectedIndex != -1) {
			control.select(selectedIndex);
		}
		control.pack();

		updateControl(view, g, clip, res, composite);

		control.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!t.getAllowsArbitraryInput()) {
					finish();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if (t.getAllowsArbitraryInput()) {
					finish();
				}
			}

			protected void finish() {
				String text = control.getText();
				t.setText(text);
				t.setCompletionStatus(CompletionStatus.OK);
				t.setEditing(false);
			}
		});
		control.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.character == SWT.ESC) {
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
			int minWidth = gc.textExtent(control.getText()).x;
			for (String text : control.getItems()) {
				minWidth = Math.max(minWidth, gc.textExtent(text).x);
			}
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
