/**
 */
package net.gexf_1_2.gexf.util;

import net.gexf_1_2.gexf.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.gexf_1_2.gexf.GexfPackage
 * @generated
 */
public class GexfAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static GexfPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GexfAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = GexfPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GexfSwitch<Adapter> modelSwitch =
    new GexfSwitch<Adapter>()
    {
      @Override
      public Adapter caseAttributeContent(AttributeContent object)
      {
        return createAttributeContentAdapter();
      }
      @Override
      public Adapter caseAttributesContent(AttributesContent object)
      {
        return createAttributesContentAdapter();
      }
      @Override
      public Adapter caseAttvaluesContent(AttvaluesContent object)
      {
        return createAttvaluesContentAdapter();
      }
      @Override
      public Adapter caseAttvalueType(AttvalueType object)
      {
        return createAttvalueTypeAdapter();
      }
      @Override
      public Adapter caseDocumentRoot(DocumentRoot object)
      {
        return createDocumentRootAdapter();
      }
      @Override
      public Adapter caseEdgeContent(EdgeContent object)
      {
        return createEdgeContentAdapter();
      }
      @Override
      public Adapter caseEdgesContent(EdgesContent object)
      {
        return createEdgesContentAdapter();
      }
      @Override
      public Adapter caseGexfContent(GexfContent object)
      {
        return createGexfContentAdapter();
      }
      @Override
      public Adapter caseGraphContent(GraphContent object)
      {
        return createGraphContentAdapter();
      }
      @Override
      public Adapter caseMetaContent(MetaContent object)
      {
        return createMetaContentAdapter();
      }
      @Override
      public Adapter caseNodeContent(NodeContent object)
      {
        return createNodeContentAdapter();
      }
      @Override
      public Adapter caseNodesContent(NodesContent object)
      {
        return createNodesContentAdapter();
      }
      @Override
      public Adapter caseParentsContent(ParentsContent object)
      {
        return createParentsContentAdapter();
      }
      @Override
      public Adapter caseParentType(ParentType object)
      {
        return createParentTypeAdapter();
      }
      @Override
      public Adapter caseSpellsContent(SpellsContent object)
      {
        return createSpellsContentAdapter();
      }
      @Override
      public Adapter caseSpellType(SpellType object)
      {
        return createSpellTypeAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.AttributeContent <em>Attribute Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.AttributeContent
   * @generated
   */
  public Adapter createAttributeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.AttributesContent <em>Attributes Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.AttributesContent
   * @generated
   */
  public Adapter createAttributesContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.AttvaluesContent <em>Attvalues Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.AttvaluesContent
   * @generated
   */
  public Adapter createAttvaluesContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.AttvalueType <em>Attvalue Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.AttvalueType
   * @generated
   */
  public Adapter createAttvalueTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.DocumentRoot
   * @generated
   */
  public Adapter createDocumentRootAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.EdgeContent <em>Edge Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.EdgeContent
   * @generated
   */
  public Adapter createEdgeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.EdgesContent <em>Edges Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.EdgesContent
   * @generated
   */
  public Adapter createEdgesContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.GexfContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.GexfContent
   * @generated
   */
  public Adapter createGexfContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.GraphContent <em>Graph Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.GraphContent
   * @generated
   */
  public Adapter createGraphContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.MetaContent <em>Meta Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.MetaContent
   * @generated
   */
  public Adapter createMetaContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.NodeContent <em>Node Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.NodeContent
   * @generated
   */
  public Adapter createNodeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.NodesContent <em>Nodes Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.NodesContent
   * @generated
   */
  public Adapter createNodesContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.ParentsContent <em>Parents Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.ParentsContent
   * @generated
   */
  public Adapter createParentsContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.ParentType <em>Parent Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.ParentType
   * @generated
   */
  public Adapter createParentTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.SpellsContent <em>Spells Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.SpellsContent
   * @generated
   */
  public Adapter createSpellsContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.gexf.SpellType <em>Spell Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.gexf.SpellType
   * @generated
   */
  public Adapter createSpellTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //GexfAdapterFactory
