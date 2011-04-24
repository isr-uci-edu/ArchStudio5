package org.archstudio.aimlauncher.core;

import java.io.IOException;

import org.archstudio.aim.common.IAIM;
import org.archstudio.common.EclipseUtils;
import org.archstudio.editors.common.AbstractArchstudioEditorMyxComponent;
import org.archstudio.launcher.common.ILaunchData;
import org.archstudio.launcher.common.LaunchData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.swt.graphics.Image;

public class AIMLauncherMyxComponent extends AbstractArchstudioEditorMyxComponent {

	public static final String EDITOR_NAME = "AIM Launcher";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.aimlauncher.core.AIMLauncherEditor";

	public static final String URL_BASE = "platform:/plugin/org.archstudio.aimlauncher.core/";

	public static final String IMAGE_AIMLAUNCHER_ICON = "aimlauncher:icon";
	public static final String URL_AIMLAUNCHER_ICON = URL_BASE + "res/aimlauncher-icon-32.gif";
	public static final String IMAGE_AIMLAUNCHER_GO_ICON = "aimlauncher:go";
	public static final String URL_AIMLAUNCHER_GO_ICON = URL_BASE + "res/icon-go.gif";
	public static final String IMAGE_AIMLAUNCHER_STOP_ICON = "aimlauncher:stop";
	public static final String URL_AIMLAUNCHER_STOP_ICON = URL_BASE + "res/icon-stop.gif";

	public static final IMyxName INTERFACE_NAME_OUT_AIM = MyxUtils.createName("aim");

	protected IAIM aim = null;

	public AIMLauncherMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}

	public void begin() {
		super.begin();
		aim = (IAIM) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_AIM);
	}
	
	boolean resourcesCreated = false;
	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_AIMLAUNCHER_ICON, EclipseUtils.getBytes(URL_AIMLAUNCHER_ICON));
				resources.createImage(IMAGE_AIMLAUNCHER_GO_ICON, EclipseUtils.getBytes(URL_AIMLAUNCHER_GO_ICON));
				resources.createImage(IMAGE_AIMLAUNCHER_STOP_ICON, EclipseUtils.getBytes(URL_AIMLAUNCHER_STOP_ICON));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}
	
	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "Architecture Instantiation Manager", getIcon(), ILaunchData.LaunchType.EDITOR);
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_AIMLAUNCHER_ICON);
	}

	public IAIM getAIM() {
		return aim;
	}
}
