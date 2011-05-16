package org.archstudio.bna.demo;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic.StickyMode;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Lists;

public class StickySplineDemo {

	public static void main(String args[]) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final IBNAModel model = new DefaultBNAModel();
		final IBNAWorld world = new DefaultBNAWorld("top view", model);
		final BNACanvas view = new BNACanvas(shell, SWT.V_SCROLL | SWT.H_SCROLL, world);

		view.setSize(500, 500);
		view.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		populateModel(world);
		addUILogics(view);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);
	}

	private static void addUILogics(IBNAView view) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = view.getBNAWorld().getThingLogicManager();

		tlm.addThingLogic(RotatingOffsetLogic.class);
		tlm.addThingLogic(MousePanAndZoomLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
		//tlm.addThingLogic(DebugLogic.class);

		//		tlm.addThingLogic(new StickRelativeMovablesLogic(rtl, tpptl));
		//		tlm.addThingLogic(new ToolTipLogic());
		//
		//		tlm.addThingLogic(new MouseWheelZoomingLogic());
		//		tlm.addThingLogic(new MousePanAndZoomLogic());
		//		tlm.addThingLogic(new MoveWithLogic(rtl));
		//		tlm.addThingLogic(new MirrorAnchorPointLogic(rtl));
		//		tlm.addThingLogic(new AbstractMirrorValueLogic(rtl, tpptl));
		//		tlm.addThingLogic(new MirrorBoundingBoxLogic(rtl));
		//		tlm.addThingLogic(new MirrorPointLogic(rtl));
		//		DragMoveEventsLogic dml = new DragMoveEventsLogic();
		//		tlm.addThingLogic(dml);
		//		tlm.addThingLogic(new RotatingOffsetLogic(tttl));
		//		tlm.addThingLogic(new ClickSelectionLogic(tttl));
		//		tlm.addThingLogic(new MarqueeSelectionLogic(tttl));
		//		tlm.addThingLogic(new DragMovableLogic(dml, tptl));
		//		tlm.addThingLogic(new BoxReshapeHandleLogic(stl, dml));
		//		tlm.addThingLogic(new SplineReshapeHandleLogic(stl, dml));
		//		tlm.addThingLogic(new SplineBreakLogic());
		//		tlm.addThingLogic(new MaintainAnchoredAssemblyOrientationLogic(rtl));
		//		tlm.addThingLogic(new StandardCursorLogic());
		//
		//		tlm.addThingLogic(new ModelBoundsTrackingLogic(tttl));
		//		tlm.addThingLogic(new WorldThingExternalEventsLogic(tttl));

	}

	static void populateModel(IBNAView view) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();

		ICoordinateMapper cm = view.getCoordinateMapper();
		Point offset = cm.getLocalBounds(new Rectangle()).getCenter();

		for (StickyMode stickyMode : StickyMode.values()) {
			PolygonThing p = new PolygonThing(null);
			Rectangle r = new Rectangle(0, 0, 20, 20);
			int x1 = r.x;
			int y1 = r.y;
			int w = r.width;
			int h = r.height;
			int x2 = x1 + w;
			int y2 = y1 + h;
			p.setPoints(Lists.newArrayList(new Point(x1 + w / 2, y1), new Point(x2, y1 + h / 2), new Point(x1 + w / 2,
					y2), new Point(x1, y1 + h / 2), new Point(x1 + w / 2, y1)));
			//			p.getPolygonGlassThing().setBoundingBox(10 + .nextInt(430), 10 + r.nextInt(430), 10 + r.nextInt(40),
			//					10 + r.nextInt(40));
			ToolTipLogic.setToolTip(p, stickyMode.name().replace("_", " "));
			UserEditableUtils.addEditableQuality(p.getPolygonGlassThing(), IHasMutableSelected.USER_MAY_SELECT,
					IRelativeMovable.USER_MAY_MOVE, IHasMutableMinimumSize.USER_MAY_RESIZE);
		}

		for (StickyMode fromStickyMode : StickyMode.values()) {
			for (StickyMode toStickyMode : StickyMode.values()) {
				IAssemblyThing fa = AssemblyUtils.getAssemblyWithRoot(m.getAllThings(), fromStickyMode);
				IAssemblyThing ta = AssemblyUtils.getAssemblyWithRoot(m.getAllThings(), toStickyMode);

				SplineAssembly s = new SplineAssembly(m, null, null);
				s.getEndpoint1ArrowheadThing().setArrowheadShape(ArrowheadShape.TRIANGLE);
				s.getEndpoint2ArrowheadThing().setArrowheadShape(ArrowheadShape.TRIANGLE);
				StickRelativeMovablesLogic.stickPoint(fa.getPart("glass"), IHasEndpoints.ENDPOINT_1_KEY,
						fromStickyMode, s.getSplineGlassThing());
				if (toStickyMode != fromStickyMode) {
					StickRelativeMovablesLogic.stickPoint(ta.getPart("glass"), IHasEndpoints.ENDPOINT_2_KEY,
							toStickyMode, s.getSplineGlassThing());
				}
				else {
					s.getSplineGlassThing().setEndpoint2(new Point(10 + r.nextInt(430), 10 + r.nextInt(430)));
				}
				ToolTipLogic.setToolTip(s.getSplineGlassThing(), fromStickyMode.name().replace("_", " ") + " - to - "
						+ toStickyMode.name().replace("_", " "));
				UserEditableUtils.addEditableQuality(s.getSplineGlassThing(), IHasMutableSelected.USER_MAY_SELECT,
						IRelativeMovable.USER_MAY_MOVE, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1,
						IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
						IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS);
				UserEditableUtils.addEditableQuality(s.getSplineGlassThing(),
						IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1,
						IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);
			}
		}
	}
}