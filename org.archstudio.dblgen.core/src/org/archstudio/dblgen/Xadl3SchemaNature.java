package org.archstudio.dblgen;

import org.archstudio.dblgen.builder.Xadl3SchemaBuilder;
import org.archstudio.utils.eclipse.AbstractNature;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class Xadl3SchemaNature extends AbstractNature {

	public static final String NATURE_ID = "org.archstudio.dblgen.xadl3SchemaNature";

	public Xadl3SchemaNature() {
		super(NATURE_ID, Xadl3SchemaBuilder.BUILDER_ID);
	}

	public void configure() throws CoreException {
		super.configure();

		IFolder modelFolder = project.getFolder("model");
		if (!modelFolder.exists()) {
			modelFolder.create(true, true, null);
		}
	}
}
