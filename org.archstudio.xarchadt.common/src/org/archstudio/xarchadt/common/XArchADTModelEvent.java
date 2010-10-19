package org.archstudio.xarchadt.common;

import java.util.ArrayList;
import java.util.List;

public class XArchADTModelEvent  implements java.io.Serializable{

	public enum EventType{
		SET,
		CLEAR,
		ADD,
		REMOVE
	}
	
	protected final EventType eventType;
	protected final ObjRef src;
	protected final List<ObjRef> srcAncestors;
	protected final XArchADTPath srcPath;
	
	protected final String featureName;
	
	protected final Object oldValue;
	
	/**
	 * This is the <CODE>XArchADTPath</CODE> to the oldValue if the oldValue
	 * is an ObjRef, or <CODE>null</CODE> if it's not.
	 */
	protected final XArchADTPath oldValuePath;
	
	protected final Object newValue;
	
	protected final XArchADTPath newValuePath;
	
	/**
	 * Create a new xArch event.
	 *
	 * @param src xArch element that is the source of this event (i.e. that
	 * changed.)
	 * @param eventType One of the event types (above) that indicates what
	 * happened.
	 * @param srcType One of the event types (above) that indicates what
	 * changed.
	 * @param targetName Name of the element or attribute that was 
	 * set/added/cleared/removed.
	 * @param target The attribute/element/value that was 
	 * set/added/cleared/removed.
	 * @param isAttached <code>true</code> if the element that was changed is
	 * actually connected to the xArch element emitting this event or not.
	 */
	public XArchADTModelEvent(EventType eventType, ObjRef src, List<ObjRef> srcAncestors, XArchADTPath srcPath, 
	String featureName, 
	Object oldValue, XArchADTPath oldValuePath,
	Object newValue, XArchADTPath newValuePath){
		this.eventType = eventType;
		this.src = src;
		this.srcAncestors = new ArrayList<ObjRef>(srcAncestors);
		this.srcPath = srcPath;
		
		this.featureName = featureName;

		this.oldValue = oldValue;
		this.oldValuePath = oldValuePath;

		this.newValue = newValue;
		this.newValuePath = newValuePath;
	}

	/**
	 * Get the source xArch element of this event.
	 * @return source of this event.
	 */
	public ObjRef getSource(){
		return src;
	}
	
	public List<ObjRef> getSourceAncestors(){
		return new ArrayList<ObjRef>(srcAncestors);
	}
	
	/**
	 * Get the <CODE>XArchPath</CODE> to the source element
	 * of this event.
	 * @return <CODE>XArchPath</CODE> to source.
	 */
	public XArchADTPath getSourcePath(){
		return srcPath;
	}
	
	/**
	 * Get the event type of this event.
	 * @return Event type.
	 */
	public EventType getEventType(){
		return eventType;
	}
	
	public String getFeatureName() {
    	return featureName;
    }

	public Object getOldValue() {
    	return oldValue;
    }

	public XArchADTPath getOldValuePath() {
    	return oldValuePath;
    }

	public Object getNewValue() {
    	return newValue;
    }

	public XArchADTPath getNewValuePath() {
    	return newValuePath;
    }

	public String toString(){
		return "XArchFlatEvent{" +
			"eventType=" + eventType + ", " +
			"src=" + src + ", " +
			"srcPath=" + srcPath + ", " +
			"featureName=" + featureName + ", " +
			"oldValue=" + oldValue + ", " +
			"oldValuePath=" + oldValuePath + 
			"newValue=" + newValue + ", " +
			"newValuePath=" + newValuePath + 
			"};";
	}
}
