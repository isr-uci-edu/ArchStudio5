package org.archstudio.xarchadt.core.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class AbstractProxy {

	static final class NameContext {
		String name;

		public NameContext(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "NamedContext[name=" + name + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NameContext other = (NameContext) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			}
			else if (!name.equals(other.name))
				return false;
			return true;
		}
	}

	protected static abstract class Handler<Context, Proxy extends InvocationHandler> {

		final Context context;

		public Handler() {
			this(null);
		}

		public Handler(Context context) {
			this.context = context;
		}

		public abstract Object invoke(Proxy proxy, Method method, Object[] args) throws Throwable;
	}

	private static final class DefaultHandler extends Handler<Void, InvocationHandler> {

		public DefaultHandler() {
		}

		@Override
		public Object invoke(InvocationHandler proxy, Method method, Object[] args) throws Throwable {
			/*
			 * This is a last resort handler to call the basic Object methods on the actual proxy object itself, e.g.:
			 * #toString(), #equals(Object), and #hashCode()
			 * 
			 * Note: an IllegalArgumentException with the message of 'object is not an instance of declaring class'
			 * may occur if the methods map does not crate a specialized handler for the method being called.
			 */
			return method.invoke(proxy, args);
		}
	}

	private static final ConcurrentMap<Class<? extends Handler<?, ?>>, Map<Object, Handler<?, ?>>> autoHandler = new MapMaker()
			.makeComputingMap(new Function<Class<? extends Handler<?, ?>>, Map<Object, Handler<?, ?>>>() {
				@Override
				public Map<Object, Handler<?, ?>> apply(final Class<? extends Handler<?, ?>> handlerClass) {
					return new MapMaker().makeComputingMap(new Function<Object, Handler<?, ?>>() {
						@Override
						public Handler<?, ?> apply(Object context) {
							try {
								if (context == VOID_CONTEXT) {
									return handlerClass.getConstructor().newInstance();
								}
								return handlerClass.getConstructor(context.getClass()).newInstance(context);
							}
							catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
					});
				}
			});

	@SuppressWarnings("unchecked")
	protected static final <H extends Handler<C, P>, C, P extends InvocationHandler> H getHandler(
			Class<H> handlerClass, C context) {
		return (H) autoHandler.get(handlerClass).get(context);
	}

	private static final Object VOID_CONTEXT = new Object();

	@SuppressWarnings("unchecked")
	protected static final <H extends Handler<C, P>, C, P extends InvocationHandler> H getHandler(Class<H> handlerClass) {
		return getHandler(handlerClass, (C) VOID_CONTEXT);
	}

	private static final DefaultHandler DEFAULT_HANDLER = new DefaultHandler();

	@SuppressWarnings("unchecked")
	protected static final <H extends Handler<?, ?>> H getDefaultHandler() {
		return (H) DEFAULT_HANDLER;
	}

}
