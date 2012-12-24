package org.archstudio.archipelago.core;

import java.util.HashMap;
import java.util.Map;

import org.archstudio.xarchadt.ObjRef;

public class TreeNodeDataCache implements IArchipelagoTreeNodeDataCache {

	protected Map<KeyTuple, Object> map = new HashMap<KeyTuple, Object>();

	public TreeNodeDataCache() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.archstudio.archipelago.core.ITreeNodeDataCache#setData(org.archstudio
	 * .xarchflat.ObjRef, java.lang.Object, java.lang.String, java.lang.Object)
	 */

	@Override
	public void setData(ObjRef xArchRef, Object treeNode, String key, Object data) {
		KeyTuple kt = new KeyTuple(xArchRef, treeNode, key);
		map.put(kt, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.archstudio.archipelago.core.ITreeNodeDataCache#getData(org.archstudio
	 * .xarchflat.ObjRef, java.lang.Object, java.lang.String)
	 */

	@Override
	public Object getData(ObjRef xArchRef, Object treeNode, String key) {
		KeyTuple kt = new KeyTuple(xArchRef, treeNode, key);
		return map.get(kt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.archstudio.archipelago.core.ITreeNodeDataCache#clear()
	 */

	@Override
	public void clear() {
		map.clear();
	}

	@SuppressWarnings("unused")
	private static class KeyTuple {
		private ObjRef xArchRef = null;
		private Object treeNode = null;
		private String key = null;

		public KeyTuple(ObjRef xArchRef, Object treeNode, String key) {
			this.xArchRef = xArchRef;
			this.treeNode = treeNode;
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public Object getTreeNode() {
			return treeNode;
		}

		public ObjRef getXArchRef() {
			return xArchRef;
		}

		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + (key == null ? 0 : key.hashCode());
			result = PRIME * result + (treeNode == null ? 0 : treeNode.hashCode());
			result = PRIME * result + (xArchRef == null ? 0 : xArchRef.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final KeyTuple other = (KeyTuple) obj;
			if (key == null) {
				if (other.key != null) {
					return false;
				}
			}
			else if (!key.equals(other.key)) {
				return false;
			}
			if (treeNode == null) {
				if (other.treeNode != null) {
					return false;
				}
			}
			else if (!treeNode.equals(other.treeNode)) {
				return false;
			}
			if (xArchRef == null) {
				if (other.xArchRef != null) {
					return false;
				}
			}
			else if (!xArchRef.equals(other.xArchRef)) {
				return false;
			}
			return true;
		}
	}
}
