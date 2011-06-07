package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing.dialogs;

import java.awt.Image;

public class OptionsDialogLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof ICopiedElementNode) {
			ICopiedElementNode node = (ICopiedElementNode) element;
			switch (columnIndex) {
			case 0:
				return node.getDescription();
			case 1:
				return null;
			}
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {

	}

}
