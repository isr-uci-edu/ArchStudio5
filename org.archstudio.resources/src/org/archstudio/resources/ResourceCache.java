package org.archstudio.resources;

import java.net.URL;

import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ResourceCache {

	public static final Image getImage(URL url) {
		return ImageUtils.getImage(Display.getDefault(), url);
	}

	public static final Image getIcon(IXArchADT xarch, ObjRef objRef) {
		return ImageUtils.getIcon16ForType(Display.getDefault(), XadlUtils.getType(xarch, objRef));
	}

	public static final Image getIcon(Class<?> forClass) {
		return ImageUtils.getIcon16ForType(Display.getDefault(), forClass);
	}
}