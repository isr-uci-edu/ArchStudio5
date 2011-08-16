package org.archstudio.bna.demo;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.MirrorAnchorPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BasicBNADemo {

	public static void main(String args[]) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final IBNAModel m = new DefaultBNAModel();

		IBNAWorld bnaWorld1 = new DefaultBNAWorld("bna", m);
		setupTopWorld(bnaWorld1);
		populateModel(bnaWorld1);

		IBNAView bnaView1 = new DefaultBNAView(null, null, bnaWorld1, new LinearCoordinateMapper());

		IBNAModel m2 = new DefaultBNAModel();
		IBNAWorld bnaWorld2 = new DefaultBNAWorld("subworld", m2);
		setupWorld(bnaWorld2);
		populateModel(bnaWorld2);

		//IBNAView bnaView2 = new DefaultBNAView(bnaView1, bnaWorld2, new DefaultCoordinateMapper());
		//ViewThing wt2 = new ViewThing();
		//wt2.setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20,
		//		(DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20, 200, 200);
		//wt2.setView(bnaView2);
		//m.addThing(wt2);
		//IBNAView bnaView3 = new DefaultBNAView(bnaView1, bnaWorld2, new DefaultCoordinateMapper());
		//ViewThing wt3 = new ViewThing();
		//wt3.setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 200,
		//		(DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20, 200, 200);
		//wt3.setView(bnaView3);
		//m.addThing(wt3);

		// populateWithViews(m, bnaView1, bnaWorld2);

		final BNACanvas bnaComposite = new BNACanvas(shell, SWT.V_SCROLL | SWT.H_SCROLL | SWT.DOUBLE_BUFFERED,
				bnaWorld1);
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

	static void setupTopWorld(IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();
		logicManager.addThingLogic(new MousePanAndZoomLogic());
		setupWorld(bnaWorld);
	}

	static void setupWorld(IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		logicManager.addThingLogic(new MoveWithLogic());
		logicManager.addThingLogic(new MirrorAnchorPointLogic());
		logicManager.addThingLogic(new MirrorBoundingBoxLogic());
		logicManager.addThingLogic(new RotatingOffsetLogic());
		logicManager.addThingLogic(new ClickSelectionLogic());
		logicManager.addThingLogic(new MarqueeSelectionLogic());
		logicManager.addThingLogic(new DragMovableLogic());
		logicManager.addThingLogic(new ReshapeRectangleLogic());
		logicManager.addThingLogic(new ReshapeSplineLogic());
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new StandardCursorLogic());

		logicManager.addThingLogic(new ModelBoundsTrackingLogic());
		//logicManager.addThingLogic(new WorldThingExternalEventsLogic(tttl));

	}

	static void populateModel(IBNAWorld world) {
		final IBNAModel model = world.getBNAModel();
		final RectangleGlassThing[] boxes = new RectangleGlassThing[50];

		for (int i = 0; i < boxes.length; i++) {
			RectangleGlassThing box = Assemblies.createRectangle(world, null, null);
			box.getBoxThing().setGradientFilled(true);
			box.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
			box.getBoxBorderThing().setColor(new RGB(0, 0, 0));
			box.getBoxedLabelThing().setText("Now is the time for all good men to come to the aid of their country");
			box.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
			box.getRectangleGlassThing().setBoundingBox(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20 + i * 10,
					DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20 + i * 10, 100, 100);
			box.getRectangleGlassThing().setSelected(i % 2 == 0);
			boxes[i] = box;
		}

		EndpointAssembly endpoint = new EndpointAssembly(world, null, null);
		endpoint.getBoxThing().setColor(new RGB(255, 255, 255));
		endpoint.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		endpoint.getDirectionalLabelThing().setColor(new RGB(0, 0, 0));
		endpoint.getDirectionalLabelThing().setOrientation(Orientation.NORTHWEST);
		endpoint.getDirectionalLabelThing().setFlow(Flow.INOUT);
		endpoint.getEndpointGlassThing().setAnchorPoint(
				new Point(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20,
						DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20));
		StickRelativeMovablesLogic.stickPoint(boxes[0].getRectangleGlassThing(), IHasAnchorPoint.ANCHOR_POINT_KEY,
				StickyMode.EDGE, endpoint.getEndpointGlassThing());

		SplineAssembly spline = new SplineAssembly(m, null, null);
		spline.getSplineGlassThing().setEndpoint1(
				new Point(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20,
						DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20));
		Point[] midpoints = new Point[] {
				new Point(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 30,
						DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 30),
				new Point(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 50,
						DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 50) };
		spline.getSplineGlassThing().setMidpoints(midpoints);
		spline.getSplineGlassThing().setEndpoint2(
				new Point(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 200,
						DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 100));
		spline.getEndpoint1ArrowheadThing().setArrowheadShape(ArrowheadShape.TRIANGLE);
		spline.getEndpoint1ArrowheadThing().setArrowheadSize(10);
		spline.getEndpoint1ArrowheadThing().setSecondaryColor(new RGB(128, 128, 128));
		spline.getEndpoint1ArrowheadThing().setArrowheadFilled(true);

		spline.getEndpoint2ArrowheadThing().setArrowheadShape(ArrowheadShape.TRIANGLE);
		spline.getEndpoint2ArrowheadThing().setArrowheadSize(10);
		spline.getEndpoint2ArrowheadThing().setSecondaryColor(new RGB(128, 128, 128));
		spline.getEndpoint2ArrowheadThing().setArrowheadFilled(true);
		/*
		 * final PathAssembly[] paths = new PathAssembly[50]; for (int i = 0; i
		 * < paths.length; i++) { PathAssembly path = PathAssembly.create(m,
		 * null); path.getPathThing().setGradientFilled(true);
		 * path.getPathGlassThing().setPathData(newExamplePathData(100, 100));
		 * path.getPathGlassThing().moveRelative((DefaultCoordinateMapper.
		 * DEFAULT_WORLD_WIDTH / 2) + 220 + (i 10),
		 * (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20 + (i 10));
		 * path.getPathGlassThing().setSelected((i % 2) == 0); paths[i] = path;
		 * }
		 */
		/*
		 * MarqueeBorderThing mbt = new MarqueeBorderThing();
		 * mbt.setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2),
		 * (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2), 100, 100);
		 * m.addThing(mbt);
		 */
		// final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.H_SCROLL |
		// SWT.V_SCROLL | SWT.BORDER);
	}

	static void populateWithViews(IBNAWorld world, IBNAView parentView, IBNAWorld internalWorld) {
		//		IBNAModel model = world.getBNAModel();
		//
		//		RectangleGlassThing vbox1 = Assemblies.createRectangle(world, null, null);
		//		vbox1.getBoxThing().setGradientFilled(true);
		//		vbox1.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
		//		vbox1.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		//		vbox1.getBoxedLabelThing().setText("Viewsion 1");
		//		vbox1.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
		//		vbox1.getRectangleGlassThing().setBoundingBox(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20 + 200,
		//				DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20 + 100, 200, 200);
		//		vbox1.getRectangleGlassThing().setSelected(false);
		//		vbox1.getWorldThing().setWorld(internalWorld);
		//		//vbox1.getViewThing().setView(new DefaultBNAView(parentView, internalWorld, new DefaultCoordinateMapper()));
		//
		//		RectangleGlassThing vbox2 = Assemblies.createRectangle(world, null, null);
		//		Assemblies.BACKGROUND_KEY.get(vbox2, model).set(IHasGradientFill.GRADIENT_FILLED_KEY, true);
		//		Assemblies.BACKGROUND_KEY.get(vbox2, model).set(IHasLineStyle.LINE_STYLE_KEY, SWT.LINE_DASH);
		//		vbox2.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		//		vbox2.getBoxedLabelThing().setText("Viewsion 2");
		//		vbox2.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
		//		vbox2.getRectangleGlassThing().setBoundingBox(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20 + 400,
		//				DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20 + 100, 200, 200);
		//		vbox2.getRectangleGlassThing().setSelected(false);
		//		vbox2.getWorldThing().setWorld(internalWorld);
		//		//vbox2.getViewThing().setView(new DefaultBNAView(parentView, internalWorld, new DefaultCoordinateMapper()));
		//
	}
}
