package org.archstudio.xarchadt.variability.ui.actions;

import java.util.List;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.VariabilityUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;

public class AddChangeSetAction extends Action implements IHasXArchRef {

	private static int count = 0;

	IXArchADTVariability xarch;

	ObjRef xArchRef = null;

	public AddChangeSetAction(IXArchADTVariability xarch) {
		super("Add Change Set");
		setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "add_change_set.gif"));
		this.xarch = xarch;
		setToolTipText(getText());
		setXArchRef(null);
	}

	@Override
	public void run() {
		try {
			if (xArchRef != null) {
				Variability variability = VariabilityUtils.getVariability(xarch, xArchRef);
				if (variability != null) {
					Variability_3_0Factory factory = XArchADTProxy.proxy(xarch, Variability_3_0Package.eNS_URI);
					ChangeSet changeSet = factory.createChangeSetOfChanges();
					changeSet.setId(UIDGenerator.generateUID());
					changeSet.setName("[New Change Set " + ++count + "]");
					variability.getChangeSet().add(changeSet);
					List<ObjRef> appliedChangeSets = xarch.getAppliedChangeSets(xArchRef);
					appliedChangeSets.add(XArchADTProxy.unproxy(changeSet));
					xarch.applyChangeSets(xArchRef, appliedChangeSets);
					xarch.setActiveChangeSet(xArchRef, XArchADTProxy.unproxy(changeSet));
				}
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
			MessageDialog.openError(null, "Error", "Unable to create change set: " + t.getMessage());
		}
	}

	@Override
	public ObjRef getXArchRef() {
		return xArchRef;
	}

	@Override
	public void setXArchRef(ObjRef xArchRef) {
		this.xArchRef = xArchRef;
		setEnabled(xArchRef != null && VariabilityUtils.getVariability(xarch, xArchRef) != null);
	}
}