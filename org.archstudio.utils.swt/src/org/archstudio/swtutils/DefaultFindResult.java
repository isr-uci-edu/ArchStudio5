package org.archstudio.swtutils;

import org.eclipse.jface.resource.ImageDescriptor;

public class DefaultFindResult implements IFindResult {
	protected Object data = null;
	protected String s = null;
	protected ImageDescriptor image = null;

	public DefaultFindResult(Object data, String s, ImageDescriptor image) {
		this.data = data;
		this.s = s;
		this.image = image;
	}

	public Object getData() {
		return data;
	}

	public ImageDescriptor getImageDescriptor() {
		return image;
	}

	public String getString() {
		return s;
	}

	public int compareTo(IFindResult o) {
		return getString().compareToIgnoreCase(o.getString());
	}

}
