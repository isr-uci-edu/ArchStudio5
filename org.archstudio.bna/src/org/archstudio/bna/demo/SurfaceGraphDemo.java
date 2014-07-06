package org.archstudio.bna.demo;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.EditTextLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.events.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.PanAndZoomLogic;
import org.archstudio.bna.things.graphs.NumericAxis;
import org.archstudio.bna.things.graphs.NumericSurfaceGraphThing;
import org.archstudio.bna.things.graphs.NumericSurfaceGraphThing.Data;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.utils.AbstractCoordinateMapper;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.LinearCoordinateMapper;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SurfaceGraphDemo {

	static Rectangle originRectangle = AbstractCoordinateMapper.getDefaultBounds();
	static Point origin = new Point(originRectangle.x + originRectangle.width / 2, originRectangle.y
			+ originRectangle.height / 2);

	public static void main(String args[]) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		IBNAModel model = new DefaultBNAModel();
		IBNAWorld world = new DefaultBNAWorld("bna", model);
		IBNAView view = new DefaultBNAView(null, world, new LinearCoordinateMapper());

		GridThing.createIn(world);
		ShadowThing.createIn(world);

		setupTopWorld(world);
		populateModel(world);

		IBNAModel iModel = new DefaultBNAModel();
		IBNAWorld iWorld = new DefaultBNAWorld("subworld", iModel);

		setupWorld(iWorld);
		populateModel(iWorld);

		final BNACanvas bnaComposite = new BNACanvas(shell, SWT.H_SCROLL | SWT.V_SCROLL, view);
		BNARenderingSettings.setAntialiasGraphics(bnaComposite, true);
		BNARenderingSettings.setAntialiasText(bnaComposite, true);
		BNARenderingSettings.setDecorativeGraphics(bnaComposite, true);
		BNARenderingSettings.setDisplayShadows(bnaComposite, true);

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

	static void setupTopWorld(IBNAWorld world) {
		IThingLogicManager logicManager = world.getThingLogicManager();
		logicManager.addThingLogic(PanAndZoomLogic.class);
		setupWorld(world);
	}

	static void setupWorld(IBNAWorld world) {
		IThingLogicManager logicManager = world.getThingLogicManager();

		logicManager.addThingLogic(ClickSelectionLogic.class);
		logicManager.addThingLogic(DragMovableLogic.class);
		logicManager.addThingLogic(EditTextLogic.class);
		logicManager.addThingLogic(KeyNudgerLogic.class);
		logicManager.addThingLogic(MarqueeSelectionLogic.class);
		logicManager.addThingLogic(PanAndZoomLogic.class);
		logicManager.addThingLogic(ReshapeRectangleLogic.class);
		logicManager.addThingLogic(ReshapeSplineLogic.class);
		logicManager.addThingLogic(RotatingOffsetLogic.class);
		logicManager.addThingLogic(StandardCursorLogic.class);
		logicManager.addThingLogic(ToolTipLogic.class);

		logicManager.addThingLogic(WorldThingExternalEventsLogic.class);
		logicManager.addThingLogic(WorldThingInternalEventsLogic.class);
	}

	static void populateModel(IBNAWorld world) {
		final IBNAModel model = world.getBNAModel();

		NumericSurfaceGraphThing surface = model.addThing(new NumericSurfaceGraphThing(null));
		Data data = new Data();
		data.resize(50, 50);
		for (int x = 0; x < data.getWidth(); x++) {
			for (int y = 0; y < data.getHeight(); y++) {
				data.setValue(x, y, 7 * Math.sin((double) x / 8 * y / 8));
				int r = (int) (128 + Math.sin(x) * 127);
				int g = (int) (128 + Math.sin(y) * 127);
				int b = (int) (128 + Math.sin(x * y) * 127);
				data.setColor(x, y, new RGB(r, g, b));
			}
		}
		surface.setData(data);
		surface.setBoundingBox(new Rectangle(0, 0, 800, 800));
		NumericAxis zAxis = surface.getZMajorAxis();
		zAxis.unit = 5;
		surface.setZMajorAxis(zAxis);

		UserEditableUtils.addEditableQualities(surface, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE);
	}
}
