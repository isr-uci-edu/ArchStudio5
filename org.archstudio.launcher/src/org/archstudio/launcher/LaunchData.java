package org.archstudio.launcher;

import org.eclipse.swt.graphics.Image;

public class LaunchData implements ILaunchData {
	protected Image icon = null;
	protected String name = null;
	protected String description = null;
	protected String eclipseID = null;
	protected LaunchType launchType = LaunchType.VIEW;

	public LaunchData(String eclipseID, String name, String description, Image icon, LaunchType launchType) {
		super();
		this.eclipseID = eclipseID;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.launchType = launchType;
	}

	@Override
	public String getEclipseID() {
		return eclipseID;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Image getIcon() {
		return icon;
	}

	@Override
	public LaunchType getLaunchType() {
		return launchType;
	}

	@Override
	public String getName() {
		return name;
	}

}
