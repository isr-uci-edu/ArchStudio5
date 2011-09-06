package org.archstudio.archipelago.core;

import java.io.IOException;

import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Image;

/**
 * Myx brick: "Archipelago Impl"
 * 
 * @see org.archstudio.archipelago.core.ArchipelagoMyxComponentStub
 * @generated
 */
public class ArchipelagoMyxComponent extends org.archstudio.archipelago.core.ArchipelagoMyxComponentStub {

	public static final String EDITOR_NAME = "Archipelago";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archipelago.core.ArchipelagoEditor";

	public static final String IMAGE_ARCHIPELAGO_ICON = "archipelago:icon";

	public ArchipelagoMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	boolean resourcesCreated = false;

	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHIPELAGO_ICON, SystemUtils.blt(ArchipelagoMyxComponent.class
						.getResourceAsStream("res/archipelago-icon-32.gif")));
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
		return resources.getImage(IMAGE_ARCHIPELAGO_ICON);
	}

	@Override
	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "A graphical architecture editor", getIcon(),
				ILaunchData.LaunchType.EDITOR);
	}
}