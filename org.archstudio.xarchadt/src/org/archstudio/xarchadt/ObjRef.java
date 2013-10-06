package org.archstudio.xarchadt;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.MapMaker;
import com.google.common.primitives.Longs;

public final class ObjRef implements java.io.Serializable {

	private static Map<Long, ObjRef> objRefs = new MapMaker().softValues().makeMap();

	public static ObjRef lookupObjRef(long uid) {
		return objRefs.get(uid);
	}

	private static final long serialVersionUID = -1027266603679479510L;

	private static final AtomicLong atomicLong = new AtomicLong(1000);

	private final long uid;

	public ObjRef() {
		this.uid = atomicLong.getAndIncrement();
		objRefs.put(uid, this);
	}

	public long getUID() {
		return uid;
	}

	@Override
	public int hashCode() {
		return Longs.hashCode(uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ObjRef other = (ObjRef) obj;
		if (uid != other.uid) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ObjRef[" + uid + "]";
	}
}
