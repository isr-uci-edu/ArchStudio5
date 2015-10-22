package org.archstudio.archipelago2.core.outline.swt;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago2.IArchipelago2Outline;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

/**
 * Triggers an edit event on an element if it is clicked on once after it is already selected.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class EditOnClickOfAlreadySelectedElement implements ISelectionChangedListener {
  /** The outline being edited. */
  private final IArchipelago2Outline outline;
  /** The tree viewer on which to trigger edits. */
  private final TreeViewer treeViewer;
  /** The most recent selection of elements in the tree viewer. */
  private List<Object> previousSelection = new ArrayList<>();
  /** The editor to trigger. */
  private final EditObjRefNameInOutine editor;
  /** Set to <code>true</code> if editing was active on mouse down. */
  private boolean wasEditingOnMouseDown = false;
  /** Set to <code>true</code> if the next selection event should. */
  private boolean checkSelectionOnNextEvent = false;

  public EditOnClickOfAlreadySelectedElement(IArchipelago2Outline outline,
      final TreeViewer treeViewer, EditObjRefNameInOutine editor) {
    this.outline = outline;
    this.treeViewer = treeViewer;
    this.editor = editor;
    treeViewer.getTree().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDown(MouseEvent e) {
        wasEditingOnMouseDown = treeViewer.isCellEditorActive();
        checkSelectionOnNextEvent = true;
      }
    });
    treeViewer.addPostSelectionChangedListener(this);
  }

  @Override
  public void selectionChanged(SelectionChangedEvent event) {
    @SuppressWarnings("unchecked")
    List<Object> newSelection = ((IStructuredSelection) event.getSelection()).toList();
    if (!wasEditingOnMouseDown && checkSelectionOnNextEvent) {
      if (previousSelection.equals(newSelection)) {
        for (final Object element : newSelection) {
          if (editor.canEdit(element)) {
            treeViewer.getTree().getDisplay().asyncExec(new Runnable() {
              @Override
              public void run() {
                outline.editElement(element, 0);
              }
            });
          }
          break;
        }
      }
    }
    previousSelection = newSelection;
    checkSelectionOnNextEvent = false;
  }
}
