package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;

public class SWTTextThingPeer<T extends SWTTextThing> extends AbstractControlThingPeer<T, Text> {

	public SWTTextThingPeer(T thing) {
		super(thing);
	}

	protected Text createControl(final IBNAView view, ICoordinateMapper cm) {
		final Text control = new Text(view.getComposite(), SWT.BORDER | SWT.FLAT | SWT.SINGLE);
		control.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent e) {
				remove(view);
			}
		});
		control.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				if (e.character == SWT.CR) {
					t.setText(control.getText());
					remove(view);
				}
				else if (e.character == SWT.ESC) {
					remove(view);
				}
			}
		});
		control.setText(t.getText());
		control.selectAll();
		control.forceFocus();
		return control;
	}

	protected Rectangle getBounds(Text control, IBNAView view, ICoordinateMapper cm) {
		Rectangle bounds = super.getBounds(control, view, cm);

		GC gc = null;
		try {
			gc = new GC(control.getDisplay());
			gc.setFont(control.getFont());
			int minHeight = gc.getFontMetrics().getHeight();
			int minWidth = gc.textExtent(control.getText()).x + gc.getFontMetrics().getAverageCharWidth() * 4;
			org.eclipse.swt.graphics.Rectangle newBounds = control.computeTrim(bounds.x, bounds.y, minWidth, minHeight);
			bounds.width = Math.max(newBounds.width, bounds.width);
			bounds.height = Math.max(newBounds.height, bounds.height);
		}
		finally {
			SWTWidgetUtils.quietlyDispose(gc);
		}

		return bounds;
	}
}