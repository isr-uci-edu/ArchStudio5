package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.IArchipelagoEvent;
import org.archstudio.archipelago.core.structure.StructureMapper.BrickKind;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.MappingAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;

public class StructureEvents{
	private StructureEvents(){}
	
	public static class WorldCreatedEvent implements IArchipelagoEvent{
		protected final ObjRef structureRef;
		protected final IBNAWorld world;

		public WorldCreatedEvent(ObjRef structureRef, IBNAWorld world){
			this.structureRef = structureRef;
			this.world = world;
		}

		public ObjRef getStructureRef(){
			return structureRef;
		}

		public IBNAWorld getWorld(){
			return world;
		}
	}
	
	public static class StructureUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final ObjRef structureRef;

		public StructureUpdatedEvent(IBNAWorld bnaWorld, ObjRef structureRef){
			this.bnaWorld = bnaWorld;
			this.structureRef = structureRef;
		}

		public ObjRef getStructureRef(){
			return structureRef;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}
	}

	public static class BrickUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final ObjRef brickRef;
		protected final BrickKind kind;
		protected final BoxAssembly brickAssembly;
		
		public BrickUpdatedEvent(IBNAWorld bnaWorld, ObjRef brickRef, BrickKind kind, BoxAssembly brickAssembly){
			this.bnaWorld = bnaWorld;
			this.brickRef = brickRef;
			this.kind = kind;
			this.brickAssembly = brickAssembly;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}

		public ObjRef getBrickRef(){
			return brickRef;
		}

		public BrickKind getBrickKind(){
			return kind;
		}
		
		public BoxAssembly getBrickAssembly(){
			return brickAssembly;
		}
	}
	
	public static class SubarchitectureUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final BoxAssembly brickAssembly;
		protected final ObjRef brickRef;
		
		public SubarchitectureUpdatedEvent(IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef){
			this.bnaWorld = bnaWorld;
			this.brickAssembly = brickAssembly;
			this.brickRef = brickRef;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}

		public BoxAssembly getBrickAssembly(){
			return brickAssembly;
		}

		public ObjRef getBrickRef(){
			return brickRef;
		}
	}
	
	public static class InterfaceMappingUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final BoxAssembly brickAssembly;
		protected final ObjRef brickRef;
		protected final IBNAWorld innerWorld;
		protected final ObjRef interfaceMappingRef;
		protected final MappingAssembly mappingAssembly;
		
		public InterfaceMappingUpdatedEvent(IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef brickRef, IBNAWorld innerWorld, ObjRef interfaceMappingRef, MappingAssembly mappingAssembly){
			this.bnaWorld = bnaWorld;
			this.brickAssembly = brickAssembly;
			this.brickRef = brickRef;
			this.innerWorld = innerWorld;
			this.interfaceMappingRef = interfaceMappingRef;
			this.mappingAssembly = mappingAssembly;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}

		public BoxAssembly getBrickAssembly(){
			return brickAssembly;
		}

		public ObjRef getBrickRef(){
			return brickRef;
		}

		public IBNAWorld getInnerWorld(){
			return innerWorld;
		}

		public ObjRef getInterfaceMappingRef(){
			return interfaceMappingRef;
		}
		
		public MappingAssembly getMappingAssembly(){
			return mappingAssembly;
		}
	}
	
	public static class InterfaceUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final BoxAssembly brickAssembly;
		protected final ObjRef interfaceRef;
		protected final EndpointAssembly interfaceAssembly;
		
		public InterfaceUpdatedEvent(IBNAWorld bnaWorld, BoxAssembly brickAssembly, ObjRef interfaceRef, EndpointAssembly interfaceAssembly){
			this.bnaWorld = bnaWorld;
			this.brickAssembly = brickAssembly;
			this.interfaceRef = interfaceRef;
			this.interfaceAssembly = interfaceAssembly;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}

		public BoxAssembly getBrickAssembly(){
			return brickAssembly;
		}

		public ObjRef getInterfaceRef(){
			return interfaceRef;
		}
		
		public EndpointAssembly getInterfaceAssembly(){
			return interfaceAssembly;
		}
	}
	
	public static class LinkUpdatedEvent implements IArchipelagoEvent{
		protected final IBNAWorld bnaWorld;
		protected final ObjRef linkRef;
		protected final StickySplineAssembly linkAssembly;
		
		public LinkUpdatedEvent(IBNAWorld bnaWorld, ObjRef linkRef, StickySplineAssembly linkAssembly){
			this.bnaWorld = bnaWorld;
			this.linkRef = linkRef;
			this.linkAssembly = linkAssembly;
		}

		public IBNAWorld getBNAWorld(){
			return bnaWorld;
		}

		public ObjRef getLinkRef(){
			return linkRef;
		}
		
		public StickySplineAssembly getLinkAssembly(){
			return linkAssembly;
		}
	}
}
