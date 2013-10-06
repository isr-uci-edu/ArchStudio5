package org.archstudio.archipelago.core.structure;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collection;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.resources.ArchStudioCommonResources;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

public class StructureNewInterfaceLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected final IXArchADT xarch;
	protected final IResources resources;

	public StructureNewInterfaceLogic(IBNAWorld world, IXArchADT xarch, IResources resources) {
		super(world);
		this.xarch = xarch;
		this.resources = resources;
	}

	protected boolean matches(IBNAView view, IThing t) {
		if (t != null) {
			ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
			if (XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.BRICK)) {
				return true;
			}
		}
		return false;
	}

	@Override
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		Collection<IThing> selectedThings = BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel());
		if (selectedThings.size() > 1) {
			return;
		}

		IThing t = Assemblies.getThingWithProperty(model, firstOrNull(things), IHasObjRef.OBJREF_KEY);
		if (matches(view, t)) {
			Point world = location.getWorldPoint();
			for (IAction action : getActions(view, t, world.x, world.y)) {
				m.add(action);
			}
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	protected IAction[] getActions(IBNAView view, IThing t, int worldX, int worldY) {

		final ObjRef eltRef = t.get(IHasObjRef.OBJREF_KEY);
		if (eltRef == null) {
			//Nothing to create an interface on
			return new IAction[0];
		}

		Action newInterfaceAction = new Action("New Interface",
				resources.getImageDescriptor(ArchStudioCommonResources.ICON_INTERFACE)) {

			@Override
			public void run() {
				ObjRef interfaceRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE);
				xarch.set(interfaceRef, "id", UIDGenerator.generateUID("interface"));
				XadlUtils.setName(xarch, interfaceRef, "[New Interface]");
				xarch.set(interfaceRef, "direction", Direction.NONE);
				XArchADTOperations.add("Add Interface", xarch, eltRef, "interface", interfaceRef);
			}
		};

		return new IAction[] { newInterfaceAction };
	}

}
