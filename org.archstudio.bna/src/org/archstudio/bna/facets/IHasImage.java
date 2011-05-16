package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.ImageData;

public interface IHasImage {

	public static final ThingKey<String, V> IMAGE_PATH_KEY = ThingKey.createKey("imagePath");
	public static final ThingKey<ImageData, V> IMAGE_DATA_KEY = ThingKey.createKey("imageData");

	/**
	 * @deprecated Use {@link #getImageData()} instead.
	 */
	@Deprecated
	public String getImagePath();

	public ImageData getImageData();
}
