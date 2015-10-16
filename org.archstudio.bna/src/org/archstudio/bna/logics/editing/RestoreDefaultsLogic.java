package org.archstudio.bna.logics.editing;

import java.util.HashSet;
import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.mapping.IBNAMappingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.jface.action.IMenuManager;

/**
 * Provides a menu entry to restore a thing's default settings according to the {@link IBNAMappingLogic mapping logic}
 * that created it.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class RestoreDefaultsLogic extends AbstractThingLogic implements IBNAMenuListener2 {
	protected final ThingValueTrackingLogic valueLogic;

	public RestoreDefaultsLogic(IBNAWorld world) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation, IMenuManager menu) {
		BNAUtils.checkLock();
		if (thingsAtLocation.getThingAtLocation() != null) {
			final Set<IThing> toRestoreThings = new HashSet<>();
			for (IThing t : BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) {
				if (t.get(IBNAMappingLogic.MAPPING_KEY) != null) {
					toRestoreThings.add(t);
				}
			}
			if (thingsAtLocation.getThingAtLocation().getThing().get(IBNAMappingLogic.MAPPING_KEY) != null) {
				toRestoreThings.add(thingsAtLocation.getThingAtLocation().getThing());
			}
			menu.add(new BNAAction("Restore Defaults") {
				@Override
				public void runWithLock() {
					for (IThing t : toRestoreThings) {
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
					return toRestoreThings.size() > 0;
				}
			});
		}
	}
}
