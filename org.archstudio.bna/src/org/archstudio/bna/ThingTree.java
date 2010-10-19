package org.archstudio.bna;

import java.util.*;

class ThingTree implements Iterable<IThing> {
	//The ThingTree isn't a proper tree.  At its base, it's a list of Nodes,
	//each of which (by default) contains one Thing.  However, each node
	//may, in addition to its basic Thing, contain a list of child Things
	//that will be drawn stacked directly atop the parent Thing in depth-first fashion

	protected ArrayList<ThingNode> rootNodeList = null;
	protected int size = 0;

	private class ThingCache {
		private List<IThing> cachedThingList = new ArrayList<IThing>();
		private boolean dirty = true;
		
		public ThingCache() {
		}

		private void appendToThingList(List<IThing> thingList, List<ThingNode> nodeList) {
			int nodeListLen = nodeList.size();
			for (int i = 0; i < nodeListLen; i++) {
				ThingNode n = (ThingNode) nodeList.get(i);
				thingList.add(n.thing);
				appendToThingList(thingList, n.childList);
			}
		}
		
		private void update() {
			if (dirty) {
				synchronized(this) {
					cachedThingList = new ArrayList<IThing>(size);
					appendToThingList(cachedThingList, rootNodeList);
					dirty = false;
				}
			}
		}
		
		private void markDirty() {
			this.dirty = true;
		}
		
		public List<IThing> get() {
			update();
			return cachedThingList;
		}
	}
	
	private final ThingCache thingCache = new ThingCache();

	public ThingTree() {
		rootNodeList = new ArrayList<ThingNode>();
	}

	public void add(IThing t) {
		ThingNode newNode = new ThingNode();
		newNode.thing = t;
		newNode.parentNode = null;
		rootNodeList.add(newNode);
		size++;
		thingCache.markDirty();
	}

	public void add(IThing t, IThing parent) {
		ThingNode parentNode = findThingNode(parent);
		if (parentNode != null) {
			ThingNode newNode = new ThingNode();
			newNode.parentNode = parentNode;
			newNode.thing = t;
			parentNode.childList.add(newNode);
			size++;
		}
		else {
			System.err.println("ThingTree:add() WARNING: Parent not found.");
			ThingNode newNode = new ThingNode();
			newNode.thing = t;
			newNode.parentNode = null;
			rootNodeList.add(newNode);
			size++;
		}
		thingCache.markDirty();
	}

	public void dumpThingTree(IThing t) {
		if (t == null) {
			for (ThingNode childNode : rootNodeList) {
				dumpThingNode(childNode, 0);
			}
		}
		else {
			ThingNode tn = findThingNode(t);
			if (tn == null)
				return;
			dumpThingNode(tn, 0);
		}
	}

	protected void dumpThingNode(ThingNode tn, int indent) {
		for (int i = 0; i < indent; i++) {
			System.err.print(' ');
		}
		System.err.println(tn.thing.getID());
		for (ThingNode childNode : tn.childList) {
			dumpThingNode(childNode, indent + 2);
		}
	}

	protected ThingNode findThingNode(IThing t) {
		return findThingNode(t, rootNodeList);
	}

	protected ThingNode findThingNode(IThing t, ArrayList<ThingNode> l) {
		int len = l.size();
		for (int i = 0; i < len; i++) {
			ThingNode n = (ThingNode) l.get(i);
			if (n.thing.equals(t)) {
				return n;
			}
			else {
				ThingNode foundNode = findThingNode(t, n.childList);
				if (foundNode != null) {
					return foundNode;
				}
			}
		}
		return null;
	}

	public boolean bringToFront(IThing thing) {
		return bringToFront(thing, rootNodeList);
	}

	protected boolean bringToFront(IThing thing, ArrayList<ThingNode> nodeList) {
		int len = nodeList.size();
		int thingIndex = -1;
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (n.thing.equals(thing)) {
				thingIndex = i;
				ThingNode tn = nodeList.remove(thingIndex);
				nodeList.add(nodeList.size(), tn);
				thingCache.markDirty();
				return true;
			}
		}

