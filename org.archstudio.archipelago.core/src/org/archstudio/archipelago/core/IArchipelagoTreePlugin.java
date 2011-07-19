package org.archstudio.archipelago.core;

import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.dnd.DragSourceListener;

public interface IArchipelagoTreePlugin {
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
