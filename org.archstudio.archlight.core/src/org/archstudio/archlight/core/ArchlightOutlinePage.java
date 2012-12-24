package org.archstudio.archlight.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archstudio.archlight.ArchlightDocTest;
import org.archstudio.archlight.ArchlightTest;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchSite;

public class ArchlightOutlinePage extends AbstractArchStudioOutlinePage {
	protected IArchlightTestADT testadt = null;

	protected List<? extends ArchlightTest> tests = null;
	protected List<? extends ArchlightDocTest> doctests = null;

	public ArchlightOutlinePage(IArchlightTestADT testadt, IXArchADT xarch, ObjRef xArchRef, IResources resources) {
		super(xarch, xArchRef, resources, false, true);
		this.testadt = testadt;

		updateTests();
		updateDocTests();
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		getTreeViewer().expandToLevel(4);
	}

	@Override
	protected ITreeContentProvider createViewContentProvider() {
		return new ViewContentProvider();
	}

	@Override
	protected ILabelProvider createViewLabelProvider() {
		return new ViewLabelProvider();
	}

	public void updateTests() {
		tests = testadt.getAllTests();
	}

	public void updateDocTests() {
		doctests = ArchlightUtils.loadDocTests(xarch, documentRootRef);
	}

	@Override
	public void updateOutlinePage() {
		updateTests();
		updateDocTests();
		super.updateOutlinePage();
	}

	protected int getTestState(ArchlightTest test) {
		String uid = test.getUID();
		for (ArchlightDocTest doctest : doctests) {
			if (doctest.getTestUID().equals(uid)) {
				if (doctest.isEnabled()) {
					return ArchlightUtils.APPLIED;
				}
				else {
					return ArchlightUtils.DISABLED;
				}
			}
		}
		return ArchlightUtils.NOT_APPLIED;
	}

