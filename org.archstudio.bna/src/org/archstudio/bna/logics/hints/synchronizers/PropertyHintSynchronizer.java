package org.archstudio.bna.logics.hints.synchronizers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archstudio.bna.IThing;
import org.archstudio.bna.UserEditableUtils;
import org.archstudio.bna.assemblies.IAssembly;
import org.archstudio.bna.logics.hints.IHintRepository;


public class PropertyHintSynchronizer extends AbstractHintSynchronizer {

	protected final String hintSuffix;
	protected final int hintSuffixLength;
	protected final Class<? extends IThing> thingInterface;
	protected final String propertyName;
	protected final String[] userProperties;
	protected final List<String> oldHintNames = new ArrayList<String>();
	protected final Map<String, String> oldHintNamesMap = new HashMap<String, String>();

	public PropertyHintSynchronizer(String hintSuffix, Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		this.hintSuffix = hintSuffix != null ? hintSuffix : "/" + propertyName;
		this.hintSuffixLength = this.hintSuffix.length();
		this.thingInterface = thingInterface;
		this.propertyName = propertyName;
		this.userProperties = userProperties;
	}

	public PropertyHintSynchronizer(Class<? extends IThing> thingInterface, String propertyName, String... userProperties) {
		this(null, thingInterface, propertyName, userProperties);
	}

	public PropertyHintSynchronizer addOldHintName(String oldHintName, String oldHintPath) {
		this.oldHintNames.add(oldHintName);
		this.oldHintNamesMap.put(oldHintName, oldHintPath + hintSuffix);
		return this;
	}

	protected List<String> getHintNames(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		List<String> hintNames = new ArrayList<String>();
		hintNames.add(partPath + hintSuffix);
		hintNames.addAll(oldHintNames);
		return hintNames;
	}

	protected String getPartPath(String hintName) {
		if (hintName.endsWith(hintSuffix)) {
			return hintName.substring(0, hintName.length() - hintSuffixLength);
		}
		return null;
	}

	protected void filteredRestoreHints(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		for (String hintName : getHintNames(repository, context, partPath, parts, thing)) {
			Object hintValue = repository.getHint(context, hintName);
			if (hintValue != null) {
				String newHintName = oldHintNamesMap.get(hintName);
				restoreHint(repository, context, partPath, parts, thing, propertyName, newHintName != null ? newHintName : hintName, hintValue);
				break;
			}
		}
		storeHint(repository, context, partPath + hintSuffix, thing, propertyName, thing.getProperty(propertyName));
	}

	public void restoreHints(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing) {
		if (thingInterface.isInstance(thing)) {
			if (UserEditableUtils.hasAllEditableQualities(thing, userProperties)) {
				filteredRestoreHints(repository, context, partPath, parts, thing);
			}
		}
	}

	protected void filteredThingChanged(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName,
	        Object oldValue, Object newValue) {
		if (this.propertyName.equals(propertyName)) {
			storeHint(repository, context, partPath + hintSuffix, thing, propertyName, newValue);
		}
	}

	public void thingChanged(IHintRepository repository, Object context, String partPath, String[] parts, IThing thing, Object propertyName, Object oldValue,
	        Object newValue) {
		if (thingInterface.isInstance(thing)) {
			if (UserEditableUtils.isEditableForAllQualities(thing, userProperties)) {
				filteredThingChanged(repository, context, partPath, parts, thing, propertyName, oldValue, newValue);
			}
		}
	}

	protected void filteredRepositoryChanged(IHintRepository repository, Object context, IAssembly[] assemblies, String partPath, String hintName) {
		String[] parts = pathSplitPattern.split(partPath);
		for (IAssembly assembly : assemblies) {
			IThing thing = getThing(assembly, parts);
			if (thingInterface.isInstance(thing)) {
				if (UserEditableUtils.hasAllEditableQualities(thing, userProperties)) {
					for (String hn : getHintNames(repository, context, partPath, parts, thing)) {
						Object hintValue = repository.getHint(context, hn);
						if (hintValue != null) {
							String newHintName = oldHintNamesMap.get(hn);
							restoreHint(repository, context, partPath, parts, thing, propertyName, newHintName != null ? newHintName : hn, hintValue);
							break;
						}
					}
				}
			}
		}
	}

	public void repositoryChanged(IHintRepository repository, Object context, IAssembly[] assemblies, String hintName) {
		String newHintName = oldHintNamesMap.get(hintName);
		if (newHintName != null) {
			hintName = newHintName;
		}
		if (hintName != null) {
			String partPath = getPartPath(hintName);
			if (partPath != null) {
				filteredRepositoryChanged(repository, context, assemblies, partPath, hintName);
			}
		}
	}
}
