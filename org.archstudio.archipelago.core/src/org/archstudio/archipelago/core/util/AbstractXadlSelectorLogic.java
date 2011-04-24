package org.archstudio.archipelago.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.things.SWTXadlSelectorThing;
import org.archstudio.xadlswt.XadlTreeUtils;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.CompletionStatus;

public abstract class AbstractXadlSelectorLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener, IBNAMouseListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	protected List<SWTXadlSelectorThing> openControls = Collections.synchronizedList(new ArrayList<SWTXadlSelectorThing>());
	
	public AbstractXadlSelectorLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}

	public abstract boolean matches(IBNAView view, IThing t);
	public abstract String getXArchID(IBNAView view, IThing t);
	
	public abstract String getMenuItemString();
	public abstract ImageDescriptor getMenuItemIcon(ObjRef eltRef);
	public abstract Object getInitialValue(ObjRef eltRef);
	public abstract ObjRef getRootRef(ObjRef eltRef);
	public abstract Set<XadlTreeUtils.Type> getFlags(ObjRef eltRef);
	public abstract void setValue(ObjRef eltRef, Object newValue);
	
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
		final ArchipelagoServices fAS = AS;
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
		
		final Object finitialValue = getInitialValue(eltRef);
		
		Action action = new Action(getMenuItemString()){
			public void run(){
				Point p = BNAUtils.getCentralPoint(ft);
				if(p == null) p = new Point(fworldX, fworldY);
				
				SWTXadlSelectorThing st = new SWTXadlSelectorThing();
				st.setProperty("#targetXArchID", eltXArchID);
				st.setRepository(fAS.xarch);
				st.setResources(fAS.resources);
				st.setContentProviderRootRef(getRootRef(eltRef));
				st.setContentProviderFlags(getFlags(eltRef));

				if(finitialValue != null){
					st.setValue(finitialValue);
				}
				
				st.setAnchorPoint(p);
				st.setMovesWithThingID(ft.getID());
				st.setEditing(true);
				openControls.add(st);
				fview.getWorld().getBNAModel().addThing(st, ft);
			}
		};
		ImageDescriptor icon = getMenuItemIcon(eltRef);
		if(icon != null){
			action.setImageDescriptor(icon);
		}
		return new IAction[]{action};
	}
	
	public void bnaModelChanged(BNAModelEvent evt){
		if(evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED){
			if(evt.getTargetThing() instanceof SWTXadlSelectorThing){
				SWTXadlSelectorThing st = (SWTXadlSelectorThing)evt.getTargetThing();
				if(openControls.contains(st)){
					if(st.getCompletionStatus() == CompletionStatus.OK){
						String targetXArchID = st.getProperty("#targetXArchID");
						if(targetXArchID != null){
							ObjRef eltRef = AS.xarch.getByID(xArchRef, targetXArchID);
							if(eltRef != null){
								setValue(eltRef, st.getValue());
							}
						}
					}
					if(st.getCompletionStatus() != CompletionStatus.INCOMPLETE){
						evt.getSource().removeThing(st);
						openControls.remove(st);
					}
				}
			}
		}
	}
	
	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
	}
	
	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
		if(evt.button == 1){
			if(openControls.size() > 0){
				SWTXadlSelectorThing[] oc = openControls.toArray(new SWTXadlSelectorThing[openControls.size()]);
				for(SWTXadlSelectorThing st : oc){
					st.setCompletionStatus(CompletionStatus.CANCEL);
					st.setEditing(false);
				}
			}
		}
	}
	
	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
	}
	
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
	}
}
