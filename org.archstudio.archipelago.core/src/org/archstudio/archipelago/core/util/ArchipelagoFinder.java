package org.archstudio.archipelago.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.StructureMapper;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.utility.WorldThing;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.FlyToUtils;
import org.archstudio.resources.ArchStudioCommonResources;
import org.archstudio.swtutils.DefaultFindResult;
import org.archstudio.swtutils.IFindResult;
import org.archstudio.swtutils.IFinder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Point;

public class ArchipelagoFinder implements IFinder<IBNAView>{

	protected ArchipelagoServices AS = null;
	
	public ArchipelagoFinder(ArchipelagoServices AS){
		this.AS = AS;
	}
	
	@SuppressWarnings("unchecked")
	public IFindResult[] find(IBNAView context, String search){
		List<IFindResult> resultList = new ArrayList<IFindResult>();
		find(context, context.getWorld().getBNAModel(), search, "", resultList);
		Collections.sort(resultList);
		return resultList.toArray(new IFindResult[resultList.size()]);
	}
	
	public void selected(IFindResult selectedResult){
		Object o = selectedResult.getData();
		if((o != null) && (o instanceof FindResultData)){
			final FindResultData frd = (FindResultData)o;
			FlyToUtils.flyTo(frd.view, frd.p.x, frd.p.y);
			
			IThing t = frd.t;
			ArchipelagoUtils.pulseNotify(frd.view.getWorld().getBNAModel(), t);
		}
	}
	
	protected void find(IBNAView context, IBNAModel m, String search, String prefix, List<IFindResult> resultList){
		for(IThing t : m.getAllThings()){
			IFindResult r = null;
			if(StructureMapper.isComponentAssemblyRootThing(t)){
				BoxAssembly a = BoxAssembly.attach(m, (IHasAssemblyData)t);
				if(a.getBoxedLabelThing() != null){
					if(matches(search, a.getBoxedLabelThing().getText())){
						r = createFindResult(context, a.getBoxedLabelThing(), prefix, a.getBoxedLabelThing().getText(), AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_COMPONENT));
					}
				}
				find(context, a, search, resultList);
			}
			else if(StructureMapper.isConnectorAssemblyRootThing(t)){
				BoxAssembly a = BoxAssembly.attach(m, (IHasAssemblyData)t);
				if(a.getBoxedLabelThing() != null){
					if(matches(search, a.getBoxedLabelThing().getText())){
						r = createFindResult(context, a.getBoxedLabelThing(), prefix, a.getBoxedLabelThing().getText(), AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_CONNECTOR));
					}
				}
				find(context, a, search, resultList);
			}
			else if(StructureMapper.isInterfaceAssemblyRootThing(t)){
				EndpointAssembly a = EndpointAssembly.attach(m, (IHasAssemblyData)t);
				if(a.getEndpointGlassThing() != null){
					if(matches(search, a.getEndpointGlassThing().getToolTip())){
						r = createFindResult(context, a.getEndpointGlassThing(), prefix, a.getEndpointGlassThing().getToolTip(), AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_INTERFACE));
					}
				}
			}
			else if(StructureMapper.isLinkAssemblyRootThing(t)){
				StickySplineAssembly a = StickySplineAssembly.attach(m, (IHasAssemblyData)t);
				if(a.getSplineGlassThing() != null){
					if(matches(search, a.getSplineGlassThing().getToolTip())){
						r = createFindResult(context, a.getSplineGlassThing(), prefix, a.getSplineGlassThing().getToolTip(), AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_LINK));
					}
				}
			}
			if(r != null){
				resultList.add(r);
			}
		}
	}
	
	protected static boolean matches(String query, String target){
		if((query == null) || (target == null)) return false;
		query = query.toLowerCase();
		target = target.toLowerCase();
		return (target.indexOf(query) != -1);
	}
	
	protected void find(IBNAView context, BoxAssembly ba, String search, List<IFindResult> resultList){
		String prefix = "[No Description]/";
		if(ba.getBoxedLabelThing() != null){
			String text = ba.getBoxedLabelThing().getText();
			if(text != null){
				prefix = text + "/";
			}
		}
		
		WorldThing wt = ba.getWorldThing();
		
		WorldThingPeer wtp = (WorldThingPeer)context.getPeer(wt);
		
		if(wt != null){
			IBNAWorld world = wt.getWorld();
			if(world != null){
				IBNAModel internalModel = world.getBNAModel();
				find(wtp.getInnerView(), internalModel, search, prefix, resultList);
			}
		}
	}
	
	protected IFindResult createFindResult(IBNAView view, IThing t, String prefix, String text, ImageDescriptor image){
		if(text == null){
			return null;
		}
		Point p = BNAUtils.getCentralPoint(t);
		if(p == null){
			return null;
		}
		if(prefix == null) prefix = "";
		return new DefaultFindResult(new FindResultData(view, t, p), prefix + text, image);
	}
	
	class FindResultData{
		public IBNAView view;
		public IThing t;
		public Point p;
		
		public FindResultData(IBNAView view, IThing t, Point p){
			this.view = view;
			this.t = t;
			this.p = p;
		}
	}
	
}
