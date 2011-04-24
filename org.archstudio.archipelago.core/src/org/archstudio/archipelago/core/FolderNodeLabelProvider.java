package org.archstudio.archipelago.core;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class FolderNodeLabelProvider implements IArchipelagoLabelProvider{
	protected ArchipelagoServices AS = null;
	
	public FolderNodeLabelProvider(ArchipelagoServices services){
		this.AS = services;
	}
	
	public String getText(Object element, String textFromPreviousProvider){
		if(element instanceof FolderNode){
			return ((FolderNode)element).getText();
		}
		return textFromPreviousProvider;
	}
	
	public Image getImage(Object element, Image imageFromPreviousProvider){
		if(element instanceof FolderNode){
			return AS.resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER);
		}
		return imageFromPreviousProvider;
	}
	
}
