package org.archstudio.swtutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/*
 * NOTE: This class probably isn't useful in Eclipse using the default ObjectInputStream, but extended
 * ObjectInputStreams that can use alternate classloaders would work. If using this class, find one of those
 * objectinputstreamloader classes under license, and use that instead.
 */
public class SerializableTransfer extends ByteArrayTransfer {

	private static final String SERIALIZABLE_NAME = "serializable";
	private static final int SERIALIZABLE_ID = registerType(SERIALIZABLE_NAME);

	private static SerializableTransfer _instance = new SerializableTransfer();

	private SerializableTransfer() {
	}

	public static SerializableTransfer getInstance() {
		return _instance;
	}

	@Override
	public void javaToNative(Object object, TransferData transferData) {
		if (object == null) {
			return;
		}

		Serializable[] serializables = null;
		if (object instanceof Serializable) {
			serializables = new Serializable[] { (Serializable) object };
		}
		else if (object.getClass().isArray()) {
			Object[] arr = (Object[]) object;
			if (arr.length == 0) {
				serializables = new Serializable[0];
			}
			else {
				if (arr[0] instanceof Serializable) {
					serializables = new Serializable[arr.length];
					for (int i = 0; i < arr.length; i++) {
						serializables[i] = (Serializable) arr[i];
					}
				}
			}
		}
		if (serializables == null) {
			return;
		}

		if (isSupportedType(transferData)) {
			try {
				// write data to a byte array and then ask super to convert to pMedium
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ObjectOutputStream writeOut = new ObjectOutputStream(out);

				//writeOut.write(serializables.length);
				//writeOut.flush();
				for (Serializable serializable : serializables) {
					writeOut.writeObject(serializable);
				}
				writeOut.flush();
				byte[] buffer = out.toByteArray();
				writeOut.close();

				super.javaToNative(buffer, transferData);
			}
			catch (IOException e) {
			}
		}
	}

	@Override
	public Object nativeToJava(TransferData transferData) {
		if (isSupportedType(transferData)) {
			byte[] buffer = (byte[]) super.nativeToJava(transferData);
			if (buffer == null) {
				return null;
			}

			List<Serializable> serializableList = new ArrayList<Serializable>();
			Serializable[] serializables = new Serializable[0];
			try {
				ByteArrayInputStream in = new ByteArrayInputStream(buffer);
				ObjectInputStream readIn = new ObjectInputStream(in);

				while (true) {
					Serializable s = (Serializable) readIn.readObject();
					if (s == null) {
						break;
					}
					serializableList.add(s);
				}
				readIn.close();
				serializables = serializableList.toArray(new Serializable[serializableList.size()]);
			}
			catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return null;
			}
			catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
			return serializables;
		}

		return null;
	}

	@Override
	protected String[] getTypeNames() {
		return new String[] { SERIALIZABLE_NAME };
	}

	@Override
	protected int[] getTypeIds() {
		return new int[] { SERIALIZABLE_ID };
	}
}
