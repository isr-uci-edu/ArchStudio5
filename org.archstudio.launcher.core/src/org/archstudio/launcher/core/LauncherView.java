package org.archstudio.launcher.core;

import java.io.IOException;
import java.util.List;

import org.archstudio.eclipse.ui.EclipseUtils;
import org.archstudio.eclipse.ui.XadlEditorMatchingStrategy;
import org.archstudio.eclipse.ui.views.AbstractArchStudioView;
import org.archstudio.filemanager.core.NewFileWizard;
import org.archstudio.launcher.ILaunchData;
import org.archstudio.resources.ArchStudioCommonResources;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.ResourceTransfer;

public class LauncherView extends AbstractArchStudioView<LauncherMyxComponent> {

	protected static final String IMAGE_ARCHSTUDIO_LOGO = "launcher:logo/archstudio";
	protected static final String IMAGE_NEW_FILE_ICON = "launcher:icon/new-file";
	protected static final String IMAGE_ARCHSTUDIO_ICON = "launcher:icon/archstudio";
	protected static final String IMAGE_ISR_ICON = "launcher:icon/isr";

	private static final String NO_TOOL = "Point mouse cursor at tool for more detail.";

	protected IResources resources = null;
	protected List<ILaunchData> launchData = null;

	public LauncherView() {
		super(LauncherMyxComponent.class);
	}

