package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractEditNameLogic;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.glass.StickySplineGlassThing;

public class StructureEditDescriptionLogic extends AbstractEditNameLogic{
	
	public StructureEditDescriptionLogic(ArchipelagoServices AS, ObjRef xArchRef){
		super(AS, xArchRef);
	}
	
	public boolean matches(IBNAView view, IThing t){
		if(t instanceof RectangleGlassThing){
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				return StructureMapper.isBrickAssemblyRootThing(pt);
			}
		}
		else if(t instanceof EndpointGlassThing){
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				return StructureMapper.isInterfaceAssemblyRootThing(pt);
			}
		}
		else if(t instanceof StickySplineGlassThing){
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				return StructureMapper.isLinkAssemblyRootThing(pt);
			}
		}
		return false;
	}
	
	public String getXArchID(IBNAView view, IThing t){
		if(t instanceof RectangleGlassThing){
			IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
			return parentThing.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		else if(t instanceof EndpointGlassThing){
			IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
			return parentThing.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		else if(t instanceof StickySplineGlassThing){
			IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
			return parentThing.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		return null;
	}
}
