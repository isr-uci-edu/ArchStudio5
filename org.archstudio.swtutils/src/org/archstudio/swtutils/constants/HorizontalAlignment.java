package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum HorizontalAlignment{
	LEFT,
	CENTER,
	RIGHT;
	
	public int toSWT(){
		switch(this){
			case LEFT:
				return SWT.LEFT;
			case CENTER:
				return SWT.CENTER;
			case RIGHT:
				return SWT.RIGHT;
			default:
				return SWT.CENTER;
		}
	}
}
