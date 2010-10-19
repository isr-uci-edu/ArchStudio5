package org.archstudio.bna;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.swtutils.constants.FontStyle;

public class ResourceUtils {
	protected static FontRegistry getFontRegistry(Display display) {
		FontRegistry fr = (FontRegistry) display.getData("BNA:ResourceUtils:FontRegistry");
		if (fr == null) {
			fr = new FontRegistry(display);
			display.setData("BNA:ResourceUtils:FontRegistry", fr);
		}
		return fr;
	}

	protected static ColorRegistry getColorRegistry(Display display) {
		ColorRegistry cr = (ColorRegistry) display.getData("BNA:ResourceUtils:ColorRegistry");
		if (cr == null) {
			cr = new ColorRegistry(display);
			display.setData("BNA:ResourceUtils:ColorRegistry", cr);
		}
		return cr;
	}

	public static Font getFont(Display display, String name, int size, FontStyle style) {
		if (name.equals(IFontConstants.DEFAULT_FONT_NAME)) {
			Font defaultFont = display.getSystemFont();
			name = defaultFont.getFontData()[0].getName();
		}
		String fontID = toFontID(name, size);

		FontRegistry fr = getFontRegistry(display);
		Font f = getFont(fr, fontID, style);
		if (f == null) {
			int styleint = 0;
			if (style.equals(FontStyle.NORMAL))
				styleint = SWT.NORMAL;
			else if (style.equals(FontStyle.BOLD))
				styleint = SWT.BOLD;
			else if (style.equals(FontStyle.ITALIC))
				styleint = SWT.ITALIC;
			else
				styleint = SWT.NORMAL;

			FontData fd = new FontData(name, size, styleint);
			fr.put(fontID, new FontData[] { fd });
			f = getFont(fr, fontID, style);
		}
		return f;
	}

	protected static Font getFont(FontRegistry fr, String fontID, FontStyle style) {
		if (!fr.hasValueFor(fontID))
			return null;
		if (style.equals(FontStyle.NORMAL)) {
			return fr.get(fontID);
		}
		else if (style.equals(FontStyle.BOLD)) {
			return fr.getBold(fontID);
		}
		else if (style.equals(FontStyle.ITALIC)) {
			return fr.getItalic(fontID);
		}
		else
			return fr.get(fontID);
	}

	protected static String toFontID(String name, int size) {
		StringBuffer sb = new StringBuffer();
		sb.append(name.toUpperCase());
		sb.append("$_$");
		sb.append(size);
		return sb.toString();
	}

	public static Color getColor(Display d, RGB rgb) {
		if (rgb == null)
			return null;
		ColorRegistry cr = getColorRegistry(d);
		String id = toColorID(rgb);
		Color c = cr.get(id);
		if (c == null) {
			cr.put(id, rgb);
		}
		return cr.get(id);
	}

	protected static String toColorID(RGB rgb) {
		StringBuffer sb = new StringBuffer(6);
		sb.append(Integer.toHexString(rgb.red));
		sb.append(Integer.toHexString(rgb.green));
		sb.append(Integer.toHexString(rgb.blue));
		return sb.toString();
	}

}
