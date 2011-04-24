package org.archstudio.myx.fw;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class MyxContainer implements IMyxContainer {

	protected Set<IMyxBrick> internalBricks = Collections.synchronizedSet(new CopyOnWriteArraySet<IMyxBrick>());
	protected IMyxBrickItems brickItems = null;

	protected final IMyxProvidedServiceProvider providedServiceProvider;
	protected final Set<? extends IMyxLifecycleProcessor> lifecycleProcessors = Collections.synchronizedSet(Collections
	        .singleton(new MyxContainerLifecycleProcessor(this)));

	public MyxContainer() {
		providedServiceProvider = new MyxBasicProvidedServiceProvider();
	}

	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		this.brickItems = brickItems;
	}

	public IMyxBrickItems getMyxBrickItems() {
		return this.brickItems;
	}

	public void addInternalBrick(IMyxBrick brick) {
		internalBricks.add(brick);
	}

	public void removeInternalBrick(IMyxBrick brick) {
		internalBricks.remove(brick);
	}

	public Collection<? extends IMyxBrick> getInternalBricks() {
		return Collections.unmodifiableSet(internalBricks);
	}

	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return lifecycleProcessors;
	}

	public IMyxProvidedServiceProvider getProvidedServiceProvider() {
		return providedServiceProvider;
	}

	public IMyxBrick getInternalBrick(IMyxName brickName) {
		for (IMyxBrick brick : getInternalBricks()) {
			IMyxBrickItems brickItems = brick.getMyxBrickItems();
			if (brickItems != null) {
				IMyxName brickName2 = brickItems.getBrickName();
				if (brickName2 != null) {
					if (brickName2.equals(brickName)) {
						return brick;
					}
				}
			}
		}
		return null;
	}

	static class MyxContainerLifecycleProcessor implements IMyxLifecycleProcessor {
		private final MyxContainer c;

		private MyxContainerLifecycleProcessor(MyxContainer c) {
			this.c = c;
		}

		public void init() {
			op(Operation.INIT);
		}

		public void begin() {
			op(Operation.BEGIN);
		}

		public void end() {
			op(Operation.END);
		}

		public void destroy() {
			op(Operation.DESTROY);
		}

		private void op(Operation op) {
			for (IMyxBrick brick : c.getInternalBricks()) {
				Collection<? extends IMyxLifecycleProcessor> lps = brick.getLifecycleProcessors();
				if (lps != null) {
					for (IMyxLifecycleProcessor lp : lps) {
						switch (op) {
						case INIT:
							lp.init();
							break;
						case BEGIN:
							lp.begin();
							break;
						case END:
							lp.end();
							break;
						case DESTROY:
							lp.destroy();
							break;
						}
					}
				}
			}

		}

	}

}
