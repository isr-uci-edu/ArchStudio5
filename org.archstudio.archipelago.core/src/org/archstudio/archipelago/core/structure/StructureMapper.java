package org.archstudio.archipelago.core.structure;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.MappingAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.utility.AssemblyRootThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.NoThing;

public class StructureMapper{
	private StructureMapper(){
	}

	public static final String ASSEMBLY_TYPE_PROPERTY_NAME = "#assemblyType";
	public static final String ASSEMBLY_TYPE_COMPONENT = "component";
	public static final String ASSEMBLY_TYPE_CONNECTOR = "connector";
	public static final String ASSEMBLY_TYPE_INTERFACE = "interface";
	public static final String ASSEMBLY_TYPE_IM = "interfaceMapping";
	public static final String ASSEMBLY_TYPE_LINK = "link";
	public static final String BRICK_KIND_PROPERTY_NAME = "#brickKind";

	public static enum BrickKind{
		COMPONENT, CONNECTOR
	}

	public static void updateStructureInJob(ArchipelagoServices AS, IBNAWorld world, ObjRef structureRef){
		final ArchipelagoServices fAS = AS;
		final IBNAWorld fbnaWorld = world;
		final ObjRef fstructureRef = structureRef;
		Job job = new Job("Updating Structure"){
			protected IStatus run(IProgressMonitor monitor){
				updateStructure(fAS, fbnaWorld, fstructureRef);
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	public static void updateStructure(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef structureRef){
		removeOrphanedBricks(AS, bnaWorld, structureRef);

		List<ObjRef> componentRefs = AS.xarch.getAll(structureRef, "component");
		for(ObjRef componentRef : componentRefs){
			updateComponent(AS, bnaWorld, componentRef);
		}

		List<ObjRef> connectorRefs = AS.xarch.getAll(structureRef, "connector");
		for(ObjRef connectorRef : connectorRefs){
			updateConnector(AS, bnaWorld, connectorRef);
		}

		removeOrphanedLinks(AS, bnaWorld, structureRef);
		List<ObjRef> linkRefs = AS.xarch.getAll(structureRef, "link");
		for(ObjRef linkRef : linkRefs){
			updateLink(AS, bnaWorld, linkRef);
		}

		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaWorld.getBNAModel());
		if(!ept.hasProperty("hintsApplied")){
			StructureEditorSupport.readHints(AS, bnaWorld.getBNAModel(), structureRef);
			ept.setProperty("hintsApplied", true);
		}
		AS.eventBus.fireArchipelagoEvent(new StructureEvents.StructureUpdatedEvent(bnaWorld, structureRef));
	}

	public synchronized static void removeOrphanedBricks(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef structureRef){
		IBNAModel bnaModel = bnaWorld.getBNAModel();
		IThing brickRootThing = getBrickRootThing(bnaModel);
		for(IThing t : bnaModel.getChildThings(brickRootThing)){
			if(isBrickAssemblyRootThing(t)){
				String xArchID = t.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
				if(xArchID != null){
					BrickKind kind = t.getProperty(BRICK_KIND_PROPERTY_NAME);
					if(kind == null)
						continue;

					List<ObjRef> brickRefs = null;
					switch(kind){
						case COMPONENT:
							brickRefs = AS.xarch.getAll(structureRef, "component");
							break;
						case CONNECTOR:
							brickRefs = AS.xarch.getAll(structureRef, "connector");
							break;
						default:
							continue;
					}

					boolean found = false;
					for(ObjRef refToMatch : brickRefs){
						String idToMatch = XadlUtils.getID(AS.xarch, refToMatch);
						if((idToMatch != null) && (idToMatch.equals(xArchID))){
							found = true;
							break;
						}
					}
					if(!found){
						bnaModel.removeThingAndChildren(t);
					}
				}
			}
		}
	}

	public static void updateComponent(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef componentRef){
		updateBrick(AS, bnaWorld, componentRef, BrickKind.COMPONENT);
	}

	public static void updateConnector(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef connectorRef){
		updateBrick(AS, bnaWorld, connectorRef, BrickKind.CONNECTOR);
	}

	public synchronized static void updateBrick(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef brickRef, BrickKind kind){
		IBNAModel bnaModel = bnaWorld.getBNAModel();

		String xArchID = XadlUtils.getID(AS.xarch, brickRef);
		if(xArchID == null)
			return;

		BoxAssembly brickAssembly = null;
		IThing brickAssemblyRootThing = ArchipelagoUtils.findThing(bnaModel, xArchID);

		boolean isNew = false;

		if((brickAssemblyRootThing == null) || (!isBrickAssemblyRootThing(brickAssemblyRootThing))){
			isNew = true;

			brickAssembly = BoxAssembly.create(bnaModel, getBrickRootThing(bnaModel));
			brickAssembly.getRootThing().setProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xArchID);

			Point p = ArchipelagoUtils.findOpenSpotForNewThing(bnaModel);
			brickAssembly.getBoxGlassThing().setBoundingBox(p.x, p.y, 100, 100);

			brickAssembly.getBoxThing().setGradientFilled(true);
			brickAssembly.getBoxBorderThing().setLineWidth(1);
			brickAssembly.getBoxBorderThing().setLineStyle(SWT.LINE_SOLID);

			switch(kind){
				case COMPONENT:
					brickAssembly.getRootThing().setProperty(ASSEMBLY_TYPE_PROPERTY_NAME, ASSEMBLY_TYPE_COMPONENT);
					brickAssembly.getRootThing().setProperty(BRICK_KIND_PROPERTY_NAME, BrickKind.COMPONENT);
					brickAssembly.getBoxThing().setColor(getDefaultComponentColor(AS));
					brickAssembly.getBoxBorderThing().setCount(2);
					break;
				case CONNECTOR:
					brickAssembly.getRootThing().setProperty(ASSEMBLY_TYPE_PROPERTY_NAME, ASSEMBLY_TYPE_CONNECTOR);
					brickAssembly.getRootThing().setProperty(BRICK_KIND_PROPERTY_NAME, BrickKind.CONNECTOR);
					brickAssembly.getBoxThing().setColor(getDefaultConnectorColor(AS));
					brickAssembly.getBoxBorderThing().setCount(1);
					break;
			}
		}
		else{
			brickAssembly = BoxAssembly.attach(bnaModel, (IHasAssemblyData)brickAssemblyRootThing);
		}
		String name = XadlUtils.getName(AS.xarch, brickRef);
		if(name == null)
			name = "[No Description]";

		brickAssembly.getBoxedLabelThing().setText(name);
		brickAssembly.getBoxGlassThing().setToolTip(name);

		// update the interfaces
		removeOrphanedInterfaces(AS, bnaWorld, brickAssembly, brickRef);
		List<ObjRef> interfaceRefs = AS.xarch.getAll(brickRef, "interface");
		for(ObjRef interfaceRef : interfaceRefs){
			updateInterface(AS, bnaWorld, brickAssembly, interfaceRef);
		}

		FontData[] fd = PreferenceConverter.getFontDataArray(AS.prefs, ArchipelagoStructureConstants.PREF_DEFAULT_FONT);
		if(fd.length > 0){
			brickAssembly.getBoxedLabelThing().setFontName(fd[0].getName());
			brickAssembly.getBoxedLabelThing().setFontSize(fd[0].getHeight());
			brickAssembly.getBoxedLabelThing().setFontStyle(FontStyle.fromSWT(fd[0].getStyle()));
		}

		updateSubarchitecture(AS, bnaWorld, brickAssembly, brickRef);

		AS.eventBus.fireArchipelagoEvent(new StructureEvents.BrickUpdatedEvent(bnaWorld, brickRef, kind, brickAssembly));
	}

	public synchronized static void updateSubarchitecture(ArchipelagoServices AS, IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef){
		ObjRef subStructureRef = (ObjRef)AS.xarch.get(brickRef, "subStructure");
		if(subStructureRef != null){
			ObjRef internalStructureRef = (ObjRef)AS.xarch.get(subStructureRef, "innerStructureLink");

			if(internalStructureRef != null){
				ObjRef documentRootRef = AS.xarch.getDocumentRootRef(brickRef);
				IBNAWorld internalWorld = StructureEditorSupport.setupWorld(AS, documentRootRef, internalStructureRef);
				if(internalWorld != null){
					StructureMapper.updateStructure(AS, internalWorld, internalStructureRef);
					brickAssembly.getWorldThing().setWorld(internalWorld);

					removeOrphanedInterfaceMappings(AS, bnaWorld, brickAssembly, brickRef, internalWorld);
					List<ObjRef> imRefs = AS.xarch.getAll(subStructureRef, "interfaceMapping");
					for(ObjRef imRef : imRefs){
						updateInterfaceMapping(AS, bnaWorld, brickAssembly, brickRef, internalWorld, imRef);
					}
					AS.eventBus.fireArchipelagoEvent(new StructureEvents.SubarchitectureUpdatedEvent(bnaWorld, brickAssembly, brickRef));
					return;
				}
			}
		}
		// It doesn't have a valid subarchitecture, clear it
		brickAssembly.getWorldThing().clearWorld();

		AS.eventBus.fireArchipelagoEvent(new StructureEvents.SubarchitectureUpdatedEvent(bnaWorld, brickAssembly, brickRef));
	}

	public static void removeOrphanedInterfaceMappings(ArchipelagoServices AS, IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef, IBNAWorld innerWorld){
		List<ObjRef> interfaceRefs = AS.xarch.getAll(brickRef, "interface");

		IBNAModel bnaModel = bnaWorld.getBNAModel();
		ObjRef subStructureRef = (ObjRef)AS.xarch.get(brickRef, "subStructure");
		if(subStructureRef != null){
			List<ObjRef> imRefs = AS.xarch.getAll(subStructureRef, "interfaceMapping");

			for(IThing t : bnaModel.getChildThings(brickAssembly.getMappingRootThing())){
				if((t instanceof IHasAssemblyData) && (((IHasAssemblyData)t).getAssemblyKind().equals(MappingAssembly.ASSEMBLY_KIND))){
					String xArchID = t.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
					if(xArchID != null){
						boolean found = false;
						for(ObjRef refToMatch : imRefs){
							String idToMatch = XadlUtils.getID(AS.xarch, refToMatch);
							if((idToMatch != null) && (idToMatch.equals(xArchID))){
								found = true;
							}
						}
						if(!found){
							bnaModel.removeThingAndChildren(t);
						}
					}
				}
			}
		}
	}

	public static void updateInterfaceMapping(ArchipelagoServices AS, IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef, IBNAWorld innerWorld, ObjRef imRef){
		List<ObjRef> interfaceRefs = AS.xarch.getAll(brickRef, "interface");

		IBNAModel bnaModel = bnaWorld.getBNAModel();
		String xArchID = XadlUtils.getID(AS.xarch, imRef);
		if(xArchID == null)
			return;
		
		ObjRef outerInterfaceRef = (ObjRef)AS.xarch.get(imRef, "outerInterfaceLink");
		if(outerInterfaceRef == null){
			return;
		}
		ObjRef innerInterfaceRef = (ObjRef)AS.xarch.get(imRef, "innerInterfaceLink");
		if(innerInterfaceRef == null){
			return;
		}

		String outerInterfaceXArchID = XadlUtils.getID(AS.xarch, outerInterfaceRef);
		String innerInterfaceXArchID = XadlUtils.getID(AS.xarch, innerInterfaceRef);

		if((outerInterfaceRef != null) && (outerInterfaceXArchID != null)){
			MappingAssembly iimAssembly = null;
			IThing iimAssemblyRootThing = null;
			IThing[] iimAssemblyRootThings = ArchipelagoUtils.findAllThings(bnaModel, xArchID);
			for(int i = 0; i < iimAssemblyRootThings.length; i++){
				String iimAssemblyRootThingBrickXArchID = (String)iimAssemblyRootThings[i].getProperty("interfaceXArchID");
				if((iimAssemblyRootThingBrickXArchID != null) && (iimAssemblyRootThingBrickXArchID.equals(outerInterfaceXArchID))){
					iimAssemblyRootThing = iimAssemblyRootThings[i];
				}
			}

			if((iimAssemblyRootThing == null) || (!(iimAssemblyRootThing instanceof IHasAssemblyData))){
				iimAssembly = MappingAssembly.create(bnaModel, brickAssembly.getMappingRootThing());
				iimAssembly.getRootThing().setProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xArchID);
				iimAssembly.getRootThing().setProperty("interfaceXArchID", outerInterfaceXArchID);

				iimAssembly.getMappingThing().setColor(new RGB(0, 0, 0));
				iimAssembly.getMappingThing().setLineWidth(2);

				iimAssembly.getMappingGlassThing().setUserEditable(false);

				iimAssembly.getRootThing().setProperty(ASSEMBLY_TYPE_PROPERTY_NAME, ASSEMBLY_TYPE_IM);
			}
			else{
				iimAssembly = MappingAssembly.attach(bnaModel, (IHasAssemblyData)iimAssemblyRootThing);
			}
			String name = XadlUtils.getName(AS.xarch, imRef);
			if(name == null){
				name = "[No Name]";
			}

			iimAssembly.getMappingGlassThing().setToolTip(name);

			IThing outerInterfaceAssemblyRootThing = ArchipelagoUtils.findThing(bnaModel, outerInterfaceXArchID);
			if((outerInterfaceAssemblyRootThing != null) && (outerInterfaceAssemblyRootThing instanceof IHasAssemblyData)){
				EndpointAssembly outerInterfaceAssembly = EndpointAssembly.attach(bnaModel, (IHasAssemblyData)outerInterfaceAssemblyRootThing);
				if(outerInterfaceAssembly.getEndpointGlassThing() != null){
					iimAssembly.getMappingGlassThing().setExternalEndpointStuckToID(outerInterfaceAssembly.getEndpointGlassThing().getID());
				}
			}
			if(brickAssembly.getWorldThing() != null){
				iimAssembly.getMappingGlassThing().setWorldThingID(brickAssembly.getWorldThing().getID());
				iimAssembly.getMappingThing().setWorldThingID(brickAssembly.getWorldThing().getID());
			}
			IBNAModel innerModel = innerWorld.getBNAModel();
			IThing interfaceAssemblyRootThing = ArchipelagoUtils.findThing(innerModel, innerInterfaceXArchID);
			if((interfaceAssemblyRootThing != null) && (interfaceAssemblyRootThing instanceof IHasAssemblyData)){
				EndpointAssembly interfaceAssembly = EndpointAssembly.attach(innerModel, (IHasAssemblyData)interfaceAssemblyRootThing);
				if(interfaceAssembly.getEndpointGlassThing() != null){
					iimAssembly.getMappingGlassThing().setInternalEndpointStuckToThingID(interfaceAssembly.getEndpointGlassThing().getID());
					iimAssembly.getMappingGlassThing().setEndpoint2(interfaceAssembly.getEndpointGlassThing().getAnchorPoint());
				}
			}
			AS.eventBus.fireArchipelagoEvent(new StructureEvents.InterfaceMappingUpdatedEvent(bnaWorld, brickAssembly, brickRef, innerWorld, imRef, iimAssembly));
		}
	}

