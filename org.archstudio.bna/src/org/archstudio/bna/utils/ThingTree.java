package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.List;

import org.archstudio.bna.IThing;

import com.google.common.collect.Lists;

class ThingTree {

	private static class Node {

		final IThing t;
		final List<Node> children = Lists.newArrayList();
		Node parent;

		public Node(IThing t) {
			this.t = t;
		}
	}

	final Node root = new Node(null);
	final FastIntMap<Node> lookup = new FastIntMap<Node>(1000);

	public ThingTree() {
	}

	protected Node getNode(IThing t, boolean create) {
		if (t == null) {
			return root;
		}
		Node node = lookup.get(t.getUID());
		if (node == null && create) {
			lookup.put(t.getUID(), node = new Node(t));
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
	}

	public void remove(IThing t) {
		Node childNode = lookup.remove(t.getUID());
		if (childNode != null) {
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
	}

	protected void move(Node parentNode, Node childNode, int toIndex) {
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
	}

	public void bringToFront(IThing t) {
		Node childNode = getNode(t, false);
		if (childNode != null) {
			Node parentNode = childNode.parent;
			if (parentNode != null) {
				move(parentNode, childNode, -1);
			}
		}
	}

	public void sendToBack(IThing t) {
		Node childNode = getNode(t, false);
		if (childNode != null) {
			Node parentNode = childNode.parent;
			if (parentNode != null) {
				move(parentNode, childNode, 0);
			}
		}
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
	}

	protected List<IThing> appendAllDescendants(Node parent, List<IThing> toList) {
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

	public List<IThing> getAllThings() {
		List<IThing> allThings = Lists.newArrayListWithCapacity(size());
		appendAllDescendants(root, allThings);
		return allThings;
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
		return lookup.size();
	}
}
