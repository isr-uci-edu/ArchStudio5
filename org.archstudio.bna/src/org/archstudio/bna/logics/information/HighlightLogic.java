package org.archstudio.bna.logics.information;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasGlow;
import org.archstudio.bna.facets.IHasMutableGlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

public class HighlightLogic extends AbstractThingLogic implements IBNAMenuListener {

	public static final String USER_MAY_HIGHLIGHT = "userMayHighlight";

	public HighlightLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		BNAUtils.checkLock();

		if (!things.isEmpty()) {
			final IHasMutableGlow t = Assemblies.getEditableThing(model, firstOrNull(things), IHasMutableGlow.class,
					USER_MAY_HIGHLIGHT);
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
