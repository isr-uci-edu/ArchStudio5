package org.archstudio.bna.logics.hints.synchronizers;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.hints.IHintSynchronizer;

public abstract class AbstractHintSynchronizer implements IHintSynchronizer {

	protected IBNAWorld world;

	public AbstractHintSynchronizer() {
	}

	@Override
	public void setBNAWorld(IBNAWorld world) {
		this.world = world;
	}

	//	protected final IThing getThing(IAssembly assembly, ThingKey<IThing>[] parts) {
	//		for (int i = 0; i < parts.length; i++) {
	//			if (assembly == null) {
	//				break;
	//			}
	//			IThing thing = assembly.getPart(parts[i]);
	//			if (i == parts.length - 1) {
	//				return thing;
	//			}
	//			assembly = AssemblyUtils.getAssemblyWithRoot(thing);
	//		}
	//		return null;
	//	}
	//
	//	protected final Object getFirstHint(IHintRepository repository, Object context, List<String> hintNames) {
	//		for (String hintName : hintNames) {
	//			Object hintValue = repository.getHint(context, hintName);
	//			if (hintValue != null) {
	//				return hintValue;
	//			}
	//		}
	//		return null;
	//	}
	//
	//	protected <T> void restoreHint(IHintRepository repository, Object context, String partPath, String[] parts,
	//			IThing thing, ThingKey<T> propertyName, String hintName, T hintValue) {
	//		if (hintValue != null) {
	//			thing.setProperty(propertyName, hintValue);
	//		}
	//	}
	//
	//	protected <T> void storeHint(IHintRepository repository, Object context, String hintName, IThing thing,
	//			ThingKey<T> propertyName, T value) {
	//		if (value != null) {
	//			repository.storeHint(context, hintName, value);
	//		}
	//	}
}
