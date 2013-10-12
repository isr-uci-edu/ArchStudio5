package org.archstudio.bna.utils;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModel.RunnableStreamNotification;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class IgnoreEvents {

	private final IBNAModel model;
	private final Multiset<Thread> ignoreUpdatesFromThread = HashMultiset.create();

	public IgnoreEvents(IBNAModel model) {
		this.model = model;
	}

	public boolean shouldIgnore(BNAModelEvent evt) {
		synchronized (this) {
			return ignoreUpdatesFromThread.contains(evt.getThread());
		}
	}

	public void startIgnoring() {
		final Thread threadToIgnore = Thread.currentThread();
		model.fireStreamNotificationEvent(new RunnableStreamNotification() {
			@Override
			public void run() {
				synchronized (IgnoreEvents.this) {
					ignoreUpdatesFromThread.add(threadToIgnore);
				}
			}
		});
	}

	public void stopIgnoring() {
		final Thread threadToIgnore = Thread.currentThread();
		model.fireStreamNotificationEvent(new RunnableStreamNotification() {
			@Override
			public void run() {
				synchronized (IgnoreEvents.this) {
					ignoreUpdatesFromThread.remove(threadToIgnore);
				}
			}
		});
	}

}
