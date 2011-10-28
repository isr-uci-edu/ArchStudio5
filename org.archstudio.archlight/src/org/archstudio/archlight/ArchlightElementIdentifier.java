package org.archstudio.archlight;

import org.archstudio.xarchadt.ObjRef;

public class ArchlightElementIdentifier implements java.io.Serializable {

	private static final long serialVersionUID = 3313771265145301624L;

	protected String elementID;
	protected ObjRef elementRef;
	protected String elementDescription;

	public ArchlightElementIdentifier(ObjRef elementRef, String elementDescription) {
		super();
		this.elementRef = elementRef;
		this.elementID = null;
		this.elementDescription = elementDescription;
	}

	public ArchlightElementIdentifier(String elementID, String elementDescription) {
		super();
		this.elementRef = null;
		this.elementID = elementID;
		this.elementDescription = elementDescription;
	}

	public String getElementDescription() {
		return elementDescription;
	}

	public void setElementDescription(String elementDescription) {
		this.elementDescription = elementDescription;
	}

	public String getElementID() {
		return elementID;
	}

	public void setElementID(String elementID) {
		this.elementID = elementID;
	}

	public ObjRef getElementRef() {
		return elementRef;
	}

	public void setElementRef(ObjRef elementRef) {
		this.elementRef = elementRef;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ArchlightElementIdentifier)) {
			return false;
		}
		ArchlightElementIdentifier oei = (ArchlightElementIdentifier) o;
		return nulleq(elementID, oei.elementID) && nulleq(elementRef, oei.elementRef)
				&& nulleq(elementDescription, oei.elementDescription);
	}

	private static boolean nulleq(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		return o1.equals(o2);
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("ArchlightElementIdentifier[");
		buf.append("elementID=").append(elementID).append("; ");
		buf.append("elementRef=").append(elementRef).append("; ");
		buf.append("elementDescription=").append(elementDescription).append("];");
		return buf.toString();
	}
}
