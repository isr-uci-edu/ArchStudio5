package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.ImageData;

public interface IHasMutableImage extends IHasImage {

	/**
	 * @deprecated Use {@link #setImageData(ImageData)} instead.
	 */
	@Deprecated
	public void setImagePath(String imagePath);

	public void setImageData(ImageData imageData);
}
