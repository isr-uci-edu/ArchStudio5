package org.archstudio.bna.logics.editing;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

import com.google.common.collect.Iterables;

public class EditFlowLogic extends AbstractThingLogic implements IBNAMenuListener {

	public EditFlowLogic() {
	}

	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager menu) {
		IThing editThing = null;
		if (Iterables.size(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) <= 1) {
			MAIN: for (IThing thing : things) {
				for (IThing assemblyPartThing : Assemblies.getRelatedParts(view.getBNAWorld().getBNAModel(), thing)) {
					if (assemblyPartThing instanceof IHasMutableFlow
							&& UserEditableUtils.isEditableForAnyQualities(assemblyPartThing,
									IHasMutableFlow.USER_MAY_EDIT_FLOW)) {
						editThing = assemblyPartThing;
						break MAIN;
					}
				}
			}
		}
		final IThing finalThing = editThing;
		if (finalThing != null) {
			MenuManager editDirectionMenu = new MenuManager("Edit Direction...");
			for (final Flow f : Flow.values()) {
				editDirectionMenu.add(new Action(f.toString()) {

					public void run() {
						BNAOperations.set("Direction", getBNAModel(), finalThing, IHasFlow.FLOW_KEY, f);
					}
				});
			}
			menu.add(editDirectionMenu);
		}
	}

}
