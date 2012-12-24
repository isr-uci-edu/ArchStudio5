/**
 */
package org.archstudio.xadl3.domain_3_0.util;

import java.util.Map;

import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Domain_3_0XMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Domain_3_0XMLProcessor() {
		super(EPackage.Registry.INSTANCE);
		Domain_3_0Package.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the
	 * Domain_3_0ResourceFactoryImpl factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */

	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new Domain_3_0ResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new Domain_3_0ResourceFactoryImpl());
		}
		return registrations;
	}

} //Domain_3_0XMLProcessor
