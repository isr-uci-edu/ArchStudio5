package org.archstudio.archipelago.core;

public class FolderNode {
	protected String type;
	protected String text;
	protected Object parent;

	public FolderNode(Object parent, String type, String text) {
		if (parent == null) {
			throw new IllegalArgumentException("Null parent");
		}
		if (type == null) {
			throw new IllegalArgumentException("Null type");
		}
		if (text == null) {
			throw new IllegalArgumentException("Null text");
		}
		this.type = type;
		this.text = text;
		this.parent = parent;
	}

	public Object getParent() {
		return parent;
	}

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}
}
