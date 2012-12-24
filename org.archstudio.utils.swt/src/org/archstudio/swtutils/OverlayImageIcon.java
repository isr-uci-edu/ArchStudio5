// package com.ibm.DecoratorDemo.ui.Decorators;
package org.archstudio.swtutils;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * @author Eric Dashofy, based on code from balajik@ibm
 * 
 *         This class is used for overlaying image icons
 */
public class OverlayImageIcon extends CompositeImageDescriptor {
	/**
	 * Base image of the object
	 */
	private Image baseImage_;

	/**
	 * Size of the base image
	 */
	private Point sizeOfImage_;

	/**
	 * Vector of image keys
	 */
	private int[] imageKey_;

	/**
	 * Image instance
	 */
	private Image[] overlayImage_;

	public static final int TOP_LEFT = 0;
	public static final int TOP_RIGHT = 1;
	public static final int BOTTOM_LEFT = 2;
	public static final int BOTTOM_RIGHT = 3;

	/**
	 * Constructor for overlayImageIcon.
	 */
	public OverlayImageIcon(Image baseImage, Image[] overlayImages, int[] imageKey) {
		// Base image of the object
		baseImage_ = baseImage;
		// Demo Image Object 
		overlayImage_ = overlayImages;
		imageKey_ = imageKey;
		sizeOfImage_ = new Point(baseImage.getBounds().width, baseImage.getBounds().height);
	}

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
	 *      int) DrawCompositeImage is called to draw the composite image.
	 * 
	 */
	@Override
	protected void drawCompositeImage(int arg0, int arg1) {
		// Draw the base image
		drawImage(baseImage_.getImageData(), 0, 0);
		for (int i = 0; i < overlayImage_.length; i++) {
			ImageData imageData = overlayImage_[i].getImageData();
			switch (imageKey_[i]) {
			// Draw on the top left corner
			case TOP_LEFT:
				drawImage(imageData, 0, 0);
				break;

			// Draw on top right corner  
			case TOP_RIGHT:
				drawImage(imageData, sizeOfImage_.x - imageData.width, 0);
				break;

			// Draw on bottom left  
			case BOTTOM_LEFT:
				drawImage(imageData, 0, sizeOfImage_.y - imageData.height);
				break;

			// Draw on bottom right corner  
			case BOTTOM_RIGHT:
				drawImage(imageData, sizeOfImage_.x - imageData.width, sizeOfImage_.y - imageData.height);
				break;

			}
		}

	}

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize() get
	 *      the size of the object
	 */
	@Override
	protected Point getSize() {
		return sizeOfImage_;
	}

	/**
	 * Get the image formed by overlaying different images on the base image
	 * 
	 * @return composite image
	 */
	public Image getImage() {
		return createImage();
	}

}
