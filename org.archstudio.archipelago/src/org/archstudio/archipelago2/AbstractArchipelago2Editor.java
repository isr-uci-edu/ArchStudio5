package org.archstudio.archipelago2;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractArchipelago2Editor implements IArchipelago2Editor {
  /** XArchADT instance backing this label provider. */
  protected IXArchADT xarch;

  /** XArchADT root document reference. */
  protected ObjRef docRef;

  /** Control that should receive focus upon calls to {@link #setFocus()}. */
  Control focusControl = null;

  /** Initializes the instance of this editor, called immediately after instantiation. */
  @Override
  public void init(IXArchADT xarch, ObjRef docRef) {
    this.xarch = xarch;
    this.docRef = docRef;
  }

  @Override
  public void dispose() {}

  /**
   * Sets the control that should receive focus upon calls to {@link #setFocus()}.
   *
   * @param control The control that should receive focus.
   * @return The control passed in.
   */
  protected <T extends Control> T setFocusControl(T control) {
    focusControl = control;
    return control;
  }

  @Override
  public void setFocus() {
    if (focusControl != null) {
      focusControl.setFocus();
    }
  }
}
