package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.ImageData;

public interface IHasImage {

	public static final IThingKey<String> IMAGE_PATH_KEY = ThingKey.create("imagePath");
	public static final IThingKey<ImageData> IMAGE_DATA_KEY = ThingKey.create("imageData");

	/**
	 * @deprecated Use {@link #getImageData()} instead.
	 */
	@Deprecated
	public String getImagePath();

	public ImageData getImageData();
}
