package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing.dialogs;

import java.util.List;

public class OptionsDialogContentProvider implements ITreeContentProvider {

	ICopiedElementNode[] nodes = null;

	public OptionsDialogContentProvider(ICopiedElementNode[] nodes) {
		this.nodes = nodes;
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ICopiedElementNode) {
			ICopiedElementNode parentNode = (ICopiedElementNode) parentElement;
			List<ICopiedElementNode> children = parentNode.getChildren();
			if (children.size() > 0) {
				return children.toArray(new ICopiedElementNode[children.size()]);
			}
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof ICopiedElementNode) {
			ICopiedElementNode node = (ICopiedElementNode) element;
			return node.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {

		if (element instanceof ICopiedElementNode) {
			ICopiedElementNode node = (ICopiedElementNode) element;
			return node.getChildren().size() > 0 ? true : false;
		}
		return false;
	}

	public Object[] getElements(Object inputElement) {

		if (this.nodes != null && this.nodes.length > 0) {
			return this.nodes;
		}
		return new Object[0];
	}

	public void dispose() {

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

}
