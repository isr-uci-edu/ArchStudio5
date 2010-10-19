package org.archstudio.archipelago.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.things.swt.SWTTextThing;

public abstract class AbstractEditNameLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener, IBNAMouseListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	protected List<SWTTextThing> openControls = Collections.synchronizedList(new ArrayList<SWTTextThing>());
	
	public AbstractEditNameLogic(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public abstract boolean matches(IBNAView view, IThing t);
	public abstract String getXArchID(IBNAView view, IThing t);
	
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
			//Nothing to set name on
			return new IAction[0];
		}
		
		ObjRef eltRef = AS.xarch.getByID(xArchRef, eltXArchID);
		if(eltRef == null){
			//Nothing to set name on
			return new IAction[0];
		}
		
		String oldName = XadlUtils.getName(AS.xarch, eltRef);
		if(oldName == null) oldName = "";
		
		final String foldName = oldName;
		
		Action editNameAction = new Action("Edit Name..."){
			public void run(){
				Point p = BNAUtils.getCentralPoint(ft);
				if(p == null) p = new Point(fworldX, fworldY);
				
				SWTTextThing tt = new SWTTextThing();
				tt.setProperty("#targetXArchID", eltXArchID);
				tt.setText(foldName);
				tt.setAnchorPoint(p);
				tt.setMovesWithThingID(ft.getID());
				tt.setEditing(true);
				openControls.add(tt);
				fview.getWorld().getBNAModel().addThing(tt, ft);
			}
		};
		
		return new IAction[]{editNameAction};
	}
	
	public void bnaModelChanged(BNAModelEvent evt){
		if(evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED){
			if(evt.getTargetThing() instanceof SWTTextThing){
				SWTTextThing tt = (SWTTextThing)evt.getTargetThing();
				if(openControls.contains(tt)){
					if(tt.getCompletionStatus() == CompletionStatus.OK){
						String targetXArchID = tt.getProperty("#targetXArchID");
						if(targetXArchID != null){
							ObjRef eltRef = AS.xarch.getByID(xArchRef, targetXArchID);
							if(eltRef != null){
								String newValue = tt.getText();
								if(newValue == null){
									AS.xarch.clear(eltRef, "name");
								}
								else{
									XadlUtils.setName(AS.xarch, eltRef, newValue);
								}
							}
						}
					}
					if(tt.getCompletionStatus() != CompletionStatus.INCOMPLETE){
						evt.getSource().removeThing(tt);
						openControls.remove(tt);
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
				SWTTextThing[] oc = openControls.toArray(new SWTTextThing[openControls.size()]);
				for(SWTTextThing tt : oc){
					tt.setCompletionStatus(CompletionStatus.CANCEL);
					tt.setEditing(false);
				}
			}
		}
	}
	
	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
	}
	
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY){
	}
}
