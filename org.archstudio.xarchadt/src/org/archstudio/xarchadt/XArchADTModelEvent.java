package org.archstudio.xarchadt;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class XArchADTModelEvent implements java.io.Serializable {

	private static final long serialVersionUID = -3787246697456371402L;

	public enum EventType {
		SET, CLEAR, ADD, REMOVE
	}

	protected final EventType eventType;
	protected final String featureName;

	protected final ObjRef src;
	protected final List<ObjRef> srcAncestors;
	protected final XArchADTPath srcPath;

	protected final Object oldValue;
	protected final List<ObjRef> oldValueAncestors;
	protected final XArchADTPath oldValuePath;

	protected final Object newValue;
	protected final List<ObjRef> newValueAncestors;
	protected final XArchADTPath newValuePath;

	/**
	 * Create a new xArch event.
	 * 
	 * @param src
	 *            xArch element that is the source of this event (i.e. that
	 *            changed.)
	 * @param eventType
	 *            One of the event types (above) that indicates what happened.
	 * @param srcType
	 *            One of the event types (above) that indicates what changed.
	 * @param targetName
	 *            Name of the element or attribute that was
	 *            set/added/cleared/removed.
	 * @param target
	 *            The attribute/element/value that was
	 *            set/added/cleared/removed.
	 * @param isAttached
	 *            <code>true</code> if the element that was changed is actually
	 *            connected to the xArch element emitting this event or not.
	 */
	public XArchADTModelEvent(EventType eventType, ObjRef src, List<ObjRef> srcAncestors, XArchADTPath srcPath,
			String featureName, Object oldValue, XArchADTPath oldValuePath, Object newValue, XArchADTPath newValuePath) {
		assert srcAncestors.size() == srcPath.getLength() + 1;

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

	/**
	 * Get the <CODE>XArchADTPath</CODE> to the source element of this event.
	 * 
	 * @return <CODE>XArchADTPath</CODE> to source.
	 */
	public XArchADTPath getSourcePath() {
		return srcPath;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public List<ObjRef> getOldValueAncestors() {
		return oldValueAncestors;
	}

	public XArchADTPath getOldValuePath() {
		return oldValuePath;
	}

	public Object getNewValue() {
		return newValue;
	}

	public List<ObjRef> getNewValueAncestors() {
		return newValueAncestors;
	}

	public XArchADTPath getNewValuePath() {
		return newValuePath;
	}

	@Override
	public String toString() {
		return "XArchADTModelEvent[" + //
				"eventType=" + eventType + ", " + //
				"featureName=" + featureName + ", " + //
				"oldValue=" + oldValue + ", " + //
				"newValue=" + newValue + ", " + //
				"src=" + src + ", " + //
				"srcAncestors=" + srcAncestors + ", " + //
				"srcPath=" + srcPath + "]";
	}
}
