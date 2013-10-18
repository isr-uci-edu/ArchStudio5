package org.archstudio.bna.logics.information;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

public class ProgressLogic extends AbstractThingLogic {

	class ProgressLogicJob extends Job {

		IRunnableWithProgress runnable;

		public ProgressLogicJob(String name, IRunnableWithProgress runnable) {
			super(name);
			this.runnable = runnable;
			setUser(false);
			setSystem(false);
			setPriority(Job.SHORT);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			try {
				monitor.beginTask(getName(), 1);
				runnable.run(monitor);
				return Status.OK_STATUS;
			}
			catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
				return new Status(IStatus.ERROR, "org.archstudio.bna", "Failed to execute: " + getName(), e);
			}
			finally {
				tasks.decrementAndGet();
				synchronized (tasks) {
					tasks.notifyAll();
				}
			}
		}
	}

	static ExecutorService asyncExecutor = Executors.newCachedThreadPool(new ThreadFactory() {

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.setName(ProgressLogic.class.getName());
			return t;
		}
	});

	private final AtomicInteger tasks = new AtomicInteger();

	public ProgressLogic(IBNAWorld world) {
		super(world);
	}

	synchronized public void run(final String description, final IRunnableWithProgress runnable) {
		tasks.incrementAndGet();
		if (Platform.isRunning() && PlatformUI.isWorkbenchRunning()) {
			new ProgressLogicJob(description, runnable).schedule();
		}
		else {
			asyncExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						runnable.run(null);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						tasks.decrementAndGet();
						synchronized (tasks) {
							tasks.notifyAll();
						}
					}
				}
			});
		}
	}

	public void waitForCompletion() {
		if (Platform.isRunning()) {
			try {
				Job.getJobManager().join(ProgressLogic.class, null);
			}
			catch (OperationCanceledException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			synchronized (tasks) {
				while (tasks.get() > 0) {
					try {
						tasks.wait();
					}
					catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