		//Didn't find it in the given list, check all the child node lists
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (bringToFront(thing, n.childList)) {
				return true;
			}
		}
		return false;
	}

	public boolean sendToBack(IThing thing) {
		return sendToBack(thing, rootNodeList);
	}

	protected boolean sendToBack(IThing thing, ArrayList<ThingNode> nodeList) {
		int len = nodeList.size();
		int thingIndex = -1;
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (n.thing.equals(thing)) {
				thingIndex = i;
				ThingNode tn = nodeList.remove(thingIndex);
				nodeList.add(0, tn);
				thingCache.markDirty();
				return true;
			}
		}

		//Didn't find it in the given list, check all the child node lists
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (sendToBack(thing, n.childList)) {
				return true;
			}
		}
		return false;
	}

	public boolean moveAfter(IThing firstThing, IThing secondThing) {
		return moveAfter(firstThing, secondThing, rootNodeList);
	}

	protected boolean moveAfter(IThing firstThing, IThing secondThing, ArrayList<ThingNode> nodeList) {
		int len = nodeList.size();
		int firstThingIndex = -1;
		int secondThingIndex = -1;
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (n.thing.equals(firstThing)) {
				firstThingIndex = i;
			}
			else if (n.thing.equals(secondThing)) {
				secondThingIndex = i;
			}
		}

		if ((firstThingIndex != -1) && (secondThingIndex != -1)) {
			if (firstThingIndex < secondThingIndex) {
				return true;
			}
			else {
				ThingNode secondThingNode = (ThingNode) nodeList.get(secondThingIndex);
				nodeList.remove(secondThingIndex);
				nodeList.add(secondThingNode);
				thingCache.markDirty();
				return true;
			}
		}

		//Didn't find it in the given list, check all the child node lists
		for (int i = 0; i < len; i++) {
			ThingNode n = nodeList.get(i);
			if (moveAfter(firstThing, secondThing, n.childList)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(IThing t) {
		return findThingNode(t) != null;
	}

	public boolean remove(IThing t) {
		return remove(t, rootNodeList);
	}

	//Removes just the given Thing; its children are all promoted to
	//replace the parent in-line
	protected boolean remove(IThing t, ArrayList<ThingNode> l) {
		int len = l.size();
		for (int i = 0; i < len; i++) {
			ThingNode n = l.get(i);
			if (n.thing.equals(t)) {
				ThingNode grandparentNode = n.parentNode;
				n.parentNode = null;
				l.remove(i);
				for (ThingNode child : n.childList) {
					child.parentNode = grandparentNode;
				}
				l.addAll(i, n.childList);
				size--;
				thingCache.markDirty();
				return true;
			}
			else {
				if (remove(t, n.childList)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeWithChildren(IThing t) {
		return removeWithChildren(t, rootNodeList);
	}

	//Removes just the given Thing; its children are removed too
	protected boolean removeWithChildren(IThing t, ArrayList<ThingNode> l) {
		int len = l.size();
		for (int i = 0; i < len; i++) {
			ThingNode n = (ThingNode) l.get(i);
			if (n.thing.equals(t)) {
				n.parentNode = null;
				l.remove(i);
				size -= (getChildCount(n) + 1);
				thingCache.markDirty();
				return true;
			}
			else {
				if (removeWithChildren(t, n.childList)) {
					return true;
				}
			}
		}
		return false;
	}

	public int size() {
		return size;
	}
	
	public List<IThing> asList() {
		return Collections.unmodifiableList(thingCache.get());
	}

	public ListIterator<IThing> listIterator(int index) {
		return asList().listIterator(index);
	}
	
	public Iterator<IThing> iterator() {
		return asList().iterator();
	}

	private int getChildCount(ThingNode tn) {
		int childCount = tn.childList.size();
		for (int i = 0; i < tn.childList.size(); i++) {
			childCount += getChildCount((ThingNode) tn.childList.get(i));
		}
		return childCount;
	}

	public List<IThing> getChildren(IThing t) {
		if (t == null) {
			List<IThing> rootListCopy = new ArrayList<IThing>(rootNodeList.size());
			for (ThingNode tn : rootNodeList) {
				rootListCopy.add(tn.thing);
			}
			return rootListCopy;
		}
		ThingNode roottn = findThingNode(t);
		if (roottn == null)
			return null;
		List<IThing> l = new ArrayList<IThing>(roottn.childList.size());
		for (ThingNode tn : roottn.childList) {
			l.add(tn.thing);
		}
		return l;
	}

	public IThing getParent(IThing t) {
		if (t == null)
			return null;
		ThingNode tn = findThingNode(t);
		if (tn == null)
			return null;
		ThingNode parentNode = tn.parentNode;
		if (parentNode == null) {
			return null;
		}
		return parentNode.thing;
	}

	public List<IThing> getAncestors(IThing t) {
		List<IThing> ancestorList = new ArrayList<IThing>();
		ThingNode tn = findThingNode(t);
		while (tn != null) {
			tn = tn.parentNode;
			if (tn != null) {
				ancestorList.add(tn.thing);
			}
		}
		return ancestorList;
	}

	private class ThingNode {
		public ThingNode parentNode = null;
		public IThing thing = null;
		public ArrayList<ThingNode> childList = new ArrayList<ThingNode>();

		public String toString() {
			//return "ThingNode{thing=" + thing + "];";
			return "ThingNode{thing=" + thing + "; childList=" + childList + "};";
		}
	}
}
