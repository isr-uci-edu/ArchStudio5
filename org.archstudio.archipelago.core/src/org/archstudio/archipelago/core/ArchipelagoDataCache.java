package org.archstudio.archipelago.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.xarchadt.ObjRef;

public class ArchipelagoDataCache{
	protected List<CacheEntry> entryList = Collections.synchronizedList(new ArrayList<CacheEntry>());
	
	private ArchipelagoDataCache(){}
	
	public synchronized void addCacheEntry(ArchipelagoOutlinePage outlinePage, ObjRef xArchRef, TreeNodeDataCache data){
		CacheEntry ce = new CacheEntry(outlinePage, xArchRef, data);
		entryList.add(ce);
	}
	
	public synchronized void removeCacheEntry(ArchipelagoOutlinePage outlinePage){
		CacheEntry doomedEntry = null;
		for(CacheEntry ce : entryList){
			if(ce.outlinePage == outlinePage){
				doomedEntry = ce;
				break;
			}
		}
		if(doomedEntry != null){
			entryList.remove(doomedEntry);
		}
	}
	
	public synchronized TreeNodeDataCache getData(ObjRef xArchRef){
		for(CacheEntry ce : entryList){
			if(ce.xArchRef.equals(xArchRef)){
				return ce.data;
			}
		}
		return null;
	}
	
	static class CacheEntry{
		public ArchipelagoOutlinePage outlinePage;
		public ObjRef xArchRef;
		public TreeNodeDataCache data;
		
		public CacheEntry(ArchipelagoOutlinePage outlinePage, ObjRef xArchRef, TreeNodeDataCache data){
			this.outlinePage = outlinePage;
			this.xArchRef = xArchRef;
			this.data = data;
		}
	}
	
	private static ArchipelagoDataCache theInstance = new ArchipelagoDataCache();
	
	public static ArchipelagoDataCache getInstance(){
		return theInstance;
	}
}