	public synchronized static void removeOrphanedInterfaces(ArchipelagoServices AS, IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef){
		IBNAModel bnaModel = bnaWorld.getBNAModel();
		String brickXArchID = XadlUtils.getID(AS.xarch, brickRef);
		if(brickXArchID == null)
			return;

		for(IThing t : bnaModel.getChildThings(brickAssembly.getEndpointRootThing())){
			if((t instanceof IHasAssemblyData) && (((IHasAssemblyData)t).getAssemblyKind().equals(EndpointAssembly.ASSEMBLY_KIND))){
				String xArchID = t.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
				if(xArchID != null){
					List<ObjRef> interfaceRefs = AS.xarch.getAll(brickRef, "interface");

					boolean found = false;
					for(ObjRef refToMatch : interfaceRefs){
						String idToMatch = XadlUtils.getID(AS.xarch, refToMatch);
						if((idToMatch != null) && (idToMatch.equals(xArchID))){
							found = true;
							break;
						}
					}
					if(!found){
						bnaModel.removeThingAndChildren(t);
					}
				}
			}
		}
	}

	public synchronized static void updateInterface(ArchipelagoServices AS, IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef interfaceRef){
		IBNAModel bnaModel = bnaWorld.getBNAModel();
		String xArchID = XadlUtils.getID(AS.xarch, interfaceRef);
		if(xArchID == null)
			return;

		EndpointAssembly endpointAssembly = null;
		IThing endpointAssemblyRootThing = ArchipelagoUtils.findThing(bnaModel, xArchID);
		if((endpointAssemblyRootThing == null) || (!(endpointAssemblyRootThing instanceof IHasAssemblyData))){
			endpointAssembly = EndpointAssembly.create(bnaModel, brickAssembly.getEndpointRootThing());
			endpointAssembly.getRootThing().setProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xArchID);

			endpointAssembly.getEndpointGlassThing().setAnchorPoint(new Point(brickAssembly.getBoxGlassThing().getBoundingBox().x, brickAssembly.getBoxGlassThing().getBoundingBox().y));

			endpointAssembly.getBoxThing().setColor(new RGB(0xff, 0xff, 0xff));

			endpointAssembly.getEndpointGlassThing().setBoundingBoxRailMasterThingID(brickAssembly.getBoxGlassThing().getID());

			endpointAssembly.getRootThing().setProperty(ASSEMBLY_TYPE_PROPERTY_NAME, ASSEMBLY_TYPE_INTERFACE);
		}
		else{
			endpointAssembly = EndpointAssembly.attach(bnaModel, (IHasAssemblyData)endpointAssemblyRootThing);
		}
		String name = XadlUtils.getName(AS.xarch, interfaceRef);
		if(name == null){
			name = "[No Name]";
		}

