package org.archstudio.archipelago.core.structure;

import org.eclipse.swt.graphics.RGB;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractEditColorLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.SplineAssembly;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.shapes.BoxThing;
import org.archstudio.bna.things.shapes.SplineThing;

public class StructureEditColorLogic extends AbstractEditColorLogic{
	protected ArchipelagoServices AS = null;
	
	public StructureEditColorLogic(ArchipelagoServices services){
		super();
		this.AS = services;
	}
	
	protected boolean matches(IBNAView view, IThing t){
		IThing pt = view.getWorld().getBNAModel().getParentThing(t);
		if(pt != null){
			if((t instanceof BoxGlassThing) && StructureMapper.isBrickAssemblyRootThing(pt)){
				return true;
			}
			else if(StructureMapper.isLinkAssemblyRootThing(pt)){
				return true;
			}
		}
		return false;
	}
	
	protected RGB getDefaultRGB(IBNAView view, IThing[] thingsToEdit){
		RGB defaultRGB = null;
		RGB defaultComponentRGB = StructureMapper.getDefaultComponentColor(AS);
		RGB defaultConnectorRGB = StructureMapper.getDefaultConnectorColor(AS);
		for(int i = 0; i < thingsToEdit.length; i++){
			IThing pt = view.getWorld().getBNAModel().getParentThing(thingsToEdit[i]);
			if(pt != null){
				if(StructureMapper.isComponentAssemblyRootThing(pt)){
					if((defaultRGB == null) || (BNAUtils.nulleq(defaultRGB, defaultComponentRGB))){
						defaultRGB = defaultComponentRGB;
					}
					else{
						return null;
					}
				}
				else if(StructureMapper.isConnectorAssemblyRootThing(pt)){
					if((defaultRGB == null) || (BNAUtils.nulleq(defaultRGB, defaultConnectorRGB))){
						defaultRGB = defaultConnectorRGB;
					}
					else{
						return null;
					}
				}
			}
		}
		return defaultRGB;
	}
	
	protected IHasMutableColor getColoredThing(IBNAView view, IThing t){
		IThing pt = view.getWorld().getBNAModel().getParentThing(t);
		if(pt != null){
			if(StructureMapper.isBrickAssemblyRootThing(pt)){
				BoxAssembly boxAssembly = BoxAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)pt);
				BoxThing boxThing = boxAssembly.getBoxThing();
				if(boxThing != null){
					return boxThing;
				}
			}
			else if(StructureMapper.isLinkAssemblyRootThing(pt)){
				SplineAssembly splineAssembly = SplineAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)pt);
				SplineThing splineThing = splineAssembly.getSplineThing();
				if(splineThing != null){
					return splineThing;
				}
			}
		}
		return null;
	}
	
	protected RGB getRGB(IBNAView view, IThing t){
		IHasMutableColor ct = getColoredThing(view, t);
		if(ct != null){
			return ct.getColor();
		}
		return null;
	}
	
	protected void setRGB(IBNAView view, IThing t, RGB newRGB){
		IHasMutableColor ct = getColoredThing(view, t);
		if(ct != null){
			ct.setColor(newRGB);
		}
	}
}
