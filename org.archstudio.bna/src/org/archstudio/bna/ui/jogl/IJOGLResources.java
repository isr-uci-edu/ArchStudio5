package org.archstudio.bna.ui.jogl;

import java.nio.FloatBuffer;

import org.archstudio.bna.ui.IUIResources;

import com.jogamp.opengl.util.PMVMatrix;

public interface IJOGLResources extends IUIResources {

	public void fillShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int numPoints);

	public void drawShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int numPoints, int stipple);

	public PMVMatrix getMatrix();

	// PMVMatrix.glPopMatrix is buggy, use these methods instead
	public void pushMatrix(int matrixName);

	// PMVMatrix.glPopMatrix is buggy, use these methods instead
	public void popMatrix(int matrixName);

	public void pushBlendFunction();

	public void popBlendFunction();

}
