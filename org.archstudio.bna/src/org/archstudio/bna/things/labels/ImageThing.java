package org.archstudio.bna.things.labels;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasImage;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableImage;
import org.archstudio.bna.facets.IHasMutableScaled;
import org.archstudio.bna.facets.IHasMutableVerticalAlignment;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.swt.graphics.ImageData;

public class ImageThing extends AbstractRectangleThing implements IHasMutableHorizontalAlignment,
		IHasMutableVerticalAlignment, IHasMutableImage, IHasMutableScaled {

	public ImageThing() {
		this(null);
	}

	public ImageThing(Object id) {
		super(id);
		this.addThingListener(new IThingListener() {
			public void thingChanged(ThingEvent thingEvent) {
				if (IHasImage.IMAGE_PATH_KEY.equals(thingEvent.getPropertyName())) {
					String imagePath = getImagePath();
					ImageData imageData = null;
					if (imagePath != null) {
						try {
							imageData = new ImageData(imagePath);
						}
						catch (Exception e) {
						}
					}
					setImageData(imageData);
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setImagePath(null);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setScaled(false);
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return get(HORIZONTAL_ALIGNMENT_KEY);
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		set(HORIZONTAL_ALIGNMENT_KEY, horizontalAlignment);
	}

	public VerticalAlignment getVerticalAlignment() {
		return get(VERTICAL_ALIGNMENT_KEY);
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		set(VERTICAL_ALIGNMENT_KEY, verticalAlignment);
	}

	@Deprecated
	public String getImagePath() {
		return get(IMAGE_PATH_KEY);
	}

	@Deprecated
	public void setImagePath(String s) {
		set(IMAGE_PATH_KEY, s);
	}

	public ImageData getImageData() {
		return get(IHasImage.IMAGE_DATA_KEY);
	}

	public void setImageData(ImageData imageData) {
		set(IHasImage.IMAGE_DATA_KEY, imageData);
	}

	public boolean isScaled() {
		return get(SCALED_KEY);
	}

	public void setScaled(boolean scaled) {
		set(SCALED_KEY, scaled);
	}
}
