package org.archstudio.archipelago.core.things;

import java.util.Set;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import org.archstudio.resources.IResources;
import org.archstudio.xadlswt.XadlTreeContentProvider;
import org.archstudio.xadlswt.XadlTreeLabelProvider;
import org.archstudio.xadlswt.XadlTreeUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.swt.AbstractSWTTreeThingPeer;

public class SWTXadlSelectorThingPeer extends AbstractSWTTreeThingPeer{

	protected SWTXadlSelectorThing lt;
	
	public SWTXadlSelectorThingPeer(IThing t){
		super(t);
		if(!(t instanceof SWTXadlSelectorThing)){
			throw new IllegalArgumentException("SWTXadlSelectorThingPeer can only peer for SWTXadlSelectorThing");
		}
		this.lt = (SWTXadlSelectorThing)t;
	}
	
	protected Object getInput(){
		return lt.getContentProviderRootRef();
	}
	
	protected ITreeContentProvider getContentProvider(){
		IXArchADT xarch = lt.getRepository();
		if(xarch != null){
			ObjRef rootRef = lt.getContentProviderRootRef();
			if(rootRef != null){
				Set<XadlTreeUtils.Type> flags = lt.getContentProviderFlags();
				XadlTreeContentProvider contentProvider = new XadlTreeContentProvider(xarch, rootRef, flags);
				return contentProvider;
			}
		}
		return null;
	}
	
	protected ILabelProvider getLabelProvider(){
		IXArchADT xarch = lt.getRepository();
		if(xarch != null){
			IResources resources = lt.getResources();
			if(resources != null){
				return new XadlTreeLabelProvider(xarch, resources);
			}
		}
		return null;
	}

}
