package org.archstudio.bna.ui.jogl;

import java.awt.geom.Point2D;

import org.archstudio.bna.ui.IUIResources;
import org.archstudio.sysutils.Matrix;

public interface IJOGLResources extends IUIResources {

	public Matrix getMatrix();

	public Point2D.Double transformXY(Matrix matrix, double x, double y, double z);

}
