/**
 */
package net.gexf_1_2.viz.util;

import net.gexf_1_2.viz.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.gexf_1_2.viz.VizPackage
 * @generated
 */
public class VizAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static VizPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VizAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = VizPackage.eINSTANCE;
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
  protected VizSwitch<Adapter> modelSwitch =
    new VizSwitch<Adapter>()
    {
      @Override
      public Adapter caseColorContent(ColorContent object)
      {
        return createColorContentAdapter();
      }
      @Override
      public Adapter caseEdgeShapeContent(EdgeShapeContent object)
      {
        return createEdgeShapeContentAdapter();
      }
      @Override
      public Adapter caseNodeShapeContent(NodeShapeContent object)
      {
        return createNodeShapeContentAdapter();
      }
      @Override
      public Adapter casePositionContent(PositionContent object)
      {
        return createPositionContentAdapter();
      }
      @Override
      public Adapter caseSizeContent(SizeContent object)
      {
        return createSizeContentAdapter();
      }
      @Override
      public Adapter caseThicknessContent(ThicknessContent object)
      {
        return createThicknessContentAdapter();
      }
      @Override
      public Adapter caseDocumentRoot(DocumentRoot object)
      {
        return createDocumentRootAdapter();
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
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.ColorContent <em>Color Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.ColorContent
   * @generated
   */
  public Adapter createColorContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.EdgeShapeContent <em>Edge Shape Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.EdgeShapeContent
   * @generated
   */
  public Adapter createEdgeShapeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.NodeShapeContent <em>Node Shape Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.NodeShapeContent
   * @generated
   */
  public Adapter createNodeShapeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.PositionContent <em>Position Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.PositionContent
   * @generated
   */
  public Adapter createPositionContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.SizeContent <em>Size Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.SizeContent
   * @generated
   */
  public Adapter createSizeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.ThicknessContent <em>Thickness Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.ThicknessContent
   * @generated
   */
  public Adapter createThicknessContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link net.gexf_1_2.viz.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see net.gexf_1_2.viz.DocumentRoot
   * @generated
   */
  public Adapter createDocumentRootAdapter()
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

} //VizAdapterFactory
