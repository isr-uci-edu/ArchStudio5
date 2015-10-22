package org.archstudio.archipelago2;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.widgets.Display;

public abstract class AbstractArchipelago2Provider implements IArchipelago2Provider {
  /** XArchADT instance backing this label provider. */
  protected IXArchADT xarch;

  /** XArchADT root document reference. */
  protected ObjRef docRef;

  /** The display to use for creating resources. */
  protected Display display;

  /** The Archipelago outline. */
  protected IArchipelago2Outline outline;

  @Override
  public void init(IXArchADT xarch, ObjRef docRef, Display display, IArchipelago2Outline outline) {
    this.xarch = xarch;
    this.docRef = docRef;
    this.display = display;
    this.outline = outline;
  }

  @Override
  public void dispose() {}
}
