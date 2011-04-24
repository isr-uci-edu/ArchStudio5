package org.archstudio.myx.demo.basic;

import org.archstudio.myx.fw.*;

public class ClientComponent extends AbstractMyxSimpleBrick {

	public ClientComponent() {
	}

	public void begin() {
		IMath math = (IMath) MyxUtils.getFirstRequiredServiceObject(this, MyxUtils.createName("math"));
		System.out.println("2+2=" + math.add(2, 2));
	}

	public Object getServiceObject(IMyxName interfaceName) {
		return null;
	}

}
