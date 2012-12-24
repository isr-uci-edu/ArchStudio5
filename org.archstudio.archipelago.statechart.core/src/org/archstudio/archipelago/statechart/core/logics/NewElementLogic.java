package org.archstudio.archipelago.statechart.core.logics;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.statechart_1_0.StateType;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

public class NewElementLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected final IXArchADT xarch;
	protected final IResources resources;
	protected final ObjRef structureRef;

	public NewElementLogic(IXArchADT xarch, IResources resources, ObjRef structureRef) {
		this.xarch = xarch;
		this.resources = resources;
		this.structureRef = structureRef;
	}

	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		if (things.size() == 0) {
			Point world = location.getWorldPoint();
			for (IAction action : getActions(view, SystemUtils.firstOrNull(things), world.x, world.y)) {
				m.add(action);
			}
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	protected IAction[] getActions(IBNAView view, IThing t, int worldX, int worldY) {

		ArchipelagoUtils.setNewThingSpot(view.getBNAWorld().getBNAModel(), worldX, worldY);

		return new IAction[] {//
		new Action("New State") {

			public void run() {
				ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.STATE);
				xarch.set(newRef, "id", UIDGenerator.generateUID("state"));
				xarch.set(newRef, "type", StateType.STATE);
				XadlUtils.setName(xarch, newRef, "[New State]");
				XArchADTOperations.add("New State", xarch, structureRef, "state", newRef);
			}

			public ImageDescriptor getImageDescriptor() {
				return null;
			}
		},

		new Action("New Transition") {

			public void run() {
				ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.TRANSITION);
				xarch.set(newRef, "id", UIDGenerator.generateUID("transition"));
				XadlUtils.setName(xarch, newRef, "[New Transition]");
				XArchADTOperations.add("New Transition", xarch, structureRef, "transition", newRef);
			}

			public ImageDescriptor getImageDescriptor() {
				return null;
			}
		},

		new Action("New Initial State") {

			public void run() {
				ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.STATE);
				xarch.set(newRef, "id", UIDGenerator.generateUID("initialState"));
				xarch.set(newRef, "type", StateType.INITIAL);
				XadlUtils.setName(xarch, newRef, "[New Initial State]");
				XArchADTOperations.add("New Initial State", xarch, structureRef, "state", newRef);
			}

			public ImageDescriptor getImageDescriptor() {
				return null;
			}
		},

		new Action("New Final State") {

			public void run() {
				ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.STATE);
				xarch.set(newRef, "id", UIDGenerator.generateUID("finalState"));
				xarch.set(newRef, "type", StateType.FINAL);
				XadlUtils.setName(xarch, newRef, "[New Final State]");
				XArchADTOperations.add("New Final State", xarch, structureRef, "state", newRef);
			}

			public ImageDescriptor getImageDescriptor() {
				return null;
			}
		} };
	}

}
