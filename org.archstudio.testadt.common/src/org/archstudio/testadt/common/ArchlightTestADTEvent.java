package org.archstudio.testadt.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.archstudio.archlight.common.ArchlightTest;

public class ArchlightTestADTEvent implements java.io.Serializable{

	public enum EventType{
		TESTS_ADDED,
		TESTS_REMOVED
	}
	
	protected final EventType type;
	protected final List<ArchlightTest> tests;
	
	public ArchlightTestADTEvent(EventType type, Collection<? extends ArchlightTest> tests){
		this.type = type;
		this.tests = Collections.unmodifiableList(new ArrayList<ArchlightTest>(tests));
	}
	
	public List<ArchlightTest> getTests(){
		return tests;
	}

	public EventType getEventType(){
		return type;
	}

}
