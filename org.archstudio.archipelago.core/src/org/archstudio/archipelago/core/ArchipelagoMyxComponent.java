package org.archstudio.archipelago.core;

import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

import org.archstudio.EclipseUtils;
import org.archstudio.editors.AbstractArchstudioEditorMyxComponent;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

public class ArchipelagoMyxComponent extends AbstractArchstudioEditorMyxComponent{

	public static final String EDITOR_NAME = "Archipelago";
	public static final String ECLIPSE_EDITOR_ID = "org.archstudio.archipelago.core.ArchipelagoEditor";
	
	public static final String URL_BASE = "platform:/plugin/org.archstudio.archipelago.core/";

	public static final String IMAGE_ARCHIPELAGO_ICON = "archipelago:icon";
	public static final String URL_ARCHIPELAGO_ICON = URL_BASE + "res/archipelago-icon-32.gif";

	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_OUT_GRAPHLAYOUT = MyxUtils.createName("graphlayout");
	
	protected IPreferenceStore prefs = null;
	protected IGraphLayout graphLayout = null;
	
	public ArchipelagoMyxComponent(){
		super(EDITOR_NAME, ECLIPSE_EDITOR_ID, true);
	}
	
	public void begin(){
		super.begin();
		prefs = (IPreferenceStore)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_PREFERENCES);
		graphLayout = (IGraphLayout)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_GRAPHLAYOUT);
	}

	boolean resourcesCreated = false;
	private synchronized void createResources() {
		if (!resourcesCreated) {
			try {
				resources.createImage(IMAGE_ARCHIPELAGO_ICON, EclipseUtils.getBytes(URL_ARCHIPELAGO_ICON));
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException("This shouldn't happen.");
			}
			resourcesCreated = true;
		}
	}
	
	public IPreferenceStore getPreferences(){
		return prefs;
	}
	
	public IGraphLayout getGraphLayout(){
		return graphLayout;
	}
	
	public Image getIcon(){
		createResources();
		return resources.getImage(IMAGE_ARCHIPELAGO_ICON);
	}
	
	public ILaunchData getLaunchData(){
		return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "A graphical architecture editor", getIcon(), ILaunchData.LaunchType.EDITOR);
	}
	
}
