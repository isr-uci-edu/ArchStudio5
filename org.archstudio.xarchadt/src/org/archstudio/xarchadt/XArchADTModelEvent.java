package org.archstudio.xarchadt;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

@NonNullByDefault
public class XArchADTModelEvent implements java.io.Serializable {

	private static final long serialVersionUID = -3787246697456371402L;

	public enum EventType {
		SET, CLEAR, ADD, REMOVE
	}

	protected final EventType eventType;
	protected final String featureName;

	protected final ObjRef src;
	protected final List<ObjRef> srcAncestors;
	protected final String srcPath;

	protected final Object oldValue;
	protected final List<ObjRef> oldValueAncestors;
	protected final String oldValuePath;

	protected final Object newValue;
	protected final List<ObjRef> newValueAncestors;
	protected final String newValuePath;

	/**
	 * Create a new xArch event.
	 */
	public XArchADTModelEvent(EventType eventType, ObjRef src, List<ObjRef> srcAncestors, String srcPath,
			String featureName, @Nullable Object oldValue, @Nullable String oldValuePath, @Nullable Object newValue,
			@Nullable String newValuePath) {

		this.eventType = eventType;
		this.featureName = featureName;

		this.src = src;
		this.srcAncestors = Collections.unmodifiableList(Lists.newArrayList(srcAncestors));
		this.srcPath = srcPath;

		this.oldValue = oldValue;
		this.oldValueAncestors = oldValue instanceof ObjRef ? prepend((ObjRef) oldValue, srcAncestors) : null;
		this.oldValuePath = oldValuePath;

		this.newValue = newValue;
		this.newValueAncestors = newValue instanceof ObjRef ? prepend((ObjRef) newValue, srcAncestors) : null;
		this.newValuePath = newValuePath;
	}

	private List<ObjRef> prepend(ObjRef objRef, List<ObjRef> srcAncestors) {
		List<ObjRef> ancestors = Lists.newArrayListWithCapacity(1 + srcAncestors.size());
		ancestors.add(objRef);
		ancestors.addAll(srcAncestors);
		return Collections.unmodifiableList(ancestors);
	}

	/**
	 * Get the event type of this event.
	 * 
	 * @return Event type.
	 */
	public EventType getEventType() {
		return eventType;
	}

	public String getFeatureName() {
		return featureName;
	}

	/**
	 * Get the source xArch element of this event.
	 * 
	 * @return source of this event.
	 */
	public ObjRef getSource() {
		return src;
	}

	public List<ObjRef> getSourceAncestors() {
		return srcAncestors;
	}

	public String getSourcePath() {
		return srcPath;
	}

	public @Nullable
	Object getOldValue() {
		return oldValue;
	}

	public @Nullable
	List<ObjRef> getOldValueAncestors() {
		return oldValueAncestors;
	}

	public @Nullable
	String getOldValuePath() {
		return oldValuePath;
	}

	public @Nullable
	Object getNewValue() {
		return newValue;
	}

	public @Nullable
	List<ObjRef> getNewValueAncestors() {
		return newValueAncestors;
	}

	public @Nullable
	String getNewValuePath() {
		return newValuePath;
	}

	public String toString() {
		return "XArchADTModelEvent[" + //
				"eventType=" + eventType + ", " + //
				"featureName=" + featureName + ", " + //
				"oldValue=" + oldValue + ", " + //
				"oldValueAncestors=" + oldValueAncestors + ", " + //
				"oldValuePath=" + oldValuePath + ", " + //
				"newValue=" + newValue + ", " + //
				"newValueAncestors=" + newValueAncestors + ", " + //
				"newValuePath=" + newValuePath + ", " + //
				"src=" + src + ", " + //
				"srcAncestors=" + srcAncestors + ", " + //
				"srcPath=" + srcPath + "]";
	}
}
