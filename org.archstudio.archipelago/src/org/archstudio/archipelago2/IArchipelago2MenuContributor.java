package org.archstudio.archipelago2;

import java.util.List;

import org.archstudio.bna.ui.IBNAMenuListener2;
import org.eclipse.jface.action.IMenuManager;

/**
 * Contributes menu items to elements in the Archipelago outline. Groups defined in
 * {@link IBNAMenuListener2} will be present for sub-menu use.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2MenuContributor extends IArchipelago2Provider {
  /**
   * Contributes menu items to the Archipelago Outline page. Groups defined in
   * {@link IBNAMenuListener2} will be present for sub-menu use.
   *
   * @param outline The Archipelago outline displaying the menu.
   * @param element A {@link List} of all node objects starting with the docRef root and including
   *        all node elements up to the leaf being queried. The list will always have at least the
   *        docRef element in it.
   * @param menuManager The {@link IMenuManager} to use to contribute menu items to.
   */
  public void fillMenu(IArchipelago2Outline outline, List<Object> element,
      IMenuManager menuManager);
}
