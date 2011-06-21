package org.archstudio.archipelago.core.structure;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IXArchEventHandlerLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTModelEvent.EventType;

public class StructureXArchEventHandlerLogic extends AbstractThingLogic implements IXArchEventHandlerLogic{
	protected ArchipelagoServices AS = null;
	protected TypedThingSetTrackingLogic<IHasWorld> ttstlView = null;
	
	public StructureXArchEventHandlerLogic(ArchipelagoServices AS, TypedThingSetTrackingLogic<IHasWorld> ttstlView){
		this.AS = AS;
		this.ttstlView = ttstlView;
	}
	
	public void handleXArchADTModelEvent(XArchADTModelEvent evt, IBNAWorld world){
		ArchipelagoUtils.sendEventToInnerViews(evt, world, ttstlView);
		
		IBNAModel model = world.getBNAModel();
		
		ObjRef sourceRef = evt.getSource();
		String sourcePathString = AS.xarch.getTagsOnlyPathString(sourceRef);

		String featureName = evt.getFeatureName();
		
		if(evt.getEventType().equals(EventType.REMOVE)){
			if((sourcePathString != null) && sourcePathString.equals("xADL") && featureName.equals("structure")){
				//See if we're removing the currently editing structure;
				//if so, then clear the editor and display the default editor
				
				String targetID = XadlUtils.getID(AS.xarch, (ObjRef)evt.getOldValue());
				if(targetID != null){
					EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(model);
					String editingStructureID = (String)ept.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
					if((editingStructureID != null) && (editingStructureID.equals(targetID))){
						AS.editor.clearEditor();
						AS.editor.displayDefaultEditor();
						return;
					}
				}
			}
		}

		//We only handle things that occur under xArch/archStructure
		if((sourcePathString == null) || (!sourcePathString.startsWith("xADL/structure"))){
			return;
		}
		
		//And specifically the one we're editing...
		List<ObjRef> srcAncestorRefs = evt.getSourceAncestors();
		ObjRef structureRef = srcAncestorRefs.get(srcAncestorRefs.size() - 3);
		String structureID = XadlUtils.getID(AS.xarch, structureRef);

		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(model);
		String editingStructureID = (String)ept.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		
		if(!BNAUtils.nulleq(structureID, editingStructureID)){
			return;
		}
		
		if((evt.getEventType() == EventType.CLEAR) || (evt.getEventType() == EventType.REMOVE)){
			if((sourcePathString != null) && (sourcePathString.equals("xADL/structure"))){
				if(featureName.equals("component")){
					StructureMapper.removeOrphanedBricks(AS, world, structureRef);
					return;
				}
				else if(featureName.equals("connector")){
					StructureMapper.removeOrphanedBricks(AS, world, structureRef);
					return;
				}
				else if(featureName.equals("link")){
					StructureMapper.removeOrphanedLinks(AS, world, structureRef);
					return;
				}
			}
			else if((sourcePathString != null) && (sourcePathString.startsWith("xADL/structure/component"))){
				StructureMapper.updateComponent(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
				return;
			}
			else if((sourcePathString != null) && (sourcePathString.startsWith("xADL/structure/connector"))){
				StructureMapper.updateConnector(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
				return;
			}
			else if((sourcePathString != null) && (sourcePathString.startsWith("xADL/structure/link"))){
				StructureMapper.updateLink(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
				return;
			}
		}

		if(sourcePathString != null){
			if(sourcePathString.equals("xADL/structure")){
				if(featureName.equals("component")){
					StructureMapper.updateComponent(AS, world, (ObjRef)evt.getNewValue());
				}
				else if(featureName.equals("connector")){
					StructureMapper.updateConnector(AS, world, (ObjRef)evt.getNewValue());
				}
				else if(featureName.equals("link")){
					StructureMapper.updateLink(AS, world, (ObjRef)evt.getNewValue());
				}
			}
			else if(sourcePathString.startsWith("xADL/structure/component")){
				StructureMapper.updateComponent(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
			}
			else if(sourcePathString.startsWith("xADL/structure/connector")){
				StructureMapper.updateConnector(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
			}
			else if(sourcePathString.startsWith("xADL/structure/link")){
				StructureMapper.updateLink(AS, world, srcAncestorRefs.get(srcAncestorRefs.size() - 4));
			}
		}
	}
	
}
