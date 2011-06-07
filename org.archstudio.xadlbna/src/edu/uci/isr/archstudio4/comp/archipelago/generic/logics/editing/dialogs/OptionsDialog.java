package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing.dialogs;

import java.awt.Composite;
import java.awt.Dialog;
import java.util.List;

import javax.swing.CellEditor;

public class OptionsDialog extends Dialog implements ICheckStateListener {

	ICopiedElementNode[] nodes;

	private CheckboxTreeViewer optionsTreeViewer;
	private Object input;

	public OptionsDialog(Shell parentShell, ICopiedElementNode[] nodes, Object input) {
		super(parentShell);
		this.nodes = nodes;
		this.input = input;
		setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX | SWT.APPLICATION_MODAL);
	}

	protected static void addCellEditor(TreeViewer viewer, CellEditor editor) {
		CellEditor[] editors = viewer.getCellEditors();
		if (editors == null) {
			editors = new CellEditor[0];
		}
		CellEditor[] newEditors = new CellEditor[editors.length + 1];
		System.arraycopy(editors, 0, newEditors, 0, editors.length);
		newEditors[newEditors.length - 1] = editor;
		viewer.setCellEditors(newEditors);
	}

	protected static void addColumnProperty(TreeViewer viewer, String property) {
		String[] properties = (String[]) viewer.getColumnProperties();
		if (properties == null) {
			properties = new String[0];
		}
		String[] newProperties = new String[properties.length + 1];
		System.arraycopy(properties, 0, newProperties, 0, properties.length);
		newProperties[newProperties.length - 1] = property;
		viewer.setColumnProperties(newProperties);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = (Composite) super.createDialogArea(parent);
		optionsTreeViewer = new CheckboxTreeViewer(composite);
		optionsTreeViewer.addCheckStateListener(this);
		optionsTreeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		TableLayout layout = new TableLayout();
		Tree optionsTree = optionsTreeViewer.getTree();
		optionsTree.setHeaderVisible(true);
		optionsTree.setLinesVisible(true);
		optionsTree.setLayout(layout);

		TreeColumn column = new TreeColumn(optionsTree, SWT.LEFT);
		column.setText("Options");
		column.setImage(null);
		column.setResizable(true);
		layout.addColumnData(new ColumnWeightData(1, column.getResizable()));
		addColumnProperty(optionsTreeViewer, "Options");
		CellEditor cellEditor = new TextCellEditor(optionsTree);
		addCellEditor(optionsTreeViewer, cellEditor);

		optionsTreeViewer.setContentProvider(new OptionsDialogContentProvider(nodes));
		optionsTreeViewer.setLabelProvider(new OptionsDialogLabelProvider());
		optionsTreeViewer.setInput(input);

		optionsTreeViewer.expandAll();

		setCheckedForRequiredNodes(nodes, true);
		optionsTreeViewer.refresh();
		return composite;
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Select Related Items");
		Display display = shell.getDisplay();
		shell.setSize(display.getBounds().width / 2, display.getBounds().height / 2);

	}

	public void setCheckedForRequiredNodes(ICopiedElementNode[] nodes, boolean parentChecked) {
		if (nodes != null && nodes.length > 0) {
			for (ICopiedElementNode node : nodes) {
				boolean newParentChecked = parentChecked;
				if (node instanceof ObjRefNode) {
					optionsTreeViewer.setGrayChecked(node, true);
				}
				else {
					if (parentChecked) {
						if (node instanceof RelatedElementNode) {
							IRelatedElement relatedElement = ((RelatedElementNode) node).getRelatedElement();
							if (relatedElement.isDefaultSelection()) {
								optionsTreeViewer.setChecked(node, true);
								node.setSelected(true);
							}
							else {
								newParentChecked = false;
							}
						}
					}
				}
				List<ICopiedElementNode> children = node.getChildren();
				if (children != null && children.size() > 0) {
					setCheckedForRequiredNodes(children.toArray(new ICopiedElementNode[children.size()]),
							newParentChecked);
				}
			}
		}
	}

	public void checkStateChanged(CheckStateChangedEvent event) {
		Object element = event.getElement();
		if (element instanceof RelatedElementNode) {
			boolean isChecked = event.getChecked();
			if (isChecked) {
				ICopiedElementNode parentNode = ((ICopiedElementNode) element).getParent();
				while (parentNode != null) {
					if (parentNode instanceof RelatedElementNode && !parentNode.isSelected()) {
						optionsTreeViewer.setChecked(element, false);
						break;
					}
					if ((parentNode instanceof ObjRefNode && parentNode.getParent() == null)
							|| (parentNode instanceof RelatedElementNode && parentNode.isSelected())) {
						setDefaultCheckedForRelatedChildren((ICopiedElementNode) element);
						break;
					}
					parentNode = parentNode.getParent();
				}
			}
			else {
				unCheckHierarchy((ICopiedElementNode) element);
			}
		}
		else if (element instanceof ObjRefNode) {
			optionsTreeViewer.setGrayChecked(element, true);
		}
	}

	private void unCheckHierarchy(ICopiedElementNode node) {
		if (node instanceof RelatedElementNode) {
			node.setSelected(false);
			optionsTreeViewer.setChecked(node, false);
		}
		List<ICopiedElementNode> children = node.getChildren();
		if (children != null && children.size() > 0) {
			for (ICopiedElementNode child : children) {
				unCheckHierarchy(child);

			}
		}

	}

	private void setDefaultCheckedForRelatedChildren(ICopiedElementNode node) {
		if (node instanceof RelatedElementNode) {
			node.setSelected(true);
			optionsTreeViewer.setChecked(node, true);
		}
		List<ICopiedElementNode> children = node.getChildren();
		if (children != null && children.size() > 0) {
			for (ICopiedElementNode child : children) {
				if (child instanceof RelatedElementNode) {
					RelatedElementNode relatedElementNode = (RelatedElementNode) child;
					if (relatedElementNode.getRelatedElement().isDefaultSelection()) {
						setDefaultCheckedForRelatedChildren(relatedElementNode);
					}
				}
				else if (child instanceof ObjRefNode) {
					setDefaultCheckedForRelatedChildren(child);
				}
			}
		}
	}
}
