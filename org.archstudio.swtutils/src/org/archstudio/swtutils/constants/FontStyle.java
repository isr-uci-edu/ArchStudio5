package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum FontStyle{
	NORMAL,
	BOLD,
	ITALIC;
	
	public int toSWT(){
		switch(this){
			case NORMAL: return SWT.NORMAL;
			case BOLD: return SWT.BOLD;
			case ITALIC: return SWT.ITALIC;
		}
		return SWT.NORMAL;
	}
	
	public static FontStyle fromSWT(int style){
		switch(style){
			case SWT.NORMAL: return NORMAL;
			case SWT.BOLD: return BOLD;
			case SWT.ITALIC: return ITALIC;
		}
		throw new IllegalArgumentException("Invaid font style constant.");
	}
}