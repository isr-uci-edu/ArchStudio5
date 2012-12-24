package org.archstudio.archlight.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.archstudio.archlight.ArchlightDocTest;
import org.archstudio.archlight.ArchlightTest;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

import com.google.common.collect.Iterables;

public class ArchlightUtils {

	public static final int APPLIED = 1;
	public static final int DISABLED = 2;
	public static final int NOT_APPLIED = 3;

	public static final String IMAGE_OVERLAY_CHECKBOX_CHECKED = "archlight:checkbox/checked";
	public static final String IMAGE_OVERLAY_STOPSIGN = "archlight:stopsign";
	public static final String IMAGE_OVERLAY_CHECKBOX_UNCHECKED = "archlight:checkbox/unchecked";

	public static final String IMAGE_FOLDER_APPLIED = "archlight:folder/enabled";
	public static final String IMAGE_FOLDER_DISABLED = "archlight:folder/disabled";
	public static final String IMAGE_FOLDER_NOTAPPLIED = "archlight:folder/notapplied";

	public static final String IMAGE_TEST_APPLIED = "archlight:test/enabled";
	public static final String IMAGE_TEST_DISABLED = "archlight:test/disabled";
	public static final String IMAGE_TEST_NOTAPPLIED = "archlight:test/notapplied";

	public static final String IMAGE_RUN_TESTS = "archlight:runtests";
	public static final String IMAGE_RELOAD_TESTS = "archlight:refreshtests";

	private ArchlightUtils() {
	}

	public static void initResources(IResources resources) {
		try {
			resources.createImage(IMAGE_RUN_TESTS,
					SystemUtils.blt(ArchlightUtils.class.getResourceAsStream("res/icon-go.gif")));
			resources.createImage(IMAGE_RELOAD_TESTS,
					SystemUtils.blt(ArchlightUtils.class.getResourceAsStream("res/icon-refresh.gif")));

			resources.createImage(IMAGE_OVERLAY_CHECKBOX_CHECKED,
					SystemUtils.blt(ArchlightUtils.class.getResourceAsStream("res/decorator-checkbox-checked.gif")));
			resources.createImage(IMAGE_OVERLAY_CHECKBOX_UNCHECKED,
					SystemUtils.blt(ArchlightUtils.class.getResourceAsStream("res/decorator-checkbox-unchecked.gif")));
			resources.createImage(IMAGE_OVERLAY_STOPSIGN,
					SystemUtils.blt(ArchlightUtils.class.getResourceAsStream("res/decorator-stopsign.gif")));

			resources.createOverlayImage(IMAGE_FOLDER_APPLIED,
					resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER),
					new Image[] { resources.getImage(IMAGE_OVERLAY_CHECKBOX_CHECKED) },
					new int[] { IResources.BOTTOM_RIGHT });

			resources.createOverlayImage(IMAGE_FOLDER_DISABLED,
					resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER),
					new Image[] { resources.getImage(IMAGE_OVERLAY_STOPSIGN) }, new int[] { IResources.BOTTOM_RIGHT });

