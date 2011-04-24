package org.archstudio.launcher;

import org.eclipse.swt.graphics.Image;

public interface ILaunchData{
	
	public enum LaunchType {
		VIEW,
		EDITOR
	}
	
	public String getDescription();
	public Image getIcon();
	public LaunchType getLaunchType();
	public String getName();
	public String getEclipseID();
}