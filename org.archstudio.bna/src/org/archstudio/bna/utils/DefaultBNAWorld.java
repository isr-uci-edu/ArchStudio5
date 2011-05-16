package org.archstudio.bna.utils;

import static org.archstudio.sysutils.SystemUtils.sortedByValue;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class DefaultBNAWorld implements IBNAWorld, IBNAModelListener, IBNASynchronousModelListener {

	protected static final boolean DEBUG = false;
	protected final Map<Object, AtomicLong> listenerStats = !DEBUG ? null : new MapMaker().weakKeys().makeComputingMap(
			new Function<Object, AtomicLong>() {
				@Override
				public AtomicLong apply(Object input) {
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

		getBNAModel().addSynchronousBNAModelListener(this);
		getBNAModel().addBNAModelListener(this);
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt) {
		for (IBNAModelListener logic : logicManager.getThingLogics(IBNAModelListener.class)) {
			try {
				long lTime;
				if (DEBUG) {
					lTime = System.nanoTime();
				}
				logic.bnaModelChanged(evt);
				if (DEBUG) {
					lTime = System.nanoTime() - lTime;
					listenerStats.get(logic).addAndGet(lTime);
				}
			}
			catch (Throwable t) {
			}
		}

		if (DEBUG) {
			System.err.println("----");
			for (Map.Entry<Object, AtomicLong> e : sortedByValue(listenerStats.entrySet())) {
				System.err.println(e.getValue() + " " + e.getKey());
			}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		for (IBNASynchronousModelListener logic : logicManager.getThingLogics(IBNASynchronousModelListener.class)) {
			try {
				long lTime;
				if (DEBUG) {
					lTime = System.nanoTime();
				}
				logic.bnaModelChangedSync(evt);
				if (DEBUG) {
					lTime = System.nanoTime() - lTime;
					listenerStats.get(logic).addAndGet(lTime);
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
	public void destroy() {
		logicManager.destroy();

		model.removeBNAModelListener(this);
		model.removeSynchronousBNAModelListener(this);

		isDestroyed = true;
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
