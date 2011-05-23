package org.archstudio.myxgen;

import org.archstudio.eclipse.AbstractNature;
import org.archstudio.myxgen.builder.MyxStubBuilder;
import org.eclipse.core.resources.IProjectNature;

public class MyxStubNature extends AbstractNature implements IProjectNature {

	public static final String NATURE_ID = "org.archstudio.myxgen.myxStubNature";

	public MyxStubNature() {
		super(NATURE_ID, MyxStubBuilder.BUILDER_ID);
	}
}
