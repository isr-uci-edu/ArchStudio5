package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.FastIntMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

class ThingTree {

	private static class Node {

		final IThing t;
		final List<Node> children = Lists.newArrayList();
		Node parent;

		public Node(IThing t) {
			this.t = t;
		}
	}

	private final Node root = new Node(null);
	private final FastIntMap<Node> nodesByUID = new FastIntMap<Node>(1000);
	private final Map<Object, Node> nodesByID = Maps.newHashMap();
	private List<IThing> allThings = null;

	public ThingTree() {
	}

	public IThing getThing(int uid) {
		Node node = nodesByUID.get(uid);
		return node != null ? node.t : null;
	}

	public IThing getThing(Object id) {
		Node node = nodesByID.get(id);
		return node != null ? node.t : null;
	}

	private Node getNode(IThing t, boolean create) {
		if (t == null) {
			return root;
		}
		Node node = nodesByUID.get(t.getUID());
		if (node == null && create) {
			nodesByUID.put(t.getUID(), node = new Node(t));
			nodesByID.put(t.getID(), node);
		}
		return node;
	}

	public void add(IThing t) {
		add(t, null);
	}

	public void add(IThing t, IThing parent) {
		Node parentNode = checkNotNull(getNode(parent, false));
		Node childNode = getNode(t, true);
		if (childNode.parent != null) {
			throw new IllegalArgumentException("Thing already added!");
		}
		parentNode.children.add(childNode);
		childNode.parent = parentNode;
		if (parent != null) {
			allThings = null;
		}
		else if (allThings != null) {
			allThings.add(t);
		}
	}

	public void insert(IThing t, IThing beforeThing) {
		Node parentNode = checkNotNull(getNode(getParent(beforeThing), false));
		Node beforeNode = checkNotNull(getNode(beforeThing, false));
		Node childNode = getNode(t, true);
		if (childNode.parent != null) {
			throw new IllegalArgumentException("Thing already added!");
		}
		parentNode.children.add(parentNode.children.indexOf(beforeNode), childNode);
		childNode.parent = parentNode;
		allThings = null;
	}

	public void remove(IThing t) {
		Node childNode = nodesByUID.remove(t.getUID());
		if (childNode != null) {
			nodesByID.remove(t.getID());
			Node parentNode = childNode.parent;
			if (parentNode != null) {
				List<Node> siblings = parentNode.children;
				int index = siblings.indexOf(childNode);
				if (index >= 0) {
					siblings.remove(index);
					siblings.addAll(index, childNode.children);
					for (Node grandchild : childNode.children) {
						grandchild.parent = parentNode;
					}
				}
			}
		}
		if (allThings != null) {
			if (allThings.size() > 0 && allThings.get(allThings.size() - 1).equals(t)) {
				allThings.remove(allThings.size() - 1);
			}
			else {
				allThings = null;
			}
		}
	}

	private void move(Node parentNode, Node childNode, int toIndex) {
		List<Node> siblings = parentNode.children;
		int index = siblings.indexOf(childNode);
		if (toIndex < 0) {
			toIndex = siblings.size() + toIndex + 1;
		}
		if (index >= 0 && index != toIndex) {
			siblings.remove(index);
			if (index < toIndex) {
				toIndex--;
			}
			siblings.add(toIndex, childNode);
		}
		allThings = null;
	}

	public void bringToFront(IThing t) {
		Node childNode = getNode(t, false);
		if (childNode != null) {
			Node parentNode = childNode.parent;
			if (parentNode != null) {
				move(parentNode, childNode, -1);
			}
		}
		allThings = null;
	}

	public void sendToBack(IThing t) {
		Node childNode = getNode(t, false);
		if (childNode != null) {
			Node parentNode = childNode.parent;
			if (parentNode != null) {
				move(parentNode, childNode, 0);
			}
		}
		allThings = null;
	}

