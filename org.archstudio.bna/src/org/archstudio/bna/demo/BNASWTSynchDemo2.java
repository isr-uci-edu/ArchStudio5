package org.archstudio.bna.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
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
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSWTLayout;
import org.archstudio.bna.facets.IHasSWTLayoutData;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.facets.IHasStickyEndpoints;
import org.archstudio.bna.facets.IHasWorld;
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
import org.archstudio.bna.logics.swt.SWTBNALayoutLogic;
import org.archstudio.bna.logics.swt.SWTBNASynchLogic;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.logics.tracking.StickyBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class BNASWTSynchDemo2 {

	SWTBNASynchLogic swtSynchLogic;

	SWTBNALayoutLogic swtLayoutLogic;

	Composite layoutComposite;

	public static void main(String args[]) {
		BNASWTSynchDemo2 demo = new BNASWTSynchDemo2();
	}

	public BNASWTSynchDemo2() {

		// Create Core {SWT} components
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		// Left Hand Side {SWT} Composite
		// layoutComposite = new Composite(shell, SWT.BORDER);

		// Right Hand Side {BNA} Composite
		// Create Core {BNA} components
		final IBNAModel m = new DefaultBNAModel();
		IBNAWorld world1 = new DefaultBNAWorld("bna", m);
		setupWorld(world1);
		IBNAView view1 = new DefaultBNAView(null, world1, new DefaultCoordinateMapper());

		final BNAComposite bnaComposite = new BNAComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL | SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED, view1);
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

		BoxAssembly box = BoxAssembly.create(m, null);
		box.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20,
		        100, 100);
		box.getBoxGlassThing().setProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME, new GridLayout(1, false));

		// class BNA
		BoxAssembly classBox = BoxAssembly.create(m, box.getBoxThing());
		classBox.getBoxGlassThing().setBoundingBox((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 20,
		        (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 20, 100, 100);
		classBox.getBoxGlassThing().setProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME, new GridLayout(1, false));
		classBox.getBoxGlassThing().setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME, new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		classBox.getBoxGlassThing().setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME, box.getBoxGlassThing().getID());

		// logic's actions
		//		Composite classBoxSWT = new Composite(composite, SWT.NONE);
		//		classBoxSWT.setBackground(classBoxSWT.getDisplay().getSystemColor(SWT.COLOR_RED));
		//		classBoxSWT.setLayout((Layout)classBox.getBoxGlassThing().getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME));
		//		swtBNALogic.addBNAToSWTMapping(classBox.getBoxGlassThing().getID(), classBoxSWT);

		// class title BNA
		BoxAssembly classTitle = BoxAssembly.create(m, classBox.getBoxThing());
		classTitle.getBoxedLabelThing().setText("class");
		classTitle.getBoxGlassThing().setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME, new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		classTitle.getBoxGlassThing().setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME, classBox.getBoxGlassThing().getID());

		// logic's actions
		//		Button classTitleSWT = new Button(classBoxSWT, SWT.NONE);
		//		classTitleSWT.setText(classTitle.getText());
		//		classTitleSWT.setVisible(true);
		//		classTitleSWT.setLayoutData(classTitle.getProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME));
		//		swtBNALogic.addSWTToBNAMapping(classTitleSWT, classTitle.getID());

		BoxAssembly[] classOperations = new BoxAssembly[3];
		for (int i = 0; i < classOperations.length; i++) {
			// class operation BNA
			BoxAssembly classOperation = BoxAssembly.create(m, classBox.getBoxThing());
			classOperation.getBoxedLabelThing().setText("method" + i);
			classOperations[i] = classOperation;
			classOperation.getBoxGlassThing().setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME, new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			classOperation.getBoxGlassThing().setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME, classBox.getBoxGlassThing().getID());

			// logic's actions
			//			Button classOperationSWT = new Button(classBoxSWT, SWT.NONE);
			//			classOperationSWT.setText(classOperation.getText());
			//			classOperationSWT.setVisible(true);
			//			classOperationSWT.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));
			//			swtBNALogic.addSWTToBNAMapping(classOperationSWT, classOperation.getID());
		}

		BoxAssembly[] classFields = new BoxAssembly[3];
		for (int i = 0; i < classFields.length; i++) {
			// class field BNA
			BoxAssembly classField = BoxAssembly.create(m, classBox.getBoxThing());
			classFields[i] = classField;
			classField.getBoxedLabelThing().setText("field " + i);
			classField.getBoxGlassThing().setProperty(IHasSWTLayoutData.SWT_LAYOUT_DATA_PROPERTY_NAME, new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			classField.getBoxGlassThing().setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME, classBox.getBoxGlassThing().getID());

			// logic's actions
			//			Button classFieldSWT = new Button(classBoxSWT, SWT.NONE);
			//			classFieldSWT.setText(classField.getText());
			//			classFieldSWT.setVisible(true);
			//			classFieldSWT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 3, 1));
			//			swtBNALogic.addSWTToBNAMapping(classFieldSWT, classField.getID());
		}

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		System.exit(0);
	}

	private void setupWorld(IBNAWorld bnaWorld) {

		// Add all associated logics

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

		swtSynchLogic = new SWTBNASynchLogic(trtl);
		logicManager.addThingLogic(swtSynchLogic);

		swtLayoutLogic = new SWTBNALayoutLogic(swtSynchLogic);
		logicManager.addThingLogic(swtLayoutLogic);

		DragMovableLogic dml = new DragMovableLogic();
		logicManager.addThingLogic(dml);
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

}
