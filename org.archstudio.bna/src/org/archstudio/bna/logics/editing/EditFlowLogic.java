package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.swtutils.constants.Flow;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

public class EditFlowLogic extends AbstractThingLogic implements IBNAMenuListener2 {

	public EditFlowLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation, IMenuManager menu) {
		BNAUtils.checkLock();
		if (thingsAtLocation.getThingAtLocation() != null) {
			final IHasFlow editThing = Assemblies.getEditableThing(model, thingsAtLocation.getThing(), IHasFlow.class,
					IHasMutableFlow.USER_MAY_EDIT_FLOW);
			if (editThing != null) {
				MenuManager editDirectionMenu = new MenuManager("Edit Direction...");
				for (final Flow f : Flow.values()) {
					editDirectionMenu.add(new BNAAction(f.toString()) {

						@Override
						public void runWithLock() {
							BNAOperations.set("Direction", model, editThing, IHasFlow.FLOW_KEY, f);
						}
					});
				}
				menu.add(editDirectionMenu);
			}
		}
	}
}
