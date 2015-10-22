package org.archstudio.archipelago2.ext.statecharts.utils;

import java.util.List;

import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.statechart_1_0.FinalState;
import org.archstudio.xadl3.statechart_1_0.InitialState;
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.StateType;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.Transition;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Preconditions;

public class MenuContributor {
  public static final void addNewStatechartAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef xAdlRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(xAdlRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Statechart") {
      @Override
      public void run() {
        final ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.STATECHART);
        xarch.set(newRef, "id", UIDGenerator.generateUID("statechart"));
        XadlUtils.setName(xarch, newRef, "[New Statechart]");
        XArchADTOperations.add("New statechart", xarch, xAdlRef, "topLevelElement", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils.toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(),
            Statechart_1_0Package.Literals.STATECHART.getInstanceClass()));
      }
    });
  }

  public static final void addNewStateAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef statechartRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(statechartRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New State") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.STATE);
        xarch.set(newRef, "id", UIDGenerator.generateUID("state_"));
        xarch.set(newRef, "type", StateType.STATE);
        XadlUtils.setName(xarch, newRef, "[New State]");
        XArchADTOperations.add("Add State", xarch, statechartRef, "state", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), State.class));
      }
    });
  }

  public static final void addNewTransitionAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef statechartRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(statechartRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Transition") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.TRANSITION);
        xarch.set(newRef, "id", UIDGenerator.generateUID("transition_"));
        XadlUtils.setName(xarch, newRef, "[New Transition]");
        XArchADTOperations.add("Add Transition", xarch, statechartRef, "transition", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), Transition.class));
      }
    });
  }

  public static final void addNewInitialStateAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef statechartRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(statechartRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP,
        new Action("New Initial State") {
          @Override
          public void run() {
            ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.INITIAL_STATE);
            xarch.set(newRef, "id", UIDGenerator.generateUID("initialState_"));
            xarch.set(newRef, "type", StateType.INITIAL);
            XadlUtils.setName(xarch, newRef, "[New Initial State]");
            XArchADTOperations.add("Add Initial State", xarch, statechartRef, "state", newRef);
            if (outline != null && parentElement != null) {
              List<Object> element = Archipelago2Utils.append(parentElement, newRef);
              outline.selectElement(element);
              outline.editElement(element, 0);
            }
          }

          @Override
          public ImageDescriptor getImageDescriptor() {
            return ImageUtils.toImageDescriptor(
                ImageUtils.getIcon16ForType(Display.getDefault(), InitialState.class));
          }
        });
  }

  public static final void addNewFinalStateAction(IMenuManager menuManager, final IXArchADT xarch,
      final ObjRef statechartRef, @Nullable final IArchipelago2Outline outline,
      @Nullable final List<Object> parentElement) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(statechartRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP, new Action("New Final State") {
      @Override
      public void run() {
        ObjRef newRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.FINAL_STATE);
        xarch.set(newRef, "id", UIDGenerator.generateUID("finalState_"));
        xarch.set(newRef, "type", StateType.FINAL);
        XadlUtils.setName(xarch, newRef, "[New Final State]");
        XArchADTOperations.add("Add Final State", xarch, statechartRef, "state", newRef);
        if (outline != null && parentElement != null) {
          List<Object> element = Archipelago2Utils.append(parentElement, newRef);
          outline.selectElement(element);
          outline.editElement(element, 0);
        }
      }

      @Override
      public ImageDescriptor getImageDescriptor() {
        return ImageUtils
            .toImageDescriptor(ImageUtils.getIcon16ForType(Display.getDefault(), FinalState.class));
      }
    });
  }

  public static final void addClearSubStatechartAction(IMenuManager menuManager,
      final IXArchADT xarch, final ObjRef stateRef) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(stateRef);
    menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP,
        new Action("Remove SubStatechart") {
          @Override
          public void run() {
            xarch.clear(stateRef, Statechart_1_0Package.Literals.STATE__SUB_STATECHART.getName());
          }
        });
  }

  public static final void addClearStatechartSpecAction(IMenuManager menuManager,
      final IXArchADT xarch, final ObjRef objRef) {
    Preconditions.checkNotNull(menuManager);
    Preconditions.checkNotNull(xarch);
    Preconditions.checkNotNull(objRef);
    final ObjRef statechartSpec = XadlUtils.getExt(xarch, objRef, Statechart_1_0Package.eNS_URI,
        Statechart_1_0Package.Literals.STATECHART_SPECIFICATION.getName());
    if (statechartSpec != null) {
      menuManager.appendToGroup(IBNAMenuListener2.NEW_ELEMENTS_GROUP,
          new Action("Remove Statechart Spec") {
            @Override
            public void run() {
              xarch.remove(objRef, "ext", statechartSpec);
            }
          });
    }
  }
}
