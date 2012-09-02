package org.archstudio.bna.utils;

import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class DefaultBNAWorld implements IBNAWorld, IBNAModelListener {

	protected static final boolean DEBUG = false;
	protected final LoadingCache<Object, AtomicLong> debugStats = !DEBUG ? null : CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	protected Object id = null;
	protected IBNAModel model = null;
	protected boolean isDestroyed = false;

	protected transient IThingLogicManager logicManager = null;

	public DefaultBNAWorld(Object id, IBNAModel model) {
		this.id = id;
		this.model = model;
		this.logicManager = new DefaultThingLogicManager(this);

		getBNAModel().addBNAModelListener(this);
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		for (IBNAModelListener logic : logicManager.getThingLogics(IBNAModelListener.class)) {
			try {
				long lTime;
				if (DEBUG) {
					lTime = System.nanoTime();
				}
				logic.bnaModelChanged(evt);
				if (DEBUG) {
					lTime = System.nanoTime() - lTime;
					debugStats.getUnchecked(logic).addAndGet(lTime);
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	@Override
	public IThingLogicManager getThingLogicManager() {
		return logicManager;
	}

	@Override
	public void dispose() {
		logicManager.destroy();

		model.removeBNAModelListener(this);
		model.removeBNAModelListener(this);

		isDestroyed = true;

		if (DEBUG) {
			for (Entry<Object, AtomicLong> e : SystemUtils.sortedByValue(debugStats.asMap().entrySet())) {
				System.err.println(e.getValue() + " " + e.getKey());
			}
		}
	}

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public Object getID() {
		return id;
	}

	@Override
	public IBNAModel getBNAModel() {
		return model;
	}

}
