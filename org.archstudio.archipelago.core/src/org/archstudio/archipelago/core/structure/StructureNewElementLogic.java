package org.archstudio.archipelago.core.structure;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.resources.common.ArchStudioCommonResources;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;

public class StructureNewElementLogic extends AbstractThingLogic implements IBNAMenuListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	public StructureNewElementLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public boolean matches(IBNAView view, IThing t){
		return t == null;
	}
	
	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY){
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
		
		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(view.getWorld().getBNAModel());

		String archStructureXArchID = ept.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		if(archStructureXArchID == null){
			//Nothing to set description on
			return new IAction[0];
		}
		
		final ObjRef archStructureRef = AS.xarch.getByID(xArchRef, archStructureXArchID);
		if(archStructureRef == null){
			//Nothing to add elements to
			return new IAction[0];
		}
		
		ArchipelagoUtils.setNewThingSpot(view.getWorld().getBNAModel(), fworldX, fworldY);
		
		Action newComponentAction = new Action("New Component"){
			public void run(){
				ObjRef componentRef = XadlUtils.create(AS.xarch, Structure_3_0Package.Literals.COMPONENT);
				AS.xarch.set(componentRef, "id", UIDGenerator.generateUID("component"));
				XadlUtils.setName(AS.xarch, componentRef, "[New Component]");
				AS.xarch.add(archStructureRef, "component", componentRef);
			}
			
			public ImageDescriptor getImageDescriptor(){
				return AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_COMPONENT);
			}
		};
		
		
		Action newConnectorAction = new Action("New Connector"){
			public void run(){
				ObjRef connectorRef = XadlUtils.create(AS.xarch, Structure_3_0Package.Literals.CONNECTOR);
				AS.xarch.set(connectorRef, "id", UIDGenerator.generateUID("connector"));
				XadlUtils.setName(AS.xarch, connectorRef, "[New Connector]");
				AS.xarch.add(archStructureRef, "connector", connectorRef);
			}

			public ImageDescriptor getImageDescriptor(){
				return AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_CONNECTOR);
			}
		};
		
		Action newLinkAction = new Action("New Link"){
			public void run(){
				ObjRef linkRef = XadlUtils.create(AS.xarch, Structure_3_0Package.Literals.LINK);
				AS.xarch.set(linkRef, "id", UIDGenerator.generateUID("link"));
				XadlUtils.setName(AS.xarch, linkRef, "[New Link]");
				AS.xarch.add(archStructureRef, "link", linkRef);
			}
			
			public ImageDescriptor getImageDescriptor(){
				return AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_LINK);
			}
		};
		
		return new IAction[]{newComponentAction, newConnectorAction, newLinkAction};
	}
	
}
