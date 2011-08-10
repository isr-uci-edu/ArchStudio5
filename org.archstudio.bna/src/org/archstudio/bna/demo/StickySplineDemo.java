package org.archstudio.bna.demo;

import java.util.List;
import java.util.Random;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.MirrorPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.AbstractStickPointsLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.ShadowThing;
import org.archstudio.bna.things.glass.EllipseGlassThing;
import org.archstudio.bna.things.glass.PolygonGlassThing;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
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

		BNARenderingSettings.setAntialiasGraphics(view, true);
		BNARenderingSettings.setAntialiasText(view, true);
		BNARenderingSettings.setDecorativeGraphics(view, true);

		view.setSize(500, 500);
		view.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		model.addThing(new ShadowThing(null));

		populateModel(view);
		addLogics(view);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);
	}

	private static void addLogics(IBNAView view) {
		IThingLogicManager tlm = view.getBNAWorld().getThingLogicManager();

		tlm.addThingLogic(RotatingOffsetLogic.class);
		tlm.addThingLogic(MousePanAndZoomLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
		//tlm.addThingLogic(DebugLogic.class);
	}

	static void populateModel(IBNAView view) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();
		AbstractStickPointsLogic spl = world.getThingLogicManager().addThingLogic(AbstractStickPointsLogic.class);
		MirrorPointLogic mpl = world.getThingLogicManager().addThingLogic(MirrorPointLogic.class);

		ICoordinateMapper cm = view.getCoordinateMapper();
		Point offset = cm.getWorldBounds(new Rectangle()).getCenter();
		List<IIsSticky> shapeThings = Lists.newArrayList();
		IThingKey<StickyMode> shapeStickyMode = ThingKey.create("stickyMode");

		Random r = new Random();
		for (int j = 0; j < 2; j++) {
			for (StickyMode stickyMode : StickyMode.values()) {
				PolygonGlassThing p = Assemblies.createPolygon(world, null, null);
				p.setAnchorPoint(offset.getTranslated(r.nextInt(200) + 50, r.nextInt(200) + 50));
				List<Point> points = Lists.newArrayList();
				points.add(new Point(-50, -50));
				points.add(new Point(-50, 50));
				for (int i = 0; i < 5; i++) {
					points.add(new Point(r.nextInt(100) - 50, r.nextInt(100) - 50));
				}
				p.setPoints(points);
				//int w = r.nextInt(100);
				//int h = r.nextInt(100);
				//p.setPoints(Lists.newArrayList(new Point(w / 2, 0), new Point(w, h / 2), new Point(w / 2, h), new Point(0,
				//		h / 2), new Point(w / 2, 0)));
				//p.getPolygonGlassThing().setBoundingBox(10 + .nextInt(430), 10 + r.nextInt(430), 10 + r.nextInt(40),
				//		10 + r.nextInt(40));
				//ToolTipLogic.setToolTip(p, stickyMode.name().replace("_", " "));
				UserEditableUtils.addEditableQualities(p, IHasMutableSelected.USER_MAY_SELECT,
						IRelativeMovable.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE);

				p.set(shapeStickyMode, stickyMode);

				AnchoredLabelThing label = model.addThing(new AnchoredLabelThing(null));
				label.setText(stickyMode.name());
				MirrorValueLogic mvl = world.getThingLogicManager().addThingLogic(MirrorValueLogic.class);
				mvl.mirrorValue(p, IHasAnchorPoint.ANCHOR_POINT_KEY, label);
				shapeThings.add(p);
			}
		}

		for (int f = 0; f < shapeThings.size(); f++) {
			for (int t = f + 1; t < shapeThings.size(); t++) {

				IIsSticky ft = shapeThings.get(f);
				StickyMode fsm = ft.get(shapeStickyMode);

				IIsSticky tt = shapeThings.get(t);
				StickyMode tsm = tt.get(shapeStickyMode);

				SplineGlassThing s = Assemblies.createSpline(world, null, null);

				spl.stick(ft, fsm, 0, s);
				EllipseGlassThing e0 = Assemblies.createEllipse(world, null, ft);
				e0.setBoundingBox(new Rectangle(0, 0, 7, 7));
				mpl.mirror(s, 0, e0);

				spl.stick(tt, tsm, -1, s);
				EllipseGlassThing e1 = Assemblies.createEllipse(world, null, tt);
				e1.setBoundingBox(new Rectangle(0, 0, 7, 7));
				mpl.mirror(s, -1, e1);

				//StickRelativeMovablesLogic.stickPoint(fa.getPart("glass"), IHasEndpoints.ENDPOINT_1_KEY,
				//		fromStickyMode, s.getSplineGlassThing());
				//if (toStickyMode != fromStickyMode) {
				//	StickRelativeMovablesLogic.stickPoint(ta.getPart("glass"), IHasEndpoints.ENDPOINT_2_KEY,
				//			toStickyMode, s.getSplineGlassThing());
				//}
				//else {
				//	s.getSplineGlassThing().setEndpoint2(new Point(10 + r.nextInt(430), 10 + r.nextInt(430)));
				//}
				//ToolTipLogic.setToolTip(s.getSplineGlassThing(), fromStickyMode.name().replace("_", " ") + " - to - "
				//		+ toStickyMode.name().replace("_", " "));
				//UserEditableUtils.addEditableQuality(s.getSplineGlassThing(), IHasMutableSelected.USER_MAY_SELECT,
				//		IRelativeMovable.USER_MAY_MOVE, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1,
				//		IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
				//		IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS);
				//UserEditableUtils.addEditableQuality(s.getSplineGlassThing(),
				//		IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1,
				//		IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);
			}
		}
	}
}