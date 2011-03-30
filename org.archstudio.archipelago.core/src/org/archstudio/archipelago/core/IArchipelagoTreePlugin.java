package org.archstudio.archipelago.core;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.dnd.DragSourceListener;

import org.archstudio.filemanager.common.IFileManagerListener;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.IXArchADTModelListener;

public interface IArchipelagoTreePlugin{
	public IArchipelagoTreeContentProvider getContentProvider();
	public IArchipelagoTreeDoubleClickHandler getDoubleClickHandler();
	public IArchipelagoLabelProvider getLabelProvider();
	public IArchipelagoTreeContextMenuFiller[] getContextMenuFillers();
	public ICellModifier[] getCellModifiers();
	public IXArchADTModelListener getXArchADTModelListener();
	public IXArchADTFileListener getXArchADTFileListener();
	public IFileManagerListener getFileManagerListener();
	public IArchipelagoEditorFocuser getEditorFocuser();
	
	public DragSourceListener getDragSourceListener();
}