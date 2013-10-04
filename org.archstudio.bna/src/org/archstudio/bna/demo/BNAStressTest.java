package org.archstudio.bna.demo;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BNAStressTest {

	private static boolean DEBUG = true;

	public static void main(String[] args) {
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

		populateModel(canvas.getBNAView(), 40, 40, 3, 2);
		addUILogics(canvas.getBNAView());

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		model.dispose();
		world.dispose();
		display.dispose();
		System.exit(0);
	}

	private static void addUILogics(IBNAView view) {
		IThingLogicManager tlm = view.getBNAWorld().getThingLogicManager();
		tlm.addThingLogic(RotatingOffsetLogic.class);
		tlm.addThingLogic(MousePanAndZoomLogic.class);
		tlm.addThingLogic(MarqueeSelectionLogic.class);
		tlm.addThingLogic(ClickSelectionLogic.class);
		tlm.addThingLogic(DragMovableLogic.class);
		//tlm.addThingLogic(DebugLogic.class);
	}

	private static void populateModel(IBNAView view, int cw, int ch, int itbw, int ilrh) {

		IBNAWorld world = view.getBNAWorld();
		IBNAModel model = world.getBNAModel();

		model.addThing(new GridThing());
		model.addThing(new ShadowThing());

		ICoordinateMapper cm = view.getCoordinateMapper();
		Rectangle bounds = cm.getLocalBounds();
		Point offset = new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);

		int x0 = offset.x;
		int y0 = offset.y;
		int w = 60;
		int h = 40;
		int sw = 20;
		int sh = 20;

		long lTime = System.nanoTime();
		model.beginBulkChange();
		try {
			for (int cwi = 0; cwi < cw; cwi++) {
				for (int chi = 0; chi < ch; chi++) {

					int cx1 = x0 + (w + sw) * cwi;
					int cy1 = y0 + (h + sh) * chi;
					Object id = id(cwi, chi);

					RectangleThing b = Assemblies.createRectangle(world, id, null);
					b.setBoundingBox(new Rectangle(cx1, cy1, w, h));
					Assemblies.TEXT_KEY.get(b, model).set(
							IHasText.TEXT_KEY,
							"Now is the time for all good men to come to the aid of their country (" + (cwi + chi * cw)
									+ ")");
					UserEditableUtils.addEditableQualities(b, IHasMutableSelected.USER_MAY_SELECT,
							IRelativeMovable.USER_MAY_MOVE);

					Flow f = Flow.values()[(cwi + chi) % 4];

					for (int iwi = 0; iwi < itbw; iwi++) {
						createEndpoint(world, id(id, Orientation.NORTH, iwi), b, new Point(cx1 + w * (iwi + 1)
								/ (itbw + 1), cy1), f, Orientation.NORTH);
						createEndpoint(world, id(id, Orientation.SOUTH, iwi), b, new Point(cx1 + w * (iwi + 1)
								/ (itbw + 1), cy1 + h), f, Orientation.SOUTH);
					}
					for (int ihi = 0; ihi < ilrh; ihi++) {
						createEndpoint(world, id(id, Orientation.WEST, ihi), b, new Point(cx1, cy1 + h * (ihi + 1)
								/ (ilrh + 1)), f, Orientation.WEST);
						createEndpoint(world, id(id, Orientation.EAST, ihi), b, new Point(cx1 + w, cy1 + h * (ihi + 1)
								/ (ilrh + 1)), f, Orientation.EAST);
					}

					createEndpoint(world, id(id, Orientation.NORTHWEST, 0), b, new Point(cx1, cy1), f,
							Orientation.NORTHWEST);
					createEndpoint(world, id(id, Orientation.NORTHEAST, 0), b, new Point(cx1 + w, cy1), f,
							Orientation.NORTHEAST);
					createEndpoint(world, id(id, Orientation.SOUTHWEST, 0), b, new Point(cx1, cy1 + h), f,
							Orientation.SOUTHWEST);
					createEndpoint(world, id(id, Orientation.SOUTHEAST, 0), b, new Point(cx1 + w, cy1 + h), f,
							Orientation.SOUTHEAST);
				}
			}
			for (int cwi = 0; cwi < cw; cwi++) {
				for (int chi = 1; chi < ch; chi++) {
					for (int iwi = 0; iwi < itbw; iwi++) {
						createSpline(world, id(id(cwi, chi - 1), Orientation.SOUTH, iwi),
								id(id(cwi, chi), Orientation.NORTH, iwi));
					}
				}
			}
			for (int cwi = 1; cwi < cw; cwi++) {
				for (int chi = 0; chi < ch; chi++) {
					for (int ihi = 0; ihi < ilrh; ihi++) {
						createSpline(world, id(id(cwi - 1, chi), Orientation.EAST, ihi),
								id(id(cwi, chi), Orientation.WEST, ihi));
					}
				}
			}
			for (int cwi = 1; cwi < cw; cwi++) {
				for (int chi = 1; chi < ch; chi++) {
					createSpline(world, id(id(cwi - 1, chi - 1), Orientation.SOUTHEAST, 0),
							id(id(cwi, chi), Orientation.NORTHWEST, 0));
					createSpline(world, id(id(cwi, chi - 1), Orientation.SOUTHWEST, 0),
							id(id(cwi - 1, chi), Orientation.NORTHEAST, 0));
				}
			}
		}
		finally {
			model.endBulkChange();
		}
		if (DEBUG) {
			lTime = System.nanoTime() - lTime;
			System.err.println("Time to add elements: " + lTime / 1000000);
		}
	}

	private static Object id(int cwi, int chi) {
		return "(" + cwi + "," + chi + ")";
	}

	private static Object id(Object id, Orientation orientation, int index) {
		return "(" + id + ":" + orientation + "," + index + ")";
	}

	public static EndpointGlassThing createEndpoint(IBNAWorld world, Object id, IIsSticky parent, Point location,
			Flow flow, Orientation orientation) {
		IBNAModel model = world.getBNAModel();

		EndpointGlassThing e = Assemblies.createEndpoint(world, id, parent);
		e.setAnchorPoint(location);

		Assemblies.DIRECTION_KEY.get(e, model).setFlow(flow);
		Assemblies.DIRECTION_KEY.get(e, model).setOrientation(orientation);

		UserEditableUtils.addEditableQualities(e, IRelativeMovable.USER_MAY_MOVE);

		return e;
	}

	private static void createSpline(IBNAWorld world, Object id0, Object id1) {
		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		SplineThing s = Assemblies.createSpline(world, null, null);
		IHasAnchorPoint a0 = (IHasAnchorPoint) model.getThing(id0);
		IHasAnchorPoint a1 = (IHasAnchorPoint) model.getThing(id1);

		StickPointLogic spl = tlm.addThingLogic(StickPointLogic.class);
		spl.stick(s, IHasEndpoints.ENDPOINT_1_KEY, StickyMode.CENTER, a0);
		spl.stick(s, IHasEndpoints.ENDPOINT_2_KEY, StickyMode.CENTER, a1);
	}
}
