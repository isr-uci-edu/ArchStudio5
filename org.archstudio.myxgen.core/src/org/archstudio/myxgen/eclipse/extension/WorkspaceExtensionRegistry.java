package org.archstudio.myxgen.eclipse.extension;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PDEExtensionRegistry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * An interim wrapper for {@link PDEExtensionRegistry} until something better comes along... Methods are implemented as
 * needed...
 */
@SuppressWarnings("restriction")
public class WorkspaceExtensionRegistry implements IExtensionRegistry {

	PDEExtensionRegistry e = PDECore.getDefault().getExtensionsRegistry();
	List<IRegistryEventListener> listeners = new CopyOnWriteArrayList<IRegistryEventListener>();
	Multimap<String, IRegistryEventListener> extensionIdListeners = HashMultimap.create();

	public WorkspaceExtensionRegistry() {

		// necessary to initialize the registry
		e.findExtensionPointPlugin("org.eclipse.pde.core.source");

		e.addListener(new IRegistryChangeListener() {

			@Override
			public void registryChanged(IRegistryChangeEvent event) {
				synchronized (extensionIdListeners) {
					for (IExtensionDelta d : event.getExtensionDeltas()) {
						switch (d.getKind()) {
						case IExtensionDelta.ADDED:
							for (IRegistryEventListener listener : extensionIdListeners.get(null)) {
								listener.added(new IExtensionPoint[] { d.getExtensionPoint() });
							}
							for (IRegistryEventListener listener : extensionIdListeners.get(d.getExtensionPoint()
									.getUniqueIdentifier())) {
								listener.added(new IExtensionPoint[] { d.getExtensionPoint() });
							}
							break;
						case IExtensionDelta.REMOVED:
							for (IRegistryEventListener listener : extensionIdListeners.get(null)) {
								listener.removed(new IExtensionPoint[] { d.getExtensionPoint() });
							}
							for (IRegistryEventListener listener : extensionIdListeners.get(d.getExtensionPoint()
									.getUniqueIdentifier())) {
								listener.removed(new IExtensionPoint[] { d.getExtensionPoint() });
							}
							break;
						}
					}
				}
			}
		});
	}

	@Override
	public void addRegistryChangeListener(IRegistryChangeListener listener, String namespace) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addRegistryChangeListener(IRegistryChangeListener listener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IConfigurationElement[] getConfigurationElementsFor(String extensionPointId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IConfigurationElement[] getConfigurationElementsFor(String namespace, String extensionPointName) {
		String extensionPointId = namespace + "." + extensionPointName;
		List<IConfigurationElement> configurationElements = Lists.newArrayList();
		configurationElements.addAll(Arrays.asList(PDEUtils.getExtensionPoint(extensionPointId,
				e.findExtensionPlugins(extensionPointId, true)).getConfigurationElements()));
		return configurationElements.toArray(new IConfigurationElement[configurationElements.size()]);
	}

	@Override
	public IConfigurationElement[] getConfigurationElementsFor(String namespace, String extensionPointName,
			String extensionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtension getExtension(String extensionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtension getExtension(String extensionPointId, String extensionId) {
		for (IExtension d : e.findExtensions(extensionPointId, true)) {
			for (IConfigurationElement c : d.getConfigurationElements()) {
				if (extensionId.equals(c.getAttribute("id"))) {
					return d;
				}
			}
		}
		return null;
	}

	@Override
	public IExtension getExtension(String namespace, String extensionPointName, String extensionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtensionPoint getExtensionPoint(String extensionPointId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtensionPoint getExtensionPoint(String namespace, String extensionPointName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtensionPoint[] getExtensionPoints() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtensionPoint[] getExtensionPoints(String namespace) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtensionPoint[] getExtensionPoints(IContributor contributor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtension[] getExtensions(String namespace) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IExtension[] getExtensions(IContributor contributor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getNamespaces() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeRegistryChangeListener(IRegistryChangeListener listener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addContribution(InputStream is, IContributor contributor, boolean persist, String name,
			ResourceBundle translationBundle, Object token) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeExtension(IExtension extension, Object token) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeExtensionPoint(IExtensionPoint extensionPoint, Object token) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop(Object token) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(IRegistryEventListener listener) {
		synchronized (extensionIdListeners) {
			extensionIdListeners.put(null, listener);
		}
	}

	@Override
	public void addListener(IRegistryEventListener listener, String extensionPointId) {
		synchronized (extensionIdListeners) {
			extensionIdListeners.put(extensionPointId, listener);
		}
	}

	@Override
	public void removeListener(IRegistryEventListener listener) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isMultiLanguage() {
		throw new UnsupportedOperationException();
	}
}
