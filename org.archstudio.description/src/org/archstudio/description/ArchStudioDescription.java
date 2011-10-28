package org.archstudio.description;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.implementation_3_0.ImplementationExtension;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Factory;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Factory;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Factory;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.myxgen_3_0.MyxGen;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Factory;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package;
import org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Factory;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Package;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.archstudio.xarchadt.core.XArchADTProxy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ArchStudioDescription {

	public static void main(String[] args) {
		new ArchStudioDescription().execute();
	}

	{
		// Note: referencing an EPackage initializes it and its corresponding EFactory 
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;
		p = Domain_3_0Package.eINSTANCE;
	}

	private final IXArchADT xarch = new XArchADTImpl();

	protected final Implementation_3_0Factory implFactory = XArchADTProxy.proxy(xarch,
			Implementation_3_0Package.eINSTANCE.getNsURI());
	protected final Javaimplementation_3_0Factory javaFactory = XArchADTProxy.proxy(xarch,
			Javaimplementation_3_0Package.eINSTANCE.getNsURI());
	protected final Lookupimplementation_3_0Factory lookupFactory = XArchADTProxy.proxy(xarch,
			Lookupimplementation_3_0Package.eINSTANCE.getNsURI());
	protected final Myxgen_3_0Factory myxgenFactory = XArchADTProxy
			.proxy(xarch, Myxgen_3_0Package.eINSTANCE.getNsURI());
	protected final Osgiimplementation_3_0Factory osgiFactory = XArchADTProxy.proxy(xarch,
			Osgiimplementation_3_0Package.eINSTANCE.getNsURI());

	private Implementation createMyxGenImplementation(Class<?> mainClass) {
		MyxGen impl = myxgenFactory.createMyxGen();
		impl.setBrickID(mainClass.getName());
		return impl;
	}

	private JavaClass createJavaClass(Class<?> theClass) {
		JavaClass javaClass = javaFactory.createJavaClass();
		javaClass.setClassName(theClass.getName());
		return javaClass;
	}

	private Implementation createJavaImplementation(Class<?> mainClass, Class<?>... auxClasses) {
		JavaImplementation impl = javaFactory.createJavaImplementation();
		impl.setMainClass(createJavaClass(mainClass));
		return impl;
	}

	private Implementation createLookupImplementation(String lookupName) {
		LookupImplementation impl = lookupFactory.createLookupImplementation();
		impl.setLookup(lookupName);
		return impl;
	}

	private Implementation createOSGiImplementation(String bundle) {
		OSGiImplementation impl = osgiFactory.createOSGiImplementation();
		impl.setBundle(bundle);
		return impl;
	}

	private Implementation createInitializationParams(Map<String, String> parameters) {
		InitializationParametersImplementation impl = implFactory.createInitializationParametersImplementation();
		impl.getInitializationParameter().putAll(parameters);
		return impl;
	}

	private ObjRef addImplementations(ObjRef baseRef, Implementation... implementations) {
		ImplementationExtension ext = XArchADTProxy.proxy(xarch,
				XadlUtils.createExt(xarch, baseRef, Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION));
		ext.getImplementation().addAll(Arrays.asList(implementations));
		return baseRef;
	}

	private ObjRef createInterface(String name, Direction direction, DomainType domain, Class<?> javaClass) {
		ObjRef ifaceRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE);
		xarch.set(ifaceRef, "id", toID(name));
		xarch.set(ifaceRef, "name", name);
		xarch.set(ifaceRef, "direction", direction);

		ObjRef domainExtRef = XadlUtils.create(xarch, Domain_3_0Package.Literals.DOMAIN_EXTENSION);
		ObjRef domainRef = XadlUtils.create(xarch, Domain_3_0Package.Literals.DOMAIN);
		xarch.set(domainRef, "type", domain);
		xarch.set(domainExtRef, "domain", domainRef);
		xarch.add(ifaceRef, "ext", domainExtRef);

		addImplementations(ifaceRef, createJavaImplementation(javaClass), createLookupImplementation(name));

		return ifaceRef;
	}

	private ObjRef createBrick(String typeOfBrick, String name, Class<?> javaClass, ObjRef... ifaceRefs) {
		ObjRef brickRef = xarch.create(Structure_3_0Package.eNS_URI, typeOfBrick);
		xarch.set(brickRef, "id", toID(name));
		xarch.set(brickRef, "name", name);
		for (ObjRef ifaceRef : ifaceRefs) {
			xarch.set(ifaceRef, "id", toID(name) + "_" + xarch.get(ifaceRef, "id"));
		}
		xarch.add(brickRef, "interface", Arrays.asList(ifaceRefs));

		addImplementations(brickRef, createMyxGenImplementation(javaClass));

		return brickRef;
	}

	private ObjRef createComponent(String name, Class<?> javaClass, ObjRef... ifaceRefs) {
		return createBrick("component", name, javaClass, ifaceRefs);
	}

	private ObjRef createEditorComponent(String name, Class<?> javaClass, ObjRef... additionalIfaceRefs) {
		List<ObjRef> ifaceRefs = new ArrayList<ObjRef>();
		ifaceRefs.addAll(Arrays.asList(additionalIfaceRefs));

		ifaceRefs.add(createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class));
		ifaceRefs.add(createInterface("editorManager", Direction.OUT, DomainType.TOP,
				org.archstudio.editormanager.IEditorManager.class));
		ifaceRefs.add(createInterface("fileEvents", Direction.IN, DomainType.TOP,
				org.archstudio.xarchadt.IXArchADTFileListener.class));
		ifaceRefs.add(createInterface("modelEvents", Direction.IN, DomainType.TOP,
				org.archstudio.xarchadt.IXArchADTModelListener.class));
		ifaceRefs.add(createInterface("fileManagerEvents", Direction.IN, DomainType.TOP,
				org.archstudio.filemanager.IFileManagerListener.class));
		ifaceRefs.add(createInterface("focusEditorEvents", Direction.IN, DomainType.TOP,
				org.archstudio.eclipse.ui.IFocusEditorListener.class));
		ifaceRefs.add(createInterface("resources", Direction.OUT, DomainType.TOP,
				org.archstudio.resources.IResources.class));
		ifaceRefs.add(createInterface("fileManager", Direction.OUT, DomainType.TOP,
				org.archstudio.filemanager.IFileManager.class));
		ifaceRefs.add(createInterface("launcher", Direction.IN, DomainType.BOTTOM,
				org.archstudio.launcher.ILaunchable.class));

		return createBrick("component", name, javaClass, ifaceRefs.toArray(new ObjRef[0]));
	}

	private List<ObjRef> createEditorLinks(ObjRef structureRef, String editorComponentName) {
		List<ObjRef> linkList = new ArrayList<ObjRef>();

		linkList.add(createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
				getInterfaceRef(structureRef, editorComponentName, "xarch")));

		linkList.add(createLink(getInterfaceRef(structureRef, "EditorManager", "editorManager"),
				getInterfaceRef(structureRef, editorComponentName, "editorManager")));

		linkList.add(createLink(getInterfaceRef(structureRef, "XArchADTModelEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "modelEvents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "XArchADTFileEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "fileEvents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FocusEditorEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "focusEditorEvents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FileManagerEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "fileManagerEvents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "Resources", "resources"),
				getInterfaceRef(structureRef, editorComponentName, "resources")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FileManager", "fileManager"),
				getInterfaceRef(structureRef, editorComponentName, "fileManager")));

		linkList.add(createLink(getInterfaceRef(structureRef, editorComponentName, "launcher"),
				getInterfaceRef(structureRef, "LauncherMultiway", "out")));

		return linkList;
	}

	private ObjRef createLink(ObjRef iface1Ref, ObjRef iface2Ref) {
		ObjRef linkRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.LINK);

		ObjRef brickRef1 = xarch.getParent(iface1Ref);
		ObjRef brickRef2 = xarch.getParent(iface2Ref);

		String name1 = XadlUtils.getName(xarch, brickRef1) + "." + XadlUtils.getName(xarch, iface1Ref);
		String name2 = XadlUtils.getName(xarch, brickRef2) + "." + XadlUtils.getName(xarch, iface2Ref);

		xarch.set(linkRef, "id", toID(name1 + "-to-" + name2));
		xarch.set(linkRef, "name", name1 + "-to-" + name2);
		xarch.set(linkRef, "point1", iface1Ref);
		xarch.set(linkRef, "point2", iface2Ref);

		return linkRef;
	}

	private ObjRef getInterfaceRef(ObjRef structureRef, String targetBrickName, String targetInterfaceName) {
		List<ObjRef> brickRefs = new ArrayList<ObjRef>();
		brickRefs.addAll(Lists.newArrayList(Iterables.filter(xarch.getAll(structureRef, "component"), ObjRef.class)));
		brickRefs.addAll(Lists.newArrayList(Iterables.filter(xarch.getAll(structureRef, "connector"), ObjRef.class)));
		for (ObjRef brickRef : brickRefs) {
			String brickName = XadlUtils.getName(xarch, brickRef);
			if (brickName != null && brickName.equals(targetBrickName)
					|| XadlUtils.getID(xarch, brickRef).equals(toID(targetBrickName))) {
				for (ObjRef interfaceRef : Iterables.filter(xarch.getAll(brickRef, "interface"), ObjRef.class)) {
					String interfaceName = XadlUtils.getName(xarch, interfaceRef);
					if (interfaceName != null && interfaceName.equals(targetInterfaceName)) {
						return interfaceRef;
					}
				}
			}
		}
		throw new IllegalArgumentException("Can't find interface: " + targetInterfaceName + " on brick "
				+ targetBrickName);
	}

	public Properties createSingletonProperties(String name, String value) {
		Properties p = new Properties();
		p.setProperty(name, value);
		return p;
	}

	private ObjRef createConnector(String name, String bundleName, Class<?> javaClass, Map<String, String> initParams,
			ObjRef... ifaceRefs) {
		ObjRef brickRef = createBrick("connector", name, javaClass, ifaceRefs);
		return addImplementations(brickRef, createOSGiImplementation(bundleName),
				createInitializationParams(initParams));
	}

	private ObjRef createDownwardEventPumpConnector(String name, String osgiBundle, Map<String, String> initParams) {
		return createConnector(name, osgiBundle, org.archstudio.myx.osgi.conn.OSGiEventPumpConnector.class, initParams,
				createInterface("in", Direction.IN, DomainType.TOP, Object.class),
				createInterface("out", Direction.OUT, DomainType.BOTTOM, Object.class));
	}

	public void execute() {
		URI docURI = URI.createURI("urn:archstudio5description");
		ObjRef rootElementRef = xarch.createDocument(docURI);

		ObjRef xADLRef = XadlUtils.create(xarch, Xadlcore_3_0Package.Literals.XADL_TYPE);

		xarch.set(rootElementRef, "xADL", xADLRef);

		ObjRef structureRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.STRUCTURE);
		xarch.set(structureRef, "id", toID("ArchStudio 5 Main Structure"));
		xarch.set(structureRef, "name", "ArchStudio 5 Main Structure");

		xarch.add(xADLRef, "topLevelElement", structureRef);

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"xArch ADT",
						org.archstudio.xarchadt.core.XArchADTMyxComponent.class,
						createInterface("xarch", Direction.IN, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADT.class),
						createInterface("fileEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADTFileListener.class),
						createInterface("modelEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADTModelListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Resources",
						org.archstudio.resources.core.ResourcesMyxComponent.class,
						createInterface("resources", Direction.IN, DomainType.BOTTOM,
								org.archstudio.resources.IResources.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Preferences ADT",
						org.archstudio.preferencesadt.core.PreferencesADTMyxComponent.class,
						createInterface("preferences", Direction.IN, DomainType.BOTTOM,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Root Preferences",
						org.archstudio.rootpreferences.core.RootPreferencesMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Myx Runtime",
						org.archstudio.myx.java.comp.MyxRuntimeComponent.class,
						createInterface("myxRuntime", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.fw.IMyxRuntime.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"AIM",
						org.archstudio.aim.core.AIMMyxComponent.class,
						createInterface("aim", Direction.IN, DomainType.BOTTOM, org.archstudio.aim.IAIM.class),
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("myxRuntime", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.fw.IMyxRuntime.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Editor Manager",
						org.archstudio.editormanager.core.EditorManagerMyxComponent.class,
						createInterface("editorManager", Direction.IN, DomainType.BOTTOM,
								org.archstudio.editormanager.IEditorManager.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("focusEditorEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.eclipse.ui.IFocusEditorListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Editor Prefs",
						org.archstudio.editormanager.core.EditorPrefsMyxComponent.class,
						createInterface("editorManager", Direction.OUT, DomainType.TOP,
								org.archstudio.editormanager.IEditorManager.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent("AIM Launcher", org.archstudio.aimlauncher.core.AIMLauncherMyxComponent.class,
						createInterface("aim", Direction.OUT, DomainType.TOP, org.archstudio.aim.IAIM.class)));

		xarch.add(structureRef, "component",
				createEditorComponent("ArchEdit", org.archstudio.archedit.core.ArchEditMyxComponent.class));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Launcher",
						org.archstudio.launcher.core.LauncherMyxComponent.class,
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class),
						createInterface("launcher", Direction.OUT, DomainType.TOP,
								org.archstudio.launcher.ILaunchable.class),
						createInterface("results", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.java.conn.IMultiwayResults.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"File Manager",
						org.archstudio.filemanager.core.FileManagerMyxComponent.class,
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("fileEvents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTFileListener.class),
						createInterface("modelEvents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTModelListener.class),
						createInterface("fileManager", Direction.IN, DomainType.BOTTOM,
								org.archstudio.filemanager.IFileManager.class),
						createInterface("fileManagerEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.filemanager.IFileManagerListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Graph Layout",
						org.archstudio.graphlayout.core.GraphLayoutMyxComponent.class,
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("graphLayout", Direction.IN, DomainType.BOTTOM,
								org.archstudio.graphlayout.IGraphLayout.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Graph Layout Prefs",
						org.archstudio.graphlayout.core.GraphLayoutPrefsMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archipelago",
						org.archstudio.archipelago.core.ArchipelagoMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("graphLayout", Direction.OUT, DomainType.TOP,
								org.archstudio.graphlayout.IGraphLayout.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Archipelago Prefs",
						org.archstudio.archipelago.core.prefs.ArchipelagoPrefsMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Archipelago Structure Prefs",
						org.archstudio.archipelago.core.prefs.structure.ArchipelagoStructurePrefsMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArch ADT Model Event Pump", "org.archstudio.xarchadt.core",
						interfaceClass(org.archstudio.xarchadt.IXArchADTModelListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArch ADT File Event Pump", "org.archstudio.xarchadt.core",
						interfaceClass(org.archstudio.xarchadt.IXArchADTFileListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("File Manager Event Pump", "org.archstudio.filemanager.core",
						interfaceClass(org.archstudio.filemanager.IFileManagerListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("Focus Editor Event Pump", "org.archstudio.editormanager.core",
						interfaceClass(org.archstudio.eclipse.ui.IFocusEditorListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createConnector(
						"Launcher Multiway",
						"org.archstudio.launcher.core",
						org.archstudio.myx.java.conn.SynchronousMultiwayProxyConnector.class,
						interfaceClass(org.archstudio.launcher.ILaunchable.class),
						createInterface("in", Direction.IN, DomainType.BOTTOM,
								org.archstudio.launcher.ILaunchable.class),
						createInterface("out", Direction.OUT, DomainType.TOP, org.archstudio.launcher.ILaunchable.class),
						createInterface("results", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.java.conn.IMultiwayResults.class),
						createInterface("progress", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.java.conn.IMultiwayProgressListener.class)));

		//Archlight
		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Issue ADT",
						org.archstudio.issueadt.core.ArchlightIssueADTMyxComponent.class,
						createInterface("issues", Direction.IN, DomainType.BOTTOM,
								org.archstudio.issueadt.IArchlightIssueADT.class),
						createInterface("fileEvents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTFileListener.class),
						createInterface("issueEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.issueadt.ArchlightIssueADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Notice ADT",
						org.archstudio.noticeadt.core.ArchlightNoticeADTMyxComponent.class,
						createInterface("notices", Direction.IN, DomainType.BOTTOM,
								org.archstudio.noticeadt.IArchlightNoticeADT.class),
						createInterface("noticeEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.noticeadt.ArchlightNoticeADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Test ADT",
						org.archstudio.testadt.core.ArchlightTestADTMyxComponent.class,
						createInterface("tests", Direction.IN, DomainType.BOTTOM,
								org.archstudio.testadt.IArchlightTestADT.class),
						createInterface("testEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.testadt.ArchlightTestADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Issue View",
						org.archstudio.issueview.core.ArchlightIssueViewMyxComponent.class,
						createInterface("issues", Direction.OUT, DomainType.TOP,
								org.archstudio.issueadt.IArchlightIssueADT.class),
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("editorManager", Direction.OUT, DomainType.TOP,
								org.archstudio.editormanager.IEditorManager.class),
						createInterface("issueEvents", Direction.IN, DomainType.TOP,
								org.archstudio.issueadt.ArchlightIssueADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Notice View",
						org.archstudio.noticeview.core.ArchlightNoticeViewMyxComponent.class,
						createInterface("notices", Direction.OUT, DomainType.TOP,
								org.archstudio.noticeadt.IArchlightNoticeADT.class),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class),
						createInterface("noticeEvents", Direction.IN, DomainType.TOP,
								org.archstudio.noticeadt.ArchlightNoticeADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Schematron",
						org.archstudio.schematron.core.SchematronMyxComponent.class,
						createInterface("tools", Direction.IN, DomainType.BOTTOM,
								org.archstudio.archlight.IArchlightTool.class),
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("issues", Direction.OUT, DomainType.TOP,
								org.archstudio.issueadt.IArchlightIssueADT.class),
						createInterface("tests", Direction.OUT, DomainType.TOP,
								org.archstudio.testadt.IArchlightTestADT.class),
						createInterface("notices", Direction.OUT, DomainType.TOP,
								org.archstudio.noticeadt.IArchlightNoticeADT.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Schematron Prefs",
						org.archstudio.schematron.core.prefs.SchematronPrefsMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		//xarch.add(
		//		structureRef,
		//		"component",
		//		createComponent(
		//				"Flow Checker",
		//				org.archstudio.flowchecker.core.FlowCheckerMyxComponent.class,
		//				null,
		//				createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
		//						org.archstudio.archlight.IArchlightTool.class),
		//				createInterface("xarch", Direction.OUT, DomainType.TOP,
		//						org.archstudio.xarchadt.IXArchADT.class),
		//				createInterface("archlightissueadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.issueadt.IArchlightIssueADT.class),
		//				createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.testadt.IArchlightTestADT.class),
		//				createInterface("archlightnoticeadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.noticeadt.IArchlightNoticeADT.class),
		//				createInterface("preferences", Direction.OUT, DomainType.TOP,
		//						org.eclipse.jface.preference.IPreferenceStore.class)));

		//xarch.add(
		//		structureRef,
		//		"component",
		//		createComponent(
		//				"Memory Checker",
		//				org.archstudio.memorychecker.core.MemoryCheckerMyxComponent.class,
		//				null,
		//				createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
		//						org.archstudio.archlight.IArchlightTool.class),
		//				createInterface("xarch", Direction.OUT, DomainType.TOP,
		//						org.archstudio.xarchadt.IXArchADT.class),
		//				createInterface("archlightissueadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.issueadt.IArchlightIssueADT.class),
		//				createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.testadt.IArchlightTestADT.class),
		//				createInterface("archlightnoticeadt", Direction.OUT, DomainType.TOP,
		//						org.archstudio.noticeadt.IArchlightNoticeADT.class),
		//				createInterface("preferences", Direction.OUT, DomainType.TOP,
		//						org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Archlight Tool Aggregator",
						org.archstudio.archlight.core.ArchlightToolAggregatorMyxComponent.class,
						createInterface("results", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.java.conn.IMultiwayResults.class),
						createInterface("tools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class),
						createInterface("tool", Direction.IN, DomainType.BOTTOM,
								org.archstudio.archlight.IArchlightTool.class),
						createInterface("progress", Direction.IN, DomainType.TOP,
								org.archstudio.myx.java.conn.IMultiwayProgressListener.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archlight",
						org.archstudio.archlight.core.ArchlightMyxComponent.class,
						createInterface("tests", Direction.OUT, DomainType.TOP,
								org.archstudio.testadt.IArchlightTestADT.class),
						createInterface("tools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Archlight Prefs",
						org.archstudio.archlight.core.prefs.ArchlightPrefsMyxComponent.class,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("Issue ADT Event Pump", "org.archstudio.issueadt.core",
						interfaceClass(org.archstudio.issueadt.ArchlightIssueADTListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("Notice ADT Event Pump", "org.archstudio.noticeadt",
						interfaceClass(org.archstudio.noticeadt.ArchlightNoticeADTListener.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("Test ADT Event Pump", "org.archstudio.testadt",
						interfaceClass(org.archstudio.testadt.ArchlightTestADTListener.class)));

		//xarch.add(
		//		structureRef,
		//		"component",
		//		createEditorComponent("FlowEditor",
		//				org.archstudio.floweditor.core.FlowEditorMyxComponent.class, null));

		//xarch.add(
		//		structureRef,
		//		"component",
		//		createEditorComponent("HPCConfigurationEditor",
		//				org.archstudio.hpc.editor.HPCConfigurationEditorMyxComponent.class, null));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "AIM", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "fileEvents"),
						getInterfaceRef(structureRef, "XArchADTFileEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "modelEvents"),
						getInterfaceRef(structureRef, "XArchADTModelEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "AIM", "myxRuntime"),
						getInterfaceRef(structureRef, "MyxRuntime", "myxRuntime")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Resources", "resources"),
						getInterfaceRef(structureRef, "Launcher", "resources")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "LauncherMultiway", "in"),
						getInterfaceRef(structureRef, "Launcher", "launcher")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "LauncherMultiway", "results"),
						getInterfaceRef(structureRef, "Launcher", "results")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "FileManager", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "XArchADTFileEventPump", "out"),
						getInterfaceRef(structureRef, "FileManager", "fileEvents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "XArchADTModelEventPump", "out"),
						getInterfaceRef(structureRef, "FileManager", "modelEvents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "FileManager", "fileManagerEvents"),
						getInterfaceRef(structureRef, "FileManagerEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "EditorManager", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "EditorManager", "editorManager"),
						getInterfaceRef(structureRef, "EditorPrefs", "editorManager")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "EditorPrefs", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "EditorManager", "focusEditorEvents"),
						getInterfaceRef(structureRef, "FocusEditorEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "RootPreferences", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Resources", "resources"),
						getInterfaceRef(structureRef, "RootPreferences", "resources")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "GraphLayout", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "GraphLayout", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "GraphLayoutPrefs", "preferences")));

		for (ObjRef linkRef : createEditorLinks(structureRef, "AIMLauncher")) {
			xarch.add(structureRef, "link", linkRef);
		}

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "AIM", "aim"),
						getInterfaceRef(structureRef, "AIMLauncher", "aim")));

		for (ObjRef linkRef : createEditorLinks(structureRef, "ArchEdit")) {
			xarch.add(structureRef, "link", linkRef);
		}

		for (ObjRef linkRef : createEditorLinks(structureRef, "Archipelago")) {
			xarch.add(structureRef, "link", linkRef);
		}

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "Archipelago", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "GraphLayout", "graphLayout"),
						getInterfaceRef(structureRef, "Archipelago", "graphLayout")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "ArchipelagoPrefs", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "ArchipelagoStructurePrefs", "preferences")));

		//Archlight
		//  IssueADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "XArchADTFileEventPump", "out"),
						getInterfaceRef(structureRef, "IssueADT", "fileEvents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "issueEvents"),
						getInterfaceRef(structureRef, "IssueADTEventPump", "in")));

		//  NoticeADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "noticeEvents"),
						getInterfaceRef(structureRef, "NoticeADTEventPump", "in")));

		//  TestADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "testEvents"),
						getInterfaceRef(structureRef, "TestADTEventPump", "in")));

		//  IssueView
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "issues"),
						getInterfaceRef(structureRef, "IssueView", "issues")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "IssueView", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Resources", "resources"),
						getInterfaceRef(structureRef, "IssueView", "resources")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "IssueView", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "EditorManager", "editorManager"),
						getInterfaceRef(structureRef, "IssueView", "editorManager")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADTEventPump", "out"),
						getInterfaceRef(structureRef, "IssueView", "issueEvents")));

		//  NoticeView
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "notices"),
						getInterfaceRef(structureRef, "NoticeView", "notices")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Resources", "resources"),
						getInterfaceRef(structureRef, "NoticeView", "resources")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADTEventPump", "out"),
						getInterfaceRef(structureRef, "NoticeView", "noticeEvents")));

		//  Schematron
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "Schematron", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "issues"),
						getInterfaceRef(structureRef, "Schematron", "issues")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "tests"),
						getInterfaceRef(structureRef, "Schematron", "tests")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "notices"),
						getInterfaceRef(structureRef, "Schematron", "notices")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "Schematron", "preferences")));

		//  FlowChecker

		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
		//				getInterfaceRef(structureRef, "FlowChecker", "xarch")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
		//				getInterfaceRef(structureRef, "FlowChecker", "archlightissueadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
		//				getInterfaceRef(structureRef, "FlowChecker", "archlighttestadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
		//				getInterfaceRef(structureRef, "FlowChecker", "archlightnoticeadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
		//				getInterfaceRef(structureRef, "FlowChecker", "preferences")));

		//  MemoryChecker

		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
		//				getInterfaceRef(structureRef, "MemoryChecker", "xarch")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
		//				getInterfaceRef(structureRef, "MemoryChecker", "archlightissueadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
		//				getInterfaceRef(structureRef, "MemoryChecker", "archlighttestadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
		//				getInterfaceRef(structureRef, "MemoryChecker", "archlightnoticeadt")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
		//				getInterfaceRef(structureRef, "MemoryChecker", "preferences")));

		//  ArchlightToolAggregator
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Schematron", "tools"),
						getInterfaceRef(structureRef, "ArchlightToolAggregator", "tools")));

		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "FlowChecker", "archlighttool"),
		//				getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttools")));
		//
		//xarch.add(
		//		structureRef,
		//		"link",
		//		createLink(getInterfaceRef(structureRef, "MemoryChecker", "archlighttool"),
		//				getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttools")));

		//  Archlight
		for (ObjRef linkRef : createEditorLinks(structureRef, "Archlight")) {
			xarch.add(structureRef, "link", linkRef);
		}

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "SchematronPrefs", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "ArchlightPrefs", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "tests"),
						getInterfaceRef(structureRef, "Archlight", "tests")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "ArchlightToolAggregator", "tool"),
						getInterfaceRef(structureRef, "Archlight", "tools")));

		//Flow Editor

		//for (ObjRef linkRef : createEditorLinks(structureRef, "FlowEditor")) {
		//	xarch.add(structureRef, "link", linkRef);
		//}
		//
		//for (ObjRef linkRef : createEditorLinks(structureRef, "HPCConfigurationEditor")) {
		//	xarch.add(structureRef, "link", linkRef);
		//}

		String xmlString = new String(xarch.serialize(docURI));
		System.err.println(xmlString);
	}

	private Map<String, String> interfaceClass(Class<?> interfaceClass) {
		Map<String, String> initParams = Maps.newHashMap();
		initParams.put("interfaceClassName0", interfaceClass.getName());
		return initParams;
	}

	private Serializable toID(String string) {
		return string.replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "_");
	}
}
