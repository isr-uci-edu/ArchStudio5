package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;

public interface IThingKeyKey<N, K extends IThingKey<?>, V> extends IThingKey<V> {

	N getName();

	K getKey();
}