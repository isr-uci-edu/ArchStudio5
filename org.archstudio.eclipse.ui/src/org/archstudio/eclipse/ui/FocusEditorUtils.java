package org.archstudio.eclipse.ui;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class FocusEditorUtils {
	public static void focusEditor(IXArchADT xarch, ObjRef ref, String editorID, String editorName) {
		final IXArchADT fxarch = xarch;
		final ObjRef fref = ref;
		final String feditorID = editorID;
		final String feditorName = editorName;
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				_focusEditor(fxarch, fref, feditorID, feditorName);
			}
		});
	}

	private static void _focusEditor(IXArchADT xarch, ObjRef ref, String editorID, String editorName) {
		if (ref == null) {
			return;
		}
		if (!xarch.isValidObjRef(ref)) {
			return;
		}
		ObjRef xArchRef = xarch.getDocumentRootRef(ref);
		if (!xarch.isValidObjRef(xArchRef)) {
			return;
		}

		URI uri = xarch.getURI(xArchRef);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		Path path = new Path(uri.path());
		IFile file = root.getFile(path);
		if (file == null) {
			return;
		}

		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				IWorkbenchPage page = win.getActivePage();
				if (page != null) {
					try {
						IFileEditorInput fileEditorInput = new FileEditorInput(file);
						IEditorPart editorPart = page.openEditor(fileEditorInput, editorID, true,
								IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT);
						if (editorPart instanceof IFocusEditorListener) {
							((IFocusEditorListener) editorPart).focusEditor(editorName, new ObjRef[] { ref });
						}
					}
					catch (PartInitException pie) {
						//pie.printStackTrace();
					}
				}
			}
		}
	}

}
