package org.archstudio.archipelago.core.structure;

import java.util.HashSet;
import java.util.Set;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasStickyEndpoints;
import org.archstudio.bna.things.glass.StickySplineGlassThing;

public class StructureLinkEndpointLogic extends AbstractThingLogic implements IBNAModelListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	protected Set<String> linksBeingUpdated = new HashSet<String>();

	public StructureLinkEndpointLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public void destroy(){
		linksBeingUpdated.clear();
	}
	
	public void bnaModelChanged(BNAModelEvent evt){
		if(evt.getEventType().equals(BNAModelEvent.EventType.STREAM_NOTIFICATION_EVENT)){
			String not = evt.getStreamNotification();
			if(not != null){
				if(not.startsWith("+updateLink$")){
					linksBeingUpdated.add(not.substring(not.indexOf('$') + 1));
				}
				if(not.startsWith("-updateLink$")){
					linksBeingUpdated.remove(not.substring(not.indexOf('$') + 1));
				}
			}
			return;
		}
		IBNAModel model = (IBNAModel)evt.getSource();
		if(model != null){
			if(evt.getEventType().equals(BNAModelEvent.EventType.THING_CHANGED)){
				IThing targetThing = evt.getTargetThing();
				if(targetThing instanceof StickySplineGlassThing){
					StickySplineGlassThing sgt = (StickySplineGlassThing)targetThing;
					if(linksBeingUpdated.contains(sgt.getID())){
						return;
					}
					ThingEvent tevt = evt.getThingEvent();
					if(tevt != null){
						int endpointNum = 0;
						String propertyName = tevt.getPropertyName();
						if((propertyName != null) && (propertyName.equals(IHasStickyEndpoints.ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME))){
							endpointNum = 1;
						}
						else if((propertyName != null) && (propertyName.equals(IHasStickyEndpoints.ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME))){
							endpointNum = 2;
						}
						else{
							return;
						}
						String newStuckToThingID = (String)tevt.getNewPropertyValue();
						IThing newStuckToThing = model.getThing(newStuckToThingID);
						if(newStuckToThing != null){
							IThing newStuckToParentThing = model.getParentThing(newStuckToThing);
							if(newStuckToParentThing != null){
								if(StructureMapper.isInterfaceAssemblyRootThing(newStuckToParentThing)){
									String newStuckToXArchID = newStuckToParentThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
									if(newStuckToXArchID != null){
										ObjRef newStuckToRef = AS.xarch.getByID(xArchRef, newStuckToXArchID);
										if(newStuckToRef != null){
											if(AS.xarch.isInstanceOf(newStuckToRef, "org.archstudio.xadl3.structure_3_0.Interface")){
												IThing splineGlassThingParentThing = model.getParentThing(sgt);
												if(splineGlassThingParentThing != null){
													if(StructureMapper.isLinkAssemblyRootThing(splineGlassThingParentThing)){
														String linkXArchID = splineGlassThingParentThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
														if(linkXArchID != null){
															ObjRef linkRef = AS.xarch.getByID(xArchRef, linkXArchID);
															if(linkRef != null){
																AS.xarch.set(linkRef, "point" + endpointNum, newStuckToRef);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						else{
							//Disconnected the point
							IThing splineGlassThingParentThing = model.getParentThing(sgt);
							if(splineGlassThingParentThing != null){
								if(StructureMapper.isLinkAssemblyRootThing(splineGlassThingParentThing)){
									String linkXArchID = splineGlassThingParentThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
									if(linkXArchID != null){
										ObjRef linkRef = AS.xarch.getByID(xArchRef, linkXArchID);
										if(linkRef != null){
											AS.xarch.clear(linkRef, "point" + endpointNum);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
