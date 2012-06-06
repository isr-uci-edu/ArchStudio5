package org.archstudio.bna.graphs;

import java.util.Random;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.graphs.GraphCoordinateMapper.Type;
import org.archstudio.bna.graphs.things.DataPointThing;
import org.archstudio.bna.logics.DebugLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BNAGraphDemo {

	static Random random = new Random();

	public static void main(String args[]) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final IBNAModel bnaModel = new DefaultBNAModel();
		final IBNAWorld bnaWorld = new DefaultBNAWorld("bna", bnaModel);
		final IBNAView bnaView = new DefaultBNAView(null, bnaWorld, new GraphCoordinateMapper(Type.LOGARITHMIC, Type.LINEAR));

		// setup graph
		RectangleGlassThing graphGlassThing = GraphAssemblies.createGraph(bnaWorld, null, "Top", "Bottom", "Left", "Right");
		graphGlassThing.setBoundingBox(new Rectangle(0, -100, 150, 100));
		UserEditableUtils.addEditableQualities(graphGlassThing, IRelativeMovable.USER_MAY_MOVE,
				IHasMutableSize.USER_MAY_RESIZE, IHasMutableSelected.USER_MAY_SELECT);

		// add some points to the plot
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(graphGlassThing.getBoundingBox().width);
			int y = random.nextInt(graphGlassThing.getBoundingBox().height);
			DataPointThing point = bnaModel.addThing(new DataPointThing(null));
			point.setAnchorPoint(new Point(x, -y));
		}

		IThingLogicManager tlm = bnaWorld.getThingLogicManager();
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
		tlm.addThingLogic(ReshapeRectangleLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(MousePanAndZoomLogic.class);
		//tlm.addThingLogic(DebugLogic.class);

		final BNACanvas bnaComposite = new BNACanvas(shell, SWT.V_SCROLL | SWT.H_SCROLL, bnaView);
		BNARenderingSettings.setAntialiasGraphics(bnaComposite, true);
		BNARenderingSettings.setAntialiasText(bnaComposite, true);
		BNARenderingSettings.setDecorativeGraphics(bnaComposite, true);

		bnaComposite.setSize(500, 500);
		bnaComposite.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);
	}
}
