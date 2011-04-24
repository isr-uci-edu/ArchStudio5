package org.archstudio.bna.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.DefaultBNAModel;
import org.archstudio.bna.DefaultBNAView;
import org.archstudio.bna.DefaultBNAWorld;
import org.archstudio.bna.DefaultCoordinateMapper;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.facets.IHasStickyEndpoints;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic;
import org.archstudio.bna.logics.coordinating.AssemblyLogic;
import org.archstudio.bna.logics.coordinating.BoundingBoxRailLogic;
import org.archstudio.bna.logics.coordinating.EndpointFlowOrientationLogic;
import org.archstudio.bna.logics.coordinating.MaintainStickyEndpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorAnchorPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorEndpointLogic;
import org.archstudio.bna.logics.coordinating.MirrorEndpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorMidpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorPathDataLogic;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.coordinating.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.coordinating.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.editing.BoxReshapeHandleLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.DragMovableSelectionLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.SplineReshapeHandleLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.editing.StickySplineEndpointsColorLogic;
import org.archstudio.bna.logics.editing.StickySplineEndpointsLogic;
import org.archstudio.bna.logics.navigating.MousePanningLogic;
import org.archstudio.bna.logics.navigating.MouseWheelZoomingLogic;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.logics.tracking.StickyBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;

public class BasicBNADemo {
	public static void main(String args[]) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final IBNAModel m = new DefaultBNAModel();

		IBNAWorld bnaWorld1 = new DefaultBNAWorld("bna", m);
		setupTopWorld(bnaWorld1);
		populateModel(m);

		IBNAView bnaView1 = new DefaultBNAView(null, bnaWorld1, new DefaultCoordinateMapper());

		IBNAModel m2 = new DefaultBNAModel();
		IBNAWorld bnaWorld2 = new DefaultBNAWorld("subworld", m2);
		setupWorld(bnaWorld2);

		populateModel(m2);
		/*
		 * IBNAView bnaView2 = new DefaultBNAView(bnaView1, bnaWorld2, new
		 * DefaultCoordinateMapper()); ViewThing wt2 = new ViewThing();
		 * wt2.setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2)
		 * + 20, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20, 200,
		 * 200); wt2.setView(bnaView2); m.addThing(wt2); IBNAView bnaView3 = new
		 * DefaultBNAView(bnaView1, bnaWorld2, new DefaultCoordinateMapper());
		 * ViewThing wt3 = new ViewThing();
		 * wt3.setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2)
		 * + 200, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20, 200,
		 * 200); wt3.setView(bnaView3); m.addThing(wt3);
		 */

		populateWithViews(m, bnaView1, bnaWorld2);