		endpointAssembly.getEndpointGlassThing().setToolTip(name);
		endpointAssembly.getTagThing().setText(name);

		Direction direction = (Direction)AS.xarch.get(interfaceRef, "direction");
		Flow flow = Flow.NONE;
		if(direction != null){
			if(direction.equals(Direction.IN)){
				flow = Flow.IN;
			}
			else if(direction.equals(Direction.OUT)){
				flow = Flow.OUT;
			}
			else if(direction.equals(Direction.INOUT)){
				flow = Flow.INOUT;
			}
		}
		endpointAssembly.getDirectionalLabelThing().setFlow(flow);
		AS.eventBus.fireArchipelagoEvent(new StructureEvents.InterfaceUpdatedEvent(bnaWorld, brickAssembly, interfaceRef, endpointAssembly));
	}

	public synchronized static void removeOrphanedLinks(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef structureRef){
		IBNAModel bnaModel = bnaWorld.getBNAModel();
		IThing linkRootThing = getLinkRootThing(bnaModel);
		for(IThing t : bnaModel.getChildThings(linkRootThing)){
			if((t instanceof IHasAssemblyData) && (((IHasAssemblyData)t).getAssemblyKind().equals(StickySplineAssembly.ASSEMBLY_KIND))){
				String xArchID = t.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
				if(xArchID != null){
					List<ObjRef> linkRefs = AS.xarch.getAll(structureRef, "link");
					boolean found = false;
					for(ObjRef refToMatch : linkRefs){
						String idToMatch = XadlUtils.getID(AS.xarch, refToMatch);
						if((idToMatch != null) && (idToMatch.equals(xArchID))){
							found = true;
							break;
						}
					}
					if(!found){
						bnaModel.removeThingAndChildren(t);
					}
				}
			}
		}
	}

	public synchronized static void updateLink(ArchipelagoServices AS, IBNAWorld bnaWorld, ObjRef linkRef){
		IBNAModel bnaModel = bnaWorld.getBNAModel();

		String xArchID = XadlUtils.getID(AS.xarch, linkRef);
		if(xArchID == null)
			return;

		StickySplineAssembly linkAssembly = null;
		IThing linkAssemblyRootThing = ArchipelagoUtils.findThing(bnaModel, xArchID);
		if((linkAssemblyRootThing == null) || (!(linkAssemblyRootThing instanceof IHasAssemblyData))){
			linkAssembly = StickySplineAssembly.create(bnaModel, getLinkRootThing(bnaModel));
			linkAssembly.getRootThing().setProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xArchID);

			Point p = ArchipelagoUtils.findOpenSpotForNewThing(bnaModel);
			linkAssembly.getSplineGlassThing().setEndpoint1(new Point(p.x - 50, p.y - 50));
			linkAssembly.getSplineGlassThing().setEndpoint2(new Point(p.x + 50, p.y + 50));
			linkAssembly.getRootThing().setProperty(ASSEMBLY_TYPE_PROPERTY_NAME, ASSEMBLY_TYPE_LINK);
		}
		else{
			linkAssembly = StickySplineAssembly.attach(bnaModel, (IHasAssemblyData)linkAssemblyRootThing);
		}
		bnaModel.fireStreamNotificationEvent("+updateLink$" + linkAssembly.getSplineGlassThing().getID());

		String name = XadlUtils.getName(AS.xarch, linkRef);
		if(name == null){
			name = "[No Description]";
		}
		linkAssembly.getSplineGlassThing().setToolTip(name);

		// Stick the endpoints
		boolean endpoint1Stuck = false;
		ObjRef endpoint1TargetRef = (ObjRef)AS.xarch.get(linkRef, "point1");
		if(endpoint1TargetRef != null){
			String endpoint1TargetID = XadlUtils.getID(AS.xarch, endpoint1TargetRef);
			if(endpoint1TargetID != null){
				IThing endpoint1TargetThing = ArchipelagoUtils.findThing(bnaModel, endpoint1TargetID);
				EndpointAssembly endpointAssembly = EndpointAssembly.attach(bnaModel, (IHasAssemblyData)endpoint1TargetThing);
				if(endpoint1TargetThing != null){
					linkAssembly.getSplineGlassThing().setEndpoint1StuckToThingID(endpointAssembly.getEndpointGlassThing().getID());
					endpoint1Stuck = true;
				}
			}
		}
		if(!endpoint1Stuck){
			linkAssembly.getSplineGlassThing().clearEndpoint1StuckToThingID();
		}

		boolean endpoint2Stuck = false;
		ObjRef endpoint2TargetRef = (ObjRef)AS.xarch.get(linkRef, "point2");
		if(endpoint2TargetRef != null){
			String endpoint2TargetID = XadlUtils.getID(AS.xarch, endpoint2TargetRef);
			if(endpoint2TargetID != null){
				IThing endpoint2TargetThing = ArchipelagoUtils.findThing(bnaModel, endpoint2TargetID);
				EndpointAssembly endpointAssembly = EndpointAssembly.attach(bnaModel, (IHasAssemblyData)endpoint2TargetThing);
				if(endpoint2TargetThing != null){
					linkAssembly.getSplineGlassThing().setEndpoint2StuckToThingID(endpointAssembly.getEndpointGlassThing().getID());
					endpoint2Stuck = true;
				}
			}
		}
		if(!endpoint2Stuck){
			linkAssembly.getSplineGlassThing().clearEndpoint2StuckToThingID();
		}
		bnaModel.fireStreamNotificationEvent("-updateLink$" + linkAssembly.getSplineGlassThing().getID());
		AS.eventBus.fireArchipelagoEvent(new StructureEvents.LinkUpdatedEvent(bnaWorld, linkRef, linkAssembly));
	}

	public static boolean isComponentAssemblyRootThing(IThing t){
		if(t instanceof AssemblyRootThing){
			String assemblyType = t.getProperty(ASSEMBLY_TYPE_PROPERTY_NAME);
			if(assemblyType != null){
				if(assemblyType.equals(ASSEMBLY_TYPE_COMPONENT)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isConnectorAssemblyRootThing(IThing t){
		if(t instanceof AssemblyRootThing){
			String assemblyType = t.getProperty(ASSEMBLY_TYPE_PROPERTY_NAME);
			if(assemblyType != null){
				if(assemblyType.equals(ASSEMBLY_TYPE_CONNECTOR)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isBrickAssemblyRootThing(IThing t){
		if(t instanceof AssemblyRootThing){
			String assemblyType = t.getProperty(ASSEMBLY_TYPE_PROPERTY_NAME);
			if(assemblyType != null){
				if(assemblyType.equals(ASSEMBLY_TYPE_COMPONENT)){
					return true;
				}
				if(assemblyType.equals(ASSEMBLY_TYPE_CONNECTOR)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isInterfaceAssemblyRootThing(IThing t){
		if(t instanceof AssemblyRootThing){
			String assemblyType = t.getProperty(ASSEMBLY_TYPE_PROPERTY_NAME);
			if(assemblyType != null){
				if(assemblyType.equals(ASSEMBLY_TYPE_INTERFACE)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isLinkAssemblyRootThing(IThing t){
		if(t instanceof AssemblyRootThing){
			String assemblyType = t.getProperty(ASSEMBLY_TYPE_PROPERTY_NAME);
			if(assemblyType != null){
				if(assemblyType.equals(ASSEMBLY_TYPE_LINK)){
					return true;
				}
			}
		}
		return false;
	}

	public static RGB getDefaultComponentColor(ArchipelagoServices AS){
		if(AS.prefs.contains(ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR)){
			return PreferenceConverter.getColor(AS.prefs, ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR);
		}
		return ArchipelagoStructureConstants.DEFAULT_COMPONENT_RGB;
	}

	public static RGB getDefaultConnectorColor(ArchipelagoServices AS){
		if(AS.prefs.contains(ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR)){
			return PreferenceConverter.getColor(AS.prefs, ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR);
		}
		return ArchipelagoStructureConstants.DEFAULT_CONNECTOR_RGB;
	}

	// Functions to set up the BNA model with basic root things for the hierarchy
	public static final String BNA_BRICK_ROOT_THING_ID = "$BRICKROOT";

	public static final String BNA_LINK_ROOT_THING_ID = "$LINKROOT";

	protected static void initBNAModel(IBNAModel m){
		IThing normalRootThing = ArchipelagoUtils.getNormalRootThing(m);

		if(m.getThing(BNA_LINK_ROOT_THING_ID) == null){
			IThing linkRootThing = new NoThing(BNA_LINK_ROOT_THING_ID);
			m.addThing(linkRootThing);
		}

		if(m.getThing(BNA_BRICK_ROOT_THING_ID) == null){
			IThing brickRootThing = new NoThing(BNA_BRICK_ROOT_THING_ID);
			m.addThing(brickRootThing);
		}
	}

	protected static IThing getRootThing(IBNAModel m, String id){
		IThing rootThing = m.getThing(id);
		if(rootThing == null){
			initBNAModel(m);
			return m.getThing(id);
		}
		return rootThing;
	}

	public static IThing getBrickRootThing(IBNAModel m){
		return getRootThing(m, BNA_BRICK_ROOT_THING_ID);
	}

	public static IThing getLinkRootThing(IBNAModel m){
		return getRootThing(m, BNA_LINK_ROOT_THING_ID);
	}

	public static BoxAssembly findBrickAssembly(ArchipelagoServices AS, IBNAModel m, ObjRef brickRef){
		String xArchID = XadlUtils.getID(AS.xarch, brickRef);
		if(xArchID != null){
			IThing t = ArchipelagoUtils.findThing(m, xArchID);
			if((t != null) && isBrickAssemblyRootThing(t)){
				BoxAssembly assembly = BoxAssembly.attach(m, (IHasAssemblyData)t);
				return assembly;
			}
		}
		return null;
	}

	public static EndpointAssembly findInterfaceAssembly(ArchipelagoServices AS, IBNAModel m, ObjRef interfaceRef){
		String xArchID = XadlUtils.getID(AS.xarch, interfaceRef);
		if(xArchID != null){
			IThing t = ArchipelagoUtils.findThing(m, xArchID);
			if((t != null) && isInterfaceAssemblyRootThing(t)){
				EndpointAssembly assembly = EndpointAssembly.attach(m, (IHasAssemblyData)t);
				return assembly;
			}
		}
		return null;
	}

	public static StickySplineAssembly findLinkAssembly(ArchipelagoServices AS, IBNAModel m, ObjRef linkRef){
		String xArchID = XadlUtils.getID(AS.xarch, linkRef);
		if(xArchID != null){
			IThing t = ArchipelagoUtils.findThing(m, xArchID);
			if((t != null) && isLinkAssemblyRootThing(t)){
				StickySplineAssembly assembly = StickySplineAssembly.attach(m, (IHasAssemblyData)t);
				return assembly;
			}
		}
		return null;
	}

}
