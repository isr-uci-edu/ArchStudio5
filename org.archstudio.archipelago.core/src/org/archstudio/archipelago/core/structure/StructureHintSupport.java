package org.archstudio.archipelago.core.structure;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.HintSupport;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;

public class StructureHintSupport{

	public static void writeHintsForStructure(ArchipelagoServices AS, IBNAModel m, ObjRef structureRef){
		for(ObjRef componentRef : AS.xarch.getAll(structureRef, "component")){
			writeHintsForBrick(AS, m, componentRef);
		}
		for(ObjRef connectorRef : AS.xarch.getAll(structureRef, "connector")){
			writeHintsForBrick(AS, m, connectorRef);
		}
		for(ObjRef linkRef : AS.xarch.getAll(structureRef, "link")){
			writeHintsForLink(AS, m, linkRef);
		}
	}
	
	public static void writeHintsForBrick(ArchipelagoServices AS, IBNAModel m, ObjRef brickRef){
		BoxAssembly brickAssembly = StructureMapper.findBrickAssembly(AS, m, brickRef);
		if(brickAssembly != null){
			HintSupport.getInstance().writeProperty(AS, brickRef, 
				"glassBoundingBox", brickAssembly.getBoxGlassThing().getBoundingBox());
			HintSupport.getInstance().writeProperty(AS, brickRef, 
				"boxColor", brickAssembly.getBoxThing().getColor());
			HintSupport.getInstance().writeProperty(AS, brickRef, 
				"labelColor", brickAssembly.getBoxedLabelThing().getColor());

			for(ObjRef interfaceRef : AS.xarch.getAll(brickRef, "interface")){
				writeHintsForInterface(AS, m, interfaceRef);
			}
		}
	}
	
	public static void writeHintsForInterface(ArchipelagoServices AS, IBNAModel m, ObjRef interfaceRef){
		EndpointAssembly endpointAssembly = StructureMapper.findInterfaceAssembly(AS, m, interfaceRef);
		if(endpointAssembly != null){
			HintSupport.getInstance().writeProperty(AS, interfaceRef,
				"glassAnchorPoint", endpointAssembly.getEndpointGlassThing().getAnchorPoint());
			HintSupport.getInstance().writeProperty(AS, interfaceRef,
				"tagAnchorPoint", endpointAssembly.getTagThing().getAnchorPoint());
			HintSupport.getInstance().writeProperty(AS, interfaceRef,
				"tagVisible", endpointAssembly.getTagThing().isVisible());
			HintSupport.getInstance().writeProperty(AS, interfaceRef,
				"tagAngle", endpointAssembly.getTagThing().getAngle());
		}
	}
	
	public static void writeHintsForLink(ArchipelagoServices AS, IBNAModel m, ObjRef linkRef){
		StickySplineAssembly linkAssembly = StructureMapper.findLinkAssembly(AS, m, linkRef);
		if(linkAssembly != null){
			HintSupport.getInstance().writeProperty(AS, linkRef, 
				"glassEndpoint1", linkAssembly.getSplineGlassThing().getEndpoint1());
			HintSupport.getInstance().writeProperty(AS, linkRef, 
				"glassEndpoint2", linkAssembly.getSplineGlassThing().getEndpoint2());
			HintSupport.getInstance().writeProperty(AS, linkRef, 
				"glassMidpoints", linkAssembly.getSplineGlassThing().getMidpoints());
			HintSupport.getInstance().writeProperty(AS, linkRef, 
				"splineColor", linkAssembly.getSplineThing().getColor());
		}
	}
	
	/* --------------------------------- */

	public static void readHintsForStructure(ArchipelagoServices AS, IBNAModel m, ObjRef structureRef){
		for(ObjRef componentRef : AS.xarch.getAll(structureRef, "component")){
			readHintsForBrick(AS, m, componentRef);
		}
		for(ObjRef connectorRef : AS.xarch.getAll(structureRef, "connector")){
			readHintsForBrick(AS, m, connectorRef);
		}
		for(ObjRef linkRef : AS.xarch.getAll(structureRef, "link")){
			readHintsForLink(AS, m, linkRef);
		}
	}
	
