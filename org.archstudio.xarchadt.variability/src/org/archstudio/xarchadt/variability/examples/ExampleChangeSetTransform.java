package org.archstudio.xarchadt.variability.examples;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.archstudio.ljm.LJMException;
import org.archstudio.ljm.LJMProxyFactory;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.ChangeSetTransform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/*
 * This example transform reverses the direction of all interfaces in all architectures
 */
public class ExampleChangeSetTransform implements ChangeSetTransform {

	public ExampleChangeSetTransform() {
	}

	@Override
	public void transform(IXArchADT xarch, ObjRef documentRootRef) {
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		XADLType xadlType = documentRoot.getXADL();
		if (xadlType != null) {
			for (EObject eObject : xadlType.getTopLevelElement()) {
				if (eObject instanceof Structure) {
					Structure structure = (Structure) eObject;
					for (Component c : structure.getComponent()) {
						for (Interface i : c.getInterface()) {
							i.setDirection(getOpposite(i.getDirection()));
						}
					}
					for (Connector c : structure.getConnector()) {
						for (Interface i : c.getInterface()) {
							i.setDirection(getOpposite(i.getDirection()));
						}
					}
				}
			}
		}
	}

	private Direction getOpposite(Direction direction) {
		switch (direction) {
		case IN:
			return Direction.OUT;
		case OUT:
			return Direction.IN;
		case INOUT:
			return Direction.NONE;
		case NONE:
			return Direction.INOUT;
		}
		throw new IllegalArgumentException(direction.toString());
	}

	public static void main(String[] args) throws LJMException, UnknownHostException {
		int port = Integer.parseInt(System.getenv("IXArchADTPort"));
		String documentURI = System.getenv("IXArchADTDocumentURI");

		IXArchADT xarch = LJMProxyFactory.createProxy(InetAddress.getLocalHost(), port, "IXArchADT", IXArchADT.class);
		ObjRef documentRootRef = xarch.getDocumentRootRef(URI.createURI(documentURI));

		new ExampleChangeSetTransform().transform(xarch, documentRootRef);
	}
}
