package org.archstudio.resources.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.archstudio.common.EclipseUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

public class ArchStudioCommonResources{
	private static final String BASE_URL = "platform:/plugin/org.archstudio.resources.common/"; 
	
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
	
	public static void init(IResources resources){
		if(initedResources.contains(resources)) return;
		
		try{
			resources.createImage(ICON_STRUCTURE, EclipseUtils.getBytes(BASE_URL + "res/icon-structure.gif"));
			resources.createImage(ICON_TYPES, EclipseUtils.getBytes(BASE_URL + "res/icon-types.gif"));
			
			resources.createImage(ICON_OVERLAY_XML, EclipseUtils.getBytes(BASE_URL + "res/decorator-xml.gif"));
			Image xmlOverlay = resources.getImage(ICON_OVERLAY_XML);
			resources.createOverlayImage(ICON_XML_DOCUMENT, 
				resources.getPlatformImage(ISharedImages.IMG_OBJ_FOLDER), 
				new Image[]{xmlOverlay}, new int[]{IResources.BOTTOM_RIGHT});

			resources.createImage(ICON_COMPONENT, EclipseUtils.getBytes(BASE_URL + "res/icon-component.gif"));
			resources.createImage(ICON_CONNECTOR, EclipseUtils.getBytes(BASE_URL + "res/icon-connector.gif"));
			resources.createImage(ICON_INTERFACE, EclipseUtils.getBytes(BASE_URL + "res/icon-interface.gif"));
			resources.createImage(ICON_LINK, EclipseUtils.getBytes(BASE_URL + "res/icon-link.gif"));
			
			resources.createImage(ICON_COMPONENT_TYPE, EclipseUtils.getBytes(BASE_URL + "res/icon-component-type.gif"));
			resources.createImage(ICON_CONNECTOR_TYPE, EclipseUtils.getBytes(BASE_URL + "res/icon-connector-type.gif"));
			resources.createImage(ICON_INTERFACE_TYPE, EclipseUtils.getBytes(BASE_URL + "res/icon-interface-type.gif"));
			
			resources.createImage(ICON_STATECHART, EclipseUtils.getBytes(BASE_URL + "res/icon-statechart.gif"));
			resources.createImage(ICON_STATE, EclipseUtils.getBytes(BASE_URL + "res/icon-state.gif"));
			resources.createImage(ICON_INITIAL_STATE, EclipseUtils.getBytes(BASE_URL + "res/icon-initialstate.gif"));
			resources.createImage(ICON_FINAL_STATE, EclipseUtils.getBytes(BASE_URL + "res/icon-finalstate.gif"));

			resources.createImage(ICON_GRID, EclipseUtils.getBytes(BASE_URL + "res/icon-grid.gif"));
		}
		catch(IOException ioe){
			throw new RuntimeException("This shouldn't happen.");
		}

		initedResources.add(resources);
	}
}
