package org.archstudio.archedit.core;

import java.io.IOException;

import org.archstudio.common.EclipseUtils;
import org.archstudio.editors.common.AbstractArchstudioEditorMyxComponent;
import org.archstudio.launcher.common.ILaunchData;
import org.archstudio.launcher.common.LaunchData;
import org.eclipse.swt.graphics.Image;

public class ArchEditMyxComponent extends AbstractArchstudioEditorMyxComponent {
	public static final String EDITOR_NAME = "ArchEdit";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archedit.core.ArchEditEditor";

	public static final String URL_BASE = "platform:/plugin/org.archstudio.archedit.core/";

	public static final String IMAGE_ARCHEDIT_ICON = "archedit:icon";
	public static final String URL_ARCHEDIT_ICON = URL_BASE + "res/archedit-icon-32.gif";

	public ArchEditMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	boolean resourcesCreated = false;
	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHEDIT_ICON, EclipseUtils.getBytes(URL_ARCHEDIT_ICON));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}

	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "A syntax-directed editor for architecture descriptions", getIcon(), ILaunchData.LaunchType.EDITOR);
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_ARCHEDIT_ICON);
	}
}
