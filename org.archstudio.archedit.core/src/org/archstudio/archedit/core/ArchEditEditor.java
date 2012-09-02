package org.archstudio.archedit.core;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.eclipse.ui.editors.AbstractArchStudioEditor;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.AutoResizeTableLayout;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.ide.IGotoMarker;

public class ArchEditEditor extends AbstractArchStudioEditor<ArchEditMyxComponent> implements IGotoMarker {
	public static final String[] COLUMN_NAMES = new String[] { "Name", "Value" };

	public ArchEditEditor() {
		super(ArchEditMyxComponent.class, ArchEditMyxComponent.EDITOR_NAME);
		setBannerInfo(brick.getIcon(), "Syntax-Directed Architecture Editor");
		setHasBanner(true);
	}

	protected AbstractArchStudioOutlinePage createOutlinePage() {
		return new ArchEditOutlinePage(xarch, documentRootRef, resources);
	}

	public void createEditorContents(Composite parent) {
		List<INodeInfo> selectedNodeInfos = null;
		if (outlinePage != null) {
			selectedNodeInfos = ((ArchEditOutlinePage) outlinePage).getSelectedNodeInfos();
		}

		if ((selectedNodeInfos == null) || (selectedNodeInfos.size() == 0)) {
			Label lNothingSelected = new Label(parent, SWT.LEFT);
			lNothingSelected.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			lNothingSelected.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
			lNothingSelected.setText("Select one or more elements in the outline view.");
		}
		else {
			for (INodeInfo selectedNodeInfo : selectedNodeInfos) {
				Label lElement = new Label(parent, SWT.LEFT);
				lElement.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
				lElement.setFont(resources.getPlatformFont(IResources.PLATFORM_HEADER_FONT_ID));

				if (selectedNodeInfo instanceof IReferenceNodeInfo) {
					IReferenceNodeInfo referenceNodeInfo = (IReferenceNodeInfo) selectedNodeInfo;
					String headerLine = "Link: " + referenceNodeInfo.getFeatureName();
					lElement.setText(headerLine.toString());
					createDragSourceComposite(parent, referenceNodeInfo);
				}
				else if (selectedNodeInfo instanceof IElementNodeInfo) {
					ObjRef selectedRef = ((IElementNodeInfo) selectedNodeInfo).getRef();

					IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(selectedRef);
					StringBuffer headerLine = new StringBuffer();
					headerLine.append(XadlUtils.getDisplayName(xarch, selectedRef));
					headerLine.append(": ");
					headerLine.append(typeMetadata.getTypeName());
					lElement.setText(headerLine.toString());

					boolean hasAttribute = false;
					for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
						if (feature.getType().equals(IXArchADTFeature.FeatureType.ATTRIBUTE)) {
							hasAttribute = true;
							break;
						}
					}

					if (!hasAttribute) {
						//No attributes.
						Label lNoAttributes = new Label(parent, SWT.LEFT);
						lNoAttributes.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
						lNoAttributes.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
						lNoAttributes.setText("This element has no attributes.");
					}
					else {
						TableViewer tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION
								| SWT.NO_FOCUS);
						tv.setContentProvider(new ViewContentProvider());
						tv.setLabelProvider(new ViewLabelProvider());
						tv.setInput(selectedRef);

						Table table = tv.getTable();

						TableColumn column = new TableColumn(table, SWT.LEFT);
						column.setText(COLUMN_NAMES[0]);

						TableColumn column2 = new TableColumn(table, SWT.LEFT);
						column2.setText(COLUMN_NAMES[1]);

						TableLayout tableLayout = new AutoResizeTableLayout(table);
						tableLayout.addColumnData(new ColumnWeightData(30, true));
						tableLayout.addColumnData(new ColumnWeightData(70, true));
						table.setLayout(tableLayout);

						table.setHeaderVisible(true);
						table.setLinesVisible(true);
						table.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

						tv.setColumnProperties(COLUMN_NAMES);
						CellEditor tce = new TextCellEditor(table);
						tv.setCellEditors(new CellEditor[] { null, tce });
						tv.setCellModifier(new ViewCellModifier(selectedRef));

						tv.refresh();
					}
				}
			}
		}
	}

	protected class NameValuePair {
		public String name;
		public String value;
	}

	protected class ViewContentProvider implements IStructuredContentProvider {
		public ViewContentProvider() {
		}

		public Object[] getElements(Object inputElement) {
			List<String[]> l = new ArrayList<String[]>();
			IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata((ObjRef) inputElement);

			for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
				if (feature.getType().equals(IXArchADTFeature.FeatureType.ATTRIBUTE)) {
					Object value = xarch.get((ObjRef) inputElement, feature.getName());
					l.add(new String[] { feature.getName(), value == null ? null : value.toString() });
				}
			}
			return l.toArray();
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return ((String[]) obj)[index];
		}

		public Image getColumnImage(Object obj, int index) {
			return null;
		}

		public Image getImage(Object obj) {
			return null;
		}
	}

	class ViewCellModifier implements ICellModifier {
		protected ObjRef ref;

		public ViewCellModifier(ObjRef ref) {
			this.ref = ref;
		}

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			if (element == null) {
				return "";
			}
			if (element instanceof String[]) {
				String[] elts = (String[]) element;
				if (elts[1] == null)
					return "";
				return elts[1].toString();
			}
			return null;
		}

		public void modify(Object element, String property, Object value) {
			//SWT bug workaround
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			if (element instanceof String[]) {
				String[] elts = (String[]) element;
				String propertyName = elts[0].toString();

				String oldValue = null;
				if (elts[1] != null)
					oldValue = elts[1].toString();

				String newValue = null;
				if (value != null)
					newValue = value.toString();

				if ((oldValue == null) && (newValue == null)) {
					//Do nothing
				}
				else if ((oldValue != null) && (newValue == null)) {
					XArchADTOperations.set("Set", xarch, ref, propertyName, null);
				}
				else if ((oldValue == null) && (newValue != null)) {
					set(propertyName, newValue);
				}
				else {
					//Both non-null:
					if (!oldValue.equals(newValue)) {
						set(propertyName, newValue);
					}
				}
			}
		}

		private void set(String featureName, String stringValue) {
			IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(ref);
			IXArchADTFeature feature = typeMetadata.getFeatures().get(featureName);
			if (feature != null) {
				try {
					// If the feature is an enumeration type, then xarch.set will
					// automatically try to convert it to an enum and throw
					// IllegalArgumentException if it's not a valid value.
					XArchADTOperations.set("Set", xarch, ref, featureName, stringValue);
				}
				catch (IllegalArgumentException iae) {
					MessageBox messageBox = new MessageBox(parent.getShell(), SWT.OK | SWT.ICON_ERROR);
					messageBox.setMessage("Invalid value for this field.");
					messageBox.setText("Error");
					messageBox.open();
				}
			}
			else {
				throw new RuntimeException("This shouldn't happen.");
			}
		}
	}

	public void createDragSourceComposite(Composite parent, IReferenceNodeInfo referenceNodeInfo) {
		//Composite c = new Composite(parent, SWT.BORDER);
		Group c = new Group(parent, SWT.SHADOW_ETCHED_IN);
		GridLayout gl = new GridLayout(2, false);
		gl.marginTop = 1;
		gl.marginBottom = 5;
		gl.marginLeft = 1;
		gl.marginRight = 1;
		gl.marginHeight = 1;
		gl.marginWidth = 1;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;

		c.setLayout(gl);

		c.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		Label il = new Label(c, SWT.LEFT | SWT.NO_FOCUS);
		il.setImage(resources.getPlatformImage(ISharedImages.IMG_OBJS_INFO_TSK));
		il.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		Label l = new Label(c, SWT.LEFT | SWT.NO_FOCUS);
		l.setText("Drag this area to a target in the tree to quick-link.");
		l.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		c.pack();

		DragSource[] sources = new DragSource[] {
				//new DragSource(il, DND.DROP_MOVE | DND.DROP_COPY),
				new DragSource(l, DND.DROP_MOVE | DND.DROP_COPY), new DragSource(c, DND.DROP_MOVE | DND.DROP_COPY), };

		final ObjRef fParentRef = referenceNodeInfo.getParentRef();
		final String fFeatureName = referenceNodeInfo.getFeatureName();

		for (int i = 0; i < sources.length; i++) {
			DragSource source = sources[i];
			Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
			source.setTransfer(types);
			source.addDragListener(new DragSourceListener() {
				public void dragStart(DragSourceEvent event) {
					event.doit = true;
				}

				public void dragSetData(DragSourceEvent event) {
					if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
						event.data = "$LINK$" + fFeatureName + "$" + xarch.getXPath(fParentRef);
					}
				}

				public void dragFinished(DragSourceEvent event) {
					if (event.detail == DND.DROP_MOVE) {
					}
				}
			});
		}
	}

	public void focusEditor(String editorName, ObjRef[] refs) {
		super.focusEditor(editorName, refs);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IGotoMarker.class)) {
			return this;
		}
		return super.getAdapter(key);
	}

	public void gotoMarker(IMarker marker) {
		ObjRef objRef = xarch.getByID(documentRootRef, marker.getAttribute(IMarker.LOCATION, null));
		if (objRef != null) {
			focusEditor(editorName, new ObjRef[] { objRef });
		}

	}
}
