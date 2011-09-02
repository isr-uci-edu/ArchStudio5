package org.archstudio.archipelago.core;

import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.archipelago.core.IArchipelagoTreePlugin;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.dnd.DragSourceListener;

public class AbstractArchipelagoTreePlugin implements IArchipelagoTreePlugin {

	protected IArchipelagoTreeContentProvider contentProvider = null;
	protected IArchipelagoTreeDoubleClickHandler doubleClickHandler = null;
	protected IArchipelagoLabelProvider labelProvider = null;
	protected IArchipelagoTreeContextMenuFiller[] contextMenuFillers = null;
	protected ICellModifier[] cellModifiers = null;
	protected IXArchADTModelListener flatListener = null;
	protected IXArchADTFileListener fileListener = null;
	protected IFileManagerListener fileManagerListener = null;
	protected DragSourceListener dragSourceListener = null;
	protected IArchipelagoEditorFocuser editorFocuser = null;

	public IArchipelagoTreeContentProvider getContentProvider() {
		return contentProvider;
	}

	public IArchipelagoTreeDoubleClickHandler getDoubleClickHandler() {
		return doubleClickHandler;
	}

	public IArchipelagoLabelProvider getLabelProvider() {
		return labelProvider;
	}

	public IArchipelagoTreeContextMenuFiller[] getContextMenuFillers() {
		return contextMenuFillers;
	}

	public ICellModifier[] getCellModifiers() {
		return cellModifiers;
	}

	public IXArchADTFileListener getXArchADTFileListener() {
		return fileListener;
	}

	public IXArchADTModelListener getXArchADTModelListener() {
		return flatListener;
	}

	public DragSourceListener getDragSourceListener() {
		return dragSourceListener;
	}

	public IFileManagerListener getFileManagerListener() {
		return fileManagerListener;
	}

	public IArchipelagoEditorFocuser getEditorFocuser() {
		return editorFocuser;
	}
}
