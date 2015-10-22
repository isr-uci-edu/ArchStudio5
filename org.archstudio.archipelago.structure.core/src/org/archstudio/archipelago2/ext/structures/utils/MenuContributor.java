package org.archstudio.archipelago2.ext.structures.utils;

import java.util.List;

import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.archipelago2.ext.structures.logics.CreateMappingLogic;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Link;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Preconditions;

public class MenuContributor {
  public static final void addNewStructureAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef xAdlRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(xAdlRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Structure") {
      @Override
      public void run() {
        final ObjRef newRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.STRUCTURE);
        xarch.set(newRef, "id", UIDGenerator.generateUID());
        XadlUtils.setName(xarch, newRef, "[New Structure]");
        XArchADTOperations.add("New structure", xarch, xAdlRef, "topLevelElement", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils.toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(),
            Structure_3_0Package.Literals.STRUCTURE.getInstanceClass()));
      }
    });
  }

  public static final void addNewComponentAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef structureRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(structureRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Component") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.COMPONENT);
        xarch.set(newRef, "id", UIDGenerator.generateUID());
        XadlUtils.setName(xarch, newRef, "[New Component]");
        XArchADTOperations.add("Add Component", xarch, structureRef, "component", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), Component.class));
      }
    });
  }

  public static final void addNewConnectorAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef structureRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(structureRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Connector") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.CONNECTOR);
        xarch.set(newRef, "id", UIDGenerator.generateUID());
        XadlUtils.setName(xarch, newRef, "[New Connector]");
        XArchADTOperations.add("Add Connector", xarch, structureRef, "connector", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), Connector.class));
      }
    });
  }

  public static final void addNewInterfaceAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef brickRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(brickRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Interface") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE);
        xarch.set(newRef, "id", UIDGenerator.generateUID());
        XadlUtils.setName(xarch, newRef, "[New Interface]");
        xarch.set(newRef, "direction", Direction.NONE);
        XArchADTOperations.add("Add Interface", xarch, brickRef, "interface", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), Interface.class));
      }
    });
  }

  public static final void addNewLinkAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef structureRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(structureRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Link") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.LINK);
        xarch.set(newRef, "id", UIDGenerator.generateUID());
        XadlUtils.setName(xarch, newRef, "[New Link]");
        XArchADTOperations.add("Add Link", xarch, structureRef, "link", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), Link.class));
      }
    });
  }

  public static final void addNewMapping(IMenuManager menuManager, final IXArchADT xarch,
      final IBNAView view, IThing possibleInterfaceThing, final Point initialLocalPoint) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(view);
    Preconditions.checkNotNull(possibleInterfaceThing);
    possibleInterfaceThing =
        Assemblies.getRoot(view.getBNAWorld().getBNAModel(), possibleInterfaceThing);
    // This is only applicable to interfaces.
    if (possibleInterfaceThing == null) {
      return;
    }
    final ObjRef ifaceRef = possibleInterfaceThing.get(IHasObjRef.OBJREF_KEY);
    if (ifaceRef == null) {
      return;
    }
    if (!XadlUtils.isInstanceOf(xarch, ifaceRef, Structure_3_0Package.Literals.INTERFACE)) {
      return;
    }
    // This is only applicable to bricks with a substructure.
    final ObjRef brickRef = xarch.getParent(ifaceRef);
    final ObjRef subStructureRef =
        (ObjRef) xarch.get(brickRef, Structure_3_0Package.Literals.BRICK__SUB_STRUCTURE.getName());
    if (subStructureRef == null) {
      return;
    }
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP,
        new BNAAction("New Interface-Interface Mapping...") {
          @Override
          public void runWithLock() {
            IBNAWorld world = view.getBNAWorld();
            IThingLogicManager logics = world.getThingLogicManager();
            CreateMappingLogic createLogic = logics.addThingLogic(CreateMappingLogic.class);
            createLogic.beginEditing(view, xarch, ifaceRef, subStructureRef);
          }

          @Override
          public ImageDescriptor getImageDescriptor() {
            return ImageUtils.toImageDescriptor(
                ImageUtils.getIcon16ForType(Display.getDefault(), InterfaceMapping.class));
          }
        });
  }

  public static final void addClearSubStructureAction(IMenuManager menuManager,
      final IXArchADT xarch, final ObjRef brickRef) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(brickRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP,
        new Action("Remove SubStructure") {
          @Override
          public void run() {
            xarch.clear(brickRef, Structure_3_0Package.Literals.BRICK__SUB_STRUCTURE.getName());
          }
        });
  }
}
