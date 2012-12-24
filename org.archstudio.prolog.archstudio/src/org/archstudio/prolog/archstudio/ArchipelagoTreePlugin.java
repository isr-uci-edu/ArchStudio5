package org.archstudio.prolog.archstudio;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.archipelago.core.IArchipelagoTreePlugin;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.Services;
import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.SingleThreadProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xadl.PrologUtils;
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

import com.google.common.collect.Iterables;

public class ArchipelagoTreePlugin implements IArchipelagoTreePlugin {

	protected final Services services;

	public ArchipelagoTreePlugin(TreeViewer viewer, Services services, ObjRef documentRootRef) {
		this.services = services;
	}

	public IArchipelagoTreeContentProvider getContentProvider() {
		return null;
	}

	public IArchipelagoTreeDoubleClickHandler getDoubleClickHandler() {
		return null;
	}

	public IArchipelagoLabelProvider getLabelProvider() {
		return null;
	}

	public IArchipelagoTreeContextMenuFiller[] getContextMenuFillers() {
		return new IArchipelagoTreeContextMenuFiller[] { new IArchipelagoTreeContextMenuFiller() {

			public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
				if (selectedNodes != null && selectedNodes.length == 1) {
					final Object selectedNode = selectedNodes[0];
					if (selectedNode instanceof ObjRef) {
						IAction newStructureAction = new Action("Generate Prolog Facts") {

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

	public IArchipelagoEditorFocuser getEditorFocuser() {
		return null;
	}

	public void setEditor(IArchipelagoEditorPane editor) {
	}

	public ICellModifier[] getCellModifiers() {
		return null;
	}

	public IXArchADTModelListener getXArchADTModelListener() {
		return null;
	}

	public IXArchADTFileListener getXArchADTFileListener() {
		return null;
	}

	public IFileManagerListener getFileManagerListener() {
		return null;
	}

	public DragSourceListener getDragSourceListener() {
		return null;
	}

	public void process(final EObject eObject) {
		try {
			PlatformUI.getWorkbench().getProgressService().run(true, true, new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Prolog", 1);
					try {
						ProofContext proofContext = new ProofContext();
						UnificationEngine unifier = new MostGeneralUnifierEngine();
						ProofEngine proofEngine = new SingleThreadProofEngine();

						proofContext.add(PrologUtils.getFacts(subMonitor.newChild(1), eObject));
						proofContext.add(Iterables.filter(
								PrologParser
										.parseTerms(
												proofContext,
												"compatible_directions('in', 'out')."
														+ "compatible_directions('out', 'in')."
														+ "compatible_directions('inout', 'inout')."
														+ "compatible_directions('none', 'none')."
														+ "test(Id, 'Connected interfaces have incompatible directions') :- link(L), id(L, Id)."),
								//												proofContext,
								//												"connectedInterfaces(XIfaceRef, YIfaceRef) :- link(L), link_point1(L, XIfaceRef), link_point2(L, YIfaceRef), interface(XIfaceRef), interface(YIfaceRef), XIfaceRef\\=YIfaceRef."
								//														+ "connectedInterfaces(XIfaceRef, YIfaceRef) :- link(L), link_point2(L, XIfaceRef), link_point1(L, YIfaceRef), interface(XIfaceRef), interface(YIfaceRef), XIfaceRef\\=YIfaceRef."
								//														+ "brick_interface(X, Y) :- component_interface(X, Y)."
								//														+ "brick_interface(X, Y) :- connector_interface(X, Y)."
								//														+ "connectedBricks(XBrickRef, YBrickRef) :- connectedInterfaces(X, Y), X\\=Y, brick_interface(XBrickRef, X), brick_interface(YBrickRef, Y)."),
								ComplexTerm.class));

						ComplexTerm goal = (ComplexTerm) PrologParser.parseTerms(proofContext, "test(A, B).").get(0);

						int total = 0;
						for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unifier, goal)) {
							System.out.println(v);
							total++;
							if (subMonitor.isCanceled()) {
								break;
							}
						}
						System.out.println("Done. " + total + " result(s).");
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
