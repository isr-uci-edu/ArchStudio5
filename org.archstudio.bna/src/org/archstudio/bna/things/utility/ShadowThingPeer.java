package org.archstudio.bna.things.utility;

import java.util.concurrent.atomic.AtomicInteger;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLContext;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.RenderAttachment;
import com.jogamp.opengl.FBObject.TextureAttachment;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> {

	static AtomicInteger blurInitCount = new AtomicInteger();
	static GL2ES2 blurGL;
	static ShaderCode blurHCode;
	static ShaderProgram blurHProgram;
	static boolean blurHInitialized;
	static ShaderCode blurVCode;
	static ShaderProgram blurVProgram;
	static boolean blurVInitialized;

	private static void init() {
		synchronized (blurInitCount) {
			if (blurInitCount.incrementAndGet() == 1) {
				blurGL = GLContext.getCurrentGL().getGL2ES2();
				blurHCode = ShaderCode.create(blurGL, GL2ES2.GL_FRAGMENT_SHADER, 1, ShadowThingPeer.class,
						new String[] { "glsl/blurH.fs" }, false);
				if (blurHCode != null) {
					blurHProgram = new ShaderProgram();
					if (blurHProgram.init(blurGL)) {
						if (blurHProgram.add(blurGL, blurHCode, System.err)) {
							if (blurHProgram.link(blurGL, System.err)) {
								blurHInitialized = blurHProgram.program() != 0 && blurHProgram.linked();
							}
						}
					}
				}
				blurVCode = ShaderCode.create(blurGL, GL2ES2.GL_FRAGMENT_SHADER, 1, ShadowThingPeer.class,
						new String[] { "glsl/blurV.fs" }, false);
				if (blurVCode != null) {
					blurVProgram = new ShaderProgram();
					if (blurVProgram.init(blurGL)) {
						if (blurVProgram.add(blurGL, blurVCode, System.err)) {
							if (blurVProgram.link(blurGL, System.err)) {
								blurVInitialized = blurVProgram.program() != 0 && blurVProgram.linked();
							}
						}
					}
				}
			}
		}
	}

	private static void destroy() {
		synchronized (blurInitCount) {
			if (blurInitCount.decrementAndGet() == 0) {
				if (blurGL != null) {
					blurVInitialized = false;
					if (blurVProgram != null) {
						blurVProgram.release(blurGL);
						blurVProgram = null;
					}
					if (blurVCode != null) {
						blurVCode.destroy(blurGL);
						blurVCode = null;
					}
					blurHInitialized = false;
					if (blurHProgram != null) {
						blurHProgram.release(blurGL);
						blurHProgram = null;
					}
					if (blurHCode != null) {
						blurHCode.destroy(blurGL);
						blurHCode = null;
					}
					blurGL = null;
				}
			}
		}
	}

	boolean initialized = false;

	public ShadowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void dispose() {
		if (initialized) {
			initialized = false;
			destroy();
		}
		super.dispose();
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return;
		}

		if (!initialized) {
			initialized = true;
			init();
		}
		if (blurHInitialized && blurVInitialized) {
			//gl = new TraceGL2(GLContext.getCurrentGL().getGL2(), System.err);
			FBObject fbObject = null;
			RenderAttachment renderAttachment = null;
			TextureAttachment texture0Attachment = null;
			TextureAttachment texture1Attachment = null;
			try {
				// create framebuffer
				fbObject = new FBObject();
				fbObject.reset(gl, localBounds.width, localBounds.height);

				// create and bind renderbuffers
				fbObject.attachRenderbuffer(gl, GL.GL_DEPTH_COMPONENT16);
				renderAttachment = fbObject.getDepthAttachment();
				renderAttachment.initialize(gl);

				// create and bind texture 0
				texture0Attachment = fbObject.attachTexture2D(gl, 0, true);
				texture0Attachment.initialize(gl);

				// draw shadows on texture 0 ...
				gl.glDrawBuffers(1, new int[] { GL.GL_COLOR_ATTACHMENT0 + 0 }, 0);

				// ... clear the background
				gl.glClearColor(1f, 1f, 1f, 0f);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);

				// ... draw shadows
				int shadowOffset = BNAUtils.round(6 * cm.getLocalScale());
				int shadowSize = Math.min(shadowOffset, 15);
				gl.glTranslated(shadowOffset, -shadowOffset, 0);
				try {
					Rectangle newLocalBounds = new Rectangle(-shadowOffset, shadowOffset, localBounds.width,
							localBounds.height);
					for (IThing t : model.getAllThings()) {
						IThingPeer<?> tp = view.getThingPeer(t);
						if (tp instanceof IHasShadowPeer) {
							IHasShadowPeer<?> stp = (IHasShadowPeer<?>) tp;
							stp.drawShadow(gl, newLocalBounds, r);
						}
					}
				}
				finally {
					gl.glTranslated(-shadowOffset, shadowOffset, 0);
				}

				// create and bind texture 1
				texture1Attachment = fbObject.attachTexture2D(gl, 1, true);
				texture1Attachment.initialize(gl);

				// draw a blur of texture 0 on texture 1 ...
				gl.glDrawBuffers(1, new int[] { GL.GL_COLOR_ATTACHMENT0 + 1 }, 0);

				// ... clear the background
				gl.glClearColor(1f, 1f, 1f, 0f);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);

				// ... apply blur
				blurVProgram.useProgram(gl, true);
				try {
					gl.glUniform1i(gl.glGetUniformLocation(blurVProgram.program(), "texture"), 0);
					gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
					gl.glBindTexture(GL.GL_TEXTURE_2D, texture0Attachment.getName());
					gl.glUniform2f(gl.glGetUniformLocation(blurVProgram.program(), "resolution"), localBounds.width,
							localBounds.height);
					gl.glUniform1i(gl.glGetUniformLocation(blurVProgram.program(), "size"), shadowSize);
					if (!blurVProgram.validateProgram(gl, System.err)) {
						blurVInitialized = false;
					}

					gl.glBegin(GL2.GL_TRIANGLE_FAN);
					gl.glVertex2i(0, 0);
					gl.glVertex2i(0, localBounds.height);
					gl.glVertex2i(localBounds.width, localBounds.height);
					gl.glVertex2i(localBounds.width, 0);
					gl.glEnd();
				}
				finally {
					blurVProgram.useProgram(gl, false);
				}

				// draw a blur of texture 1 on the main buffer ...
				gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, 0);

				// ... apply blur
				blurHProgram.useProgram(gl, true);
				try {
					gl.glUniform1i(gl.glGetUniformLocation(blurHProgram.program(), "texture"), 0);
					gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
					gl.glBindTexture(GL.GL_TEXTURE_2D, texture1Attachment.getName());
					gl.glUniform2f(gl.glGetUniformLocation(blurHProgram.program(), "resolution"), localBounds.width,
							localBounds.height);
					gl.glUniform1i(gl.glGetUniformLocation(blurHProgram.program(), "size"), shadowSize);
					if (!blurHProgram.validateProgram(gl, System.err)) {
						blurHInitialized = false;
					}

					gl.glBegin(GL2.GL_TRIANGLE_FAN);
					gl.glVertex2i(0, 0);
					gl.glVertex2i(0, localBounds.height);
					gl.glVertex2i(localBounds.width, localBounds.height);
					gl.glVertex2i(localBounds.width, 0);
					gl.glEnd();
				}
				finally {
					blurHProgram.useProgram(gl, false);
				}
			}
			finally {
				if (texture1Attachment != null) {
					texture1Attachment.free(gl);
				}
				if (texture0Attachment != null) {
					texture0Attachment.free(gl);
				}
				if (renderAttachment != null) {
					renderAttachment.free(gl);
				}
				if (fbObject != null) {
					fbObject.destroy(gl);
				}
			}
		}
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}
}
