package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.ImageData;

public interface IHasMutableImage extends IHasImage {

	public void setImageData(ImageData imageData);
}
