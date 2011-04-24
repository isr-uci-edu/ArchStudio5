package org.archstudio.archedit.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.editors.AbstractArchstudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTPackageMetadata;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTPath;
import org.archstudio.xarchadt.IXArchADTFeature.FeatureType;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchSite;

public class ArchEditOutlinePage extends AbstractArchstudioOutlinePage {
	protected boolean showIDs = false;
	protected boolean showDescriptions = true;
	protected boolean showObjRefs = false;
	
	protected static int nextId = 100;

	public ArchEditOutlinePage(IXArchADT xarch, ObjRef xArchRef, IResources resources) {
		super(xarch, xArchRef, resources, true, true);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		if (documentRootRef == null)
			return;
		setupDoubleClick();
		setupDragAndDrop();
	}

	protected ITreeContentProvider createViewContentProvider() {
		return new ViewContentProvider();
	}

	protected ILabelProvider createViewLabelProvider() {
		return new ViewLabelProvider();
	}

	protected void setupDoubleClick() {
		getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				List<INodeInfo> selectedNodeInfos = getSelectedNodeInfos();
				if (selectedNodeInfos.size() == 1) {
					INodeInfo selectedNodeInfo = selectedNodeInfos.get(0);
					if (selectedNodeInfo instanceof IReferenceNodeInfo) {
						IReferenceNodeInfo referenceNodeInfo = (IReferenceNodeInfo) selectedNodeInfo;
						ObjRef parentRef = referenceNodeInfo.getParentRef();
						String featureName = referenceNodeInfo.getFeatureName();

						if (referenceNodeInfo.isMultiple()) {
							List<ObjRef> referenceTargetObjects = xarch.getAll(parentRef, featureName);
							if (referenceNodeInfo.getIndex() < referenceTargetObjects.size()) {
								ObjRef referenceTargetRef = (ObjRef) referenceTargetObjects.get(referenceNodeInfo.getIndex());
								if (referenceTargetRef != null) {
									focusEditor(ArchEditMyxComponent.EDITOR_NAME, new ObjRef[] { referenceTargetRef });
								}
							}
						}
						else {
							Object referenceTargetObject = xarch.get(parentRef, featureName);
							if ((referenceTargetObject != null) && (referenceTargetObject instanceof ObjRef)) {
								ObjRef referenceTargetRef = (ObjRef) referenceTargetObject;
								focusEditor(ArchEditMyxComponent.EDITOR_NAME, new ObjRef[] { referenceTargetRef });
							}
						}
					}
				}
			}
		});
	}

	protected void setupDragAndDrop() {
		DropTarget target = new DropTarget(getTreeViewer().getTree(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		final TextTransfer textTransfer = TextTransfer.getInstance();
		target.setTransfer(new Transfer[] { textTransfer });

		target.addDropListener(new DropTargetListener() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					}
					else {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				if (textTransfer.isSupportedType(event.currentDataType)) {
					Object o = textTransfer.nativeToJava(event.currentDataType);
					if (o != null) {
						String t = (String) o;
						if (t.startsWith("$LINK$")) {
							event.feedback = DND.FEEDBACK_SCROLL;
						}
					}
				}
				event.feedback = DND.FEEDBACK_NONE;
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					}
					else {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (textTransfer.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;
					if (text.startsWith("$LINK$")) {
						text = text.substring("$LINK$".length());
						int hashLocation = text.lastIndexOf('#');
						if (hashLocation == -1) {
							return;
						}

						String pathString = text.substring(0, hashLocation);
						String featureName = text.substring(hashLocation + 1);
						int index = -1;

						int colonLocation = featureName.lastIndexOf(':');
						if (colonLocation != -1) {
							String indexString = featureName.substring(colonLocation + 1);
							featureName = featureName.substring(0, colonLocation);
							index = Integer.parseInt(indexString);
						}

						ObjRef parentRef = XArchADTPath.resolve(xarch, documentRootRef, new XArchADTPath(pathString));
						if (parentRef == null) {
							return;
						}

						if ((event.item != null) && (event.item instanceof TreeItem)) {
							Object data = ((TreeItem) event.item).getData();
							if ((data != null) && (data instanceof ArchEditElementNode)) {
								ObjRef targetRef = ((ArchEditElementNode) data).getRef();
								IXArchADTFeature feature = XadlUtils.getFeatureByName(xarch, parentRef, featureName);
								if (feature != null) {
									if (!XadlUtils.isAssignableTo(xarch, feature, xarch.getTypeMetadata(targetRef))) {
										MessageBox messageBox = new MessageBox(getControl().getShell(), SWT.OK | SWT.ICON_ERROR);
										messageBox.setMessage("Can't link that kind of element to this link.");
										messageBox.setText("Error");
										messageBox.open();
									}
									else {
										if (index == -1) {
											//It's not a multiple reference variable.
											xarch.set(parentRef, featureName, targetRef);
										}
										else {
											List<ObjRef> oldRefs = xarch.getAll(parentRef, featureName);
											List<ObjRef> newRefs = xarch.getAll(parentRef, featureName);
											//Remove all the old refs
											//TODO: Fix this with better list manipulating operations
											xarch.remove(parentRef, featureName, oldRefs);
											newRefs.set(index, targetRef);
											for (ObjRef newRef : newRefs) {
												xarch.add(parentRef, featureName, newRef);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
	}

	class ViewContentProvider implements ITreeContentProvider {
		private Object[] EMPTY_ARRAY = new Object[0];

		IArchEditNode rootNode = null;

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IWorkbenchSite) {
				if (rootNode == null) {
					rootNode = new ArchEditElementNode((ObjRef) xarch.get(documentRootRef, "xADL"));
				}
				return new Object[] { rootNode };
			}
			else if (parentElement instanceof IArchEditNode) {
				return ((IArchEditNode) parentElement).getChildren().toArray(new IArchEditNode[0]);
			}
			return EMPTY_ARRAY;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof IWorkbenchSite) {
				return true;
			}
			else if (element instanceof IArchEditNode) {
				return ((IArchEditNode) element).hasChildren();
			}
			return false;
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}
	}

	class ViewLabelProvider extends LabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			if (element instanceof IArchEditNode) {
				return ((IArchEditNode) element).getImage();
			}
			return null;
		}

		public String getText(Object element) {
			return super.getText(element);
		}
	}

	public List<INodeInfo> getSelectedNodeInfos() {
		List<INodeInfo> nodeInfoList = new ArrayList<INodeInfo>();
		for (Object selectedObject : getSelectedObjects()) {
			if (selectedObject instanceof INodeInfo) {
				nodeInfoList.add((INodeInfo) selectedObject);
			}
		}
		return nodeInfoList;
	}

	protected interface IArchEditNode {
		public boolean hasChildren();

		public List<IArchEditNode> getChildren();

		public Image getImage();
	}

	protected class ArchEditReferenceNode implements IArchEditNode, IReferenceNodeInfo {
		protected final ObjRef parentRef;
		protected final String featureName;
		protected final int index;
		protected final boolean isMultiple;

		public ArchEditReferenceNode(ObjRef parentRef, String featureName, boolean isMultiple, int index) {
			super();
			this.parentRef = parentRef;
			this.featureName = featureName;
			this.isMultiple = isMultiple;
			this.index = index;
		}

		public ObjRef getParentRef() {
			return parentRef;
		}

		public String getFeatureName() {
			return featureName;
		}

		public Image getImage() {
			return resources.getPlatformImage(ISharedImages.IMG_TOOL_FORWARD);
		}

		public boolean hasChildren() {
			return false;
		}

		public List<IArchEditNode> getChildren() {
			return Collections.emptyList();
		}

		public boolean isMultiple() {
			return isMultiple;
		}

		public int getIndex() {
			return index;
		}

		public String toString() {
			return getFeatureName();
		}
	}

	protected class ArchEditElementNode implements IArchEditNode, IElementNodeInfo {
		protected final ObjRef ref;
		protected final IXArchADTTypeMetadata typeMetadata;

		public ArchEditElementNode(ObjRef ref) {
			this.ref = ref;
			typeMetadata = xarch.getTypeMetadata(ref);
		}

		public ObjRef getRef() {
			return ref;
		}

		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (o instanceof ArchEditElementNode) {
				if (((ArchEditElementNode) o).ref.equals(ref)) {
					return true;
				}
			}
			return false;
		}

		public int hashCode() {
			return ref.hashCode();
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();

			sb.append(XadlUtils.getDisplayName(xarch, ref));

			if ((showIDs) && (!showDescriptions)) {
				String id = XadlUtils.getID(xarch, ref);
				if (id != null) {
					sb.append(" [");
					sb.append(id);
					sb.append("]");
				}
			}
			else if ((showDescriptions) && (!showIDs)) {
				String desc = XadlUtils.getName(xarch, ref);
				if (desc != null) {
					sb.append(" [");
					sb.append(desc);
					sb.append("]");
				}
			}
			else if (showDescriptions && showIDs) {
				String id = XadlUtils.getID(xarch, ref);
				String desc = XadlUtils.getName(xarch, ref);
				if ((id != null) || (desc != null)) {
					if (id == null)
						id = "(No ID)";
					if (desc == null)
						desc = "(No Description)";
					sb.append(" [");
					sb.append(id);
					sb.append("; ");
					sb.append(desc);
					sb.append("]");
				}
			}
			if (showObjRefs) {
				sb.append(" <" + ref + ">");
			}
			return sb.toString();
		}

		public List<IArchEditNode> getChildren() {
			List<IArchEditNode> l = new ArrayList<IArchEditNode>();

			for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
				String eltName = feature.getName();
				if (feature.isReference()) {
					switch (feature.getType()) {
					case ELEMENT_SINGLE:
						l.add(new ArchEditReferenceNode(ref, eltName, false, 0));
						break;
					case ELEMENT_MULTIPLE:
						List<ObjRef> childElts = xarch.getAll(ref, eltName);
						for (int i = 0; i < childElts.size(); i++) {
							l.add(new ArchEditReferenceNode(ref, eltName, true, i));
						}
						break;
					}
				}
				else {
					switch (feature.getType()) {
					case ELEMENT_SINGLE:
						ObjRef childRef = (ObjRef) xarch.get(ref, eltName);
						if (childRef != null) {
							l.add(new ArchEditElementNode(childRef));
						}
						break;
					case ELEMENT_MULTIPLE:
						for (ObjRef childRef2 : xarch.getAll(ref, eltName)) {
							l.add(new ArchEditElementNode(childRef2));
						}
					}
				}
			}
			return l;
		}

		public boolean hasChildren() {
			for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
				switch (feature.getType()) {
				case ELEMENT_MULTIPLE:
					return true;
				case ELEMENT_SINGLE:
					return true;
				}
			}
			return false;
		}

		public Image getImage() {
			if (hasChildren()) {
				return resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER);
			}
			else {
				return resources.getPlatformImage(ISharedImages.IMG_OBJ_ELEMENT);
			}
		}
	}

	protected IAction[] createPulldownMenuItems() {
		Action showIDAction = new Action("Show IDs", Action.AS_CHECK_BOX) {
			public void run() {
				Object[] expandedElements = getTreeViewer().getExpandedElements();
				showIDs = isChecked();
				getTreeViewer().refresh(true);
				getTreeViewer().setExpandedElements(expandedElements);
			}
		};
		showIDAction.setChecked(showIDs);
		showIDAction.setText("Show IDs");
		showIDAction.setToolTipText("Show IDs with elements");

		Action showDescAction = new Action("Show Descriptions", Action.AS_CHECK_BOX) {
			public void run() {
				Object[] expandedElements = getTreeViewer().getExpandedElements();
				showDescriptions = isChecked();
				getTreeViewer().refresh(true);
				getTreeViewer().setExpandedElements(expandedElements);
			}
		};
		showDescAction.setChecked(showDescriptions);
		showDescAction.setText("Show Descriptions");
		showDescAction.setToolTipText("Show Descriptions with elements");

		Action showObjRefAction = new Action("Show ObjRefs", Action.AS_CHECK_BOX) {
			public void run() {
				Object[] expandedElements = getTreeViewer().getExpandedElements();
				showObjRefs = isChecked();
				getTreeViewer().refresh(true);
				getTreeViewer().setExpandedElements(expandedElements);
			}
		};
		showObjRefAction.setChecked(showObjRefs);
		showObjRefAction.setText("Show ObjRefs");
		showObjRefAction.setToolTipText("Show ObjRefs with elements");

		return new Action[] { showIDAction, showDescAction, showObjRefAction };
	}

	protected void fillContextMenu(IMenuManager menuMgr) {
		List<INodeInfo> selectedNodeInfos = getSelectedNodeInfos();
		if (selectedNodeInfos.size() == 0) {
			Action noAction = new Action("[No Selection]") {
				public void run() {
				}
			};
			noAction.setEnabled(false);
			menuMgr.add(noAction);
		}
		else if (selectedNodeInfos.size() > 1) {
			Action noAction = new Action("[Select One Node for Menu]") {
				public void run() {
				}
			};
			noAction.setEnabled(false);
			menuMgr.add(noAction);
		}
		else {
			INodeInfo selectedNodeInfo = selectedNodeInfos.get(0);
			if (selectedNodeInfo instanceof IElementNodeInfo) {
				ObjRef ref = ((IElementNodeInfo) selectedNodeInfo).getRef();

				if (!XadlUtils.isRootElement(xarch, ref)) {
					// It's not the root node so we can remove it.
					menuMgr.add(createContextMenuRemoveAction(ref));
					menuMgr.add(new Separator());
				}
				
				IAction generateIdAction = createContextMenuGenerateIDAction(ref);
				if (generateIdAction != null) {
					menuMgr.add(generateIdAction);
					menuMgr.add(new Separator());
				}

				List<Object> items = createAddContextMenuItems(ref);
				if (items.isEmpty()) {
					menuMgr.add(SWTWidgetUtils.createNoAction("[No Actions]"));
				}
				else {
					for (Object item : items) {
						if (item instanceof IContributionItem) {
							menuMgr.add((IContributionItem) item);
						}
						else if (item instanceof IAction) {
							menuMgr.add((IAction) item);
						}
					}
				}
			}
		}
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public IAction createContextMenuRemoveAction(ObjRef ref) {
		final ObjRef fref = ref;
		Action removeAction = new Action("Remove") {
			public void run() {
				ObjRef parentRef = xarch.getParent(fref);
				String featureName = xarch.getContainingFeatureName(fref);

				IXArchADTFeature feature = XadlUtils.getFeatureByName(xarch, parentRef, featureName);
				switch (feature.getType()) {
				case ELEMENT_MULTIPLE:
					xarch.remove(parentRef, featureName, fref);
					break;
				case ELEMENT_SINGLE:
					xarch.clear(parentRef, featureName);
					break;
				}

				Object[] expandedElements = getTreeViewer().getExpandedElements();
				getTreeViewer().refresh(true);
				getTreeViewer().setExpandedElements(expandedElements);
			}
		};
		return removeAction;
	}
	
	protected IAction createContextMenuGenerateIDAction(ObjRef ref) {
		final ObjRef fref = ref;
		final IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(ref);
		final IXArchADTFeature idFeature = typeMetadata.getFeatures().get("id");
		if (idFeature == null) {
			return null;
		}
		Action generateIdAction = new Action("Generate ID") {
			public void run() {
				String id = "";
				while (true) {
					id = typeMetadata.getTypeName() + (nextId++);
					// Ensure the ID is unique
					if (xarch.getByID(id) == null) {
						break;
					}
				}
				xarch.set(fref, "id", id);
			}
		};
		String existingId = (String)xarch.get(ref, "id");
		generateIdAction.setEnabled(existingId == null);
		return generateIdAction;
	}

	protected List<Object> createAddContextMenuItems(final ObjRef ref) {
		List<Object> items = new ArrayList<Object>();

		final IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(ref);

		for (final IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			if (feature.getType().equals(FeatureType.ELEMENT_SINGLE) || feature.getType().equals(FeatureType.ELEMENT_MULTIPLE)) {
				boolean disabled = false;
				if (feature.getType().equals(FeatureType.ELEMENT_SINGLE)) {
					Object existingElement = xarch.get(ref, feature.getName());
					if (existingElement != null) {
						//We can't add a new one because we already have one.
						items.add(SWTWidgetUtils.createNoAction("Add " + SystemUtils.capFirst(feature.getName())));
						disabled = true;
					}
				}
				if (!disabled) {
					final MenuManager submenuManager = new MenuManager("Add " + SystemUtils.capFirst(feature.getName()));

					// Find all the candidates
					boolean foundOne = false;
					
					if (XadlUtils.isExtension(xarch, feature)) {
						String typeName = typeMetadata.getTypeName().substring(typeMetadata.getTypeName().lastIndexOf('.') + 1);
						for (IXArchADTSubstitutionHint hint : xarch.getSubstitutionHintsForTarget(typeMetadata.getNsURI(), typeName)) {
							foundOne = true;
							Action addEltAction = new AddElementAction(ref, feature, hint.getSourceNsURI(), hint.getSourceTypeName());
							submenuManager.add(addEltAction);
						}
					}
					else {
						// This handles substitution groups and the like
						for (IXArchADTSubstitutionHint hint : xarch.getSubstitutionHintsForSource(typeMetadata.getNsURI(), feature.getName())) {
							foundOne = true;
							Action addEltAction = new AddElementAction(ref, feature, hint.getTargetNsURI(), hint.getTargetTypeName());
							submenuManager.add(addEltAction);
						}
					}
					
					if (foundOne) {
						submenuManager.add(new Separator());
					}
					
					for (final IXArchADTPackageMetadata packageMetadata : xarch.getAvailablePackageMetadata()) {
						for (Map.Entry<String, IXArchADTTypeMetadata> e : packageMetadata.getTypeMetadata().entrySet()) {
							final String elementName = e.getKey();
							if (XadlUtils.isAssignableTo(xarch, feature, e.getValue()) && !e.getValue().isAbstract()) {
								foundOne = true;
								Action addEltAction = new AddElementAction(ref, feature, packageMetadata.getNsURI(), elementName);
								submenuManager.add(addEltAction);
							}
						}
					}
					if (!foundOne) {
						submenuManager.add(SWTWidgetUtils.createNoAction("[No Candidates]"));
					}
					items.add(submenuManager);
				}
			}
		}
		return items;
	}
	
	private class AddElementAction extends Action {
		private final ObjRef ref;
		private final IXArchADTFeature feature;
		private final String packageNsURI;
		private final String elementName;
		
		public AddElementAction(ObjRef ref, IXArchADTFeature feature, String packageNsURI, String elementName) {
			super(SystemUtils.capFirst(elementName));
			this.ref = ref;
			this.feature = feature;
			this.packageNsURI = packageNsURI;
			this.elementName = elementName;
		}
		
		public void run() {
			ObjRef newRef = xarch.create(packageNsURI, elementName);
			switch (feature.getType()) {
			case ELEMENT_SINGLE:
				xarch.set(ref, feature.getName(), newRef);
				break;
			case ELEMENT_MULTIPLE:
				xarch.add(ref, feature.getName(), newRef);
			}

			Object[] expandedElements = getTreeViewer().getExpandedElements();
			getTreeViewer().refresh(true);
			getTreeViewer().setExpandedElements(expandedElements);
		}
	}

	public void focusEditor(String editorName, ObjRef[] refs) {
		if (refs.length > 0) {
			List<ArchEditElementNode> nodes = new ArrayList<ArchEditElementNode>(refs.length);
			for (int i = 0; i < refs.length; i++) {
				nodes.add(new ArchEditElementNode(refs[i]));
			}
			//Expand the ancestors of the selected items
			int i = 0;
			for (ArchEditElementNode node : nodes) {
				List<ObjRef> ancestors = xarch.getAllAncestors(refs[i++]);
				for (int j = (ancestors.size() - 1); j >= 1; j--) {
					ArchEditElementNode ancestorNode = new ArchEditElementNode(ancestors.get(j));
					getTreeViewer().expandToLevel(ancestorNode, 1);
				}
			}
			IStructuredSelection ss = new StructuredSelection(nodes);
			getTreeViewer().setSelection(ss, true);
		}
	}

}
