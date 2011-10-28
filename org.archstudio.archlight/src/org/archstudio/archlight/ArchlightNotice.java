package org.archstudio.archlight;

public class ArchlightNotice implements java.io.Serializable {

	private static final long serialVersionUID = 518740025276233364L;

	protected String toolID;
	protected String message;
	protected String additionalDetail;
	protected Throwable error;

	public ArchlightNotice(String toolID, String message, String additionalDetail, Throwable error) {
		super();
		this.toolID = toolID;
		this.message = message;
		this.additionalDetail = additionalDetail;
		this.error = error;
	}

	public String getToolID() {
		return toolID;
	}

	public String getAdditionalDetail() {
		return additionalDetail;
	}

	public void setAdditionalDetail(String additionalDetail) {
		this.additionalDetail = additionalDetail;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
