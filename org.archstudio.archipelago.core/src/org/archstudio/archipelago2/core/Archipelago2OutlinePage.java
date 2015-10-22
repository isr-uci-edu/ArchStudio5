package org.archstudio.archipelago2.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2ContentProvider;
import org.archstudio.archipelago2.IArchipelago2LabelDecorator;
import org.archstudio.archipelago2.IArchipelago2LabelProvider;
import org.archstudio.archipelago2.IArchipelago2MenuContributor;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.archipelago2.IArchipelago2Provider;
import org.archstudio.archipelago2.OutlineElementTransfer;
import org.archstudio.archipelago2.core.outline.CopyPasteObjRefMenuContributor;
import org.archstudio.archipelago2.core.outline.ObjRefLabelDecorator;
import org.archstudio.archipelago2.core.outline.ObjRefLabelProvider;
import org.archstudio.archipelago2.core.outline.PathContentProvider;
import org.archstudio.archipelago2.core.outline.PathLabelProvider;
import org.archstudio.archipelago2.core.outline.XAdlContentProvider;
import org.archstudio.archipelago2.core.outline.XAdlLabelProvider;
import org.archstudio.archipelago2.core.outline.swt.EditObjRefNameInOutine;
import org.archstudio.archipelago2.core.outline.swt.EditOnClickOfAlreadySelectedElement;
import org.archstudio.bna.ui.utils.AbstractBNAUI;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Maintains an outline for Archipelago editors.
 * <p/>
 * Each element in the outline consists of a list of all nodes from the root {@link #docRef} node to
 * a given element, capturing the path from the root. This allows content providers and listeners to
 * receive and manipulate the full context of a particular path. The only exception is that content
 * provides return only the child node objects rather than new lists; from these, this class create
 * new element lists.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class Archipelago2OutlinePage extends ContentOutlinePage
    implements IArchipelago2Outline, ILabelProviderListener {
  /** XArchADT instance backing this label provider. */
  protected final IXArchADT xarch;

  /** XArchADT root document reference. */
  protected final ObjRef docRef;

  /** The content providers supplied by the org.archstudio.archipelago2.outlines extension. */
  protected List<IArchipelago2ContentProvider> contentProviders = new ArrayList<>();

  /** The content providers supplied by the org.archstudio.archipelago2.outlines extension. */
  protected List<IArchipelago2LabelProvider> labelProviders = new ArrayList<>();

  /** The label decorators supplied by the org.archstudio.archipelago2.outlines extension. */
  protected List<IArchipelago2LabelDecorator> labelDecorators = new ArrayList<>();

  /** The menu contributors supplied by the org.archstudio.archipelago2.outlines extension. */
  protected List<IArchipelago2MenuContributor> menuContributors = new ArrayList<>();

  /**
   * The listeners to notify if the user performs a "hard" selection, i.e., double clicks on an
   * element or presses enter while it is selected.
   */
  protected List<ISelectionChangedListener> hardSelectionListeners = new CopyOnWriteArrayList<>();

  /**
   * A cross reference of node objects to the tree elements (i.e., List<Object>) that contain them.
   */
  protected Map<Object, Set<List<Object>>> nodeObjectToElementsMap = new HashMap<>();

  /**
   * A cross reference of tree elements (i.e., List<Object>) and the children node objects they
   * contain.
   */
  protected Map<Object, Set<Object>> elementToChildNodeObjectsMap = new HashMap<>();

  /** The element to select and edit when it becomes available. */
  protected List<Object> selectAndEditWhenAvailable = null;

  /** The cell text editor. */
  protected EditObjRefNameInOutine editor;

  public Archipelago2OutlinePage(IXArchADT xarch, ObjRef docRef) {
    this.xarch = xarch;
    this.docRef = docRef;
  }

  /**
   * Initializes a provider by calling its
   * {@link IArchipelago2Provider#init(IXArchADT, ObjRef, org.eclipse.swt.widgets.Display, IArchipelago2Outline)
   * init} method.
   *
   * @param provider the provider to initialize.
   * @return the provider after initialization.
   */
  private <T extends IArchipelago2Provider> T init(T provider) {
    provider.init(xarch, docRef, getTreeViewer().getTree().getDisplay(), this);
    if (provider instanceof IBaseLabelProvider) {
      ((IBaseLabelProvider) provider).addListener(this);
    }
    if (provider instanceof IArchipelago2ContentProvider) {
      ((IArchipelago2ContentProvider) provider).addListener(this);
    }
    return provider;
  }

  @Override
  public void dispose() {
    for (IArchipelago2Provider provider : Iterables.concat(contentProviders, labelProviders,
        labelDecorators)) {
      if (provider instanceof IBaseLabelProvider) {
        ((IBaseLabelProvider) provider).removeListener(this);
      }
      if (provider instanceof IArchipelago2ContentProvider) {
        ((IArchipelago2ContentProvider) provider).removeListener(this);
      }
      provider.dispose();
    }
    contentProviders.clear();
    labelProviders.clear();
    labelDecorators.clear();
    super.dispose();
  }

  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);
    final TreeViewer treeViewer = getTreeViewer();
    final Tree tree = treeViewer.getTree();
    MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
    IMyxBrick brick = myxRegistry.waitForBrick(Archipelago2MyxComponent.class);

    // Read content and label provider extensions.
    PathContentProvider pathContentProvider = init(new PathContentProvider());
    IExtensionRegistry reg = Platform.getExtensionRegistry();
    if (reg != null) {
      for (IConfigurationElement configurationElement : reg
          .getConfigurationElementsFor("org.archstudio.archipelago2.outlines")) {
        String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
        if ("Path".equals(configurationElement.getName())) {
          pathContentProvider.addPath(configurationElement.getAttribute("name"));
        } else if ("ContentProvider".equals(configurationElement.getName())) {
          String providerClassName = configurationElement.getAttribute("class");
          try {
            Class<?> providerClass = Platform.getBundle(bundleName).loadClass(providerClassName);
            contentProviders.add(init((IArchipelago2ContentProvider) providerClass.newInstance()));
          } catch (Exception ignored) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, bundleName, "Class "
                + providerClassName + " either doesn't exist or has no default constructor."));
          }
        } else if ("LabelProvider".equals(configurationElement.getName())) {
          String providerClassName = configurationElement.getAttribute("class");
          try {
            Class<?> providerClass = Platform.getBundle(bundleName).loadClass(providerClassName);
            labelProviders.add(init((IArchipelago2LabelProvider) providerClass.newInstance()));
          } catch (Exception ignored) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, bundleName, "Class "
                + providerClassName + " either doesn't exist or has no default constructor."));
          }
        } else if ("LabelDecorator".equals(configurationElement.getName())) {
          String providerClassName = configurationElement.getAttribute("class");
          try {
            Class<?> providerClass = Platform.getBundle(bundleName).loadClass(providerClassName);
            labelDecorators.add(init((IArchipelago2LabelDecorator) providerClass.newInstance()));
          } catch (Exception ignored) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, bundleName, "Class "
                + providerClassName + " either doesn't exist or has no default constructor."));
          }
        } else if ("MenuContributor".equals(configurationElement.getName())) {
          String providerClassName = configurationElement.getAttribute("class");
          try {
            Class<?> providerClass = Platform.getBundle(bundleName).loadClass(providerClassName);
            menuContributors.add(init((IArchipelago2MenuContributor) providerClass.newInstance()));
          } catch (Exception ignored) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, bundleName, "Class "
                + providerClassName + " either doesn't exist or has no default constructor."));
          }
        }
      }
    }
    contentProviders.add(pathContentProvider);
    labelProviders.add(init(new PathLabelProvider(pathContentProvider)));
    contentProviders.add(init(new XAdlContentProvider()));
    labelProviders.add(init(new XAdlLabelProvider()));
    labelProviders.add(init(new ObjRefLabelProvider()));
    labelDecorators.add(init(new ObjRefLabelDecorator()));
    menuContributors.add(init(new CopyPasteObjRefMenuContributor()));
    for (Object provider : Iterables.concat(contentProviders, labelProviders, labelDecorators)) {
      MyxRegistry.getSharedInstance().registerObject(brick, provider);
    }

    // Enable editing ObjRef name attribute in the outline view.
    editor = new EditObjRefNameInOutine(this, treeViewer, xarch);
    // Trigger edit on selection of already selected item.
    new EditOnClickOfAlreadySelectedElement(this, treeViewer, editor);

    // Add a content provider that accumulates content from the providers in contentProvider.
    treeViewer.setContentProvider(new ITreeContentProvider() {
      @Override
      public void dispose() {}

      @Override
      public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        for (IArchipelago2ContentProvider provider : contentProviders) {
          provider.inputChanged(viewer, oldInput, newInput);
        }
      }

      @Override
      public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
      }

      @Override
      public Object[] getChildren(Object parentElement) {
        @SuppressWarnings("unchecked")
        List<Object> parentPath = (List<Object>) parentElement;
        Set<Object> children = new HashSet<>();
        for (IArchipelago2ContentProvider provider : contentProviders) {
          children.addAll(Arrays.asList(provider.getElements(parentPath)));
        }
        children.remove(null);
        return updateCrossReferences(parentElement, children);
      }

      /**
       * Updates nodeObjectToElementsMap and elementToChildNodeObjectsMap to reflect the new set of
       * children.
       *
       * @param element The element containing the children.
       * @param newChildren The new set of children for that element.
       * @return the newChildren in the form of an array.
       */
      @SuppressWarnings("unchecked")
      private Object[] updateCrossReferences(Object element, Set<Object> newChildren) {
        Set<Object> children = elementToChildNodeObjectsMap.get(element);
        if (children == null) {
          elementToChildNodeObjectsMap.put(element, children = new HashSet<>());
        }
        Set<Object> differingChildren = Sets.symmetricDifference(children, newChildren);
        for (Object child : Lists.newArrayList(differingChildren)) {
          Set<List<Object>> elements = nodeObjectToElementsMap.get(child);
          if (elements == null) {
            nodeObjectToElementsMap.put(child, elements = new HashSet<>());
          }
          List<Object> childElement = Archipelago2Utils.append((List<Object>) element, child);
          if (children.remove(child)) {
            elements.remove(childElement);
            if (elements.size() == 0) {
              nodeObjectToElementsMap.remove(child);
            }
          } else {
            children.add(child);
            elements.add(childElement);
          }
        }
        if (newChildren.size() == 0) {
          elementToChildNodeObjectsMap.remove(element);
        }
        List<Object> elementPath = (List<Object>) element;
        List<List<Object>> childPaths = Lists.newArrayListWithCapacity(newChildren.size());
        for (Object child : newChildren) {
          childPaths.add(Archipelago2Utils.append(elementPath, child));
        }
        return childPaths.toArray(new Object[childPaths.size()]);
      }

      @Override
      public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
      }

      @Override
      public Object getParent(Object element) {
        @SuppressWarnings("unchecked")
        List<Object> childPath = (List<Object>) element;
        if (childPath.isEmpty()) {
          return null;
        }
        return childPath.subList(0, childPath.size() - 1);
      }
    });

    // Add a label provider that scans the labelProviders and labelDecorators for a label.
    final IStyledLabelProvider labelProvider = new IStyledLabelProvider() {
      @Override
      public void dispose() {}

      @Override
      public void addListener(ILabelProviderListener listener) {}

      @Override
      public void removeListener(ILabelProviderListener listener) {}

      @Override
      public boolean isLabelProperty(Object element, String property) {
        @SuppressWarnings("unchecked")
        List<Object> nodePath = (List<Object>) element;
        for (IArchipelago2LabelProvider provider : labelProviders) {
          if (provider.isLabelProperty(nodePath, property)) {
            return true;
          }
        }
        for (IArchipelago2LabelDecorator provider : labelDecorators) {
          if (provider.isLabelProperty(nodePath, property)) {
            return true;
          }
        }
        return false;
      }

      @Override
      public Image getImage(Object element) {
        @SuppressWarnings("unchecked")
        List<Object> nodePath = (List<Object>) element;
        Image image = null;
        for (IArchipelago2LabelProvider provider : labelProviders) {
          image = provider.getImage(nodePath);
          if (image != null) {
            break;
          }
        }
        if (image != null) {
          for (IArchipelago2LabelDecorator provider : labelDecorators) {
            Image decoratedImage = provider.decorateImage(image, nodePath);
            if (decoratedImage != null) {
              image = decoratedImage;
            }
          }
        }
        return image;
      }

      @Override
      public StyledString getStyledText(Object element) {
        @SuppressWarnings("unchecked")
        List<Object> nodePath = (List<Object>) element;
        StyledString text = null;
        for (IArchipelago2LabelProvider provider : labelProviders) {
          text = provider.getStyledText(nodePath);
          if (text != null) {
            break;
          }
        }
        if (text == null) {
          Object node = nodePath.get(nodePath.size() - 1);
          if (node instanceof ObjRef) {
            text = new StyledString("[No label provider for " + node + " ("
                + XadlUtils.getType(xarch, (ObjRef) node).getSimpleName() + ")]");
          }
          text = new StyledString(
              "[No label provider for " + node + " (" + node.getClass().getName() + ")]");
        }
        for (IArchipelago2LabelDecorator provider : labelDecorators) {
          StyledString decoratedText = provider.decorateStyledText(text, nodePath);
          if (decoratedText != null) {
            text = decoratedText;
          }
        }
        return text;
      }
    };
    treeViewer.setLabelProvider(new DecoratingStyledCellLabelProvider(labelProvider, null, null));

    // Add a sorter that places strings first, then sorts by XSD schema element order, then by class
    // name, then by label.
    treeViewer.setSorter(new ViewerSorter() {
      Map<Object, Object> nodeCategories = new HashMap<>();
      Map<Object, Integer> categoryValues = new HashMap<>();

      @Override
      public boolean isSorterProperty(Object element, String property) {
        return true;
      }

      private Object getCategory(Object element) {
        @SuppressWarnings("unchecked")
        List<Object> nodePath = (List<Object>) element;
        Object node = nodePath.get(nodePath.size() - 1);
        // If node is an ObjRef, get the feature ID number for the node.
        // The thinking is that feature IDs are assigned in the order that they appear in the schema
        // file, and thus, will indicate the order of siblings.
        if (node instanceof ObjRef) {
          ObjRef objRef = (ObjRef) node;
          ObjRef parentRef = xarch.getParent(objRef);
          String name = xarch.getContainingFeatureName(objRef);
          if (parentRef != null && name != null) {
            IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(parentRef);
            String nsURI = typeMetadata.getNsURI();
            EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
            if (ePackage != null) {
              String typeName = typeMetadata.getTypeName();
              EClassifier eClassifier = ePackage.getEClassifier(typeName);
              if (eClassifier instanceof EClass) {
                EClass eClass = (EClass) eClassifier;
                EStructuralFeature feature = eClass.getEStructuralFeature(name);
                if (feature != null) {
                  return feature.getFeatureID();
                }
              }
            }
          }
        }
        return node.getClass();
      }

      @Override
      public int category(Object element) {
        Integer category = categoryValues.get(nodeCategories.get(element));
        return category != null ? category.intValue() : 0;
      }

      @SuppressWarnings("unchecked")
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        int cat1 = category(e1);
        int cat2 = category(e2);
        if (cat1 != cat2) {
          return cat1 - cat2;
        }
        String ss1 = labelProvider.getStyledText(e1).toString();
        String ss2 = labelProvider.getStyledText(e2).toString();
        if (!ss1.equals(ss2)) {
          return ss1.compareTo(ss2);
        }
        List<Object> l1 = (List<Object>) e1;
        List<Object> l2 = (List<Object>) e2;
        if (l1.size() == 0) {
          if (l2.size() == 0) {
            return 0;
          }
          return -1;
        }
        if (l2.size() == 0) {
          return 1;
        }
        Object last1 = l1.get(l1.size() - 1);
        Object last2 = l2.get(l2.size() - 1);
        return last1.toString().compareTo(last2.toString());
      }

      @Override
      public void sort(Viewer viewer, Object[] elements) {
        nodeCategories.clear();
        categoryValues.clear();
        for (Object element : elements) {
          Preconditions.checkNotNull(element);
          Object category = Preconditions.checkNotNull(getCategory(element));
          nodeCategories.put(element, category);
        }
        List<Object> categories = Lists.newArrayList(Sets.newHashSet(nodeCategories.values()));
        Collections.sort(categories, new Comparator<Object>() {
          @SuppressWarnings("unchecked")
          @Override
          public int compare(Object o1, Object o2) {
            // Place strings first.
            if (o1.equals(String.class)) {
              return -1;
            }
            if (o2.equals(String.class)) {
              return 1;
            }
            // Place numbers second, sort by the number.
            if (o1 instanceof Number) {
              if (o1 instanceof Comparable && o1.getClass() == o2.getClass()) {
                return ((Comparable<Object>) o1).compareTo(o2);
              }
              return -1;
            }
            // Place all other objects last, sort by toString().
            return o1.toString().compareTo(o2.toString());
          }
        });
        for (int i = 0; i < categories.size(); ++i) {
          categoryValues.put(categories.get(i), i);
        }
        super.sort(viewer, elements);
      }
    });

    // Trigger a hard selection event upon double click.
    treeViewer.addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event) {
        fireSelectionEvent(new SelectionChangedEvent(event.getViewer(), event.getSelection()),
            hardSelectionListeners);
      }
    });

    // Trigger events on key press.
    tree.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        // A hard selection on pressing enter.
        if (e.character == '\n') {
          fireSelectionEvent(new SelectionChangedEvent(treeViewer, treeViewer.getSelection()),
              hardSelectionListeners);
        }
        // A cell edit on pressing F2
        if (e.keyCode == SWT.F2) {
          ISelection selection = treeViewer.getSelection();
          if (selection instanceof StructuredSelection) {
            for (Object element : ((StructuredSelection) selection).toList()) {
              editElement(element, 0);
              break;
            }
          }
        }
      }
    });

    // Populate context menus with IArchipelago2MenuContributor instances.
    MenuManager menuMgr = new MenuManager();
    menuMgr.setRemoveAllWhenShown(true);
    menuMgr.addMenuListener(new IMenuListener() {
      @SuppressWarnings("unchecked")
      @Override
      public void menuAboutToShow(IMenuManager manager) {
        AbstractBNAUI.addMenuSeparators(manager);
        ISelection selection = treeViewer.getSelection();
        if (selection instanceof IStructuredSelection) {
          for (Object element : ((IStructuredSelection) selection).toList()) {
            for (IArchipelago2MenuContributor menuContributor : menuContributors) {
              menuContributor.fillMenu(Archipelago2OutlinePage.this, (List<Object>) element,
                  manager);
            }
          }
        }
      }
    });
    Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
    treeViewer.getControl().setMenu(menu);

    // Register drag and drop operations.
    treeViewer.addDragSupport(DND.DROP_LINK,
        new Transfer[] {(Transfer) OutlineElementTransfer.getInstance()}, new DragSourceListener() {
          /*
           * We have to hang on to the current selection because on some platforms (Mac) the tree
           * selection is cleared when dragging
           */
          private List<?> selectedElement = Lists.newArrayList();

          @Override
          public void dragStart(DragSourceEvent event) {
            event.doit = false;
            ISelection selection = treeViewer.getSelection();
            if (selection instanceof ITreeSelection) {
              ITreeSelection treeSelection = (ITreeSelection) selection;
              if (treeSelection.size() == 1) {
                selectedElement = (List<?>) treeSelection.getFirstElement();
                if (!(selectedElement.get(selectedElement.size() - 1) instanceof String)) {
                  event.doit = true;
                  return;
                }
              }
            }
          }

          @Override
          public void dragSetData(DragSourceEvent event) {
            event.doit = false;
            if (OutlineElementTransfer.getInstance().isSupportedType(event.dataType)) {
              if (selectedElement.size() > 0) {
                if (OutlineElementTransfer.getInstance().isSupportedType(event.dataType)) {
                  event.doit = true;
                  event.data = selectedElement;
                  return;
                }
              }
            }
          }

          @Override
          public void dragFinished(DragSourceEvent event) {}
        });

    // Set outline root element.
    treeViewer.setUseHashlookup(true);
    treeViewer.setAutoExpandLevel(3);
    treeViewer.setInput(Collections.singletonList(docRef));
  }

  /**
   * Refreshes the tree for the given elements. The elements may be a node object, in which case
   * {@link #nodeObjectToElementMap} is consulted to get tree elements to refresh; or, the elements
   * may be a tree element, in which case the tree element is refreshed directly.
   *
   * @param event The event with the elements to refresh.
   */
  @SuppressWarnings("unchecked")
  @Override
  public void labelProviderChanged(LabelProviderChangedEvent event) {
    TreeViewer treeViewer = getTreeViewer();
    List<List<Object>> elementsToRefresh = Lists.newArrayList();
    for (Object element : SystemUtils.emptyIfNull(event.getElements())) {
      if (element instanceof List) {
        elementsToRefresh.add((List<Object>) element);
      } else {
        for (List<Object> treeElement : SystemUtils
            .emptyIfNull(nodeObjectToElementsMap.get(element))) {
          elementsToRefresh.add(treeElement);
        }
      }
    }
    Object editingElement = treeViewer.isCellEditorActive() ? editor.getEditingElement() : null;
    for (Object element : elementsToRefresh) {
      treeViewer.update(element, new String[] {""}); // Properties must be non-empty.
      treeViewer.refresh(element, true);
    }
    if (editingElement != null) {
      editor.editElement(editingElement, 0);
    }
  }

  /**
   * Fires a selection event to a list of listeners.
   *
   * @param event The selection event to fire.
   * @param listeners The listeners to receive the event.
   */
  protected void fireSelectionEvent(SelectionChangedEvent event,
      List<ISelectionChangedListener> listeners) {
    for (ISelectionChangedListener listener : listeners) {
      try {
        listener.selectionChanged(event);
      } catch (Exception ignored) {
        ignored.printStackTrace();
      }
    }
  }

  /**
   * Adds a listener that will be notified if the user performs a "hard" selection, i.e., double
   * clicks on an outline node or presses enter while it is selected.
   *
   * @param listener The listener to receive the event.
   */
  public void addHardSelectionListener(ISelectionChangedListener listener) {
    hardSelectionListeners.add(listener);
  }

  /**
   * Removes a listener that will be notified if the user performs a "hard" selection, i.e., double
   * clicks on an outline node or presses enter while it is selected.
   *
   * @param listener The listener to receive the event.
   */
  public void removeHardSelectionListener(ISelectionChangedListener listener) {
    hardSelectionListeners.add(listener);
  }

  @Override
  public void selectElement(final Object element) {
    @SuppressWarnings("unchecked")
    final List<Object> nodes = (List<Object>) element;
    final TreeViewer treeViewer = getTreeViewer();
    treeViewer.getTree().getDisplay().asyncExec(new Runnable() {
      @Override
      public void run() {
        for (int i = 1; i <= nodes.size(); ++i) {
          List<Object> sublistElement = nodes.subList(0, i);
          treeViewer.refresh(sublistElement, true);
          treeViewer.expandToLevel(sublistElement, 1);
        }
        IStructuredSelection newSelection = new StructuredSelection(element);
        treeViewer.setSelection(newSelection);
      }
    });
  }

  @Override
  public void editElement(final Object element, final int column) {
    getTreeViewer().getTree().getDisplay().asyncExec(new Runnable() {
      @Override
      public void run() {
        editor.editElement(element, column);
      }
    });
  }
}
