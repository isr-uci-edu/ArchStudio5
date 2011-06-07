package org.archstudio.noticeadt.core;

/**
 * Myx brick: "Archlight Notice Impl"
 * 
 * @see org.archstudio.noticeadt.core.ArchlightNoticeADTMyxComponentStub
 * @generated
 */
public class ArchlightNoticeADTMyxComponent extends org.archstudio.noticeadt.core.ArchlightNoticeADTMyxComponentStub {

	@Override
	public void init() {
		notices = new ArchlightNoticeADT();
		((ArchlightNoticeADT) notices).addArchlightNoticeADTListener(noticeEventsProxy);
	}
}