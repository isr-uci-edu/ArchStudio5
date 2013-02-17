package org.archstudio.bna.things;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.glu.GLU;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

/*
 * Render all shadows at the same time, then blur together.
 */
public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	private static AtomicInteger count = new AtomicInteger();
	private static GL2 gl;
	private static int programs[] = new int[2];
	private static int shaders[] = new int[2];

	private static void initialize(GL2 gl) {
		if (ShadowThingPeer.gl != gl) {
			destroy();
		}
		if (ShadowThingPeer.gl == null) {
			try {
				loadShaderProgram(gl, 0, GL2ES2.GL_FRAGMENT_SHADER,
						ShadowThingPeer.class.getResourceAsStream("glsl/blurH.fs"));
				loadShaderProgram(gl, 1, GL2ES2.GL_FRAGMENT_SHADER,
						ShadowThingPeer.class.getResourceAsStream("glsl/blurV.fs"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				ShadowThingPeer.gl = gl;
			}
		}
	}

	private static void destroy() {
		if (gl != null) {
			try {
				for (int i = 0; i < programs.length; i++) {
					if (programs[i] != 0) {
						try {
							gl.glDeleteProgram(programs[i]);
						}
						catch (Exception e) {
						}
						finally {
							programs[i] = 0;
						}
					}
				}

				for (int i = 0; i < shaders.length; i++) {
					if (shaders[i] != 0) {
						try {
							gl.glDeleteShader(shaders[i]);
						}
						catch (Exception e) {
						}
						finally {
							shaders[i] = 0;
						}
					}
				}
			}
			catch (Exception e) {
			}
			finally {
				gl = null;
			}
		}
	}

	private static void loadShaderProgram(GL2 gl, int number, int shaderType, InputStream source) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(source));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}
		br.close();

		programs[number] = gl.glCreateProgram();
		shaders[number] = gl.glCreateShader(shaderType);
		gl.glAttachShader(programs[number], shaders[number]);
		gl.glShaderSource(shaders[number], 1, new String[] { sb.toString() }, new int[] { sb.length() }, 0);

		gl.glCompileShader(shaders[number]);
		handleGLError(gl, shaders[number]);

		gl.glLinkProgram(programs[number]);
		handleGLError(gl, programs[number]);
	}

	private static void handleGLError(GL2 gl, int obj) {
		String errors;
		if ((errors = getGLErrorLog(gl, obj)) != null) {
			throw new RuntimeException("Compile error:\n" + errors);
		}
	}

	private static String getGLErrorLog(GL2 gl, int obj) {
		boolean hasError = false;
		int[] infologLength = { 0 };
		int[] charsWritten = { 0 };
		byte[] infoLog;

		String message = "";
		String error = getGLError(gl);
		if (error != null) {
			message += error;
			hasError = true;
		}

		gl.glGetObjectParameterivARB(obj, GL2.GL_OBJECT_INFO_LOG_LENGTH_ARB, infologLength, 0);
		error = getGLError(gl);
		if (error != null) {
			message += (hasError ? "\n" : "") + error;
			hasError = true;
		}

		if (infologLength[0] > 1) {
			infoLog = new byte[infologLength[0]];
			gl.glGetInfoLogARB(obj, infologLength[0], charsWritten, 0, infoLog, 0);
			message += (hasError ? "\n" : "") + "InfoLog:\n" + new String(infoLog);
			hasError = true;
		}
		error = getGLError(gl);
		if (error != null) {
			message += (hasError ? "\n" : "") + error;
			hasError = true;
		}
		if (message != null && message.indexOf("successfully compiled") >= 0) {
			//System.out.println(message);
			message = null;
		}
		if (message != null && message.indexOf("shader(s) linked") >= 0) {
			//System.out.println(message);
			message = null;
		}
		if (message != null && message.indexOf("Validation successful.") >= 0) {
			//System.out.println(message);
			message = null;
		}
		return hasError ? message : null;
	}

	private static String getGLError(GL2 gl) {
		boolean hasError = false;
		String message = "";
		for (int glErr = gl.glGetError(); glErr != GL.GL_NO_ERROR; glErr = gl.glGetError()) {
			message += (hasError ? "\n" : "") + new GLU().gluErrorString(glErr);
			hasError = true;
		}
		return hasError ? message : null;
	}

	public ShadowThingPeer(T thing) {
		super(thing);
		count.incrementAndGet();
	}

	@Override
	public void dispose() {
		if (count.decrementAndGet() == 0) {
			destroy();
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return;
		}

		if (!BNARenderingSettings.getDisplayShadows(view.getComposite())) {
			return;
		}

		initialize(gl);
		if (programs[0] == 0 || programs[1] == 0) {
			return;
		}

		final int offset = BNAUtils.round(6 * cm.getLocalScale());
		final int size = Math.min(offset, 15);

		int[] frameBufferName = new int[1];
		int[] renderTexture = new int[2];
		int[] renderBuffers = new int[1];

		try {
			int status;

			// create and bind framebuffer
			gl.glGenFramebuffers(frameBufferName.length, frameBufferName, 0);
			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, frameBufferName[0]);

			// create and bind textures
			gl.glGenTextures(renderTexture.length, renderTexture, 0);
			for (int element : renderTexture) {
				gl.glBindTexture(GL.GL_TEXTURE_2D, element);
				gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, clip.width, clip.height, 0, GL.GL_RGBA,
						GL.GL_UNSIGNED_BYTE, null);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			}

			// create and bind render buffer
			gl.glGenRenderbuffers(renderBuffers.length, renderBuffers, 0);
			gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, renderBuffers[0]);
			gl.glRenderbufferStorage(GL.GL_RENDERBUFFER, GL.GL_DEPTH_COMPONENT16, clip.width, clip.height);
			gl.glFramebufferRenderbuffer(GL.GL_FRAMEBUFFER, GL.GL_DEPTH_ATTACHMENT, GL.GL_RENDERBUFFER,
					renderBuffers[0]);

			// draw shadows on image 0
			gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER, GL.GL_COLOR_ATTACHMENT0, GL.GL_TEXTURE_2D, renderTexture[0], 0);
			int[] drawBuffers = new int[] { GL.GL_COLOR_ATTACHMENT0 };
			gl.glDrawBuffers(drawBuffers.length, drawBuffers, 0);

			if ((status = gl.glCheckFramebufferStatus(GL.GL_FRAMEBUFFER)) != GL.GL_FRAMEBUFFER_COMPLETE) {
				throw new RuntimeException("glCheckFramebufferStatus: error " + status);
			}

			// clear the background
			gl.glClearColor(1f, 1f, 1f, 0f);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);

			// draw the shadows
			gl.glTranslated(offset, offset, 0);
			try {
				Rectangle newClip = new Rectangle(offset, offset, clip.width, clip.height);
				for (IThing t : view.getBNAWorld().getBNAModel().getAllThings()) {
					IThingPeer<?> tp = view.getThingPeer(t);
					if (tp instanceof IHasShadowPeer) {
						IHasShadowPeer stp = (IHasShadowPeer) tp;
						stp.drawShadow(view, cm, gl, newClip, r);
					}
				}
			}
			finally {
				gl.glTranslated(-offset, -offset, 0);
			}

			// perform a blur of image 0 to image 1
			gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER, GL.GL_COLOR_ATTACHMENT0, GL.GL_TEXTURE_2D, renderTexture[1], 0);
			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, renderBuffers[0]);

			if ((status = gl.glCheckFramebufferStatus(GL.GL_FRAMEBUFFER)) != GL.GL_FRAMEBUFFER_COMPLETE) {
				throw new RuntimeException("glCheckFramebufferStatus: error " + status);
			}

			// clear the background
			gl.glClearColor(1f, 1f, 1f, 0f);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);

			// apply blur
			gl.glUseProgram(programs[0]);

			gl.glActiveTexture(GL.GL_TEXTURE0);
			gl.glBindTexture(GL.GL_TEXTURE_2D, renderTexture[0]);
			gl.glUniform1i(gl.glGetUniformLocation(programs[0], "texture"), 0);
			gl.glUniform2f(gl.glGetUniformLocation(programs[0], "resolution"), clip.width, clip.height);
			gl.glUniform1i(gl.glGetUniformLocation(programs[0], "size"), size);

			gl.glValidateProgram(programs[0]);
			handleGLError(gl, programs[0]);

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2i(0, 0);
			gl.glVertex2i(0, clip.height);
			gl.glVertex2i(clip.width, clip.height);
			gl.glVertex2i(clip.width, 0);
			gl.glEnd();

			gl.glUseProgram(0);

			// perform a blur of image 1 to the main buffer
			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, 0);

			if ((status = gl.glCheckFramebufferStatus(GL.GL_FRAMEBUFFER)) != GL.GL_FRAMEBUFFER_COMPLETE) {
				throw new RuntimeException("glCheckFramebufferStatus: error " + status);
			}

			// apply blur
			gl.glUseProgram(programs[1]);

			gl.glActiveTexture(GL.GL_TEXTURE0);
			gl.glBindTexture(GL.GL_TEXTURE_2D, renderTexture[1]);
			gl.glUniform1i(gl.glGetUniformLocation(programs[1], "texture"), 0);
			gl.glUniform2f(gl.glGetUniformLocation(programs[1], "resolution"), clip.width, clip.height);
			gl.glUniform1i(gl.glGetUniformLocation(programs[1], "size"), size);

			gl.glValidateProgram(programs[1]);
			handleGLError(gl, programs[1]);

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2i(0, 0);
			gl.glVertex2i(0, clip.height);
			gl.glVertex2i(clip.width, clip.height);
			gl.glVertex2i(clip.width, 0);
			gl.glEnd();

			gl.glUseProgram(0);
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		finally {
			// done rendering, render to main buffer
			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, 0);

			// dispose resources
			for (int i = 0; i < renderTexture.length; i++) {
				if (renderTexture[i] != 0) {
					gl.glDeleteTextures(1, renderTexture, i);
				}
			}
			if (renderBuffers[0] != 0) {
				gl.glDeleteRenderbuffers(renderBuffers.length, renderBuffers, 0);
			}
			if (frameBufferName[0] != 0) {
				gl.glDeleteFramebuffers(frameBufferName.length, frameBufferName, 0);
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
