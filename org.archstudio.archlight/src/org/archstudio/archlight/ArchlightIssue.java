package org.archstudio.archlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.archstudio.xarchadt.ObjRef;

public class ArchlightIssue implements java.io.Serializable {

	private static final long serialVersionUID = -4160980442451729079L;

	public final static int SEVERITY_INFO = 100;
	public final static int SEVERITY_WARNING = 200;
	public final static int SEVERITY_ERROR = 300;

	protected String testUID;
	protected ObjRef documentRootRef;
	protected String toolID;
	protected int severity;
	protected String headline;
	protected String detailedDescription;
	protected String iconHref;

	protected Collection<ArchlightElementIdentifier> elementIdentifiers;

	public ArchlightIssue(String testUID, ObjRef documentRootRef, String toolID, int severity, String headline,
			String detailedDescription, String iconHref, Collection<ArchlightElementIdentifier> elementIdentifiers) {
		super();
		this.testUID = testUID;
		this.documentRootRef = documentRootRef;
		this.toolID = toolID;
		this.severity = severity;
		this.headline = headline;
		this.detailedDescription = detailedDescription;
		this.iconHref = iconHref;
		this.elementIdentifiers = elementIdentifiers;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public ObjRef getDocumentRootRef() {
		return documentRootRef;
	}

	public void setDocumentRootRef(ObjRef documentRootRef) {
		this.documentRootRef = documentRootRef;
	}

	public Collection<ArchlightElementIdentifier> getElementIdentifiers() {
		return elementIdentifiers;
	}

	public void setElementIdentifiers(Collection<ArchlightElementIdentifier> elementIdentifiers) {
		this.elementIdentifiers = Collections.unmodifiableCollection(new ArrayList<ArchlightElementIdentifier>(
				elementIdentifiers));
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getIconHref() {
		return iconHref;
	}

	public void setIconHref(String iconHref) {
		this.iconHref = iconHref;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getToolID() {
		return toolID;
	}

	public void setToolID(String toolID) {
		this.toolID = toolID;
	}

	public String getTestUID() {
		return testUID;
	}

	public void setTestUID(String testUID) {
		this.testUID = testUID;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("ArchlightIssue[");
		buf.append("headline=").append(headline).append("; ");
		buf.append("detailedDescription=").append(detailedDescription).append("; ");
		buf.append("testUID=").append(testUID).append("; ");
		buf.append("documentRootRef=").append(documentRootRef).append("; ");
		buf.append("toolID=").append(toolID).append("; ");
		String severityString = "" + severity;
		switch (severity) {
		case SEVERITY_ERROR:
			severityString = "error";
			break;
		case SEVERITY_WARNING:
			severityString = "warning";
			break;
		case SEVERITY_INFO:
			severityString = "info";
			break;
		}
		buf.append("severity=").append(severityString).append("; ");
		if (elementIdentifiers == null) {
			buf.append("elementIdentifiers=null; ");
		}
		else {
			int i = 0;
			for (ArchlightElementIdentifier elementIdentifier : elementIdentifiers) {
				buf.append("elementIdentifier[").append(i++).append("]=").append(elementIdentifier).append("; ");
			}
		}
		buf.append("iconHref=").append(iconHref).append("];");
		return buf.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ArchlightIssue)) {
			return false;
		}
		ArchlightIssue otherIssue = (ArchlightIssue) o;

		return nulleq(testUID, otherIssue.testUID) && nulleq(documentRootRef, otherIssue.documentRootRef)
				&& nulleq(toolID, otherIssue.toolID) && severity == otherIssue.severity
				&& nulleq(headline, otherIssue.headline) && nulleq(detailedDescription, otherIssue.detailedDescription)
				&& nulleq(iconHref, otherIssue.iconHref)
				&& elementIdentifiers.containsAll(otherIssue.elementIdentifiers)
				&& otherIssue.elementIdentifiers.containsAll(elementIdentifiers);
	}

	@Override
	public int hashCode() {
		return hc(testUID) ^ hc(toolID) ^ hc(headline);
	}

	private static int hc(Object o) {
		if (o == null) {
			return Object.class.hashCode();
		}
		return o.hashCode();
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
}
