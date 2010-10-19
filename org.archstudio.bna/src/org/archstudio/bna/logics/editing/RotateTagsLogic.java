package org.archstudio.bna.logics.editing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.things.utility.RotaterThing;

public class RotateTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected boolean matchesBoxAssembly(IThing rootThing) {
		if ((rootThing != null) && (rootThing instanceof IHasAssemblyData)) {
			IHasAssemblyData art = (IHasAssemblyData) rootThing;
			String assemblyKind = art.getAssemblyKind();
			if ((assemblyKind != null) && (assemblyKind.equals(BoxAssembly.ASSEMBLY_KIND))) {
				return true;
			}
		}
		return false;
	}

	protected boolean matchesEndpointAssembly(IThing rootThing) {
		if ((rootThing != null) && (rootThing instanceof IHasAssemblyData)) {
			IHasAssemblyData art = (IHasAssemblyData) rootThing;
			String assemblyKind = art.getAssemblyKind();
			if ((assemblyKind != null) && (assemblyKind.equals(EndpointAssembly.ASSEMBLY_KIND))) {
				return true;
			}
		}
		return false;
	}

	protected boolean matchesTagThing(IThing rootThing) {
		if ((rootThing != null) && (rootThing instanceof TagThing)) {
			return true;
		}
		return false;
	}

	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY) {
		if (t != null) {
			if ((t instanceof TagThing) && matchesTagThing(t)) {
				fillMenuForTagThing(view, m, t);
			}
			else if (t instanceof BoxGlassThing) {
				List<IThing> selectedThings = BNAUtils.getSelectedThings(view.getWorld().getBNAModel());
				List<IThing> rootThingList = new ArrayList<IThing>(selectedThings.size());
				for (IThing selectedThing : selectedThings) {
					IThing parentThing = view.getWorld().getBNAModel().getParentThing(selectedThing);
					if ((parentThing != null) && matchesBoxAssembly(parentThing)) {
						rootThingList.add(parentThing);
					}
				}
				IThing[] rootThings = (IThing[]) rootThingList.toArray(new IThing[rootThingList.size()]);
				fillMenuForBoxAssemblies(view, m, rootThings, worldX, worldY);
			}
		}
	}

	protected void fillMenuForTagThing(final IBNAView view, IMenuManager m, IThing t) {
		final TagThing tagThing = (TagThing) t;

		IAction hideAction = new Action("Rotate Tag") {
			public void run() {
				RotaterThing rt = new RotaterThing();
				rt.setAnchorPoint(tagThing.getAnchorPoint());
				rt.setAngle(tagThing.getAngle());
				rt.setMovesWithThingID(tagThing.getID());
				rt.setRotatedThingIDs(new String[] { tagThing.getID() });
				view.getWorld().getBNAModel().addThing(rt);
			}
		};
		m.add(hideAction);
		m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected void fillMenuForBoxAssemblies(final IBNAView view, IMenuManager m, IThing[] ts, final int worldX, final int worldY) {
		if (ts.length > 0) {
			final BoxAssembly[] boxAssemblies = new BoxAssembly[ts.length];
			for (int i = 0; i < ts.length; i++) {
				boxAssemblies[i] = BoxAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData) ts[i]);
			}
			IAction rotateAllTagsAction = new Action("Rotate All Tags") {
				public void run() {
					List<String> tagThingIDList = new ArrayList<String>();
					for (int i = 0; i < boxAssemblies.length; i++) {
						IThing endpointBaseThing = boxAssemblies[i].getEndpointRootThing();
						
						for (IThing endpointRootThing : view.getWorld().getBNAModel().getChildThings(endpointBaseThing)) {
							if (matchesEndpointAssembly(endpointRootThing)) {
								EndpointAssembly endpointAssembly = EndpointAssembly.attach(view.getWorld().getBNAModel(),
								        (IHasAssemblyData) endpointRootThing);
								EndpointGlassThing egt = endpointAssembly.getEndpointGlassThing();
								TagThing tagThing = endpointAssembly.getTagThing();
								if ((egt != null) && (tagThing != null) && (tagThing.isVisible())) {
									tagThingIDList.add(tagThing.getID());
								}
							}
						}
					}
					String[] tagThingIDs = tagThingIDList.toArray(new String[tagThingIDList.size()]);
					if (tagThingIDs.length > 0) {
						RotaterThing rt = new RotaterThing();
						rt.setAnchorPoint(new Point(worldX, worldY));
						rt.setAngle(0);
						rt.setRotatedThingIDs(tagThingIDs);
						view.getWorld().getBNAModel().addThing(rt);
					}
				}
			};
			m.add(rotateAllTagsAction);
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}
}
