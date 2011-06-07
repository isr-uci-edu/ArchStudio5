package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;

import org.omg.CORBA.CompletionStatus;

public class XadlEditDescriptionLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener,
		IBNAMouseListener {

	public static class DescriptionContext {

		public ObjRef parentRef;
		public String descriptionName;

		public DescriptionContext(ObjRef parentRef, String descriptionName) {
			this.parentRef = parentRef;
			this.descriptionName = descriptionName;
		}
	}

	protected List<SWTTextThing> openControls = Collections.synchronizedList(new ArrayList<SWTTextThing>());

	final protected XArchFlatInterface xarch;

	final protected ObjRef xArchRef;

	public XadlEditDescriptionLogic(XArchFlatInterface xarch, ObjRef xArchRef) {
		this.xarch = xarch;
		this.xArchRef = xArchRef;
	}

	public IThing matchingThing(IBNAView view, IThing t) {
		return t;
	}

	public DescriptionContext getDescriptionContext(IBNAView view, IThing t) {
		String xArchID = null;
		if (t != null) {
			if (xArchID == null) {
				xArchID = t.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
			}
			if (xArchID == null) {
				IAssembly assembly = AssemblyUtils.getAssemblyWithPart(t);
				if (assembly != null) {
					xArchID = assembly.getRootThing().getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
				}
			}
			if (xArchID != null) {
				ObjRef parentRef = xarch.getByID(xArchRef, xArchID);
				String descriptionName = "description";
				return new DescriptionContext(parentRef, descriptionName);
			}
		}
		return null;
	}

	public void fillMenu(final IBNAView view, IMenuManager m, int localX, int localY, final IThing t, final int worldX,
			final int worldY) {
		if (BNAUtils.getSelectedThings(view.getWorld().getBNAModel()).length <= 1) {
			IThing dt = matchingThing(view, t);
			if (dt != null) {
				final DescriptionContext dc = getDescriptionContext(view, t);
				if (dc != null && dc.parentRef != null && dc.descriptionName != null) {
					m.add(new Action("Edit Description...") {

						@Override
						public void run() {
							Point p = BNAUtils.getCentralPoint(t);
							if (p == null) {
								p = new Point(worldX, worldY);
							}
							SWTTextThing tt = new SWTTextThing(null);
							tt.setProperty("#descriptionContext", dc);
							tt.setText(XadlUtils.getDescription(xarch, dc.parentRef, dc.descriptionName, ""));
							tt.setAnchorPoint(p);
							MoveWithLogic.moveWith(t, MoveWithLogic.TRACK_ANCHOR_POINT_FIRST, tt);
							tt.setEditing(true);
							openControls.add(tt);
							view.getWorld().getBNAModel().addThing(tt, t);
						}
					});
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			if (evt.getTargetThing() instanceof SWTTextThing) {
				SWTTextThing tt = (SWTTextThing) evt.getTargetThing();
				if (openControls.contains(tt)) {
					if (tt.getCompletionStatus() == CompletionStatus.OK) {
						DescriptionContext dc = tt.getProperty("#descriptionContext");
						if (dc != null && dc.parentRef != null && dc.descriptionName != null) {
							XadlUtils.setDescription(xarch, dc.parentRef, dc.descriptionName, tt.getText());
						}
					}
					if (tt.getCompletionStatus() != CompletionStatus.INCOMPLETE) {
						evt.getSource().removeThing(tt);
						openControls.remove(tt);
					}
				}
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (openControls.size() > 0) {
			SWTTextThing[] oc = openControls.toArray(new SWTTextThing[openControls.size()]);
			for (SWTTextThing tt : oc) {
				tt.setCompletionStatus(CompletionStatus.OK);
				tt.setEditing(false);
			}
		}
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}
}
