package org.archstudio.archlight.core;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.archlight.ArchlightDocTest;
import org.archstudio.archlight.ArchlightTest;
import org.archstudio.archlight.IArchlightTool;
import org.archstudio.eclipse.ui.editors.AbstractArchStudioEditor;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.DropdownSelectionListener;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;

public class ArchlightEditor extends AbstractArchStudioEditor<ArchlightMyxComponent> {
	protected IArchlightTestADT tests = null;
	protected IArchlightTool tools = null;

	public ArchlightEditor() {
		super(ArchlightMyxComponent.class, ArchlightMyxComponent.EDITOR_NAME);
		tests = brick.getTests();
		tools = brick.getTools();

		ArchlightUtils.initResources(resources);

		setBannerInfo(brick.getIcon(), "Architecture Analysis Framework");
		setHasBanner(true);
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		//setSite(site);
		//setInput(input);

		setupToolbar(site);
	}

	protected AbstractArchStudioOutlinePage createOutlinePage() {
		return new ArchlightOutlinePage(tests, xarch, getDocumentRootRef(), resources);
	}

	protected void setupToolbar(IEditorSite site) {
		IActionBars bars = site.getActionBars();
		IToolBarManager manager = bars.getToolBarManager();
		IAction[] actions = getToolbarActions();
		for (IAction action : actions) {
			manager.add(action);
		}
	}

