package org.archstudio.archlight.core;

import java.util.Collection;

import org.archstudio.archlight.IArchlightTool;
import org.archstudio.myx.conn.IMultiwayProgressListener;
import org.archstudio.myx.conn.IMultiwayResults;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class ArchlightToolAggregatorMyxComponent extends AbstractMyxSimpleBrick implements IArchlightTool, IMultiwayProgressListener, IMyxDynamicBrick {

	public static final String TOOL_ID = "Aggregator";

	public static final IMyxName INTERFACE_NAME_OUT_RESULTS = MyxUtils.createName("results");
	public static final IMyxName INTERFACE_NAME_OUT_TOOLS_MULTIWAY = MyxUtils.createName("archlighttools");
	public static final IMyxName INTERFACE_NAME_IN_TOOL = MyxUtils.createName("archlighttool");
	public static final IMyxName INTERFACE_NAME_IN_PROGRESS = MyxUtils.createName("progress");

	protected IArchlightTool tools = null;
	protected IMultiwayResults results = null;

	public ArchlightToolAggregatorMyxComponent() {
	}

	public void init() {
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_RESULTS)) {
			results = (IMultiwayResults) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_TOOLS_MULTIWAY)) {
			tools = (IArchlightTool) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_RESULTS)) {
			results = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_TOOLS_MULTIWAY)) {
			tools = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_TOOL)) {
			return this;
		}
		else if (interfaceName.equals(INTERFACE_NAME_IN_PROGRESS)) {
			return this;
		}
		return null;
	}

	public String getToolID() {
		return TOOL_ID;
	}

	public void reloadTests() {
		if (tools != null)
			tools.reloadTests();
	}

	boolean runningTests = false;
	protected IProgressMonitor progressMonitor = null;

	public void runTests(ObjRef documentRef, Collection<String> testUIDs) {
		if (runningTests)
			return;

		runningTests = true;
		final ObjRef fdocumentRef = documentRef;
		final Collection<String> ftestUIDs = testUIDs;
		if (tools != null) {
			Job job = new Job("Running Archlight Tests") {
				protected IStatus run(IProgressMonitor monitor) {
					try {
						progressMonitor = monitor;
						tools.runTests(fdocumentRef, ftestUIDs);
						return Status.OK_STATUS;
					}
					finally {
						runningTests = false;
					}
				}
			};
			job.setPriority(Job.LONG);
			job.setUser(true);
			job.schedule();
		}
	}

	public void callProgress(int calleeNum, int totalCallees, Object returnValue, Throwable exception) {
		IProgressMonitor lprogressMonitor = progressMonitor;
		if (lprogressMonitor != null) {
			if (calleeNum == 0) {
				lprogressMonitor.beginTask("Running Archlight Tests", totalCallees);
			}
			else {
				lprogressMonitor.worked(calleeNum);
			}
			if (calleeNum == totalCallees) {
				lprogressMonitor.done();
			}
		}
	}

}
