package org.archstudio.xarchadt;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class XArchADTFileEvent implements java.io.Serializable {

	private static final long serialVersionUID = -1173916108861836949L;

	public enum EventType {
		XARCH_CREATED_EVENT, XARCH_OPENED_EVENT, XARCH_CLOSED_EVENT, XARCH_SAVED_EVENT, XARCH_RENAMED_EVENT
	};

	private EventType eventType;
	private URI uri;
	private URI asURI = null;
	private ObjRef rootElementRef = null;

	public XArchADTFileEvent(EventType eventType, URI uri) {
		this.eventType = eventType;
		this.uri = uri;
	}

	public XArchADTFileEvent(EventType eventType, URI url, URI asURI) {
		this.eventType = eventType;
		this.uri = url;
		this.asURI = asURI;
	}

	public XArchADTFileEvent(EventType eventType, URI uri, ObjRef rootElementRef) {
		this.eventType = eventType;
		this.uri = uri;
		this.rootElementRef = rootElementRef;
	}

	public XArchADTFileEvent(EventType eventType, URI uri, URI asURI, ObjRef rootElementRef) {
		this.eventType = eventType;
		this.uri = uri;
		this.asURI = asURI;
		this.rootElementRef = rootElementRef;
	}

	public XArchADTFileEvent(XArchADTFileEvent eventToCopy, ObjRef rootElementRef) {
		this.eventType = eventToCopy.eventType;
		this.uri = eventToCopy.uri;
		this.asURI = eventToCopy.asURI;
		this.rootElementRef = rootElementRef;
	}

	public EventType getEventType() {
		return eventType;
	}

	public URI getURI() {
		return uri;
	}

	public URI getAsURI() {
		return asURI;
	}

	public ObjRef getRootElementRef() {
		return rootElementRef;
	}

}
