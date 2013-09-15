package org.archstudio.bna.logics.hints.synchronizers;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.hints.IHintSynchronizer;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public abstract class AbstractHintSynchronizer implements IHintSynchronizer {

	protected static final boolean DEBUG = false;

	private static class HintReference {
		Object context;
		String name;

		public HintReference(Object context, String name) {
			this.context = context;
			this.name = name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (context == null ? 0 : context.hashCode());
			result = prime * result + (name == null ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			HintReference other = (HintReference) obj;
			if (context == null) {
				if (other.context != null) {
					return false;
				}
			}
			else if (!context.equals(other.context)) {
				return false;
			}
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			}
			else if (!name.equals(other.name)) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "IgnoreHintUpdate [context=" + context + ", name=" + name + "]";
		}

	}

	protected IBNAWorld bnaWorld;
	private final Multiset<HintReference> ignoreHintUpdates = HashMultiset.create();

	public AbstractHintSynchronizer() {
	}

	@Override
	public void setBNAWorld(@Nullable IBNAWorld bnaWorld) {
		this.bnaWorld = bnaWorld;
	}

	protected void ignore(Object context, String name) {
		HintReference hintReference = new HintReference(context, name);
		if (DEBUG) {
			System.err.println("   Ignoring: " + hintReference);
		}
		ignoreHintUpdates.add(hintReference);
	}

	protected boolean wasIgnored(Object context, String name) {
		HintReference hintReference = new HintReference(context, name);
		if (DEBUG) {
			if (ignoreHintUpdates.contains(hintReference)) {
				System.err.println("    Ignored: " + hintReference);
			}
			else {
				System.err.println("Not Ignored: " + hintReference);
			}
		}
		return ignoreHintUpdates.remove(hintReference);
	}
}
