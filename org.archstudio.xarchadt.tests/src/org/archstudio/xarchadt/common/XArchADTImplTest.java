package org.archstudio.xarchadt.common;

import org.eclipse.emf.common.util.URI;

public class XArchADTImplTest extends AbstractXArchADTTest {

	public void testCreate() {
		assertNotNull(documentRef);
		assertEquals(documentRef, xarch.getDocumentRootRef(URI.createURI(documentUrn)));
		assertEquals(documentUrn, xarch.getURI(documentRef).toString());
	}
}
