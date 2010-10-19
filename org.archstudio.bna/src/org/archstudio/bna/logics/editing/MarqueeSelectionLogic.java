package org.archstudio.bna.logics.editing;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasUserEditable;
import org.archstudio.bna.facets.IMarqueeSelectable;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;
import org.archstudio.bna.things.borders.MarqueeBoxBorderThing;

public class MarqueeSelectionLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	protected TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected = null;

	protected MarqueeBoxBorderThing marqueeSelection = null;

	protected int initDownX = -1;
	protected int initDownY = -1;

	public MarqueeSelectionLogic(TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected) {
		this.ttstlSelected = ttstlSelected;
	}

	public void destroy() {
		if (marqueeSelection != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				m.removeThing(marqueeSelection);
			}
		}
		super.destroy();
	}

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (marqueeSelection != null) {
			getBNAModel().removeThing(marqueeSelection);
			marqueeSelection = null;
		}
		if (evt.button == 1) {
			if (t == null) {
				initDownX = worldX;
				initDownY = worldY;

				marqueeSelection = new MarqueeBoxBorderThing();

				marqueeSelection.setBoundingBox(new Rectangle(worldX, worldY, 1, 1));
				getBNAModel().addThing(marqueeSelection);
			}
		}
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (marqueeSelection != null) {
			Rectangle r = marqueeSelection.getBoundingBox();
			r.width = worldX - r.x;
			r.height = worldY - r.y;
			marqueeSelection.setBoundingBox(r);
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		try {
			if (evt.button == 1) {
				if (marqueeSelection != null) {
					Rectangle selectionRectangle = marqueeSelection.getBoundingBox();
					selectionRectangle = BNAUtils.normalizeRectangle(selectionRectangle);

					//getBNAModel().removeThing(marqueeSelection);
					IHasMutableSelected[] allSelectableThings = ttstlSelected.getThings();
					if (!BNAUtils.wasControlPressed(evt)) {
						for (IHasMutableSelected possiblySelectedThing : allSelectableThings) {
							possiblySelectedThing.setSelected(false);
						}
					}

					try {
						getBNAModel().beginBulkChange();
						for (IHasMutableSelected thing : allSelectableThings) {
							if (thing instanceof IMarqueeSelectable) {
								if (((IHasUserEditable) thing).isUserEditable()) {
									Rectangle r = ((IHasBoundingBox) thing).getBoundingBox();
									if (BNAUtils.isWithin(selectionRectangle, r)) {
										if (!BNAUtils.wasControlPressed(evt)) {
											thing.setSelected(true);
										}
										else {
											thing.setSelected(!thing.isSelected());
										}
									}
								}
							}
						}
					}
					finally {
						getBNAModel().endBulkChange();
					}
				}
			}
		}
		finally {
			if (marqueeSelection != null) {
				getBNAModel().removeThing(marqueeSelection);
				marqueeSelection = null;
			}
		}
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}
}
