package org.archstudio.noticeview.core;

import org.archstudio.archlight.ArchlightNotice;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.noticeadt.ArchlightNoticeADTEvent;
import org.archstudio.noticeadt.ArchlightNoticeADTListener;
import org.archstudio.noticeadt.IArchlightNoticeADT;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.AutoResizeTableLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.part.ViewPart;

public class ArchlightNoticeView extends ViewPart implements ArchlightNoticeADTListener {
	private ArchlightNoticeViewMyxComponent comp = null;
	private final MyxRegistry er = MyxRegistry.getSharedInstance();

	private static final int COLUMN_INDEX_SEVERITY = 0;
	private static final int COLUMN_INDEX_SUMMARY = 1;
	private static final int COLUMN_INDEX_TOOL = 2;

	private static final String[] COLUMN_NAMES = new String[] { " " /* severity */, "Summary", "Tool" };

	private TableViewer viewer;

	protected IArchlightNoticeADT noticeadt = null;
	protected IResources resources = null;

	public ArchlightNoticeView() {
		comp = (ArchlightNoticeViewMyxComponent) er.waitForBrick(ArchlightNoticeViewMyxComponent.class);
		er.map(comp, this);
		noticeadt = comp.notices;
		resources = comp.resources;
	}

	@Override
	public void noticeADTChanged(ArchlightNoticeADTEvent evt) {
		refreshView();
	}

	public void refreshView() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				viewer.refresh();
			}
		});
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(getViewSite());

		Table table = viewer.getTable();

		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.setText(COLUMN_NAMES[0]);

		TableColumn column2 = new TableColumn(table, SWT.LEFT);
		column2.setText(COLUMN_NAMES[1]);

		TableColumn column3 = new TableColumn(table, SWT.LEFT);
		column3.setText(COLUMN_NAMES[2]);

		TableLayout tableLayout = new AutoResizeTableLayout(table);
		tableLayout.addColumnData(new ColumnWeightData(5, false));
		tableLayout.addColumnData(new ColumnWeightData(80, true));
		tableLayout.addColumnData(new ColumnWeightData(15, true));
		table.setLayout(tableLayout);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		//table.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		viewer.setColumnProperties(COLUMN_NAMES);
		//CellEditor tce = new TextCellEditor(table);
		//tv.setCellEditors(new CellEditor[]{null, tce});
		//tv.setCellModifier(new ViewCellModifier(selectedRefs[i]));

		viewer.refresh();

	}

	class ViewContentProvider implements IStructuredContentProvider {
		//private Object[] EMPTY_ARRAY = new Object[0];

		@Override
		public Object[] getElements(Object inputElement) {
			return noticeadt.getAllNotices().toArray();
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			if (obj instanceof ArchlightNotice) {
				if (index == COLUMN_INDEX_SUMMARY) {
					return ((ArchlightNotice) obj).getMessage();
				}
				else if (index == COLUMN_INDEX_TOOL) {
					return ((ArchlightNotice) obj).getToolID();
				}
			}
			return null;
		}

		@Override
		public Image getColumnImage(Object obj, int index) {
			if (obj instanceof ArchlightNotice) {
				if (index == COLUMN_INDEX_SEVERITY) {
					Throwable t = ((ArchlightNotice) obj).getError();
					if (t == null) {
						return resources.getPlatformImage(ISharedImages.IMG_OBJS_INFO_TSK);
					}
					else {
						return resources.getPlatformImage(ISharedImages.IMG_OBJS_ERROR_TSK);
					}
				}
			}
			return null;
		}

		@Override
		public Image getImage(Object obj) {
			return null;
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}