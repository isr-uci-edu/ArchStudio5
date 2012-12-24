package org.archstudio.archedit.core;

import java.io.IOException;

import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Image;

/**
 * Myx brick: "ArchEdit Component Impl"
 * 
 * @see org.archstudio.archedit.core.ArchEditMyxComponentStub
 * @generated
 */
public class ArchEditMyxComponent extends org.archstudio.archedit.core.ArchEditMyxComponentStub {
	public static final String EDITOR_NAME = "ArchEdit";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archedit.core.ArchEditEditor";

	public static final String IMAGE_ARCHEDIT_ICON = "archedit:icon";

	public ArchEditMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	boolean resourcesCreated = false;

	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHEDIT_ICON,
						SystemUtils.blt(ArchEditMyxComponent.class.getResourceAsStream("res/archedit-icon-32.gif")));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}

	@Override
	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "A syntax-directed editor for architecture descriptions",
				getIcon(), ILaunchData.LaunchType.EDITOR);
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_ARCHEDIT_ICON);
	}
}