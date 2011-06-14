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
import org.archstudio.xadl3.structure_3_0.Direction;
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
import org.archstudio.bna.things.swt.SWTComboThing;

public abstract class AbstractEditDirectionLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener, IBNAMouseListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	protected List<SWTComboThing> openControls = Collections.synchronizedList(new ArrayList<SWTComboThing>());
	
	public AbstractEditDirectionLogic(ArchipelagoServices services, ObjRef xArchRef){
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
			//Nothing to set description on
			return new IAction[0];
		}
		
		ObjRef eltRef = AS.xarch.getByID(xArchRef, eltXArchID);
		if(eltRef == null){
			//Nothing to set description on
			return new IAction[0];
		}
		
		Direction oldDirection = (Direction)AS.xarch.get(eltRef, "direction");
		if(oldDirection == null) oldDirection = Direction.NONE;
		
		final Direction foldDirection = oldDirection;
		
		Action editDirectionAction = new Action("Edit Direction..."){
			public void run(){
				Point p = BNAUtils.getCentralPoint(ft);
				if(p == null) p = new Point(fworldX, fworldY);
				
				SWTComboThing lt = new SWTComboThing();
				lt.setProperty("#targetXArchID", eltXArchID);
				lt.setOptions(new String[]{"none", "in", "out", "inout"});
				lt.setAllowsArbitraryInput(false);
				lt.setText(foldDirection.getLiteral());
				lt.setAnchorPoint(p);
				lt.setMovesWithThingID(ft.getID());
				lt.setEditing(true);
				openControls.add(lt);
				fview.getWorld().getBNAModel().addThing(ft, lt);
			}
		};
		
		return new IAction[]{editDirectionAction};
	}
	
	public void bnaModelChanged(BNAModelEvent evt){
		if(evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED){
			if(evt.getTargetThing() instanceof SWTComboThing){
				SWTComboThing lt = (SWTComboThing)evt.getTargetThing();
				if(openControls.contains(lt)){
					if(lt.getCompletionStatus() == CompletionStatus.OK){
						String targetXArchID = lt.get("#targetXArchID");
						if(targetXArchID != null){
							ObjRef eltRef = AS.xarch.getByID(xArchRef, targetXArchID);
							if(eltRef != null){
								String newValue = lt.getText();
								if(newValue == null){
									AS.xarch.clear(eltRef, "direction");
								}
								else{
									Direction newDirectionValue = Direction.get(newValue);
									AS.xarch.set(eltRef, "direction", newDirectionValue);
								}
							}
						}
					}
					if(lt.getCompletionStatus() != CompletionStatus.INCOMPLETE){
						evt.getSource().removeThing(lt);
						openControls.remove(lt);
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
				SWTComboThing[] oc = openControls.toArray(new SWTComboThing[openControls.size()]);
				for(SWTComboThing tt : oc){
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
