package org.archstudio.archipelago.core.util;

import java.util.HashSet;
import java.util.Set;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.things.borders.MarqueeBoxBorderThing;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;

public class FileDirtyLogic extends AbstractThingLogic implements IBNAModelListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;

	protected static Set<String> propertyNameSet = new HashSet<String>();
	
	static{
		propertyNameSet.add(IHasBoundingBox.BOUNDING_BOX_PROPERTY_NAME);
		propertyNameSet.add(IHasAnchorPoint.ANCHOR_POINT_PROPERTY_NAME);
		propertyNameSet.add(IHasMidpoints.MIDPOINTS_PROPERTY_NAME);
		propertyNameSet.add(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME);
		propertyNameSet.add(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME);
		propertyNameSet.add(IHasColor.COLOR_PROPERTY_NAME);
	}
	
	public FileDirtyLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public void bnaModelChanged(BNAModelEvent evt){
		ThingEvent tevt = evt.getThingEvent();
		if(tevt != null){
			IThing targetThing = tevt.getTargetThing();
			if(targetThing != null){
				if(targetThing instanceof MarqueeBoxBorderThing){
					return;
				}
				if(targetThing instanceof ReshapeHandleThing){
					return;
				}
				if(targetThing instanceof ReshapeHandleGlassThing){
					return;
				}
			}
			String propertyName = tevt.getPropertyName();
			if(propertyName != null){
				if(propertyNameSet.contains(propertyName)){
					AS.fileman.makeDirty(xArchRef);
				}
			}
		}
	}
}
