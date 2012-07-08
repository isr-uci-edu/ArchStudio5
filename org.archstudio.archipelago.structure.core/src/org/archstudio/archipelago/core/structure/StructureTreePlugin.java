package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.AbstractArchipelagoTreePlugin;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureTreePlugin extends AbstractArchipelagoTreePlugin {

	public StructureTreePlugin(TreeViewer viewer, Services AS, ObjRef documentRootRef) {
		this.contentProvider = new StructureTreeContentProvider(AS, documentRootRef);
		this.labelProvider = new StructureTreeLabelProvider(AS);
		this.doubleClickHandler = new StructureDoubleClickHandler(AS);
		this.flatListener = new StructureXArchEventHandler(AS);
		this.fileManagerListener = new StructureFileManagerListener(AS, documentRootRef);

		this.contextMenuFillers = new IArchipelagoTreeContextMenuFiller[] {
				new StructureNewStructureContextMenuFiller(viewer, AS, documentRootRef),
				new StructureEditDescriptionContextMenuFiller(viewer, AS, documentRootRef),
				new StructureRemoveContextMenuFiller(viewer, AS, documentRootRef) };
		this.cellModifiers = new ICellModifier[] { new StructureEditDescriptionCellModifier(AS, documentRootRef) };
		this.editorFocuser = new StructureEditorFocuser(viewer, AS, documentRootRef);

		this.dragSourceListener = new StructureTreeDragSourceListener(AS, documentRootRef);
	}

}
