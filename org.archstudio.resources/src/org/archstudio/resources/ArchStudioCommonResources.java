package org.archstudio.resources;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class ArchStudioCommonResources {
	public static final String ICON_STRUCTURE = "archstudio:icon/structure";
	public static final String ICON_XML_DOCUMENT = "archstudio:icon/xml-document";

	public static final String ICON_OVERLAY_XML = "archstudio:icon-overlay/xml";

	public static final String ICON_COMPONENT = "archstudio:icon/component";
	public static final String ICON_CONNECTOR = "archstudio:icon/connector";
	public static final String ICON_INTERFACE = "archstudio:icon/interface";
	public static final String ICON_LINK = "archstudio:icon/link";
	public static final String ICON_TYPES = "archstudio:icon/types";
	public static final String ICON_COMPONENT_TYPE = "archstudio:icon/component-type";
	public static final String ICON_CONNECTOR_TYPE = "archstudio:icon/connector-type";
	public static final String ICON_INTERFACE_TYPE = "archstudio:icon/interface-type";

	public static final String ICON_STATECHART = "archstudio:icon/statechart";
	public static final String ICON_STATE = "archstudio:icon/state";
	public static final String ICON_INITIAL_STATE = "archstudio:icon/initial-state";
	public static final String ICON_FINAL_STATE = "archstudio:icon/final-state";

	public static final String ICON_GRID = "archstudio:icon/grid";

	protected static Set<IResources> initedResources = new HashSet<IResources>();

	public static void init(IResources resources) {
		if (initedResources.contains(resources)) {
			return;
		}

		try {
			resources.createImage(ICON_STRUCTURE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-structure.gif")));
			resources.createImage(ICON_TYPES,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-types.gif")));

			resources.createImage(ICON_OVERLAY_XML,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("decorator-xml.gif")));
			Image xmlOverlay = resources.getImage(ICON_OVERLAY_XML);
			resources.createOverlayImage(ICON_XML_DOCUMENT, resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER),
					new Image[] { xmlOverlay }, new int[] { IResources.BOTTOM_RIGHT });

			resources.createImage(ICON_COMPONENT,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-component.gif")));
			resources.createImage(ICON_CONNECTOR,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-connector.gif")));
			resources.createImage(ICON_INTERFACE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-interface.gif")));
			resources.createImage(ICON_LINK,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-link.gif")));

			resources.createImage(ICON_COMPONENT_TYPE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-component-type.gif")));
			resources.createImage(ICON_CONNECTOR_TYPE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-connector-type.gif")));
			resources.createImage(ICON_INTERFACE_TYPE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-interface-type.gif")));

			resources.createImage(ICON_STATECHART,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-statechart.gif")));
			resources.createImage(ICON_STATE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-state.gif")));
			resources.createImage(ICON_INITIAL_STATE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-initialstate.gif")));
			resources.createImage(ICON_FINAL_STATE,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-finalstate.gif")));

			resources.createImage(ICON_GRID,
					SystemUtils.blt(ArchStudioCommonResources.class.getResourceAsStream("icon-grid.gif")));
		}
		catch (IOException ioe) {
			throw new RuntimeException("This shouldn't happen.");
		}

		initedResources.add(resources);
	}
}
