package org.archstudio.bna.things.labels;

import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.things.AbstractBoxThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.LabelUtils;
import org.archstudio.bna.utils.WeakThingListener;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.TextLayout;

import com.google.common.collect.Sets;

public class BoxedLabelThingPeer<T extends BoxedLabelThing> extends AbstractBoxThingPeer<T> {

	protected static final Set<IThingKey<?>> UPDATE_ON_CHANGE = Sets.<IThingKey<?>> newHashSet(//
			IHasText.TEXT_KEY,//
			IHasFontData.FONT_NAME_KEY,//
			IHasFontData.FONT_SIZE_KEY,//
			IHasFontData.FONT_STYLE_KEY);

	protected boolean needsTextLayout = true;
	protected TextLayout textLayout = null;
	protected Dimension textLayoutBounds = new Dimension();
	protected Dimension textLayoutSize = null;
	protected double lastScale = Double.NaN;

	public BoxedLabelThingPeer(T thing) {
		super(thing);
		thing.addThingListener(new WeakThingListener(thing, new IThingListener() {
			@Override
			public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
				needsTextLayout = true;
			}
		}));
	}

	@Override
	public void dispose() {
		if (textLayout != null) {
			try {
				textLayout.dispose();
			}
			catch (Exception e) {
			}
			finally {
				textLayout = null;
			}
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip) {
		if (!worldClip.intersects(t.getBoundingBox())) {
			return;
		}

		if (r.setForegroundColor(g, t, IHasColor.COLOR_KEY)) {

			Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t, new Rectangle());

			if (textLayout == null || textLayout.getDevice() != r.getDevice()) {
				if (textLayout != null) {
					try {
						textLayout.dispose();
					}
					catch (Exception e) {
					}
					finally {
						textLayout = null;
					}
				}
				textLayout = new TextLayout(r.getDevice());
			}

			needsTextLayout |= !lbb.getSize().equals(textLayoutBounds);

			if (needsTextLayout) {
				needsTextLayout = false;
				textLayoutBounds = lbb.getSize();
				textLayoutSize = null;

				textLayout.setText(t.getText());
				textLayout.setWidth(lbb.width);
				Rectangle textBounds = LabelUtils.setFontToRender(r, textLayout, lbb.height, t.getFontName(),
						t.getFontSize(), t.getFontStyle());
				if (textBounds != null) {
					textLayoutSize = textBounds.getSize();
				}
			}

			if (textLayoutSize != null) {

				int x = lbb.x;
				int y = lbb.y;

				HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
				switch (horizontalAlignment) {
				case LEFT:
					break;
				case CENTER:
					x += (lbb.width - textLayoutSize.width) / 2;
					break;
				case RIGHT:
					x += lbb.width - textLayoutSize.width;
					break;
				}
				textLayout.setAlignment(horizontalAlignment.toSWT());

				switch (t.getVerticalAlignment()) {
				case TOP:
					break;
				case MIDDLE:
					y += (lbb.height - textLayoutSize.height) / 2;
					break;
				case BOTTOM:
					y += lbb.height - textLayoutSize.height;
					break;
				}

				g.drawTextLayout(textLayout, x, y);
			}
			else {
				g.setLineStyle(SWT.LINE_SOLID);
				g.setAlpha(128);
				if (lbb.width >= 3) {
					if (lbb.height >= 1) {
						int y = lbb.y + lbb.height / 2;
						g.drawLine(lbb.x + 1, y, lbb.x + lbb.width - 2, y);
					}
					if (lbb.height > 5) {
						int y = lbb.y + lbb.height / 2 - 3;
						g.drawLine(lbb.x + 1, y, lbb.x + lbb.width - 2, y);
						y += 6;
						g.drawLine(lbb.x + 1, y, lbb.x + lbb.width - 2, y);
					}
				}
			}
		}
	}

	@Override
	public boolean getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		if (super.getLocalBounds(view, cm, g, r, boundsResult)) {
			boundsResult.crop(t.getLocalInsets());
			return true;
		}
		return false;
	}
}
