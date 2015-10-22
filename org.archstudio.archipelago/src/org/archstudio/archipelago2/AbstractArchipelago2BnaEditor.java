package org.archstudio.archipelago2;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * The Archipelago editor for statecharts, as defined by the {@link Statechart} xADL type.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public abstract class AbstractArchipelago2BnaEditor extends AbstractArchipelago2Editor
    implements IPropertyChangeListener {
  /** The BNA world used for the editor. */
  protected IBNAWorld bnaWorld;

  /** The BNA canvas backing the editor. */
  protected BNACanvas bnaCanvas;

  public AbstractArchipelago2BnaEditor() {
    super();
    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
  }

  @Override
  public void setFocus(List<Object> elementPath) {
    Archipelago2Utils.focusOnObjRef(bnaCanvas.getBNAView(), elementPath);
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    if (bnaCanvas == null || bnaCanvas.isDisposed()) {
      return;
    }
    try (Finally lock = BNAUtils.lock()) {
      BNARenderingSettings.setAntialiasGraphics(bnaCanvas,
          Archipelago2Preferences.getAntialiasGraphics());
      BNARenderingSettings.setAntialiasText(bnaCanvas, Archipelago2Preferences.getAntialiasText());
      BNARenderingSettings.setDecorativeGraphics(bnaCanvas,
          Archipelago2Preferences.getDecorativeGraphics());
      BNARenderingSettings.setDisplayShadows(bnaCanvas,
          Archipelago2Preferences.getDisplayShadows());
      GridThing gridThing = (GridThing) bnaWorld.getBNAModel().getThing(GridThing.class);
      if (gridThing != null) {
        gridThing.setGridSpacing(Archipelago2Preferences.getGridSpacing());
        gridThing.setGridDisplayType(Archipelago2Preferences.getGridDisplayType());
      }
      IBNAUI.AvailableUI availableUI = Archipelago2Preferences.getBNAUI();
      IBNAUI bnaUI = (IBNAUI) availableUI.getBNAUIClass().getConstructors()[0]
          .newInstance(bnaCanvas.getBNAView());
      bnaCanvas.setBNAUI(bnaUI);
    } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    } finally {
      bnaCanvas.redraw();
    }
  }
}
