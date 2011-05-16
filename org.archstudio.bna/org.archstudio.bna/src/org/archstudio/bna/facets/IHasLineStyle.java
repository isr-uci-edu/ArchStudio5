package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.SWT;

public interface IHasLineStyle extends IThing {

	public static final IThingKey<Integer> LINE_STYLE_KEY = ThingKey.create("lineStyle");

	public static final int LINE_STYLE_SOLID = SWT.LINE_SOLID;
	public static final int LINE_STYLE_DASH = SWT.LINE_DASH;
	public static final int LINE_STYLE_DOT = SWT.LINE_DOT;
	public static final int LINE_STYLE_DASHDOT = SWT.LINE_DASHDOT;
	public static final int LINE_STYLE_DASHDOTDOT = SWT.LINE_DASHDOTDOT;

	public int getLineStyle();
}
