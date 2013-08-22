package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

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
import org.archstudio.swtutils.constants.Flow;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

import com.google.common.collect.Iterables;

public class EditFlowLogic extends AbstractThingLogic implements IBNAMenuListener {

	public EditFlowLogic() {
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager menu) {
		IHasMutableFlow editThing = null;
		if (Iterables.size(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) <= 1) {
			editThing = Assemblies.getEditableThing(getBNAModel(), firstOrNull(things), IHasMutableFlow.class,
					IHasMutableFlow.USER_MAY_EDIT_FLOW);
		}
		final IHasMutableFlow finalThing = editThing;
		if (finalThing != null) {
			MenuManager editDirectionMenu = new MenuManager("Edit Direction...");
			for (final Flow f : Flow.values()) {
				editDirectionMenu.add(new Action(f.toString()) {

					@Override
					public void run() {
						BNAOperations.set("Direction", getBNAModel(), finalThing, IHasFlow.FLOW_KEY, f);
					}
				});
			}
			menu.add(editDirectionMenu);
		}
	}
}
