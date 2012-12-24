package org.archstudio.bna.logics;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.CoordinateMapperEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.ICoordinateMapperListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class DebugLogic extends AbstractThingLogic implements IBNAModelListener, IBNAMouseListener,
		IBNAMouseClickListener, ICoordinateMapperListener {

	public DebugLogic() {
	}

	@Override
	protected void init() {
		super.init();
		System.err.println("DL: Initialized");
	}

	@Override
	protected void destroy() {
		System.err.println("DL: Destroyed");
		super.destroy();
	}

	int modelEventCount = 0;

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		modelEventCount++;
		System.err.println("DL: bnaModelChangedSync " + modelEventCount + " " + evt);
	}

	@Override
	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		System.err.println("DL: coordinateMappingsChanged " + evt);
	}

	int mouseEventCount = 0;

	@Override
	public void mouseClick(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		mouseEventCount++;
		System.err.println("DL: mouseClick " + mouseEventCount + " " + evt);
		ICoordinateMapper cm = view.getCoordinateMapper();
		Point w = cm.localToWorld(new Point(evt.x, evt.y));
		System.err.println("DL:  - local mouse: (" + evt.x + "," + evt.y + ")");
		System.err.println("DL:  - world mouse: (" + w.x + "," + w.y + ")");

	}

	@Override
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		mouseEventCount++;
		System.err.println("DL: mouseDoubleClick " + mouseEventCount + " " + evt);
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		mouseEventCount++;
		System.err.println("DL: mouseUp " + mouseEventCount + " " + evt);
	}

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		mouseEventCount++;
		System.err.println("DL: mouseDown " + mouseEventCount + " " + evt);
	}
}
