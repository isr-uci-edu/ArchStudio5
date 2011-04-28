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
		// Note: referencing an EPackage initializes it and its corresponding EFacotry 
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;
		p = Domain_3_0Package.eINSTANCE;
		p = Implementation_3_0Package.eINSTANCE;
		p = Javaimplementation_3_0Package.eINSTANCE;
		p = Lookupimplementation_3_0Package.eINSTANCE;
	}

	private static ObjRef createJavaImplementation(String mainClass, Properties initParams) {
		ObjRef javaImplementationRef = XadlUtils.create(xarch,
				Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION);

		ObjRef javaClassRef = XadlUtils.create(xarch, Javaimplementation_3_0Package.Literals.JAVA_CLASS);
		xarch.set(javaClassRef, "id", getNewID());
		xarch.set(javaClassRef, "className", mainClass);

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

	private static ObjRef createInterface(String name, Direction direction, DomainType domain, String javaClass) {
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

	private static ObjRef createBrick(String typeOfBrick, String name, String javaClass, Properties initParams,
			ObjRef... ifaceRefs) {
		ObjRef brickRef = xarch.create(Structure_3_0Package.eNS_URI, typeOfBrick);
		xarch.set(brickRef, "id", getNewID());
		xarch.set(brickRef, "name", name);

		xarch.add(brickRef, "interface", Arrays.asList(ifaceRefs));

		xarch.add(brickRef, "ext", createImplementationExt(createJavaImplementation(javaClass, initParams)));

		return brickRef;
	}

	private static ObjRef createComponent(String name, String javaClass, Properties initParams, ObjRef... ifaceRefs) {
		return createBrick("component", name, javaClass, initParams, ifaceRefs);
	}

	private static ObjRef createEditorComponent(String name, String javaClass, Properties initParams,
			ObjRef... additionalIfaceRefs) {
		List<ObjRef> ifaceRefs = new ArrayList<ObjRef>();
		ifaceRefs.addAll(Arrays.asList(additionalIfaceRefs));

		ifaceRefs.add(createInterface("xarch", Direction.OUT, DomainType.TOP,
				org.archstudio.xarchadt.IXArchADT.class.getName()));
		ifaceRefs.add(createInterface("editormanager", Direction.OUT, DomainType.TOP,
				org.archstudio.editormanager.IEditorManager.class.getName()));
		ifaceRefs.add(createInterface("fileevents", Direction.IN, DomainType.TOP,
				org.archstudio.xarchadt.IXArchADTFileListener.class.getName()));
		ifaceRefs.add(createInterface("modelevents", Direction.IN, DomainType.TOP,
				org.archstudio.xarchadt.IXArchADTModelListener.class.getName()));
		ifaceRefs.add(createInterface("filemanagerevents", Direction.IN, DomainType.TOP,
				org.archstudio.filemanager.IFileManagerListener.class.getName()));
		ifaceRefs.add(createInterface("focuseditorevents", Direction.IN, DomainType.TOP,
				org.archstudio.editors.IFocusEditorListener.class.getName()));
		ifaceRefs.add(createInterface("resources", Direction.OUT, DomainType.TOP,
				org.archstudio.resources.IResources.class.getName()));
		ifaceRefs.add(createInterface("filemanager", Direction.OUT, DomainType.TOP,
				org.archstudio.filemanager.IFileManager.class.getName()));
		ifaceRefs.add(createInterface("launcher", Direction.IN, DomainType.BOTTOM,
				org.archstudio.launcher.ILaunchable.class.getName()));

		return createBrick("component", name, javaClass, initParams, (ObjRef[]) ifaceRefs.toArray(new ObjRef[0]));
	}

	private static List<ObjRef> createEditorLinks(ObjRef structureRef, String editorComponentName) {
		List<ObjRef> linkList = new ArrayList<ObjRef>();

		linkList.add(createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
				getInterfaceRef(structureRef, editorComponentName, "xarch")));

		linkList.add(createLink(getInterfaceRef(structureRef, "EditorManager", "editormanager"),
				getInterfaceRef(structureRef, editorComponentName, "editormanager")));

		linkList.add(createLink(getInterfaceRef(structureRef, "XArchADTModelEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "modelevents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "XArchADTFileEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "fileevents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FocusEditorEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "focuseditorevents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FileManagerEventPump", "out"),
				getInterfaceRef(structureRef, editorComponentName, "filemanagerevents")));

		linkList.add(createLink(getInterfaceRef(structureRef, "Resources", "resources"),
				getInterfaceRef(structureRef, editorComponentName, "resources")));

		linkList.add(createLink(getInterfaceRef(structureRef, "FileManager", "filemanager"),
				getInterfaceRef(structureRef, editorComponentName, "filemanager")));

		linkList.add(createLink(getInterfaceRef(structureRef, editorComponentName, "launcher"),
				getInterfaceRef(structureRef, "LauncherMultiway", "out")));

		return linkList;
	}

	private static ObjRef createConnector(String name, String javaClass, Properties initParams, ObjRef... ifaceRefs) {
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

	private static ObjRef createDownwardEventPumpConnector(String name, String interfaceClassName) {
		return createConnector(name, org.archstudio.myx.conn.EventPumpConnector.class.getName(),
				createSingletonProperties("interfaceClass0", interfaceClassName),
				createInterface("in", Direction.IN, DomainType.TOP, interfaceClassName),
				createInterface("out", Direction.OUT, DomainType.BOTTOM, interfaceClassName));
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
						org.archstudio.xarchadt.core.XArchADTMyxComponent.class.getName(),
						null,
						createInterface("xarch", Direction.IN, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("fileevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADTFileListener.class.getName()),
						createInterface("modelevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.xarchadt.IXArchADTModelListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Resources",
						org.archstudio.resources.core.ResourcesMyxComponent.class.getName(),
						null,
						createInterface("resources", Direction.IN, DomainType.BOTTOM,
								org.archstudio.resources.IResources.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"PreferencesADT",
						org.archstudio.preferencesadt.core.PreferencesADTMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.IN, DomainType.BOTTOM,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"RootPreferences",
						org.archstudio.rootpreferences.core.RootPreferencesMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName()),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"MyxRuntime",
						org.archstudio.myx.comp.MyxRuntimeComponent.class.getName(),
						null,
						createInterface("myxruntime", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.fw.IMyxRuntime.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"AIM",
						org.archstudio.aim.core.AIMMyxComponent.class.getName(),
						null,
						createInterface("aim", Direction.IN, DomainType.BOTTOM, org.archstudio.aim.IAIM.class.getName()),
						createInterface("xarch", Direction.OUT, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("myxruntime", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.fw.IMyxRuntime.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"EditorManager",
						org.archstudio.editormanager.core.EditorManagerMyxComponent.class.getName(),
						null,
						createInterface("editormanager", Direction.IN, DomainType.BOTTOM,
								org.archstudio.editormanager.IEditorManager.class.getName()),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName()),
						createInterface("focuseditorevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.editors.IFocusEditorListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"EditorPrefs",
						org.archstudio.editormanager.core.EditorPrefsMyxComponent.class.getName(),
						null,
						createInterface("editormanager", Direction.OUT, DomainType.TOP,
								org.archstudio.editormanager.IEditorManager.class.getName()),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent("AIMLauncher",
						org.archstudio.aimlauncher.core.AIMLauncherMyxComponent.class.getName(), null,
						createInterface("aim", Direction.OUT, DomainType.TOP, org.archstudio.aim.IAIM.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent("ArchEdit", org.archstudio.archedit.core.ArchEditMyxComponent.class.getName(),
						null));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Launcher",
						org.archstudio.launcher.core.LauncherMyxComponent.class.getName(),
						null,
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class.getName()),
						createInterface("launcher", Direction.OUT, DomainType.TOP,
								org.archstudio.launcher.ILaunchable.class.getName()),
						createInterface("results", Direction.OUT, DomainType.TOP,
								org.archstudio.myx.conn.IMultiwayResults.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"FileManager",
						org.archstudio.filemanager.core.FileManagerMyxComponent.class.getName(),
						null,
						createInterface("xarch", Direction.OUT, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("fileevents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTFileListener.class.getName()),
						createInterface("modelevents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTModelListener.class.getName()),
						createInterface("filemanager", Direction.IN, DomainType.BOTTOM,
								org.archstudio.filemanager.IFileManager.class.getName()),
						createInterface("filemanagerevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.filemanager.IFileManagerListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"GraphLayout",
						org.archstudio.graphlayout.core.GraphLayoutMyxComponent.class.getName(),
						null,
						createInterface("xarch", Direction.OUT, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName()),
						createInterface("graphlayout", Direction.IN, DomainType.BOTTOM,
								org.archstudio.graphlayout.IGraphLayout.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"GraphLayoutPrefs",
						org.archstudio.graphlayout.core.GraphLayoutPrefsMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archipelago",
						org.archstudio.archipelago.core.ArchipelagoMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName()),
						createInterface("graphlayout", Direction.OUT, DomainType.TOP,
								org.archstudio.graphlayout.IGraphLayout.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchipelagoPrefs",
						org.archstudio.archipelago.core.prefs.ArchipelagoPrefsMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchipelagoStructurePrefs",
						org.archstudio.archipelago.core.prefs.structure.ArchipelagoStructurePrefsMyxComponent.class
								.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArchADTModelEventPump",
						org.archstudio.xarchadt.IXArchADTModelListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("XArchADTFileEventPump",
						org.archstudio.xarchadt.IXArchADTFileListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("FileManagerEventPump",
						org.archstudio.filemanager.IFileManagerListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("FocusEditorEventPump",
						org.archstudio.editors.IFocusEditorListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createConnector(
						"LauncherMultiway",
						org.archstudio.myx.conn.SynchronousMultiwayProxyConnector.class.getName(),
						createSingletonProperties("interfaceClass0",
								org.archstudio.launcher.ILaunchable.class.getName()),
						createInterface("in", Direction.IN, DomainType.BOTTOM,
								org.archstudio.launcher.ILaunchable.class.getName()),
						createInterface("out", Direction.OUT, DomainType.TOP,
								org.archstudio.launcher.ILaunchable.class.getName()),
						createInterface("results", Direction.IN, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayResults.class.getName()),
						createInterface("progress", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayProgressListener.class.getName())));

		//Archlight
		xarch.add(
				structureRef,
				"component",
				createComponent(
						"IssueADT",
						org.archstudio.issueadt.core.ArchlightIssueADTMyxComponent.class.getName(),
						null,
						createInterface("archlightissueadt", Direction.IN, DomainType.BOTTOM,
								org.archstudio.issueadt.IArchlightIssueADT.class.getName()),
						createInterface("fileevents", Direction.IN, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADTFileListener.class.getName()),
						createInterface("archlightissueadtevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.issueadt.ArchlightIssueADTListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"NoticeADT",
						org.archstudio.noticeadt.core.ArchlightNoticeADTMyxComponent.class.getName(),
						null,
						createInterface("archlightnoticeadt", Direction.IN, DomainType.BOTTOM,
								org.archstudio.noticeadt.IArchlightNoticeADT.class.getName()),
						createInterface("archlightnoticeadtevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.noticeadt.ArchlightNoticeADTListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"TestADT",
						org.archstudio.testadt.core.ArchlightTestADTMyxComponent.class.getName(),
						null,
						createInterface("archlighttestadt", Direction.IN, DomainType.BOTTOM,
								org.archstudio.testadt.IArchlightTestADT.class.getName()),
						createInterface("archlighttestadtevents", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.testadt.ArchlightTestADTListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"IssueView",
						org.archstudio.issueview.core.ArchlightIssueViewMyxComponent.class.getName(),
						null,
						createInterface("archlightissueadt", Direction.OUT, DomainType.TOP,
								org.archstudio.issueadt.IArchlightIssueADT.class.getName()),
						createInterface("xarch", Direction.OUT, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class.getName()),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName()),
						createInterface("editormanager", Direction.OUT, DomainType.TOP,
								org.archstudio.editormanager.IEditorManager.class.getName()),
						createInterface("archlightissueadtevents", Direction.IN, DomainType.TOP,
								org.archstudio.issueadt.ArchlightIssueADTListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"NoticeView",
						org.archstudio.noticeview.core.ArchlightNoticeViewMyxComponent.class.getName(),
						null,
						createInterface("archlightnoticeadt", Direction.OUT, DomainType.TOP,
								org.archstudio.noticeadt.IArchlightNoticeADT.class.getName()),
						createInterface("resources", Direction.OUT, DomainType.TOP,
								org.archstudio.resources.IResources.class.getName()),
						createInterface("archlightnoticeadtevents", Direction.IN, DomainType.TOP,
								org.archstudio.noticeadt.ArchlightNoticeADTListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"Schematron",
						org.archstudio.schematron.core.SchematronMyxComponent.class.getName(),
						null,
						createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
								org.archstudio.archlight.IArchlightTool.class.getName()),
						createInterface("xarch", Direction.OUT, DomainType.TOP,
								org.archstudio.xarchadt.IXArchADT.class.getName()),
						createInterface("archlightissueadt", Direction.OUT, DomainType.TOP,
								org.archstudio.issueadt.IArchlightIssueADT.class.getName()),
						createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
								org.archstudio.testadt.IArchlightTestADT.class.getName()),
						createInterface("archlightnoticeadt", Direction.OUT, DomainType.TOP,
								org.archstudio.noticeadt.IArchlightNoticeADT.class.getName()),
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"SchematronPrefs",
						org.archstudio.schematron.core.prefs.SchematronPrefsMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		/*
		 * xarch.add(structureRef, "component", createComponent( "FlowChecker",
		 * org.archstudio.flowchecker.core.FlowCheckerMyxComponent.class.getName(), null,
		 * createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
		 * org.archstudio.archlight.IArchlightTool.class.getName()), createInterface("xarch", Direction.OUT,
		 * DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class.getName()), createInterface("archlightissueadt",
		 * Direction.OUT, DomainType.TOP, org.archstudio.issueadt.IArchlightIssueADT.class.getName()),
		 * createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
		 * org.archstudio.testadt.IArchlightTestADT.class.getName()), createInterface("archlightnoticeadt",
		 * Direction.OUT, DomainType.TOP, org.archstudio.noticeadt.IArchlightNoticeADT.class.getName()),
		 * createInterface("preferences", Direction.OUT, DomainType.TOP,
		 * org.eclipse.jface.preference.IPreferenceStore.class.getName()) ));
		 */

		/*
		 * xarch.add(structureRef, "component", createComponent( "MemoryChecker",
		 * org.archstudio.memorychecker.core.MemoryCheckerMyxComponent.class.getName(), null,
		 * createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
		 * org.archstudio.archlight.IArchlightTool.class.getName()), createInterface("xarch", Direction.OUT,
		 * DomainType.TOP, org.archstudio.xarchadt.IXArchADT.class.getName()), createInterface("archlightissueadt",
		 * Direction.OUT, DomainType.TOP, org.archstudio.issueadt.IArchlightIssueADT.class.getName()),
		 * createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
		 * org.archstudio.testadt.IArchlightTestADT.class.getName()), createInterface("archlightnoticeadt",
		 * Direction.OUT, DomainType.TOP, org.archstudio.noticeadt.IArchlightNoticeADT.class.getName()),
		 * createInterface("preferences", Direction.OUT, DomainType.TOP,
		 * org.eclipse.jface.preference.IPreferenceStore.class.getName()) ));
		 */

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchlightToolAggregator",
						org.archstudio.archlight.core.ArchlightToolAggregatorMyxComponent.class.getName(),
						null,
						createInterface("results", Direction.OUT, DomainType.BOTTOM,
								org.archstudio.myx.conn.IMultiwayResults.class.getName()),
						createInterface("archlighttools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class.getName()),
						createInterface("archlighttool", Direction.IN, DomainType.BOTTOM,
								org.archstudio.archlight.IArchlightTool.class.getName()),
						createInterface("progress", Direction.IN, DomainType.TOP,
								org.archstudio.myx.conn.IMultiwayProgressListener.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createEditorComponent(
						"Archlight",
						org.archstudio.archlight.core.ArchlightMyxComponent.class.getName(),
						null,
						createInterface("archlighttestadt", Direction.OUT, DomainType.TOP,
								org.archstudio.testadt.IArchlightTestADT.class.getName()),
						createInterface("archlighttools", Direction.OUT, DomainType.TOP,
								org.archstudio.archlight.IArchlightTool.class.getName())));

		xarch.add(
				structureRef,
				"component",
				createComponent(
						"ArchlightPrefs",
						org.archstudio.archlight.core.prefs.ArchlightPrefsMyxComponent.class.getName(),
						null,
						createInterface("preferences", Direction.OUT, DomainType.TOP,
								org.eclipse.jface.preference.IPreferenceStore.class.getName())));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("IssueADTEventPump",
						org.archstudio.issueadt.ArchlightIssueADTListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("NoticeADTEventPump",
						org.archstudio.noticeadt.ArchlightNoticeADTListener.class.getName()));

		xarch.add(
				structureRef,
				"connector",
				createDownwardEventPumpConnector("TestADTEventPump",
						org.archstudio.testadt.ArchlightTestADTListener.class.getName()));

		/*
		 * xarch.add(structureRef, "component", createEditorComponent( "FlowEditor",
		 * org.archstudio.floweditor.core.FlowEditorMyxComponent.class.getName(), null ));
		 */

		/*
		 * xarch.add(structureRef, "component", createEditorComponent( "HPCConfigurationEditor",
		 * org.archstudio.hpc.editor.HPCConfigurationEditorMyxComponent.class.getName(), null ));
		 */

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "AIM", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "fileevents"),
						getInterfaceRef(structureRef, "XArchADTFileEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "modelevents"),
						getInterfaceRef(structureRef, "XArchADTModelEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "AIM", "myxruntime"),
						getInterfaceRef(structureRef, "MyxRuntime", "myxruntime")));

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
						getInterfaceRef(structureRef, "FileManager", "fileevents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "XArchADTModelEventPump", "out"),
						getInterfaceRef(structureRef, "FileManager", "modelevents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "FileManager", "filemanagerevents"),
						getInterfaceRef(structureRef, "FileManagerEventPump", "in")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "EditorManager", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "EditorManager", "editormanager"),
						getInterfaceRef(structureRef, "EditorPrefs", "editormanager")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "EditorPrefs", "preferences")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "EditorManager", "focuseditorevents"),
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
				createLink(getInterfaceRef(structureRef, "GraphLayout", "graphlayout"),
						getInterfaceRef(structureRef, "Archipelago", "graphlayout")));

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
						getInterfaceRef(structureRef, "IssueADT", "fileevents")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "archlightissueadtevents"),
						getInterfaceRef(structureRef, "IssueADTEventPump", "in")));

		//  NoticeADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadtevents"),
						getInterfaceRef(structureRef, "NoticeADTEventPump", "in")));

		//  TestADT
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "archlighttestadtevents"),
						getInterfaceRef(structureRef, "TestADTEventPump", "in")));

		//  IssueView
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
						getInterfaceRef(structureRef, "IssueView", "archlightissueadt")));

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
				createLink(getInterfaceRef(structureRef, "EditorManager", "editormanager"),
						getInterfaceRef(structureRef, "IssueView", "editormanager")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADTEventPump", "out"),
						getInterfaceRef(structureRef, "IssueView", "archlightissueadtevents")));

		//  NoticeView
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
						getInterfaceRef(structureRef, "NoticeView", "archlightnoticeadt")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Resources", "resources"),
						getInterfaceRef(structureRef, "NoticeView", "resources")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADTEventPump", "out"),
						getInterfaceRef(structureRef, "NoticeView", "archlightnoticeadtevents")));

		//  Schematron
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "xArchADT", "xarch"),
						getInterfaceRef(structureRef, "Schematron", "xarch")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
						getInterfaceRef(structureRef, "Schematron", "archlightissueadt")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
						getInterfaceRef(structureRef, "Schematron", "archlighttestadt")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
						getInterfaceRef(structureRef, "Schematron", "archlightnoticeadt")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
						getInterfaceRef(structureRef, "Schematron", "preferences")));

		//  FlowChecker
		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "xArchADT", "xarch"),
		 * getInterfaceRef(structureRef, "FlowChecker", "xarch")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
		 * getInterfaceRef(structureRef, "FlowChecker", "archlightissueadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
		 * getInterfaceRef(structureRef, "FlowChecker", "archlighttestadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
		 * getInterfaceRef(structureRef, "FlowChecker", "archlightnoticeadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
		 * getInterfaceRef(structureRef, "FlowChecker", "preferences")));
		 */

		//  MemoryChecker
		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "xArchADT", "xarch"),
		 * getInterfaceRef(structureRef, "MemoryChecker", "xarch")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "IssueADT", "archlightissueadt"),
		 * getInterfaceRef(structureRef, "MemoryChecker", "archlightissueadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
		 * getInterfaceRef(structureRef, "MemoryChecker", "archlighttestadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "NoticeADT", "archlightnoticeadt"),
		 * getInterfaceRef(structureRef, "MemoryChecker", "archlightnoticeadt")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "PreferencesADT", "preferences"),
		 * getInterfaceRef(structureRef, "MemoryChecker", "preferences")));
		 */

		//  ArchlightToolAggregator
		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "Schematron", "archlighttool"),
						getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttools")));

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "FlowChecker", "archlighttool"),
		 * getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttools")));
		 */

		/*
		 * xarch.add(structureRef, "link", createLink( getInterfaceRef(structureRef, "MemoryChecker", "archlighttool"),
		 * getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttools")));
		 */

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
				createLink(getInterfaceRef(structureRef, "TestADT", "archlighttestadt"),
						getInterfaceRef(structureRef, "Archlight", "archlighttestadt")));

		xarch.add(
				structureRef,
				"link",
				createLink(getInterfaceRef(structureRef, "ArchlightToolAggregator", "archlighttool"),
						getInterfaceRef(structureRef, "Archlight", "archlighttools")));

		//Flow Editor
		/*
		 * for(ObjRef linkRef : createEditorLinks(structureRef, "FlowEditor")) { xarch.add(structureRef, "link",
		 * linkRef); }
		 */
		/*
		 * for(ObjRef linkRef : createEditorLinks(structureRef, "HPCConfigurationEditor")) { xarch.add(structureRef,
		 * "link", linkRef); }
		 */

		String xmlString = new String(xarch.serialize(docURI));
		System.err.println(xmlString);
	}

	static int nextID = 0;

	public static String getNewID() {
		return "id" + ++nextID;
	}
}
