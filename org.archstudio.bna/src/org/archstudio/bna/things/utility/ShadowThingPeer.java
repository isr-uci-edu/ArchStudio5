package org.archstudio.bna.things.utility;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL2GL3;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.peers.IHasLocalBounds;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.RenderAttachment;
import com.jogamp.opengl.FBObject.TextureAttachment;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> implements IHasLocalBounds {

	private static class Blur {
		GL2ES2 gl;
		ShaderCode blurCode;
		ShaderProgram blurProgram;
		boolean blurInitialized;

		public Blur(GL currentGL) {
			gl = currentGL.getGL2ES2();
			blurCode = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, 1, ShadowThingPeer.class,
					new String[] { "glsl/blur.fs" }, false);
			if (blurCode != null) {
				blurProgram = new ShaderProgram();
				if (blurProgram.init(gl)) {
					if (blurProgram.add(gl, blurCode, System.err)) {
						if (blurProgram.link(gl, System.err)) {
							blurInitialized = blurProgram.program() != 0 && blurProgram.linked();
						}
					}
				}
			}
		}

		public void dispose() {
			if (gl != null) {
				blurInitialized = false;
				if (blurProgram != null) {
					blurProgram.release(gl);
					blurProgram = null;
				}
				if (blurCode != null) {
					blurCode.destroy(gl);
					blurCode = null;
				}
				gl = null;
			}
		}
	}

	protected static final LoadingCache<GL, Blur> blurs = CacheBuilder.newBuilder().maximumSize(16)
			.removalListener(new RemovalListener<GL, Blur>() {
				@Override
				public void onRemoval(RemovalNotification<GL, Blur> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<GL, Blur>() {
				@Override
				public Blur load(GL gl) throws Exception {
					return new Blur(gl);
				}
			});

	public ShadowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	int shadowSize;
	int shadowOffset;
	double shadowAlpha;

	private void updateShadowData() {
		shadowSize = Math.min(BNAUtils.round(6 * cm.getLocalScale()), 5);
		shadowOffset = Math.max(BNAUtils.round(6 * cm.getLocalScale()), shadowSize);
		shadowAlpha = 0.8;
	}

	@Override
	public Rectangle getLocalBounds() {
		Rectangle lbb = null;
		updateShadowData();

		for (IThing t : model.getAllThings()) {
			IThingPeer<?> tp = view.getThingPeer(t);
			if (tp instanceof IHasShadowPeer && t instanceof IHasBoundingBox) {
				Rectangle tLBB = BNAUtils.getLocalBoundingBox(cm, (IHasBoundingBox) t);
				tLBB.width += shadowOffset + shadowSize;
				tLBB.height += shadowOffset + shadowSize;
				if (lbb == null) {
					lbb = tLBB;
				}
				else {
					lbb = lbb.union(tLBB);
				}
			}
		}
		return lbb;
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return false;
		}

		if (!r.isDisplayShadows()) {
			return false;
		}

		updateShadowData();

		return true;
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, IUIResources r) {

		boolean transparent = true;

		// only render shadows if hardware accelerated
		if (!gl.getGLProfile().isHardwareRasterizer()) {
			return;
		}

		// initialize blur data
		Blur blur = blurs.getUnchecked(gl.getGL());

		if (blur.blurInitialized) {
			//gl = new TraceGL2(GLContext.getCurrentGL().getGL2(), System.err);

			int[] currentFrameBufferBindings = new int[3];
			gl.glGetIntegerv(GL.GL_FRAMEBUFFER_BINDING, currentFrameBufferBindings, 0);
			if (gl.hasFullFBOSupport()) {
				currentFrameBufferBindings[1] = gl.getDefaultDrawFramebuffer();
				currentFrameBufferBindings[2] = gl.getDefaultReadFramebuffer();
			}

			FBObject fbObject = null;
			RenderAttachment renderAttachment = null;
			TextureAttachment texture0Attachment = null;
			TextureAttachment texture1Attachment = null;
			try {
				// create framebuffer
				fbObject = new FBObject();
				fbObject.reset(gl, localBounds.width, localBounds.height);
				fbObject.bind(gl);

				// create and bind renderbuffers
				fbObject.attachRenderbuffer(gl, GL.GL_DEPTH_COMPONENT16);
				renderAttachment = fbObject.getDepthAttachment();
				renderAttachment.initialize(gl);

				// create and bind texture 0
				texture0Attachment = fbObject.attachTexture2D(gl, 0, transparent);
				texture0Attachment.initialize(gl);

				// draw shadows on texture 0 ...
				gl.glDrawBuffers(1, new int[] { GL.GL_COLOR_ATTACHMENT0 + 0 }, 0);

				// ... clear the background
				gl.glClearColor(0, 0, 0, 0);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

				// ... draw shadows
				r.pushMatrix(shadowOffset, shadowOffset, 0);
				try {
					Rectangle newLocalBounds = new Rectangle(localBounds.x - shadowOffset,
							localBounds.y - shadowOffset, localBounds.width, localBounds.height);
					for (IThing t : model.getAllThings()) {
						IThingPeer<?> tp = view.getThingPeer(t);
						if (tp instanceof IHasShadowPeer) {
							IHasShadowPeer<?> stp = (IHasShadowPeer<?>) tp;
							if (stp.drawShadow(newLocalBounds, r)) {
								stp.drawShadow(gl, newLocalBounds, r);
							}
						}
					}
				}
				finally {
					r.popMatrix();
				}

				// create and bind texture 1
				texture1Attachment = fbObject.attachTexture2D(gl, 1, transparent);
				texture1Attachment.initialize(gl);

				// draw a blur of texture 0 on texture 1 ...
				gl.glDrawBuffers(1, new int[] { GL.GL_COLOR_ATTACHMENT0 + 1 }, 0);

				// ... clear the background
				gl.glClearColor(0, 0, 0, 0);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

				// ... apply blur
				blur.blurProgram.useProgram(gl, true);
				try {
					gl.glUniform1i(gl.glGetUniformLocation(blur.blurProgram.program(), "texture"), 0);
					gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
					gl.glBindTexture(GL.GL_TEXTURE_2D, texture0Attachment.getName());
					gl.glUniform2f(gl.glGetUniformLocation(blur.blurProgram.program(), "resolution"),
							localBounds.width, localBounds.height);
					gl.glUniform1i(gl.glGetUniformLocation(blur.blurProgram.program(), "size"), shadowSize);
					gl.glUniform2f(gl.glGetUniformLocation(blur.blurProgram.program(), "direction"), 1, 0);
					gl.glUniform1f(gl.glGetUniformLocation(blur.blurProgram.program(), "alpha"), 1);
					if (!blur.blurProgram.validateProgram(gl, System.err)) {
						blur.blurInitialized = false;
					}

					// disable blending so that we simply blur the existing texture to the new one
					boolean isBlendEnabled = gl.glIsEnabled(GL.GL_BLEND);
					gl.glDisable(GL.GL_BLEND);

					gl.glBegin(GL2.GL_TRIANGLE_FAN);
					gl.glVertex2i(localBounds.x, localBounds.y);
					gl.glVertex2i(localBounds.x, localBounds.y + localBounds.height);
					gl.glVertex2i(localBounds.x + localBounds.width, localBounds.y + localBounds.height);
					gl.glVertex2i(localBounds.x + localBounds.width, localBounds.y);
					gl.glEnd();

					// restore blending
					if (isBlendEnabled) {
						gl.glEnable(GL.GL_BLEND);
					}
				}
				finally {
					blur.blurProgram.useProgram(gl, false);
				}

				// draw a blur of texture 1 on the main buffer ...
				if (gl.hasFullFBOSupport()) {
					gl.glBindFramebuffer(GL2GL3.GL_DRAW_FRAMEBUFFER, currentFrameBufferBindings[1]);
					gl.glBindFramebuffer(GL2GL3.GL_READ_FRAMEBUFFER, currentFrameBufferBindings[2]);
				}
				gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, currentFrameBufferBindings[0]);

				// ... apply blur
				blur.blurProgram.useProgram(gl, true);
				try {
					gl.glUniform1i(gl.glGetUniformLocation(blur.blurProgram.program(), "texture"), 0);
					gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
					gl.glBindTexture(GL.GL_TEXTURE_2D, texture1Attachment.getName());
					gl.glUniform2f(gl.glGetUniformLocation(blur.blurProgram.program(), "resolution"),
							localBounds.width, localBounds.height);
					gl.glUniform1i(gl.glGetUniformLocation(blur.blurProgram.program(), "size"), shadowSize);
					gl.glUniform2f(gl.glGetUniformLocation(blur.blurProgram.program(), "direction"), 0f, 1f);
					gl.glUniform1f(gl.glGetUniformLocation(blur.blurProgram.program(), "alpha"), (float) shadowAlpha);
					if (!blur.blurProgram.validateProgram(gl, System.err)) {
						blur.blurInitialized = false;
					}

					gl.glBegin(GL2.GL_TRIANGLE_FAN);
					gl.glVertex2i(localBounds.x, localBounds.y);
					gl.glVertex2i(localBounds.x, localBounds.y + localBounds.height);
					gl.glVertex2i(localBounds.x + localBounds.width, localBounds.y + localBounds.height);
					gl.glVertex2i(localBounds.x + localBounds.width, localBounds.y);
					gl.glEnd();
				}
				finally {
					blur.blurProgram.useProgram(gl, false);
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
					fbObject.unbind(gl);
					fbObject.destroy(gl);

					if (gl.hasFullFBOSupport()) {
						gl.glBindFramebuffer(GL2GL3.GL_DRAW_FRAMEBUFFER, currentFrameBufferBindings[1]);
						gl.glBindFramebuffer(GL2GL3.GL_READ_FRAMEBUFFER, currentFrameBufferBindings[2]);
					}
					gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, currentFrameBufferBindings[0]);
				}
			}
		}
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, IUIResources r) {
		int shadowSize = this.shadowSize + this.shadowOffset;
		int offsetStep = Math.max(1, (shadowSize + 2) / 5);
		double cumulativeAlpha = 1 - shadowAlpha;

		for (int offset = shadowSize; offset > 0; offset -= offsetStep) {
			double alpha = (float) Math.exp((float) -offset * offset / shadowSize / shadowSize);
			alpha = SystemUtils.bound(0, alpha * 2 - cumulativeAlpha, 1);
			cumulativeAlpha += alpha;
			int shadowOffset = shadowSize;
			int translateOffset = shadowSize - offset;
			r.pushMatrix(translateOffset, translateOffset, 0);
			r.pushAlpha(alpha);
			try {
				Rectangle newLocalBounds = new Rectangle(localBounds.x + shadowOffset, localBounds.y + shadowOffset,
						localBounds.width, localBounds.height);
				for (IThing t : model.getAllThings()) {
					IThingPeer<?> tp = view.getThingPeer(t);
					if (tp instanceof IHasShadowPeer) {
						IHasShadowPeer<?> stp = (IHasShadowPeer<?>) tp;
						if (stp.drawShadow(newLocalBounds, r)) {
							stp.drawShadow(gc, newLocalBounds, r);
						}
					}
				}
			}
			finally {
				r.popAlpha();
				r.popMatrix();
			}
		}
	}
}