	public static void readHintsForBrick(ArchipelagoServices AS, IBNAModel m, ObjRef brickRef){
		BoxAssembly brickAssembly = StructureMapper.findBrickAssembly(AS, m, brickRef);

		if((brickAssembly != null) && (brickRef != null)){
			Rectangle glassBoundingBox = (Rectangle)HintSupport.getInstance().readProperty(AS, brickRef, "glassBoundingBox");
			if(glassBoundingBox != null){
				brickAssembly.getBoxGlassThing().setBoundingBox(glassBoundingBox);
			}

			RGB boxColor = (RGB)HintSupport.getInstance().readProperty(AS, brickRef, "boxColor");
			if(boxColor != null){
				brickAssembly.getBoxThing().setColor(boxColor);
			}
			
			RGB labelColor = (RGB)HintSupport.getInstance().readProperty(AS, brickRef, "labelColor");
			if(labelColor != null){
				brickAssembly.getBoxedLabelThing().setColor(labelColor);
			}
			
			for(ObjRef interfaceRef : AS.xarch.getAll(brickRef, "interface")){
				readHintsForInterface(AS, m, interfaceRef);
			}
		}
	}
	
	public static void readHintsForInterface(ArchipelagoServices AS, IBNAModel m, ObjRef interfaceRef){
		EndpointAssembly endpointAssembly = StructureMapper.findInterfaceAssembly(AS, m, interfaceRef);
		
		if((endpointAssembly != null) && (interfaceRef != null)){

			Point glassAnchorPoint = (Point)HintSupport.getInstance().readProperty(AS, interfaceRef, "glassAnchorPoint");
			if(glassAnchorPoint != null){
				endpointAssembly.getEndpointGlassThing().setAnchorPoint(glassAnchorPoint);
			}
			
			Point tagAnchorPoint = (Point)HintSupport.getInstance().readProperty(AS, interfaceRef, "tagAnchorPoint");
			if(tagAnchorPoint != null){
				endpointAssembly.getTagThing().setAnchorPoint(tagAnchorPoint);
			}
			
			Boolean tagVisible = (Boolean)HintSupport.getInstance().readProperty(AS, interfaceRef, "tagVisible");
			if(tagVisible != null){
				endpointAssembly.getTagThing().setVisible(tagVisible);
			}
			
			Integer tagAngle = (Integer)HintSupport.getInstance().readProperty(AS, interfaceRef, "tagAngle");
			if(tagAngle != null){
				endpointAssembly.getTagThing().setAngle(tagAngle);
			}
		}
	}
	
	public static void readHintsForLink(ArchipelagoServices AS, IBNAModel m, ObjRef linkRef){
		StickySplineAssembly linkAssembly = StructureMapper.findLinkAssembly(AS, m, linkRef);

		if((linkAssembly != null) && (linkRef != null)){
			Point glassEndpoint1 = (Point)HintSupport.getInstance().readProperty(AS, linkRef, "glassEndpoint1");
			if(glassEndpoint1 != null){
				linkAssembly.getSplineGlassThing().setEndpoint1(glassEndpoint1);
			}
			
			Point glassEndpoint2 = (Point)HintSupport.getInstance().readProperty(AS, linkRef, "glassEndpoint2");
			if(glassEndpoint2 != null){
				linkAssembly.getSplineGlassThing().setEndpoint2(glassEndpoint2);
			}
			
			Point[] glassMidpoints = (Point[])HintSupport.getInstance().readProperty(AS, linkRef, "glassMidpoints");
			if(glassMidpoints != null){
				linkAssembly.getSplineGlassThing().setMidpoints(glassMidpoints);
			}
			
			RGB splineColor = (RGB)HintSupport.getInstance().readProperty(AS, linkRef, "splineColor");
			if(splineColor != null){
				linkAssembly.getSplineThing().setColor(splineColor);
			}
		}
	}
	
	
}
