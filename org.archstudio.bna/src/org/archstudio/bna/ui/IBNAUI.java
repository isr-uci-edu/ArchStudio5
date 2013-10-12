package org.archstudio.bna.ui;

import java.awt.image.BufferedImage;

import org.archstudio.bna.ui.jogl.JOGLBNAUI;
import org.archstudio.bna.ui.swt.SWTBNAUI;
import org.archstudio.bna.ui.utils.AutodetectBNAUI;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public interface IBNAUI {

	public static enum AvailableUI {
		AUTODETECT("Autodetect", "Automatically select the best graphical subsystem.", AutodetectBNAUI.class), //
		JOGL("OpenGL", "Faster, but unstable on some systems.", JOGLBNAUI.class), //
		SWT("SWT", "Slower, use this if OpenGL does not work or causes JVM crashes.", SWTBNAUI.class);

		private final String name;
		private final String description;
		private final Class<? extends IBNAUI> bnaUIClass;

		private AvailableUI(String name, String description, Class<? extends IBNAUI> bnaUIClass) {
			this.name = name;
			this.description = description;
			this.bnaUIClass = bnaUIClass;
		}

		public Class<? extends IBNAUI> getBNAUIClass() {
			return bnaUIClass;
		}

		@Override
		public String toString() {
			return name + " - " + description;
		}
	}

	public void init(Composite parent, int style);

	public void dispose();

	public Composite getComposite();

	public void forceFocus();

	public void paint();

	public BufferedImage render(Rectangle localBounds);

}
