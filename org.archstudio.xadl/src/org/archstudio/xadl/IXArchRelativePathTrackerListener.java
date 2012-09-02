package org.archstudio.xadl;

import java.util.List;

import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

/**
 * A listener for {@link XArchRelativePathTracker}. Note, it is guaranteed that
 * for any ObjRef, {@link #processAdd(List, ObjRef)} will be called first, then
 * any number of
 * {@link #processUpdate(List, XArchADTPath, ObjRef, XArchADTModelEvent)}'s and
 * finally {@link #processRemove(List, ObjRef)}.
 */
public interface IXArchRelativePathTrackerListener {

	/**
	 * Called for each ObjRef referenced by the given xPath when scanning is
	 * started, and subsequently for each added ObjRef that matches the xPath
	 * while scanning is active.
	 * 
	 * @param relLineageRefs
	 *            The lineage (i.e., the reverse of the ancestry) of the objRef
	 *            that was added relative to the root ObjRef.
	 * @param objRef
	 *            The ObjRef that has been added.
	 */
	public void processAdd(List<ObjRef> relLineageRefs, ObjRef objRef);

	/**
	 * Called when an ObjRef pointed to by the xPath is modified, or any of its
	 * children are modified.
	 * 
	 * @param relLineageRefs
	 *            The lineage (i.e., the reverse of the ancestry) of the objRef
	 *            that was modified relative to the root ObjRef.
	 * @param relPath
	 *            The XArchPath of the objRef that was modified relative to the
	 *            root ObjRef.
	 * @param objRef
	 *            The ObjRef that was modified.
	 * @param evt
	 *            The modifying event.
	 */
	public void processUpdate(List<ObjRef> relLineageRefs, String relPath, ObjRef objRef, XArchADTModelEvent evt);

	/**
	 * Called for each removed ObjRef that was previously referenced by the
	 * given xPath during scanning, and called for each previously added ObjRef
	 * when scanning is stopped. Note: this is called only for ObjRefs that were
	 * previously added via a call to {@link #processAdd(List, ObjRef)}.
	 * 
	 * @param relLineageRefs
	 *            The lineage (i.e., the reverse of the ancestry) of the objRef
	 *            that was removed relative to the root ObjRef.
	 * @param objRef
	 *            the ObjRef that has been removed
	 * 
	 */
	public void processRemove(List<ObjRef> relLineageRefs, ObjRef objRef);

}