	public void createEditorContents(Composite c) {
		Object[] selectedNodes = null;
		if (outlinePage != null) {
			selectedNodes = outlinePage.getSelectedObjects();
		}

		Label sep1 = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
		sep1.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		Composite cButtons = new Composite(c, SWT.NONE);
		cButtons.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayout cButtonsGridLayout = new GridLayout(4, false);
		cButtonsGridLayout.marginTop = 5;
		cButtonsGridLayout.marginBottom = 5;
		cButtonsGridLayout.marginLeft = 5;
		cButtonsGridLayout.marginRight = 5;
		cButtons.setLayout(cButtonsGridLayout);

		Button bRunTests = new Button(cButtons, SWT.NONE);
		bRunTests.setText("Run Tests");
		bRunTests.setImage(resources.getImage(ArchlightUtils.IMAGE_RUN_TESTS));
		bRunTests.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				runTests();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				runTests();
			}
		});

		Label lRunTests = new Label(cButtons, SWT.LEFT);
		lRunTests.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		lRunTests.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
		lRunTests.setText("Click this button to run all enabled tests.");

		Button bReloadTests = new Button(cButtons, SWT.NONE);
		bReloadTests.setText("Reload Tests");
		bReloadTests.setImage(resources.getImage(ArchlightUtils.IMAGE_RELOAD_TESTS));
		bReloadTests.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				reloadTests();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
				reloadTests();
			}
		});

		GridData separatorData = new GridData();
		separatorData.horizontalIndent = 15;
		bReloadTests.setLayoutData(separatorData);

		Label lReloadTests = new Label(cButtons, SWT.LEFT);
		lReloadTests.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		lReloadTests.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
		lReloadTests.setText("Click this button to reload all tests.");

		Label sep2 = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
		sep2.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		if (selectedNodes == null || selectedNodes.length == 0) {
			Label lNothingSelected = new Label(c, SWT.LEFT);
			lNothingSelected.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			lNothingSelected.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
			lNothingSelected.setText("Select one or more elements in the outline view.");
		}
		else {
			for (final Object node : selectedNodes) {
				if (node instanceof ObjRef) {
					Label lElement = new Label(c, SWT.LEFT);
					lElement.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
					lElement.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
					lElement.setText("Select a sub-node of the document for more detail.");
				}
				else if (node instanceof FolderNode || node instanceof ArchlightTest) {
					boolean isFolder = node instanceof FolderNode;

					Label lElement = new Label(c, SWT.LEFT);
					lElement.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
					lElement.setFont(resources.getPlatformFont(IResources.PLATFORM_HEADER_FONT_ID));
					if (isFolder) {
						lElement.setText("Test Folder: " + ((FolderNode) node).getLastSegment());
					}
					else {
						lElement.setText("Test: "
								+ ArchlightTest.getLastCategoryPathComponent(((ArchlightTest) node).getCategory()));
					}

					Group g = new Group(c, SWT.NONE);
					g.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
					GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
					gd.verticalIndent = 5;
					gd.horizontalIndent = 10;
					g.setLayoutData(gd);

					GridLayout groupGridLayout = new GridLayout(1, true);
					groupGridLayout.marginTop = 5;
					groupGridLayout.marginBottom = 5;
					groupGridLayout.marginLeft = 5;
					groupGridLayout.marginLeft = 5;
					groupGridLayout.verticalSpacing = 5;
					g.setLayout(groupGridLayout);

					g.setText("Manage Test Folder");

					Label lElementDesc = new Label(g, SWT.LEFT);
					lElementDesc.setBackground(g.getDisplay().getSystemColor(SWT.COLOR_WHITE));
					lElementDesc.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
					if (isFolder) {
						lElementDesc
								.setText("This is a test folder.  It is a container for other tests.  You can use it to enable or disable tests as a group.");
					}
					else {
						lElementDesc.setText(((ArchlightTest) node).getLongDescription());
					}

					ToolBar toolBar = new ToolBar(g, SWT.HORIZONTAL | SWT.RIGHT);
					toolBar.setBackground(g.getDisplay().getSystemColor(SWT.COLOR_WHITE));

					String dropDownImageID = isFolder ? ISharedImages.IMG_OBJ_FOLDER : ISharedImages.IMG_OBJ_FILE;
					String dropDownText = isFolder ? "Change Test States" : "Change Test State";
					ToolItem dropDownButton = createToolItem(toolBar, SWT.DROP_DOWN, dropDownText,
							resources.getPlatformImage(dropDownImageID), null, dropDownText);
					dropDownButton.addSelectionListener(new DropdownSelectionListener(dropDownButton) {

						public void fillDropdownMenu(IMenuManager menuMgr) {
							List<? extends IAction> actions = ArchlightUtils.createTestMenuActions(xarch,
									documentRootRef, tests.getAllTests(), resources, node);
							if (actions.isEmpty()) {
								Action noAction = new Action("[No Actions]") {

									public void run() {
									}
								};
								noAction.setEnabled(false);
								menuMgr.add(noAction);
							}
							for (IAction action : actions) {
								menuMgr.add(action);
							}
						}
					});
				}

				Label sep = new Label(c, SWT.SEPARATOR | SWT.HORIZONTAL);
				sep.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
			}
		}
	}

	private ToolItem createToolItem(ToolBar parent, int type, String text, Image image, Image hotImage,
			String toolTipText) {
		ToolItem item = new ToolItem(parent, type);
		item.setText(text);
		item.setImage(image);
		item.setHotImage(hotImage);
		item.setToolTipText(toolTipText);
		return item;
	}

	public void setFocus() {
		// parent.getChildren()[0].setFocus();
	}

	public IAction[] getToolbarActions() {
		Action runTests = new Action("Run Tests", IAction.AS_PUSH_BUTTON) {

			public void run() {
				runTests();
			};
		};
		runTests.setImageDescriptor(resources.getImageDescriptor(ArchlightUtils.IMAGE_RUN_TESTS));
		runTests.setToolTipText("Run Tests");

		Action reloadTests = new Action("Reload Tests", IAction.AS_PUSH_BUTTON) {

			public void run() {
				reloadTests();
			};
		};
		reloadTests.setImageDescriptor(resources.getImageDescriptor(ArchlightUtils.IMAGE_RELOAD_TESTS));
		reloadTests.setToolTipText("Reload Tests");

		return new IAction[] { runTests, reloadTests };
	}

	protected void reloadTests() {
		tests.removeTests(tests.getAllTests());
		tools.reloadTests();
	}

	protected void runTests() {
		List<? extends ArchlightDocTest> docTests = ArchlightUtils.loadDocTests(xarch, documentRootRef);
		List<String> testUIDlist = new ArrayList<String>(docTests.size());
		for (ArchlightDocTest docTest : docTests) {
			if (docTest.isEnabled()) {
				testUIDlist.add(docTest.getTestUID());
			}
		}
		tools.runTests(documentRootRef, testUIDlist);

		try {
			getEditorSite().getPage().showView("org.eclipse.ui.views.ProblemView");
		}
		catch (PartInitException pe) {
		}
	}
}
