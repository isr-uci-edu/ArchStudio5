package org.archstudio.archlight.core;

import java.util.Collection;

import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Myx brick: "Myx Impl"
 * 
 * @see org.archstudio.archlight.core.ArchlightToolAggregatorMyxComponentStub
 * @generated
 */
public class ArchlightToolAggregatorMyxComponent extends
		org.archstudio.archlight.core.ArchlightToolAggregatorMyxComponentStub {

	public static final String TOOL_ID = "Aggregator";

	@Override
	public String getToolID() {
		return TOOL_ID;
	}

	@Override
	public void reloadTests() {
		if (tools != null) {
			tools.reloadTests();
		}
	}

	boolean runningTests = false;
	protected IProgressMonitor progressMonitor = null;

	@Override
	public void runTests(ObjRef documentRef, Collection<String> testUIDs) {
		if (runningTests) {
			return;
		}

		runningTests = true;
		final ObjRef fdocumentRef = documentRef;
		final Collection<String> ftestUIDs = testUIDs;
		if (tools != null) {
			Job job = new Job("Running Archlight Tests") {

				@Override
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

	@Override
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