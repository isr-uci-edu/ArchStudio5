package org.archstudio.xarchadt.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class AbstractProxy {

	static final class NameContext {
		String name;

		public NameContext(String name) {
			String suffix;
			if (name.endsWith(suffix = "_")) {
				name = name.substring(0, name.length() - suffix.length());
			}
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
			result = prime * result + (name == null ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			NameContext other = (NameContext) obj;
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			}
			else if (!name.equals(other.name)) {
				return false;
			}
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
			 * Note: an IllegalArgumentException with the message of 'object is not an instance of declaring class' may
			 * occur if the methods map does not create a specialized handler for the method being called.
			 */
			return method.invoke(proxy, args);
		}
	}

	private static final LoadingCache<Class<? extends Handler<?, ?>>, LoadingCache<Object, Handler<?, ?>>> handlerCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends Handler<?, ?>>, LoadingCache<Object, Handler<?, ?>>>() {

				@Override
				public LoadingCache<Object, Handler<?, ?>> load(final Class<? extends Handler<?, ?>> handlerClass)
						throws Exception {
					return CacheBuilder.newBuilder().build(new CacheLoader<Object, Handler<?, ?>>() {

						@Override
						public AbstractProxy.Handler<?, ?> load(Object context) throws Exception {
							if (context == VOID_CONTEXT) {
								return handlerClass.getConstructor().newInstance();
							}
							return handlerClass.getConstructor(context.getClass()).newInstance(context);
						};
					});
				};
			});

	@SuppressWarnings("unchecked")
	protected static final <H extends Handler<C, P>, C, P extends InvocationHandler> H getHandler(
			Class<H> handlerClass, C context) {
		return (H) handlerCache.getUnchecked(handlerClass).getUnchecked(context);
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

	@SuppressWarnings("unchecked")
	protected static <T> T unproxy(Object o) {
		if (o instanceof EObject) {
			return (T) XArchADTProxy.unproxy((EObject) o);
		}
		return (T) o;
	}

	@SuppressWarnings("unchecked")
	protected static <T> T proxy(IXArchADT xarch, Object o) {
		if (o instanceof ObjRef) {
			return (T) XArchADTProxy.proxy(xarch, (ObjRef) o);
		}
		return (T) o;
	}

}
