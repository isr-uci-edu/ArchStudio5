package org.archstudio.rootpreferences.core;

import java.io.IOException;

import org.archstudio.common.EclipseUtils;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.resources.common.IResources;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchStudioPreferencePanel extends PreferencePage implements IWorkbenchPreferencePage{

	public static final String URL_BASE = "platform:/plugin/org.archstudio.rootpreferences.core/";

	public static final String IMAGE_PREFERENCES_BANNER = "archstudio:preferences/banner";
	public static final String URL_PREFERENCES_BANNER = URL_BASE + "res/banner.png";
	
	private RootPreferencesMyxComponent comp = null;
	private MyxRegistry er = MyxRegistry.getSharedInstance();
	
	protected IResources resources = null;
	protected IPreferenceStore preferences = null;
	
	public ArchStudioPreferencePanel(){
		super("ArchStudio Preferences");
		comp = (RootPreferencesMyxComponent)er.waitForBrick(RootPreferencesMyxComponent.class);
		er.map(comp, this);
		resources = comp.resources;
		preferences = comp.preferences;
		try{
			resources.createImage(IMAGE_PREFERENCES_BANNER, EclipseUtils.getBytes(URL_PREFERENCES_BANNER));
		}
		catch(IOException ioe){
			throw new RuntimeException("This shouldn't happen.");
		}
	}

	public void init(IWorkbench workbench){
	}

	protected Control createContents(Composite parent){
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(1, false));
		
		Label lBanner = new Label(c, SWT.BORDER);
		lBanner.setImage(resources.getImage(IMAGE_PREFERENCES_BANNER));
		
		Label lText = new Label(c, SWT.LEFT);
		lText.setText("Select a sub-node for options.");
		return c;
	}

}
