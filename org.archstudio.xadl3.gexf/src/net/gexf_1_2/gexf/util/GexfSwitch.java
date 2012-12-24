/**
 */
package net.gexf_1_2.gexf.util;

import net.gexf_1_2.gexf.AttributeContent;
import net.gexf_1_2.gexf.AttributesContent;
import net.gexf_1_2.gexf.AttvalueType;
import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.DocumentRoot;
import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.MetaContent;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.gexf.ParentType;
import net.gexf_1_2.gexf.ParentsContent;
import net.gexf_1_2.gexf.SpellType;
import net.gexf_1_2.gexf.SpellsContent;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see net.gexf_1_2.gexf.GexfPackage
 * @generated
 */
public class GexfSwitch<T> extends Switch<T> {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static GexfPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public GexfSwitch() {
		if (modelPackage == null) {
			modelPackage = GexfPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */

	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns
	 * a non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code>
	 *         call.
	 * @generated
	 */

	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case GexfPackage.ATTRIBUTE_CONTENT: {
			AttributeContent attributeContent = (AttributeContent) theEObject;
			T result = caseAttributeContent(attributeContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.ATTRIBUTES_CONTENT: {
			AttributesContent attributesContent = (AttributesContent) theEObject;
			T result = caseAttributesContent(attributesContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.ATTVALUES_CONTENT: {
			AttvaluesContent attvaluesContent = (AttvaluesContent) theEObject;
			T result = caseAttvaluesContent(attvaluesContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.ATTVALUE_TYPE: {
			AttvalueType attvalueType = (AttvalueType) theEObject;
			T result = caseAttvalueType(attvalueType);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.DOCUMENT_ROOT: {
			DocumentRoot documentRoot = (DocumentRoot) theEObject;
			T result = caseDocumentRoot(documentRoot);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.EDGE_CONTENT: {
			EdgeContent edgeContent = (EdgeContent) theEObject;
			T result = caseEdgeContent(edgeContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.EDGES_CONTENT: {
			EdgesContent edgesContent = (EdgesContent) theEObject;
			T result = caseEdgesContent(edgesContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.GEXF_CONTENT: {
			GexfContent gexfContent = (GexfContent) theEObject;
			T result = caseGexfContent(gexfContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.GRAPH_CONTENT: {
			GraphContent graphContent = (GraphContent) theEObject;
			T result = caseGraphContent(graphContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.META_CONTENT: {
			MetaContent metaContent = (MetaContent) theEObject;
			T result = caseMetaContent(metaContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.NODE_CONTENT: {
			NodeContent nodeContent = (NodeContent) theEObject;
			T result = caseNodeContent(nodeContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.NODES_CONTENT: {
			NodesContent nodesContent = (NodesContent) theEObject;
			T result = caseNodesContent(nodesContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.PARENTS_CONTENT: {
			ParentsContent parentsContent = (ParentsContent) theEObject;
			T result = caseParentsContent(parentsContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.PARENT_TYPE: {
			ParentType parentType = (ParentType) theEObject;
			T result = caseParentType(parentType);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.SPELLS_CONTENT: {
			SpellsContent spellsContent = (SpellsContent) theEObject;
			T result = caseSpellsContent(spellsContent);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case GexfPackage.SPELL_TYPE: {
			SpellType spellType = (SpellType) theEObject;
			T result = caseSpellType(spellType);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attribute Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attribute Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeContent(AttributeContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attributes Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attributes Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributesContent(AttributesContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attvalues Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attvalues Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttvaluesContent(AttvaluesContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attvalue Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attvalue Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttvalueType(AttvalueType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Document Root</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Edge Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Edge Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgeContent(EdgeContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Edges Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Edges Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdgesContent(EdgesContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Content</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGexfContent(GexfContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Graph Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Graph Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGraphContent(GraphContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Meta Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Meta Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaContent(MetaContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Node Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Node Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeContent(NodeContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Nodes Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Nodes Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodesContent(NodesContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Parents Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Parents Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParentsContent(ParentsContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Parent Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Parent Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParentType(ParentType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Spells Content</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Spells Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpellsContent(SpellsContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Spell Type</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Spell Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpellType(SpellType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */

	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //GexfSwitch
