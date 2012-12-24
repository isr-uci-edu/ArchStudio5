package org.archstudio.aimlauncher.core;

import java.io.IOException;

import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Image;

/**
 * Myx brick: "AIM Launcher Component Impl"
 * 
 * @see org.archstudio.aimlauncher.core.AIMLauncherMyxComponentStub
 * @generated
 */
public class AIMLauncherMyxComponent extends org.archstudio.aimlauncher.core.AIMLauncherMyxComponentStub {

	public static final String EDITOR_NAME = "AIM Launcher";
	public static final String ECLIPSE_EDITOR_ID = AIMLauncherEditor.class.getName();

	public static final String IMAGE_AIMLAUNCHER_ICON = "aimlauncher:icon";
	public static final String IMAGE_AIMLAUNCHER_GO_ICON = "aimlauncher:go";
	public static final String IMAGE_AIMLAUNCHER_STOP_ICON = "aimlauncher:stop";

	public AIMLauncherMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	boolean resourcesCreated = false;

	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_AIMLAUNCHER_ICON, SystemUtils.blt(AIMLauncherMyxComponent.class
						.getResourceAsStream("res/aimlauncher-icon-32.gif")));
				resources.createImage(IMAGE_AIMLAUNCHER_GO_ICON,
						SystemUtils.blt(AIMLauncherMyxComponent.class.getResourceAsStream("res/icon-go.gif")));
				resources.createImage(IMAGE_AIMLAUNCHER_STOP_ICON,
						SystemUtils.blt(AIMLauncherMyxComponent.class.getResourceAsStream("res/icon-stop.gif")));

			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}

	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "Architecture Instantiation Manager", getIcon(),
				ILaunchData.LaunchType.EDITOR);
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_AIMLAUNCHER_ICON);
	}
}