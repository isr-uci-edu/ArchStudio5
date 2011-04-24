package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.PathData;

import org.archstudio.bna.IThing;

public interface IHasPathData extends IThing {

	public static final String PATH_DATA_PROPERTY_NAME = "pathData";

	public abstract PathData getPathData();

}