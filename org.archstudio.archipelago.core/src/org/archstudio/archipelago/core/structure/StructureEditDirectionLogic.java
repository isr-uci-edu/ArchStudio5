package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractEditDirectionLogic;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.xarchadt.ObjRef;

public class StructureEditDirectionLogic extends AbstractEditDirectionLogic {

	public StructureEditDirectionLogic(ArchipelagoServices AS, ObjRef xArchRef) {
		super(AS, xArchRef);
	}

	public boolean matches(IBNAView view, IThing t) {
		if (t instanceof EndpointGlassThing) {
			IThing pt = view.getBNAWorld().getBNAModel().getParentThing(t);
			if (pt != null) {
				return StructureMapper.isInterfaceAssemblyRootThing(pt);
			}
		}
		return false;
	}

	public String getXArchID(IBNAView view, IThing t) {
		if (t instanceof EndpointGlassThing) {
			IThing parentThing = view.getBNAWorld().getBNAModel().getParentThing(t);
			return parentThing.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		return null;
	}
}