	public void moveAfter(IThing t, IThing referenceThing) {
		Node childNode = getNode(t, false);
		Node referenceNode = getNode(referenceThing, false);
		if (childNode != null && referenceNode != null) {
			Node parentNode = childNode.parent;
			if (parentNode != null && parentNode == referenceNode.parent) {
				int referenceIndex = parentNode.children.indexOf(referenceNode);
				if (referenceIndex >= 0) {
					move(parentNode, childNode, referenceIndex + 1);
				}
			}
		}
		allThings = null;
	}

	public void reparent(IThing newParentThing, IThing thing) {
		Node newParentThingNode = getNode(newParentThing, false);
		Node thingNode = getNode(thing, false);
		if (newParentThingNode != null && thingNode != null) {
			if (thingNode.parent != null) {
				thingNode.parent.children.remove(thingNode);
				thingNode.parent = null;
			}
			newParentThingNode.children.add(thingNode);
			thingNode.parent = newParentThingNode;
		}
		allThings = null;
	}

	private List<IThing> appendAllDescendants(Node parent, List<IThing> toList) {
		for (Node child : parent.children) {
			toList.add(child.t);
			appendAllDescendants(child, toList);
		}
		return toList;
	}

	public List<IThing> getAllDescendantThings(IThing t) {
		Node parentNode = getNode(t, false);
		if (parentNode != null) {
			return appendAllDescendants(parentNode, Lists.<IThing> newArrayList(t));
		}
		return Collections.emptyList();
	}

	private List<IThing> _getAllThings() {
		synchronized (this) {
			if (allThings == null) {
				List<IThing> allThings = Lists.newArrayListWithCapacity(size());
				appendAllDescendants(root, allThings);
				this.allThings = allThings;
			}
		}
		return allThings;
	}

	public List<IThing> getAllThings() {
		return Lists.newArrayList(_getAllThings());
	}

	public List<IThing> getAncestorThings(IThing t) {
		List<IThing> ancestors = Lists.newArrayList();
		if (t != null) {
			Node node = getNode(t, false);
			while (node != null) {
				ancestors.add(node.t);
				node = node.parent;
			}
		}
		return ancestors;
	}

	public List<IThing> getChildThings(IThing t) {
		Node parentNode = getNode(t, false);
		if (parentNode != null && parentNode.children.size() > 0) {
			List<IThing> children = Lists.newArrayList();
			for (Node child : parentNode.children) {
				children.add(child.t);
			}
			return children;
		}
		return Collections.emptyList();
	}

	public IThing getParent(IThing t) {
		if (t != null) {
			Node childNode = getNode(t, false);
			if (childNode != null) {
				return childNode.parent.t;
			}
		}
		return null;
	}

	public int size() {
		return nodesByUID.size();
	}

	private FastIntMap<Long> _getOrderByUID() {
		synchronized (this) {
			FastIntMap<Long> orderByUID = new FastIntMap<>(nodesByUID.size());
			long orderByUIDSpread = Long.MAX_VALUE / (nodesByUID.size() + 1);
			long orderByUIDNumber = Long.MIN_VALUE + orderByUIDSpread;
			for (IThing t : _getAllThings()) {
				orderByUID.put(t.getUID(), orderByUIDNumber);
				orderByUIDNumber += orderByUIDSpread;
			}
			return orderByUID;
		}
	}

	public List<IThing> sortBackToFront(Collection<IThing> things) {
		final FastIntMap<Long> orderByUID = _getOrderByUID();
		List<IThing> sorted = Lists.newArrayList(things);
		Collections.sort(sorted, new Comparator<IThing>() {
			@Override
			public int compare(IThing o1, IThing o2) {
				long lo1 = orderByUID.get(o1.getUID());
				long lo2 = orderByUID.get(o2.getUID());
				if (lo1 < lo2) {
					return -1;
				}
				if (lo1 == lo2) {
					return 0;
				}
				return 1;
			}
		});
		return sorted;
	}
}
