package org.archstudio.archipelago.core.util;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.utility.WorldThing;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.FlyToUtils;
import org.archstudio.resources.IResources;
import org.archstudio.resources.ResourceCache;
import org.archstudio.swtutils.DefaultFindResult;
import org.archstudio.swtutils.IFindResult;
import org.archstudio.swtutils.IFinder;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Link;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Point;

public class ArchipelagoFinder implements IFinder<IBNAView> {

	protected IXArchADT xarch;
	protected IResources resources;

	public ArchipelagoFinder(IXArchADT xarch, IResources resources) {
		this.xarch = xarch;
		this.resources = resources;
	}

	@Override
	public IFindResult[] find(IBNAView context, String search) {
		List<IFindResult> resultList = new ArrayList<IFindResult>();
		find(context, context.getBNAWorld().getBNAModel(), search, "", resultList);
		Collections.sort(resultList);
		return resultList.toArray(new IFindResult[resultList.size()]);
	}

	@Override
	public void selected(IFindResult selectedResult) {
		Object o = selectedResult.getData();
		if (o != null && o instanceof FindResultData) {
			final FindResultData frd = (FindResultData) o;
			FlyToUtils.flyTo(frd.view, frd.p);

			IThing t = frd.t;
			ArchipelagoUtils.pulseNotify(frd.view.getBNAWorld().getBNAModel(), t);
		}
	}

	protected void find(IBNAView context, IBNAModel m, String search, String prefix, List<IFindResult> resultList) {
		for (IThing t : m.getAllThings()) {
			IFindResult r = null;
			IThing assembly = Assemblies.isRoot(t) ? t : null;
			ObjRef objRef = assembly != null ? assembly.get(IHasObjRef.OBJREF_KEY) : null;
			if (assembly != null && objRef != null
					&& XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.COMPONENT)) {
				String text = Assemblies.BOUNDED_TEXT_KEY.get(assembly, m).getText();
				if (matches(search, text)) {
					r = createFindResult(context, assembly, prefix, text,
							ImageDescriptor.createFromImage(ResourceCache.getIcon(Component.class)));
				}
				find(context, assembly, search, text, resultList);
			}
			if (assembly != null && objRef != null
					&& XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.CONNECTOR)) {
				String text = Assemblies.BOUNDED_TEXT_KEY.get(assembly, m).getText();
				if (matches(search, text)) {
					r = createFindResult(context, assembly, prefix, text,
							ImageDescriptor.createFromImage(ResourceCache.getIcon(Connector.class)));
				}
				find(context, assembly, search, text, resultList);
			}
			if (assembly != null && objRef != null
					&& XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.INTERFACE)) {
				String text = ToolTipLogic.getToolTip(assembly);
				if (matches(search, text)) {
					r = createFindResult(context, assembly, prefix, text,
							ImageDescriptor.createFromImage(ResourceCache.getIcon(Interface.class)));
				}
			}
			if (assembly != null && objRef != null
					&& XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.LINK)) {
				String text = ToolTipLogic.getToolTip(assembly);
				if (matches(search, text)) {
					r = createFindResult(context, assembly, prefix, text,
							ImageDescriptor.createFromImage(ResourceCache.getIcon(Link.class)));
				}
			}
			if (assembly != null && objRef != null
					&& XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.INTERFACE_MAPPING)) {
				String text = ToolTipLogic.getToolTip(assembly);
				if (matches(search, text)) {
					r = createFindResult(context, assembly, prefix, text,
							ImageDescriptor.createFromImage(ResourceCache.getIcon(InterfaceMapping.class)));
				}
			}
			if (r != null) {
				resultList.add(r);
			}
		}
	}

	protected static boolean matches(String query, String target) {
		if (query == null || target == null) {
			return false;
		}
		query = query.toLowerCase();
		target = target.toLowerCase();
		return target.indexOf(query) != -1;
	}

	protected void find(IBNAView context, IThing boxAssembly, String search, String prefix, List<IFindResult> resultList) {
		WorldThing wt = castOrNull(Assemblies.WORLD_KEY.get(boxAssembly, context.getBNAWorld().getBNAModel()),
				WorldThing.class);
		if (wt != null) {
			@SuppressWarnings("unchecked")
			WorldThingPeer<WorldThing> wtp = castOrNull(context.getThingPeer(wt), WorldThingPeer.class);
			if (wtp != null) {
				IBNAWorld world = wt.getWorld();
				if (world != null) {
					IBNAModel internalModel = world.getBNAModel();
					find(wtp.getInnerView(), internalModel, search, prefix + "/", resultList);
				}
			}
		}
	}

	protected IFindResult createFindResult(IBNAView view, IThing t, String prefix, String text, ImageDescriptor image) {
		if (text == null) {
			return null;
		}
		Point2D p = BNAUtils.getCentralPoint(t);
		if (p == null) {
			return null;
		}
		if (prefix == null) {
			prefix = "";
		}
		return new DefaultFindResult(new FindResultData(view, t, BNAUtils.toPoint(p)), prefix + text, image);
	}

	class FindResultData {
		public IBNAView view;
		public IThing t;
		public Point p;

		public FindResultData(IBNAView view, IThing t, Point p) {
			this.view = view;
			this.t = t;
			this.p = p;
		}
	}

}
