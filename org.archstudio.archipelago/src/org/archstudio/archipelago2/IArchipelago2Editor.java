package org.archstudio.archipelago2;

import java.util.List;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

/**
 * Creates an Archipelago editor for a given {@link IArchipelago2ContentProvider element path}.
 * <p/>
 * This class will be instantiated with a call to {@link #init(IXArchADT, ObjRef)} and disposed with
 * a call to {@link #dispose()}. However, it is not guaranteed that a call to to
 * {@link #createPartControl(Composite, ObjRef)} will ever be made. may never be made.
 * <p/>
 * This class will also be registered as a listener for all "in" interface listeners on the
 * Archipelago Myx component. For example, the Archipelago Myx component has an "in" interface of
 * the type {@link IXArchADTModelListener}. If this editor also implements
 * {@link IXArchADTModelListener}, it will receive those events. However, it will only be registered
 * after a call to {@link #createPartControl(Composite, ObjRef)} is made.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2Editor {
  /**
   * Initialize the editor with the the context of the Archipelago editor. This method will only be
   * called once, immediately after instantiation.
   *
   * @param xarch the IXArchADT instance.
   * @param docRef the root document reference.
   */
  public void init(IXArchADT xarch, ObjRef docRef);

  /**
   * Disposes this editor. No methods will be called after a call to this method. Note: this method
   * may be called without ever making a call {@link #createPartControl(Composite, ObjRef)}.
   */
  public void dispose();

  /**
   * Returns whether this editor can edit the given element in the Archipelago outline. If so,
   * Archipelago may call {@link #createPartControl(Composite, ObjRef)} to create the actual editor.
   *
   * @param elementPath the list of nodes starting with the docRef passed to
   *        {@link #init(IXArchADT, ObjRef)} and ending with the specific node. See
   *        {@link IArchipelago2ContentProvider} for a description of the elementPath.
   * @return <code>true</code> if this instance can create an editor for editRef, <code>false</code>
   *         otherwise.
   */
  public boolean canEdit(List<Object> elementPath);

  /**
   * Creates the SWT controls for this workbench part, which creates the editor for the given
   * editRef. This method will only be called once on this object, which will be disposed if another
   * editRef is selected to be edited.
   * <p>
   * For implementors this is a multi-step process:
   * <ol>
   * <li>Create one or more controls within the parent.</li>
   * <li>Set the parent layout as needed.</li>
   * <li>Register any global actions with the site's <code>IActionBars</code>.</li>
   * <li>Register any context menus with the site.</li>
   * <li>Register a selection provider with the site, to make it available to the workbench's
   * <code>ISelectionService</code> (optional).</li>
   * </ol>
   * </p>
   *
   * @param editorPart The editor part containing the Archipelago editor.
   * @param parent The parent control.
   * @param elementPath the list of nodes starting with the docRef passed to
   *        {@link #init(IXArchADT, ObjRef)} and ending with the specific node. See
   *        {@link IArchipelago2ContentProvider} for a description of the elementPath.
   */
  public void createPartControl(IEditorPart editorPart, Composite parent,
      List<Object> elementPath);

  /**
   * Asks this part to take focus within the workbench. Parts must assign focus to one of the
   * controls contained in the part's parent composite.
   */
  public void setFocus();

  /**
   * Asks this part to focus on the given element.
   *
   * @param elementPath the list of nodes starting with the docRef passed to
   *        {@link #init(IXArchADT, ObjRef)} and ending with the specific node. See
   *        {@link IArchipelago2ContentProvider} for a description of the elementPath.
   */
  public void setFocus(List<Object> elementPath);
}
