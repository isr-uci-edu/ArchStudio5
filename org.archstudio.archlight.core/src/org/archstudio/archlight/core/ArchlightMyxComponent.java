package org.archstudio.archlight.core;

import java.io.IOException;

import org.archstudio.archlight.common.IArchlightTool;
import org.archstudio.common.EclipseUtils;
import org.archstudio.editors.common.AbstractArchstudioEditorMyxComponent;
import org.archstudio.launcher.common.ILaunchData;
import org.archstudio.launcher.common.LaunchData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.testadt.common.IArchlightTestADT;
import org.eclipse.swt.graphics.Image;

public class ArchlightMyxComponent extends AbstractArchstudioEditorMyxComponent {

	public static final String EDITOR_NAME = "Archlight";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archlight.core.ArchlightEditor";

	public static final String URL_BASE = "platform:/plugin/org.archstudio.archlight.core/";
	public static final String IMAGE_ARCHLIGHT_ICON = "archlight:icon";
	public static final String URL_ARCHLIGHT_ICON = "res/archlight-icon-32.gif";

	public static final IMyxName INTERFACE_NAME_OUT_TESTADT = MyxUtils.createName("archlighttestadt");
	public static final IMyxName INTERFACE_NAME_OUT_TOOLS = MyxUtils.createName("archlighttools");

	protected IArchlightTestADT testadt = null;
	protected IArchlightTool tools = null;

	public ArchlightMyxComponent() {
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, false);
	}

	public void begin() {
		super.begin();

		testadt = (IArchlightTestADT) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_TESTADT);
		tools = (IArchlightTool) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_TOOLS);
	}

	boolean resourcesCreated = false;
	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHLIGHT_ICON, EclipseUtils.getBytes(URL_BASE + URL_ARCHLIGHT_ICON));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}
	
	public IArchlightTestADT getTestADT() {
		return testadt;
	}

	public IArchlightTool getTools() {
		return tools;
	}

	public Image getIcon() {
		createResources();
		return resources.getImage(IMAGE_ARCHLIGHT_ICON);
	}

	public ILaunchData getLaunchData() {
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "An Architecture Analysis Framework", getIcon(), ILaunchData.LaunchType.EDITOR);
	}
}
