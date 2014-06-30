package org.archstudio.bna.ui.jogl;

import java.awt.Dimension;
import java.nio.FloatBuffer;

import org.archstudio.bna.ui.IUIResources;

import com.jogamp.opengl.util.PMVMatrix;

public interface IJOGLResources extends IUIResources {

	public void fillShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int n);

	public void drawShape(Dimension size, int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int n);

	public PMVMatrix getMatrix();

	// PMVMatrix.glPopMatrix is buggy, use these methods instead
	public void pushMatrix(int matrixName);

	// PMVMatrix.glPopMatrix is buggy, use these methods instead
	public void popMatrix(int matrixName);

}
