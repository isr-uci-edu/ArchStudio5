package org.archstudio.bna.logics.editing;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.mapping.IBNAMappingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.BNAAction;
import org.eclipse.jface.action.IMenuManager;

/**
 * Provides a menu entry to restore a thing's default settings according to the {@link IBNAMappingLogic mapping logic}
 * that created it.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class RestoreDefaultsLogic extends AbstractThingLogic implements IBNAMenuListener {
	protected final ThingValueTrackingLogic valueLogic;

	public RestoreDefaultsLogic(IBNAWorld world) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		boolean hasDefaultsToRestore = false;
		for (IThing t : valueLogic.getThings(IHasSelected.SELECTED_KEY, true)) {
			if (t.get(IBNAMappingLogic.MAPPING_KEY) != null) {
				hasDefaultsToRestore = true;
				break;
			}
		}
		final boolean finalHasDefaultsToRestore = hasDefaultsToRestore;

		menu.add(new BNAAction("Restore Defaults") {
			@Override
			public void runWithLock() {
				for (IThing t : valueLogic.getThings(IHasSelected.SELECTED_KEY, true)) {
					@SuppressWarnings("unchecked")
					IBNAMappingLogic<IThing> mappingLogic =
							(IBNAMappingLogic<IThing>) t.get(IBNAMappingLogic.MAPPING_KEY);
					if (mappingLogic != null) {
						mappingLogic.applyDefaults(t);
					}
				}
			}

			@Override
			public boolean isEnabled() {
				return finalHasDefaultsToRestore;
			}
		});
	}
}
