package org.archstudio.dblgen.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

public class Xadl3SchemaLocation {

	public static final String EXTENSION_POINT_ID = "org.archstudio.dblgen.schemalocation";

	public static enum UpdateFrequency {

		NEVER("never", -1), //
		EVERY_BUILD("everyBuild", 0), //
		HOURLY("hourly", 60 * 60 * 1000), //
		DAILY("daily", 60 * 60 * 24 * 1000), //
		WEEKLY("weekly", 60 * 60 * 24 * 7 * 1000), //
		MONTHLY("monthly", 60 * 60 * 24 * 30 * 1000);

		private final String stringRepresentation;
		private final long numMilliseconds;

		private UpdateFrequency(String stringRepresentation, long numMilliseconds) {
			this.stringRepresentation = stringRepresentation;
			this.numMilliseconds = numMilliseconds;
		}

		public String getStringRepresentation() {
			return stringRepresentation;
		}

		public long getNumMilliseconds() {
			return numMilliseconds;
		}

		public static UpdateFrequency fromString(String stringRepresentation) {
			for (UpdateFrequency f : UpdateFrequency.values()) {
				if (f.getStringRepresentation().equals(stringRepresentation)) {
					return f;
				}
			}
			return null;
		}

		public String toString() {
			return getStringRepresentation();
		}
	}

	public static List<Xadl3SchemaLocation> parse(IExtensions extensions) {
		List<Xadl3SchemaLocation> locations = new ArrayList<Xadl3SchemaLocation>();
		if (extensions != null) {
			for (IPluginExtension pluginExtension : extensions.getExtensions()) {
				if (pluginExtension.getPoint() != null && pluginExtension.getPoint().equals(EXTENSION_POINT_ID)) {
					IPluginObject[] pluginObjects = pluginExtension.getChildren();
					if (pluginObjects != null && pluginObjects.length > 0) {
						for (IPluginElement pluginElement : Iterables.filter(Arrays.asList(pluginObjects),
								IPluginElement.class)) {
							locations.add(new Xadl3SchemaLocation(pluginElement));
						}
					}
				}
			}
		}
		return locations;
	}

	public static List<Xadl3SchemaLocation> parse(IProject project) {
		IPluginModelBase workspacePluginModelBase = PluginRegistry.findModel(project);
		if (workspacePluginModelBase != null) {
			return parse(workspacePluginModelBase.getExtensions());
		}
		return Collections.emptyList();
	}

	protected final URI url;
	protected final String name;
	protected final UpdateFrequency autoUpdateFrequency;
	protected final boolean copyLocally;

	public Xadl3SchemaLocation(URI url, String name, UpdateFrequency autoUpdateFrequency, boolean copyLocally) {
		this.url = url;
		this.name = name;
		this.autoUpdateFrequency = autoUpdateFrequency;
		this.copyLocally = copyLocally;
	}

	public Xadl3SchemaLocation(IPluginElement pluginElement) {
		url = URI.createURI(Preconditions.checkNotNull(pluginElement.getAttribute("url").getValue()));
		name = Preconditions.checkNotNull(pluginElement.getAttribute("name").getValue());
		autoUpdateFrequency = UpdateFrequency.fromString(pluginElement.getAttribute("autoUpdateFrequency").getValue());
		copyLocally = Boolean.valueOf(pluginElement.getAttribute("copyLocally").getValue());
	}

	public URI getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public UpdateFrequency getAutoUpdateFrequency() {
		return autoUpdateFrequency;
	}

	public boolean isCopyLocally() {
		return copyLocally;
	}
}
