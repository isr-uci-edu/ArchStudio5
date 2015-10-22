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

public class OutlineElementTransfer extends ByteArrayTransfer implements IUITransferProvider {
  private static Cache<ObjRef, Object> transferredObjRefs =
      CacheBuilder.newBuilder().expireAfterWrite(12, TimeUnit.HOURS).build();
  private static final String OUTLINE_ELEMENT_TYPE_NAME = OutlineElementTransfer.class.getName();
  private static final int OUTLINE_ELEMENT_TYPE_ID = registerType(OUTLINE_ELEMENT_TYPE_NAME);
  private static final String TRANSFER_PREFIX = OUTLINE_ELEMENT_TYPE_NAME + ":";
  private static OutlineElementTransfer _instance = new OutlineElementTransfer();

  private OutlineElementTransfer() {}

  public static IUITransferProvider getInstance() {
    return _instance;
  }

  @Override
  public void addData(TransferData transferData, DNDData dndData) {
    List<Object> element = nativeToJava(transferData);
    if (element != null) {
      dndData.addData(element);
      for (Object node : element) {
        dndData.addData(node);
      }
    }
  }

  @Override
  public void javaToNative(Object object, TransferData transferData) {
    if (object != null && isSupportedType(transferData)) {
      if (object instanceof List<?>) {
        List<?> list = (List<?>) object;
        StringBuffer sb = new StringBuffer();
        sb.append(TRANSFER_PREFIX);
        for (Object item : list) {
          sb.append("\t");
          if (item == null) {
            sb.append("null");
          } else if (item instanceof String) {
            sb.append("String:").append(((String) item).replace("\\", "\\\\").replace("\t", "\\t"));
          } else if (item instanceof ObjRef) {
            // hold the ObjRef so it is not garbage collected
            transferredObjRefs.put((ObjRef) item, item);
            sb.append("ObjRef:").append(((ObjRef) item).getUID());
          } else {
            throw new IllegalArgumentException("Unsupported object type: " + item);
          }
        }
        super.javaToNative(sb.toString().getBytes(), transferData);
      } else {
        throw new IllegalArgumentException("Unsupported object type: " + object);
      }
    } else {
      throw new IllegalArgumentException("Unsupported object type: " + object);
    }
  }

  @Override
  public List<Object> nativeToJava(TransferData transferData) {
    if (isSupportedType(transferData)) {
      byte[] buffer = (byte[]) super.nativeToJava(transferData);
      if (buffer != null) {
        String bufferString = new String(buffer);
        if (bufferString.startsWith(TRANSFER_PREFIX + "\t")) {
          List<Object> list = Lists.newArrayList();
          for (String item : bufferString.split("\t")) {
            if (item.equals(TRANSFER_PREFIX)) {
            } else if (item.startsWith("String:")) {
              list.add(
                  item.substring("String:".length()).replace("\\t", "\t").replace("\\\\", "\\"));
            } else if (item.startsWith("ObjRef:")) {
              list.add(ObjRef.lookupObjRef(Long.parseLong(item.substring("ObjRef:".length()))));
            } else {
              throw new IllegalArgumentException("Unsupported object type: " + item);
            }
          }
          return list;
        }
      }
    }
    return null;
  }

  @Override
  protected String[] getTypeNames() {
    return new String[] {OUTLINE_ELEMENT_TYPE_NAME};
  }

  @Override
  protected int[] getTypeIds() {
    return new int[] {OUTLINE_ELEMENT_TYPE_ID};
  }
}
