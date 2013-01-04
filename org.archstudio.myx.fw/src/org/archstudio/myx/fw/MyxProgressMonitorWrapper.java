/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others. All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.archstudio.myx.fw;

/**
 * An abstract wrapper around a progress monitor which, unless overridden, forwards <code>IMyxProgressMonitor</code> and
 * <code>IMyxProgressMonitorWithBlocking</code> methods to the wrapped progress monitor.
 * <p>
 * This class can be used without OSGi running.
 * </p>
 * <p>
 * Clients may subclass.
 * </p>
 */
public abstract class MyxProgressMonitorWrapper implements IMyxProgressMonitor {

	/** The wrapped progress monitor. */
	private IMyxProgressMonitor progressMonitor;

	/**
	 * Creates a new wrapper around the given monitor.
	 * 
	 * @param monitor
	 *            the progress monitor to forward to
	 */
	protected MyxProgressMonitorWrapper(IMyxProgressMonitor monitor) {
		progressMonitor = monitor;
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#beginTask(String, int)
	 */
	@Override
	public void beginTask(String name, int totalWork) {
		progressMonitor.beginTask(name, totalWork);
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#done()
	 */
	@Override
	public void done() {
		progressMonitor.done();
	}

	/**
	 * Returns the wrapped progress monitor.
	 * 
	 * @return the wrapped progress monitor
	 */
	public IMyxProgressMonitor getWrappedProgressMonitor() {
		return progressMonitor;
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#internalWorked(double)
	 */
	@Override
	public void internalWorked(double work) {
		progressMonitor.internalWorked(work);
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#isCanceled()
	 */
	@Override
	public boolean isCanceled() {
		return progressMonitor.isCanceled();
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#setCanceled(boolean)
	 */
	@Override
	public void setCanceled(boolean b) {
		progressMonitor.setCanceled(b);
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#setTaskName(String)
	 */
	@Override
	public void setTaskName(String name) {
		progressMonitor.setTaskName(name);
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#subTask(String)
	 */
	@Override
	public void subTask(String name) {
		progressMonitor.subTask(name);
	}

	/**
	 * This implementation of a <code>IMyxProgressMonitor</code> method forwards to the wrapped progress monitor.
	 * Clients may override this method to do additional processing.
	 * 
	 * @see IMyxProgressMonitor#worked(int)
	 */
	@Override
	public void worked(int work) {
		progressMonitor.worked(work);
	}
}
