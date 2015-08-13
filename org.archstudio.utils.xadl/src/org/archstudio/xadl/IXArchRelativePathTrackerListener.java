package org.archstudio.xadl;

import java.util.List;

import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

/**
 * A listener for {@link XArchRelativePathTracker}. Note, it is guaranteed that for any ObjRef,
 * {@link #processAdd(List, ObjRef)} will be called first, then any number of
 * {@link #processUpdate(List, String, ObjRef, XArchADTModelEvent)}'s and finally
 * {@link #processRemove(List, ObjRef)}.
 */
public interface IXArchRelativePathTrackerListener {
  /**
   * Called for each ObjRef referenced by the given XPath when scanning is started and subsequently
   * for each added (or set) ObjRef.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to addedRef,
   *        inclusive.
   * @param addedRef The ObjRef that has been added.
   */
  public void processAdd(List<ObjRef> descendantRefs, ObjRef addedRef);

  /**
   * Called when an ObjRef pointed to by the XPath is modified or any of its children are modified.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to modifiedRef,
   *        inclusive.
   * @param descendantPath The string path from rootRef to addedRef.
   * @param modifiedRef The ObjRef that was modified.
   * @param relativeEvt The relative event, rooted in the modified Ref.
   */
  public void processUpdate(List<ObjRef> descendantRefs, String descendantPath, ObjRef modifiedRef,
      XArchADTModelEvent relativeEvt);

  /**
   * Called for each removed ObjRef referenced by the given XPath and when scanning is ended. This
   * will only be called for ObjRefs that were previously added via a call to
   * {@link #processAdd(ObjRef, List)}.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to removedRef,
   *        inclusive.
   * @param removedRef The ObjRef that has been removed.
   */
  public void processRemove(List<ObjRef> descendantRefs, ObjRef removedRef);
}
