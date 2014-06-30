package org.archstudio.bna.ui.utils;

import java.awt.image.BufferedImage;

import javax.media.opengl.GLProfile;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.ui.jogl.JOGLBNAUI;
import org.archstudio.bna.ui.swt.SWTBNAUI;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class AutodetectBNAUI implements IBNAUI {

	protected IBNAUI bnaUI;

	public AutodetectBNAUI(IBNAView view) {
		IBNAUI bnaUI;
		if (!GLProfile.getGL2ES2().isHardwareRasterizer()) {
			bnaUI = new SWTBNAUI(view);
		}
		else {
			bnaUI = new JOGLBNAUI(view);
		}
		this.bnaUI = bnaUI;
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
