package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractEditDirectionLogic;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;

public class StructureEditDirectionLogic extends AbstractEditDirectionLogic{
	
	public StructureEditDirectionLogic(ArchipelagoServices AS, ObjRef xArchRef){
		super(AS, xArchRef);
	}
	
	public boolean matches(IBNAView view, IThing t){
		if(t instanceof EndpointGlassThing){
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				return StructureMapper.isInterfaceAssemblyRootThing(pt);
			}
		}
		return false;
	}
	
	public String getXArchID(IBNAView view, IThing t){
		if(t instanceof EndpointGlassThing){
			IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
			return parentThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		return null;
	}
}
