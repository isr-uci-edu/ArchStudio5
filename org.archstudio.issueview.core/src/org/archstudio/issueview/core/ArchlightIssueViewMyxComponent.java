package org.archstudio.issueview.core;

import org.archstudio.editormanager.common.IEditorManager;
import org.archstudio.issueadt.common.ArchlightIssueADTEvent;
import org.archstudio.issueadt.common.ArchlightIssueADTListener;
import org.archstudio.issueadt.common.IArchlightIssueADT;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.resources.common.IResources;
import org.archstudio.xarchadt.common.IXArchADT;
import org.eclipse.jface.preference.IPreferenceStore;

public class ArchlightIssueViewMyxComponent extends AbstractMyxSimpleBrick implements ArchlightIssueADTListener, IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_OUT_ISSUEADT = MyxUtils.createName("archlightissueadt");
	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_OUT_RESOURCES = MyxUtils.createName("resources");
	public static final IMyxName INTERFACE_NAME_OUT_PREFS = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_OUT_EDITORMANAGER = MyxUtils.createName("editormanager");
	public static final IMyxName INTERFACE_NAME_IN_ISSUEADTEVENTS = MyxUtils.createName("archlightissueadtevents");

	private MyxRegistry er = MyxRegistry.getSharedInstance();

	//protected static ArchlightIssueViewMyxComponent sharedInstance = null;
	protected IXArchADT xarch = null;
	protected IArchlightIssueADT issueadt = null;
	protected IResources resources = null;
	protected IEditorManager editorManager = null;
	protected IPreferenceStore prefs = null;

	public ArchlightIssueViewMyxComponent() {
	}

	public void begin() {
		er.register(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT)) {
			issueadt = (IArchlightIssueADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = (IXArchADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)) {
			resources = (IResources) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_EDITORMANAGER)) {
			editorManager = (IEditorManager) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFS)) {
			prefs = (IPreferenceStore) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT)) {
			issueadt = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)) {
			resources = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_EDITORMANAGER)) {
			editorManager = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFS)) {
			prefs = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public void end() {
		er.unregister(this);
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_ISSUEADTEVENTS)) {
			return this;
		}
		return null;
	}

	public void issueADTChanged(ArchlightIssueADTEvent evt) {
		for (Object o : er.getObjects(this)) {
			if (o instanceof ArchlightIssueADTListener) {
				((ArchlightIssueADTListener) o).issueADTChanged(evt);
			}
		}
	}

}
