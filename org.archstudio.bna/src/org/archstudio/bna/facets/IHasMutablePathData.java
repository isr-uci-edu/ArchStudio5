package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.PathData;

import org.archstudio.bna.IThing;

public interface IHasMutablePathData extends IThing, IHasPathData {

	public void setPathData(PathData pathData);

}