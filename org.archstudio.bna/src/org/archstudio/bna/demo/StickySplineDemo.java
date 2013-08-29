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
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.ShadowThing;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
		final BNACanvas canvas = new BNACanvas(shell, SWT.V_SCROLL | SWT.H_SCROLL, world);

		BNARenderingSettings.setAntialiasGraphics(canvas, true);
		BNARenderingSettings.setAntialiasText(canvas, true);
		BNARenderingSettings.setDecorativeGraphics(canvas, true);

		canvas.setSize(500, 500);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		model.addThing(new ShadowThing(null));

		populateModel(canvas.getBNAView());
		addLogics(canvas.getBNAView());

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

		tlm.addThingLogic(MousePanAndZoomLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
		//tlm.addThingLogic(DebugLogic.class);
	}

	static void populateModel(IBNAView view) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();
		StickPointLogic spl = world.getThingLogicManager().addThingLogic(StickPointLogic.class);
		MirrorValueLogic mvl = world.getThingLogicManager().addThingLogic(MirrorValueLogic.class);

		ICoordinateMapper cm = view.getCoordinateMapper();
		Rectangle cmwb = cm.getWorldBounds();
		Point offset = new Point(cmwb.x + cmwb.width / 2, cmwb.y + cmwb.height / 2);
		List<IIsSticky> shapeThings = Lists.newArrayList();
		IThingKey<StickyMode> shapeStickyMode = ThingKey.create("stickyMode");

		Random r = new Random();
		for (StickyMode stickyMode : StickyMode.values()) {
			PolygonThing p = Assemblies.createPolygon(world, null, null);
			p.setColor(null);
			p.setAnchorPoint(new Point(offset.x + r.nextInt(200) + 50, offset.y + r.nextInt(200) + 50));
			List<Point> points = Lists.newArrayList();
			points.add(new Point(-50, -50));
			points.add(new Point(-50, 50));
			points.add(new Point(50, 50));
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
			mvl.mirrorValue(p, IHasAnchorPoint.ANCHOR_POINT_KEY, label);

			shapeThings.add(p);
		}

		IIsSticky e = Assemblies.createEllipse(world, null, null);
		e.set(shapeStickyMode, StickyMode.CENTER);
		shapeThings.add(e);
		UserEditableUtils.addEditableQualities(e, IHasMutableSelected.USER_MAY_SELECT, IRelativeMovable.USER_MAY_MOVE,
				IHasMutableSize.USER_MAY_RESIZE);

		for (int f = 0; f < shapeThings.size(); f++) {
			for (int t = f + 1; t < shapeThings.size(); t++) {

				IIsSticky ft = shapeThings.get(f);
				StickyMode fsm = ft.get(shapeStickyMode);

				IIsSticky tt = shapeThings.get(t);
				StickyMode tsm = tt.get(shapeStickyMode);

				SplineThing s = Assemblies.createSpline(world, null, null);
				spl.stick(s, IHasEndpoints.ENDPOINT_1_KEY, fsm, ft);
				spl.stick(s, IHasEndpoints.ENDPOINT_2_KEY, tsm, tt);

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