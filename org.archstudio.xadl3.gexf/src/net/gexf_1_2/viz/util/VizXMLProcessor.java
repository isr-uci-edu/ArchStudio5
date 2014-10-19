/**
 */
package net.gexf_1_2.viz.util;

import java.util.Map;

import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class VizXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VizXMLProcessor() {
		super(EPackage.Registry.INSTANCE);
		VizPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the VizResourceFactoryImpl factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new VizResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new VizResourceFactoryImpl());
		}
		return registrations;
	}

} //VizXMLProcessor
