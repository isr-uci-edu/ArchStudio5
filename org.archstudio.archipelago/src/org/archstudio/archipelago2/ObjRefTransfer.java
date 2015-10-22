package org.archstudio.archipelago2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.ui.IUITransferProvider;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

public class ObjRefTransfer extends ByteArrayTransfer implements IUITransferProvider {
  private static Cache<ObjRef, Object> transferredObjRefs =
      CacheBuilder.newBuilder().expireAfterWrite(12, TimeUnit.HOURS).build();
  private static final String OBJREF_TYPE_NAME = ObjRefTransfer.class.getName();
  private static final int OBJREF_TYPE_ID = registerType(OBJREF_TYPE_NAME);
  private static final String TRANSFER_PREFIX = OBJREF_TYPE_NAME + ":";
  private static ObjRefTransfer _instance = new ObjRefTransfer();

private ObjRefTransfer() {
	}

	public static ObjRefTransfer getInstance() {
		return _instance;
  }

  @Override
  public void addData(TransferData transferData, DNDData dndData) {
    List<ObjRef> objRefs = nativeToJava(transferData);
    if (objRefs != null) {
      dndData.addData(objRefs);
      if (objRefs.size() > 0) {
        dndData.addData(objRefs.get(0));
      }
    }
  }

  @Override
  public void javaToNative(Object object, TransferData transferData) {
    if (object != null && isSupportedType(transferData)) {
      if (object instanceof Iterable<?>) {
        Iterable<?> refs = (Iterable<?>) object;
        StringBuilder sb = new StringBuilder();
        sb.append(TRANSFER_PREFIX);
        boolean first = false;
        for (Object ref : refs) {
          if (!first) {
            sb.append(",");
          }
          if (!(ref instanceof ObjRef)) {
            throw new IllegalArgumentException(
                "Object " + ref + " in " + object + " is not an ObjRef");
          }
          // hold the ObjRef so it is not garbage collected
          transferredObjRefs.put((ObjRef) ref, null);
          sb.append(((ObjRef) ref).getUID());
          first = false;
        }
        super.javaToNative(sb.toString().getBytes(), transferData);
      } else if (object instanceof ObjRef) {
        ObjRef ref = (ObjRef) object;
        StringBuilder sb = new StringBuilder();
        sb.append(TRANSFER_PREFIX);
        sb.append(ref.getUID());
        super.javaToNative(sb.toString().getBytes(), transferData);
      }
    }
  }

  @Override
  public List<ObjRef> nativeToJava(TransferData transferData) {
    if (isSupportedType(transferData)) {
      byte[] buffer = (byte[]) super.nativeToJava(transferData);
      if (buffer != null) {
        String s = new String(buffer);
        if (s.startsWith(TRANSFER_PREFIX)) {
          String[] uids = s.substring(TRANSFER_PREFIX.length()).split(",");
          List<ObjRef> refs = Lists.newArrayList();
          for (String uid : uids) {
            refs.add(ObjRef.lookupObjRef(Long.valueOf(uid)));
          }
          return refs;
        }
      }
    }
    return null;
  }

  @Override
  protected String[] getTypeNames() {
    return new String[] {OBJREF_TYPE_NAME};
  }

  @Override
  protected int[] getTypeIds() {
    return new int[] {OBJREF_TYPE_ID};
  }
}