	public void createPartControl(Composite parent) {
		resources = brick.getResources();
		launchData = brick.getLaunchData();

		try {
			// TODO: clean up this when resources are refactored
			resources.createImage(IMAGE_ARCHSTUDIO_LOGO,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("res/archstudio-logo.gif")));
			resources
					.createImage(IMAGE_NEW_FILE_ICON, SystemUtils.blt(ArchStudioCommonResources.class
							.getResourceAsStream("res/icon-new-file-32x32.gif")));
			resources.createImage(IMAGE_ARCHSTUDIO_ICON, SystemUtils.blt(ArchStudioCommonResources.class
					.getResourceAsStream("res/archstudio-icon-32x32.png")));
			resources.createImage(IMAGE_ISR_ICON,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("res/isr-icon-32x32.gif")));
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		GridLayout parentLayout = new GridLayout(1, false);
		parentLayout.marginHeight = 2;
		parentLayout.verticalSpacing = 2;
		parentLayout.marginRight = 10;
		parent.setLayout(parentLayout);
		parent.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));

		Composite cLabels = new Composite(parent, SWT.NONE);
		cLabels.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));
		GridLayout cLabelsLayout = new GridLayout(2, false);
		cLabelsLayout.marginHeight = 0;
		cLabels.setLayout(cLabelsLayout);
		cLabels.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		Label lLogo = new Label(cLabels, SWT.NONE);
		lLogo.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));
		lLogo.setImage(resources.getImage(IMAGE_ARCHSTUDIO_LOGO));

		Composite cTopButtons = new Composite(cLabels, SWT.NONE);
		cTopButtons.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));
		GridLayout cTopButtonsLayout = new GridLayout(3, true);
		cTopButtonsLayout.marginHeight = 0;
		cTopButtons.setLayout(cTopButtonsLayout);
		cTopButtons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL));

		Button bNewFile = new Button(cTopButtons, SWT.PUSH | SWT.FLAT);
		bNewFile.setImage(resources.getImage(IMAGE_NEW_FILE_ICON));
		bNewFile.setToolTipText("New Architecture Description");
		bNewFile.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_HORIZONTAL));
		bNewFile.setAlignment(SWT.CENTER);
		bNewFile.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				NewFileWizard.showWizard(getSite().getShell(), getSite().getWorkbenchWindow().getWorkbench());
			}
		});

		Button bVisitISRWebPage = new Button(cTopButtons, SWT.PUSH | SWT.FLAT);
		bVisitISRWebPage.setImage(resources.getImage(IMAGE_ISR_ICON));
		bVisitISRWebPage.setToolTipText("Visit ISR Website");
		bVisitISRWebPage.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_HORIZONTAL));
		bVisitISRWebPage.setAlignment(SWT.CENTER);
		bVisitISRWebPage.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				EclipseUtils.openExternalBrowser("http://www.isr.uci.edu/");
			}
		});

		Button bVisitWebPage = new Button(cTopButtons, SWT.PUSH | SWT.FLAT);
		bVisitWebPage.setImage(resources.getImage(IMAGE_ARCHSTUDIO_ICON));
		bVisitWebPage.setToolTipText("Visit ArchStudio Website");
		bVisitWebPage.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_HORIZONTAL));
		bVisitWebPage.setAlignment(SWT.CENTER);
		bVisitWebPage.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				EclipseUtils.openExternalBrowser("http://www.isr.uci.edu/projects/archstudio/");
			}
		});

		final Group mainGroup = new Group(parent, SWT.NONE);
		mainGroup.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));
		mainGroup.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		//mainGroup.setText("Available Tools");
		GridData mainGroupData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		mainGroupData.horizontalIndent = 20;
		mainGroupData.verticalIndent = 0;
		mainGroup.setLayoutData(mainGroupData);

		mainGroup.setLayout(new GridLayout(1, false));

		final Label lExplanation = new Label(mainGroup, SWT.NONE);
		lExplanation.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));
		lExplanation.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		lExplanation.setText(NO_TOOL);

		createToolBar(mainGroup, lExplanation);

		/*
		 * tb.addListener(SWT.MouseExit, new Listener(){ public void
		 * handleEvent(Event event){ lExplanation.setText(NO_TOOL);
		 * mainGroup.layout(new Control[]{lExplanation}); } });
		 * tb.addListener(SWT.MouseMove, new Listener(){ public void
		 * handleEvent(Event event){ ToolItem ti = tb.getItem(new Point(event.x,
		 * event.y)); if((ti != null) && (ti.getData() instanceof ILaunchData)){
		 * ILaunchData ld = (ILaunchData)ti.getData(); String text =
		 * ld.getDescription(); if(ld.getLaunchType() == ILaunchData.EDITOR){
		 * text += ".  Drop a file here to begin."; } else if(ld.getLaunchType()
		 * == ILaunchData.VIEW){ text += ".  Click this button to begin."; }
		 * lExplanation.setText(text); } else{ lExplanation.setText(NO_TOOL); }
		 * mainGroup.layout(new Control[]{lExplanation}); } }); for(int i = 0; i
		 * < tb.getItemCount(); i++){ ToolItem ti = tb.getItem(i);
		 * if(ti.getData() instanceof ILaunchData){ final ILaunchData ld =
		 * (ILaunchData)ti.getData(); ti.addListener(SWT.Selection, new
		 * Listener(){ public void handleEvent(Event event){
		 * if(ld.getLaunchType() == ILaunchData.EDITOR){ IResource[] res =
		 * EclipseUtils.selectResourcesToOpen(getSite().getShell(), SWT.SINGLE,
		 * "Choose file to open", new String[]{"xml"}); if((res != null) &&
		 * (res.length > 0)){ openEditor(ld, res[0]); } return; } else{
		 * //TODO:Implement view selection } }; }); } }
		 */

		//setupDrop(tb);
	}

	protected void createToolBar(Composite parent, Label lDetail) {
		Composite cBar = new Composite(parent, SWT.NONE);
		GridLayout cBarLayout = new GridLayout(launchData.size(), false);
		cBarLayout.marginHeight = 0;
		cBarLayout.marginWidth = 0;
		cBarLayout.horizontalSpacing = 2;
		cBarLayout.verticalSpacing = 0;
		cBar.setLayout(cBarLayout);

		cBar.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));

		for (ILaunchData launchDataItem : launchData) {
			createToolItem(cBar, launchDataItem, lDetail);
		}
	}

	protected void createToolItem(Composite parent, final ILaunchData launchData, final Label lDetail) {
		Composite cItem = new Composite(parent, SWT.NONE);
		cItem.setLayout(new GridLayout(1, false));
		cItem.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));

		Button bItem = new Button(cItem, SWT.PUSH);
		bItem.setImage(launchData.getIcon());
		bItem.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL));
		bItem.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));

		Label lItem = new Label(cItem, SWT.NONE);
		lItem.setText(launchData.getName());
		lItem.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		lItem.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL
				| GridData.VERTICAL_ALIGN_CENTER));
		lItem.setBackground(resources.getColor(IResources.COLOR_ARCHSTUDIO));

		final Control[] controls = new Control[] { lDetail };

		bItem.addListener(SWT.MouseExit, new Listener() {

			public void handleEvent(Event event) {
				lDetail.setText(NO_TOOL);
				lDetail.getParent().layout(controls);
			}
		});

		bItem.addListener(SWT.MouseMove, new Listener() {

			public void handleEvent(Event event) {
				String text = launchData.getDescription();
				if (launchData.getLaunchType().equals(ILaunchData.LaunchType.EDITOR)) {
					text += ".  Drop a file here to begin.";
				}
				else if (launchData.getLaunchType().equals(ILaunchData.LaunchType.VIEW)) {
					text += ".  Click this button to begin.";
				}
				lDetail.setText(text);
				lDetail.getParent().layout(controls);
			}
		});

		bItem.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				if (launchData.getLaunchType().equals(ILaunchData.LaunchType.EDITOR)) {
					IResource[] res = EclipseUtils.selectResourcesToOpen(getSite().getShell(), SWT.SINGLE,
							"Choose file to open", new String[] { "xml" });
					if (res != null && res.length > 0) {
						openEditor(launchData, res[0]);
					}
					return;
				}
				else {
					//TODO: Implement view selection
				}
			}
		});

		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(bItem, operations);

		final ResourceTransfer resourceTransfer = ResourceTransfer.getInstance();
		Transfer[] types = new Transfer[] { resourceTransfer };
		target.setTransfer(types);

		target.addDropListener(new DropTargetListener() {

			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					}
					else {
						event.detail = DND.DROP_NONE;
					}
				}
				for (TransferData dataType : event.dataTypes) {
					if (resourceTransfer.isSupportedType(dataType)) {
						event.currentDataType = dataType;
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (resourceTransfer.isSupportedType(event.currentDataType)) {
					if (launchData.getLaunchType().equals(ILaunchData.LaunchType.EDITOR)) {
						event.detail = DND.DROP_COPY;
						return;
					}
				}
				event.detail = DND.DROP_NONE;
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (resourceTransfer.isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (resourceTransfer.isSupportedType(event.currentDataType)) {
					IResource[] resources = (IResource[]) event.data;
					if (!isValidDrop(resources)) {
						MessageDialog.openError(getSite().getShell(), "Error",
								"Invalid input: drop a single valid architecture description to begin.");
					}
					else {
						if (launchData.getLaunchType().equals(ILaunchData.LaunchType.EDITOR)) {
							openEditor(launchData, resources[0]);
							return;
						}
					}
				}
			}

			private boolean isValidDrop(IResource[] resources) {
				if (resources.length == 1) {
					IResource rsrc = resources[0];
					IFileEditorInput fei = EclipseUtils.getFileEditorInput(rsrc.getFullPath());
					if (fei != null) {
						XadlEditorMatchingStrategy xadlChecker = new XadlEditorMatchingStrategy();
						return xadlChecker.matches(null, fei);
					}
				}
				return false;
			}
		});
	}

	protected void openEditor(ILaunchData ld, IResource resource) {
		if (resource instanceof IFile) {
			EclipseUtils.openEditor(ld.getEclipseID(), (IFile) resource);
		}
	}

	public void setFocus() {
	}
}
