package org.archstudio.myxgen;

import org.archstudio.eclipse.AbstractNature;
import org.archstudio.myxgen.builder.MyxStubBuilder;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class MyxStubNature extends AbstractNature implements IProjectNature {

	public static final String NATURE_ID = "org.archstudio.myxgen.myxStubNature";

	public MyxStubNature() {
		super(NATURE_ID, MyxStubBuilder.BUILDER_ID);
	}
	
	@Override
	public void configure() throws CoreException {
		super.configure();
		
		IFolder folder = getProject().getFolder("._codegen_tmp");
		if(!folder.exists())
			folder.create(true, true, null);
		folder.setDerived(true, null);
		folder.setHidden(true);
	}
}