	/*
	 * We do an interesting thing here; namely that the tree itself is fake and
	 * not structured like a tree. Instead, we just have the list of current
	 * tests. The view content provider iterates through those and responds to
	 * getChildren based on the contents of that list. Intermediate FolderNodes
	 * are created as necessary as placeholders.
	 */
	class ViewContentProvider implements ITreeContentProvider {
		private Object[] EMPTY_ARRAY = new Object[0];

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IWorkbenchSite) {
				ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
				return new Object[] { xADLRef };
			}
			else if (parentElement instanceof ObjRef || parentElement instanceof FolderNode) {
				//Figure out where we are
				String[] currentLocation = null;
				if (parentElement instanceof ObjRef) {
					currentLocation = new String[0];
				}
				else if (parentElement instanceof FolderNode) {
					currentLocation = ((FolderNode) parentElement).getPathSegments();
				}
				else {
					throw new RuntimeException("Unknown node in tree: " + parentElement);
				}

				//Find all the children of where we are
				List<Object> children = new ArrayList<Object>();
				List<? extends ArchlightTest> ts = tests;
				for (ArchlightTest test : ts) {
					String[] testLocation = ArchlightTest.getCategoryPathComponents(test.getCategory());
					if (testLocation.length <= currentLocation.length) {
						continue;
					}
					boolean matches = true;
					for (int j = 0; j < currentLocation.length; j++) {
						if (!currentLocation[j].equals(testLocation[j])) {
							matches = false;
							break;
						}
					}
					if (matches) {
						//Okay, this is at least a descendant of our current location
						if (testLocation.length == currentLocation.length + 1) {
							//It's a direct child
							children.add(test);
						}
						else {
							//It's a descendant and we need to put in a foldernode
							String[] folderNodeLocation = new String[currentLocation.length + 1];
							System.arraycopy(currentLocation, 0, folderNodeLocation, 0, currentLocation.length);
							folderNodeLocation[currentLocation.length] = testLocation[currentLocation.length];
							FolderNode fn = new FolderNode(folderNodeLocation);
							if (!children.contains(fn)) {
								children.add(fn);
							}
						}
					}
				}
				//Sort the list of children
				Collections.sort(children, new Comparator<Object>() {
					@Override
					public int compare(Object o1, Object o2) {
						if (o1 instanceof FolderNode && o2 instanceof ArchlightTest) {
							return -1; //folders always before tests
						}
						else if (o1 instanceof ArchlightTest && o2 instanceof FolderNode) {
							return 1; //Tests always after folders
						}
						else if (o1 instanceof FolderNode && o2 instanceof FolderNode) {
							return ((FolderNode) o1).getLastSegment().compareToIgnoreCase(
									((FolderNode) o2).getLastSegment());
						}
						else if (o1 instanceof ArchlightTest && o2 instanceof ArchlightTest) {
							return ArchlightTest.getLastCategoryPathComponent(((ArchlightTest) o1).getCategory())
									.compareTo(
											ArchlightTest.getLastCategoryPathComponent(((ArchlightTest) o2)
													.getCategory()));
						}
						return 0;
					};
				});
				return children.toArray();
			}
			return EMPTY_ARRAY;
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof IWorkbenchSite) {
				return true;
			}
			else if (element instanceof ObjRef) {
				return true;
			}
			else if (element instanceof FolderNode) {
				return true;
			}
			else if (element instanceof ArchlightTest) {
				return false;
			}
			return false;
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}
	}

	class ViewLabelProvider extends LabelProvider implements ILabelProvider {
		@Override
		public Image getImage(Object element) {
			if (element instanceof ObjRef) {
				return resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER);
			}
			else if (element instanceof FolderNode) {
				return resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER);
			}
			else if (element instanceof ArchlightTest) {
				switch (getTestState((ArchlightTest) element)) {
				case ArchlightUtils.APPLIED:
					return resources.getImage(ArchlightUtils.IMAGE_TEST_APPLIED);
				case ArchlightUtils.DISABLED:
					return resources.getImage(ArchlightUtils.IMAGE_TEST_DISABLED);
				case ArchlightUtils.NOT_APPLIED:
					return resources.getImage(ArchlightUtils.IMAGE_TEST_NOTAPPLIED);
				}
				return resources.getPlatformImage(ISharedImages.IMG_OBJ_FILE);
			}
			return null;
		}

		@Override
		public String getText(Object element) {
			if (element instanceof ObjRef) {
				String uri = xarch.getURI(xarch.getDocumentRootRef((ObjRef) element)).toString();
				if (uri == null) {
					return "Document";
				}
				else {
					return uri;
				}
			}
			else if (element instanceof FolderNode) {
				return ((FolderNode) element).getLastSegment();
			}
			else if (element instanceof ArchlightTest) {
				return ArchlightTest.getLastCategoryPathComponent(((ArchlightTest) element).getCategory());
			}
			return super.getText(element);
		}
	}

	public Object[] getSelectedNodes() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object[] nodes = ss.toArray();
			return nodes;
		}
		else {
			return new Object[0];
		}
	}

	@Override
	protected void fillContextMenu(IMenuManager menuMgr) {
		Object[] selectedNodes = getSelectedNodes();
		if (selectedNodes.length == 0) {
			Action noAction = new Action("[No Selection]") {
				@Override
				public void run() {
				}
			};
			noAction.setEnabled(false);
			menuMgr.add(noAction);
		}
		else if (selectedNodes.length > 1) {
			Action noAction = new Action("[Select One Node for Menu]") {
				@Override
				public void run() {
				}
			};
			noAction.setEnabled(false);
			menuMgr.add(noAction);
		}
		else {
			Object node = selectedNodes[0];
			List<? extends IAction> actions = ArchlightUtils.createTestMenuActions(xarch, documentRootRef, tests,
					resources, node);
			if (actions.isEmpty()) {
				Action noAction = new Action("[No Actions]") {
					@Override
					public void run() {
					}
				};
				noAction.setEnabled(false);
				menuMgr.add(noAction);
			}
			for (IAction action : actions) {
				menuMgr.add(action);
			}
		}
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	public void focusEditor(String editorName, ObjRef[] refs) {
	}
}
