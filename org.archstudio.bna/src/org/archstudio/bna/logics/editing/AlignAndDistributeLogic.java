package org.archstudio.bna.logics.editing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.BNAAlignUtils;
import org.archstudio.bna.utils.BNADistributeUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchActionConstants;

public class AlignAndDistributeLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected ImageRegistry imageRegistry = null;

	protected static final String ALIGN_LEFT = "alignLeft";
	protected static final String ALIGN_CENTER = "alignCenter";
	protected static final String ALIGN_RIGHT = "alignRight";
	protected static final String ALIGN_TOP = "alignTop";
	protected static final String ALIGN_MIDDLE = "alignMiddle";
	protected static final String ALIGN_BOTTOM = "alignBottom";

	protected static final String DISTRIBUTE_HORIZONTAL_LOOSE = "distributeHorizontalLoose";
	protected static final String DISTRIBUTE_HORIZONTAL_TIGHT = "distributeHorizontalTight";
	protected static final String DISTRIBUTE_VERTICAL_LOOSE = "distributeVerticalLoose";
	protected static final String DISTRIBUTE_VERTICAL_TIGHT = "distributeVerticalTight";

	public AlignAndDistributeLogic(IBNAWorld world) {
		super(world);
	}

	protected void loadImages(IBNAView view) {
		Display disp = view.getBNAUI().getComposite().getDisplay();
		imageRegistry = new ImageRegistry(disp);

		imageRegistry.put(ALIGN_LEFT, new Image(disp, getClass().getResourceAsStream("res/align-left.gif")));
		imageRegistry.put(ALIGN_CENTER, new Image(disp, getClass().getResourceAsStream("res/align-center.gif")));
		imageRegistry.put(ALIGN_RIGHT, new Image(disp, getClass().getResourceAsStream("res/align-right.gif")));
		imageRegistry.put(ALIGN_TOP, new Image(disp, getClass().getResourceAsStream("res/align-top.gif")));
		imageRegistry.put(ALIGN_MIDDLE, new Image(disp, getClass().getResourceAsStream("res/align-middle.gif")));
		imageRegistry.put(ALIGN_BOTTOM, new Image(disp, getClass().getResourceAsStream("res/align-bottom.gif")));

		imageRegistry.put(DISTRIBUTE_HORIZONTAL_LOOSE,
				new Image(disp, getClass().getResourceAsStream("res/distribute-horizontal-loose.gif")));
		imageRegistry.put(DISTRIBUTE_HORIZONTAL_TIGHT,
				new Image(disp, getClass().getResourceAsStream("res/distribute-horizontal-tight.gif")));
		imageRegistry.put(DISTRIBUTE_VERTICAL_LOOSE,
				new Image(disp, getClass().getResourceAsStream("res/distribute-vertical-loose.gif")));
		imageRegistry.put(DISTRIBUTE_VERTICAL_TIGHT,
				new Image(disp, getClass().getResourceAsStream("res/distribute-vertical-tight.gif")));
	}

	@Override
	synchronized public void dispose() {
		if (imageRegistry != null) {
			imageRegistry.dispose();
		}
	}

	@Override
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		if (imageRegistry == null) {
			loadImages(view);
		}

		Collection<IThing> selectedThings = BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel());
		if (selectedThings.size() < 2) {
			return;
		}

		final List<IThing> thingsToEditList = new ArrayList<IThing>();

		//Make sure at least two things have either an anchor point or bounding box
		for (IThing st : selectedThings) {
			if (st instanceof IHasBoundingBox || st instanceof IHasMutableAnchorPoint) {
				thingsToEditList.add(st);
			}
		}
		if (thingsToEditList.size() < 2) {
			return;
		}
		final IThing[] thingsToEdit = thingsToEditList.toArray(new IThing[thingsToEditList.size()]);
		final Runnable undoRunnable = BNAOperations.takeSnapshotOfLocations(model, thingsToEditList);

		IMenuManager alignMenu = new MenuManager("Align");

		IAction alignTop = new Action("Align &Top", imageRegistry.getDescriptor(ALIGN_TOP)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, VerticalAlignment.TOP);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignTop);

		IAction alignMiddle = new Action("Align &Middle", imageRegistry.getDescriptor(ALIGN_MIDDLE)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, VerticalAlignment.MIDDLE);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignMiddle);

		IAction alignBottom = new Action("Align &Bottom", imageRegistry.getDescriptor(ALIGN_BOTTOM)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, VerticalAlignment.BOTTOM);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignBottom);

		IAction alignLeft = new Action("Align &Left", imageRegistry.getDescriptor(ALIGN_LEFT)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, HorizontalAlignment.LEFT);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignLeft);

		IAction alignCenter = new Action("Align &Center", imageRegistry.getDescriptor(ALIGN_CENTER)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, HorizontalAlignment.CENTER);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignCenter);

		IAction alignRight = new Action("Align &Right", imageRegistry.getDescriptor(ALIGN_RIGHT)) {

			@Override
			public void run() {
				BNAAlignUtils.align(thingsToEdit, HorizontalAlignment.RIGHT);
				BNAOperations.runnable("Align", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		alignMenu.add(alignRight);

		IMenuManager distributeMenu = new MenuManager("Distribute");

		IAction distributeHorizontalLoose = new Action("Distribute Horizontal Loose",
				imageRegistry.getDescriptor(DISTRIBUTE_HORIZONTAL_LOOSE)) {

			@Override
			public void run() {
				BNADistributeUtils.distributeHorizontalLoose(thingsToEdit);
				BNAOperations.runnable("Distribute", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		distributeMenu.add(distributeHorizontalLoose);

		IAction distributeHorizontalTight = new Action("Distribute Horizontal Tight",
				imageRegistry.getDescriptor(DISTRIBUTE_HORIZONTAL_TIGHT)) {

			@Override
			public void run() {
				BNADistributeUtils.distributeHorizontalTight(thingsToEdit);
				BNAOperations.runnable("Distribute", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		distributeMenu.add(distributeHorizontalTight);

		IAction distributeVerticalLoose = new Action("Distribute Vertical Loose",
				imageRegistry.getDescriptor(DISTRIBUTE_VERTICAL_LOOSE)) {

			@Override
			public void run() {
				BNADistributeUtils.distributeVerticalLoose(thingsToEdit);
				BNAOperations.runnable("Distribute", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		distributeMenu.add(distributeVerticalLoose);

		IAction distributeVerticalTight = new Action("Distribute Vertical Tight",
				imageRegistry.getDescriptor(DISTRIBUTE_VERTICAL_TIGHT)) {

			@Override
			public void run() {
				BNADistributeUtils.distributeVerticalTight(thingsToEdit);
				BNAOperations.runnable("Distribute", undoRunnable,
						BNAOperations.takeSnapshotOfLocations(model, thingsToEditList), false);
			}
		};
		distributeMenu.add(distributeVerticalTight);

		menu.add(alignMenu);
		menu.add(distributeMenu);
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
}
