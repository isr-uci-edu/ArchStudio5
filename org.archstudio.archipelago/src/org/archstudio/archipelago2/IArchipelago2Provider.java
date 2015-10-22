package org.archstudio.archipelago2;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.widgets.Display;

/**
 * Provides resources to the Archipelago outline view. Instances of this object will be registered
 * with the MyxRegistry meaning that any events sent to the MyxRegistry will also be forwarded to
 * this provider. For instance, this object will receive {@link IXArchADTModelListener xArch ADT
 * Model Listener} events if it implements the {@link IXArchADTModelListener} interface.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2Provider {
  /**
   * Initialize this provider with the context of the Archipelago outline page. This method will
   * only be called once, immediately after instantiation.
   *
   * @param xarch The IXArchADT instance.
   * @param docRef The root document reference.
   * @param display The display to use for creating resources.
   * @param outline The Archipelago outline.
   */
  public void init(IXArchADT xarch, ObjRef docRef, Display display, IArchipelago2Outline outline);

  /**
   * Disposes of this provider.
   */
  public void dispose();
}
