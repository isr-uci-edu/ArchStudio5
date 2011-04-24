package org.archstudio.bna.facets;

import org.eclipse.swt.widgets.Layout;

import org.archstudio.bna.IThing;

public interface IHasSWTLayout extends IThing {
	public static final String SWT_LAYOUT_PROPERTY_NAME = "swtLayout";

	public Layout getSWTLayout();

	public void setSWTLayout(Layout layout);
}
