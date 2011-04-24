package org.archstudio.resources.common;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.swtutils.OverlayImageIcon;

public interface IResources {
	public static final int TOP_LEFT = OverlayImageIcon.TOP_LEFT;
	public static final int TOP_RIGHT = OverlayImageIcon.TOP_RIGHT;
	public static final int BOTTOM_LEFT = OverlayImageIcon.BOTTOM_LEFT;
	public static final int BOTTOM_RIGHT = OverlayImageIcon.BOTTOM_RIGHT;

	public static final String PLATFORM_BANNER_FONT_ID = JFaceResources.BANNER_FONT;
	public static final String PLATFORM_DEFAULT_FONT_ID = JFaceResources.DEFAULT_FONT;
	public static final String PLATFORM_DIALOG_FONT_ID = JFaceResources.DIALOG_FONT;
	public static final String PLATFORM_HEADER_FONT_ID = JFaceResources.HEADER_FONT;
	public static final String PLATFORM_TEXT_FONT_ID = JFaceResources.TEXT_FONT;

	public static final String COLOR_ARCHSTUDIO = "archstudio:main";
	public static final String COLOR_BANNER_BRIGHT = "archstudio:banner/bright";
	public static final String COLOR_BANNER_DARK = "archstudio:banner/dark";

	public static final RGB RGB_BANNER_BRIGHT = new RGB(58, 102, 153);
	public static final RGB RGB_BANNER_DARK = new RGB(45, 79, 117);

	public static final RGB RGB_ARCHSTUDIO_MAIN = new RGB(52, 91, 135);

	public void createColor(String symbolicName, RGB colorData);

	public Color getColor(String symbolicName);

	public void createImage(String symbolicName, byte[] bytes);

	public void createImage(String symbolicName, Image image);

	public void createOverlayImage(String symbolicName, Image base, Image[] overlays, int[] overlayPositions);

	public ImageDescriptor getPlatformImageDescriptor(String symbolicName);

	public Image getPlatformImage(String symbolicName);

	public ImageDescriptor getImageDescriptor(String symbolicName);

	public Image getImage(String symbolicName);

	public Font getPlatformFont(String symbolicName);

	public Font getBoldPlatformFont(String symbolicName);

	public Font getItalicPlatformFont(String symbolicName);

	public void createFont(String symbolicName, FontData[] fontData);

	public void createDerivedFont(String newSymbolicName, FontData[] existingFontData, int newHeight, int newStyle);

	public Font getFont(String symbolicName);

}