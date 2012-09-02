package org.archstudio.archipelago.core;

import java.util.concurrent.TimeUnit;

import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class ObjRefTransfer extends ByteArrayTransfer {

	private static final String OBJREF_TYPE_NAME = "ObjRef";
	private static final int OBJREF_TYPE_ID = registerType(OBJREF_TYPE_NAME);

	private static ObjRefTransfer _instance = new ObjRefTransfer();

	private Cache<Long, ObjRef> transferredObjRefs = CacheBuilder.newBuilder().expireAfterWrite(12, TimeUnit.HOURS)
			.build();

	private ObjRefTransfer() {
	}

	public static ObjRefTransfer getInstance() {
		return _instance;
	}

	public void javaToNative(Object object, TransferData transferData) {
		if (object == null || !(object instanceof ObjRef[])) {
			return;
		}

		if (isSupportedType(transferData)) {
			ObjRef[] refs = (ObjRef[]) object;

			StringBuffer sb = new StringBuffer(refs.length * 10);
			for (int i = 0; i < refs.length; i++) {
				transferredObjRefs.put(refs[i].getUID(), refs[i]);
				sb.append(refs[i].getUID());
				if (i < (refs.length - 1)) {
					sb.append(",");
				}
			}
			super.javaToNative(sb.toString().getBytes(), transferData);
		}
	}

	public Object nativeToJava(TransferData transferData) {
		if (isSupportedType(transferData)) {
			byte[] buffer = (byte[]) super.nativeToJava(transferData);
			if (buffer == null) {
				return null;
			}

			String s = new String(buffer);
			String[] uids = s.split(",");
			ObjRef[] refs = new ObjRef[uids.length];
			for (int i = 0; i < uids.length; i++) {
				refs[i] = transferredObjRefs.getIfPresent(Long.valueOf(uids[i]));
			}
			return refs;
		}
		return null;
	}

	protected String[] getTypeNames() {
		return new String[] { OBJREF_TYPE_NAME };
	}

	protected int[] getTypeIds() {
		return new int[] { OBJREF_TYPE_ID };
	}
}