		final BNAComposite bnaComposite = new BNAComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL | SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED, bnaView1);
		bnaComposite.setSize(500, 500);
		bnaComposite.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		System.exit(0);
	}

	static void setupTopWorld(IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();
		logicManager.addThingLogic(new MouseWheelZoomingLogic());
		logicManager.addThingLogic(new MousePanningLogic());
		setupWorld(bnaWorld);
	}

	static void setupWorld(IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		ThingRefTrackingLogic trtl = new ThingRefTrackingLogic();
		logicManager.addThingLogic(trtl);
		BoundingBoxTrackingLogic bbtl = new BoundingBoxTrackingLogic();
		logicManager.addThingLogic(bbtl);
		AnchorPointTrackingLogic aptl = new AnchorPointTrackingLogic();
		logicManager.addThingLogic(aptl);
		SelectionTrackingLogic stl = new SelectionTrackingLogic();
		logicManager.addThingLogic(stl);
		EndpointsTrackingLogic epstl = new EndpointsTrackingLogic();
		logicManager.addThingLogic(epstl);
		MidpointsTrackingLogic mpstl = new MidpointsTrackingLogic();
		logicManager.addThingLogic(mpstl);
		StickyBoxTrackingLogic sbtl = new StickyBoxTrackingLogic();
		logicManager.addThingLogic(sbtl);

		TypedThingSetTrackingLogic<IHasMutableRotatingOffset> ttstlOffset = new TypedThingSetTrackingLogic<IHasMutableRotatingOffset>(
		        IHasMutableRotatingOffset.class);
		logicManager.addThingLogic(ttstlOffset);

		TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected = new TypedThingSetTrackingLogic<IHasMutableSelected>(IHasMutableSelected.class);
		logicManager.addThingLogic(ttstlSelected);

		TypedThingSetTrackingLogic<IHasStickyBox> ttstlSticky = new TypedThingSetTrackingLogic<IHasStickyBox>(IHasStickyBox.class);
		logicManager.addThingLogic(ttstlSticky);

		TypedThingSetTrackingLogic<IHasStickyEndpoints> ttstlStickyEndpoints = new TypedThingSetTrackingLogic<IHasStickyEndpoints>(IHasStickyEndpoints.class);
		logicManager.addThingLogic(ttstlStickyEndpoints);

		TypedThingSetTrackingLogic<IHasWorld> ttstlView = new TypedThingSetTrackingLogic<IHasWorld>(IHasWorld.class);
		logicManager.addThingLogic(ttstlView);

		logicManager.addThingLogic(new AssemblyLogic());
		logicManager.addThingLogic(new MoveWithLogic(trtl, bbtl, aptl));
		logicManager.addThingLogic(new MirrorAnchorPointLogic(trtl, aptl));
		logicManager.addThingLogic(new MirrorBoundingBoxLogic(trtl, bbtl));
		logicManager.addThingLogic(new MirrorEndpointsLogic(trtl, epstl));
		logicManager.addThingLogic(new MirrorMidpointsLogic(trtl, mpstl));
		logicManager.addThingLogic(new MirrorEndpointLogic(trtl, epstl));
		logicManager.addThingLogic(new ArrowheadLogic(trtl, epstl, mpstl));
		logicManager.addThingLogic(new MirrorPathDataLogic(trtl));
		DragMovableLogic dml = new DragMovableLogic();
		logicManager.addThingLogic(dml);
		logicManager.addThingLogic(new RotatingOffsetLogic(ttstlOffset));
		logicManager.addThingLogic(new ClickSelectionLogic(ttstlSelected));
		logicManager.addThingLogic(new MarqueeSelectionLogic(ttstlSelected));
		logicManager.addThingLogic(new DragMovableSelectionLogic(dml, stl));
		logicManager.addThingLogic(new BoxReshapeHandleLogic(stl, bbtl, dml));
		SplineReshapeHandleLogic srhl = new SplineReshapeHandleLogic(stl, epstl, mpstl, dml);
		logicManager.addThingLogic(srhl);
		logicManager.addThingLogic(new StickySplineEndpointsLogic(ttstlSticky, srhl));
		logicManager.addThingLogic(new StickySplineEndpointsColorLogic(stl, srhl));
		logicManager.addThingLogic(new MaintainStickyEndpointsLogic(trtl, ttstlStickyEndpoints, sbtl));
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new BoundingBoxRailLogic(trtl, bbtl, aptl));
		logicManager.addThingLogic(new EndpointFlowOrientationLogic(aptl));
		logicManager.addThingLogic(new StandardCursorLogic());

		logicManager.addThingLogic(new ModelBoundsTrackingLogic(bbtl, aptl));
		logicManager.addThingLogic(new WorldThingInternalEventsLogic(ttstlView));
		logicManager.addThingLogic(new WorldThingExternalEventsLogic(ttstlView));

	}

	static void populateModel(/* BNAComposite c, */IBNAModel m) {
		final BoxAssembly[] boxes = new BoxAssembly[50];

		for (int i = 0; i < boxes.length; i++) {
			BoxAssembly box = BoxAssembly.create(m, null);
			box.getBoxThing().setGradientFilled(true);
			box.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
			box.getBoxBorderThing().setColor(new RGB(0, 0, 0));
			box.getBoxedLabelThing().setText("Now is the time for all good men to come to the aid of their country");
			box.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
			box.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20 + (i * 10),
			        (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20 + (i * 10), 100, 100);
			box.getBoxGlassThing().setSelected((i % 2) == 0);
			boxes[i] = box;
		}

		EndpointAssembly endpoint = EndpointAssembly.create(m, null);
		endpoint.getBoxThing().setColor(new RGB(255, 255, 255));
		endpoint.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		endpoint.getDirectionalLabelThing().setColor(new RGB(0, 0, 0));
		endpoint.getDirectionalLabelThing().setOrientation(Orientation.NORTHWEST);
		endpoint.getDirectionalLabelThing().setFlow(Flow.INOUT);
		endpoint.getEndpointGlassThing().setAnchorPoint(
		        new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20));
		endpoint.getEndpointGlassThing().setBoundingBoxRailMasterThingID(boxes[0].getBoxGlassThing().getID());

		StickySplineAssembly spline = StickySplineAssembly.create(m, null);
		spline.getSplineGlassThing().setEndpoint1(
		        new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20));
		Point[] midpoints = new Point[] {
		        new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 30, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 30),
		        new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 50, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 50) };
		spline.getSplineGlassThing().setMidpoints(midpoints);
		spline.getSplineGlassThing().setEndpoint2(
		        new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 200, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 100));
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
		 * DEFAULT_WORLD_WIDTH / 2) + 220 + (i * 10),
		 * (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20 + (i * 10));
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

	static void populateWithViews(IBNAModel m, IBNAView parentView, IBNAWorld internalWorld) {
		BoxAssembly vbox1 = BoxAssembly.create(m, null);
		vbox1.getBoxThing().setGradientFilled(true);
		vbox1.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
		vbox1.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		vbox1.getBoxedLabelThing().setText("Viewsion 1");
		vbox1.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
		vbox1.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20 + 200,
		        (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20 + 100, 200, 200);
		vbox1.getBoxGlassThing().setSelected(false);
		vbox1.getWorldThing().setWorld(internalWorld);
		//vbox1.getViewThing().setView(new DefaultBNAView(parentView, internalWorld, new DefaultCoordinateMapper()));

		BoxAssembly vbox2 = BoxAssembly.create(m, null);
		vbox2.getBoxThing().setGradientFilled(true);
		vbox2.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
		vbox2.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		vbox2.getBoxedLabelThing().setText("Viewsion 2");
		vbox2.getBoxedLabelThing().setColor(new RGB(255, 0, 0));
		vbox2.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20 + 400,
		        (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20 + 100, 200, 200);
		vbox2.getBoxGlassThing().setSelected(false);
		vbox2.getWorldThing().setWorld(internalWorld);
		//vbox2.getViewThing().setView(new DefaultBNAView(parentView, internalWorld, new DefaultCoordinateMapper()));

	}
}
