package org.archstudio.aim.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.domain_3_0.Domain;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTQuery;
import org.archstudio.xarchadt.common.ObjRef;

public class AIMInstantiationOrderCalculator {
	
	private static class BrickNode {
		public ObjRef brickRef;
		public Set<BrickNode> topBricks = new HashSet<BrickNode>();
		public Set<BrickNode> bottomBricks = new HashSet<BrickNode>();
		
		public BrickNode(ObjRef brickRef) {
			this.brickRef = brickRef;
		}
	}

	// Returns a list of ObjRef to bricks
	public static List<ObjRef> calculateInstantiationOrder(IXArchADT xarch, ObjRef structureRef) {
		List<BrickNode> brickNodeList = calculateBrickGraph(xarch, structureRef);
		List<BrickNode> sortedBrickNodeList = new ArrayList<BrickNode>();
		
		while (!brickNodeList.isEmpty()) {
			List<BrickNode> zeroDependencyBricks = new ArrayList<BrickNode>();
			
			// Find all the (remaining) bricks with no top dependencies
			for (BrickNode brickNode : brickNodeList) {
				if (brickNode.topBricks.size() == 0) {
					zeroDependencyBricks.add(brickNode);
				}
			}
			
			// Move them to the sorted list
			brickNodeList.removeAll(zeroDependencyBricks);
			sortedBrickNodeList.addAll(zeroDependencyBricks);
			
			// Remove all the top-pointers to these bricks (as if we're slicing a layer off the graph)
			for (BrickNode brickNode : brickNodeList) {
				brickNode.topBricks.removeAll(zeroDependencyBricks);
			}
			
			// Now, on our next trip through the list, we will slice off the next set, and the next,
			// and so on, until the sortedBrickNodeList contains all brick nodes
		}
		
		List<ObjRef> orderedBrickRefs = new ArrayList<ObjRef>(sortedBrickNodeList.size());
		for (BrickNode bn : sortedBrickNodeList) {
			orderedBrickRefs.add(bn.brickRef);
		}
		return orderedBrickRefs;
	}
	
	private static List<BrickNode> calculateBrickGraph(IXArchADT xarch, ObjRef structureRef) { 
		List<BrickNode> brickNodeList = new ArrayList<BrickNode>();

		for (ObjRef brickRef : xarch.getAll(structureRef, "component")) {
			brickNodeList.add(new BrickNode(brickRef));
		}
		for (ObjRef brickRef : xarch.getAll(structureRef, "connector")) {
			brickNodeList.add(new BrickNode(brickRef));
		}
		
		List<ObjRef> linkRefs = xarch.getAll(structureRef, "link");
		
		for (BrickNode brickNode : brickNodeList) {
			for (ObjRef interfaceRef : xarch.getAll(brickNode.brickRef, "interface")) {
				DomainType interfaceDomainType = getDomain(xarch, interfaceRef);
				if (interfaceDomainType != null) {
					// Find all the bricks connected on this interface
					for (ObjRef linkRef : linkRefs) {
						ObjRef point1Ref = (ObjRef)xarch.get(linkRef, "point1");
						ObjRef point2Ref = (ObjRef)xarch.get(linkRef, "point2");
						
						if ((point1Ref != null) && (point2Ref != null)) {
							ObjRef otherInterfaceRef = null;
							
							// See if this link points at this interface
							if (xarch.equals(interfaceRef, point1Ref)) {
								otherInterfaceRef = point2Ref;
							}
							else if (xarch.equals(interfaceRef, point2Ref)) {
								otherInterfaceRef = point1Ref;
							}
							
							// If one endpoint does, look at the brick on the other side
							if (otherInterfaceRef != null) {
								ObjRef otherBrickRef = xarch.getParent(otherInterfaceRef);
								
								// Find the brick node for this other brick
								BrickNode otherBrickNode = null;
								for (BrickNode bn : brickNodeList) {
									if (xarch.equals(bn.brickRef, otherBrickRef)) {
										otherBrickNode = bn;
										break;
									}
								}
								
								if (otherBrickNode != null) {
									switch (interfaceDomainType.getValue()) {
									case DomainType.TOP_VALUE:
										brickNode.topBricks.add(otherBrickNode);
										break;
									case DomainType.BOTTOM_VALUE:
										brickNode.bottomBricks.add(otherBrickNode);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return brickNodeList;
	}

	public static DomainType getDomain(IXArchADTQuery xarch, ObjRef interfaceRef) {
		ObjRef domainExtRef = XadlUtils.getExt(xarch, interfaceRef, "org.archstudio.xadl3.domain_3_0.DomainExtension");
		if (domainExtRef != null) {
			ObjRef domainRef = (ObjRef) xarch.get(domainExtRef, "domain");
			if (domainRef != null) {
				DomainType domainType = (DomainType) xarch.get(domainRef, "type");
				return domainType;
			}
		}
		return null;
	}

}
