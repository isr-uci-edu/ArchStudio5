package org.archstudio.noticeadt.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.archlight.common.ArchlightNotice;
import org.archstudio.noticeadt.common.ArchlightNoticeADTEvent;
import org.archstudio.noticeadt.common.ArchlightNoticeADTListener;
import org.archstudio.noticeadt.common.IArchlightNoticeADT;

public class ArchlightNoticeADT implements IArchlightNoticeADT {
	public static final int MAX_NOTICE_SIZE = 250;

	protected List<ArchlightNotice> toolNoticeList = new ArrayList<ArchlightNotice>();

	public ArchlightNoticeADT() {
		super();
	}

	public void addNotices(String toolID, List<String> messages) {
		List<ArchlightNotice> ttns = new ArrayList<ArchlightNotice>(messages.size());
		for (String message : messages) {
			ttns.add(new ArchlightNotice(toolID, message, null, null));
		}
		addNotices(ttns);
	}

	public void addNotice(String toolID, String message) {
		ArchlightNotice ttn = new ArchlightNotice(toolID, message, null, null);
		addNotice(ttn);
	}

	public void addNotice(String toolID, String message, String additionalDetail) {
		ArchlightNotice ttn = new ArchlightNotice(toolID, message, additionalDetail, null);
		addNotice(ttn);
	}

	public void addNotice(String toolID, String message, Throwable error) {
		ArchlightNotice ttn = new ArchlightNotice(toolID, message, null, error);
		addNotice(ttn);
	}

	public void addNotice(String toolID, String message, String additionalDetail, Throwable error) {
		ArchlightNotice ttn = new ArchlightNotice(toolID, message, additionalDetail, error);
		addNotice(ttn);
	}

	public synchronized void addNotice(ArchlightNotice ttn) {
		toolNoticeList.add(ttn);
		fireNoticesAdded(Collections.singleton(ttn));
		if (toolNoticeList.size() > MAX_NOTICE_SIZE) {
			ArchlightNotice removedNotice = (ArchlightNotice) toolNoticeList.remove(0);
			fireNoticesRemoved(Collections.singleton(removedNotice));
		}
	}

	public synchronized void addNotices(Collection<? extends ArchlightNotice> notices) {
		for (ArchlightNotice notice : notices) {
			toolNoticeList.add(notice);
			if (toolNoticeList.size() > MAX_NOTICE_SIZE) {
				ArchlightNotice removedNotice = (ArchlightNotice) toolNoticeList.remove(0);
				fireNoticesRemoved(Collections.singleton(removedNotice));
			}
		}
		fireNoticesAdded(notices);
	}

	public Collection<? extends ArchlightNotice> getAllNotices() {
		return Collections.unmodifiableCollection(toolNoticeList);
	}

	protected List<ArchlightNoticeADTListener> listeners = new CopyOnWriteArrayList<ArchlightNoticeADTListener>();

	public void addArchlightNoticeADTListener(ArchlightNoticeADTListener l) {
		listeners.add(l);
	}

	public void removeArchlightNoticeADTListener(ArchlightNoticeADTListener l) {
		listeners.remove(l);
	}

	protected void fireNoticesAdded(Collection<? extends ArchlightNotice> notices) {
		fireEvent(ArchlightNoticeADTEvent.EventType.NOTICES_ADDED, notices);
	}

	protected void fireNoticesRemoved(Collection<? extends ArchlightNotice> notices) {
		fireEvent(ArchlightNoticeADTEvent.EventType.NOTICES_REMOVED, notices);
	}

	protected void fireEvent(ArchlightNoticeADTEvent.EventType type, Collection<? extends ArchlightNotice> issues) {
		ArchlightNoticeADTEvent evt = new ArchlightNoticeADTEvent(type, issues);
		for (ArchlightNoticeADTListener l : listeners) {
			l.noticeADTChanged(evt);
		}
	}
}
