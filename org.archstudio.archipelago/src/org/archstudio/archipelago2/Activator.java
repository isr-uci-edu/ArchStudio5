package org.archstudio.archipelago2;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.common.base.Preconditions;

public class Activator extends AbstractUIPlugin {
  public static final String PLUGIN_ID = "org.archstudio.archipelago2"; //$NON-NLS-1$
  private static Activator plugin;

  public static Activator getDefault() {
    return plugin;
  }

  public Activator() {}

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public IMyxBrick getMyxBrick() {
    // The Archipelago v2.0 component id.
    String name = "ffa80104-7bc6719a-572f1ed7-727c0052";
    return Preconditions
        .checkNotNull(MyxRegistry.getSharedInstance().getBrick(MyxUtils.createName(name)));
  }
}
