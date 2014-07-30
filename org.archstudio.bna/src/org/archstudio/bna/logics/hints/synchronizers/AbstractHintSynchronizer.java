package org.archstudio.bna.logics.hints.synchronizers;

import java.util.Collection;
import java.util.Collections;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.hints.IHintSynchronizer;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public abstract class AbstractHintSynchronizer implements IHintSynchronizer {

	public boolean DEBUG = false;

	protected final IBNAWorld world;
	protected final IBNAModel model;
	protected final String hintName;
	private final Multiset<Object> ignoreHintUpdates = HashMultiset.create();

	public AbstractHintSynchronizer(IBNAWorld world, String hintName) {
		this.world = world;
		this.model = world.getBNAModel();
		this.hintName = hintName;
	}

	protected void ignore(Object context) {
		if (DEBUG) {
			System.out.println("   Ignoring: " + context);
		}
		ignoreHintUpdates.add(context);
	}

	protected boolean wasIgnored(Object context) {
		if (DEBUG) {
			if (ignoreHintUpdates.contains(context)) {
				System.out.println("    Ignored: " + context);
			}
			else {
				System.out.println("Not Ignored: " + context);
			}
		}
		return ignoreHintUpdates.remove(context);
	}

	@Override
	public final Collection<String> getRepositoryNamesOfInterest() {
		return Collections.singleton(hintName);
	}

}
