package org.archstudio.archipelago.core.things;

import java.util.EnumSet;
import java.util.Set;

import org.archstudio.resources.common.IResources;
import org.archstudio.xadlswt.common.XadlTreeUtils;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.things.swt.AbstractSWTTreeThing;

public class SWTXadlSelectorThing extends AbstractSWTTreeThing{

	public SWTXadlSelectorThing(){
		this(BNAUtils.generateUID(SWTXadlSelectorThing.class.getName()));
	}
	
	public SWTXadlSelectorThing(String id){
		super(id);
	}
	
	protected void initProperties(){
		super.initProperties();
		setContentProviderFlags(EnumSet.noneOf(XadlTreeUtils.Type.class));
	}
	
	public void setRepository(IXArchADT xarch){
		setProperty("$xarch", xarch);
	}
	
	public IXArchADT getRepository(){
		return getProperty("$xarch");
	}
	
	public void setResources(IResources resources){
		setProperty("$resources", resources);
	}
	
	public IResources getResources(){
		return getProperty("$resources");
	}
	
	public void setContentProviderRootRef(ObjRef rootRef){
		setProperty("rootRef", rootRef);
	}
	
	public ObjRef getContentProviderRootRef(){
		return getProperty("rootRef");
	}
	
	public void setContentProviderFlags(Set<XadlTreeUtils.Type> contentProviderFlags){
		setProperty("contentProviderFlags", contentProviderFlags);
	}
	
	public Set<XadlTreeUtils.Type> getContentProviderFlags(){
		return getProperty("contentProviderFlags");
	}
}
