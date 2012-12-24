package org.archstudio.myx.fw;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
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

	@Override
	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		this.brickItems = brickItems;
	}

	@Override
	public IMyxBrickItems getMyxBrickItems() {
		return this.brickItems;
	}

	@Override
	public void addInternalBrick(IMyxBrick brick) {
		internalBricks.add(brick);
	}

	@Override
	public void removeInternalBrick(IMyxBrick brick) {
		internalBricks.remove(brick);
	}

	@Override
	public Collection<? extends IMyxBrick> getInternalBricks() {
		return Collections.unmodifiableSet(internalBricks);
	}

	@Override
	public Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return lifecycleProcessors;
	}

	@Override
	public IMyxProvidedServiceProvider getProvidedServiceProvider() {
		return providedServiceProvider;
	}

	@Override
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

		@Override
		public void init() {
			op(Operation.INIT);
		}

		@Override
		public void begin() {
			op(Operation.BEGIN);
		}

		@Override
		public void end() {
			op(Operation.END);
		}

		@Override
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
