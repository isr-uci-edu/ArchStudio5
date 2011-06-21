package org.archstudio.bna.demo;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.coordinating.AbstractMirrorValueLogic;
import org.archstudio.bna.logics.coordinating.MirrorAnchorPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorPointLogic;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.SplineReshapeHandleLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.swt.SWTBNALayoutLogic;
import org.archstudio.bna.logics.swt.SWTBNASynchLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BNASWTSynchDemo {

	SWTBNASynchLogic swtSynchLogic;

	SWTBNALayoutLogic swtLayoutLogic;

	public static void main(String args[]) {
		BNASWTSynchDemo demo = new BNASWTSynchDemo();
	}

	public BNASWTSynchDemo() {

		// Create Core {SWT} components
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		// Right Hand Side {BNA} Composite
		// Create Core {BNA} components
		final IBNAModel m = new DefaultBNAModel();
		IBNAWorld world1 = new DefaultBNAWorld("bna", m);
		setupWorld(world1);
		IBNAView view1 = new DefaultBNAView(null, world1, new DefaultCoordinateMapper());

		final BNAComposite bnaComposite = new BNAComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL | SWT.DOUBLE_BUFFERED,
				view1);
		bnaComposite.setSize(500, 500);
		bnaComposite.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

		// Draw shell to screen
		shell.setSize(600, 600);
		shell.open();

		//// Sample Box
		//BoxAssembly[] boxes = new BoxAssembly[10];
		//for(int i=0; i<boxes.length; i++){
		//	BoxAssembly box = boxes[i] = BoxAssembly.create(m, null);
		//	box.getBoxedLabelThing().setText("I'm a BNA Box");
		//	box.getBoxThing().setGradientFilled(true);
		//	box.getBoxBorderThing().setLineStyle(SWT.LINE_DASH);
		//	box.getBoxBorderThing().setColor(new RGB(0, 0, 0));
		//	box.getBoxedLabelThing().setColor(new RGB(255, 255, 255));	
		//	box.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2)
		//				+ 20 + (i * 10), (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2)
		//				+ 20 + (i * 10), 100, 100);
		//	box.getBoxGlassThing().setSelected(true);	
		//}
		//
		//composite.setLayout(new GridLayout(2, false));
		//GridData gridData = new GridData();
		//gridData.horizontalIndent = 0;
		//gridData.verticalIndent = 0;
		//composite.setLayoutData(gridData);
		//
		//// Sample Button
		//Button[] swtButtons = new Button[10];
		//for(int i=0; i<swtButtons.length; i++){
		//	Button swtButton = swtButtons[i] = new Button(composite, SWT.NONE);
		//	swtButton.setText("I'm a SWT button");
		//	swtButton.setSize(150, 100);
		//	swtButton.setLocation(10 + i * 10, 10 + i * 10);
		//	swtButton.setVisible(true);
		//	gridData = new GridData();
		//	if(i == 0)
		//		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		//	gridData.horizontalIndent = 0;
		//	gridData.verticalIndent = 0;
		//	swtButton.setLayoutData(gridData);
		//}
		//
		//// Add mappings to each BNA/SWT pair the logic should be aware of managing
		//for(int i=0; i<boxes.length && i<swtButtons.length; i++){
		//	//swtBNALogic.addSWTToBNAMapping(swtButtons[i], boxes[i].getBoxGlassThing().getID());
		//	swtBNALogic.addBNAToSWTMapping(boxes[i].getBoxClassThing().getID(), swtButtons[i]);
		//}

		GridData gd;

		// EXAMPLE CLASS

		// class BNA
		BoxAssembly classBox = new BoxAssembly(m, null, null);
		classBox.getBoxGlassThing().setBoundingBox(DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2 + 20,
				DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2 + 20, 100, 100);
		classBox.getBoxGlassThing().setProperty(IHasSWTLayout.SWT_LAYOUT_KEY, new GridLayout(3, false));

		// logic's actions
		//		Composite classBoxSWT = new Composite(composite, SWT.NONE);
		//		classBoxSWT.setBackground(classBoxSWT.getDisplay().getSystemColor(SWT.COLOR_RED));
		//		classBoxSWT.setLayout((Layout)classBox.getBoxGlassThing().getProperty(IHasSWTLayout.SWT_LAYOUT_KEY));
		//		swtBNALogic.addBNAToSWTMapping(classBox.getBoxGlassThing().getID(), classBoxSWT);

		// class title BNA
		BoundedLabelThing classTitle = new BoundedLabelThing();
		classTitle.setText("Class");
		m.addThing(classTitle, classBox.getBoxThing());
		classTitle.setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_KEY, new GridData(SWT.FILL, SWT.CENTER, true, false,
				3, 1));
		classTitle.setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY, classBox.getBoxGlassThing().getID());

		// logic's actions
		//		Button classTitleSWT = new Button(classBoxSWT, SWT.NONE);
		//		classTitleSWT.setText(classTitle.getText());
		//		classTitleSWT.setVisible(true);
		//		classTitleSWT.setLayoutData(classTitle.getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_KEY));
		//		swtBNALogic.addSWTToBNAMapping(classTitleSWT, classTitle.getID());

		BoundedLabelThing[] classOperations = new BoundedLabelThing[3];
		for (int i = 0; i < classOperations.length; i++) {
			// class operation BNA
			BoundedLabelThing classOperation = classOperations[i] = new BoundedLabelThing();
			classOperation.setText("operation " + i);
			m.addThing(classOperation, classBox.getBoxThing());
			classOperation.setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_KEY, new GridData(SWT.LEFT, SWT.CENTER, true,
					false, 3, 1));
			classOperation.setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY, classBox.getBoxGlassThing().getID());

			// logic's actions
			//			Button classOperationSWT = new Button(classBoxSWT, SWT.NONE);
			//			classOperationSWT.setText(classOperation.getText());
			//			classOperationSWT.setVisible(true);
			//			classOperationSWT.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
			//			swtBNALogic.addSWTToBNAMapping(classOperationSWT, classOperation.getID());
		}

		BoundedLabelThing[] classFields = new BoundedLabelThing[3];
		for (int i = 0; i < classFields.length; i++) {
			// class field BNA
			BoundedLabelThing classField = classFields[i] = new BoundedLabelThing();
			classField.setText("field " + i);
			m.addThing(classField, classBox.getBoxThing());
			classField.setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_KEY, new GridData(SWT.RIGHT, SWT.CENTER, true,
					false, 3, 1));
			classField.setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_KEY, classBox.getBoxGlassThing().getID());

			// logic's actions
			//			Button classFieldSWT = new Button(classBoxSWT, SWT.NONE);
			//			classFieldSWT.setText(classField.getText());
			//			classFieldSWT.setVisible(true);
			//			classFieldSWT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 3, 1));
			//			swtBNALogic.addSWTToBNAMapping(classFieldSWT, classField.getID());
		}

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);
	}

	private void setupWorld(IBNAWorld bnaWorld) {

		// Add all associated logics

		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		ThingTypeTrackingLogic tttl = new ThingTypeTrackingLogic();
		logicManager.addThingLogic(tttl);
		ThingValueTrackingLogic tptl = new ThingValueTrackingLogic();
		logicManager.addThingLogic(tptl);
		KeyReferenceTrackingLogic tpptl = new KeyReferenceTrackingLogic(tptl);
		logicManager.addThingLogic(tpptl);
		ReferenceTrackingLogic rtl = new ReferenceTrackingLogic();
		logicManager.addThingLogic(rtl);

		SelectionTrackingLogic stl = new SelectionTrackingLogic();
		logicManager.addThingLogic(stl);

		logicManager.addThingLogic(new MoveWithLogic(rtl));
		logicManager.addThingLogic(new AbstractMirrorValueLogic(rtl, tpptl));
		logicManager.addThingLogic(new MirrorAnchorPointLogic(rtl));
		logicManager.addThingLogic(new MirrorBoundingBoxLogic(rtl));
		logicManager.addThingLogic(new MirrorPointLogic(rtl));

		swtSynchLogic = new SWTBNASynchLogic(rtl);
		logicManager.addThingLogic(swtSynchLogic);

		swtLayoutLogic = new SWTBNALayoutLogic(swtSynchLogic);
		logicManager.addThingLogic(swtLayoutLogic);

		DragMoveEventsLogic dml = new DragMoveEventsLogic();
		logicManager.addThingLogic(dml);
		logicManager.addThingLogic(new ClickSelectionLogic(tttl));
		logicManager.addThingLogic(new MarqueeSelectionLogic(tttl));
		logicManager.addThingLogic(new DragMovableLogic(dml, tptl));
		logicManager.addThingLogic(new ReshapeRectangleLogic(stl, dml));
		logicManager.addThingLogic(new SplineReshapeHandleLogic(stl, dml));
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new MaintainAnchoredAssemblyOrientationLogic(rtl));
		logicManager.addThingLogic(new StandardCursorLogic());

		logicManager.addThingLogic(new ModelBoundsTrackingLogic(tttl));
		logicManager.addThingLogic(new WorldThingExternalEventsLogic(tttl));

	}
}
