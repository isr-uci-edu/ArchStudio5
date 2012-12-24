package org.archstudio.swtutils;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class NameValuePairTable {
	public interface Controller<T> {
		public String getNameColumnHeader();

		public String getValueColumnHeader();

		public boolean getAllowChangingRows();

		public List<Map.Entry<String, String>> getEntries(T input);

		public void setPropertyValue(T input, String propertyName, String propertyValue);

		public void clearPropertyValue(T input, String propertyName);

		public void removeProperty(T input, String propertyName);
	}

	public static <T> TableViewer createTableViewer(Composite parent, final T input, final Controller<T> controller) {
		final TableViewer tv = new TableViewer(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_FOCUS);
		tv.setContentProvider(new ViewContentProvider<T>(controller));
		tv.setLabelProvider(new ViewLabelProvider());
		tv.setInput(input);

		Table table = tv.getTable();

		TableColumn column = new TableColumn(table, SWT.LEFT);
		column.setText(controller.getNameColumnHeader());

		TableColumn column2 = new TableColumn(table, SWT.LEFT);
		column2.setText(controller.getValueColumnHeader());

		TableLayout tableLayout = new AutoResizeTableLayout(table);
		tableLayout.addColumnData(new ColumnWeightData(30, true));
		tableLayout.addColumnData(new ColumnWeightData(70, true));
		table.setLayout(tableLayout);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		tv.setColumnProperties(new String[] { controller.getNameColumnHeader(), controller.getValueColumnHeader() });
		CellEditor tce = new TextCellEditor(table);
		tv.setCellEditors(new CellEditor[] { null, tce });
		tv.setCellModifier(new ViewCellModifier<T>(parent, controller, input));

		if (controller.getAllowChangingRows()) {
			Composite cChangeRows = new Composite(parent, SWT.NONE);
			cChangeRows.setLayout(new GridLayout(6, false));

			Label lName = new Label(cChangeRows, SWT.NONE);
			lName.setText("Name: ");

			final Text tfName = new Text(cChangeRows, SWT.SINGLE | SWT.BORDER);
			tfName.setEditable(true);
			tfName.setLayoutData(new GridData(150, SWT.DEFAULT));

			Label lValue = new Label(cChangeRows, SWT.NONE);
			lValue.setText("Value: ");

			final Text tfValue = new Text(cChangeRows, SWT.SINGLE | SWT.BORDER);
			tfValue.setEditable(true);
			tfValue.setLayoutData(new GridData(150, SWT.DEFAULT));

			SelectionListener slAddRow = new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				public void widgetSelected(SelectionEvent e) {
					String name = tfName.getText().trim();
					String value = tfValue.getText().trim();
					if (name.length() > 0 && value.length() > 0) {
						controller.setPropertyValue(input, tfName.getText(), tfValue.getText());
					}
				}
			};

			tfValue.addSelectionListener(slAddRow);

			final Button bAddRow = new Button(cChangeRows, SWT.PUSH);
			bAddRow.setText("Add");
			bAddRow.addSelectionListener(slAddRow);

			final Button bRemoveRow = new Button(cChangeRows, SWT.PUSH);
			bRemoveRow.setText("Remove Selected");
			bRemoveRow.setEnabled(!tv.getSelection().isEmpty());

			tv.addSelectionChangedListener(new ISelectionChangedListener() {

				public void selectionChanged(SelectionChangedEvent event) {
					bRemoveRow.setEnabled(!tv.getSelection().isEmpty());
				}
			});

			SelectionListener slRemoveRow = new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}

				public void widgetSelected(SelectionEvent e) {
					ISelection selection = tv.getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						Object element = structuredSelection.getFirstElement();
						if (element instanceof String[]) {
							String propertyName = ((String[]) element)[0];
							controller.removeProperty(input, propertyName);
						}
					}
				}
			};
			bRemoveRow.addSelectionListener(slRemoveRow);
		}

		tv.refresh();
		return tv;
	}

	protected static class ViewContentProvider<T> implements IStructuredContentProvider {
		private final Controller<T> controller;

		public ViewContentProvider(Controller<T> controller) {
			this.controller = controller;
		}

		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			List<Map.Entry<String, String>> nameValuePairList = controller.getEntries((T) inputElement);
			Object[] entryArray = new Object[nameValuePairList.size()];
			for (int i = 0; i < nameValuePairList.size(); i++) {
				Map.Entry<String, String> entry = nameValuePairList.get(i);
				entryArray[i] = new String[] { entry.getKey(), entry.getValue() };
			}
			return entryArray;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected static class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
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

	protected static class ViewCellModifier<T> implements ICellModifier {
		protected final Composite parent;
		protected final Controller<T> controller;
		protected final Object input;

		public ViewCellModifier(Composite parent, Controller<T> controller, Object input) {
			this.parent = parent;
			this.controller = controller;
			this.input = input;
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
				if (elts[1] == null) {
					return "";
				}
				return elts[1].toString();
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		public void modify(Object element, String property, Object value) {
			//SWT bug workaround
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			if (element instanceof String[]) {
				String[] elts = (String[]) element;
				String propertyName = elts[0].toString();

				String oldValue = null;
				if (elts[1] != null) {
					oldValue = elts[1].toString();
				}

				String newValue = null;
				if (value != null) {
					newValue = value.toString();
				}

				if (oldValue == null && newValue == null) {
					//Do nothing
				}
				else if (oldValue != null && newValue == null) {
					controller.clearPropertyValue((T) input, propertyName);
				}
				else if (oldValue == null && newValue != null) {
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

		@SuppressWarnings("unchecked")
		private void set(String propertyName, String propertyValue) {
			if (propertyName != null) {
				try {
					controller.setPropertyValue((T) input, propertyName, propertyValue);
				}
				catch (IllegalArgumentException iae) {
					MessageBox messageBox = new MessageBox(parent.getShell(), SWT.OK | SWT.ICON_ERROR);
					messageBox.setMessage("Invalid value for this field.");
					messageBox.setText("Error");
					messageBox.open();
				}
			}
		}
	}
}
