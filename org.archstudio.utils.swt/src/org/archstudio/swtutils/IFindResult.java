package org.archstudio.swtutils;

import org.eclipse.jface.resource.ImageDescriptor;

public interface IFindResult extends Comparable<IFindResult> {
	public String getString();

	public ImageDescriptor getImageDescriptor();

	public Object getData();
}
