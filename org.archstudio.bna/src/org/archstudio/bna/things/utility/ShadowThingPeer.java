package org.archstudio.bna.things.utility;

import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL2ES3;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.peers.IHasLocalBounds;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Program;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Shader;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.RenderAttachment;
import com.jogamp.opengl.FBObject.TextureAttachment;

public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> implements IHasLocalBounds {

	public ShadowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	int shadowSize;
	int shadowOffset;
	double shadowAlpha;

	private GL2ES2Shader blurVP;
	private GL2ES2Shader blurFP;
	private GL2ES2Program blurP;

	private void updateShadowData() {
		shadowSize = Math.min(SystemUtils.round(6 * cm.getLocalScale()), 5);
		shadowOffset = Math.min(SystemUtils.round(6 * cm.getLocalScale()), shadowSize);
		shadowAlpha = 0.4;
	}

	@Override
	public void dispose() {

		if (blurP != null) {
			blurP.dispose();
			blurP = null;
		}

		if (blurVP != null) {
			blurVP.dispose();
			blurVP = null;
		}

		if (blurFP != null) {
			blurFP.dispose();
			blurFP = null;
		}

		super.dispose();
	}

	@Override
	public Rectangle getLocalBounds() {
		Rectangle lbb = null;
		updateShadowData();

		for (IThing t : model.getAllThings()) {
			IThingPeer<?> tp = view.getThingPeer(t);
			if (tp instanceof IHasShadowPeer && t instanceof IHasBoundingBox) {
				Rectangle tLBB = cm.worldToLocal(((IHasBoundingBox) t).getBoundingBox());
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
	public void draw(GL2ES2 gl, Rectangle localBounds, IJOGLResources r) {
		
		// load blur shader
		if (blurVP == null) {
			blurVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, //
					ShadowThingPeer.class.getResource("glsl/blur.vp"));
		}
		if (blurFP == null) {
			blurFP = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, //
					ShadowThingPeer.class.getResource("glsl/blur.fp"));
		}
		if (blurP == null) {
			blurP = GL2ES2Program.create(gl, blurVP, blurFP);
			blurP.bindAttribute("attribute_position", 1);
			blurP.bindAttribute("attribute_texture_position", 1);
			blurP.link();
		}

		// note current FBO binding to restore later on
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
			// create vertices
			FloatBuffer vertices = Buffers.newDirectFloatBuffer(8);
			vertices.put(localBounds.x);
			vertices.put(localBounds.y);
			vertices.put(localBounds.x);
			vertices.put(localBounds.y + localBounds.height);
			vertices.put(localBounds.x + localBounds.width);
			vertices.put(localBounds.y + localBounds.height);
			vertices.put(localBounds.x + localBounds.width);
			vertices.put(localBounds.y);

			// create framebuffer
			fbObject = new FBObject();
			fbObject.reset(gl, localBounds.width, localBounds.height);
			fbObject.bind(gl);

			// create and bind renderbuffers
			fbObject.attachRenderbuffer(gl, GL.GL_DEPTH_COMPONENT16);
			renderAttachment = fbObject.getDepthAttachment();
			renderAttachment.initialize(gl);

			// create and bind texture 0
			texture0Attachment = fbObject.attachTexture2D(gl, 0, true);
			texture0Attachment.initialize(gl);

			// ... clear the background
			gl.glClearColor(0, 0, 0, 0);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

			// ... draw shadows
			r.pushMatrix(shadowOffset, shadowOffset, 0);
			try {
				Rectangle newLocalBounds = new Rectangle(localBounds.x - shadowOffset, localBounds.y - shadowOffset,
						localBounds.width, localBounds.height);
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

			// disable blending so that we simply blur the existing texture to the new one
			r.pushBlendFunction();
			gl.glDisable(GL.GL_BLEND);
			try {
				// ... apply blur
				blurP.use();
				try {
					gl.glUniformMatrix4fv(blurP.getUniform("uniform_projection"), 1, false, r.getMatrix()
							.glGetMatrixf());
					gl.glUniform1i(blurP.getUniform("uniform_texture"), 0);
					gl.glActiveTexture(GL.GL_TEXTURE0);
					gl.glBindTexture(GL.GL_TEXTURE_2D, texture0Attachment.getName());
					gl.glUniform2f(blurP.getUniform("uniform_resolution"), localBounds.width, localBounds.height);
					gl.glUniform1i(blurP.getUniform("uniform_size"), shadowSize);
					gl.glUniform2f(blurP.getUniform("uniform_direction"), 1, 0);
					gl.glUniform1f(blurP.getUniform("uniform_alpha"), 1);
					vertices.rewind();
					blurP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", 4, vertices, GL.GL_STATIC_DRAW, 2,
							false);

					gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 4);
				}
				finally {
					blurP.done();
				}
				gl.glFlush();
			}
			finally {
				// restore blending
				r.popBlendFunction();
			}

			// draw a blur of texture 1 on the main buffer ...
			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, currentFrameBufferBindings[0]);
			if (gl.hasFullFBOSupport()) {
				gl.glBindFramebuffer(GL2ES3.GL_DRAW_FRAMEBUFFER, currentFrameBufferBindings[1]);
				gl.glBindFramebuffer(GL2ES3.GL_READ_FRAMEBUFFER, currentFrameBufferBindings[2]);
			}

			// ... apply blur
			blurP.use();
			try {
				gl.glUniformMatrix4fv(blurP.getUniform("uniform_projection"), 1, false, r.getMatrix().glGetMatrixf());
				gl.glUniform1i(blurP.getUniform("uniform_texture"), 0);
				gl.glActiveTexture(GL.GL_TEXTURE0);
				gl.glBindTexture(GL.GL_TEXTURE_2D, texture0Attachment.getName());
				gl.glUniform2f(blurP.getUniform("uniform_resolution"), localBounds.width, localBounds.height);
				gl.glUniform1i(blurP.getUniform("uniform_size"), shadowSize);
				gl.glUniform2f(blurP.getUniform("uniform_direction"), 0, 1);
				gl.glUniform1f(blurP.getUniform("uniform_alpha"), (float) shadowAlpha);

				vertices.rewind();
				blurP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", 4, vertices, GL.GL_STATIC_DRAW, 2, false);

				gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 4);
			}
			finally {
				blurP.done();
			}
		}
		finally {
			if (texture1Attachment != null) {
				texture1Attachment.free(gl);
				texture1Attachment = null;
			}
			if (texture0Attachment != null) {
				texture0Attachment.free(gl);
				texture0Attachment = null;
			}
			if (renderAttachment != null) {
				renderAttachment.free(gl);
				renderAttachment = null;
			}
			if (fbObject != null) {
				fbObject.unbind(gl);
				fbObject.destroy(gl);
				fbObject = null;
			}

			gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, currentFrameBufferBindings[0]);
			if (gl.hasFullFBOSupport()) {
				gl.glBindFramebuffer(GL2ES3.GL_DRAW_FRAMEBUFFER, currentFrameBufferBindings[1]);
				gl.glBindFramebuffer(GL2ES3.GL_READ_FRAMEBUFFER, currentFrameBufferBindings[2]);
			}
		}
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, ISWTResources r) {
		int shadowSize = this.shadowSize + this.shadowOffset;
		int offsetStep = Math.max(1, (shadowSize + 2) / 5);
		double cumulativeAlpha = 0;

		for (int offset = shadowSize; offset > 0; offset -= offsetStep) {
			double alpha = (float) Math.exp((float) -offset * offset / shadowSize / shadowSize) * shadowAlpha;
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

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
