package org.archstudio.archipelago.core.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.resources.common.ArchStudioCommonResources;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.swt.SWTTextThing;

public class StructureNewInterfaceLogic extends AbstractThingLogic implements IBNAMenuListener{
	protected static final String STRUCTURE_FACTORY = "org.archstudio.xadl3.structure_3_0";
	
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	protected List<SWTTextThing> openControls = Collections.synchronizedList(new ArrayList<SWTTextThing>());
	
	public StructureNewInterfaceLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public boolean matches(IBNAView view, IThing t){
		if(t instanceof BoxGlassThing){
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				return StructureMapper.isBrickAssemblyRootThing(pt);
			}
		}
		return false;
	}
	
	public String getXArchID(IBNAView view, IThing t){
		if(t instanceof BoxGlassThing){
			IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
			return parentThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		}
		return null;
	}
	
	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY){
		List<IThing> selectedThings = BNAUtils.getSelectedThings(view.getWorld().getBNAModel());
		if(selectedThings.size() > 1) return;

		if(matches(view, t)){
			for(IAction action : getActions(view, t, worldX, worldY)){
				m.add(action);
			}
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}
	
	protected IAction[] getActions(IBNAView view, IThing t, int worldX, int worldY){
		final IBNAView fview = view;
		final IThing ft = t;
		final int fworldX = worldX;
		final int fworldY = worldY;
		
		final String eltXArchID = getXArchID(view, t);
		if(eltXArchID == null){
			//Nothing to set description on
			return new IAction[0];
		}
		
		final ObjRef eltRef = AS.xarch.getByID(xArchRef, eltXArchID);
		if(eltRef == null){
			//Nothing to set description on
			return new IAction[0];
		}
		
		Action newInterfaceAction = new Action("New Interface", AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_INTERFACE)){
			public void run(){
				ObjRef interfaceRef = XadlUtils.create(AS.xarch, Structure_3_0Package.Literals.INTERFACE);
				AS.xarch.set(interfaceRef, "id", UIDGenerator.generateUID("interface"));
				XadlUtils.setName(AS.xarch, interfaceRef, "[New Interface]");
				AS.xarch.set(interfaceRef, "direction", Direction.NONE);
				AS.xarch.add(eltRef, "interface", interfaceRef);
			}
		};
		
		return new IAction[]{newInterfaceAction};
	}
	
}
