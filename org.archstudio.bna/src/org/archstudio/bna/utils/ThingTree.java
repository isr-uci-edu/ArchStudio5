package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.FastMap;

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

	private final Node root = new Node(null);
	private final FastMap<Object, Node> nodesByID = new FastMap<>(false);
	private List<IThing> allThings = null;

	public ThingTree() {
	}

	private Node getNode(IThing t) {
		if (t == null) {
			return root;
		}
		return checkNotNull(nodesByID.get(t.getID()), "Thing not added: %s", t);
	}

	private Node createNode(IThing t) {
		Map.Entry<Object, Node> entry = nodesByID.createEntry(t.getID());
		if (entry.getValue() != null) {
			throw new IllegalArgumentException("Thing already added: " + t);
		}
		Node node = new Node(t);
		entry.setValue(node);
		return node;
	}

	public IThing getThing(Object id) {
		Node node = nodesByID.get(id);
		return node != null ? node.t : null;
	}

	public void add(IThing t) {
		Node parentNode = root;
		Node childNode = createNode(t);
		parentNode.children.add(childNode);
		childNode.parent = parentNode;
		if (allThings != null) {
			allThings.add(t);
		}
	}

	public void add(IThing t, IThing parentThing) {
		Node parentNode = getNode(parentThing);
		Node childNode = createNode(t);
		parentNode.children.add(childNode);
		childNode.parent = parentNode;
		allThings = null;
	}

	public void insert(IThing t, IThing beforeThing) {
		Node beforeNode = getNode(beforeThing);
		Node parentNode = beforeNode.parent;
		Node childNode = createNode(t);
		parentNode.children.add(parentNode.children.indexOf(beforeNode), childNode);
		childNode.parent = parentNode;
		allThings = null;
	}

	public void remove(IThing t) {
		Node childNode = nodesByID.remove(t.getID());
		if (childNode != null) {
			Node parentNode = childNode.parent;
			List<Node> siblings = parentNode.children;
			int index = siblings.indexOf(childNode);
			siblings.remove(index);
			siblings.addAll(index, childNode.children);
			for (Node grandchild : childNode.children) {
				grandchild.parent = parentNode;
			}
			if (allThings != null && allThings.size() > 0) {
				int lastIndex = allThings.size() - 1;
				if (allThings.get(lastIndex).equals(t)) {
					allThings.remove(lastIndex);
				}
				else {
					allThings = null;
				}
			}
		}
	}

	private void move(Node childNode, int toIndex) {
		List<Node> siblings = childNode.parent.children;
		if (toIndex < 0) {
			toIndex = siblings.size() + toIndex + 1;
		}
		int index = siblings.indexOf(childNode);
		if (index != toIndex) {
			siblings.remove(index);
			if (index < toIndex) {
				toIndex--;
			}
			siblings.add(toIndex, childNode);
			allThings = null;
		}
	}

	public void bringToFront(IThing t) {
		Node childNode = getNode(t);
		move(childNode, -1);
	}

	public void sendToBack(IThing t) {
		Node childNode = getNode(t);
		move(childNode, 0);
	}

	public void moveAfter(IThing t, IThing afterThing) {
		Node childNode = getNode(t);
		Node afterNode = getNode(afterThing);
		if (childNode.parent != afterNode.parent) {
			throw new IllegalArgumentException("Things do not share a parent!");
		}
		int afterIndex = afterNode.parent.children.indexOf(afterNode);
		move(childNode, afterIndex + 1);
	}

	public void reparent(IThing t, IThing parentThing) {
		Node parentNode = getNode(parentThing);
		Node childNode = getNode(t);
		childNode.parent.children.remove(childNode);
		childNode.parent = parentNode;
		parentNode.children.add(childNode);
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
		Node node = getNode(t);
		return appendAllDescendants(node, Lists.<IThing> newArrayList(t));
	}

	public List<IThing> getAllThings() {
		if (allThings == null) {
			allThings = Lists.newArrayListWithCapacity(size());
			appendAllDescendants(root, allThings);
		}
		return Lists.newArrayList(allThings);
	}

	public List<IThing> getAncestorThings(IThing t) {
		Node node = getNode(t);
		List<IThing> ancestors = Lists.newArrayList();
		do {
			ancestors.add(node.t);
			node = node.parent;
		} while (node != null && node != root);
		return ancestors;
	}

	public List<IThing> getChildThings(IThing t) {
		Node parentNode = getNode(t);
		List<IThing> children = Lists.newArrayListWithCapacity(parentNode.children.size());
		for (Node child : parentNode.children) {
			children.add(child.t);
		}
		return children;
	}

	public IThing getParent(IThing t) {
		Node childNode = getNode(t);
		return childNode.parent.t;
	}

	public int size() {
		return nodesByID.size();
	}

}
