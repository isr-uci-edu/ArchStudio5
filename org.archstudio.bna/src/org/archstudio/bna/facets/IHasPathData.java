package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.PathData;

public interface IHasPathData extends IThing {

	public static final IThingKey<PathData> PATH_DATA_KEY = ThingKey.create("pathData");

	public abstract PathData getPathData();

}