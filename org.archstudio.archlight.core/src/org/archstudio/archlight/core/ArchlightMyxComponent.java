package org.archstudio.archlight.core;

import java.io.IOException;

import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Image;

/**
 * Myx brick: "Archlight Impl"
 * 
 * @see org.archstudio.archlight.core.ArchlightMyxComponentStub
 * @generated
 */
public class ArchlightMyxComponent extends org.archstudio.archlight.core.ArchlightMyxComponentStub {

	public static final String EDITOR_NAME = "Archlight";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archlight.core.ArchlightEditor";

	public static final String IMAGE_ARCHLIGHT_ICON = "archlight:icon";

	public ArchlightMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, false);
	}

	boolean resourcesCreated = false;

	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHLIGHT_ICON,
						SystemUtils.blt(ArchlightMyxComponent.class.getResourceAsStream("res/archlight-icon-32.gif")));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_ARCHLIGHT_ICON);
	}

	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "An Architecture Analysis Framework", getIcon(),
				ILaunchData.LaunchType.EDITOR);
	}
}