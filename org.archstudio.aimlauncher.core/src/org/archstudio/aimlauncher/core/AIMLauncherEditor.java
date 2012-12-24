package org.archstudio.aimlauncher.core;

import java.util.Map;

import org.archstudio.eclipse.ui.editors.AbstractArchStudioEditor;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.google.common.collect.Maps;

public class AIMLauncherEditor extends AbstractArchStudioEditor<AIMLauncherMyxComponent> // implements IMyxRuntimeRegistry
{

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override
		public String getColumnText(Object obj, int index) {
			return ((String[]) obj)[index];
		}

		@Override
		public Image getColumnImage(Object obj, int index) {
			return null;
		}

		@Override
		public Image getImage(Object obj) {
			return null;
		}
	}

	class ViewCellModifier implements ICellModifier {
		protected ObjRef ref;

		public ViewCellModifier(ObjRef ref) {
			this.ref = ref;
		}

		@Override
		public boolean canModify(Object element, String property) {
			return true;
		}

		@Override
		public Object getValue(Object element, String property) {
			if (element == null) {
				return "";
			}
			if (element instanceof String[]) {
				String[] elts = (String[]) element;
				if (elts[1] == null) {
					return "";
				}
				return elts[1].toString();
			}
			return null;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			// SWT bug workaround
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			if (element instanceof String[]) {
				String[] elts = (String[]) element;
				String propertyName = elts[0].toString();

				String oldValue = null;
				if (elts[1] != null) {
					oldValue = elts[1].toString();
				}

				String newValue = null;
				if (value != null) {
					newValue = value.toString();
				}

				if (oldValue == null && newValue == null) {
					// Do nothing
				}
				else if (oldValue != null && newValue == null) {
					xarch.clear(ref, propertyName);
				}
				else if (oldValue == null && newValue != null) {
					xarch.set(ref, propertyName, newValue);
				}
				else {
					// Both non-null:
					if (!oldValue.equals(newValue)) {
						xarch.set(ref, propertyName, newValue);
					}
				}
			}
		}
	}

	public AIMLauncherEditor() {
		super(AIMLauncherMyxComponent.class, AIMLauncherMyxComponent.EDITOR_NAME);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		setBannerInfo(brick.getIcon(), "Architecture Instantiation Manager");
		setHasBanner(true);
	}

	@Override
	protected AbstractArchStudioOutlinePage createOutlinePage() {
		return new AIMLauncherOutlinePage(xarch, documentRootRef, resources);
	}

	@Override
	public void createEditorContents(final Composite parent) {
		parent.setLayout(new GridLayout(1, true));
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		ObjRef[] selectedRefs = null;
		if (outlinePage != null) {
			selectedRefs = ((AIMLauncherOutlinePage) outlinePage).getSelectedRefs();
		}

		if (selectedRefs == null || selectedRefs.length == 0) {
			Label lNothingSelected = new Label(parent, SWT.LEFT);
			lNothingSelected.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			lNothingSelected.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
			lNothingSelected.setText("Select one or more elements in the outline view.");
		}
		else {
			ObjRef selectedRef = selectedRefs[0];
			String tagsPath = xarch.getTagsOnlyPathString(selectedRef);
			if (selectedRefs.length == 1 && (tagsPath.equals("") || tagsPath.equals("xADL"))) {
				// It's a document root element
				Label lNothingSelected = new Label(parent, SWT.LEFT);
				lNothingSelected.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
				lNothingSelected.setFont(resources.getPlatformFont(IResources.PLATFORM_DEFAULT_FONT_ID));
				lNothingSelected.setText("Select one or more structures in the outline view.");
			}
			else {
				for (final ObjRef structureRef : selectedRefs) {
					Label lElement = new Label(parent, SWT.LEFT);
					lElement.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
					lElement.setFont(resources.getPlatformFont(IResources.PLATFORM_HEADER_FONT_ID));
					lElement.setText(XadlUtils.getName(xarch, structureRef));

					final Button bInstantiate = new Button(parent, SWT.NONE);
					bInstantiate.setImage(resources.getImage(AIMLauncherMyxComponent.IMAGE_AIMLAUNCHER_GO_ICON));
					bInstantiate.setText("Instantiate");
					bInstantiate.addSelectionListener(new SelectionAdapter() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							bInstantiate.setEnabled(false);
							bInstantiate.setText("Instantiating...");
							parent.layout(true, true);
							Thread t = new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										launchArchitecture(structureRef);
									}
									finally {
										SWTWidgetUtils.async(parent, new Runnable() {

											@Override
											public void run() {
												bInstantiate.setEnabled(true);
												bInstantiate.setText("Instantiate");
												parent.layout(true, true);
											}
										});
									}
								}
							});
							t.start();
						}
					});

					break;
				}
			}
		}
	}

	protected void launchArchitecture(final ObjRef structureRef) {

		try {
			// create a launch configuration and run it in order to support debugging
			// for an example for creating java launch configurations, see:
			// http://www.eclipse.org/articles/Article-Java-launch/launching-java.html

			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType jType = manager.getLaunchConfigurationType("org.eclipse.pde.ui.RuntimeWorkbench");
			ILaunchConfigurationWorkingCopy workingConfig = jType.newInstance(null, "AIM Launcher");
			workingConfig.setAttribute("application", AIMLauncherApp.class.getName());
			workingConfig.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, "-consoleLog -debug");

			// we pass the architecture and top level element as an environment variables
			Map<String, String> env = Maps.newHashMap();
			env.put("architecture", new String(xarch.serialize(xarch.getURI(structureRef))));
			env.put("topLevelElement", (String) xarch.get(structureRef, "name"));
			workingConfig.setAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES, env);

			workingConfig.launch(ILaunchManager.DEBUG_MODE, null, true, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
