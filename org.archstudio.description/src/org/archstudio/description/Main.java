package org.archstudio.description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public class Main {
	private static IXArchADT xarch = new XArchADTImpl();

	static {
		// Note: referencing an EPackage initializes it and its corresponding EFactory 
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;
		p = Domain_3_0Package.eINSTANCE;
		p = Implementation_3_0Package.eINSTANCE;
		p = Javaimplementation_3_0Package.eINSTANCE;
		p = Lookupimplementation_3_0Package.eINSTANCE;
		p = Myxgen_3_0Package.eINSTANCE;
	}

	private static ObjRef createJavaImplementation(Class<?> mainClass, Properties initParams) {
		ObjRef javaImplementationRef = XadlUtils.create(xarch,
				Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION);

		ObjRef javaClassRef = XadlUtils.create(xarch, Javaimplementation_3_0Package.Literals.JAVA_CLASS);
		xarch.set(javaClassRef, "id", getNewID());
		xarch.set(javaClassRef, "className", mainClass.getName());

		if (initParams != null) {
			for (Object key : initParams.keySet()) {
				if (key instanceof String) {
					String name = (String) key;
					String value = initParams.getProperty(name);

					ObjRef initParamRef = XadlUtils.create(xarch,
							Javaimplementation_3_0Package.Literals.INITIALIZATION_PARAMETER);
					xarch.set(initParamRef, "id", getNewID());
					xarch.set(initParamRef, "name", name);
					xarch.set(initParamRef, "value", value);

					xarch.add(javaClassRef, "initializationParameter", initParamRef);
				}
			}
		}

		xarch.set(javaImplementationRef, "mainClass", javaClassRef);

		return javaImplementationRef;
	}

	private static ObjRef createMyxGenImplementation(Class<?> mainClass) {
		ObjRef myxGenImplementationRef = XadlUtils.create(xarch, Myxgen_3_0Package.Literals.MYX_GEN);

		xarch.set(myxGenImplementationRef, "brickID", mainClass.getName());

		return myxGenImplementationRef;
	}

	private static ObjRef createLookupImplementation(String lookupName) {
		ObjRef lookupImplementationRef = XadlUtils.create(xarch,
				Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION);

		xarch.set(lookupImplementationRef, "lookup", lookupName);

		return lookupImplementationRef;
	}

	private static ObjRef createImplementationExt(ObjRef... implementationRefs) {
		ObjRef implementationExtRef = XadlUtils.create(xarch,
				Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION);
		for (ObjRef implementationRef : implementationRefs) {
			xarch.add(implementationExtRef, "implementation", implementationRef);
		}
		return implementationExtRef;
	}

	private static ObjRef createInterface(String name, Direction direction, DomainType domain, Class<?> javaClass) {
		ObjRef ifaceRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE);
		xarch.set(ifaceRef, "id", getNewID());
		xarch.set(ifaceRef, "name", name);
		xarch.set(ifaceRef, "direction", direction);

		ObjRef domainExtRef = XadlUtils.create(xarch, Domain_3_0Package.Literals.DOMAIN_EXTENSION);
		ObjRef domainRef = XadlUtils.create(xarch, Domain_3_0Package.Literals.DOMAIN);
		xarch.set(domainRef, "type", domain);
		xarch.set(domainExtRef, "domain", domainRef);
		xarch.add(ifaceRef, "ext", domainExtRef);

		xarch.add(ifaceRef, "ext",
				createImplementationExt(createJavaImplementation(javaClass, null), createLookupImplementation(name)));
		return ifaceRef;
	}

	private static ObjRef createBrick(String typeOfBrick, String name, Class<?> javaClass, Properties initParams,
			ObjRef... ifaceRefs) {
		ObjRef brickRef = xarch.create(Structure_3_0Package.eNS_URI, typeOfBrick);
		xarch.set(brickRef, "id", getNewID());
		xarch.set(brickRef, "name", name);

		xarch.add(brickRef, "interface", Arrays.asList(ifaceRefs));

		xarch.add(brickRef, "ext", createImplementationExt(createMyxGenImplementation(javaClass)));

		return brickRef;
	}

	private static ObjRef createComponent(String name, Class<?> javaClass, Properties initParams, ObjRef... ifaceRefs) {
		return createBrick("component", name, javaClass, initParams, ifaceRefs);
	}

	private static ObjRef createEditorComponent(String name, Class<?> javaClass, Properties initParams,
			ObjRef... additionalIfaceRefs) {
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
				org.archstudio.editors.IFocusEditorListener.class));
		ifaceRefs.add(createInterface("resources", Direction.OUT, DomainType.TOP,
				org.archstudio.resources.IResources.class));
		ifaceRefs.add(createInterface("fileManager", Direction.OUT, DomainType.TOP,
				org.archstudio.filemanager.IFileManager.class));
		ifaceRefs.add(createInterface("launcher", Direction.IN, DomainType.BOTTOM,
				org.archstudio.launcher.ILaunchable.class));

		return createBrick("component", name, javaClass, initParams, ifaceRefs.toArray(new ObjRef[0]));
	}

	private static List<ObjRef> createEditorLinks(ObjRef structureRef, String editorComponentName) {
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

	private static ObjRef createConnector(String name, Class<?> javaClass, Properties initParams, ObjRef... ifaceRefs) {
		return createBrick("connector", name, javaClass, initParams, ifaceRefs);
	}

	private static ObjRef createLink(ObjRef iface1Ref, ObjRef iface2Ref) {
		ObjRef linkRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.LINK);

		ObjRef brickRef1 = xarch.getParent(iface1Ref);
		ObjRef brickRef2 = xarch.getParent(iface2Ref);

		String name1 = XadlUtils.getName(xarch, brickRef1) + "." + XadlUtils.getName(xarch, iface1Ref);
		String name2 = XadlUtils.getName(xarch, brickRef2) + "." + XadlUtils.getName(xarch, iface2Ref);

		xarch.set(linkRef, "id", getNewID());
		xarch.set(linkRef, "name", name1 + "-to-" + name2);
		xarch.set(linkRef, "point1", iface1Ref);
		xarch.set(linkRef, "point2", iface2Ref);

		return linkRef;
	}

	private static ObjRef getInterfaceRef(ObjRef structureRef, String targetBrickName, String targetInterfaceName) {
		List<ObjRef> brickRefs = new ArrayList<ObjRef>();
		brickRefs.addAll(xarch.getAll(structureRef, "component"));
		brickRefs.addAll(xarch.getAll(structureRef, "connector"));
		for (ObjRef brickRef : brickRefs) {
			String brickName = XadlUtils.getName(xarch, brickRef);
			if (brickName != null && brickName.equals(targetBrickName)) {
				for (ObjRef interfaceRef : xarch.getAll(brickRef, "interface")) {
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

	public static Properties createSingletonProperties(String name, String value) {
		Properties p = new Properties();
		p.setProperty(name, value);
		return p;
	}

	private static ObjRef createDownwardEventPumpConnector(String name, Class<?> interfaceClass) {
		return createConnector(name, org.archstudio.myx.conn.EventPumpConnector.class,
				createSingletonProperties("interfaceClass0", interfaceClass.getName()),
				createInterface("in", Direction.IN, DomainType.TOP, interfaceClass),
				createInterface("out", Direction.OUT, DomainType.BOTTOM, interfaceClass));
	}

	public static void main(String[] args) {
		URI docURI = URI.createURI("urn:archstudio5description");
		ObjRef rootElementRef = xarch.createDocument(docURI);

		ObjRef xADLRef = XadlUtils.create(xarch, Xadlcore_3_0Package.Literals.XADL_TYPE);

		xarch.set(rootElementRef, "xADL", xADLRef);

		ObjRef structureRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.STRUCTURE);
		xarch.set(structureRef, "id", getNewID());
		xarch.set(structureRef, "name", "ArchStudio 5 Main Structure");

		xarch.add(xADLRef, "topLevelElement", structureRef);

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"xArchADT",
						org.archstudio.xarchadt.core.XArchADTMyxComponent.class,
						null,
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
						null,
						createInterface("resources", Direction.IN, DomainType.BOTTOM,
								org.archstudio.resources.IResources.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"PreferencesADT",
						org.archstudio.preferencesadt.core.PreferencesADTMyxComponent.class,
						null,
						createInterface("preferences", Direction.IN, DomainType.BOTTOM,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"RootPreferences",
						org.archstudio.rootpreferences.core.RootPreferencesMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"MyxRuntime",
						org.archstudio.myx.comp.MyxRuntimeComponent.class,
						null,
						createInterface("myxRuntime", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.fw.IMyxRuntime.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"AIM",
						org.archstudio.aim.core.AIMMyxComponent.class,
						null,
						createInterface("aim", Direction.IN, DomainType.BOTTOM, org.archstudio.aim.IAIM.class),
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("myxRuntime", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.fw.IMyxRuntime.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"EditorManager",
						org.archstudio.editormanager.core.EditorManagerMyxComponent.class,
						null,
						createInterface("editorManager", Direction.IN, DomainType.BOTTOM,
								org.archstudio.editormanager.IEditorManager.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("focusEditorEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.editors.IFocusEditorListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"EditorPrefs",
						org.archstudio.editormanager.core.EditorPrefsMyxComponent.class,
						null,
						createInterface("editorManager", Direction.OUT, DomainType.TOP,
								org.archstudio.editormanager.IEditorManager.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent("AIMLauncher", org.archstudio.aimlauncher.core.AIMLauncherMyxComponent.class,
						null, createInterface("aim", Direction.OUT, DomainType.TOP, org.archstudio.aim.IAIM.class)));

		xarch.add(structureRef, "component",
				createEditorComponent("ArchEdit", org.archstudio.archedit.core.ArchEditMyxComponent.class, null));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Launcher",
						org.archstudio.launcher.core.LauncherMyxComponent.class,
						null,
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class),
						createInterface("launcher", Direction.OUT, DomainType.TOP,
								org.archstudio.launcher.ILaunchable.class),
						createInterface("results", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.conn.IMultiwayResults.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"FileManager",
						org.archstudio.filemanager.core.FileManagerMyxComponent.class,
						null,
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
						"GraphLayout",
						org.archstudio.graphlayout.core.GraphLayoutMyxComponent.class,
						null,
						createInterface("xarch", Direction.OUT, DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("graphLayout", Direction.IN, DomainType.BOTTOM,
								org.archstudio.graphlayout.IGraphLayout.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"GraphLayoutPrefs",
						org.archstudio.graphlayout.core.GraphLayoutPrefsMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archipelago",
						org.archstudio.archipelago.core.ArchipelagoMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class),
						createInterface("graphLayout", Direction.OUT, DomainType.TOP,
								org.archstudio.graphlayout.IGraphLayout.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchipelagoPrefs",
						org.archstudio.archipelago.core.prefs.ArchipelagoPrefsMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchipelagoStructurePrefs",
						org.archstudio.archipelago.core.prefs.structure.ArchipelagoStructurePrefsMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArchADTModelEventPump",
						org.archstudio.xarchadt.IXArchADTModelListener.class));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArchADTFileEventPump",
						org.archstudio.xarchadt.IXArchADTFileListener.class));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("FileManagerEventPump",
						org.archstudio.filemanager.IFileManagerListener.class));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("FocusEditorEventPump",
						org.archstudio.editors.IFocusEditorListener.class));

		xarch.add(
				structureRef,
				"connector",
				createConnector(
						"LauncherMultiway",
						org.archstudio.myx.conn.SynchronousMultiwayProxyConnector.class,
						createSingletonProperties("interfaceClass0",
								org.archstudio.launcher.ILaunchable.class.getName()),
						createInterface("in", Direction.IN, DomainType.BOTTOM,
								org.archstudio.launcher.ILaunchable.class),
						createInterface("out", Direction.OUT, DomainType.TOP, org.archstudio.launcher.ILaunchable.class),
						createInterface("results", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayResults.class),
						createInterface("progress", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayProgressListener.class)));

		//Archlight
		xarch.add(
				structureRef,
				"component",
				createComponent(
						"IssueADT",
						org.archstudio.issueadt.core.ArchlightIssueADTMyxComponent.class,
						null,
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
						"NoticeADT",
						org.archstudio.noticeadt.core.ArchlightNoticeADTMyxComponent.class,
						null,
						createInterface("notices", Direction.IN, DomainType.BOTTOM,
								org.archstudio.noticeadt.IArchlightNoticeADT.class),
						createInterface("noticeEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.noticeadt.ArchlightNoticeADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"TestADT",
						org.archstudio.testadt.core.ArchlightTestADTMyxComponent.class,
						null,
						createInterface("tests", Direction.IN, DomainType.BOTTOM,
								org.archstudio.testadt.IArchlightTestADT.class),
						createInterface("testEvents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.testadt.ArchlightTestADTListener.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"IssueView",
						org.archstudio.issueview.core.ArchlightIssueViewMyxComponent.class,
						null,
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
						"NoticeView",
						org.archstudio.noticeview.core.ArchlightNoticeViewMyxComponent.class,
						null,
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
						null,
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
						"SchematronPrefs",
						org.archstudio.schematron.core.prefs.SchematronPrefsMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		//xarch.add(
		//		structureRef,
		//		"component",
		//		createComponent(
		//				"FlowChecker",
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
		//				"MemoryChecker",
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
						"ArchlightToolAggregator",
						org.archstudio.archlight.core.ArchlightToolAggregatorMyxComponent.class,
						null,
						createInterface("results", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayResults.class),
						createInterface("tools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class),
						createInterface("tool", Direction.IN, DomainType.BOTTOM,
								org.archstudio.archlight.IArchlightTool.class),
						createInterface("progress", Direction.IN, DomainType.TOP,
								org.archstudio.myx.conn.IMultiwayProgressListener.class)));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archlight",
						org.archstudio.archlight.core.ArchlightMyxComponent.class,
						null,
						createInterface("tests", Direction.OUT, DomainType.TOP,
								org.archstudio.testadt.IArchlightTestADT.class),
						createInterface("tools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class)));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchlightPrefs",
						org.archstudio.archlight.core.prefs.ArchlightPrefsMyxComponent.class,
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class)));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("IssueADTEventPump",
						org.archstudio.issueadt.ArchlightIssueADTListener.class));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("NoticeADTEventPump",
						org.archstudio.noticeadt.ArchlightNoticeADTListener.class));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("TestADTEventPump",
						org.archstudio.testadt.ArchlightTestADTListener.class));

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
				createLink(getInterfaceRef(structureRef, "IssueADT", "issues"),
						getInterfaceRef(structureRef, "IssueADTEventPump", "in")));

		//  NoticeADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "notices"),
						getInterfaceRef(structureRef, "NoticeADTEventPump", "in")));

		//  TestADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "tests"),
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

	static int nextID = 0;

	public static String getNewID() {
		return "id" + ++nextID;
	}
}
