package org.archstudio.myx.fw.eclipse;

import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitor;

public class EclipseProgessMonitor implements IProgressMonitor {

	private final IMyxProgressMonitor monitor;
	
	public EclipseProgessMonitor(IMyxProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public void beginTask(String name, int totalWork) {
		monitor.beginTask(name, totalWork);
	}

	public void done() {
		monitor.done();
	}

	public void internalWorked(double work) {
		monitor.internalWorked(work);
	}

	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	public void setCanceled(boolean value) {
		monitor.setCanceled(value);
	}

	public void setTaskName(String name) {
		monitor.setTaskName(name);
	}

	public void subTask(String name) {
		monitor.subTask(name);
	}

	public void worked(int work) {
		monitor.worked(work);
	}

}
