package org.archstudio.prolog.archstudio;

import java.lang.reflect.InvocationTargetException;

import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.archipelago.core.IArchipelagoTreePlugin;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.Services;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.ui.PlatformUI;

public class ArchipelagoTreePlugin implements IArchipelagoTreePlugin {

	protected final Services services;

	public ArchipelagoTreePlugin(TreeViewer viewer, Services services, ObjRef documentRootRef) {
		this.services = services;
	}

	@Override
	public IArchipelagoTreeContentProvider getContentProvider() {
		return null;
	}

	@Override
	public IArchipelagoTreeDoubleClickHandler getDoubleClickHandler() {
		return null;
	}

	@Override
	public IArchipelagoLabelProvider getLabelProvider() {
		return null;
	}

	@Override
	public IArchipelagoTreeContextMenuFiller[] getContextMenuFillers() {
		return new IArchipelagoTreeContextMenuFiller[] { new IArchipelagoTreeContextMenuFiller() {

			@Override
			public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
				if (selectedNodes != null && selectedNodes.length == 1) {
					final Object selectedNode = selectedNodes[0];
					if (selectedNode instanceof ObjRef) {
						IAction newStructureAction = new Action("Generate Prolog Facts") {

							@Override
							public void run() {
								process(XArchADTProxy.<EObject> proxy(services.get(IXArchADT.class),
										(ObjRef) selectedNode));
							}
						};
						m.add(newStructureAction);
					}
				}
			}
		} };
	}

	@Override
	public IArchipelagoEditorFocuser getEditorFocuser() {
		return null;
	}

	@Override
	public void setEditor(IArchipelagoEditorPane editor) {
	}

	@Override
	public ICellModifier[] getCellModifiers() {
		return null;
	}

	@Override
	public IXArchADTModelListener getXArchADTModelListener() {
		return null;
	}

	@Override
	public IXArchADTFileListener getXArchADTFileListener() {
		return null;
	}

	@Override
	public IFileManagerListener getFileManagerListener() {
		return null;
	}

	@Override
	public DragSourceListener getDragSourceListener() {
		return null;
	}

	public void process(final EObject eObject) {
		try {
			PlatformUI.getWorkbench().getProgressService().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Prolog", 1);
					try {
						ProofContext proofContext = new ProofContext();
						proofContext.add(PrologUtils.getFacts(proofContext, subMonitor.newChild(1), eObject));
					}
					catch (Throwable t) {
						throw new InvocationTargetException(t);
					}
				}
			});
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
