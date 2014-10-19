package org.archstudio.bna.ui.utils;

import java.awt.image.BufferedImage;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.ui.swt.SWTBNAUI;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class AutodetectBNAUI implements IBNAUI {

	protected IBNAUI bnaUI;

	public AutodetectBNAUI(IBNAView view) {
		// this.bnaUI = null;
		// if (GLProfile.getGL2ES2().isHardwareRasterizer()) {
		// bnaUI = new JOGLBNAUI(view);
		// }
		// if (bnaUI == null){
		// // as a fall back, select SWT
		// bnaUI = new SWTBNAUI(view);
		// }
		bnaUI = new SWTBNAUI(view);
	}

	@Override
	public void init(Composite parent, int style) {
		bnaUI.init(parent, style);
	}

	@Override
	public void dispose() {
		bnaUI.dispose();
	}

	@Override
	public Composite getComposite() {
		return bnaUI.getComposite();
	}

	@Override
	public void forceFocus() {
		bnaUI.forceFocus();
	}

	@Override
	public void paint() {
		bnaUI.paint();
	}

	@Override
	public BufferedImage render(Rectangle localBounds) {
		return bnaUI.render(localBounds);
	}

}