			resources.createOverlayImage(IMAGE_FOLDER_NOTAPPLIED,
					resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER),
					new Image[] { resources.getImage(IMAGE_OVERLAY_CHECKBOX_UNCHECKED) },
					new int[] { IResources.BOTTOM_RIGHT });

			resources.createOverlayImage(IMAGE_TEST_APPLIED, resources.getPlatformImage(ISharedImages.IMG_OBJ_FILE),
					new Image[] { resources.getImage(IMAGE_OVERLAY_CHECKBOX_CHECKED) },
					new int[] { IResources.BOTTOM_RIGHT });

			resources.createOverlayImage(IMAGE_TEST_DISABLED, resources.getPlatformImage(ISharedImages.IMG_OBJ_FILE),
					new Image[] { resources.getImage(IMAGE_OVERLAY_STOPSIGN) }, new int[] { IResources.BOTTOM_RIGHT });

			resources.createOverlayImage(IMAGE_TEST_NOTAPPLIED, resources.getPlatformImage(ISharedImages.IMG_OBJ_FILE),
					new Image[] { resources.getImage(IMAGE_OVERLAY_CHECKBOX_UNCHECKED) },
					new int[] { IResources.BOTTOM_RIGHT });
		}
		catch (IOException ioe) {
			throw new RuntimeException("This shouldn't happen.");
		}
	}

	public static List<ArchlightDocTest> loadDocTests(IXArchADT xarch, ObjRef docRootRef) {
		List<ArchlightDocTest> docTestList = new ArrayList<ArchlightDocTest>();
		try {
			ObjRef xADLRef = (ObjRef) xarch.get(docRootRef, "xADL");
			if (xADLRef != null) {
				List<ObjRef> archlightRefs = XadlUtils.getAllSubstitutionGroupElementsByTag(xarch, xADLRef,
						"topLevelElement", "archlight");
				if (archlightRefs.size() > 0) {
					ObjRef archlightRef = archlightRefs.get(0);
					for (ObjRef testRef : Iterables.filter(xarch.getAll(archlightRef, "test"), ObjRef.class)) {
						String testUID = XadlUtils.getID(xarch, testRef);
						String testName = XadlUtils.getName(xarch, testRef);
						if (testName == null) {
							testName = "[no data]";
						}
						Boolean enabled = (Boolean) xarch.get(testRef, "enabled");
						if (enabled == null) {
							enabled = Boolean.FALSE;
						}
						ArchlightDocTest newDocTest = new ArchlightDocTest(testUID, testName, enabled.booleanValue());
						docTestList.add(newDocTest);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableList(docTestList);
	}

	public static void makeDocTestApplied(IXArchADT xarch, ObjRef documentRootRef, ArchlightTest testToUpdate,
			boolean isEnabled) {
		ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
		if (xADLRef != null) {
			ObjRef archlightRef = null;

			List<ObjRef> archlightRefs = XadlUtils.getAllSubstitutionGroupElementsByTag(xarch, xADLRef,
					"topLevelElement", "archlight");
			if (archlightRefs.size() > 0) {
				archlightRef = archlightRefs.get(0);
			}

			if (archlightRef != null) {
				for (ObjRef testRef : Iterables.filter(xarch.getAll(archlightRef, "test"), ObjRef.class)) {
					String testUID = (String) xarch.get(testRef, "id");
					if (testUID != null && testUID.equals(testToUpdate.getUID())) {
						//Found it.  Just update with the new status.
						xarch.set(testRef, "enabled", isEnabled);
						return;
					}
				}
			}
			//We didn't find it.  Create ancestor elements if necessary.
			if (archlightRef == null) {
				archlightRef = XadlUtils.create(xarch, Archlight_3_0Package.Literals.ARCHLIGHT);
				xarch.add(xADLRef, "topLevelElement", archlightRef);
			}
			//Let's create the test.
			ObjRef testRef = XadlUtils.create(xarch, Archlight_3_0Package.Literals.TEST);

			xarch.set(testRef, "id", testToUpdate.getUID());
			String nameString = "Tool: " + testToUpdate.getToolID() + "; Category: " + testToUpdate.getCategory();
			XadlUtils.setName(xarch, testRef, nameString);
			try {
				xarch.set(testRef, "enabled", isEnabled);
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
			xarch.add(archlightRef, "test", testRef);
		}
	}

	public static void makeDocTestNotApplied(IXArchADT xarch, ObjRef documentRootRef, ArchlightTest testToUpdate) {
		ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
		if (xADLRef != null) {
			ObjRef archlightRef = null;

			List<ObjRef> archlightRefs = XadlUtils.getAllSubstitutionGroupElementsByTag(xarch, xADLRef,
					"topLevelElement", "archlight");
			if (archlightRefs.size() > 0) {
				archlightRef = archlightRefs.get(0);
			}

			if (archlightRef != null) {
				for (ObjRef testRef : Iterables.filter(xarch.getAll(archlightRef, "test"), ObjRef.class)) {
					String testUID = (String) xarch.get(testRef, "id");
					if (testUID != null && testUID.equals(testToUpdate.getUID())) {
						//Found it.  Remove it
						xarch.remove(archlightRef, "test", testRef);
						return;
					}
				}
			}
		}
	}

	public static void changeTestState(IXArchADT xarch, ObjRef xArchRef, Collection<? extends ArchlightTest> tests,
			Object element, int newState) {
		List<ArchlightTest> testsToChange = new ArrayList<ArchlightTest>();

		if (element instanceof ObjRef) {
			testsToChange.addAll(tests);
		}
		else if (element instanceof FolderNode) {
			String[] basePathSegments = ((FolderNode) element).getPathSegments();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < basePathSegments.length; i++) {
				sb.append(basePathSegments[i]);
				if (i != basePathSegments.length - 1) {
					sb.append("/");
				}
			}
			String basePath = sb.toString();
			for (ArchlightTest test : tests) {
				if (test.getCategory().startsWith(basePath)) {
					testsToChange.add(test);
				}
			}
		}
		else if (element instanceof ArchlightTest) {
			testsToChange.add((ArchlightTest) element);
		}

		for (ArchlightTest test : testsToChange) {
			if (newState == ArchlightUtils.APPLIED || newState == ArchlightUtils.DISABLED) {
				ArchlightUtils.makeDocTestApplied(xarch, xArchRef, test, newState == ArchlightUtils.APPLIED);
			}
			else if (newState == ArchlightUtils.NOT_APPLIED) {
				ArchlightUtils.makeDocTestNotApplied(xarch, xArchRef, test);
			}
		}
	}

	public static List<? extends IAction> createTestMenuActions(IXArchADT xarch, ObjRef documentRootRef,
			Collection<? extends ArchlightTest> tests, IResources resources, Object element) {
		final IXArchADT fxarch = xarch;
		final ObjRef fdocumentRootRef = documentRootRef;
		final Collection<? extends ArchlightTest> ftests = tests;

		List<Action> actions = new ArrayList<Action>();
		boolean multi = !(element instanceof ArchlightTest);

		String kindOfThing = multi ? "All Tests" : "Test";
		Image applyIcon = resources.getImage(multi ? IMAGE_FOLDER_APPLIED : IMAGE_TEST_APPLIED);
		Image disableIcon = resources.getImage(multi ? IMAGE_FOLDER_DISABLED : IMAGE_TEST_DISABLED);
		Image unapplyIcon = resources.getImage(multi ? IMAGE_FOLDER_NOTAPPLIED : IMAGE_TEST_NOTAPPLIED);

		final Object felement = element;

		Action applyAction = new Action("Make " + kindOfThing + " Applied/Enabled",
				ImageDescriptor.createFromImage(applyIcon)) {

			public void run() {
				changeTestState(fxarch, fdocumentRootRef, ftests, felement, APPLIED);
			}
		};
		actions.add(applyAction);

		Action disableAction = new Action("Make " + kindOfThing + " Applied/Disabled",
				ImageDescriptor.createFromImage(disableIcon)) {

			public void run() {
				changeTestState(fxarch, fdocumentRootRef, ftests, felement, DISABLED);
			}
		};
		actions.add(disableAction);

		Action unapplyAction = new Action("Make " + kindOfThing + " Unapplied",
				ImageDescriptor.createFromImage(unapplyIcon)) {

			public void run() {
				changeTestState(fxarch, fdocumentRootRef, ftests, felement, NOT_APPLIED);
			}
		};
		actions.add(unapplyAction);

		return Collections.unmodifiableList(actions);
	}

}
