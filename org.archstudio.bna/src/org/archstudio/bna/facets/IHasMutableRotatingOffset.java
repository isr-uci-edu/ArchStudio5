package org.archstudio.bna.facets;

import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.things.borders.MarqueeBoxBorderThing;
import org.archstudio.bna.things.shapes.RectangleThing;

public interface IHasMutableRotatingOffset extends IHasRotatingOffset {

	/**
	 * This is an optimization to reduce unnecessary BNA event noise caused by the {@link RotatingOffsetLogic} calling
	 * {@link #incrementRotatingOffset()}. For example, it is unnecessary to call {@link #incrementRotatingOffset()} for
	 * things that are not selected, e.g., {@link RectangleThing}. Conversely, {@link #incrementRotatingOffset()} must
	 * always be called on {@link MarqueeBoxBorderThing}.
	 * 
	 * @see RotatingOffsetLogic
	 * @see MarqueeBoxBorderThing
	 * @return <code>false</code> if it is know that calling the {@link #incrementRotatingOffset()} method is
	 *         unnecessary, <code>true</code> otherwise.
	 */
	public boolean shouldIncrementRotatingOffset();

	public void incrementRotatingOffset();
}
