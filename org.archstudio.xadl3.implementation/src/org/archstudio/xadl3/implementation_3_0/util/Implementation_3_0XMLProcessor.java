/**
 */
package org.archstudio.xadl3.implementation_3_0.util;

import java.util.Map;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class Implementation_3_0XMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Implementation_3_0XMLProcessor() {
		super(EPackage.Registry.INSTANCE);
		Implementation_3_0Package.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the
	 * Implementation_3_0ResourceFactoryImpl factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new Implementation_3_0ResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new Implementation_3_0ResourceFactoryImpl());
		}
		return registrations;
	}

} //Implementation_3_0XMLProcessor
