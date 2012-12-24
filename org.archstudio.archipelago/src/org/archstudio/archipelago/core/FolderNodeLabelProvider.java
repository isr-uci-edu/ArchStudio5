package org.archstudio.archipelago.core;

import org.archstudio.resources.IResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class FolderNodeLabelProvider implements IArchipelagoLabelProvider {

	protected final IResources resources;

	public FolderNodeLabelProvider(IResources resources) {
		this.resources = resources;
	}

	public String getText(Object element, String textFromPreviousProvider) {
		if (element instanceof FolderNode) {
			return ((FolderNode) element).getText();
		}
		return textFromPreviousProvider;
	}

	public Image getImage(Object element, Image imageFromPreviousProvider) {
		if (element instanceof FolderNode) {
			return resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER);
		}
		return imageFromPreviousProvider;
	}

}
