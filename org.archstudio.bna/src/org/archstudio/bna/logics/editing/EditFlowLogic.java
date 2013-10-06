package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.constants.Flow;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

public class EditFlowLogic extends AbstractThingLogic implements IBNAMenuListener {

	public EditFlowLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location,
			IMenuManager menu) {
		final IHasMutableFlow editThing = Assemblies.getEditableThing(model, firstOrNull(things),
				IHasMutableFlow.class, IHasMutableFlow.USER_MAY_EDIT_FLOW);
		if (editThing != null) {
			MenuManager editDirectionMenu = new MenuManager("Edit Direction...");
			for (final Flow f : Flow.values()) {
				editDirectionMenu.add(new Action(f.toString()) {

					@Override
					public void run() {
						BNAOperations.set("Direction", model, editThing, IHasFlow.FLOW_KEY, f);
					}
				});
			}
			menu.add(editDirectionMenu);
		}
	}
}
