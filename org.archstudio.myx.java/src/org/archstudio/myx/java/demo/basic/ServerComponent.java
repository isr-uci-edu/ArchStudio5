package org.archstudio.myx.demo.basic;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

public class ServerComponent extends AbstractMyxSimpleBrick {
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("math");

	protected IMath math;

	public ServerComponent() {
		this.math = new MathImpl();
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(PROVIDED_INTERFACE_NAME)) {
			return math;
		}
		return null;
	}

	class MathImpl implements IMath {
		public int add(int a, int b) {
			return a + b;
		}

		public int mul(int a, int b) {
			return a * b;
		}

		public int sub(int a, int b) {
			return a - b;
		}
	}

}
