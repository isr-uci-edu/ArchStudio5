package org.archstudio.xarchadt.variability.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTModelEvent.EventType;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ChangeSetLabelProvider extends LabelProvider implements IColorProvider, IFontProvider,
		ITableLabelProvider, IXArchADTModelListener, IXArchADTVariabilityListener {

	enum AppliedType {
		APPLIED, IMPLIED, EXCLUDED, UNAPPLIED
	};

	public static final String APPLY_PROPERTY = "Apply";
	public static final String EXPLICIT_PROPERTY = "View";
	public static final String CHANGE_SET_PROPERTY = "Change Set";
	public static final List<String> properties = Collections.unmodifiableList(Lists.newArrayList("", APPLY_PROPERTY,
			EXPLICIT_PROPERTY, CHANGE_SET_PROPERTY));

	protected final ColumnViewer viewer;
	protected final IXArchADTVariability xarch;

	ImageRegistry imageRegistry = null;
	Font activeChangeSetFont = null;

	ObjRef activeChangeSetRef = null;
	Map<ObjRef, AppliedType> appliedChangeSetRefs = Maps.newHashMap();
	Set<ObjRef> explicitChangeSetRefs = Sets.newHashSet();

	public ChangeSetLabelProvider(ColumnViewer viewer, IXArchADTVariability xarch) {
		this.viewer = viewer;
		this.xarch = xarch;

		Display d = PlatformUI.getWorkbench().getDisplay();

		imageRegistry = new ImageRegistry(d);
		imageRegistry.put("applied", new Image(d, getClass().getResourceAsStream("applied.gif")));
		imageRegistry.put("implied", new Image(d, getClass().getResourceAsStream("implied.gif")));
		imageRegistry.put("excluded", new Image(d, getClass().getResourceAsStream("excluded.gif")));
		imageRegistry.put("unapplied", new Image(d, getClass().getResourceAsStream("unapplied.gif")));
		imageRegistry.put("explicit", new Image(d, getClass().getResourceAsStream("explicit.gif")));
		imageRegistry.put("implicit", new Image(d, getClass().getResourceAsStream("implicit.gif")));
		imageRegistry.put("transform", new Image(d, getClass().getResourceAsStream("transform.gif")));

		FontData[] fontDatas = d.getSystemFont().getFontData();
		if (fontDatas != null && fontDatas.length > 0) {
			for (FontData f : fontDatas) {
				f.setStyle(f.getStyle() | SWT.BOLD);
			}
			activeChangeSetFont = new Font(d, fontDatas);
		}

		refreshFromXADL();
	}

	private void refreshFromXADL() {
		activeChangeSetRef = null;
		appliedChangeSetRefs.clear();
		explicitChangeSetRefs.clear();

		ObjRef documentRootRef = SystemUtils.castOrNull(viewer.getInput(), ObjRef.class);
		if (documentRootRef != null && xarch.isChangeSetsEnabled(documentRootRef)) {
			activeChangeSetRef = xarch.getActiveChangeSet(documentRootRef);
			for (ObjRef changeSetRef : xarch.getAppliedChangeSets(documentRootRef)) {
				appliedChangeSetRefs.put(changeSetRef, AppliedType.APPLIED);
			}
			explicitChangeSetRefs.addAll(xarch.getExplicitChangeSets(documentRootRef));
		}
	}

	@Override
	public void dispose() {
		if (imageRegistry != null)
			imageRegistry.dispose();
		if (activeChangeSetFont != null)
			activeChangeSetFont.dispose();
		super.dispose();
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return properties.contains(property);
	}

	public Image getColumnImage(Object element, int columnIndex) {
		Object columnProperty = properties.get(columnIndex);

		if (APPLY_PROPERTY.equals(columnProperty)) {
			ObjRef changeSetRef = (ObjRef) element;
			if (appliedChangeSetRefs.get(changeSetRef) != null) {
				switch (appliedChangeSetRefs.get(changeSetRef)) {
				case APPLIED:
					return imageRegistry.get("applied");
				case IMPLIED:
					return imageRegistry.get("implied");
				case EXCLUDED:
					return imageRegistry.get("excluded");
				default:
					break;
				}
			}
			return imageRegistry.get("unapplied");
		}
		else if (EXPLICIT_PROPERTY.equals(columnProperty)) {
			ObjRef changeSetRef = (ObjRef) element;
			if (explicitChangeSetRefs.contains(changeSetRef))
				return imageRegistry.get("explicit");
			else
				return imageRegistry.get("implicit");
		}
		else if (CHANGE_SET_PROPERTY.equals(columnProperty)) {
			ObjRef changeSetRef = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, changeSetRef,
					Variability_3_0Package.Literals.TRANSFORM_CHANGE_SET_OF_CHANGES)) {
				return imageRegistry.get("transform");
			}
			return null;
		}
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		Object columnProperty = properties.get(columnIndex);

		if (APPLY_PROPERTY.equals(columnProperty)) {
			return null;
		}
		else if (EXPLICIT_PROPERTY.equals(columnProperty)) {
			return null;
		}
		else if (CHANGE_SET_PROPERTY.equals(columnProperty)) {
			return XadlUtils.getName(xarch, (ObjRef) element);
		}
		return null;
	}

	@Override
	public void handleXArchADTModelEvent(@NonNull XArchADTModelEvent evt) {
		if (evt.getEventType() == EventType.ADD) {
			if (evt.getNewValuePath().equals("xADL/variability/changeSet")) {
				refresh();
			}
		}
		if (evt.getEventType() == EventType.SET) {
			if (evt.getSourcePath().equals("xADL/variability/changeSet")) {
				refresh();
			}
		}
	}

	@Override
	public void handleXArchADTVariabilityEvent(XArchADTVariabilityEvent evt) {
		if (evt.getDocumentRootRef().equals(viewer.getInput())) {
			refresh();
		}
	}

	public void setChangeColors(ObjRef objRef) {
		//		changeSetRefToColor.clear();
		//		if (objRef != null) {
		//			ObjRef xArchRef = xarch.getDocumentRootRef(objRef);
		//			if (objRef != null && xarch.isChangeSetsEnabled(xArchRef)) {
		//				ObjRef[] changeSetRefs = XArchChangeSetUtils.getOrderedChangeSets(xarch, xArchRef, "appliedChangeSets",
		//						false).toArray(new ObjRef[0]);
		//				ChangeStatus[] changeStatuses = xarch.getAllChangeStatus(objRef, changeSetRefs);
		//				Display display = PlatformUI.getWorkbench().getDisplay();
		//				for (int i = 0; i < changeSetRefs.length; i++) {
		//					ObjRef changeSetRef = changeSetRefs[i];
		//					ChangeStatus changeStatus = changeStatuses[i];
		//					Color color = null;
		//					if (changeStatus != null) {
		//						switch (changeStatus) {
		//						case ADDED:
		//							color = display.getSystemColor(SWT.COLOR_DARK_CYAN);
		//							break;
		//						case MODIFIED:
		//							color = display.getSystemColor(SWT.COLOR_MAGENTA);
		//							break;
		//						case REMOVED:
		//							color = display.getSystemColor(SWT.COLOR_RED);
		//							break;
		//						default:
		//							color = display.getSystemColor(SWT.COLOR_GRAY);
		//							break;
		//						}
		//					}
		//					changeSetRefToColor.put(changeSetRef, color);
		//				}
		//			}
		//		}
		//		fireLabelProviderChanged(new LabelProviderChangedEvent(ChangeSetLabelProvider.this));
	}

	private Map<ObjRef, Color> changeSetRefToColor = new HashMap<ObjRef, Color>();

	public Color getBackground(Object element) {
		//		ObjRef changeSetRef = (ObjRef) element;
		//		return changeSetRefToColor.get(changeSetRef) == null ? 
		//				null : PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WHITE);
		return null;
	}

	public Color getForeground(Object element) {
		ObjRef changeSetRef = (ObjRef) element;
		return changeSetRefToColor.get(changeSetRef);
	}

	public Font getFont(Object element) {
		if (SystemUtils.nullEquals(element, activeChangeSetRef)) {
			return activeChangeSetFont;
		}
		return null;
	}

	boolean needsRefresh = false;

	protected void refresh() {
		refreshFromXADL();

		needsRefresh = true;
		SWTWidgetUtils.async(viewer, new Runnable() {

			public void run() {
				if (needsRefresh) {
					needsRefresh = false;
					fireLabelProviderChanged(new LabelProviderChangedEvent(ChangeSetLabelProvider.this));
				}
			}
		});
	}

}
