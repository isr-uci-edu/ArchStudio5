package org.archstudio.archipelago2.core.outline.swt;

import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTFeature.FeatureType;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

/**
 * Enables the editing of ObjRef name attributes within the outline.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class EditObjRefNameInOutine {
  /** The tree viewer behind the editor. */
  private final TreeViewer treeViewer;
  /** The xArh ADT interface. */
  private final IXArchADT xarch;
  /** Set to <code>true</code> to allow editing of a cell, <code>false</code> otherwise. */
  private boolean allowEdits = false;
  /** Currently edited element. */
  private Object editingElement = null;

  public EditObjRefNameInOutine(IArchipelago2Outline outline, TreeViewer treeViewer,
      final IXArchADT xarch) {
    this.treeViewer = treeViewer;
    this.xarch = xarch;
    final Tree tree = treeViewer.getTree();
    // Create a column for editing.
    final TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.NONE);
    // Set the width of the column to that of the tree.
    tree.addControlListener(new ControlAdapter() {
      @Override
      public void controlResized(ControlEvent e) {
        column.getColumn().setWidth(tree.getBounds().width);
      }
    });

    // Add text editor.
    ColumnViewerEditorActivationStrategy editor =
        new ColumnViewerEditorActivationStrategy(treeViewer) {
          @Override
          protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
            return allowEdits;
          }
        };
    TreeViewerEditor.create(treeViewer, editor, ColumnViewerEditor.DEFAULT);
    // Make the cell editor have a border.
    column.setEditingSupport(new EditingSupport(treeViewer) {
      final TextCellEditor cellEditor = new TextCellEditor(tree, SWT.BORDER) {
        @Override
        public LayoutData getLayoutData() {
          LayoutData data = super.getLayoutData();
          Text text = (Text) getControl();
          data.minimumHeight = text.getLineHeight() + text.getBorderWidth() + 6;
          return data;
        }
      };

      @Override
      protected void setValue(Object element, Object value) {
        ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
        xarch.set(objRef, "name", value.toString());
      }

      @Override
      protected Object getValue(Object element) {
        ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
        String name = (String) xarch.get(objRef, "name");
        if (name == null) {
          return "[No Name]";
        }
        return name;
      }

      @Override
      protected TextCellEditor getCellEditor(Object element) {
        return cellEditor;
      }

      @Override
      protected boolean canEdit(Object element) {
        return EditObjRefNameInOutine.this.canEdit(element);
      }
    });
  }

  /**
   * Causes the outline to edit the text of the given element if it exists and can be edited.
   *
   * @param element The element to edit.
   * @param column The column to edit.
   */
  public void editElement(Object element, int column) {
    try {
      allowEdits = true;
      treeViewer.editElement(editingElement = element, 0);
    } finally {
      allowEdits = false;
    }
  }

  /**
   * Returns <code>true</code> if the editor can edit the selected element, or <code>false</code>
   * otherwise.
   */
  public boolean canEdit(Object element) {
    ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
    if (objRef != null) {
      IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
      IXArchADTFeature nameFeature = type.getFeatures().get("name");
      if (nameFeature != null && nameFeature.getType() == FeatureType.ATTRIBUTE) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the current element begin edited.
   *
   * @return the current element being edited.
   */
  public Object getEditingElement() {
    return editingElement;
  }
}
