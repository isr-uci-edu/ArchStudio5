package org.archstudio.aimlauncher.core;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.editors.AbstractArchstudioEditor;
import org.archstudio.editors.AbstractArchstudioOutlinePage;
import org.archstudio.ljm.LJMServer;
import org.archstudio.main.ArchStudio5Activator;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunch;
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
import org.osgi.framework.BundleException;

public class AIMLauncherEditor extends AbstractArchstudioEditor /* implements IMyxRuntimeRegistry */{

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

	long launchCount = 0;

	IAIM aim = null;

	LJMServer server;

	Map<String, LaunchData> launchDatas = Collections.synchronizedMap(new HashMap<String, LaunchData>());

	private class LaunchData {
		String name;
		ObjRef documentRootRef = null;
		ObjRef structureRef = null;
		ILaunch launch = null;
	}

	public AIMLauncherEditor() {
		super(AIMLauncherMyxComponent.class, AIMLauncherMyxComponent.EDITOR_NAME);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		setBannerInfo(((AIMLauncherMyxComponent) comp).getIcon(), "Architecture Instantiation Manager");
		setHasBanner(true);

		aim = ((AIMLauncherMyxComponent) comp).getAim();

		try {
			server = new LJMServer();
			server.deploy("AIMLauncher", this);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void dispose() {
		server.destroy();
		super.dispose();
	}

	@Override
	protected AbstractArchstudioOutlinePage createOutlinePage() {
		return new AIMLauncherOutlinePage(xarch, documentRootRef, resources);
	}

	@Override
	public void createEditorContents(final Composite parent) {
		try {
			Platform.getBundle(ArchStudio5Activator.PLUGIN_ID).start();
		}
		catch (BundleException be) {
			throw new RuntimeException(be);
		}

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
		String name = XadlUtils.getName(xarch, structureRef);
		try {
			aim.instantiate(name, xarch.getDocumentRootRef(structureRef), structureRef);
		}
		catch (ArchitectureInstantiationException aie) {
			aie.printStackTrace();
		}
		aim.begin(name);

		// for an example for creating java launch configurations, see:
		// http://www.eclipse.org/articles/Article-Java-launch/launching-java.html

		/*
		 * try{ final LaunchData launchData = new LaunchData(); launchData.name = "Launch:" + (++launchCount);
		 * launchData.documentRootRef = xarch.getDocumentRootRef(structureRef); launchData.structureRef = structureRef;
		 * launchDatas.put(launchData.name, launchData); URI xArchURI =
		 * URI.create(xarch.getXArchURI(launchData.documentRootRef)); URI xArchLocalURI =
		 * ResourcesPlugin.getWorkspace().getRoot().findMember(xArchURI.getPath()).getLocationURI(); IProject
		 * xArchProject = ResourcesPlugin.getWorkspace().getRoot().findMember(xArchURI.getPath()).getProject();
		 * ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager(); ILaunchConfigurationType jType =
		 * manager.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
		 * ILaunchConfigurationWorkingCopy workingConfig = jType.newInstance(null, "AIM Launcher");
		 * workingConfig.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, xArchProject.getName());
		 * workingConfig.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
		 * "edu.uci.isr.myx.fw.MyxRemoteRuntime");
		 * workingConfig.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
		 * "-registryHost localhost -registryPort " + server.getPort() +
		 * " -registryName AIMLauncher -runtimeHost localhost -runtimeName \"" + launchData.name + "\"");
		 * launchData.launch = workingConfig.launch(ILaunchManager.DEBUG_MODE, null, true, true); Thread t = new
		 * Thread(new Runnable(){ public void run(){ while(true){ try{ if(launchData.launch.isTerminated()) break;
		 * Thread.sleep(1000); } catch(InterruptedException e){ } } launchDatas.remove(launchData.name); } });
		 * t.setDaemon(true); t.start(); } catch(Exception e){ e.printStackTrace(); }
		 */
	}

	/*
	 * public void addLJMRuntime(String name, String host, int port){ addRuntime(name,
	 * (IMyxRuntime)LJMProxyFactory.createProxy(host, port, name, new Class[]{IMyxRuntime.class})); } public void
	 * addRuntime(String name, IMyxRuntime runtime){ final LaunchData launchData = launchDatas.get(name); if(launchData
	 * != null){ try{ aim.instantiate(name, xarch.getDocumentRootRef(launchData.structureRef), launchData.structureRef);
	 * aim.begin(name); } catch(Exception e){ e.printStackTrace(); try{ launchData.launch.terminate(); } catch(Exception
	 * e2){ e2.printStackTrace(); } } } } public void removeRuntime(String name){ final LaunchData launchData =
	 * launchDatas.get(name); if(launchData != null && launchData.runtime != null){ try{ aim.end(name);
	 * aim.destroy(name); } catch(Exception e){ e.printStackTrace(); try{ launchData.launch.terminate(); }
	 * catch(Exception e2){ e2.printStackTrace(); } } } }
	 */
}
