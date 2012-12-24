package org.archstudio.xarchadt.variability.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import com.google.common.collect.Maps;

public class ChangeSetSorter extends ViewerComparator implements IXArchADTModelListener {

	protected final IXArchADTVariability xarch;
	protected Viewer viewer = null;

	public ChangeSetSorter(IXArchADTVariability xarch) {
		this.xarch = xarch;
	}

	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		//		if (evt.getXArchRef().equals(xArchRef)) {
		//			if ((evt.getTargetName().equals("changeSetOrder") || evt.getTargetName().equals("appliedChangeSets"))
		//					&& evt.getSourcePath().toTagsOnlyString().equals("xArch/variability")) {
		//				refreshView();
		//			}
		//		}
	}

	boolean needsRefresh = false;

	void refreshView() {
		needsRefresh = true;
		final Viewer fViewer = viewer;
		SWTWidgetUtils.async(fViewer, new Runnable() {

			public void run() {
				if (needsRefresh) {
					needsRefresh = false;
					fViewer.refresh();
				}
			}
		});
	}

	public boolean isSorterProperty(Object element, String property) {
		return false;
	}

	public int category(Object element) {
		throw new UnsupportedOperationException();
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		throw new UnsupportedOperationException();
	}

	synchronized public void sort(Viewer viewer, Object[] elements) {
		this.viewer = viewer;
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, (ObjRef) viewer.getInput());
		if (documentRoot != null) {
			XADLType xadlType = documentRoot.getXADL();
			Variability variability = null;
			if (xadlType != null) {
				for (EObject tle : xadlType.getTopLevelElement()) {
					if (tle instanceof Variability) {
						variability = (Variability) tle;
					}
				}
			}
			if (variability != null) {
				final Map<ObjRef, Integer> orderMap = Maps.newHashMap();
				for (ChangeSet changeSet : variability.getChangeSet()) {
					orderMap.put(XArchADTProxy.unproxy(changeSet), orderMap.size());
				}
				final Integer FIRST = Integer.MIN_VALUE;
				Arrays.sort(elements, new Comparator<Object>() {
					public int compare(Object o1, Object o2) {
						Integer i1 = orderMap.get(o1);
						Integer i2 = orderMap.get(o2);
						return -(i1 == null ? FIRST : i1).compareTo(i2 == null ? FIRST : i2);
					}
				});
			}
		}
	}
}
