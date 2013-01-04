package org.archstudio.myx.fw.equinox;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.myx.fw.IMyxBrickLoader;
import org.archstudio.myx.fw.MyxBasicRuntime;
import org.archstudio.myx.fw.MyxBrickLoaderException;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.osgi.framework.Bundle;

import com.google.common.base.Functions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * A {@link MyxBasicRuntime} that automatically loads all brick loaders defined by the brickLoader extension points.
 */
public class MyxEquinoxRuntime extends MyxBasicRuntime {

	private static final List<Class<IMyxBrickLoader>> brickLoaderClasses = Lists.newArrayList();
	static {
		try {
			// load the brick loader extension points
			Map<String, Class<IMyxBrickLoader>> brickLoaderNodes = Maps.newHashMap();
			Multimap<String, String> brickLoaderEdges = ArrayListMultimap.create();
			for (IConfigurationElement brickLoaderConfigurationElement : RegistryFactory.getRegistry()
					.getConfigurationElementsFor("org.archstudio.myx.fw.equinox", "brickLoader")) {
				String bundleName = brickLoaderConfigurationElement.getContributor().getName();
				Bundle bundle = Platform.getBundle(bundleName);
				String id = brickLoaderConfigurationElement.getAttribute("id");
				String brickLoaderClassName = brickLoaderConfigurationElement.getAttribute("class");
				@SuppressWarnings("unchecked")
				Class<IMyxBrickLoader> brickLoaderClass = (Class<IMyxBrickLoader>) bundle
						.loadClass(brickLoaderClassName);
				brickLoaderNodes.put(id, brickLoaderClass);

				for (IConfigurationElement c : brickLoaderConfigurationElement.getChildren("Override")) {
					String overrideId = c.getAttribute("brickLoader");
					brickLoaderEdges.put(id, overrideId);
				}
			}

			// sort topologically (using algorithm from wikipedia)
			List<String> L = Lists.newArrayList();
			Set<String> S = Sets.newHashSet(brickLoaderNodes.keySet());
			S.removeAll(brickLoaderEdges.values());
			while (!S.isEmpty()) {
				String n = S.iterator().next();
				S.remove(n);
				L.add(n);
				for (Iterator<String> e = brickLoaderEdges.get(n).iterator(); e.hasNext();) {
					String m = e.next();
					e.remove();
					if (!brickLoaderEdges.containsValue(m)) {
						S.add(m);
					}
				}
			}
			if (!brickLoaderEdges.isEmpty()) {
				throw new IllegalArgumentException("brick loaders contain overrides cycle");
			}
			brickLoaderClasses.addAll(Collections2.transform(L, Functions.forMap(brickLoaderNodes)));
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to initialize MyxEquinoxRuntime", e);
		}
	}

	public MyxEquinoxRuntime() {
		for (Class<IMyxBrickLoader> brickLoaderClass : brickLoaderClasses) {
			try {
				addBrickLoader(MyxUtils.createName(brickLoaderClass.getName()), brickLoaderClass, null);
			}
			catch (MyxBrickLoaderException mble) {
				throw new RuntimeException("This shouldn't happen.", mble);
			}
		}
	}

}
