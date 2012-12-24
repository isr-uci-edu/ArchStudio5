package org.archstudio.myx.fw.eclipse;

import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitor;

public class EclipseProgessMonitor implements IProgressMonitor {

	private final IMyxProgressMonitor monitor;

	public EclipseProgessMonitor(IMyxProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void beginTask(String name, int totalWork) {
		monitor.beginTask(name, totalWork);
	}

	@Override
	public void done() {
		monitor.done();
	}

	@Override
	public void internalWorked(double work) {
		monitor.internalWorked(work);
	}

	@Override
	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	@Override
	public void setCanceled(boolean value) {
		monitor.setCanceled(value);
	}

	@Override
	public void setTaskName(String name) {
		monitor.setTaskName(name);
	}

	@Override
	public void subTask(String name) {
		monitor.subTask(name);
	}

	@Override
	public void worked(int work) {
		monitor.worked(work);
	}

}
