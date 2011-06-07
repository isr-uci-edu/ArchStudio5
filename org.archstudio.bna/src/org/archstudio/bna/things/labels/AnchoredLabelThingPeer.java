package org.archstudio.bna.things.labels;

import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
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
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.WeakThingListener;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

import com.google.common.collect.Sets;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractThingPeer<T> {

	protected static final Set<IThingKey<?>> UPDATE_ON_CHANGE = Sets.<IThingKey<?>> newHashSet(//
			IHasText.TEXT_KEY,//
			IHasFontData.FONT_NAME_KEY,//
			IHasFontData.FONT_SIZE_KEY,//
			IHasFontData.FONT_STYLE_KEY);

	protected boolean needsTextLayout = true;
	protected TextLayout textLayout = null;
	protected Dimension textLayoutSize = null;

	public AnchoredLabelThingPeer(T thing) {
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
		if (needsTextLayout) {
			needsTextLayout = false;
			textLayoutSize = null;

			textLayout.setText(t.getText());
			textLayout.setFont(r.getFont(t.getFontName(), t.getFontSize(), t.getFontStyle()));
			Rectangle textBounds = BNAUtils.toRectangle(textLayout.getBounds());
			textLayoutSize = textBounds.getSize();
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip) {
		if (r.setForegroundColor(g, t, IHasColor.COLOR_KEY)) {
			doLayout(view, cm, g, r);
			if (textLayoutSize != null) {
				Point anchor = cm.worldToLocal(t.getAnchorPoint());

				HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
				switch (horizontalAlignment) {
				case LEFT:
					break;
				case CENTER:
					anchor.x -= textLayoutSize.width / 2;
					break;
				case RIGHT:
					anchor.x -= textLayoutSize.width;
					break;
				}
				textLayout.setAlignment(horizontalAlignment.toSWT());

				switch (t.getVerticalAlignment()) {
				case TOP:
					break;
				case MIDDLE:
					anchor.y -= textLayoutSize.height / 2;
					break;
				case BOTTOM:
					anchor.y -= textLayoutSize.height;
					break;
				}

				g.drawTextLayout(textLayout, anchor.x, anchor.y);
			}
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		doLayout(view, cm, g, r);
		if (textLayoutSize != null) {
			Point anchor = cm.worldToLocal(t.getAnchorPoint());

			HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
			switch (horizontalAlignment) {
			case LEFT:
				break;
			case CENTER:
				anchor.x -= textLayoutSize.width / 2;
				break;
			case RIGHT:
				anchor.x -= textLayoutSize.width;
				break;
			}
			textLayout.setAlignment(horizontalAlignment.toSWT());

			switch (t.getVerticalAlignment()) {
			case TOP:
				break;
			case MIDDLE:
				anchor.y -= textLayoutSize.height / 2;
				break;
			case BOTTOM:
				anchor.y -= textLayoutSize.height;
				break;
			}

			boundsResult.setLocation(anchor);
			boundsResult.setSize(textLayoutSize);
			return;
		}

		boundsResult.setSize(0, 0);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
