package org.archstudio.bna.logics.information;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.facets.IHasGlow;
import org.archstudio.bna.facets.IHasMutableGlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

public class HighlightLogic extends AbstractThingLogic implements IBNAMenuListener2 {

	public static final String USER_MAY_HIGHLIGHT = "userMayHighlight";

	public HighlightLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(final IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
			IMenuManager menu) {
		BNAUtils.checkLock();
		if (thingsAtLocation.getThingAtLocation() != null) {
			final IHasMutableGlow t = Assemblies.getEditableThing(model, thingsAtLocation.getThing(),
					IHasMutableGlow.class, USER_MAY_HIGHLIGHT);
			if (t != null) {
				IAction highlightAction = new BNAAction("Highlight") {

					@Override
					public void runWithLock() {
						if (t.getGlowColor() == null) {
							ColorDialog cd = new ColorDialog(view.getBNAUI().getComposite().getShell());
							cd.setText("Highlight Color");
							RGB rgb = t.getGlowColor();
							if (rgb != null) {
								cd.setRGB(rgb);
							}
							RGB newColor = cd.open();
							if (newColor != null) {
								BNAOperations.set("Highlight", model, t, IHasGlow.GLOW_COLOR_KEY, newColor);
							}
						}
						else {
							BNAOperations.set("Remove Highlight", model, t, IHasGlow.GLOW_COLOR_KEY, null);
						}
					}
				};
				highlightAction.setChecked(t.getGlowColor() != null);
				menu.add(highlightAction);
			}
		}
	}
}
