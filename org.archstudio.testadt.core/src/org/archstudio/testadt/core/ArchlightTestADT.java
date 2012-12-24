package org.archstudio.testadt.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.archlight.ArchlightTest;
import org.archstudio.testadt.ArchlightTestADTEvent;
import org.archstudio.testadt.ArchlightTestADTListener;
import org.archstudio.testadt.IArchlightTestADT;

public class ArchlightTestADT implements IArchlightTestADT {

	protected List<ArchlightTest> testList;

	public ArchlightTestADT() {
		testList = new ArrayList<ArchlightTest>();
	}

	@Override
	public synchronized List<? extends ArchlightTest> getAllTests() {
		return Collections.unmodifiableList(new ArrayList<ArchlightTest>(testList));
	}

	@Override
	public synchronized List<? extends ArchlightTest> getAllTests(String toolID) {
		List<ArchlightTest> matchingList = new ArrayList<ArchlightTest>();
		for (ArchlightTest test : testList) {
			if (test.getToolID() != null && test.getToolID().equals(toolID)) {
				matchingList.add(test);
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	@Override
	public synchronized ArchlightTest getTest(String testUID) {
		for (ArchlightTest test : testList) {
			if (test.getUID() != null && test.getUID().equals(testUID)) {
				return test;
			}
		}
		return null;
	}

	@Override
	public synchronized void addTests(Collection<? extends ArchlightTest> tests) {
		testList.addAll(tests);
		fireTestsAdded(tests);
	}

	@Override
	public synchronized void removeTests(Collection<? extends ArchlightTest> tests) {
		testList.removeAll(tests);
		fireTestsRemoved(tests);
	}

	protected List<ArchlightTestADTListener> listeners = new CopyOnWriteArrayList<ArchlightTestADTListener>();

	public void addArchlightTestADTListener(ArchlightTestADTListener l) {
		listeners.add(l);
	}

	public void removeArchlightTestADTListener(ArchlightTestADTListener l) {
		listeners.remove(l);
	}

	protected void fireTestsAdded(Collection<? extends ArchlightTest> tests) {
		fireEvent(ArchlightTestADTEvent.EventType.TESTS_ADDED, tests);
	}

	protected void fireTestsRemoved(Collection<? extends ArchlightTest> tests) {
		fireEvent(ArchlightTestADTEvent.EventType.TESTS_REMOVED, tests);
	}

	protected void fireEvent(ArchlightTestADTEvent.EventType type, Collection<? extends ArchlightTest> tests) {
		ArchlightTestADTEvent evt = new ArchlightTestADTEvent(type, tests);
		for (ArchlightTestADTListener l : listeners) {
			l.testADTChanged(evt);
		}
	}
}
