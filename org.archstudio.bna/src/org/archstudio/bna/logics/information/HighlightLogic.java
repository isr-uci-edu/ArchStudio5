package org.archstudio.bna.logics.information;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasGlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jface.action.Action;
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
	synchronized public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		if (!things.isEmpty()) {
			final IHasGlow t = Assemblies.getEditableThing(model, firstOrNull(things), IHasGlow.class,
					USER_MAY_HIGHLIGHT);
			if (t != null) {
				IAction highlightAction = new Action("Highlight") {

					@Override
					public void run() {
						if (t.getGlowColor() == null) {
							ColorDialog cd = new ColorDialog(view.getBNAUI().getComposite().getShell());
							cd.setText("Highlight Color");
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
				highlightAction.setChecked(t != null);
				menu.add(highlightAction);
			}
		}
	}
}
