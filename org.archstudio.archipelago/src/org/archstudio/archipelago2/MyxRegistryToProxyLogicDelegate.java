package org.archstudio.archipelago2;

import org.archstudio.bna.logics.events.IProxyLogicListener;
import org.archstudio.bna.logics.events.ProxyLogic;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;

import com.google.common.base.Preconditions;

/**
 * Automatically registers {@link ProxyLogic} objects with the MyxRegistry for the given brick. This
 * is primarily so that events sent to MyxRegistry listeners will also be sent to the proxy objects
 * from the {@link ProxyLogic}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class MyxRegistryToProxyLogicDelegate implements IProxyLogicListener {
  /** Brick to which proxy objects will be registered. */
  private final IMyxBrick brick;

  /**
   * Constructs a new instance, which will register proxy objects in the {@link MyxRegistry} for the
   * given brick.
   *
   * @param brick The brick to which the proxy objects will be registered.
   */
  public MyxRegistryToProxyLogicDelegate(IMyxBrick brick) {
    this.brick = Preconditions.checkNotNull(brick);
  }

  /**
   * Ignores proxy logic creation events.
   */
  @Override
  public void proxyLogicCreated(ProxyLogic proxyLogic) {}

  /**
   * Registers the given proxy object with the brick in the {@link MyxRegistry}.
   */
  @Override
  public void proxyObjectCreated(ProxyLogic proxyLogic, Object proxyObject) {
    MyxRegistry.getSharedInstance().registerObject(brick, proxyObject);
  }
}
