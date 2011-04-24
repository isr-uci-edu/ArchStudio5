package org.archstudio.bna.things.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasHorizontalAlignment;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasVerticalAlignment;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

public class BoxedLabelThingPeer extends AbstractThingPeer {

	protected BoxedLabelThing lt;

	private TextLayoutCache textLayoutCache = null;
	private TextLayoutCacheMaintainer textLayoutCacheMaintainer = null;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public BoxedLabelThingPeer(IThing t) {
		super(t);
		if (!(t instanceof BoxedLabelThing)) {
			throw new IllegalArgumentException("BoxedLabelThingPeer can only peer for BoxedLabelThing");
		}
		this.lt = (BoxedLabelThing) t;
		textLayoutCacheMaintainer = new TextLayoutCacheMaintainer();
		lt.addThingListener(new WeakThingListener(lt, textLayoutCacheMaintainer));
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	private class TextLayoutCacheMaintainer implements IThingListener {
		public void thingChanged(ThingEvent thingEvent) {
			if (textLayoutCache == null)
				return;
			String propertyName = thingEvent.getPropertyName();
			if (propertyName == null)
				return;
			if (propertyName.equals(IHasText.TEXT_PROPERTY_NAME) || propertyName.equals(IHasFontData.FONT_NAME_PROPERTY_NAME)
			        || propertyName.equals(IHasFontData.FONT_SIZE_PROPERTY_NAME) || propertyName.equals(IHasFontData.FONT_STYLE_PROPERTY_NAME)
			        || propertyName.equals(IHasVerticalAlignment.VERTICAL_ALIGNMENT_PROPERTY_NAME)
			        || propertyName.equals(IHasHorizontalAlignment.HORIZONTAL_ALIGNMENT_PROPERTY_NAME)
			        || propertyName.equals(BoxedLabelThing.DONT_INCREASE_FONT_SIZE_PROPERTY_NAME)) {
				textLayoutCache.dispose();
			}
		}
	}

	private static class TextLayoutCache {
		public TextLayout lastTextLayout = null;

		public int lastHeight = -1;
		public int lastWidth = -1;

		public int lastLabelHeight = -1;

		public TextLayoutCache(Display display) {
			display.disposeExec(new Runnable() {
				public void run() {
					TextLayoutCache.this.dispose();
				}
			});
		}

		public synchronized void dispose() {
			if (lastTextLayout != null) {
				if (!lastTextLayout.isDisposed()) {
					lastTextLayout.dispose();
				}
				lastTextLayout = null;
			}
		}

		public synchronized boolean hasLayoutFor(int width, int height) {
			return (lastTextLayout != null) && (width == lastWidth) && (height == lastHeight);
		}
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		//BNAComposite bnaComposite = getBNAComposite(context);

		if (textLayoutCache == null) {
			textLayoutCache = new TextLayoutCache(getDisplay());
		}

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}
		g.setForeground(fg);

		String text = lt.getText();
		String fontName = lt.getFontName();
		int fontSize = lt.getFontSize();
		FontStyle fontStyle = lt.getFontStyle();
		boolean dontIncreaseFontSize = lt.getDontIncreaseFontSize();

		VerticalAlignment verticalAlignment = lt.getVerticalAlignment();
		HorizontalAlignment horizontalAlignment = lt.getHorizontalAlignment();

		if ((textLayoutCache != null) && (textLayoutCache.hasLayoutFor(localBoundingBox.width, localBoundingBox.height))) {
			if (textLayoutCache.lastLabelHeight != 0) {
				TextLayout tl = textLayoutCache.lastTextLayout;
				int labelHeight = textLayoutCache.lastLabelHeight;
				if (verticalAlignment.equals(VerticalAlignment.TOP)) {
					tl.draw(g, localBoundingBox.x, localBoundingBox.y);
				}
				else if (verticalAlignment.equals(VerticalAlignment.MIDDLE)) {
					tl.draw(g, localBoundingBox.x, localBoundingBox.y + (localBoundingBox.height / 2) - (labelHeight / 2));
				}
				else /* BOTTOM */{
					tl.draw(g, localBoundingBox.x, localBoundingBox.y + (localBoundingBox.height) - (labelHeight));
				}
			}
			else {
				drawFakeLines(g);
			}
		}
		else {
			if (textLayoutCache != null) {
				textLayoutCache.dispose();
				textLayoutCache.lastWidth = localBoundingBox.width;
				textLayoutCache.lastHeight = localBoundingBox.height;
			}

			TextLayout tl = new TextLayout(g.getDevice());
			tl.setText(text);
			int tw = localBoundingBox.width;
			if (tw < 1)
				tw = 1;
			tl.setWidth(tw);
			tl.setAlignment(horizontalAlignment.toSWT());

			double scale = view.getCoordinateMapper().getScale();
			if ((!dontIncreaseFontSize) || ((dontIncreaseFontSize) && (scale < 1.0d))) {
				fontSize = BNAUtils.round((double) fontSize * scale);
			}

			if (textLayoutCache != null) {
				textLayoutCache.lastLabelHeight = 0;
			}

			Rectangle labelBounds = LabelUtils.setFontToRender(getDisplay(), tl, localBoundingBox.height, fontName, fontSize, fontStyle);
			if ((labelBounds != null) && (localBoundingBox.width > 5) && (labelBounds.height != 0)) {
				if (verticalAlignment.equals(VerticalAlignment.TOP)) {
					tl.draw(g, localBoundingBox.x, localBoundingBox.y);
				}
				else if (verticalAlignment.equals(VerticalAlignment.MIDDLE)) {
					tl.draw(g, localBoundingBox.x, localBoundingBox.y + (localBoundingBox.height / 2) - (labelBounds.height / 2));
				}
				else /* BOTTOM */{
					tl.draw(g, localBoundingBox.x, localBoundingBox.y + (localBoundingBox.height) - (labelBounds.height));
				}
				if (textLayoutCache != null) {
					textLayoutCache.lastLabelHeight = labelBounds.height;
				}
			}
			else {
				drawFakeLines(g);
			}

			if (textLayoutCache != null) {
				textLayoutCache.lastTextLayout = tl;
			}
		}
	}

	private void drawFakeLines(GC g) {
		g.setLineStyle(SWT.LINE_DASHDOT);
		if (localBoundingBox.width >= 3) {
			if (localBoundingBox.height >= 1) {
				int y = localBoundingBox.y + localBoundingBox.height / 2;
				g.drawLine(localBoundingBox.x + 1, y, localBoundingBox.x + localBoundingBox.width - 2, y);
			}
			if (localBoundingBox.height > 5) {
				int y = localBoundingBox.y + (localBoundingBox.height / 2) - 2;
				g.drawLine(localBoundingBox.x + 1, y, localBoundingBox.x + localBoundingBox.width - 2, y);
				y += 4;
				g.drawLine(localBoundingBox.x + 1, y, localBoundingBox.x + localBoundingBox.width - 2, y);
			}
		}
	}

	/*
	 * public Rectangle getLocalBoundingBox(IBNAContext context, GC g,
	 * ICoordinateMapper cm){ updateLocalBoundingBox(cm); return
	 * localBoundingBox; }
	 */

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
