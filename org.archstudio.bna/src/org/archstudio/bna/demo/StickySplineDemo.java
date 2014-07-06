package org.archstudio.bna.demo;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.navigating.PanAndZoomLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Function;
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
		BNARenderingSettings.setDisplayShadows(canvas, true);

		canvas.setSize(500, 500);
		canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		shell.setSize(400, 400);
		shell.open();

		GridThing.createIn(world);
		ShadowThing.createIn(world);

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

		tlm.addThingLogic(PanAndZoomLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
	}

	static void populateModel(IBNAView view) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();
		StickPointLogic spl = world.getThingLogicManager().addThingLogic(StickPointLogic.class);
		MirrorValueLogic mvl = world.getThingLogicManager().addThingLogic(MirrorValueLogic.class);

		List<IHasStickyShape> shapeThings = Lists.newArrayList();
		IThingKey<StickyMode> shapeStickyMode = ThingKey.create("stickyMode");

		Random r = new Random();
		for (StickyMode stickyMode : StickyMode.values()) {
			PolygonThing p = Assemblies.createPolygon(world, null, null);
			p.setColor(null);
			List<Point2D> points = Lists.newArrayList();
			points.add(new Point2D.Double(-50, -50));
			points.add(new Point2D.Double(-50, 50));
			points.add(new Point2D.Double(50, 50));
			for (int i = 0; i < 5; i++) {
				points.add(new Point2D.Double(r.nextInt(100) - 50, r.nextInt(100) - 50));
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
					IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE);

			p.set(shapeStickyMode, stickyMode);

			AnchoredLabelThing label = model.addThing(new AnchoredLabelThing(null));
			label.setText(stickyMode.name());
			mvl.mirrorValue(p, IHasBoundingBox.BOUNDING_BOX_KEY, label, IHasAnchorPoint.ANCHOR_POINT_KEY,
					new Function<Rectangle, Point2D>() {
						@Override
						public Point2D apply(Rectangle input) {
							return new Point2D.Double(input.x + input.width / 2, input.y + input.height / 2);
						}
					});

			shapeThings.add(p);
		}

		EllipseThing e = Assemblies.createEllipse(world, null, null);
		e.setColor(new RGB(192, 0, 0));
		e.setSecondaryColor(new RGB(128, 32, 32));
		e.setBoundingBox(new Rectangle(0, 0, 30, 30));
		e.set(shapeStickyMode, StickyMode.CENTER);
		shapeThings.add(e);
		UserEditableUtils.addEditableQualities(e, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE);

		for (int f = 0; f < shapeThings.size(); f++) {
			for (int t = f + 1; t < shapeThings.size(); t++) {

				IHasStickyShape ft = shapeThings.get(f);
				StickyMode fsm = ft.get(shapeStickyMode);

				IHasStickyShape tt = shapeThings.get(t);
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
				//UserEditableUtils.addEditableQuality(s.getSplineGlassThing(), IHasSelected.USER_MAY_SELECT,
				//		IHasReferencePoint.USER_MAY_MOVE, IHasEndpoints.USER_MAY_MOVE_ENDPOINT1,
				//		IHasEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMidpoints.USER_MAY_ADD_MIDPOINTS,
				//		IHasMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMidpoints.USER_MAY_REMOVE_MIDPOINTS);
				//UserEditableUtils.addEditableQuality(s.getSplineGlassThing(),
				//		IHasEndpoints.USER_MAY_RESTICK_ENDPOINT1,
				//		IHasEndpoints.USER_MAY_RESTICK_ENDPOINT2);
			}
		}
	}
}