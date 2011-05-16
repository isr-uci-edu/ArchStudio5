package org.archstudio.bna.utils;

import org.archstudio.bna.IThing;

public interface IModelAwareThing extends IThing {

	public void adding();

	public void removing();
}
