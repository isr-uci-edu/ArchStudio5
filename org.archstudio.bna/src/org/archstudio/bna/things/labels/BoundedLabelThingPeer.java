package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jogamp.opengl.util.awt.TextRenderer;

public class BoundedLabelThingPeer<T extends BoundedLabelThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T> {

	public BoundedLabelThingPeer(T thing) {
		super(thing);
	}

	private static Set<Integer> getAllowableLinebreaks(String text) {
		Set<Integer> breaks = Sets.newHashSet(0, text.length());
		int index = 0;
		for (String s : text.split("\\s")) {
			index += s.length();
			breaks.add(index);
			index++;
			breaks.add(index);
		}
		breaks.remove(text.length() + 1);
		return breaks;
	}

	String text = null;
	Dimension size = null;
	Font font = null;
	List<String> lines = Lists.newArrayList();
	List<java.awt.font.TextLayout> textLayouts = Lists.newArrayList();
	float totalHeight = 0;

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		Point canvasSize = r.getComposite().getSize();
		if (t.getText().length() == 0) {
			text = "";
			font = null;
		}
		else if (!t.getText().equals(text) //
				|| font != null && !r.getFont(t, font.getSize()).equals(font)
				// leave a little tolerance for rounding of coordinates
				|| Math.abs(lbb.height - size.height) > 2 //
				|| Math.abs(lbb.width - size.width) > 2) {
			text = t.getText();
			size = new Dimension(lbb.width, lbb.height);
			int minFontSize = 1;
			int maxFontSize = t.getFontSize();
			Set<Integer> allowableLineBreaks = getAllowableLinebreaks(text);
			while (true) {
				int fontSize = (maxFontSize + minFontSize) / 2;
				font = r.getFont(t, fontSize);
				TextRenderer tr = r.getTextRenderer(font);
				Map<Attribute, Object> attrs = Maps.newHashMap();
				attrs.put(TextAttribute.FONT, font);
				AttributedString str = new AttributedString(text, attrs);
				LineBreakMeasurer measurer = new LineBreakMeasurer(str.getIterator(), tr.getFontRenderContext());
				lines.clear();
				textLayouts.clear();
				totalHeight = 0;
				if (lbb.width > 0 && lbb.height > 0) {
					while (measurer.getPosition() < text.length()) {
						if (!allowableLineBreaks.contains(measurer.getPosition())) {
							totalHeight += lbb.height + 1;
							break;
						}
						String line = text.substring(measurer.getPosition());
						java.awt.font.TextLayout tl = measurer.nextLayout(lbb.width);
						line = line.substring(0, tl.getCharacterCount());
						lines.add(line);
						textLayouts.add(tl);
						totalHeight += tl.getAscent() + tl.getDescent();
						if (totalHeight > lbb.height) {
							break;
						}
					}
				}
				if (minFontSize > maxFontSize) {
					if (maxFontSize < 1) {
						font = null;
					}
					break;
				}
				if (totalHeight > lbb.height) {
					maxFontSize = fontSize - 1;
				}
				else {
					minFontSize = fontSize + 1;
				}
			}
		}
		if (font != null) {
			TextRenderer tr = r.getTextRenderer(font);
			tr.beginRendering(canvasSize.x, canvasSize.y);
			if (r.setColor(t, IHasColor.COLOR_KEY)) {
				float y = lbb.y;
				switch (t.getVerticalAlignment()) {
				case BOTTOM:
					y += lbb.height - totalHeight;
					break;
				case MIDDLE:
					y += Math.floor((lbb.height - totalHeight) / 2);
					break;
				case TOP:
					break;
				}

				for (int i = 0; i < lines.size(); i++) {
					String line = lines.get(i);
					java.awt.font.TextLayout tl = textLayouts.get(i);
					int x = lbb.x;
					switch (t.getHorizontalAlignment()) {
					case RIGHT:
						x += lbb.width - tl.getBounds().getWidth();
						break;
					case CENTER:
						x += Math.floor((lbb.width - tl.getBounds().getWidth()) / 2);
						break;
					case LEFT:
						break;
					}
					tr.draw(line, BNAUtils.round(x), BNAUtils.round(canvasSize.y - (y + tl.getAscent())));
					y += tl.getAscent() + tl.getDescent();
				}
			}
			tr.endRendering();
		}
	}
}
