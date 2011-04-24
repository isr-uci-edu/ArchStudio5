package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum VerticalAlignment{
	TOP,
	MIDDLE,
	BOTTOM;
	
	public int toSWT(){
		switch(this){
			case TOP:
				return SWT.TOP;
			case MIDDLE:
				return SWT.CENTER;
			case BOTTOM:
				return SWT.BOTTOM;
			default:
				return SWT.CENTER;
		}
	}

}
