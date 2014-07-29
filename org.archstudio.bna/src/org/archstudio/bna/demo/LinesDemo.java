package org.archstudio.bna.demo;

import java.awt.geom.Point2D;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import org.archstudio.bna.ui.jogl.JOGLResources;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Program;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Shader;

import com.jogamp.common.nio.Buffers;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.PMVMatrix;

public class LinesDemo implements GLEventListener {

	PMVMatrix matrix = new PMVMatrix();
	long startTime = 0;
	float x, y, l;
	GL2ES2Shader line_vp = null;
	GL2ES2Shader line_fp = null;
	GL2ES2Program line = null;

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2ES2 gl = drawable.getGL().getGL2ES2();

		System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
		System.err.println("INIT GL IS: " + gl.getClass().getName());
		System.err.println("HARDWARE_ACCELERATION: " + gl.getGL2ES2().getGLProfile().isHardwareRasterizer());
		System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
		System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
		System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));

		line_vp = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/line2d.vp"));
		line_fp = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/line2d.fp"));

		line = GL2ES2Program.create(gl, line_vp, line_fp);
		line.bindAttribute("attribute_position", 1);
		line.bindAttribute("attribute_color", 1);
		line.bindAttribute("attribute_stipple_offset", 1);
		line.link();

		gl.glDisable(GL.GL_LINE_SMOOTH);
		gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_FASTEST);

		startTime = System.currentTimeMillis();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2ES2 gl = drawable.getGL().getGL2ES2();
		gl.glViewport(x, y, width, height);
		matrix.glLoadIdentity();
		matrix.glOrthof(x, x + width, y + height, y, -10000, 10000);
		this.x = width / 2;
		this.y = height / 2;
		this.l = Math.min(width, height) / 2;
	}

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2ES2 gl = drawable.getGL().getGL2ES2();

		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL.GL_STENCIL_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		float o = 0; // SystemUtils.loop(0, (System.currentTimeMillis() - startTime) % 1000, (float) Math.PI * 2);
		for (float a = 0; a < 2 * Math.PI; a += (float) Math.PI / 360 * 15) {
			float p1x = x + 60 * (float) Math.sin(a + o) + 0.5f;
			float p1y = y + 60 * (float) Math.cos(a + o) + 0.5f;
			float p2x = x + l * (float) Math.sin(a + o) + 0.5f;
			float p2y = y + l * (float) Math.cos(a + o) + 0.5f;
			FloatBuffer vertices = Buffers.newDirectFloatBuffer(new float[] { p1x, p1y, p2x, p2y });
			FloatBuffer colors = Buffers.newDirectFloatBuffer(new float[] { 1, 0, 0, 1, 0, 0, 1, 1 });
			FloatBuffer offsets = Buffers.newDirectFloatBuffer(new float[] { 0,
					(float) Point2D.distance(p1x, p1y, p2x, p2y) });
			gl.glLineWidth(4 * a / (float) Math.PI);
			line.use();
			gl.glUniformMatrix4fv(line.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1i(line.getUniform("uniform_stipple"), 0x11111111);
			line.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", 2, vertices, GL.GL_STATIC_DRAW, 2, false);
			line.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", 2, colors, GL.GL_STATIC_DRAW, 4, false);
			line.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", 2, offsets, GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(GL.GL_LINE_STRIP, 0, 2);
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		if (line != null) {
			line.dispose();
			line = null;
		}
		if (line_vp != null) {
			line_vp.dispose();
			line_vp = null;
		}
		if (line_fp != null) {
			line_fp.dispose();
			line_fp = null;
		}
	}

	public static void main(String[] args) {
		GLCapabilities caps = new GLCapabilities(GLProfile.get(GLProfile.GL2ES2));
		caps.setHardwareAccelerated(true);
		GLWindow glWindow = GLWindow.create(caps);

		glWindow.setTitle("Raw GL2ES2 Demo");
		glWindow.setSize(500, 500);
		glWindow.setUndecorated(false);
		glWindow.setPointerVisible(true);
		glWindow.setVisible(true);
		glWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDestroyed(WindowEvent e) {
				System.exit(0);
			}
		});

		glWindow.addGLEventListener(new LinesDemo());
		Animator animator = new Animator();
		animator.add(glWindow);
		animator.start();
	}

}
