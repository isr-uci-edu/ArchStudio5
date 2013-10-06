package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;

public interface IBNADragAndDropListener {

	public void dragEnter(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location);

	public void drag(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location);

	public void dragExit(IBNAView view, DNDType type);

	public void drop(IBNAView view, DNDType type, DNDData data, List<IThing> things, ICoordinate location);

}