package org.archstudio.noticeadt.common;

import java.util.Collection;
import java.util.List;

import org.archstudio.archlight.common.ArchlightNotice;

public interface IArchlightNoticeADT{

	public void addNotices(String toolID, List<String> messages);

	public void addNotice(String toolID, String message);

	public void addNotice(String toolID, String message, String additionalDetail);

	public void addNotice(String toolID, String message, Throwable error);

	public void addNotice(String toolID, String message, String additionalDetail,
		Throwable error);

	public void addNotice(ArchlightNotice ttn);

	public void addNotices(Collection<? extends ArchlightNotice> ttns);

	public Collection<? extends ArchlightNotice> getAllNotices();

}