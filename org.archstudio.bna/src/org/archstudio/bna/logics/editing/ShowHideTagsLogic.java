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

public class ShowHideTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

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
			else if (t instanceof EndpointGlassThing) {
				IThing parentThing = view.getWorld().getBNAModel().getParentThing(t);
				if (parentThing != null) {
					if (matchesEndpointAssembly(parentThing)) {
						fillMenuForEndpointAssembly(view, m, parentThing);
					}
				}
			}
			else if (t instanceof BoxGlassThing) {
				List<IThing> rootThingList = new ArrayList<IThing>();
				for (IThing selectedThing : BNAUtils.getSelectedThings(view.getWorld().getBNAModel())) {
					IThing parentThing = view.getWorld().getBNAModel().getParentThing(selectedThing);
					if ((parentThing != null) && matchesBoxAssembly(parentThing)) {
						rootThingList.add(parentThing);
					}
				}
				IThing[] rootThings = (IThing[]) rootThingList.toArray(new IThing[rootThingList.size()]);
				fillMenuForBoxAssemblies(view, m, rootThings);
			}
		}
	}

	protected void fillMenuForTagThing(IBNAView view, IMenuManager m, IThing t) {
		final TagThing tagThing = (TagThing) t;

		IAction hideAction = new Action("Hide Tag") {
			public void run() {
				tagThing.setVisible(false);
			}
		};
		m.add(hideAction);
		m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected void fillMenuForEndpointAssembly(final IBNAView view, IMenuManager m, IThing t) {
		IHasAssemblyData rootThing = (IHasAssemblyData) t;
		EndpointAssembly endpointAssembly = EndpointAssembly.attach(view.getWorld().getBNAModel(), rootThing);

		final EndpointGlassThing endpointGlassThing = endpointAssembly.getEndpointGlassThing();
		final TagThing tagThing = endpointAssembly.getTagThing();
		if ((tagThing != null) && (endpointGlassThing != null)) {
			if (tagThing.isVisible()) {
				IAction hideAction = new Action("Hide Tag") {
					public void run() {
						tagThing.setVisible(false);
					}
				};
				m.add(hideAction);
			}
			else {
				IAction showAction = new Action("Show Tag") {
					public void run() {
						Point cp = BNAUtils.getCentralPoint(endpointGlassThing);
						if (cp != null) {
							Point ap = new Point(cp.x + 20, cp.y - 20);
							tagThing.setAnchorPoint(ap);
						}
						tagThing.setVisible(true);
					}
				};
				m.add(showAction);
			}
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	protected void fillMenuForBoxAssemblies(final IBNAView view, IMenuManager m, IThing[] ts) {
		if (ts.length > 0) {
			final BoxAssembly[] boxAssemblies = new BoxAssembly[ts.length];
			for (int i = 0; i < ts.length; i++) {
				boxAssemblies[i] = BoxAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData) ts[i]);
			}
			IAction showAction = new Action("Show All Tags") {
				public void run() {
					for (int i = 0; i < boxAssemblies.length; i++) {
						IThing endpointBaseThing = boxAssemblies[i].getEndpointRootThing();
						for (IThing endpointRootThing : view.getWorld().getBNAModel().getChildThings(endpointBaseThing)) {
							if (matchesEndpointAssembly(endpointRootThing)) {
								EndpointAssembly endpointAssembly = EndpointAssembly.attach(view.getWorld().getBNAModel(),
								        (IHasAssemblyData) endpointRootThing);
								EndpointGlassThing egt = endpointAssembly.getEndpointGlassThing();
								TagThing tagThing = endpointAssembly.getTagThing();
								if ((egt != null) && (tagThing != null) && (!tagThing.isVisible())) {
									Point cp = BNAUtils.getCentralPoint(egt);
									if (cp != null) {
										Point ap = new Point(cp.x + 20, cp.y - 20);
										tagThing.setAnchorPoint(ap);
									}
								}
								if (tagThing != null) {
									endpointAssembly.getTagThing().setVisible(true);
								}
							}
						}
					}
				}
			};
			m.add(showAction);

			IAction hideAction = new Action("Hide All Tags") {
				public void run() {
					for (int i = 0; i < boxAssemblies.length; i++) {
						IThing endpointBaseThing = boxAssemblies[i].getEndpointRootThing();
						for (IThing endpointRootThing : view.getWorld().getBNAModel().getChildThings(endpointBaseThing)) {
							if (matchesEndpointAssembly(endpointRootThing)) {
								EndpointAssembly endpointAssembly = EndpointAssembly.attach(view.getWorld().getBNAModel(),
								        (IHasAssemblyData) endpointRootThing);
								endpointAssembly.getTagThing().setVisible(false);
							}
						}
					}
				}
			};
			m.add(hideAction);
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		}
	}
}
