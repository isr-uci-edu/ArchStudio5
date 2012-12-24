package org.archstudio.aim.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class AIMInstantiationOrderCalculator {

	public static interface OrderedGroup {

		public Iterable<ObjRef> getBrickRefs();

		public Iterable<ObjRef> getLinkRefs();
	}

	private static class BrickNode implements OrderedGroup {
		public ObjRef brickRef;
		public Set<BrickNode> topBricks = Sets.newHashSet();
		public Set<ObjRef> topLinksOut = Sets.newHashSet();
		public Set<ObjRef> topLinksIn = Sets.newHashSet();
		public Set<ObjRef> topLinksOther = Sets.newHashSet();
		public Set<ObjRef> otherLinks = Sets.newHashSet();

		public BrickNode(ObjRef brickRef) {
			this.brickRef = brickRef;
		}

		public Iterable<ObjRef> getBrickRefs() {
			return brickRef == null ? Collections.<ObjRef> emptyList() : Collections.singletonList(brickRef);
		}

		public Iterable<ObjRef> getLinkRefs() {
			return Iterables.concat(topLinksOut, topLinksIn, topLinksOther, otherLinks);
		}
	}

	/**
	 * Returns an ordered list of brick and links to instantiate such that:
	 * <ul>
	 * <li>bricks on "top" will be instantiated before bricks on "bottom"</li>
	 * <li>links directed "out" and on "top" will be welded before links
	 * directed "in" and on "bottom"</li>
	 * </ul>
	 */
	public static List<? extends OrderedGroup> calculateInstantiationOrder(IXArchADT xarch, ObjRef structureRef) {
		List<BrickNode> brickNodes = Lists.newArrayList(calculateBrickGraph(xarch, structureRef));
		List<BrickNode> sortedBrickNodeList = new ArrayList<BrickNode>();

		while (!brickNodes.isEmpty()) {
			Set<BrickNode> zeroDependencyBricks = Sets.newHashSet();

			// Find all the (remaining) bricks with no top dependencies
			for (BrickNode brickNode : brickNodes) {
				if (brickNode.topBricks.size() == 0) {
					zeroDependencyBricks.add(brickNode);
				}
			}

			// at some point, we may end up with no zero dependency bricks left,
			// in which case there is a cycle
			if (zeroDependencyBricks.size() == 0) {
				System.err.println("Cycle(s) detected in: ");
				for (BrickNode n : brickNodes) {
					System.err.println(" - " + xarch.get(n.brickRef, "name"));
				}
				zeroDependencyBricks.addAll(brickNodes);
			}

			// Move them to the sorted list
			brickNodes.removeAll(zeroDependencyBricks);
			sortedBrickNodeList.addAll(zeroDependencyBricks);

			// Remove all the top-pointers to these bricks (as if we're slicing a layer off the graph)to top bricks 
			for (BrickNode brickNode : brickNodes) {
				brickNode.topBricks.removeAll(zeroDependencyBricks);
			}

			// Now, on our next trip through the list, we will slice off the next set, and the next,
			// and so on, until the sortedBrickNodeList contains all brick nodes
		}

		List<ObjRef> orderedBrickRefs = new ArrayList<ObjRef>(sortedBrickNodeList.size());
		for (BrickNode bn : sortedBrickNodeList) {
			orderedBrickRefs.add(bn.brickRef);
		}
		return sortedBrickNodeList;
	}

	private static Iterable<BrickNode> calculateBrickGraph(IXArchADT xarch, ObjRef structureRef) {
		Map<ObjRef, BrickNode> brickNodes = Maps.newHashMap();

		for (ObjRef brickRef : Iterables.filter(xarch.getAll(structureRef, "component"), ObjRef.class)) {
			brickNodes.put(brickRef, new BrickNode(brickRef));
		}
		for (ObjRef brickRef : Iterables.filter(xarch.getAll(structureRef, "connector"), ObjRef.class)) {
			brickNodes.put(brickRef, new BrickNode(brickRef));
		}

		// scan the links to populate the "top" fields of each brick
		for (ObjRef linkRef : Iterables.filter(xarch.getAll(structureRef, "link"), ObjRef.class)) {
			ObjRef iface1Ref = (ObjRef) xarch.get(linkRef, "point1");
			ObjRef iface2Ref = (ObjRef) xarch.get(linkRef, "point2");
			if (iface1Ref != null && iface2Ref != null) {
				BrickNode brick1Node = brickNodes.get(xarch.getParent(iface1Ref));
				BrickNode brick2Node = brickNodes.get(xarch.getParent(iface2Ref));
				if (brick1Node != null && brick2Node != null) {

					// see if iface1 is a "top" or "bottom" domain type
					DomainType iface1DomainType = getDomain(xarch, iface1Ref);
					DomainType iface2DomainType = getDomain(xarch, iface2Ref);
					if (iface1DomainType == null) {
						// if it is null, use the opposite domain of iface2's domain type
						if (iface2DomainType != null) {
							switch (iface2DomainType) {
							case TOP:
								iface1DomainType = DomainType.BOTTOM;
								break;
							case BOTTOM:
								iface1DomainType = DomainType.TOP;
								break;
							}
						}
					}

					// assign the "top" brick to the "bottom" brick's "top" list
					if (iface1DomainType != null) {
						// determine which brick is on "top" 
						BrickNode topBrickNode = brick1Node;
						BrickNode bottomBrickNode = brick2Node;
						ObjRef bottomIfaceRef = iface2Ref;
						if (iface1DomainType == DomainType.TOP) {
							topBrickNode = brick2Node;
							bottomBrickNode = brick1Node;
							bottomIfaceRef = iface1Ref;
						}
						bottomBrickNode.topBricks.add(topBrickNode);

						// add the link to the "bottom" brick's "top" links
						Direction bottomDir = (Direction) xarch.get(bottomIfaceRef, "direction");
						if (bottomDir != null) {
							switch (bottomDir) {
							case IN:
								bottomBrickNode.topLinksIn.add(linkRef);
								continue;
							case OUT:
								bottomBrickNode.topLinksOut.add(linkRef);
								continue;
							default:
								throw new IllegalArgumentException();
							}
						}
						bottomBrickNode.topLinksOther.add(linkRef);
					}
					brick1Node.otherLinks.add(linkRef);
				}
			}
		}

		return brickNodes.values();
	}

	public static DomainType getDomain(IXArchADTQuery xarch, ObjRef interfaceRef) {
		ObjRef domainExtRef = XadlUtils.getExt(xarch, interfaceRef, Domain_3_0Package.eNS_URI, "DomainExtension");
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
