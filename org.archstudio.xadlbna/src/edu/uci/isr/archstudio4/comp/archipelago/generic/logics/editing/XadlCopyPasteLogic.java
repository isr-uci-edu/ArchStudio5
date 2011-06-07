package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Action;

import org.archstudio.xadlbna.generic.logics.editing.dialogs.OptionsDialog;


public class XadlCopyPasteLogic extends AbstractThingLogic implements IBNAMenuListener {

	final protected SelectionTrackingLogic stl;

	final protected ObjRef diagramRef;

	final protected XArchFlatInterface xarch;

	final protected ObjRef xArchRef;

	static Object copiedLock = new Object();

	final protected ICopyPasteManager copyPasteManager;

	public XadlCopyPasteLogic(XArchFlatInterface xarch, ObjRef diagramRef, SelectionTrackingLogic stl,
			ICopyPasteManager copyPasteManager) {
		this.xarch = xarch;
		this.diagramRef = diagramRef;
		this.xArchRef = xarch.getXArch(diagramRef);
		this.stl = stl;
		this.copyPasteManager = copyPasteManager;
	}

	protected ObjRef getObjRef(IThing thing) {
		String id = thing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		if (id == null) {
			IAssembly assembly = AssemblyUtils.getAssemblyWithPart(thing);
			if (assembly != null) {
				id = assembly.getRootThing().getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
			}
		}
		if (id != null) {
			return xarch.getByID(xArchRef, id);
		}
		return null;
	}

	protected Collection<ObjRef> getObjRefs(IThing... things) {
		Collection<ObjRef> objRefs = new ArrayList<ObjRef>();
		for (IThing thing : things) {
			ObjRef objRef = getObjRef(thing);
			if (objRef != null) {
				objRefs.add(objRef);
			}
		}
		return objRefs;
	}

	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY) {
		synchronized (copiedLock) {

			IAction action;

			final Collection<ObjRef> copyObjRefs = new ArrayList<ObjRef>();
			copyObjRefs.addAll(getObjRefs(stl.getSelectedThings()));
			final IBNAView bnaView = view;
			m.add(action = new Action("Copy", PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_TOOL_COPY)) {

				@Override
				public void run() {
					copyPasteManager.copy(copyObjRefs.toArray(new ObjRef[copyObjRefs.size()]), diagramRef);
				}
			});
			action.setEnabled(copyObjRefs.size() > 0);

			final ObjRef pasteObjRef = t != null ? getObjRef(t) : diagramRef;
			m.add(action = new Action("Paste", PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE)) {

				@Override
				public void run() {
					ICopiedElementNode[] clipboardNodes = copyPasteManager.getClipboardNodes();
					OptionsDialog dialog = new OptionsDialog(bnaView.getParentComposite().getShell(), clipboardNodes,
							xarch.getXArch(pasteObjRef));
					int response = dialog.open();
					if (response == Dialog.OK) {
						copyPasteManager.paste(pasteObjRef, clipboardNodes);
					}
				}
			});
			action.setEnabled(copyPasteManager.canPaste(pasteObjRef));
		}
	}
}
