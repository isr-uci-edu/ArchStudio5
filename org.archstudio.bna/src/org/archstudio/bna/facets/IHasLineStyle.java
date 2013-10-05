package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.LineStyle;

public interface IHasLineStyle extends IThing {

	public static final IThingKey<LineStyle> LINE_STYLE_KEY = ThingKey.create("lineStyle");

	public static final LineStyle LINE_STYLE_SOLID = LineStyle.SOLID;
	public static final LineStyle LINE_STYLE_DASH = LineStyle.DASH;
	public static final LineStyle LINE_STYLE_DOT = LineStyle.DOT;
	public static final LineStyle LINE_STYLE_DASHDOT = LineStyle.DASH_DOT;
	public static final LineStyle LINE_STYLE_DASHDOTDOT = LineStyle.DASH_DOT_DOT;

	public LineStyle getLineStyle();
}
