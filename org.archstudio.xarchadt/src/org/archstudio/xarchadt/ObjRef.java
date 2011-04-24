package org.archstudio.xarchadt;

import java.util.concurrent.atomic.AtomicLong;

public final class ObjRef implements java.io.Serializable {

	private static final long serialVersionUID = -1027266603679479510L;

	private static final AtomicLong atomicLong = new AtomicLong(1000);

	private final Object uid;

	public ObjRef() {
		this.uid = Long.valueOf(atomicLong.getAndIncrement());
	}

	public ObjRef(String uid) {
		if (uid == null)
			throw new NullPointerException("ObjRef UID may not be null");
		this.uid = uid;
	}

	public Object getUID() {
		return uid;
	}

	@Override
	public int hashCode() {
		return uid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjRef other = (ObjRef) obj;
		if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ObjRef[" + uid + "]";
	}
}
