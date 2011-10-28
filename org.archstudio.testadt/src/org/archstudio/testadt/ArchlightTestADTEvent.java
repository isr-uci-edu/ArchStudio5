package org.archstudio.testadt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.archstudio.archlight.ArchlightTest;

public class ArchlightTestADTEvent implements java.io.Serializable {

	private static final long serialVersionUID = -6448627789883470715L;

	public enum EventType {
		TESTS_ADDED, TESTS_REMOVED
	}

	protected final EventType type;
	protected final List<ArchlightTest> tests;

	public ArchlightTestADTEvent(EventType type, Collection<? extends ArchlightTest> tests) {
		this.type = type;
		this.tests = Collections.unmodifiableList(new ArrayList<ArchlightTest>(tests));
	}

	public List<ArchlightTest> getTests() {
		return tests;
	}

	public EventType getEventType() {
		return type;
	}

}
