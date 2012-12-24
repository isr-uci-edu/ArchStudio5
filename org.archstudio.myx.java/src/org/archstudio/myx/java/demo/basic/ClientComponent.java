package org.archstudio.myx.java.demo.basic;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

public class ClientComponent extends AbstractMyxSimpleBrick {

	public ClientComponent() {
	}

	@Override
	public void begin() {
		IMath math = (IMath) MyxUtils.getFirstRequiredServiceObject(this, MyxUtils.createName("math"));
		System.out.println("2+2=" + math.add(2, 2));
	}

	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return null;
	}

}
