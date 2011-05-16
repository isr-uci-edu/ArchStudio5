package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic.StickyMode;

public interface IStickyModeSpecifier {

	public StickyMode[] getAllowableStickyModes(IThing thing, String propertyName, IThing stickToThing);

}