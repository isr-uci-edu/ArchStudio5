package org.archstudio.bna.things.labels;

import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasOffset;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

import com.google.common.collect.Sets;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> {

	protected static final Set<IThingKey<?>> UPDATE_ON_CHANGE = Sets.<IThingKey<?>> newHashSet(//
			IHasText.TEXT_KEY,//
			IHasFontData.FONT_NAME_KEY,//
			IHasFontData.FONT_SIZE_KEY,//
			IHasFontData.FONT_STYLE_KEY,//
			IHasAngle.ANGLE_KEY,//
			IHasOffset.OFFSET_KEY);

	protected boolean needsTextLayout = true;
	protected TextLayout textLayout = null;
	protected Dimension textLayoutSize = null;
	protected double textLayoutScale = 1d;

	public AnchoredLabelThingPeer(T thing) {
		super(thing);
		thing.addThingListener(new IThingListener() {
			@Override
			public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
				needsTextLayout = true;
			}
		});
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

	protected void doLayout(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
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
			needsTextLayout = true;
		}

		needsTextLayout |= cm.getLocalScale() != textLayoutScale;

		if (needsTextLayout) {
			needsTextLayout = false;
			textLayoutScale = cm.getLocalScale();
			textLayoutSize = null;

			textLayout.setText(t.getText());
			int size = t.getFontSize();
			if (cm.getLocalScale() < 1) {
				size *= cm.getLocalScale();
			}
			textLayout.setFont(r.getFont(t.getFontName(), size, t.getFontStyle()));
			Rectangle textBounds = BNAUtils.toRectangle(textLayout.getBounds());
			textLayoutSize = textBounds.getSize();
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		if (r.setForegroundColor(g, t, IHasColor.COLOR_KEY)) {
			doLayout(view, cm, g, r);
			if (textLayoutSize != null) {
				Point worldAnchor = t.getAnchorPoint();
				Point localOffset = new Point(0, 0);
				int offset = t.getOffset();

				HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
				switch (horizontalAlignment) {
				case LEFT:
					localOffset.x += offset * cm.getLocalScale();
					break;
				case CENTER:
					localOffset.x -= textLayoutSize.width / 2;
					break;
				case RIGHT:
					localOffset.x -= textLayoutSize.width;
					localOffset.x -= offset * cm.getLocalScale();
					break;
				}
				textLayout.setAlignment(horizontalAlignment.toSWT());

				switch (t.getVerticalAlignment()) {
				case TOP:
					break;
				case MIDDLE:
					localOffset.y -= textLayoutSize.height / 2;
					break;
				case BOTTOM:
					localOffset.y -= textLayoutSize.height;
					break;
				}

				g.translate(cm.worldToLocal(worldAnchor));
				int angle = t.getAngle();
				if (angle != 0) {
					g.rotate(angle);
				}
				g.setFont(textLayout.getFont());
				g.drawText(textLayout.getText(), localOffset.x, localOffset.y);
			}
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		doLayout(view, cm, g, r);
		if (textLayoutSize != null) {
			Point anchor = cm.worldToLocal(t.getAnchorPoint());
			int offset = t.getOffset();
			int extent = (int) Math.ceil(Math.max(offset * cm.getLocalScale() + textLayoutSize.width,
					textLayoutSize.height));
			boundsResult.setLocation(anchor);
			boundsResult.translate(-extent, -extent);
			boundsResult.setSize(extent * 2, extent * 2);
			return;
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
