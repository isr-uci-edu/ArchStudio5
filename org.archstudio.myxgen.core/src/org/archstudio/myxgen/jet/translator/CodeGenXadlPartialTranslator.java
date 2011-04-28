package org.archstudio.myxgen.jet.translator;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import org.archstudio.myxgen.jet.extension.Direction;
import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;
import org.archstudio.myxgen.jet.extension.MyxBrickExtensionUtils;

public class CodeGenXadlPartialTranslator {

	protected IXArchADT xarch = null;
	protected ObjRef xArchRef = null;

	public CodeGenXadlPartialTranslator(IXArchADT xarch, ObjRef xArchRef) {
		this.xarch = xarch;
		this.xArchRef = xArchRef;
	}

	/**
	 * Translates only interfaces into xadl.
	 * 
	 * @param brickRef
	 * @param extBrick
	 */
	public void translate(ObjRef brickRef, IMyxBrickExtension extBrick) {
		ObjRef typesContextRef = xarch.createContext(xArchRef, "types");

		//sets the url of extension point brick id
		XadlUtils.setXLinkByHref(xarch, brickRef, "type", MyxBrickExtensionUtils.getExtensionPointPluginUrl(extBrick)
				.toString());

		//extension point brink interfaces and its ancestors
		Collection<IInterface> extIntfSet = new HashSet<IInterface>();
		extIntfSet.addAll(extBrick.getInterfaces());
		extIntfSet.addAll(extBrick.getAncestorsExtensionInterfaces());

		//creates extension point brick interface url to extension point brick interface map
		Map<String, IInterface> extUrlIntfMap = new HashMap<String, IInterface>();
		for (IInterface extIntf : extIntfSet) {
			extUrlIntfMap.put(MyxBrickExtensionUtils.getExtensionPointPluginUrl(extIntf).toString(), extIntf);
		}

		//updates existing interface elements of the brickRef
		for (ObjRef interfaceEltRef : xarch.getAll(brickRef, "interface")) {
			ObjRef typeRef = (ObjRef) xarch.get(interfaceEltRef, "type");
			if (typeRef != null) {
				String interfaceUrl = XadlUtils.getHref(xarch, typeRef);
				IInterface extIntf = extUrlIntfMap.get(interfaceUrl);
				if (extIntf != null) {
					//updates the existing interface
					XadlUtils.setDescription(xarch, interfaceEltRef, extIntf.getName());
					XadlUtils.setDirection(xarch, interfaceEltRef,
							CodeGenXadlPartialTranslator.getXadlDirection(extIntf.getDirection()));

					//removes extension point interface from the set
					extIntfSet.remove(extIntf);

				}
				else {
					//removes the deleted interface
					XadlUtils.remove(xarch, interfaceEltRef);
				}
			}
			else {
				//removes the interface that does not have type link
				XadlUtils.remove(xarch, interfaceEltRef);
			}
		}

		//adds interface elements into the brickRef
		for (IInterface intf : extIntfSet) {
			//crates a new interface element
			ObjRef interfaceEltRef = xarch.create(typesContextRef, "interface");
			XadlUtils.setDescription(xarch, interfaceEltRef, intf.getName());
			XadlUtils.setDirection(xarch, interfaceEltRef,
					CodeGenXadlPartialTranslator.getXadlDirection(intf.getDirection()));

			String interfaceId = UIDGenerator.generateUID("interface");
			xarch.set(interfaceEltRef, "id", interfaceId);

			xarch.add(brickRef, "interface", interfaceEltRef);

			//sets the url of extension point brick interface id
			XadlUtils.setXLinkByHref(xarch, interfaceEltRef, "type",
					MyxBrickExtensionUtils.getExtensionPointPluginUrl(intf).toString());
		}
	}

	/**
	 * Gets string expression of the given signature for xadl.
	 * 
	 * @return
	 */
	private static String getXadlDirection(Direction direction) {
		String strDirection = null;
		switch (direction) {
		case IN:
			strDirection = "in";
			break;
		case OUT_MULTI:
		case OUT_SINGLE:
			strDirection = "out";
			break;
		default:
			strDirection = "none";

		}

		return strDirection;
	}
}
