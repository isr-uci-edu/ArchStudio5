package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.AbstractArchipelagoTreePlugin;
import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.FontData;

public class StructureTreePlugin extends AbstractArchipelagoTreePlugin {

	public StructureTreePlugin(TreeViewer viewer, ArchipelagoServices AS, ObjRef documentRootRef) {
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

		initDefaultPreferences(viewer, AS.prefs);
	}

	protected void initDefaultPreferences(TreeViewer viewer, IPreferenceStore prefs) {
		FontData[] fontDatas = viewer.getTree().getDisplay().getSystemFont().getFontData();
		PreferenceConverter.setDefault(prefs, ArchipelagoStructureConstants.PREF_DEFAULT_FONT, fontDatas);
		PreferenceConverter.setDefault(prefs, ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR,
				ArchipelagoStructureConstants.DEFAULT_COMPONENT_RGB);
		PreferenceConverter.setDefault(prefs, ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR,
				ArchipelagoStructureConstants.DEFAULT_CONNECTOR_RGB);
	}

}
