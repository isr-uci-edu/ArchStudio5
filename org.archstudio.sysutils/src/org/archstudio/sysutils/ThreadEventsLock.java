package org.archstudio.sysutils;

import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadEventsLock<T>
	extends ReentrantLock{

	static class ThreadEvent<T>{

		Thread thread;
		T event;

		public ThreadEvent(Thread thread, T event){
			this.thread = thread;
			this.event = event;
		}

		@Override
		public String toString(){
			return thread.toString();
		}
	}

	public ThreadEventsLock(){
	}

	Object writeReplace() throws ObjectStreamException{
		throw new NotSerializableException();
	}

	@SuppressWarnings("unchecked")
	private final ListenerList<IEventListener<T>> eventListeners = new ListenerList(IEventListener.class);

	public void addEventListener(IEventListener<T> listener){
		eventListeners.add(listener);
	}

	public void removeEventListener(IEventListener<T> listener){
		eventListeners.remove(listener);
	}

	private final Queue<ThreadEvent<T>> pendingThreadEvents = new LinkedList<ThreadEvent<T>>();

	public void enqueueEvent(T event){
		assert isHeldByCurrentThread();
		synchronized(pendingThreadEvents){
			pendingThreadEvents.add(new ThreadEvent<T>(Thread.currentThread(), event));
		}
	}

	private volatile Thread processingEventThread = null;

	@Override
	public void unlock(){
		try{
			if(getHoldCount() > 1){
				super.unlock();
				return;
			}
			super.unlock();
			final Thread currentThread = Thread.currentThread();

			if(processingEventThread != currentThread){
				while(true){
					ThreadEvent<T> te;
					synchronized(pendingThreadEvents){
						te = pendingThreadEvents.peek();
						if(te == null){
							break;
						}
						if(te.thread != currentThread){
							try{
								pendingThreadEvents.wait(0);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							continue;
						}
					}
					processingEventThread = currentThread;
					while(true){
						for(IEventListener<T> l: eventListeners.getListeners()){
							try{
								l.handleEvent(te.event);
							}
							catch(Throwable t){
								t.printStackTrace();
							}
						}
						synchronized(pendingThreadEvents){
							pendingThreadEvents.poll();
							te = pendingThreadEvents.peek();
							if(te != null && te.thread == currentThread){
								continue;
							}
							else{
								processingEventThread = null;
								pendingThreadEvents.notifyAll();
								break;
							}
						}
					}
				}
			}
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
}