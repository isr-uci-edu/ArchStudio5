package org.archstudio.noticeadt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.archstudio.archlight.ArchlightNotice;

public class ArchlightNoticeADTEvent implements java.io.Serializable {

	public enum EventType {
		NOTICES_ADDED, NOTICES_REMOVED
	}

	protected final EventType type;
	protected final Collection<? extends ArchlightNotice> notices;

	public <T extends ArchlightNotice> ArchlightNoticeADTEvent(EventType type, Collection<T> notices) {
		this.type = type;
		this.notices = Collections.unmodifiableCollection(new ArrayList<T>(notices));
	}

	public Collection<? extends ArchlightNotice> getNotices() {
		return notices;
	}

	public EventType getEventType() {
		return type;
	}

}
