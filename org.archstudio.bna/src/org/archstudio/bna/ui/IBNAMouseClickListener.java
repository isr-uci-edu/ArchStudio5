package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.eclipse.swt.events.MouseEvent;

/**
 * @deprecated Use {@link IBNAMouseClickListener2}.
 */
@Deprecated
public interface IBNAMouseClickListener {

	public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location);

}