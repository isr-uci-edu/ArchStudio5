/**
 */
package net.gexf_1_2.viz;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see net.gexf_1_2.viz.VizPackage
 * @generated
 */
public interface VizFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	VizFactory eINSTANCE = net.gexf_1_2.viz.impl.VizFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Color Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Color Content</em>'.
	 * @generated
	 */
	ColorContent createColorContent();

	/**
	 * Returns a new object of class '<em>Edge Shape Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Edge Shape Content</em>'.
	 * @generated
	 */
	EdgeShapeContent createEdgeShapeContent();

	/**
	 * Returns a new object of class '<em>Node Shape Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Node Shape Content</em>'.
	 * @generated
	 */
	NodeShapeContent createNodeShapeContent();

	/**
	 * Returns a new object of class '<em>Position Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Position Content</em>'.
	 * @generated
	 */
	PositionContent createPositionContent();

	/**
	 * Returns a new object of class '<em>Size Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Size Content</em>'.
	 * @generated
	 */
	SizeContent createSizeContent();

	/**
	 * Returns a new object of class '<em>Thickness Content</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Thickness Content</em>'.
	 * @generated
	 */
	ThicknessContent createThicknessContent();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	VizPackage getVizPackage();

} //VizFactory
