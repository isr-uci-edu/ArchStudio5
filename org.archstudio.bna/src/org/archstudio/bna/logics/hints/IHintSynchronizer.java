package org.archstudio.bna.logics.hints;

import java.util.Collection;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.hints.IHintRepository.HintValue;

public interface IHintSynchronizer {

	public Collection<IThingKey<?>> getThingPropertiesOfInterest();

	public Collection<String> getRepositoryNamesOfInterest();

	public void restoreHints(IHintRepository repository, Object context, IThing thing, String hintName,
			HintValue hintValue);

	public void storeHints(IHintRepository repository, Object context, IThing thing, IThingKey<?> key);

}
