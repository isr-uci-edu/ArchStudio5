package org.archstudio.bna;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GL3;
import javax.media.opengl.GL3bc;
import javax.media.opengl.GL4;
import javax.media.opengl.GL4bc;
import javax.media.opengl.GLArrayData;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLES1;
import javax.media.opengl.GLES2;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLUniformData;

import org.eclipse.swt.graphics.RGB;

import com.jogamp.common.nio.PointerBuffer;

public class ObscuredGL2 implements GL2 {

	final GL2 gl2;
	double alpha = 1d;
	RGB tint = new RGB(0, 0, 0);

	public ObscuredGL2(GL2 gl2) {
		this.gl2 = gl2;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public RGB getTint() {
		return tint;
	}

	public void setTint(RGB tint) {
		this.tint = tint;
	}

	protected double c(double v, double f) {
		return v * (1d - f) + f;
	}

	protected double r(double v) {
		return c(v, tint.red / 255d);
	}

	protected double g(double v) {
		return c(v, tint.green / 255d);
	}

	protected double b(double v) {
		return c(v, tint.blue / 255d);
	}

	protected double a(double v) {
		return v * alpha;
	}

	/////////////////////////////////////////////////////////////////

	@Override
	public GLContext getContext() {
		return gl2.getContext();
	}

	@Override
	public Object getExtension(String arg0) {
		return gl2.getExtension(arg0);
	}

	@Override
	public GL getGL() throws GLException {
		return gl2.getGL();
	}

	@Override
	public GL2 getGL2() throws GLException {
		return gl2.getGL2();
	}

	@Override
	public GL2ES1 getGL2ES1() throws GLException {
		return gl2.getGL2ES1();
	}

	@Override
	public GL2ES2 getGL2ES2() throws GLException {
		return gl2.getGL2ES2();
	}

	@Override
	public GL2GL3 getGL2GL3() throws GLException {
		return gl2.getGL2GL3();
	}

	@Override
	public GL3 getGL3() throws GLException {
		return gl2.getGL3();
	}

	@Override
	public GL3bc getGL3bc() throws GLException {
		return gl2.getGL3bc();
	}

	@Override
	public GL4 getGL4() throws GLException {
		return gl2.getGL4();
	}

	@Override
	public GL4bc getGL4bc() throws GLException {
		return gl2.getGL4bc();
	}

	@Override
	public GLES1 getGLES1() throws GLException {
		return gl2.getGLES1();
	}

	@Override
	public GLES2 getGLES2() throws GLException {
		return gl2.getGLES2();
	}

	@Override
	public GLProfile getGLProfile() {
		return gl2.getGLProfile();
	}

	@Override
	public Object getPlatformGLExtensions() {
		return gl2.getPlatformGLExtensions();
	}

	@Override
	public int getSwapInterval() {
		return gl2.getSwapInterval();
	}

	@Override
	public void glAccum(int arg0, float arg1) {
		gl2.glAccum(arg0, arg1);
	}

	@Override
	public void glActiveShaderProgram(int arg0, int arg1) {
		gl2.glActiveShaderProgram(arg0, arg1);
	}

	@Override
	public void glActiveStencilFaceEXT(int arg0) {
		gl2.glActiveStencilFaceEXT(arg0);
	}

	@Override
	public void glActiveTexture(int arg0) {
		gl2.glActiveTexture(arg0);
	}

	@Override
	public ByteBuffer glAllocateMemoryNV(int arg0, float arg1, float arg2, float arg3) {
		return gl2.glAllocateMemoryNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glAlphaFunc(int arg0, float arg1) {
		gl2.glAlphaFunc(arg0, arg1);
	}

	@Override
	public void glApplyTextureEXT(int arg0) {
		gl2.glApplyTextureEXT(arg0);
	}

	@Override
	public boolean glAreTexturesResident(int arg0, int[] arg1, int arg2, byte[] arg3, int arg4) {
		return gl2.glAreTexturesResident(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public boolean glAreTexturesResident(int arg0, IntBuffer arg1, ByteBuffer arg2) {
		return gl2.glAreTexturesResident(arg0, arg1, arg2);
	}

	@Override
	public void glArrayElement(int arg0) {
		gl2.glArrayElement(arg0);
	}

	@Override
	public void glAttachObjectARB(int arg0, int arg1) {
		gl2.glAttachObjectARB(arg0, arg1);
	}

	@Override
	public void glAttachShader(int arg0, int arg1) {
		gl2.glAttachShader(arg0, arg1);
	}

	@Override
	public void glBegin(int arg0) {
		gl2.glBegin(arg0);
	}

	@Override
	public void glBeginConditionalRender(int arg0, int arg1) {
		gl2.glBeginConditionalRender(arg0, arg1);
	}

	@Override
	public void glBeginOcclusionQueryNV(int arg0) {
		gl2.glBeginOcclusionQueryNV(arg0);
	}

	@Override
	public void glBeginPerfMonitorAMD(int arg0) {
		gl2.glBeginPerfMonitorAMD(arg0);
	}

	@Override
	public void glBeginQuery(int arg0, int arg1) {
		gl2.glBeginQuery(arg0, arg1);
	}

	@Override
	public void glBeginQueryIndexed(int arg0, int arg1, int arg2) {
		gl2.glBeginQueryIndexed(arg0, arg1, arg2);
	}

	@Override
	public void glBeginTransformFeedback(int arg0) {
		gl2.glBeginTransformFeedback(arg0);
	}

	@Override
	public void glBeginVertexShaderEXT() {
		gl2.glBeginVertexShaderEXT();
	}

	@Override
	public void glBeginVideoCaptureNV(int arg0) {
		gl2.glBeginVideoCaptureNV(arg0);
	}

	@Override
	public void glBindAttribLocation(int arg0, int arg1, String arg2) {
		gl2.glBindAttribLocation(arg0, arg1, arg2);
	}

	@Override
	public void glBindBuffer(int arg0, int arg1) {
		gl2.glBindBuffer(arg0, arg1);
	}

	@Override
	public void glBindBufferBase(int arg0, int arg1, int arg2) {
		gl2.glBindBufferBase(arg0, arg1, arg2);
	}

	@Override
	public void glBindBufferOffset(int arg0, int arg1, int arg2, long arg3) {
		gl2.glBindBufferOffset(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBindBufferRange(int arg0, int arg1, int arg2, long arg3, long arg4) {
		gl2.glBindBufferRange(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBindFragDataLocation(int arg0, int arg1, String arg2) {
		gl2.glBindFragDataLocation(arg0, arg1, arg2);
	}

	@Override
	public void glBindFragDataLocationIndexed(int arg0, int arg1, int arg2, String arg3) {
		gl2.glBindFragDataLocationIndexed(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBindFramebuffer(int arg0, int arg1) {
		gl2.glBindFramebuffer(arg0, arg1);
	}

	@Override
	public void glBindImageTexture(int arg0, int arg1, int arg2, boolean arg3, int arg4, int arg5, int arg6) {
		gl2.glBindImageTexture(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public int glBindLightParameterEXT(int arg0, int arg1) {
		return gl2.glBindLightParameterEXT(arg0, arg1);
	}

	@Override
	public int glBindMaterialParameterEXT(int arg0, int arg1) {
		return gl2.glBindMaterialParameterEXT(arg0, arg1);
	}

	@Override
	public void glBindMultiTextureEXT(int arg0, int arg1, int arg2) {
		gl2.glBindMultiTextureEXT(arg0, arg1, arg2);
	}

	@Override
	public int glBindParameterEXT(int arg0) {
		return gl2.glBindParameterEXT(arg0);
	}

	@Override
	public void glBindProgramARB(int arg0, int arg1) {
		gl2.glBindProgramARB(arg0, arg1);
	}

	@Override
	public void glBindProgramPipeline(int arg0) {
		gl2.glBindProgramPipeline(arg0);
	}

	@Override
	public void glBindRenderbuffer(int arg0, int arg1) {
		gl2.glBindRenderbuffer(arg0, arg1);
	}

	@Override
	public void glBindSampler(int arg0, int arg1) {
		gl2.glBindSampler(arg0, arg1);
	}

	@Override
	public int glBindTexGenParameterEXT(int arg0, int arg1, int arg2) {
		return gl2.glBindTexGenParameterEXT(arg0, arg1, arg2);
	}

	@Override
	public void glBindTexture(int arg0, int arg1) {
		gl2.glBindTexture(arg0, arg1);
	}

	@Override
	public int glBindTextureUnitParameterEXT(int arg0, int arg1) {
		return gl2.glBindTextureUnitParameterEXT(arg0, arg1);
	}

	@Override
	public void glBindTransformFeedback(int arg0, int arg1) {
		gl2.glBindTransformFeedback(arg0, arg1);
	}

	@Override
	public void glBindTransformFeedbackNV(int arg0, int arg1) {
		gl2.glBindTransformFeedbackNV(arg0, arg1);
	}

	@Override
	public void glBindVertexArray(int arg0) {
		gl2.glBindVertexArray(arg0);
	}

	@Override
	public void glBindVertexShaderEXT(int arg0) {
		gl2.glBindVertexShaderEXT(arg0);
	}

	@Override
	public void glBindVideoCaptureStreamBufferNV(int arg0, int arg1, int arg2, long arg3) {
		gl2.glBindVideoCaptureStreamBufferNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBindVideoCaptureStreamTextureNV(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glBindVideoCaptureStreamTextureNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, byte[] arg6, int arg7) {
		gl2.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, ByteBuffer arg6) {
		gl2.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, long arg6) {
		gl2.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glBlendColor(float arg0, float arg1, float arg2, float arg3) {
		gl2.glBlendColor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendEquation(int arg0) {
		gl2.glBlendEquation(arg0);
	}

	@Override
	public void glBlendEquationIndexedAMD(int arg0, int arg1) {
		gl2.glBlendEquationIndexedAMD(arg0, arg1);
	}

	@Override
	public void glBlendEquationSeparate(int arg0, int arg1) {
		gl2.glBlendEquationSeparate(arg0, arg1);
	}

	@Override
	public void glBlendEquationSeparateIndexedAMD(int arg0, int arg1, int arg2) {
		gl2.glBlendEquationSeparateIndexedAMD(arg0, arg1, arg2);
	}

	@Override
	public void glBlendEquationSeparatei(int arg0, int arg1, int arg2) {
		gl2.glBlendEquationSeparatei(arg0, arg1, arg2);
	}

	@Override
	public void glBlendEquationi(int arg0, int arg1) {
		gl2.glBlendEquationi(arg0, arg1);
	}

	@Override
	public void glBlendFunc(int arg0, int arg1) {
		gl2.glBlendFunc(arg0, arg1);
	}

	@Override
	public void glBlendFuncIndexedAMD(int arg0, int arg1, int arg2) {
		gl2.glBlendFuncIndexedAMD(arg0, arg1, arg2);
	}

	@Override
	public void glBlendFuncSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl2.glBlendFuncSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendFuncSeparateINGR(int arg0, int arg1, int arg2, int arg3) {
		gl2.glBlendFuncSeparateINGR(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendFuncSeparateIndexedAMD(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glBlendFuncSeparateIndexedAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBlendFuncSeparatei(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glBlendFuncSeparatei(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBlendFunci(int arg0, int arg1, int arg2) {
		gl2.glBlendFunci(arg0, arg1, arg2);
	}

	@Override
	public void glBlitFramebuffer(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9) {
		gl2.glBlitFramebuffer(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glBufferAddressRangeNV(int arg0, int arg1, long arg2, long arg3) {
		gl2.glBufferAddressRangeNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBufferData(int arg0, long arg1, Buffer arg2, int arg3) {
		gl2.glBufferData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBufferParameteri(int arg0, int arg1, int arg2) {
		gl2.glBufferParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glBufferSubData(int arg0, long arg1, long arg2, Buffer arg3) {
		gl2.glBufferSubData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glCallList(int arg0) {
		gl2.glCallList(arg0);
	}

	@Override
	public void glCallLists(int arg0, int arg1, Buffer arg2) {
		gl2.glCallLists(arg0, arg1, arg2);
	}

	@Override
	public int glCheckFramebufferStatus(int arg0) {
		return gl2.glCheckFramebufferStatus(arg0);
	}

	@Override
	public int glCheckNamedFramebufferStatusEXT(int arg0, int arg1) {
		return gl2.glCheckNamedFramebufferStatusEXT(arg0, arg1);
	}

	@Override
	public void glClampColor(int arg0, int arg1) {
		gl2.glClampColor(arg0, arg1);
	}

	@Override
	public void glClear(int arg0) {
		gl2.glClear(arg0);
	}

	@Override
	public void glClearAccum(float arg0, float arg1, float arg2, float arg3) {
		gl2.glClearAccum(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfi(int arg0, int arg1, float arg2, int arg3) {
		gl2.glClearBufferfi(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glClearBufferfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glClearBufferfv(arg0, arg1, arg2);
	}

	@Override
	public void glClearBufferiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glClearBufferiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glClearBufferiv(arg0, arg1, arg2);
	}

	@Override
	public void glClearBufferuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glClearBufferuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glClearBufferuiv(arg0, arg1, arg2);
	}

	@Override
	public void glClearColor(float arg0, float arg1, float arg2, float arg3) {
		gl2.glClearColor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearColorIi(int arg0, int arg1, int arg2, int arg3) {
		gl2.glClearColorIi(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearColorIui(int arg0, int arg1, int arg2, int arg3) {
		gl2.glClearColorIui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearDepth(double arg0) {
		gl2.glClearDepth(arg0);
	}

	@Override
	public void glClearDepthf(float arg0) {
		gl2.glClearDepthf(arg0);
	}

	@Override
	public void glClearIndex(float arg0) {
		gl2.glClearIndex(arg0);
	}

	@Override
	public void glClearStencil(int arg0) {
		gl2.glClearStencil(arg0);
	}

	@Override
	public void glClientActiveTexture(int arg0) {
		gl2.glClientActiveTexture(arg0);
	}

	@Override
	public void glClientAttribDefaultEXT(int arg0) {
		gl2.glClientAttribDefaultEXT(arg0);
	}

	@Override
	public void glClipPlane(int arg0, double[] arg1, int arg2) {
		gl2.glClipPlane(arg0, arg1, arg2);
	}

	@Override
	public void glClipPlane(int arg0, DoubleBuffer arg1) {
		gl2.glClipPlane(arg0, arg1);
	}

	@Override
	public void glColor3b(byte arg0, byte arg1, byte arg2) {
		gl2.glColor3b(arg0, arg1, arg2);
	}

	@Override
	public void glColor3bv(byte[] arg0, int arg1) {
		gl2.glColor3bv(arg0, arg1);
	}

	@Override
	public void glColor3bv(ByteBuffer arg0) {
		gl2.glColor3bv(arg0);
	}

	@Override
	public void glColor3d(double arg0, double arg1, double arg2) {
		gl2.glColor4d(r(arg0), g(arg1), b(arg2), alpha);
	}

	@Override
	public void glColor3dv(double[] arg0, int arg1) {
		gl2.glColor3dv(arg0, arg1);
	}

	@Override
	public void glColor3dv(DoubleBuffer arg0) {
		gl2.glColor3dv(arg0);
	}

	@Override
	public void glColor3f(float arg0, float arg1, float arg2) {
		gl2.glColor4d(r(arg0), g(arg1), b(arg2), alpha);
	}

	@Override
	public void glColor3fv(float[] arg0, int arg1) {
		gl2.glColor3fv(arg0, arg1);
	}

	@Override
	public void glColor3fv(FloatBuffer arg0) {
		gl2.glColor3fv(arg0);
	}

	@Override
	public void glColor3h(short arg0, short arg1, short arg2) {
		gl2.glColor3h(arg0, arg1, arg2);
	}

	@Override
	public void glColor3hv(short[] arg0, int arg1) {
		gl2.glColor3hv(arg0, arg1);
	}

	@Override
	public void glColor3hv(ShortBuffer arg0) {
		gl2.glColor3hv(arg0);
	}

	@Override
	public void glColor3i(int arg0, int arg1, int arg2) {
		gl2.glColor3i(arg0, arg1, arg2);
	}

	@Override
	public void glColor3iv(int[] arg0, int arg1) {
		gl2.glColor3iv(arg0, arg1);
	}

	@Override
	public void glColor3iv(IntBuffer arg0) {
		gl2.glColor3iv(arg0);
	}

	@Override
	public void glColor3s(short arg0, short arg1, short arg2) {
		gl2.glColor3s(arg0, arg1, arg2);
	}

	@Override
	public void glColor3sv(short[] arg0, int arg1) {
		gl2.glColor3sv(arg0, arg1);
	}

	@Override
	public void glColor3sv(ShortBuffer arg0) {
		gl2.glColor3sv(arg0);
	}

	@Override
	public void glColor3ub(byte arg0, byte arg1, byte arg2) {
		gl2.glColor3ub(arg0, arg1, arg2);
	}

	@Override
	public void glColor3ubv(byte[] arg0, int arg1) {
		gl2.glColor3ubv(arg0, arg1);
	}

	@Override
	public void glColor3ubv(ByteBuffer arg0) {
		gl2.glColor3ubv(arg0);
	}

	@Override
	public void glColor3ui(int arg0, int arg1, int arg2) {
		gl2.glColor3ui(arg0, arg1, arg2);
	}

	@Override
	public void glColor3uiv(int[] arg0, int arg1) {
		gl2.glColor3uiv(arg0, arg1);
	}

	@Override
	public void glColor3uiv(IntBuffer arg0) {
		gl2.glColor3uiv(arg0);
	}

	@Override
	public void glColor3us(short arg0, short arg1, short arg2) {
		gl2.glColor3us(arg0, arg1, arg2);
	}

	@Override
	public void glColor3usv(short[] arg0, int arg1) {
		gl2.glColor3usv(arg0, arg1);
	}

	@Override
	public void glColor3usv(ShortBuffer arg0) {
		gl2.glColor3usv(arg0);
	}

	@Override
	public void glColor4b(byte arg0, byte arg1, byte arg2, byte arg3) {
		gl2.glColor4b(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4bv(byte[] arg0, int arg1) {
		gl2.glColor4bv(arg0, arg1);
	}

	@Override
	public void glColor4bv(ByteBuffer arg0) {
		gl2.glColor4bv(arg0);
	}

	@Override
	public void glColor4d(double arg0, double arg1, double arg2, double arg3) {
		gl2.glColor4d(r(arg0), g(arg1), b(arg2), a(arg3));
	}

	@Override
	public void glColor4dv(double[] arg0, int arg1) {
		gl2.glColor4dv(arg0, arg1);
	}

	@Override
	public void glColor4dv(DoubleBuffer arg0) {
		gl2.glColor4dv(arg0);
	}

	@Override
	public void glColor4f(float arg0, float arg1, float arg2, float arg3) {
		gl2.glColor4d(r(arg0), g(arg1), b(arg2), a(arg3));
	}

	@Override
	public void glColor4fv(float[] arg0, int arg1) {
		gl2.glColor4fv(arg0, arg1);
	}

	@Override
	public void glColor4fv(FloatBuffer arg0) {
		gl2.glColor4fv(arg0);
	}

	@Override
	public void glColor4h(short arg0, short arg1, short arg2, short arg3) {
		gl2.glColor4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4hv(short[] arg0, int arg1) {
		gl2.glColor4hv(arg0, arg1);
	}

	@Override
	public void glColor4hv(ShortBuffer arg0) {
		gl2.glColor4hv(arg0);
	}

	@Override
	public void glColor4i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glColor4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4iv(int[] arg0, int arg1) {
		gl2.glColor4iv(arg0, arg1);
	}

	@Override
	public void glColor4iv(IntBuffer arg0) {
		gl2.glColor4iv(arg0);
	}

	@Override
	public void glColor4s(short arg0, short arg1, short arg2, short arg3) {
		gl2.glColor4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4sv(short[] arg0, int arg1) {
		gl2.glColor4sv(arg0, arg1);
	}

	@Override
	public void glColor4sv(ShortBuffer arg0) {
		gl2.glColor4sv(arg0);
	}

	@Override
	public void glColor4ub(byte arg0, byte arg1, byte arg2, byte arg3) {
		gl2.glColor4ub(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4ubv(byte[] arg0, int arg1) {
		gl2.glColor4ubv(arg0, arg1);
	}

	@Override
	public void glColor4ubv(ByteBuffer arg0) {
		gl2.glColor4ubv(arg0);
	}

	@Override
	public void glColor4ui(int arg0, int arg1, int arg2, int arg3) {
		gl2.glColor4ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4uiv(int[] arg0, int arg1) {
		gl2.glColor4uiv(arg0, arg1);
	}

	@Override
	public void glColor4uiv(IntBuffer arg0) {
		gl2.glColor4uiv(arg0);
	}

	@Override
	public void glColor4us(short arg0, short arg1, short arg2, short arg3) {
		gl2.glColor4us(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4usv(short[] arg0, int arg1) {
		gl2.glColor4usv(arg0, arg1);
	}

	@Override
	public void glColor4usv(ShortBuffer arg0) {
		gl2.glColor4usv(arg0);
	}

	@Override
	public void glColorFormatNV(int arg0, int arg1, int arg2) {
		gl2.glColorFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glColorMask(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {
		gl2.glColorMask(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorMaskIndexed(int arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
		gl2.glColorMaskIndexed(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glColorMaski(int arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
		gl2.glColorMaski(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glColorMaterial(int arg0, int arg1) {
		gl2.glColorMaterial(arg0, arg1);
	}

	@Override
	public void glColorP3ui(int arg0, int arg1) {
		gl2.glColorP3ui(arg0, arg1);
	}

	@Override
	public void glColorP3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glColorP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glColorP3uiv(int arg0, IntBuffer arg1) {
		gl2.glColorP3uiv(arg0, arg1);
	}

	@Override
	public void glColorP4ui(int arg0, int arg1) {
		gl2.glColorP4ui(arg0, arg1);
	}

	@Override
	public void glColorP4uiv(int arg0, int[] arg1, int arg2) {
		gl2.glColorP4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glColorP4uiv(int arg0, IntBuffer arg1) {
		gl2.glColorP4uiv(arg0, arg1);
	}

	@Override
	public void glColorPointer(GLArrayData arg0) {
		gl2.glColorPointer(arg0);
	}

	@Override
	public void glColorPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorPointer(int arg0, int arg1, int arg2, long arg3) {
		gl2.glColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glColorSubTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl2.glColorSubTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTable(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glColorTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTable(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl2.glColorTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTableParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glColorTableParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorTableParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glColorTableParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glColorTableParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glColorTableParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorTableParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glColorTableParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glCompileShader(int arg0) {
		gl2.glCompileShader(arg0);
	}

	@Override
	public void glCompileShaderARB(int arg0) {
		gl2.glCompileShaderARB(arg0);
	}

	@Override
	public void glCompileShaderIncludeARB(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl2.glCompileShaderIncludeARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCompileShaderIncludeARB(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl2.glCompileShaderIncludeARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glCompressedMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl2.glCompressedMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl2.glCompressedMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedMultiTexImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl2.glCompressedMultiTexImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl2.glCompressedMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl2.glCompressedMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, int arg10, Buffer arg11) {
		gl2.glCompressedMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glCompressedTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl2.glCompressedTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl2.glCompressedTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl2.glCompressedTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, long arg7) {
		gl2.glCompressedTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl2.glCompressedTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			long arg8) {
		gl2.glCompressedTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl2.glCompressedTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl2.glCompressedTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl2.glCompressedTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, long arg8) {
		gl2.glCompressedTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, Buffer arg10) {
		gl2.glCompressedTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glCompressedTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, long arg10) {
		gl2.glCompressedTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glCompressedTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl2.glCompressedTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl2.glCompressedTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTextureImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl2.glCompressedTextureImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl2.glCompressedTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl2.glCompressedTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, int arg10, Buffer arg11) {
		gl2.glCompressedTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl2.glConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl2.glConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl2.glConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glConvolutionParameterf(int arg0, int arg1, float arg2) {
		gl2.glConvolutionParameterf(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glConvolutionParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glConvolutionParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glConvolutionParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameteri(int arg0, int arg1, int arg2) {
		gl2.glConvolutionParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glConvolutionParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glConvolutionParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glConvolutionParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glCopyBufferSubData(int arg0, int arg1, long arg2, long arg3, long arg4) {
		gl2.glCopyBufferSubData(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glCopyColorSubTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyColorTable(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glCopyColorTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glCopyConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glCopyConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glCopyImageSubDataNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
		gl2.glCopyImageSubDataNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12,
				arg13, arg14);
	}

	@Override
	public void glCopyMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl2.glCopyMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl2.glCopyMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glCopyMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl2.glCopyMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9) {
		gl2.glCopyMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCopyPixels(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glCopyPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glCopyTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl2.glCopyTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glCopyTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glCopyTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl2.glCopyTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8) {
		gl2.glCopyTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl2.glCopyTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8) {
		gl2.glCopyTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glCopyTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl2.glCopyTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9) {
		gl2.glCopyTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public int glCreateProgram() {
		return gl2.glCreateProgram();
	}

	@Override
	public int glCreateProgramObjectARB() {
		return gl2.glCreateProgramObjectARB();
	}

	@Override
	public int glCreateShader(int arg0) {
		return gl2.glCreateShader(arg0);
	}

	@Override
	public int glCreateShaderObjectARB(int arg0) {
		return gl2.glCreateShaderObjectARB(arg0);
	}

	@Override
	public int glCreateShaderProgramv(int arg0, int arg1, PointerBuffer arg2) {
		return gl2.glCreateShaderProgramv(arg0, arg1, arg2);
	}

	@Override
	public long glCreateSyncFromCLeventARB(Buffer arg0, Buffer arg1, int arg2) {
		return gl2.glCreateSyncFromCLeventARB(arg0, arg1, arg2);
	}

	@Override
	public void glCullFace(int arg0) {
		gl2.glCullFace(arg0);
	}

	@Override
	public void glCullParameterdvEXT(int arg0, double[] arg1, int arg2) {
		gl2.glCullParameterdvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glCullParameterdvEXT(int arg0, DoubleBuffer arg1) {
		gl2.glCullParameterdvEXT(arg0, arg1);
	}

	@Override
	public void glCullParameterfvEXT(int arg0, float[] arg1, int arg2) {
		gl2.glCullParameterfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glCullParameterfvEXT(int arg0, FloatBuffer arg1) {
		gl2.glCullParameterfvEXT(arg0, arg1);
	}

	@Override
	public void glCurrentPaletteMatrix(int arg0) {
		gl2.glCurrentPaletteMatrix(arg0);
	}

	@Override
	public void glDebugMessageControlARB(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5, boolean arg6) {
		gl2.glDebugMessageControlARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glDebugMessageControlARB(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4, boolean arg5) {
		gl2.glDebugMessageControlARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDebugMessageEnableAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, boolean arg5) {
		gl2.glDebugMessageEnableAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDebugMessageEnableAMD(int arg0, int arg1, int arg2, IntBuffer arg3, boolean arg4) {
		gl2.glDebugMessageEnableAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDebugMessageInsertAMD(int arg0, int arg1, int arg2, int arg3, String arg4) {
		gl2.glDebugMessageInsertAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDebugMessageInsertARB(int arg0, int arg1, int arg2, int arg3, int arg4, String arg5) {
		gl2.glDebugMessageInsertARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDeleteBuffers(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteBuffers(int arg0, IntBuffer arg1) {
		gl2.glDeleteBuffers(arg0, arg1);
	}

	@Override
	public void glDeleteFencesAPPLE(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteFencesAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFencesAPPLE(int arg0, IntBuffer arg1) {
		gl2.glDeleteFencesAPPLE(arg0, arg1);
	}

	@Override
	public void glDeleteFencesNV(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteFencesNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFencesNV(int arg0, IntBuffer arg1) {
		gl2.glDeleteFencesNV(arg0, arg1);
	}

	@Override
	public void glDeleteFramebuffers(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteFramebuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFramebuffers(int arg0, IntBuffer arg1) {
		gl2.glDeleteFramebuffers(arg0, arg1);
	}

	@Override
	public void glDeleteLists(int arg0, int arg1) {
		gl2.glDeleteLists(arg0, arg1);
	}

	@Override
	public void glDeleteNamedStringARB(int arg0, String arg1) {
		gl2.glDeleteNamedStringARB(arg0, arg1);
	}

	@Override
	public void glDeleteNamesAMD(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glDeleteNamesAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDeleteNamesAMD(int arg0, int arg1, IntBuffer arg2) {
		gl2.glDeleteNamesAMD(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteObjectARB(int arg0) {
		gl2.glDeleteObjectARB(arg0);
	}

	@Override
	public void glDeleteOcclusionQueriesNV(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteOcclusionQueriesNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteOcclusionQueriesNV(int arg0, IntBuffer arg1) {
		gl2.glDeleteOcclusionQueriesNV(arg0, arg1);
	}

	@Override
	public void glDeletePerfMonitorsAMD(int arg0, int[] arg1, int arg2) {
		gl2.glDeletePerfMonitorsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glDeletePerfMonitorsAMD(int arg0, IntBuffer arg1) {
		gl2.glDeletePerfMonitorsAMD(arg0, arg1);
	}

	@Override
	public void glDeleteProgram(int arg0) {
		gl2.glDeleteProgram(arg0);
	}

	@Override
	public void glDeleteProgramPipelines(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteProgramPipelines(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteProgramPipelines(int arg0, IntBuffer arg1) {
		gl2.glDeleteProgramPipelines(arg0, arg1);
	}

	@Override
	public void glDeleteProgramsARB(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteProgramsARB(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteProgramsARB(int arg0, IntBuffer arg1) {
		gl2.glDeleteProgramsARB(arg0, arg1);
	}

	@Override
	public void glDeleteQueries(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteQueries(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteQueries(int arg0, IntBuffer arg1) {
		gl2.glDeleteQueries(arg0, arg1);
	}

	@Override
	public void glDeleteRenderbuffers(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteRenderbuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteRenderbuffers(int arg0, IntBuffer arg1) {
		gl2.glDeleteRenderbuffers(arg0, arg1);
	}

	@Override
	public void glDeleteSamplers(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteSamplers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteSamplers(int arg0, IntBuffer arg1) {
		gl2.glDeleteSamplers(arg0, arg1);
	}

	@Override
	public void glDeleteShader(int arg0) {
		gl2.glDeleteShader(arg0);
	}

	@Override
	public void glDeleteTextures(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteTextures(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteTextures(int arg0, IntBuffer arg1) {
		gl2.glDeleteTextures(arg0, arg1);
	}

	@Override
	public void glDeleteTransformFeedbacks(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteTransformFeedbacks(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteTransformFeedbacks(int arg0, IntBuffer arg1) {
		gl2.glDeleteTransformFeedbacks(arg0, arg1);
	}

	@Override
	public void glDeleteTransformFeedbacksNV(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteTransformFeedbacksNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteTransformFeedbacksNV(int arg0, IntBuffer arg1) {
		gl2.glDeleteTransformFeedbacksNV(arg0, arg1);
	}

	@Override
	public void glDeleteVertexArrays(int arg0, int[] arg1, int arg2) {
		gl2.glDeleteVertexArrays(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteVertexArrays(int arg0, IntBuffer arg1) {
		gl2.glDeleteVertexArrays(arg0, arg1);
	}

	@Override
	public void glDeleteVertexShaderEXT(int arg0) {
		gl2.glDeleteVertexShaderEXT(arg0);
	}

	@Override
	public void glDepthBoundsEXT(double arg0, double arg1) {
		gl2.glDepthBoundsEXT(arg0, arg1);
	}

	@Override
	public void glDepthFunc(int arg0) {
		gl2.glDepthFunc(arg0);
	}

	@Override
	public void glDepthMask(boolean arg0) {
		gl2.glDepthMask(arg0);
	}

	@Override
	public void glDepthRange(double arg0, double arg1) {
		gl2.glDepthRange(arg0, arg1);
	}

	@Override
	public void glDepthRangeArrayv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glDepthRangeArrayv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDepthRangeArrayv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glDepthRangeArrayv(arg0, arg1, arg2);
	}

	@Override
	public void glDepthRangeIndexed(int arg0, double arg1, double arg2) {
		gl2.glDepthRangeIndexed(arg0, arg1, arg2);
	}

	@Override
	public void glDepthRangef(float arg0, float arg1) {
		gl2.glDepthRangef(arg0, arg1);
	}

	@Override
	public void glDetachObjectARB(int arg0, int arg1) {
		gl2.glDetachObjectARB(arg0, arg1);
	}

	@Override
	public void glDetachShader(int arg0, int arg1) {
		gl2.glDetachShader(arg0, arg1);
	}

	@Override
	public void glDisable(int arg0) {
		gl2.glDisable(arg0);
	}

	@Override
	public void glDisableClientState(int arg0) {
		gl2.glDisableClientState(arg0);
	}

	@Override
	public void glDisableClientStateIndexedEXT(int arg0, int arg1) {
		gl2.glDisableClientStateIndexedEXT(arg0, arg1);
	}

	@Override
	public void glDisableIndexed(int arg0, int arg1) {
		gl2.glDisableIndexed(arg0, arg1);
	}

	@Override
	public void glDisableVariantClientStateEXT(int arg0) {
		gl2.glDisableVariantClientStateEXT(arg0);
	}

	@Override
	public void glDisableVertexAttribAPPLE(int arg0, int arg1) {
		gl2.glDisableVertexAttribAPPLE(arg0, arg1);
	}

	@Override
	public void glDisableVertexAttribArray(int arg0) {
		gl2.glDisableVertexAttribArray(arg0);
	}

	@Override
	public void glDisableVertexAttribArrayARB(int arg0) {
		gl2.glDisableVertexAttribArrayARB(arg0);
	}

	@Override
	public void glDisablei(int arg0, int arg1) {
		gl2.glDisablei(arg0, arg1);
	}

	@Override
	public void glDrawArrays(int arg0, int arg1, int arg2) {
		gl2.glDrawArrays(arg0, arg1, arg2);
	}

	@Override
	public void glDrawArraysInstanced(int arg0, int arg1, int arg2, int arg3) {
		gl2.glDrawArraysInstanced(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawArraysInstancedBaseInstance(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glDrawArraysInstancedBaseInstance(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawBuffer(int arg0) {
		gl2.glDrawBuffer(arg0);
	}

	@Override
	public void glDrawBuffers(int arg0, int[] arg1, int arg2) {
		gl2.glDrawBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDrawBuffers(int arg0, IntBuffer arg1) {
		gl2.glDrawBuffers(arg0, arg1);
	}

	@Override
	public void glDrawBuffersATI(int arg0, int[] arg1, int arg2) {
		gl2.glDrawBuffersATI(arg0, arg1, arg2);
	}

	@Override
	public void glDrawBuffersATI(int arg0, IntBuffer arg1) {
		gl2.glDrawBuffersATI(arg0, arg1);
	}

	@Override
	public void glDrawElements(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glDrawElements(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawElements(int arg0, int arg1, int arg2, long arg3) {
		gl2.glDrawElements(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawElementsBaseVertex(int arg0, int arg1, int arg2, Buffer arg3, int arg4) {
		gl2.glDrawElementsBaseVertex(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawElementsInstanced(int arg0, int arg1, int arg2, Buffer arg3, int arg4) {
		gl2.glDrawElementsInstanced(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawElementsInstancedBaseInstance(int arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5) {
		gl2.glDrawElementsInstancedBaseInstance(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawElementsInstancedBaseVertex(int arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5) {
		gl2.glDrawElementsInstancedBaseVertex(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawElementsInstancedBaseVertexBaseInstance(int arg0, int arg1, int arg2, Buffer arg3, int arg4,
			int arg5, int arg6) {
		gl2.glDrawElementsInstancedBaseVertexBaseInstance(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glDrawPixels(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glDrawPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawPixels(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl2.glDrawPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawRangeElements(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glDrawRangeElements(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawRangeElements(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl2.glDrawRangeElements(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawRangeElementsBaseVertex(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5, int arg6) {
		gl2.glDrawRangeElementsBaseVertex(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glDrawTransformFeedback(int arg0, int arg1) {
		gl2.glDrawTransformFeedback(arg0, arg1);
	}

	@Override
	public void glDrawTransformFeedbackInstanced(int arg0, int arg1, int arg2) {
		gl2.glDrawTransformFeedbackInstanced(arg0, arg1, arg2);
	}

	@Override
	public void glDrawTransformFeedbackNV(int arg0, int arg1) {
		gl2.glDrawTransformFeedbackNV(arg0, arg1);
	}

	@Override
	public void glDrawTransformFeedbackStream(int arg0, int arg1, int arg2) {
		gl2.glDrawTransformFeedbackStream(arg0, arg1, arg2);
	}

	@Override
	public void glDrawTransformFeedbackStreamInstanced(int arg0, int arg1, int arg2, int arg3) {
		gl2.glDrawTransformFeedbackStreamInstanced(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glEdgeFlag(boolean arg0) {
		gl2.glEdgeFlag(arg0);
	}

	@Override
	public void glEdgeFlagFormatNV(int arg0) {
		gl2.glEdgeFlagFormatNV(arg0);
	}

	@Override
	public void glEdgeFlagPointer(int arg0, Buffer arg1) {
		gl2.glEdgeFlagPointer(arg0, arg1);
	}

	@Override
	public void glEdgeFlagPointer(int arg0, long arg1) {
		gl2.glEdgeFlagPointer(arg0, arg1);
	}

	@Override
	public void glEdgeFlagv(byte[] arg0, int arg1) {
		gl2.glEdgeFlagv(arg0, arg1);
	}

	@Override
	public void glEdgeFlagv(ByteBuffer arg0) {
		gl2.glEdgeFlagv(arg0);
	}

	@Override
	public void glEnable(int arg0) {
		gl2.glEnable(arg0);
	}

	@Override
	public void glEnableClientState(int arg0) {
		gl2.glEnableClientState(arg0);
	}

	@Override
	public void glEnableClientStateIndexedEXT(int arg0, int arg1) {
		gl2.glEnableClientStateIndexedEXT(arg0, arg1);
	}

	@Override
	public void glEnableIndexed(int arg0, int arg1) {
		gl2.glEnableIndexed(arg0, arg1);
	}

	@Override
	public void glEnableVariantClientStateEXT(int arg0) {
		gl2.glEnableVariantClientStateEXT(arg0);
	}

	@Override
	public void glEnableVertexAttribAPPLE(int arg0, int arg1) {
		gl2.glEnableVertexAttribAPPLE(arg0, arg1);
	}

	@Override
	public void glEnableVertexAttribArray(int arg0) {
		gl2.glEnableVertexAttribArray(arg0);
	}

	@Override
	public void glEnableVertexAttribArrayARB(int arg0) {
		gl2.glEnableVertexAttribArrayARB(arg0);
	}

	@Override
	public void glEnablei(int arg0, int arg1) {
		gl2.glEnablei(arg0, arg1);
	}

	@Override
	public void glEnd() {
		gl2.glEnd();
	}

	@Override
	public void glEndConditionalRender() {
		gl2.glEndConditionalRender();
	}

	@Override
	public void glEndList() {
		gl2.glEndList();
	}

	@Override
	public void glEndOcclusionQueryNV() {
		gl2.glEndOcclusionQueryNV();
	}

	@Override
	public void glEndPerfMonitorAMD(int arg0) {
		gl2.glEndPerfMonitorAMD(arg0);
	}

	@Override
	public void glEndQuery(int arg0) {
		gl2.glEndQuery(arg0);
	}

	@Override
	public void glEndQueryIndexed(int arg0, int arg1) {
		gl2.glEndQueryIndexed(arg0, arg1);
	}

	@Override
	public void glEndTransformFeedback() {
		gl2.glEndTransformFeedback();
	}

	@Override
	public void glEndVertexShaderEXT() {
		gl2.glEndVertexShaderEXT();
	}

	@Override
	public void glEndVideoCaptureNV(int arg0) {
		gl2.glEndVideoCaptureNV(arg0);
	}

	@Override
	public void glEvalCoord1d(double arg0) {
		gl2.glEvalCoord1d(arg0);
	}

	@Override
	public void glEvalCoord1dv(double[] arg0, int arg1) {
		gl2.glEvalCoord1dv(arg0, arg1);
	}

	@Override
	public void glEvalCoord1dv(DoubleBuffer arg0) {
		gl2.glEvalCoord1dv(arg0);
	}

	@Override
	public void glEvalCoord1f(float arg0) {
		gl2.glEvalCoord1f(arg0);
	}

	@Override
	public void glEvalCoord1fv(float[] arg0, int arg1) {
		gl2.glEvalCoord1fv(arg0, arg1);
	}

	@Override
	public void glEvalCoord1fv(FloatBuffer arg0) {
		gl2.glEvalCoord1fv(arg0);
	}

	@Override
	public void glEvalCoord2d(double arg0, double arg1) {
		gl2.glEvalCoord2d(arg0, arg1);
	}

	@Override
	public void glEvalCoord2dv(double[] arg0, int arg1) {
		gl2.glEvalCoord2dv(arg0, arg1);
	}

	@Override
	public void glEvalCoord2dv(DoubleBuffer arg0) {
		gl2.glEvalCoord2dv(arg0);
	}

	@Override
	public void glEvalCoord2f(float arg0, float arg1) {
		gl2.glEvalCoord2f(arg0, arg1);
	}

	@Override
	public void glEvalCoord2fv(float[] arg0, int arg1) {
		gl2.glEvalCoord2fv(arg0, arg1);
	}

	@Override
	public void glEvalCoord2fv(FloatBuffer arg0) {
		gl2.glEvalCoord2fv(arg0);
	}

	@Override
	public void glEvalMapsNV(int arg0, int arg1) {
		gl2.glEvalMapsNV(arg0, arg1);
	}

	@Override
	public void glEvalMesh1(int arg0, int arg1, int arg2) {
		gl2.glEvalMesh1(arg0, arg1, arg2);
	}

	@Override
	public void glEvalMesh2(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glEvalMesh2(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glEvalPoint1(int arg0) {
		gl2.glEvalPoint1(arg0);
	}

	@Override
	public void glEvalPoint2(int arg0, int arg1) {
		gl2.glEvalPoint2(arg0, arg1);
	}

	@Override
	public void glExtractComponentEXT(int arg0, int arg1, int arg2) {
		gl2.glExtractComponentEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFeedbackBuffer(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glFeedbackBuffer(arg0, arg1, arg2);
	}

	@Override
	public void glFinish() {
		gl2.glFinish();
	}

	@Override
	public void glFinishFenceAPPLE(int arg0) {
		gl2.glFinishFenceAPPLE(arg0);
	}

	@Override
	public void glFinishFenceNV(int arg0) {
		gl2.glFinishFenceNV(arg0);
	}

	@Override
	public void glFinishObjectAPPLE(int arg0, int arg1) {
		gl2.glFinishObjectAPPLE(arg0, arg1);
	}

	@Override
	public void glFinishRenderAPPLE() {
		gl2.glFinishRenderAPPLE();
	}

	@Override
	public void glFinishTextureSUNX() {
		gl2.glFinishTextureSUNX();
	}

	@Override
	public void glFlush() {
		gl2.glFlush();
	}

	@Override
	public void glFlushMappedBufferRange(int arg0, long arg1, long arg2) {
		gl2.glFlushMappedBufferRange(arg0, arg1, arg2);
	}

	@Override
	public void glFlushMappedNamedBufferRangeEXT(int arg0, long arg1, long arg2) {
		gl2.glFlushMappedNamedBufferRangeEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFlushPixelDataRangeNV(int arg0) {
		gl2.glFlushPixelDataRangeNV(arg0);
	}

	@Override
	public void glFlushRenderAPPLE() {
		gl2.glFlushRenderAPPLE();
	}

	@Override
	public void glFlushVertexArrayRangeAPPLE(int arg0, Buffer arg1) {
		gl2.glFlushVertexArrayRangeAPPLE(arg0, arg1);
	}

	@Override
	public void glFlushVertexArrayRangeNV() {
		gl2.glFlushVertexArrayRangeNV();
	}

	@Override
	public void glFogCoordFormatNV(int arg0, int arg1) {
		gl2.glFogCoordFormatNV(arg0, arg1);
	}

	@Override
	public void glFogCoordPointer(int arg0, int arg1, Buffer arg2) {
		gl2.glFogCoordPointer(arg0, arg1, arg2);
	}

	@Override
	public void glFogCoordPointer(int arg0, int arg1, long arg2) {
		gl2.glFogCoordPointer(arg0, arg1, arg2);
	}

	@Override
	public void glFogCoordd(double arg0) {
		gl2.glFogCoordd(arg0);
	}

	@Override
	public void glFogCoorddv(double[] arg0, int arg1) {
		gl2.glFogCoorddv(arg0, arg1);
	}

	@Override
	public void glFogCoorddv(DoubleBuffer arg0) {
		gl2.glFogCoorddv(arg0);
	}

	@Override
	public void glFogCoordf(float arg0) {
		gl2.glFogCoordf(arg0);
	}

	@Override
	public void glFogCoordfv(float[] arg0, int arg1) {
		gl2.glFogCoordfv(arg0, arg1);
	}

	@Override
	public void glFogCoordfv(FloatBuffer arg0) {
		gl2.glFogCoordfv(arg0);
	}

	@Override
	public void glFogCoordh(short arg0) {
		gl2.glFogCoordh(arg0);
	}

	@Override
	public void glFogCoordhv(short[] arg0, int arg1) {
		gl2.glFogCoordhv(arg0, arg1);
	}

	@Override
	public void glFogCoordhv(ShortBuffer arg0) {
		gl2.glFogCoordhv(arg0);
	}

	@Override
	public void glFogf(int arg0, float arg1) {
		gl2.glFogf(arg0, arg1);
	}

	@Override
	public void glFogfv(int arg0, float[] arg1, int arg2) {
		gl2.glFogfv(arg0, arg1, arg2);
	}

	@Override
	public void glFogfv(int arg0, FloatBuffer arg1) {
		gl2.glFogfv(arg0, arg1);
	}

	@Override
	public void glFogi(int arg0, int arg1) {
		gl2.glFogi(arg0, arg1);
	}

	@Override
	public void glFogiv(int arg0, int[] arg1, int arg2) {
		gl2.glFogiv(arg0, arg1, arg2);
	}

	@Override
	public void glFogiv(int arg0, IntBuffer arg1) {
		gl2.glFogiv(arg0, arg1);
	}

	@Override
	public void glFrameTerminatorGREMEDY() {
		gl2.glFrameTerminatorGREMEDY();
	}

	@Override
	public void glFramebufferDrawBufferEXT(int arg0, int arg1) {
		gl2.glFramebufferDrawBufferEXT(arg0, arg1);
	}

	@Override
	public void glFramebufferDrawBuffersEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glFramebufferDrawBuffersEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferDrawBuffersEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glFramebufferDrawBuffersEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFramebufferReadBufferEXT(int arg0, int arg1) {
		gl2.glFramebufferReadBufferEXT(arg0, arg1);
	}

	@Override
	public void glFramebufferRenderbuffer(int arg0, int arg1, int arg2, int arg3) {
		gl2.glFramebufferRenderbuffer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTexture1D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTexture1D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTexture2D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTexture2D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTexture3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glFramebufferTexture3D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glFramebufferTextureARB(int arg0, int arg1, int arg2, int arg3) {
		gl2.glFramebufferTextureARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTextureEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glFramebufferTextureEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTextureFaceARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTextureFaceARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureFaceEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTextureFaceEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayer(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTextureLayer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayerARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTextureLayerARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayerEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glFramebufferTextureLayerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFrontFace(int arg0) {
		gl2.glFrontFace(arg0);
	}

	@Override
	public void glFrustum(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glFrustum(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glFrustumf(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glFrustumf(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGenBuffers(int arg0, int[] arg1, int arg2) {
		gl2.glGenBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenBuffers(int arg0, IntBuffer arg1) {
		gl2.glGenBuffers(arg0, arg1);
	}

	@Override
	public void glGenFencesAPPLE(int arg0, int[] arg1, int arg2) {
		gl2.glGenFencesAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glGenFencesAPPLE(int arg0, IntBuffer arg1) {
		gl2.glGenFencesAPPLE(arg0, arg1);
	}

	@Override
	public void glGenFencesNV(int arg0, int[] arg1, int arg2) {
		gl2.glGenFencesNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenFencesNV(int arg0, IntBuffer arg1) {
		gl2.glGenFencesNV(arg0, arg1);
	}

	@Override
	public void glGenFramebuffers(int arg0, int[] arg1, int arg2) {
		gl2.glGenFramebuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenFramebuffers(int arg0, IntBuffer arg1) {
		gl2.glGenFramebuffers(arg0, arg1);
	}

	@Override
	public int glGenLists(int arg0) {
		return gl2.glGenLists(arg0);
	}

	@Override
	public void glGenNamesAMD(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGenNamesAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGenNamesAMD(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGenNamesAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGenOcclusionQueriesNV(int arg0, int[] arg1, int arg2) {
		gl2.glGenOcclusionQueriesNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenOcclusionQueriesNV(int arg0, IntBuffer arg1) {
		gl2.glGenOcclusionQueriesNV(arg0, arg1);
	}

	@Override
	public void glGenPerfMonitorsAMD(int arg0, int[] arg1, int arg2) {
		gl2.glGenPerfMonitorsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGenPerfMonitorsAMD(int arg0, IntBuffer arg1) {
		gl2.glGenPerfMonitorsAMD(arg0, arg1);
	}

	@Override
	public void glGenProgramPipelines(int arg0, int[] arg1, int arg2) {
		gl2.glGenProgramPipelines(arg0, arg1, arg2);
	}

	@Override
	public void glGenProgramPipelines(int arg0, IntBuffer arg1) {
		gl2.glGenProgramPipelines(arg0, arg1);
	}

	@Override
	public void glGenProgramsARB(int arg0, int[] arg1, int arg2) {
		gl2.glGenProgramsARB(arg0, arg1, arg2);
	}

	@Override
	public void glGenProgramsARB(int arg0, IntBuffer arg1) {
		gl2.glGenProgramsARB(arg0, arg1);
	}

	@Override
	public void glGenQueries(int arg0, int[] arg1, int arg2) {
		gl2.glGenQueries(arg0, arg1, arg2);
	}

	@Override
	public void glGenQueries(int arg0, IntBuffer arg1) {
		gl2.glGenQueries(arg0, arg1);
	}

	@Override
	public void glGenRenderbuffers(int arg0, int[] arg1, int arg2) {
		gl2.glGenRenderbuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenRenderbuffers(int arg0, IntBuffer arg1) {
		gl2.glGenRenderbuffers(arg0, arg1);
	}

	@Override
	public void glGenSamplers(int arg0, int[] arg1, int arg2) {
		gl2.glGenSamplers(arg0, arg1, arg2);
	}

	@Override
	public void glGenSamplers(int arg0, IntBuffer arg1) {
		gl2.glGenSamplers(arg0, arg1);
	}

	@Override
	public int glGenSymbolsEXT(int arg0, int arg1, int arg2, int arg3) {
		return gl2.glGenSymbolsEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGenTextures(int arg0, int[] arg1, int arg2) {
		gl2.glGenTextures(arg0, arg1, arg2);
	}

	@Override
	public void glGenTextures(int arg0, IntBuffer arg1) {
		gl2.glGenTextures(arg0, arg1);
	}

	@Override
	public void glGenTransformFeedbacks(int arg0, int[] arg1, int arg2) {
		gl2.glGenTransformFeedbacks(arg0, arg1, arg2);
	}

	@Override
	public void glGenTransformFeedbacks(int arg0, IntBuffer arg1) {
		gl2.glGenTransformFeedbacks(arg0, arg1);
	}

	@Override
	public void glGenTransformFeedbacksNV(int arg0, int[] arg1, int arg2) {
		gl2.glGenTransformFeedbacksNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenTransformFeedbacksNV(int arg0, IntBuffer arg1) {
		gl2.glGenTransformFeedbacksNV(arg0, arg1);
	}

	@Override
	public void glGenVertexArrays(int arg0, int[] arg1, int arg2) {
		gl2.glGenVertexArrays(arg0, arg1, arg2);
	}

	@Override
	public void glGenVertexArrays(int arg0, IntBuffer arg1) {
		gl2.glGenVertexArrays(arg0, arg1);
	}

	@Override
	public int glGenVertexShadersEXT(int arg0) {
		return gl2.glGenVertexShadersEXT(arg0);
	}

	@Override
	public void glGenerateMipmap(int arg0) {
		gl2.glGenerateMipmap(arg0);
	}

	@Override
	public void glGenerateMultiTexMipmapEXT(int arg0, int arg1) {
		gl2.glGenerateMultiTexMipmapEXT(arg0, arg1);
	}

	@Override
	public void glGenerateTextureMipmapEXT(int arg0, int arg1) {
		gl2.glGenerateTextureMipmapEXT(arg0, arg1);
	}

	@Override
	public void glGetActiveAtomicCounterBufferiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetActiveAtomicCounterBufferiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveAtomicCounterBufferiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetActiveAtomicCounterBufferiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetActiveAttrib(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6, int[] arg7,
			int arg8, byte[] arg9, int arg10) {
		gl2.glGetActiveAttrib(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveAttrib(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl2.glGetActiveAttrib(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveSubroutineName(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5, byte[] arg6,
			int arg7) {
		gl2.glGetActiveSubroutineName(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetActiveSubroutineName(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4, ByteBuffer arg5) {
		gl2.glGetActiveSubroutineName(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetActiveSubroutineUniformName(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5,
			byte[] arg6, int arg7) {
		gl2.glGetActiveSubroutineUniformName(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetActiveSubroutineUniformName(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4, ByteBuffer arg5) {
		gl2.glGetActiveSubroutineUniformName(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetActiveSubroutineUniformiv(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetActiveSubroutineUniformiv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetActiveSubroutineUniformiv(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glGetActiveSubroutineUniformiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniform(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl2.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveUniform(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl2.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformARB(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl2.glGetActiveUniformARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveUniformARB(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl2.glGetActiveUniformARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformBlockName(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl2.glGetActiveUniformBlockName(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformBlockName(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl2.glGetActiveUniformBlockName(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformBlockiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetActiveUniformBlockiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformBlockiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetActiveUniformBlockiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetActiveUniformName(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl2.glGetActiveUniformName(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformName(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl2.glGetActiveUniformName(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformsiv(int arg0, int arg1, int[] arg2, int arg3, int arg4, int[] arg5, int arg6) {
		gl2.glGetActiveUniformsiv(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformsiv(int arg0, int arg1, IntBuffer arg2, int arg3, IntBuffer arg4) {
		gl2.glGetActiveUniformsiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetAttachedObjectsARB(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetAttachedObjectsARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetAttachedObjectsARB(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl2.glGetAttachedObjectsARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetAttachedShaders(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetAttachedShaders(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetAttachedShaders(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl2.glGetAttachedShaders(arg0, arg1, arg2, arg3);
	}

	@Override
	public int glGetAttribLocation(int arg0, String arg1) {
		return gl2.glGetAttribLocation(arg0, arg1);
	}

	@Override
	public void glGetBooleanIndexedv(int arg0, int arg1, byte[] arg2, int arg3) {
		gl2.glGetBooleanIndexedv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBooleanIndexedv(int arg0, int arg1, ByteBuffer arg2) {
		gl2.glGetBooleanIndexedv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleani_v(int arg0, int arg1, byte[] arg2, int arg3) {
		gl2.glGetBooleani_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBooleani_v(int arg0, int arg1, ByteBuffer arg2) {
		gl2.glGetBooleani_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleanv(int arg0, byte[] arg1, int arg2) {
		gl2.glGetBooleanv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleanv(int arg0, ByteBuffer arg1) {
		gl2.glGetBooleanv(arg0, arg1);
	}

	@Override
	public int glGetBoundBuffer(int arg0) {
		return gl2.glGetBoundBuffer(arg0);
	}

	@Override
	public void glGetBufferParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetBufferParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBufferParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetBufferParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBufferParameterui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetBufferParameterui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBufferParameterui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetBufferParameterui64vNV(arg0, arg1, arg2);
	}

	@Override
	public long glGetBufferSize(int arg0) {
		return gl2.glGetBufferSize(arg0);
	}

	@Override
	public void glGetBufferSubData(int arg0, long arg1, long arg2, Buffer arg3) {
		gl2.glGetBufferSubData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetClipPlane(int arg0, double[] arg1, int arg2) {
		gl2.glGetClipPlane(arg0, arg1, arg2);
	}

	@Override
	public void glGetClipPlane(int arg0, DoubleBuffer arg1) {
		gl2.glGetClipPlane(arg0, arg1);
	}

	@Override
	public void glGetColorTable(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetColorTable(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTable(int arg0, int arg1, int arg2, long arg3) {
		gl2.glGetColorTable(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetColorTableParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetColorTableParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetColorTableParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetColorTableParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetColorTableParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedMultiTexImageEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetCompressedMultiTexImageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetCompressedTexImage(int arg0, int arg1, Buffer arg2) {
		gl2.glGetCompressedTexImage(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedTexImage(int arg0, int arg1, long arg2) {
		gl2.glGetCompressedTexImage(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedTextureImageEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetCompressedTextureImageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionFilter(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetConvolutionFilter(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionFilter(int arg0, int arg1, int arg2, long arg3) {
		gl2.glGetConvolutionFilter(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetConvolutionParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetConvolutionParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetConvolutionParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetConvolutionParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetConvolutionParameteriv(arg0, arg1, arg2);
	}

	@Override
	public int glGetDebugMessageLogAMD(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, int[] arg6,
			int arg7, int[] arg8, int arg9, byte[] arg10, int arg11) {
		return gl2.glGetDebugMessageLogAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public int glGetDebugMessageLogAMD(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, IntBuffer arg4,
			IntBuffer arg5, ByteBuffer arg6) {
		return gl2.glGetDebugMessageLogAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public int glGetDebugMessageLogARB(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, int[] arg6,
			int arg7, int[] arg8, int arg9, int[] arg10, int arg11, byte[] arg12, int arg13) {
		return gl2.glGetDebugMessageLogARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11,
				arg12, arg13);
	}

	@Override
	public int glGetDebugMessageLogARB(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, IntBuffer arg4,
			IntBuffer arg5, IntBuffer arg6, ByteBuffer arg7) {
		return gl2.glGetDebugMessageLogARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetDoubleIndexedvEXT(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetDoubleIndexedvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetDoubleIndexedvEXT(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetDoubleIndexedvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublei_v(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetDoublei_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetDoublei_v(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetDoublei_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublev(int arg0, double[] arg1, int arg2) {
		gl2.glGetDoublev(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublev(int arg0, DoubleBuffer arg1) {
		gl2.glGetDoublev(arg0, arg1);
	}

	@Override
	public int glGetError() {
		return gl2.glGetError();
	}

	@Override
	public void glGetFenceivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetFenceivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFenceivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetFenceivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatIndexedvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetFloatIndexedvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFloatIndexedvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetFloatIndexedvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloati_v(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetFloati_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFloati_v(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetFloati_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatv(int arg0, float[] arg1, int arg2) {
		gl2.glGetFloatv(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatv(int arg0, FloatBuffer arg1) {
		gl2.glGetFloatv(arg0, arg1);
	}

	@Override
	public int glGetFragDataIndex(int arg0, String arg1) {
		return gl2.glGetFragDataIndex(arg0, arg1);
	}

	@Override
	public int glGetFragDataLocation(int arg0, String arg1) {
		return gl2.glGetFragDataLocation(arg0, arg1);
	}

	@Override
	public void glGetFramebufferAttachmentParameteriv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetFramebufferAttachmentParameteriv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFramebufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetFramebufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFramebufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetFramebufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public int glGetGraphicsResetStatus() {
		return gl2.glGetGraphicsResetStatus();
	}

	@Override
	public int glGetHandleARB(int arg0) {
		return gl2.glGetHandleARB(arg0);
	}

	@Override
	public void glGetHistogram(int arg0, boolean arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glGetHistogram(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetHistogram(int arg0, boolean arg1, int arg2, int arg3, long arg4) {
		gl2.glGetHistogram(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetHistogramParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetHistogramParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetHistogramParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetHistogramParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetHistogramParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetHistogramParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetHistogramParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetHistogramParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetInfoLogARB(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetInfoLogARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetInfoLogARB(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetInfoLogARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerIndexedv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetIntegerIndexedv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerIndexedv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetIntegerIndexedv(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegeri_v(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetIntegeri_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegeri_v(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetIntegeri_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64i_vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetIntegerui64i_vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerui64i_vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetIntegerui64i_vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glGetIntegerui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64vNV(int arg0, LongBuffer arg1) {
		gl2.glGetIntegerui64vNV(arg0, arg1);
	}

	@Override
	public void glGetIntegerv(int arg0, int[] arg1, int arg2) {
		gl2.glGetIntegerv(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerv(int arg0, IntBuffer arg1) {
		gl2.glGetIntegerv(arg0, arg1);
	}

	@Override
	public void glGetInternalformativ(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetInternalformativ(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetInternalformativ(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glGetInternalformativ(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetInvariantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl2.glGetInvariantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl2.glGetInvariantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetInvariantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetInvariantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetInvariantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetInvariantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetInvariantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetInvariantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLightfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetLightfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLightfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetLightfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetLightiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetLightiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLightiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetLightiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl2.glGetLocalConstantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl2.glGetLocalConstantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetLocalConstantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetLocalConstantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetLocalConstantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetLocalConstantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapAttribParameterfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetMapAttribParameterfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMapAttribParameterfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetMapAttribParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapAttribParameterivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMapAttribParameterivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMapAttribParameterivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMapAttribParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapControlPointsNV(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5, Buffer arg6) {
		gl2.glGetMapControlPointsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetMapParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMapParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMapParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetMapParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetMapParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapdv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetMapdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapdv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetMapdv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetMapiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetMapiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMaterialfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMaterialfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMaterialfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMaterialfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMaterialiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetMaterialiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMaterialiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetMaterialiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMinmax(int arg0, boolean arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glGetMinmax(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMinmax(int arg0, boolean arg1, int arg2, int arg3, long arg4) {
		gl2.glGetMinmax(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMinmaxParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMinmaxParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMinmaxParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMinmaxParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMinmaxParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetMinmaxParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMinmaxParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetMinmaxParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMultiTexEnvfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetMultiTexEnvfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexEnvfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetMultiTexEnvfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexEnvivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMultiTexEnvivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexEnvivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMultiTexEnvivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGendvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glGetMultiTexGendvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGendvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glGetMultiTexGendvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGenfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetMultiTexGenfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGenfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetMultiTexGenfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGenivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMultiTexGenivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGenivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMultiTexGenivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexImageEXT(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glGetMultiTexImageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl2.glGetMultiTexLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl2.glGetMultiTexLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetMultiTexLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glGetMultiTexLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMultiTexParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMultiTexParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetMultiTexParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetMultiTexParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetMultiTexParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetMultiTexParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultisamplefv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMultisamplefv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultisamplefv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMultisamplefv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMultisamplefvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetMultisamplefvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultisamplefvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetMultisamplefvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetNamedBufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedBufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetNamedBufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferParameterui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetNamedBufferParameterui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedBufferParameterui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetNamedBufferParameterui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferSubDataEXT(int arg0, long arg1, long arg2, Buffer arg3) {
		gl2.glGetNamedBufferSubDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedFramebufferAttachmentParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetNamedFramebufferAttachmentParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedFramebufferAttachmentParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetNamedFramebufferAttachmentParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetNamedProgramLocalParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetNamedProgramLocalParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetNamedProgramLocalParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetNamedProgramLocalParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterdvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glGetNamedProgramLocalParameterdvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterdvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glGetNamedProgramLocalParameterdvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetNamedProgramLocalParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetNamedProgramLocalParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramStringEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetNamedProgramStringEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetNamedProgramivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetNamedProgramivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedRenderbufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetNamedRenderbufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedRenderbufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetNamedRenderbufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedStringARB(int arg0, String arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl2.glGetNamedStringARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetNamedStringARB(int arg0, String arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl2.glGetNamedStringARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedStringivARB(int arg0, String arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetNamedStringivARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedStringivARB(int arg0, String arg1, int arg2, IntBuffer arg3) {
		gl2.glGetNamedStringivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetObjectParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetObjectParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetObjectParameterivAPPLE(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetObjectParameterivAPPLE(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetObjectParameterivAPPLE(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetObjectParameterivAPPLE(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetObjectParameterivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetObjectParameterivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetOcclusionQueryivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetOcclusionQueryivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetOcclusionQueryivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetOcclusionQueryivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetOcclusionQueryuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetOcclusionQueryuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetOcclusionQueryuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetOcclusionQueryuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPerfMonitorCounterDataAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6) {
		gl2.glGetPerfMonitorCounterDataAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetPerfMonitorCounterDataAMD(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4) {
		gl2.glGetPerfMonitorCounterDataAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorCounterInfoAMD(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetPerfMonitorCounterInfoAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPerfMonitorCounterStringAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5,
			int arg6) {
		gl2.glGetPerfMonitorCounterStringAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetPerfMonitorCounterStringAMD(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl2.glGetPerfMonitorCounterStringAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorCountersAMD(int arg0, int[] arg1, int arg2, int[] arg3, int arg4, int arg5, int[] arg6,
			int arg7) {
		gl2.glGetPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetPerfMonitorCountersAMD(int arg0, IntBuffer arg1, IntBuffer arg2, int arg3, IntBuffer arg4) {
		gl2.glGetPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorGroupStringAMD(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetPerfMonitorGroupStringAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetPerfMonitorGroupStringAMD(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetPerfMonitorGroupStringAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPerfMonitorGroupsAMD(int[] arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetPerfMonitorGroupsAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorGroupsAMD(IntBuffer arg0, int arg1, IntBuffer arg2) {
		gl2.glGetPerfMonitorGroupsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapfv(int arg0, float[] arg1, int arg2) {
		gl2.glGetPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapfv(int arg0, FloatBuffer arg1) {
		gl2.glGetPixelMapfv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapfv(int arg0, long arg1) {
		gl2.glGetPixelMapfv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, int[] arg1, int arg2) {
		gl2.glGetPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, IntBuffer arg1) {
		gl2.glGetPixelMapuiv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, long arg1) {
		gl2.glGetPixelMapuiv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapusv(int arg0, long arg1) {
		gl2.glGetPixelMapusv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapusv(int arg0, short[] arg1, int arg2) {
		gl2.glGetPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapusv(int arg0, ShortBuffer arg1) {
		gl2.glGetPixelMapusv(arg0, arg1);
	}

	@Override
	public void glGetPolygonStipple(byte[] arg0, int arg1) {
		gl2.glGetPolygonStipple(arg0, arg1);
	}

	@Override
	public void glGetPolygonStipple(ByteBuffer arg0) {
		gl2.glGetPolygonStipple(arg0);
	}

	@Override
	public void glGetPolygonStipple(long arg0) {
		gl2.glGetPolygonStipple(arg0);
	}

	@Override
	public void glGetProgramBinary(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, Buffer arg6) {
		gl2.glGetProgramBinary(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetProgramBinary(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, Buffer arg4) {
		gl2.glGetProgramBinary(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetProgramEnvParameterIivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramEnvParameterIivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterIivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramEnvParameterIivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterIuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramEnvParameterIuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterIuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramEnvParameterIuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetProgramEnvParameterdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetProgramEnvParameterdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetProgramEnvParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetProgramEnvParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramInfoLog(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetProgramInfoLog(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetProgramInfoLog(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetProgramInfoLog(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramLocalParameterIivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramLocalParameterIivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterIuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramLocalParameterIuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramLocalParameterIuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetProgramLocalParameterdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetProgramLocalParameterdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetProgramLocalParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetProgramLocalParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramPipelineInfoLog(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetProgramPipelineInfoLog(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetProgramPipelineInfoLog(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetProgramPipelineInfoLog(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramPipelineiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramPipelineiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramPipelineiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramPipelineiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramStageiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetProgramStageiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetProgramStageiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetProgramStageiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramStringARB(int arg0, int arg1, Buffer arg2) {
		gl2.glGetProgramStringARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramSubroutineParameteruivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramSubroutineParameteruivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramSubroutineParameteruivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramSubroutineParameteruivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetProgramivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetProgramivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryIndexediv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetQueryIndexediv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetQueryIndexediv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetQueryIndexediv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjecti64v(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetQueryObjecti64v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjecti64v(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetQueryObjecti64v(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjecti64vEXT(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetQueryObjecti64vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjecti64vEXT(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetQueryObjecti64vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetQueryObjectiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetQueryObjectiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectui64v(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetQueryObjectui64v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectui64v(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetQueryObjectui64v(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectui64vEXT(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetQueryObjectui64vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectui64vEXT(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetQueryObjectui64vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetQueryObjectuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetQueryObjectuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetQueryiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetQueryiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetRenderbufferParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetRenderbufferParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetRenderbufferParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetRenderbufferParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSamplerParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetSamplerParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetSamplerParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetSamplerParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSamplerParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetSamplerParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetSamplerParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetSamplerParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSamplerParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetSamplerParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetSamplerParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetSamplerParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSamplerParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetSamplerParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetSamplerParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetSamplerParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSeparableFilter(int arg0, int arg1, int arg2, Buffer arg3, Buffer arg4, Buffer arg5) {
		gl2.glGetSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetSeparableFilter(int arg0, int arg1, int arg2, long arg3, long arg4, long arg5) {
		gl2.glGetSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderInfoLog(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetShaderInfoLog(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderInfoLog(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetShaderInfoLog(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderPrecisionFormat(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderPrecisionFormat(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl2.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderSource(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetShaderSource(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderSource(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetShaderSource(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderSourceARB(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl2.glGetShaderSourceARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderSourceARB(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl2.glGetShaderSourceARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetShaderiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetShaderiv(arg0, arg1, arg2);
	}

	@Override
	public String glGetString(int arg0) {
		return gl2.glGetString(arg0);
	}

	@Override
	public String glGetStringi(int arg0, int arg1) {
		return gl2.glGetStringi(arg0, arg1);
	}

	@Override
	public int glGetSubroutineIndex(int arg0, int arg1, String arg2) {
		return gl2.glGetSubroutineIndex(arg0, arg1, arg2);
	}

	@Override
	public int glGetSubroutineUniformLocation(int arg0, int arg1, String arg2) {
		return gl2.glGetSubroutineUniformLocation(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexEnvfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetTexEnvfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexEnvfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetTexEnvfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexEnviv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetTexEnviv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexEnviv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetTexEnviv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGendv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetTexGendv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGendv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetTexGendv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGenfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetTexGenfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGenfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetTexGenfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGeniv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetTexGeniv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGeniv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetTexGeniv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexImage(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glGetTexImage(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexImage(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl2.glGetTexImage(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameterfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetTexLevelParameterfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameterfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetTexLevelParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexLevelParameteriv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetTexLevelParameteriv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameteriv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetTexLevelParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetTexParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetTexParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetTexParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetTexParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetTexParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetTexParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetTexParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetTexParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTextureImageEXT(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glGetTextureImageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl2.glGetTextureLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl2.glGetTextureLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glGetTextureLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glGetTextureLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetTextureParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetTextureParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetTextureParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetTextureParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetTextureParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetTextureParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetTextureParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetTextureParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTransformFeedbackVarying(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl2.glGetTransformFeedbackVarying(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetTransformFeedbackVarying(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4,
			IntBuffer arg5, ByteBuffer arg6) {
		gl2.glGetTransformFeedbackVarying(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public int glGetUniformBlockIndex(int arg0, String arg1) {
		return gl2.glGetUniformBlockIndex(arg0, arg1);
	}

	@Override
	public int glGetUniformBufferSizeEXT(int arg0, int arg1) {
		return gl2.glGetUniformBufferSizeEXT(arg0, arg1);
	}

	@Override
	public void glGetUniformIndices(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl2.glGetUniformIndices(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetUniformIndices(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl2.glGetUniformIndices(arg0, arg1, arg2, arg3);
	}

	@Override
	public int glGetUniformLocation(int arg0, String arg1) {
		return gl2.glGetUniformLocation(arg0, arg1);
	}

	@Override
	public int glGetUniformLocationARB(int arg0, String arg1) {
		return gl2.glGetUniformLocationARB(arg0, arg1);
	}

	@Override
	public long glGetUniformOffsetEXT(int arg0, int arg1) {
		return gl2.glGetUniformOffsetEXT(arg0, arg1);
	}

	@Override
	public void glGetUniformSubroutineuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetUniformSubroutineuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformSubroutineuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetUniformSubroutineuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetUniformfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetUniformfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetUniformfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetUniformfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetUniformiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetUniformiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetUniformivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetUniformivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetUniformui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetUniformuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetUniformuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl2.glGetVariantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl2.glGetVariantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetVariantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetVariantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVariantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVariantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribIiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribIivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIuivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIuivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribIuivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribLdv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetVertexAttribLdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribLdv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetVertexAttribLdv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribLi64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetVertexAttribLi64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribLi64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetVertexAttribLi64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribLui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glGetVertexAttribLui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribLui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glGetVertexAttribLui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribdv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetVertexAttribdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribdv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetVertexAttribdv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glGetVertexAttribdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glGetVertexAttribdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetVertexAttribfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetVertexAttribfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetVertexAttribfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetVertexAttribfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVertexAttribivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVertexAttribivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVideoCaptureStreamdvNV(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glGetVideoCaptureStreamdvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamdvNV(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glGetVideoCaptureStreamdvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureStreamfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetVideoCaptureStreamfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetVideoCaptureStreamfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureStreamivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetVideoCaptureStreamivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetVideoCaptureStreamivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetVideoCaptureivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetVideoCaptureivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetnColorTable(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glGetnColorTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnCompressedTexImage(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glGetnCompressedTexImage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnConvolutionFilter(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glGetnConvolutionFilter(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnHistogram(int arg0, boolean arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glGetnHistogram(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnMapdv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glGetnMapdv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapdv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glGetnMapdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMapfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetnMapfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetnMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMapiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetnMapiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetnMapiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMinmax(int arg0, boolean arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glGetnMinmax(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnPixelMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glGetnPixelMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glGetnPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPixelMapuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glGetnPixelMapuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glGetnPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPixelMapusv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glGetnPixelMapusv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapusv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glGetnPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPolygonStipple(int arg0, byte[] arg1, int arg2) {
		gl2.glGetnPolygonStipple(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPolygonStipple(int arg0, ByteBuffer arg1) {
		gl2.glGetnPolygonStipple(arg0, arg1);
	}

	@Override
	public void glGetnSeparableFilter(int arg0, int arg1, int arg2, int arg3, Buffer arg4, int arg5, Buffer arg6,
			Buffer arg7) {
		gl2.glGetnSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetnTexImage(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl2.glGetnTexImage(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnUniformdv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glGetnUniformdv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformdv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glGetnUniformdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glGetnUniformfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glGetnUniformfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetnUniformiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetnUniformiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformuiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glGetnUniformuiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformuiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glGetnUniformuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glHint(int arg0, int arg1) {
		gl2.glHint(arg0, arg1);
	}

	@Override
	public void glHintPGI(int arg0, int arg1) {
		gl2.glHintPGI(arg0, arg1);
	}

	@Override
	public void glHistogram(int arg0, int arg1, int arg2, boolean arg3) {
		gl2.glHistogram(arg0, arg1, arg2, arg3);
	}

	@Override
	public long glImportSyncEXT(int arg0, long arg1, int arg2) {
		return gl2.glImportSyncEXT(arg0, arg1, arg2);
	}

	@Override
	public void glIndexFormatNV(int arg0, int arg1) {
		gl2.glIndexFormatNV(arg0, arg1);
	}

	@Override
	public void glIndexFuncEXT(int arg0, float arg1) {
		gl2.glIndexFuncEXT(arg0, arg1);
	}

	@Override
	public void glIndexMask(int arg0) {
		gl2.glIndexMask(arg0);
	}

	@Override
	public void glIndexMaterialEXT(int arg0, int arg1) {
		gl2.glIndexMaterialEXT(arg0, arg1);
	}

	@Override
	public void glIndexPointer(int arg0, int arg1, Buffer arg2) {
		gl2.glIndexPointer(arg0, arg1, arg2);
	}

	@Override
	public void glIndexd(double arg0) {
		gl2.glIndexd(arg0);
	}

	@Override
	public void glIndexdv(double[] arg0, int arg1) {
		gl2.glIndexdv(arg0, arg1);
	}

	@Override
	public void glIndexdv(DoubleBuffer arg0) {
		gl2.glIndexdv(arg0);
	}

	@Override
	public void glIndexf(float arg0) {
		gl2.glIndexf(arg0);
	}

	@Override
	public void glIndexfv(float[] arg0, int arg1) {
		gl2.glIndexfv(arg0, arg1);
	}

	@Override
	public void glIndexfv(FloatBuffer arg0) {
		gl2.glIndexfv(arg0);
	}

	@Override
	public void glIndexi(int arg0) {
		gl2.glIndexi(arg0);
	}

	@Override
	public void glIndexiv(int[] arg0, int arg1) {
		gl2.glIndexiv(arg0, arg1);
	}

	@Override
	public void glIndexiv(IntBuffer arg0) {
		gl2.glIndexiv(arg0);
	}

	@Override
	public void glIndexs(short arg0) {
		gl2.glIndexs(arg0);
	}

	@Override
	public void glIndexsv(short[] arg0, int arg1) {
		gl2.glIndexsv(arg0, arg1);
	}

	@Override
	public void glIndexsv(ShortBuffer arg0) {
		gl2.glIndexsv(arg0);
	}

	@Override
	public void glIndexub(byte arg0) {
		gl2.glIndexub(arg0);
	}

	@Override
	public void glIndexubv(byte[] arg0, int arg1) {
		gl2.glIndexubv(arg0, arg1);
	}

	@Override
	public void glIndexubv(ByteBuffer arg0) {
		gl2.glIndexubv(arg0);
	}

	@Override
	public void glInitNames() {
		gl2.glInitNames();
	}

	@Override
	public void glInsertComponentEXT(int arg0, int arg1, int arg2) {
		gl2.glInsertComponentEXT(arg0, arg1, arg2);
	}

	@Override
	public void glInterleavedArrays(int arg0, int arg1, Buffer arg2) {
		gl2.glInterleavedArrays(arg0, arg1, arg2);
	}

	@Override
	public void glInterleavedArrays(int arg0, int arg1, long arg2) {
		gl2.glInterleavedArrays(arg0, arg1, arg2);
	}

	@Override
	public boolean glIsBuffer(int arg0) {
		return gl2.glIsBuffer(arg0);
	}

	@Override
	public boolean glIsBufferResidentNV(int arg0) {
		return gl2.glIsBufferResidentNV(arg0);
	}

	@Override
	public boolean glIsEnabled(int arg0) {
		return gl2.glIsEnabled(arg0);
	}

	@Override
	public boolean glIsEnabledIndexed(int arg0, int arg1) {
		return gl2.glIsEnabledIndexed(arg0, arg1);
	}

	@Override
	public boolean glIsEnabledi(int arg0, int arg1) {
		return gl2.glIsEnabledi(arg0, arg1);
	}

	@Override
	public boolean glIsFenceAPPLE(int arg0) {
		return gl2.glIsFenceAPPLE(arg0);
	}

	@Override
	public boolean glIsFenceNV(int arg0) {
		return gl2.glIsFenceNV(arg0);
	}

	@Override
	public boolean glIsFramebuffer(int arg0) {
		return gl2.glIsFramebuffer(arg0);
	}

	@Override
	public boolean glIsList(int arg0) {
		return gl2.glIsList(arg0);
	}

	@Override
	public boolean glIsNameAMD(int arg0, int arg1) {
		return gl2.glIsNameAMD(arg0, arg1);
	}

	@Override
	public boolean glIsNamedBufferResidentNV(int arg0) {
		return gl2.glIsNamedBufferResidentNV(arg0);
	}

	@Override
	public boolean glIsNamedStringARB(int arg0, String arg1) {
		return gl2.glIsNamedStringARB(arg0, arg1);
	}

	@Override
	public boolean glIsOcclusionQueryNV(int arg0) {
		return gl2.glIsOcclusionQueryNV(arg0);
	}

	@Override
	public boolean glIsPBOPackEnabled() {
		return gl2.glIsPBOPackEnabled();
	}

	@Override
	public boolean glIsPBOUnpackEnabled() {
		return gl2.glIsPBOUnpackEnabled();
	}

	@Override
	public boolean glIsProgram(int arg0) {
		return gl2.glIsProgram(arg0);
	}

	@Override
	public boolean glIsProgramARB(int arg0) {
		return gl2.glIsProgramARB(arg0);
	}

	@Override
	public boolean glIsProgramPipeline(int arg0) {
		return gl2.glIsProgramPipeline(arg0);
	}

	@Override
	public boolean glIsQuery(int arg0) {
		return gl2.glIsQuery(arg0);
	}

	@Override
	public boolean glIsRenderbuffer(int arg0) {
		return gl2.glIsRenderbuffer(arg0);
	}

	@Override
	public boolean glIsSampler(int arg0) {
		return gl2.glIsSampler(arg0);
	}

	@Override
	public boolean glIsShader(int arg0) {
		return gl2.glIsShader(arg0);
	}

	@Override
	public boolean glIsTexture(int arg0) {
		return gl2.glIsTexture(arg0);
	}

	@Override
	public boolean glIsTransformFeedback(int arg0) {
		return gl2.glIsTransformFeedback(arg0);
	}

	@Override
	public boolean glIsTransformFeedbackNV(int arg0) {
		return gl2.glIsTransformFeedbackNV(arg0);
	}

	@Override
	public boolean glIsVBOArrayEnabled() {
		return gl2.glIsVBOArrayEnabled();
	}

	@Override
	public boolean glIsVBOElementArrayEnabled() {
		return gl2.glIsVBOElementArrayEnabled();
	}

	@Override
	public boolean glIsVariantEnabledEXT(int arg0, int arg1) {
		return gl2.glIsVariantEnabledEXT(arg0, arg1);
	}

	@Override
	public boolean glIsVertexArray(int arg0) {
		return gl2.glIsVertexArray(arg0);
	}

	@Override
	public boolean glIsVertexAttribEnabledAPPLE(int arg0, int arg1) {
		return gl2.glIsVertexAttribEnabledAPPLE(arg0, arg1);
	}

	@Override
	public void glLightModelf(int arg0, float arg1) {
		gl2.glLightModelf(arg0, arg1);
	}

	@Override
	public void glLightModelfv(int arg0, float[] arg1, int arg2) {
		gl2.glLightModelfv(arg0, arg1, arg2);
	}

	@Override
	public void glLightModelfv(int arg0, FloatBuffer arg1) {
		gl2.glLightModelfv(arg0, arg1);
	}

	@Override
	public void glLightModeli(int arg0, int arg1) {
		gl2.glLightModeli(arg0, arg1);
	}

	@Override
	public void glLightModeliv(int arg0, int[] arg1, int arg2) {
		gl2.glLightModeliv(arg0, arg1, arg2);
	}

	@Override
	public void glLightModeliv(int arg0, IntBuffer arg1) {
		gl2.glLightModeliv(arg0, arg1);
	}

	@Override
	public void glLightf(int arg0, int arg1, float arg2) {
		gl2.glLightf(arg0, arg1, arg2);
	}

	@Override
	public void glLightfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glLightfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glLightfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glLightfv(arg0, arg1, arg2);
	}

	@Override
	public void glLighti(int arg0, int arg1, int arg2) {
		gl2.glLighti(arg0, arg1, arg2);
	}

	@Override
	public void glLightiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glLightiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glLightiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glLightiv(arg0, arg1, arg2);
	}

	@Override
	public void glLineStipple(int arg0, short arg1) {
		gl2.glLineStipple(arg0, arg1);
	}

	@Override
	public void glLineWidth(float arg0) {
		gl2.glLineWidth(arg0);
	}

	@Override
	public void glLinkProgram(int arg0) {
		gl2.glLinkProgram(arg0);
	}

	@Override
	public void glLinkProgramARB(int arg0) {
		gl2.glLinkProgramARB(arg0);
	}

	@Override
	public void glListBase(int arg0) {
		gl2.glListBase(arg0);
	}

	@Override
	public void glLoadIdentity() {
		gl2.glLoadIdentity();
	}

	@Override
	public void glLoadMatrixd(double[] arg0, int arg1) {
		gl2.glLoadMatrixd(arg0, arg1);
	}

	@Override
	public void glLoadMatrixd(DoubleBuffer arg0) {
		gl2.glLoadMatrixd(arg0);
	}

	@Override
	public void glLoadMatrixf(float[] arg0, int arg1) {
		gl2.glLoadMatrixf(arg0, arg1);
	}

	@Override
	public void glLoadMatrixf(FloatBuffer arg0) {
		gl2.glLoadMatrixf(arg0);
	}

	@Override
	public void glLoadName(int arg0) {
		gl2.glLoadName(arg0);
	}

	@Override
	public void glLoadTransposeMatrixd(double[] arg0, int arg1) {
		gl2.glLoadTransposeMatrixd(arg0, arg1);
	}

	@Override
	public void glLoadTransposeMatrixd(DoubleBuffer arg0) {
		gl2.glLoadTransposeMatrixd(arg0);
	}

	@Override
	public void glLoadTransposeMatrixf(float[] arg0, int arg1) {
		gl2.glLoadTransposeMatrixf(arg0, arg1);
	}

	@Override
	public void glLoadTransposeMatrixf(FloatBuffer arg0) {
		gl2.glLoadTransposeMatrixf(arg0);
	}

	@Override
	public void glLockArraysEXT(int arg0, int arg1) {
		gl2.glLockArraysEXT(arg0, arg1);
	}

	@Override
	public void glLogicOp(int arg0) {
		gl2.glLogicOp(arg0);
	}

	@Override
	public void glMakeBufferNonResidentNV(int arg0) {
		gl2.glMakeBufferNonResidentNV(arg0);
	}

	@Override
	public void glMakeBufferResidentNV(int arg0, int arg1) {
		gl2.glMakeBufferResidentNV(arg0, arg1);
	}

	@Override
	public void glMakeNamedBufferNonResidentNV(int arg0) {
		gl2.glMakeNamedBufferNonResidentNV(arg0);
	}

	@Override
	public void glMakeNamedBufferResidentNV(int arg0, int arg1) {
		gl2.glMakeNamedBufferResidentNV(arg0, arg1);
	}

	@Override
	public void glMap1d(int arg0, double arg1, double arg2, int arg3, int arg4, double[] arg5, int arg6) {
		gl2.glMap1d(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMap1d(int arg0, double arg1, double arg2, int arg3, int arg4, DoubleBuffer arg5) {
		gl2.glMap1d(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMap1f(int arg0, float arg1, float arg2, int arg3, int arg4, float[] arg5, int arg6) {
		gl2.glMap1f(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMap1f(int arg0, float arg1, float arg2, int arg3, int arg4, FloatBuffer arg5) {
		gl2.glMap1f(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMap2d(int arg0, double arg1, double arg2, int arg3, int arg4, double arg5, double arg6, int arg7,
			int arg8, double[] arg9, int arg10) {
		gl2.glMap2d(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMap2d(int arg0, double arg1, double arg2, int arg3, int arg4, double arg5, double arg6, int arg7,
			int arg8, DoubleBuffer arg9) {
		gl2.glMap2d(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMap2f(int arg0, float arg1, float arg2, int arg3, int arg4, float arg5, float arg6, int arg7,
			int arg8, float[] arg9, int arg10) {
		gl2.glMap2f(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMap2f(int arg0, float arg1, float arg2, int arg3, int arg4, float arg5, float arg6, int arg7,
			int arg8, FloatBuffer arg9) {
		gl2.glMap2f(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public ByteBuffer glMapBuffer(int arg0, int arg1) {
		return gl2.glMapBuffer(arg0, arg1);
	}

	@Override
	public ByteBuffer glMapBufferRange(int arg0, long arg1, long arg2, int arg3) {
		return gl2.glMapBufferRange(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapControlPointsNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7, Buffer arg8) {
		gl2.glMapControlPointsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glMapGrid1d(int arg0, double arg1, double arg2) {
		gl2.glMapGrid1d(arg0, arg1, arg2);
	}

	@Override
	public void glMapGrid1f(int arg0, float arg1, float arg2) {
		gl2.glMapGrid1f(arg0, arg1, arg2);
	}

	@Override
	public void glMapGrid2d(int arg0, double arg1, double arg2, int arg3, double arg4, double arg5) {
		gl2.glMapGrid2d(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMapGrid2f(int arg0, float arg1, float arg2, int arg3, float arg4, float arg5) {
		gl2.glMapGrid2f(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public ByteBuffer glMapNamedBufferEXT(int arg0, int arg1) {
		return gl2.glMapNamedBufferEXT(arg0, arg1);
	}

	@Override
	public ByteBuffer glMapNamedBufferRangeEXT(int arg0, long arg1, long arg2, int arg3) {
		return gl2.glMapNamedBufferRangeEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glMapParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glMapParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glMapParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMapParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMapParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public void glMapVertexAttrib1dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5,
			double[] arg6, int arg7) {
		gl2.glMapVertexAttrib1dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMapVertexAttrib1dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5,
			DoubleBuffer arg6) {
		gl2.glMapVertexAttrib1dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMapVertexAttrib1fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float[] arg6,
			int arg7) {
		gl2.glMapVertexAttrib1fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMapVertexAttrib1fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5,
			FloatBuffer arg6) {
		gl2.glMapVertexAttrib1fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMapVertexAttrib2dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5, double arg6,
			double arg7, int arg8, int arg9, double[] arg10, int arg11) {
		gl2.glMapVertexAttrib2dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glMapVertexAttrib2dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5, double arg6,
			double arg7, int arg8, int arg9, DoubleBuffer arg10) {
		gl2.glMapVertexAttrib2dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMapVertexAttrib2fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float arg6,
			float arg7, int arg8, int arg9, float[] arg10, int arg11) {
		gl2.glMapVertexAttrib2fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glMapVertexAttrib2fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float arg6,
			float arg7, int arg8, int arg9, FloatBuffer arg10) {
		gl2.glMapVertexAttrib2fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMaterialf(int arg0, int arg1, float arg2) {
		gl2.glMaterialf(arg0, arg1, arg2);
	}

	@Override
	public void glMaterialfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glMaterialfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMaterialfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glMaterialfv(arg0, arg1, arg2);
	}

	@Override
	public void glMateriali(int arg0, int arg1, int arg2) {
		gl2.glMateriali(arg0, arg1, arg2);
	}

	@Override
	public void glMaterialiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMaterialiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMaterialiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMaterialiv(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixFrustumEXT(int arg0, double arg1, double arg2, double arg3, double arg4, double arg5,
			double arg6) {
		gl2.glMatrixFrustumEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMatrixIndexPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glMatrixIndexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixIndexubvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glMatrixIndexubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexubvARB(int arg0, ByteBuffer arg1) {
		gl2.glMatrixIndexubvARB(arg0, arg1);
	}

	@Override
	public void glMatrixIndexuivARB(int arg0, int[] arg1, int arg2) {
		gl2.glMatrixIndexuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexuivARB(int arg0, IntBuffer arg1) {
		gl2.glMatrixIndexuivARB(arg0, arg1);
	}

	@Override
	public void glMatrixIndexusvARB(int arg0, short[] arg1, int arg2) {
		gl2.glMatrixIndexusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexusvARB(int arg0, ShortBuffer arg1) {
		gl2.glMatrixIndexusvARB(arg0, arg1);
	}

	@Override
	public void glMatrixLoadIdentityEXT(int arg0) {
		gl2.glMatrixLoadIdentityEXT(arg0);
	}

	@Override
	public void glMatrixLoadTransposedEXT(int arg0, double[] arg1, int arg2) {
		gl2.glMatrixLoadTransposedEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadTransposedEXT(int arg0, DoubleBuffer arg1) {
		gl2.glMatrixLoadTransposedEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoadTransposefEXT(int arg0, float[] arg1, int arg2) {
		gl2.glMatrixLoadTransposefEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadTransposefEXT(int arg0, FloatBuffer arg1) {
		gl2.glMatrixLoadTransposefEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoaddEXT(int arg0, double[] arg1, int arg2) {
		gl2.glMatrixLoaddEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoaddEXT(int arg0, DoubleBuffer arg1) {
		gl2.glMatrixLoaddEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoadfEXT(int arg0, float[] arg1, int arg2) {
		gl2.glMatrixLoadfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadfEXT(int arg0, FloatBuffer arg1) {
		gl2.glMatrixLoadfEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMode(int arg0) {
		gl2.glMatrixMode(arg0);
	}

	@Override
	public void glMatrixMultTransposedEXT(int arg0, double[] arg1, int arg2) {
		gl2.glMatrixMultTransposedEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultTransposedEXT(int arg0, DoubleBuffer arg1) {
		gl2.glMatrixMultTransposedEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultTransposefEXT(int arg0, float[] arg1, int arg2) {
		gl2.glMatrixMultTransposefEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultTransposefEXT(int arg0, FloatBuffer arg1) {
		gl2.glMatrixMultTransposefEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultdEXT(int arg0, double[] arg1, int arg2) {
		gl2.glMatrixMultdEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultdEXT(int arg0, DoubleBuffer arg1) {
		gl2.glMatrixMultdEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultfEXT(int arg0, float[] arg1, int arg2) {
		gl2.glMatrixMultfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultfEXT(int arg0, FloatBuffer arg1) {
		gl2.glMatrixMultfEXT(arg0, arg1);
	}

	@Override
	public void glMatrixOrthoEXT(int arg0, double arg1, double arg2, double arg3, double arg4, double arg5, double arg6) {
		gl2.glMatrixOrthoEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMatrixPopEXT(int arg0) {
		gl2.glMatrixPopEXT(arg0);
	}

	@Override
	public void glMatrixPushEXT(int arg0) {
		gl2.glMatrixPushEXT(arg0);
	}

	@Override
	public void glMatrixRotatedEXT(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl2.glMatrixRotatedEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMatrixRotatefEXT(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glMatrixRotatefEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMatrixScaledEXT(int arg0, double arg1, double arg2, double arg3) {
		gl2.glMatrixScaledEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixScalefEXT(int arg0, float arg1, float arg2, float arg3) {
		gl2.glMatrixScalefEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixTranslatedEXT(int arg0, double arg1, double arg2, double arg3) {
		gl2.glMatrixTranslatedEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixTranslatefEXT(int arg0, float arg1, float arg2, float arg3) {
		gl2.glMatrixTranslatefEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMemoryBarrier(int arg0) {
		gl2.glMemoryBarrier(arg0);
	}

	@Override
	public void glMinSampleShading(float arg0) {
		gl2.glMinSampleShading(arg0);
	}

	@Override
	public void glMinmax(int arg0, int arg1, boolean arg2) {
		gl2.glMinmax(arg0, arg1, arg2);
	}

	@Override
	public void glMultMatrixd(double[] arg0, int arg1) {
		gl2.glMultMatrixd(arg0, arg1);
	}

	@Override
	public void glMultMatrixd(DoubleBuffer arg0) {
		gl2.glMultMatrixd(arg0);
	}

	@Override
	public void glMultMatrixf(float[] arg0, int arg1) {
		gl2.glMultMatrixf(arg0, arg1);
	}

	@Override
	public void glMultMatrixf(FloatBuffer arg0) {
		gl2.glMultMatrixf(arg0);
	}

	@Override
	public void glMultTransposeMatrixd(double[] arg0, int arg1) {
		gl2.glMultTransposeMatrixd(arg0, arg1);
	}

	@Override
	public void glMultTransposeMatrixd(DoubleBuffer arg0) {
		gl2.glMultTransposeMatrixd(arg0);
	}

	@Override
	public void glMultTransposeMatrixf(float[] arg0, int arg1) {
		gl2.glMultTransposeMatrixf(arg0, arg1);
	}

	@Override
	public void glMultTransposeMatrixf(FloatBuffer arg0) {
		gl2.glMultTransposeMatrixf(arg0);
	}

	@Override
	public void glMultiDrawArrays(int arg0, int[] arg1, int arg2, int[] arg3, int arg4, int arg5) {
		gl2.glMultiDrawArrays(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMultiDrawArrays(int arg0, IntBuffer arg1, IntBuffer arg2, int arg3) {
		gl2.glMultiDrawArrays(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiDrawArraysIndirectAMD(int arg0, Buffer arg1, int arg2, int arg3) {
		gl2.glMultiDrawArraysIndirectAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiDrawElements(int arg0, int[] arg1, int arg2, int arg3, PointerBuffer arg4, int arg5) {
		gl2.glMultiDrawElements(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMultiDrawElements(int arg0, IntBuffer arg1, int arg2, PointerBuffer arg3, int arg4) {
		gl2.glMultiDrawElements(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiDrawElementsIndirectAMD(int arg0, int arg1, Buffer arg2, int arg3, int arg4) {
		gl2.glMultiDrawElementsIndirectAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexBufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glMultiTexBufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord1d(int arg0, double arg1) {
		gl2.glMultiTexCoord1d(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1dv(int arg0, double[] arg1, int arg2) {
		gl2.glMultiTexCoord1dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1dv(int arg0, DoubleBuffer arg1) {
		gl2.glMultiTexCoord1dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1f(int arg0, float arg1) {
		gl2.glMultiTexCoord1f(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1fv(int arg0, float[] arg1, int arg2) {
		gl2.glMultiTexCoord1fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1fv(int arg0, FloatBuffer arg1) {
		gl2.glMultiTexCoord1fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1h(int arg0, short arg1) {
		gl2.glMultiTexCoord1h(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1hv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord1hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1hv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord1hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1i(int arg0, int arg1) {
		gl2.glMultiTexCoord1i(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1iv(int arg0, int[] arg1, int arg2) {
		gl2.glMultiTexCoord1iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1iv(int arg0, IntBuffer arg1) {
		gl2.glMultiTexCoord1iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1s(int arg0, short arg1) {
		gl2.glMultiTexCoord1s(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1sv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord1sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1sv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord1sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2d(int arg0, double arg1, double arg2) {
		gl2.glMultiTexCoord2d(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2dv(int arg0, double[] arg1, int arg2) {
		gl2.glMultiTexCoord2dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2dv(int arg0, DoubleBuffer arg1) {
		gl2.glMultiTexCoord2dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2f(int arg0, float arg1, float arg2) {
		gl2.glMultiTexCoord2f(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2fv(int arg0, float[] arg1, int arg2) {
		gl2.glMultiTexCoord2fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2fv(int arg0, FloatBuffer arg1) {
		gl2.glMultiTexCoord2fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2h(int arg0, short arg1, short arg2) {
		gl2.glMultiTexCoord2h(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2hv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord2hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2hv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord2hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2i(int arg0, int arg1, int arg2) {
		gl2.glMultiTexCoord2i(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2iv(int arg0, int[] arg1, int arg2) {
		gl2.glMultiTexCoord2iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2iv(int arg0, IntBuffer arg1) {
		gl2.glMultiTexCoord2iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2s(int arg0, short arg1, short arg2) {
		gl2.glMultiTexCoord2s(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2sv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord2sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2sv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord2sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3d(int arg0, double arg1, double arg2, double arg3) {
		gl2.glMultiTexCoord3d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3dv(int arg0, double[] arg1, int arg2) {
		gl2.glMultiTexCoord3dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3dv(int arg0, DoubleBuffer arg1) {
		gl2.glMultiTexCoord3dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3f(int arg0, float arg1, float arg2, float arg3) {
		gl2.glMultiTexCoord3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3fv(int arg0, float[] arg1, int arg2) {
		gl2.glMultiTexCoord3fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3fv(int arg0, FloatBuffer arg1) {
		gl2.glMultiTexCoord3fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3h(int arg0, short arg1, short arg2, short arg3) {
		gl2.glMultiTexCoord3h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3hv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord3hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3hv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord3hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glMultiTexCoord3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3iv(int arg0, int[] arg1, int arg2) {
		gl2.glMultiTexCoord3iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3iv(int arg0, IntBuffer arg1) {
		gl2.glMultiTexCoord3iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3s(int arg0, short arg1, short arg2, short arg3) {
		gl2.glMultiTexCoord3s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3sv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord3sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3sv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord3sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4d(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl2.glMultiTexCoord4d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4dv(int arg0, double[] arg1, int arg2) {
		gl2.glMultiTexCoord4dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4dv(int arg0, DoubleBuffer arg1) {
		gl2.glMultiTexCoord4dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glMultiTexCoord4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4fv(int arg0, float[] arg1, int arg2) {
		gl2.glMultiTexCoord4fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4fv(int arg0, FloatBuffer arg1) {
		gl2.glMultiTexCoord4fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4h(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl2.glMultiTexCoord4h(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4hv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord4hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4hv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord4hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glMultiTexCoord4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4iv(int arg0, int[] arg1, int arg2) {
		gl2.glMultiTexCoord4iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4iv(int arg0, IntBuffer arg1) {
		gl2.glMultiTexCoord4iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4s(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl2.glMultiTexCoord4s(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4sv(int arg0, short[] arg1, int arg2) {
		gl2.glMultiTexCoord4sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4sv(int arg0, ShortBuffer arg1) {
		gl2.glMultiTexCoord4sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoordP1ui(int arg0, int arg1, int arg2) {
		gl2.glMultiTexCoordP1ui(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP1uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMultiTexCoordP1uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoordP1uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMultiTexCoordP1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP2ui(int arg0, int arg1, int arg2) {
		gl2.glMultiTexCoordP2ui(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP2uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMultiTexCoordP2uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoordP2uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMultiTexCoordP2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP3ui(int arg0, int arg1, int arg2) {
		gl2.glMultiTexCoordP3ui(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP3uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMultiTexCoordP3uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoordP3uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMultiTexCoordP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP4ui(int arg0, int arg1, int arg2) {
		gl2.glMultiTexCoordP4ui(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordP4uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glMultiTexCoordP4uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoordP4uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glMultiTexCoordP4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoordPointerEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glMultiTexCoordPointerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl2.glMultiTexEnvfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnvfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glMultiTexEnvfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glMultiTexEnvfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnviEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glMultiTexEnviEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnvivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glMultiTexEnvivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glMultiTexEnvivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGendEXT(int arg0, int arg1, int arg2, double arg3) {
		gl2.glMultiTexGendEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGendvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glMultiTexGendvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGendvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glMultiTexGendvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl2.glMultiTexGenfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glMultiTexGenfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGenfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glMultiTexGenfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGeniEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glMultiTexGeniEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glMultiTexGenivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGenivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glMultiTexGenivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl2.glMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl2.glMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMultiTexImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl2.glMultiTexImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMultiTexParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glMultiTexParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glMultiTexParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl2.glMultiTexParameterfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glMultiTexParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glMultiTexParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameteriEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glMultiTexParameteriEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glMultiTexParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glMultiTexParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexRenderbufferEXT(int arg0, int arg1, int arg2) {
		gl2.glMultiTexRenderbufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl2.glMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl2.glMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, Buffer arg11) {
		gl2.glMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glNamedBufferDataEXT(int arg0, long arg1, Buffer arg2, int arg3) {
		gl2.glNamedBufferDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedBufferSubDataEXT(int arg0, long arg1, long arg2, Buffer arg3) {
		gl2.glNamedBufferSubDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedCopyBufferSubDataEXT(int arg0, int arg1, long arg2, long arg3, long arg4) {
		gl2.glNamedCopyBufferSubDataEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferRenderbufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glNamedFramebufferRenderbufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedFramebufferTexture1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glNamedFramebufferTexture1DEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTexture2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glNamedFramebufferTexture2DEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTexture3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glNamedFramebufferTexture3DEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedFramebufferTextureEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glNamedFramebufferTextureEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedFramebufferTextureFaceEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glNamedFramebufferTextureFaceEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTextureLayerEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glNamedFramebufferTextureLayerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4dEXT(int arg0, int arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6) {
		gl2.glNamedProgramLocalParameter4dEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameter4dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glNamedProgramLocalParameter4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glNamedProgramLocalParameter4dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameter4fEXT(int arg0, int arg1, int arg2, float arg3, float arg4, float arg5,
			float arg6) {
		gl2.glNamedProgramLocalParameter4fEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameter4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glNamedProgramLocalParameter4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glNamedProgramLocalParameter4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameterI4iEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glNamedProgramLocalParameterI4iEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameterI4ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glNamedProgramLocalParameterI4ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameterI4ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glNamedProgramLocalParameterI4ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameterI4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glNamedProgramLocalParameterI4uiEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameterI4uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glNamedProgramLocalParameterI4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameterI4uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glNamedProgramLocalParameterI4uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl2.glNamedProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl2.glNamedProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParametersI4ivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glNamedProgramLocalParametersI4ivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParametersI4ivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glNamedProgramLocalParametersI4ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParametersI4uivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glNamedProgramLocalParametersI4uivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParametersI4uivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glNamedProgramLocalParametersI4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramStringEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glNamedProgramStringEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedRenderbufferStorageEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glNamedRenderbufferStorageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedRenderbufferStorageMultisampleCoverageEXT(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		gl2.glNamedRenderbufferStorageMultisampleCoverageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedRenderbufferStorageMultisampleEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glNamedRenderbufferStorageMultisampleEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedStringARB(int arg0, int arg1, String arg2, int arg3, String arg4) {
		gl2.glNamedStringARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNewList(int arg0, int arg1) {
		gl2.glNewList(arg0, arg1);
	}

	@Override
	public void glNormal3b(byte arg0, byte arg1, byte arg2) {
		gl2.glNormal3b(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3bv(byte[] arg0, int arg1) {
		gl2.glNormal3bv(arg0, arg1);
	}

	@Override
	public void glNormal3bv(ByteBuffer arg0) {
		gl2.glNormal3bv(arg0);
	}

	@Override
	public void glNormal3d(double arg0, double arg1, double arg2) {
		gl2.glNormal3d(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3dv(double[] arg0, int arg1) {
		gl2.glNormal3dv(arg0, arg1);
	}

	@Override
	public void glNormal3dv(DoubleBuffer arg0) {
		gl2.glNormal3dv(arg0);
	}

	@Override
	public void glNormal3f(float arg0, float arg1, float arg2) {
		gl2.glNormal3f(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3fv(float[] arg0, int arg1) {
		gl2.glNormal3fv(arg0, arg1);
	}

	@Override
	public void glNormal3fv(FloatBuffer arg0) {
		gl2.glNormal3fv(arg0);
	}

	@Override
	public void glNormal3h(short arg0, short arg1, short arg2) {
		gl2.glNormal3h(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3hv(short[] arg0, int arg1) {
		gl2.glNormal3hv(arg0, arg1);
	}

	@Override
	public void glNormal3hv(ShortBuffer arg0) {
		gl2.glNormal3hv(arg0);
	}

	@Override
	public void glNormal3i(int arg0, int arg1, int arg2) {
		gl2.glNormal3i(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3iv(int[] arg0, int arg1) {
		gl2.glNormal3iv(arg0, arg1);
	}

	@Override
	public void glNormal3iv(IntBuffer arg0) {
		gl2.glNormal3iv(arg0);
	}

	@Override
	public void glNormal3s(short arg0, short arg1, short arg2) {
		gl2.glNormal3s(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3sv(short[] arg0, int arg1) {
		gl2.glNormal3sv(arg0, arg1);
	}

	@Override
	public void glNormal3sv(ShortBuffer arg0) {
		gl2.glNormal3sv(arg0);
	}

	@Override
	public void glNormalFormatNV(int arg0, int arg1) {
		gl2.glNormalFormatNV(arg0, arg1);
	}

	@Override
	public void glNormalP3ui(int arg0, int arg1) {
		gl2.glNormalP3ui(arg0, arg1);
	}

	@Override
	public void glNormalP3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glNormalP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glNormalP3uiv(int arg0, IntBuffer arg1) {
		gl2.glNormalP3uiv(arg0, arg1);
	}

	@Override
	public void glNormalPointer(GLArrayData arg0) {
		gl2.glNormalPointer(arg0);
	}

	@Override
	public void glNormalPointer(int arg0, int arg1, Buffer arg2) {
		gl2.glNormalPointer(arg0, arg1, arg2);
	}

	@Override
	public void glNormalPointer(int arg0, int arg1, long arg2) {
		gl2.glNormalPointer(arg0, arg1, arg2);
	}

	@Override
	public int glObjectPurgeableAPPLE(int arg0, int arg1, int arg2) {
		return gl2.glObjectPurgeableAPPLE(arg0, arg1, arg2);
	}

	@Override
	public int glObjectUnpurgeableAPPLE(int arg0, int arg1, int arg2) {
		return gl2.glObjectUnpurgeableAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glOrtho(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glOrtho(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glOrthof(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glOrthof(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glPNTrianglesfATI(int arg0, float arg1) {
		gl2.glPNTrianglesfATI(arg0, arg1);
	}

	@Override
	public void glPNTrianglesiATI(int arg0, int arg1) {
		gl2.glPNTrianglesiATI(arg0, arg1);
	}

	@Override
	public void glPassThrough(float arg0) {
		gl2.glPassThrough(arg0);
	}

	@Override
	public void glPauseTransformFeedback() {
		gl2.glPauseTransformFeedback();
	}

	@Override
	public void glPauseTransformFeedbackNV() {
		gl2.glPauseTransformFeedbackNV();
	}

	@Override
	public void glPixelDataRangeNV(int arg0, int arg1, Buffer arg2) {
		gl2.glPixelDataRangeNV(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glPixelMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, long arg2) {
		gl2.glPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glPixelMapuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, long arg2) {
		gl2.glPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, long arg2) {
		gl2.glPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glPixelMapusv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelStoref(int arg0, float arg1) {
		gl2.glPixelStoref(arg0, arg1);
	}

	@Override
	public void glPixelStorei(int arg0, int arg1) {
		gl2.glPixelStorei(arg0, arg1);
	}

	@Override
	public void glPixelTransferf(int arg0, float arg1) {
		gl2.glPixelTransferf(arg0, arg1);
	}

	@Override
	public void glPixelTransferi(int arg0, int arg1) {
		gl2.glPixelTransferi(arg0, arg1);
	}

	@Override
	public void glPixelTransformParameterfEXT(int arg0, int arg1, float arg2) {
		gl2.glPixelTransformParameterfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameterfvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glPixelTransformParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelTransformParameterfvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glPixelTransformParameterfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameteriEXT(int arg0, int arg1, int arg2) {
		gl2.glPixelTransformParameteriEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glPixelTransformParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelTransformParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl2.glPixelTransformParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelZoom(float arg0, float arg1) {
		gl2.glPixelZoom(arg0, arg1);
	}

	@Override
	public void glPointParameterf(int arg0, float arg1) {
		gl2.glPointParameterf(arg0, arg1);
	}

	@Override
	public void glPointParameterfv(int arg0, float[] arg1, int arg2) {
		gl2.glPointParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glPointParameterfv(int arg0, FloatBuffer arg1) {
		gl2.glPointParameterfv(arg0, arg1);
	}

	@Override
	public void glPointParameteri(int arg0, int arg1) {
		gl2.glPointParameteri(arg0, arg1);
	}

	@Override
	public void glPointParameteriv(int arg0, int[] arg1, int arg2) {
		gl2.glPointParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glPointParameteriv(int arg0, IntBuffer arg1) {
		gl2.glPointParameteriv(arg0, arg1);
	}

	@Override
	public void glPointSize(float arg0) {
		gl2.glPointSize(arg0);
	}

	@Override
	public void glPolygonMode(int arg0, int arg1) {
		gl2.glPolygonMode(arg0, arg1);
	}

	@Override
	public void glPolygonOffset(float arg0, float arg1) {
		gl2.glPolygonOffset(arg0, arg1);
	}

	@Override
	public void glPolygonStipple(byte[] arg0, int arg1) {
		gl2.glPolygonStipple(arg0, arg1);
	}

	@Override
	public void glPolygonStipple(ByteBuffer arg0) {
		gl2.glPolygonStipple(arg0);
	}

	@Override
	public void glPolygonStipple(long arg0) {
		gl2.glPolygonStipple(arg0);
	}

	@Override
	public void glPopAttrib() {
		gl2.glPopAttrib();
	}

	@Override
	public void glPopClientAttrib() {
		gl2.glPopClientAttrib();
	}

	@Override
	public void glPopMatrix() {
		gl2.glPopMatrix();
	}

	@Override
	public void glPopName() {
		gl2.glPopName();
	}

	@Override
	public void glPrimitiveRestartIndex(int arg0) {
		gl2.glPrimitiveRestartIndex(arg0);
	}

	@Override
	public void glPrimitiveRestartIndexNV(int arg0) {
		gl2.glPrimitiveRestartIndexNV(arg0);
	}

	@Override
	public void glPrimitiveRestartNV() {
		gl2.glPrimitiveRestartNV();
	}

	@Override
	public void glPrioritizeTextures(int arg0, int[] arg1, int arg2, float[] arg3, int arg4) {
		gl2.glPrioritizeTextures(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPrioritizeTextures(int arg0, IntBuffer arg1, FloatBuffer arg2) {
		gl2.glPrioritizeTextures(arg0, arg1, arg2);
	}

	@Override
	public void glProgramBinary(int arg0, int arg1, Buffer arg2, int arg3) {
		gl2.glProgramBinary(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramBufferParametersIivNV(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glProgramBufferParametersIivNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersIivNV(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glProgramBufferParametersIivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramBufferParametersIuivNV(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glProgramBufferParametersIuivNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersIuivNV(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glProgramBufferParametersIuivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramBufferParametersfvNV(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl2.glProgramBufferParametersfvNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersfvNV(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl2.glProgramBufferParametersfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParameter4dARB(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glProgramEnvParameter4dARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameter4dvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glProgramEnvParameter4dvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameter4dvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glProgramEnvParameter4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameter4fARB(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glProgramEnvParameter4fARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameter4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glProgramEnvParameter4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameter4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glProgramEnvParameter4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameterI4iNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramEnvParameterI4iNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameterI4ivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glProgramEnvParameterI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameterI4ivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glProgramEnvParameterI4ivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameterI4uiNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramEnvParameterI4uiNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameterI4uivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glProgramEnvParameterI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameterI4uivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glProgramEnvParameterI4uivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameters4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramEnvParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParameters4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramEnvParameters4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParametersI4ivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramEnvParametersI4ivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParametersI4ivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramEnvParametersI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParametersI4uivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramEnvParametersI4uivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParametersI4uivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramEnvParametersI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4dARB(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glProgramLocalParameter4dARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameter4dvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glProgramLocalParameter4dvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4dvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glProgramLocalParameter4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameter4fARB(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glProgramLocalParameter4fARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameter4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glProgramLocalParameter4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glProgramLocalParameter4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameterI4iNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramLocalParameterI4iNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameterI4ivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glProgramLocalParameterI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameterI4ivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glProgramLocalParameterI4ivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameterI4uiNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramLocalParameterI4uiNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameterI4uivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glProgramLocalParameterI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameterI4uivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glProgramLocalParameterI4uivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParametersI4ivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramLocalParametersI4ivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParametersI4ivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramLocalParametersI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParametersI4uivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramLocalParametersI4uivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParametersI4uivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramLocalParametersI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramParameteri(int arg0, int arg1, int arg2) {
		gl2.glProgramParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glProgramParameteriARB(int arg0, int arg1, int arg2) {
		gl2.glProgramParameteriARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramStringARB(int arg0, int arg1, int arg2, String arg3) {
		gl2.glProgramStringARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramSubroutineParametersuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glProgramSubroutineParametersuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramSubroutineParametersuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl2.glProgramSubroutineParametersuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1d(int arg0, int arg1, double arg2) {
		gl2.glProgramUniform1d(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1dEXT(int arg0, int arg1, double arg2) {
		gl2.glProgramUniform1dEXT(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1dv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform1dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1dv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform1dv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform1dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform1dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1f(int arg0, int arg1, float arg2) {
		gl2.glProgramUniform1f(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1fEXT(int arg0, int arg1, float arg2) {
		gl2.glProgramUniform1fEXT(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1fv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform1fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1fv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform1fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform1fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform1fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1i(int arg0, int arg1, int arg2) {
		gl2.glProgramUniform1i(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1iEXT(int arg0, int arg1, int arg2) {
		gl2.glProgramUniform1iEXT(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1iv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform1iv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1iv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform1iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform1ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform1ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1ui(int arg0, int arg1, int arg2) {
		gl2.glProgramUniform1ui(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1uiEXT(int arg0, int arg1, int arg2) {
		gl2.glProgramUniform1uiEXT(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1uiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform1uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1uiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform1uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform1uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform1uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform1uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2d(int arg0, int arg1, double arg2, double arg3) {
		gl2.glProgramUniform2d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2dEXT(int arg0, int arg1, double arg2, double arg3) {
		gl2.glProgramUniform2dEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2dv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform2dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2dv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform2dv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform2dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform2dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2f(int arg0, int arg1, float arg2, float arg3) {
		gl2.glProgramUniform2f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2fEXT(int arg0, int arg1, float arg2, float arg3) {
		gl2.glProgramUniform2fEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2fv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2fv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform2fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glProgramUniform2i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2iEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glProgramUniform2iEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2iv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform2iv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2iv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform2iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform2ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform2ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2ui(int arg0, int arg1, int arg2, int arg3) {
		gl2.glProgramUniform2ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2uiEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glProgramUniform2uiEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2uiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform2uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2uiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform2uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform2uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform2uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3d(int arg0, int arg1, double arg2, double arg3, double arg4) {
		gl2.glProgramUniform3d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3dEXT(int arg0, int arg1, double arg2, double arg3, double arg4) {
		gl2.glProgramUniform3dEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3dv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform3dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3dv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform3dv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform3dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform3dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3f(int arg0, int arg1, float arg2, float arg3, float arg4) {
		gl2.glProgramUniform3f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3fEXT(int arg0, int arg1, float arg2, float arg3, float arg4) {
		gl2.glProgramUniform3fEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3fv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3fv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform3fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glProgramUniform3i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3iEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glProgramUniform3iEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3iv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform3iv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3iv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform3iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform3ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform3ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3ui(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glProgramUniform3ui(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glProgramUniform3uiEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform3uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform3uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform3uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform3uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4d(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glProgramUniform4d(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4dEXT(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl2.glProgramUniform4dEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4dv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform4dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4dv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform4dv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glProgramUniform4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glProgramUniform4dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4f(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glProgramUniform4f(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4fEXT(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl2.glProgramUniform4fEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4fv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4fv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glProgramUniform4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glProgramUniform4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4i(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramUniform4i(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4iEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramUniform4iEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4iv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform4iv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4iv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform4iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform4ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform4ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4ui(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramUniform4ui(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glProgramUniform4uiEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4uiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform4uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4uiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform4uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glProgramUniform4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glProgramUniform4uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniformMatrix2dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x3dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x3dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x3dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2x3dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x3dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x3dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x3dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2x3dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x3fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x3fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x3fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x3fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2x3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x4dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x4dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x4dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2x4dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x4dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x4dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x4dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix2x4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x4fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x4fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x4fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix2x4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix2x4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x2dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x2dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x2dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3x2dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x2dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x2dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x2dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3x2dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x2fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x2fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x2fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x2fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3x2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x4dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x4dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x4dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3x4dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x4dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x4dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x4dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix3x4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x4fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x4fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x4fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix3x4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix3x4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x2dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x2dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x2dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4x2dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x2dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x2dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x2dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4x2dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x2fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x2fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x2fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x2fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4x2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x3dv(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x3dv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x3dv(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4x3dv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x3dvEXT(int arg0, int arg1, int arg2, boolean arg3, double[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x3dvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x3dvEXT(int arg0, int arg1, int arg2, boolean arg3, DoubleBuffer arg4) {
		gl2.glProgramUniformMatrix4x3dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x3fv(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x3fv(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x3fv(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl2.glProgramUniformMatrix4x3fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl2.glProgramUniformMatrix4x3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformui64NV(int arg0, int arg1, long arg2) {
		gl2.glProgramUniformui64NV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniformui64vNV(int arg0, int arg1, int arg2, long[] arg3, int arg4) {
		gl2.glProgramUniformui64vNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformui64vNV(int arg0, int arg1, int arg2, LongBuffer arg3) {
		gl2.glProgramUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramVertexLimitNV(int arg0, int arg1) {
		gl2.glProgramVertexLimitNV(arg0, arg1);
	}

	@Override
	public void glProvokingVertex(int arg0) {
		gl2.glProvokingVertex(arg0);
	}

	@Override
	public void glProvokingVertexEXT(int arg0) {
		gl2.glProvokingVertexEXT(arg0);
	}

	@Override
	public void glPushAttrib(int arg0) {
		gl2.glPushAttrib(arg0);
	}

	@Override
	public void glPushClientAttrib(int arg0) {
		gl2.glPushClientAttrib(arg0);
	}

	@Override
	public void glPushClientAttribDefaultEXT(int arg0) {
		gl2.glPushClientAttribDefaultEXT(arg0);
	}

	@Override
	public void glPushMatrix() {
		gl2.glPushMatrix();
	}

	@Override
	public void glPushName(int arg0) {
		gl2.glPushName(arg0);
	}

	@Override
	public void glQueryCounter(int arg0, int arg1) {
		gl2.glQueryCounter(arg0, arg1);
	}

	@Override
	public void glRasterPos2d(double arg0, double arg1) {
		gl2.glRasterPos2d(arg0, arg1);
	}

	@Override
	public void glRasterPos2dv(double[] arg0, int arg1) {
		gl2.glRasterPos2dv(arg0, arg1);
	}

	@Override
	public void glRasterPos2dv(DoubleBuffer arg0) {
		gl2.glRasterPos2dv(arg0);
	}

	@Override
	public void glRasterPos2f(float arg0, float arg1) {
		gl2.glRasterPos2f(arg0, arg1);
	}

	@Override
	public void glRasterPos2fv(float[] arg0, int arg1) {
		gl2.glRasterPos2fv(arg0, arg1);
	}

	@Override
	public void glRasterPos2fv(FloatBuffer arg0) {
		gl2.glRasterPos2fv(arg0);
	}

	@Override
	public void glRasterPos2i(int arg0, int arg1) {
		gl2.glRasterPos2i(arg0, arg1);
	}

	@Override
	public void glRasterPos2iv(int[] arg0, int arg1) {
		gl2.glRasterPos2iv(arg0, arg1);
	}

	@Override
	public void glRasterPos2iv(IntBuffer arg0) {
		gl2.glRasterPos2iv(arg0);
	}

	@Override
	public void glRasterPos2s(short arg0, short arg1) {
		gl2.glRasterPos2s(arg0, arg1);
	}

	@Override
	public void glRasterPos2sv(short[] arg0, int arg1) {
		gl2.glRasterPos2sv(arg0, arg1);
	}

	@Override
	public void glRasterPos2sv(ShortBuffer arg0) {
		gl2.glRasterPos2sv(arg0);
	}

	@Override
	public void glRasterPos3d(double arg0, double arg1, double arg2) {
		gl2.glRasterPos3d(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3dv(double[] arg0, int arg1) {
		gl2.glRasterPos3dv(arg0, arg1);
	}

	@Override
	public void glRasterPos3dv(DoubleBuffer arg0) {
		gl2.glRasterPos3dv(arg0);
	}

	@Override
	public void glRasterPos3f(float arg0, float arg1, float arg2) {
		gl2.glRasterPos3f(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3fv(float[] arg0, int arg1) {
		gl2.glRasterPos3fv(arg0, arg1);
	}

	@Override
	public void glRasterPos3fv(FloatBuffer arg0) {
		gl2.glRasterPos3fv(arg0);
	}

	@Override
	public void glRasterPos3i(int arg0, int arg1, int arg2) {
		gl2.glRasterPos3i(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3iv(int[] arg0, int arg1) {
		gl2.glRasterPos3iv(arg0, arg1);
	}

	@Override
	public void glRasterPos3iv(IntBuffer arg0) {
		gl2.glRasterPos3iv(arg0);
	}

	@Override
	public void glRasterPos3s(short arg0, short arg1, short arg2) {
		gl2.glRasterPos3s(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3sv(short[] arg0, int arg1) {
		gl2.glRasterPos3sv(arg0, arg1);
	}

	@Override
	public void glRasterPos3sv(ShortBuffer arg0) {
		gl2.glRasterPos3sv(arg0);
	}

	@Override
	public void glRasterPos4d(double arg0, double arg1, double arg2, double arg3) {
		gl2.glRasterPos4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4dv(double[] arg0, int arg1) {
		gl2.glRasterPos4dv(arg0, arg1);
	}

	@Override
	public void glRasterPos4dv(DoubleBuffer arg0) {
		gl2.glRasterPos4dv(arg0);
	}

	@Override
	public void glRasterPos4f(float arg0, float arg1, float arg2, float arg3) {
		gl2.glRasterPos4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4fv(float[] arg0, int arg1) {
		gl2.glRasterPos4fv(arg0, arg1);
	}

	@Override
	public void glRasterPos4fv(FloatBuffer arg0) {
		gl2.glRasterPos4fv(arg0);
	}

	@Override
	public void glRasterPos4i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glRasterPos4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4iv(int[] arg0, int arg1) {
		gl2.glRasterPos4iv(arg0, arg1);
	}

	@Override
	public void glRasterPos4iv(IntBuffer arg0) {
		gl2.glRasterPos4iv(arg0);
	}

	@Override
	public void glRasterPos4s(short arg0, short arg1, short arg2, short arg3) {
		gl2.glRasterPos4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4sv(short[] arg0, int arg1) {
		gl2.glRasterPos4sv(arg0, arg1);
	}

	@Override
	public void glRasterPos4sv(ShortBuffer arg0) {
		gl2.glRasterPos4sv(arg0);
	}

	@Override
	public void glReadBuffer(int arg0) {
		gl2.glReadBuffer(arg0);
	}

	@Override
	public void glReadPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl2.glReadPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glReadPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl2.glReadPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glReadnPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl2.glReadnPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glRectd(double arg0, double arg1, double arg2, double arg3) {
		gl2.glRectd(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectdv(double[] arg0, int arg1, double[] arg2, int arg3) {
		gl2.glRectdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectdv(DoubleBuffer arg0, DoubleBuffer arg1) {
		gl2.glRectdv(arg0, arg1);
	}

	@Override
	public void glRectf(float arg0, float arg1, float arg2, float arg3) {
		gl2.glRectf(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectfv(float[] arg0, int arg1, float[] arg2, int arg3) {
		gl2.glRectfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectfv(FloatBuffer arg0, FloatBuffer arg1) {
		gl2.glRectfv(arg0, arg1);
	}

	@Override
	public void glRecti(int arg0, int arg1, int arg2, int arg3) {
		gl2.glRecti(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectiv(int[] arg0, int arg1, int[] arg2, int arg3) {
		gl2.glRectiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectiv(IntBuffer arg0, IntBuffer arg1) {
		gl2.glRectiv(arg0, arg1);
	}

	@Override
	public void glRects(short arg0, short arg1, short arg2, short arg3) {
		gl2.glRects(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectsv(short[] arg0, int arg1, short[] arg2, int arg3) {
		gl2.glRectsv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectsv(ShortBuffer arg0, ShortBuffer arg1) {
		gl2.glRectsv(arg0, arg1);
	}

	@Override
	public void glReleaseShaderCompiler() {
		gl2.glReleaseShaderCompiler();
	}

	@Override
	public int glRenderMode(int arg0) {
		return gl2.glRenderMode(arg0);
	}

	@Override
	public void glRenderbufferStorage(int arg0, int arg1, int arg2, int arg3) {
		gl2.glRenderbufferStorage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRenderbufferStorageMultisample(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glRenderbufferStorageMultisample(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glRenderbufferStorageMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glRenderbufferStorageMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glResetHistogram(int arg0) {
		gl2.glResetHistogram(arg0);
	}

	@Override
	public void glResetMinmax(int arg0) {
		gl2.glResetMinmax(arg0);
	}

	@Override
	public void glResumeTransformFeedback() {
		gl2.glResumeTransformFeedback();
	}

	@Override
	public void glResumeTransformFeedbackNV() {
		gl2.glResumeTransformFeedbackNV();
	}

	@Override
	public void glRotated(double arg0, double arg1, double arg2, double arg3) {
		gl2.glRotated(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRotatef(float arg0, float arg1, float arg2, float arg3) {
		gl2.glRotatef(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSampleCoverage(float arg0, boolean arg1) {
		gl2.glSampleCoverage(arg0, arg1);
	}

	@Override
	public void glSampleMaskIndexedNV(int arg0, int arg1) {
		gl2.glSampleMaskIndexedNV(arg0, arg1);
	}

	@Override
	public void glSampleMaski(int arg0, int arg1) {
		gl2.glSampleMaski(arg0, arg1);
	}

	@Override
	public void glSamplerParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glSamplerParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSamplerParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glSamplerParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glSamplerParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glSamplerParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSamplerParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glSamplerParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glSamplerParameterf(int arg0, int arg1, float arg2) {
		gl2.glSamplerParameterf(arg0, arg1, arg2);
	}

	@Override
	public void glSamplerParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glSamplerParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSamplerParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glSamplerParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glSamplerParameteri(int arg0, int arg1, int arg2) {
		gl2.glSamplerParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glSamplerParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glSamplerParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSamplerParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glSamplerParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glScaled(double arg0, double arg1, double arg2) {
		gl2.glScaled(arg0, arg1, arg2);
	}

	@Override
	public void glScalef(float arg0, float arg1, float arg2) {
		gl2.glScalef(arg0, arg1, arg2);
	}

	@Override
	public void glScissor(int arg0, int arg1, int arg2, int arg3) {
		gl2.glScissor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glScissorArrayv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glScissorArrayv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glScissorArrayv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glScissorArrayv(arg0, arg1, arg2);
	}

	@Override
	public void glScissorIndexed(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glScissorIndexed(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glScissorIndexedv(int arg0, int[] arg1, int arg2) {
		gl2.glScissorIndexedv(arg0, arg1, arg2);
	}

	@Override
	public void glScissorIndexedv(int arg0, IntBuffer arg1) {
		gl2.glScissorIndexedv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3b(byte arg0, byte arg1, byte arg2) {
		gl2.glSecondaryColor3b(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3bv(byte[] arg0, int arg1) {
		gl2.glSecondaryColor3bv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3bv(ByteBuffer arg0) {
		gl2.glSecondaryColor3bv(arg0);
	}

	@Override
	public void glSecondaryColor3d(double arg0, double arg1, double arg2) {
		gl2.glSecondaryColor3d(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3dv(double[] arg0, int arg1) {
		gl2.glSecondaryColor3dv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3dv(DoubleBuffer arg0) {
		gl2.glSecondaryColor3dv(arg0);
	}

	@Override
	public void glSecondaryColor3f(float arg0, float arg1, float arg2) {
		gl2.glSecondaryColor3f(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3fv(float[] arg0, int arg1) {
		gl2.glSecondaryColor3fv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3fv(FloatBuffer arg0) {
		gl2.glSecondaryColor3fv(arg0);
	}

	@Override
	public void glSecondaryColor3h(short arg0, short arg1, short arg2) {
		gl2.glSecondaryColor3h(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3hv(short[] arg0, int arg1) {
		gl2.glSecondaryColor3hv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3hv(ShortBuffer arg0) {
		gl2.glSecondaryColor3hv(arg0);
	}

	@Override
	public void glSecondaryColor3i(int arg0, int arg1, int arg2) {
		gl2.glSecondaryColor3i(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3iv(int[] arg0, int arg1) {
		gl2.glSecondaryColor3iv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3iv(IntBuffer arg0) {
		gl2.glSecondaryColor3iv(arg0);
	}

	@Override
	public void glSecondaryColor3s(short arg0, short arg1, short arg2) {
		gl2.glSecondaryColor3s(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3sv(short[] arg0, int arg1) {
		gl2.glSecondaryColor3sv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3sv(ShortBuffer arg0) {
		gl2.glSecondaryColor3sv(arg0);
	}

	@Override
	public void glSecondaryColor3ub(byte arg0, byte arg1, byte arg2) {
		gl2.glSecondaryColor3ub(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3ubv(byte[] arg0, int arg1) {
		gl2.glSecondaryColor3ubv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3ubv(ByteBuffer arg0) {
		gl2.glSecondaryColor3ubv(arg0);
	}

	@Override
	public void glSecondaryColor3ui(int arg0, int arg1, int arg2) {
		gl2.glSecondaryColor3ui(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3uiv(int[] arg0, int arg1) {
		gl2.glSecondaryColor3uiv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3uiv(IntBuffer arg0) {
		gl2.glSecondaryColor3uiv(arg0);
	}

	@Override
	public void glSecondaryColor3us(short arg0, short arg1, short arg2) {
		gl2.glSecondaryColor3us(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3usv(short[] arg0, int arg1) {
		gl2.glSecondaryColor3usv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3usv(ShortBuffer arg0) {
		gl2.glSecondaryColor3usv(arg0);
	}

	@Override
	public void glSecondaryColorFormatNV(int arg0, int arg1, int arg2) {
		gl2.glSecondaryColorFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColorP3ui(int arg0, int arg1) {
		gl2.glSecondaryColorP3ui(arg0, arg1);
	}

	@Override
	public void glSecondaryColorP3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glSecondaryColorP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColorP3uiv(int arg0, IntBuffer arg1) {
		gl2.glSecondaryColorP3uiv(arg0, arg1);
	}

	@Override
	public void glSecondaryColorPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glSecondaryColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSecondaryColorPointer(int arg0, int arg1, int arg2, long arg3) {
		gl2.glSecondaryColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSelectBuffer(int arg0, IntBuffer arg1) {
		gl2.glSelectBuffer(arg0, arg1);
	}

	@Override
	public void glSelectPerfMonitorCountersAMD(int arg0, boolean arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl2.glSelectPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glSelectPerfMonitorCountersAMD(int arg0, boolean arg1, int arg2, int arg3, IntBuffer arg4) {
		gl2.glSelectPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glSeparableFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6, Buffer arg7) {
		gl2.glSeparableFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glSeparableFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6, long arg7) {
		gl2.glSeparableFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glSetFenceAPPLE(int arg0) {
		gl2.glSetFenceAPPLE(arg0);
	}

	@Override
	public void glSetFenceNV(int arg0, int arg1) {
		gl2.glSetFenceNV(arg0, arg1);
	}

	@Override
	public void glSetInvariantEXT(int arg0, int arg1, Buffer arg2) {
		gl2.glSetInvariantEXT(arg0, arg1, arg2);
	}

	@Override
	public void glSetLocalConstantEXT(int arg0, int arg1, Buffer arg2) {
		gl2.glSetLocalConstantEXT(arg0, arg1, arg2);
	}

	@Override
	public void glSetMultisamplefvAMD(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glSetMultisamplefvAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSetMultisamplefvAMD(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glSetMultisamplefvAMD(arg0, arg1, arg2);
	}

	@Override
	public void glShadeModel(int arg0) {
		gl2.glShadeModel(arg0);
	}

	@Override
	public void glShaderBinary(int arg0, int[] arg1, int arg2, int arg3, Buffer arg4, int arg5) {
		gl2.glShaderBinary(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glShaderBinary(int arg0, IntBuffer arg1, int arg2, Buffer arg3, int arg4) {
		gl2.glShaderBinary(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderOp1EXT(int arg0, int arg1, int arg2) {
		gl2.glShaderOp1EXT(arg0, arg1, arg2);
	}

	@Override
	public void glShaderOp2EXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glShaderOp2EXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glShaderOp3EXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glShaderOp3EXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSource(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl2.glShaderSource(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSource(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl2.glShaderSource(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glShaderSourceARB(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl2.glShaderSourceARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSourceARB(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl2.glShaderSourceARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilClearTagEXT(int arg0, int arg1) {
		gl2.glStencilClearTagEXT(arg0, arg1);
	}

	@Override
	public void glStencilFunc(int arg0, int arg1, int arg2) {
		gl2.glStencilFunc(arg0, arg1, arg2);
	}

	@Override
	public void glStencilFuncSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl2.glStencilFuncSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilMask(int arg0) {
		gl2.glStencilMask(arg0);
	}

	@Override
	public void glStencilMaskSeparate(int arg0, int arg1) {
		gl2.glStencilMaskSeparate(arg0, arg1);
	}

	@Override
	public void glStencilOp(int arg0, int arg1, int arg2) {
		gl2.glStencilOp(arg0, arg1, arg2);
	}

	@Override
	public void glStencilOpSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl2.glStencilOpSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilOpValueAMD(int arg0, int arg1) {
		gl2.glStencilOpValueAMD(arg0, arg1);
	}

	@Override
	public void glStringMarkerGREMEDY(int arg0, Buffer arg1) {
		gl2.glStringMarkerGREMEDY(arg0, arg1);
	}

	@Override
	public void glSwapAPPLE() {
		gl2.glSwapAPPLE();
	}

	@Override
	public void glSwizzleEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glSwizzleEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTessellationFactorAMD(float arg0) {
		gl2.glTessellationFactorAMD(arg0);
	}

	@Override
	public void glTessellationModeAMD(int arg0) {
		gl2.glTessellationModeAMD(arg0);
	}

	@Override
	public boolean glTestFenceAPPLE(int arg0) {
		return gl2.glTestFenceAPPLE(arg0);
	}

	@Override
	public boolean glTestFenceNV(int arg0) {
		return gl2.glTestFenceNV(arg0);
	}

	@Override
	public boolean glTestObjectAPPLE(int arg0, int arg1) {
		return gl2.glTestObjectAPPLE(arg0, arg1);
	}

	@Override
	public void glTexBuffer(int arg0, int arg1, int arg2) {
		gl2.glTexBuffer(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord1d(double arg0) {
		gl2.glTexCoord1d(arg0);
	}

	@Override
	public void glTexCoord1dv(double[] arg0, int arg1) {
		gl2.glTexCoord1dv(arg0, arg1);
	}

	@Override
	public void glTexCoord1dv(DoubleBuffer arg0) {
		gl2.glTexCoord1dv(arg0);
	}

	@Override
	public void glTexCoord1f(float arg0) {
		gl2.glTexCoord1f(arg0);
	}

	@Override
	public void glTexCoord1fv(float[] arg0, int arg1) {
		gl2.glTexCoord1fv(arg0, arg1);
	}

	@Override
	public void glTexCoord1fv(FloatBuffer arg0) {
		gl2.glTexCoord1fv(arg0);
	}

	@Override
	public void glTexCoord1h(short arg0) {
		gl2.glTexCoord1h(arg0);
	}

	@Override
	public void glTexCoord1hv(short[] arg0, int arg1) {
		gl2.glTexCoord1hv(arg0, arg1);
	}

	@Override
	public void glTexCoord1hv(ShortBuffer arg0) {
		gl2.glTexCoord1hv(arg0);
	}

	@Override
	public void glTexCoord1i(int arg0) {
		gl2.glTexCoord1i(arg0);
	}

	@Override
	public void glTexCoord1iv(int[] arg0, int arg1) {
		gl2.glTexCoord1iv(arg0, arg1);
	}

	@Override
	public void glTexCoord1iv(IntBuffer arg0) {
		gl2.glTexCoord1iv(arg0);
	}

	@Override
	public void glTexCoord1s(short arg0) {
		gl2.glTexCoord1s(arg0);
	}

	@Override
	public void glTexCoord1sv(short[] arg0, int arg1) {
		gl2.glTexCoord1sv(arg0, arg1);
	}

	@Override
	public void glTexCoord1sv(ShortBuffer arg0) {
		gl2.glTexCoord1sv(arg0);
	}

	@Override
	public void glTexCoord2d(double arg0, double arg1) {
		gl2.glTexCoord2d(arg0, arg1);
	}

	@Override
	public void glTexCoord2dv(double[] arg0, int arg1) {
		gl2.glTexCoord2dv(arg0, arg1);
	}

	@Override
	public void glTexCoord2dv(DoubleBuffer arg0) {
		gl2.glTexCoord2dv(arg0);
	}

	@Override
	public void glTexCoord2f(float arg0, float arg1) {
		gl2.glTexCoord2f(arg0, arg1);
	}

	@Override
	public void glTexCoord2fv(float[] arg0, int arg1) {
		gl2.glTexCoord2fv(arg0, arg1);
	}

	@Override
	public void glTexCoord2fv(FloatBuffer arg0) {
		gl2.glTexCoord2fv(arg0);
	}

	@Override
	public void glTexCoord2h(short arg0, short arg1) {
		gl2.glTexCoord2h(arg0, arg1);
	}

	@Override
	public void glTexCoord2hv(short[] arg0, int arg1) {
		gl2.glTexCoord2hv(arg0, arg1);
	}

	@Override
	public void glTexCoord2hv(ShortBuffer arg0) {
		gl2.glTexCoord2hv(arg0);
	}

	@Override
	public void glTexCoord2i(int arg0, int arg1) {
		gl2.glTexCoord2i(arg0, arg1);
	}

	@Override
	public void glTexCoord2iv(int[] arg0, int arg1) {
		gl2.glTexCoord2iv(arg0, arg1);
	}

	@Override
	public void glTexCoord2iv(IntBuffer arg0) {
		gl2.glTexCoord2iv(arg0);
	}

	@Override
	public void glTexCoord2s(short arg0, short arg1) {
		gl2.glTexCoord2s(arg0, arg1);
	}

	@Override
	public void glTexCoord2sv(short[] arg0, int arg1) {
		gl2.glTexCoord2sv(arg0, arg1);
	}

	@Override
	public void glTexCoord2sv(ShortBuffer arg0) {
		gl2.glTexCoord2sv(arg0);
	}

	@Override
	public void glTexCoord3d(double arg0, double arg1, double arg2) {
		gl2.glTexCoord3d(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3dv(double[] arg0, int arg1) {
		gl2.glTexCoord3dv(arg0, arg1);
	}

	@Override
	public void glTexCoord3dv(DoubleBuffer arg0) {
		gl2.glTexCoord3dv(arg0);
	}

	@Override
	public void glTexCoord3f(float arg0, float arg1, float arg2) {
		gl2.glTexCoord3f(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3fv(float[] arg0, int arg1) {
		gl2.glTexCoord3fv(arg0, arg1);
	}

	@Override
	public void glTexCoord3fv(FloatBuffer arg0) {
		gl2.glTexCoord3fv(arg0);
	}

	@Override
	public void glTexCoord3h(short arg0, short arg1, short arg2) {
		gl2.glTexCoord3h(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3hv(short[] arg0, int arg1) {
		gl2.glTexCoord3hv(arg0, arg1);
	}

	@Override
	public void glTexCoord3hv(ShortBuffer arg0) {
		gl2.glTexCoord3hv(arg0);
	}

	@Override
	public void glTexCoord3i(int arg0, int arg1, int arg2) {
		gl2.glTexCoord3i(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3iv(int[] arg0, int arg1) {
		gl2.glTexCoord3iv(arg0, arg1);
	}

	@Override
	public void glTexCoord3iv(IntBuffer arg0) {
		gl2.glTexCoord3iv(arg0);
	}

	@Override
	public void glTexCoord3s(short arg0, short arg1, short arg2) {
		gl2.glTexCoord3s(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3sv(short[] arg0, int arg1) {
		gl2.glTexCoord3sv(arg0, arg1);
	}

	@Override
	public void glTexCoord3sv(ShortBuffer arg0) {
		gl2.glTexCoord3sv(arg0);
	}

	@Override
	public void glTexCoord4d(double arg0, double arg1, double arg2, double arg3) {
		gl2.glTexCoord4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4dv(double[] arg0, int arg1) {
		gl2.glTexCoord4dv(arg0, arg1);
	}

	@Override
	public void glTexCoord4dv(DoubleBuffer arg0) {
		gl2.glTexCoord4dv(arg0);
	}

	@Override
	public void glTexCoord4f(float arg0, float arg1, float arg2, float arg3) {
		gl2.glTexCoord4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4fv(float[] arg0, int arg1) {
		gl2.glTexCoord4fv(arg0, arg1);
	}

	@Override
	public void glTexCoord4fv(FloatBuffer arg0) {
		gl2.glTexCoord4fv(arg0);
	}

	@Override
	public void glTexCoord4h(short arg0, short arg1, short arg2, short arg3) {
		gl2.glTexCoord4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4hv(short[] arg0, int arg1) {
		gl2.glTexCoord4hv(arg0, arg1);
	}

	@Override
	public void glTexCoord4hv(ShortBuffer arg0) {
		gl2.glTexCoord4hv(arg0);
	}

	@Override
	public void glTexCoord4i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glTexCoord4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4iv(int[] arg0, int arg1) {
		gl2.glTexCoord4iv(arg0, arg1);
	}

	@Override
	public void glTexCoord4iv(IntBuffer arg0) {
		gl2.glTexCoord4iv(arg0);
	}

	@Override
	public void glTexCoord4s(short arg0, short arg1, short arg2, short arg3) {
		gl2.glTexCoord4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4sv(short[] arg0, int arg1) {
		gl2.glTexCoord4sv(arg0, arg1);
	}

	@Override
	public void glTexCoord4sv(ShortBuffer arg0) {
		gl2.glTexCoord4sv(arg0);
	}

	@Override
	public void glTexCoordFormatNV(int arg0, int arg1, int arg2) {
		gl2.glTexCoordFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordP1ui(int arg0, int arg1) {
		gl2.glTexCoordP1ui(arg0, arg1);
	}

	@Override
	public void glTexCoordP1uiv(int arg0, int[] arg1, int arg2) {
		gl2.glTexCoordP1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordP1uiv(int arg0, IntBuffer arg1) {
		gl2.glTexCoordP1uiv(arg0, arg1);
	}

	@Override
	public void glTexCoordP2ui(int arg0, int arg1) {
		gl2.glTexCoordP2ui(arg0, arg1);
	}

	@Override
	public void glTexCoordP2uiv(int arg0, int[] arg1, int arg2) {
		gl2.glTexCoordP2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordP2uiv(int arg0, IntBuffer arg1) {
		gl2.glTexCoordP2uiv(arg0, arg1);
	}

	@Override
	public void glTexCoordP3ui(int arg0, int arg1) {
		gl2.glTexCoordP3ui(arg0, arg1);
	}

	@Override
	public void glTexCoordP3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glTexCoordP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordP3uiv(int arg0, IntBuffer arg1) {
		gl2.glTexCoordP3uiv(arg0, arg1);
	}

	@Override
	public void glTexCoordP4ui(int arg0, int arg1) {
		gl2.glTexCoordP4ui(arg0, arg1);
	}

	@Override
	public void glTexCoordP4uiv(int arg0, int[] arg1, int arg2) {
		gl2.glTexCoordP4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordP4uiv(int arg0, IntBuffer arg1) {
		gl2.glTexCoordP4uiv(arg0, arg1);
	}

	@Override
	public void glTexCoordPointer(GLArrayData arg0) {
		gl2.glTexCoordPointer(arg0);
	}

	@Override
	public void glTexCoordPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glTexCoordPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoordPointer(int arg0, int arg1, int arg2, long arg3) {
		gl2.glTexCoordPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnvf(int arg0, int arg1, float arg2) {
		gl2.glTexEnvf(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnvfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glTexEnvfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnvfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glTexEnvfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnvi(int arg0, int arg1, int arg2) {
		gl2.glTexEnvi(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnviv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glTexEnviv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnviv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glTexEnviv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGend(int arg0, int arg1, double arg2) {
		gl2.glTexGend(arg0, arg1, arg2);
	}

	@Override
	public void glTexGendv(int arg0, int arg1, double[] arg2, int arg3) {
		gl2.glTexGendv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGendv(int arg0, int arg1, DoubleBuffer arg2) {
		gl2.glTexGendv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGenf(int arg0, int arg1, float arg2) {
		gl2.glTexGenf(arg0, arg1, arg2);
	}

	@Override
	public void glTexGenfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glTexGenfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGenfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glTexGenfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGeni(int arg0, int arg1, int arg2) {
		gl2.glTexGeni(arg0, arg1, arg2);
	}

	@Override
	public void glTexGeniv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glTexGeniv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGeniv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glTexGeniv(arg0, arg1, arg2);
	}

	@Override
	public void glTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl2.glTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, long arg7) {
		gl2.glTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8) {
		gl2.glTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, long arg8) {
		gl2.glTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexImage2DMultisample(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5) {
		gl2.glTexImage2DMultisample(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTexImage2DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			boolean arg6) {
		gl2.glTexImage2DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			Buffer arg9) {
		gl2.glTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			long arg9) {
		gl2.glTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTexImage3DMultisample(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
		gl2.glTexImage3DMultisample(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexImage3DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7) {
		gl2.glTexImage3DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glTexParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glTexParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glTexParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glTexParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterf(int arg0, int arg1, float arg2) {
		gl2.glTexParameterf(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glTexParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glTexParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameteri(int arg0, int arg1, int arg2) {
		gl2.glTexParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glTexParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glTexParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glTexRenderbufferNV(int arg0, int arg1) {
		gl2.glTexRenderbufferNV(arg0, arg1);
	}

	@Override
	public void glTexStorage1D(int arg0, int arg1, int arg2, int arg3) {
		gl2.glTexStorage1D(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexStorage2D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glTexStorage2D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTexStorage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glTexStorage3D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl2.glTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl2.glTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl2.glTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			long arg8) {
		gl2.glTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl2.glTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, long arg10) {
		gl2.glTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTextureBarrierNV() {
		gl2.glTextureBarrierNV();
	}

	@Override
	public void glTextureBufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glTextureBufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl2.glTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl2.glTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTextureImage2DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			int arg6, boolean arg7) {
		gl2.glTextureImage2DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureImage2DMultisampleNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
		gl2.glTextureImage2DMultisampleNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTextureImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl2.glTextureImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTextureImage3DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			int arg6, int arg7, boolean arg8) {
		gl2.glTextureImage3DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTextureImage3DMultisampleNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7) {
		gl2.glTextureImage3DMultisampleNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureLightEXT(int arg0) {
		gl2.glTextureLightEXT(arg0);
	}

	@Override
	public void glTextureMaterialEXT(int arg0, int arg1) {
		gl2.glTextureMaterialEXT(arg0, arg1);
	}

	@Override
	public void glTextureNormalEXT(int arg0) {
		gl2.glTextureNormalEXT(arg0);
	}

	@Override
	public void glTextureParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glTextureParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glTextureParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glTextureParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glTextureParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl2.glTextureParameterfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glTextureParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glTextureParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameteriEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glTextureParameteriEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glTextureParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glTextureParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureRangeAPPLE(int arg0, int arg1, Buffer arg2) {
		gl2.glTextureRangeAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glTextureRenderbufferEXT(int arg0, int arg1, int arg2) {
		gl2.glTextureRenderbufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glTextureStorage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glTextureStorage1DEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureStorage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glTextureStorage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTextureStorage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl2.glTextureStorage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl2.glTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl2.glTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, Buffer arg11) {
		gl2.glTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glTransformFeedbackVaryings(int arg0, int arg1, String[] arg2, int arg3) {
		gl2.glTransformFeedbackVaryings(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTranslated(double arg0, double arg1, double arg2) {
		gl2.glTranslated(arg0, arg1, arg2);
	}

	@Override
	public void glTranslatef(float arg0, float arg1, float arg2) {
		gl2.glTranslatef(arg0, arg1, arg2);
	}

	@Override
	public void glUniform(GLUniformData arg0) {
		gl2.glUniform(arg0);
	}

	@Override
	public void glUniform1f(int arg0, float arg1) {
		gl2.glUniform1f(arg0, arg1);
	}

	@Override
	public void glUniform1fARB(int arg0, float arg1) {
		gl2.glUniform1fARB(arg0, arg1);
	}

	@Override
	public void glUniform1fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform1fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1fv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform1fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform1fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform1fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1i(int arg0, int arg1) {
		gl2.glUniform1i(arg0, arg1);
	}

	@Override
	public void glUniform1iARB(int arg0, int arg1) {
		gl2.glUniform1iARB(arg0, arg1);
	}

	@Override
	public void glUniform1iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform1iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1iv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform1iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform1ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform1ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1ui(int arg0, int arg1) {
		gl2.glUniform1ui(arg0, arg1);
	}

	@Override
	public void glUniform1uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform1uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2f(int arg0, float arg1, float arg2) {
		gl2.glUniform2f(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fARB(int arg0, float arg1, float arg2) {
		gl2.glUniform2fARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2fv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform2fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform2fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform2fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2i(int arg0, int arg1, int arg2) {
		gl2.glUniform2i(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2iARB(int arg0, int arg1, int arg2) {
		gl2.glUniform2iARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform2iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2iv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform2iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform2ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform2ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2ui(int arg0, int arg1, int arg2) {
		gl2.glUniform2ui(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform2uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3f(int arg0, float arg1, float arg2, float arg3) {
		gl2.glUniform3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fARB(int arg0, float arg1, float arg2, float arg3) {
		gl2.glUniform3fARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform3fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform3fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform3fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glUniform3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iARB(int arg0, int arg1, int arg2, int arg3) {
		gl2.glUniform3iARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform3iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform3iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform3ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform3ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3ui(int arg0, int arg1, int arg2, int arg3) {
		gl2.glUniform3ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform3uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glUniform4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4fARB(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glUniform4fARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4fv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform4fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glUniform4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glUniform4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glUniform4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4iARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glUniform4iARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform4iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4iv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform4iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform4ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform4ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4ui(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glUniform4ui(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniform4uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4uiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniform4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniformBlockBinding(int arg0, int arg1, int arg2) {
		gl2.glUniformBlockBinding(arg0, arg1, arg2);
	}

	@Override
	public void glUniformBufferEXT(int arg0, int arg1, int arg2) {
		gl2.glUniformBufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glUniformMatrix2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix2fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix2fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2x3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix2x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2x3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix2x3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2x4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix2x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2x4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix2x4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix3fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix3fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3x2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix3x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3x2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix3x2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3x4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix3x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3x4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix3x4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix4fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4x2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix4x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4x2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix4x2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4x3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl2.glUniformMatrix4x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4x3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl2.glUniformMatrix4x3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformSubroutinesuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl2.glUniformSubroutinesuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformSubroutinesuiv(int arg0, int arg1, IntBuffer arg2) {
		gl2.glUniformSubroutinesuiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniformui64NV(int arg0, long arg1) {
		gl2.glUniformui64NV(arg0, arg1);
	}

	@Override
	public void glUniformui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl2.glUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl2.glUniformui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glUnlockArraysEXT() {
		gl2.glUnlockArraysEXT();
	}

	@Override
	public boolean glUnmapBuffer(int arg0) {
		return gl2.glUnmapBuffer(arg0);
	}

	@Override
	public boolean glUnmapNamedBufferEXT(int arg0) {
		return gl2.glUnmapNamedBufferEXT(arg0);
	}

	@Override
	public void glUseProgram(int arg0) {
		gl2.glUseProgram(arg0);
	}

	@Override
	public void glUseProgramObjectARB(int arg0) {
		gl2.glUseProgramObjectARB(arg0);
	}

	@Override
	public void glUseProgramStages(int arg0, int arg1, int arg2) {
		gl2.glUseProgramStages(arg0, arg1, arg2);
	}

	@Override
	public void glVDPAUFiniNV() {
		gl2.glVDPAUFiniNV();
	}

	@Override
	public void glVDPAUGetSurfaceivNV(long arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6) {
		gl2.glVDPAUGetSurfaceivNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glVDPAUGetSurfaceivNV(long arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4) {
		gl2.glVDPAUGetSurfaceivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVDPAUInitNV(Buffer arg0, Buffer arg1) {
		gl2.glVDPAUInitNV(arg0, arg1);
	}

	@Override
	public void glVDPAUIsSurfaceNV(long arg0) {
		gl2.glVDPAUIsSurfaceNV(arg0);
	}

	@Override
	public void glVDPAUMapSurfacesNV(int arg0, long[] arg1, int arg2) {
		gl2.glVDPAUMapSurfacesNV(arg0, arg1, arg2);
	}

	@Override
	public void glVDPAUMapSurfacesNV(int arg0, LongBuffer arg1) {
		gl2.glVDPAUMapSurfacesNV(arg0, arg1);
	}

	@Override
	public long glVDPAURegisterOutputSurfaceNV(Buffer arg0, int arg1, int arg2, int[] arg3, int arg4) {
		return gl2.glVDPAURegisterOutputSurfaceNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public long glVDPAURegisterOutputSurfaceNV(Buffer arg0, int arg1, int arg2, IntBuffer arg3) {
		return gl2.glVDPAURegisterOutputSurfaceNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public long glVDPAURegisterVideoSurfaceNV(Buffer arg0, int arg1, int arg2, int[] arg3, int arg4) {
		return gl2.glVDPAURegisterVideoSurfaceNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public long glVDPAURegisterVideoSurfaceNV(Buffer arg0, int arg1, int arg2, IntBuffer arg3) {
		return gl2.glVDPAURegisterVideoSurfaceNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVDPAUSurfaceAccessNV(long arg0, int arg1) {
		gl2.glVDPAUSurfaceAccessNV(arg0, arg1);
	}

	@Override
	public void glVDPAUUnmapSurfacesNV(int arg0, long[] arg1, int arg2) {
		gl2.glVDPAUUnmapSurfacesNV(arg0, arg1, arg2);
	}

	@Override
	public void glVDPAUUnmapSurfacesNV(int arg0, LongBuffer arg1) {
		gl2.glVDPAUUnmapSurfacesNV(arg0, arg1);
	}

	@Override
	public void glVDPAUUnregisterSurfaceNV(long arg0) {
		gl2.glVDPAUUnregisterSurfaceNV(arg0);
	}

	@Override
	public void glValidateProgram(int arg0) {
		gl2.glValidateProgram(arg0);
	}

	@Override
	public void glValidateProgramARB(int arg0) {
		gl2.glValidateProgramARB(arg0);
	}

	@Override
	public void glValidateProgramPipeline(int arg0) {
		gl2.glValidateProgramPipeline(arg0);
	}

	@Override
	public void glVariantPointerEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glVariantPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVariantPointerEXT(int arg0, int arg1, int arg2, long arg3) {
		gl2.glVariantPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVariantbvEXT(int arg0, byte[] arg1, int arg2) {
		gl2.glVariantbvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantbvEXT(int arg0, ByteBuffer arg1) {
		gl2.glVariantbvEXT(arg0, arg1);
	}

	@Override
	public void glVariantdvEXT(int arg0, double[] arg1, int arg2) {
		gl2.glVariantdvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantdvEXT(int arg0, DoubleBuffer arg1) {
		gl2.glVariantdvEXT(arg0, arg1);
	}

	@Override
	public void glVariantfvEXT(int arg0, float[] arg1, int arg2) {
		gl2.glVariantfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantfvEXT(int arg0, FloatBuffer arg1) {
		gl2.glVariantfvEXT(arg0, arg1);
	}

	@Override
	public void glVariantivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVariantivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantivEXT(int arg0, IntBuffer arg1) {
		gl2.glVariantivEXT(arg0, arg1);
	}

	@Override
	public void glVariantsvEXT(int arg0, short[] arg1, int arg2) {
		gl2.glVariantsvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantsvEXT(int arg0, ShortBuffer arg1) {
		gl2.glVariantsvEXT(arg0, arg1);
	}

	@Override
	public void glVariantubvEXT(int arg0, byte[] arg1, int arg2) {
		gl2.glVariantubvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantubvEXT(int arg0, ByteBuffer arg1) {
		gl2.glVariantubvEXT(arg0, arg1);
	}

	@Override
	public void glVariantuivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVariantuivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantuivEXT(int arg0, IntBuffer arg1) {
		gl2.glVariantuivEXT(arg0, arg1);
	}

	@Override
	public void glVariantusvEXT(int arg0, short[] arg1, int arg2) {
		gl2.glVariantusvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantusvEXT(int arg0, ShortBuffer arg1) {
		gl2.glVariantusvEXT(arg0, arg1);
	}

	@Override
	public void glVertex2d(double arg0, double arg1) {
		gl2.glVertex2d(arg0, arg1);
	}

	@Override
	public void glVertex2dv(double[] arg0, int arg1) {
		gl2.glVertex2dv(arg0, arg1);
	}

	@Override
	public void glVertex2dv(DoubleBuffer arg0) {
		gl2.glVertex2dv(arg0);
	}

	@Override
	public void glVertex2f(float arg0, float arg1) {
		gl2.glVertex2f(arg0, arg1);
	}

	@Override
	public void glVertex2fv(float[] arg0, int arg1) {
		gl2.glVertex2fv(arg0, arg1);
	}

	@Override
	public void glVertex2fv(FloatBuffer arg0) {
		gl2.glVertex2fv(arg0);
	}

	@Override
	public void glVertex2h(short arg0, short arg1) {
		gl2.glVertex2h(arg0, arg1);
	}

	@Override
	public void glVertex2hv(short[] arg0, int arg1) {
		gl2.glVertex2hv(arg0, arg1);
	}

	@Override
	public void glVertex2hv(ShortBuffer arg0) {
		gl2.glVertex2hv(arg0);
	}

	@Override
	public void glVertex2i(int arg0, int arg1) {
		gl2.glVertex2i(arg0, arg1);
	}

	@Override
	public void glVertex2iv(int[] arg0, int arg1) {
		gl2.glVertex2iv(arg0, arg1);
	}

	@Override
	public void glVertex2iv(IntBuffer arg0) {
		gl2.glVertex2iv(arg0);
	}

	@Override
	public void glVertex2s(short arg0, short arg1) {
		gl2.glVertex2s(arg0, arg1);
	}

	@Override
	public void glVertex2sv(short[] arg0, int arg1) {
		gl2.glVertex2sv(arg0, arg1);
	}

	@Override
	public void glVertex2sv(ShortBuffer arg0) {
		gl2.glVertex2sv(arg0);
	}

	@Override
	public void glVertex3d(double arg0, double arg1, double arg2) {
		gl2.glVertex3d(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3dv(double[] arg0, int arg1) {
		gl2.glVertex3dv(arg0, arg1);
	}

	@Override
	public void glVertex3dv(DoubleBuffer arg0) {
		gl2.glVertex3dv(arg0);
	}

	@Override
	public void glVertex3f(float arg0, float arg1, float arg2) {
		gl2.glVertex3f(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3fv(float[] arg0, int arg1) {
		gl2.glVertex3fv(arg0, arg1);
	}

	@Override
	public void glVertex3fv(FloatBuffer arg0) {
		gl2.glVertex3fv(arg0);
	}

	@Override
	public void glVertex3h(short arg0, short arg1, short arg2) {
		gl2.glVertex3h(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3hv(short[] arg0, int arg1) {
		gl2.glVertex3hv(arg0, arg1);
	}

	@Override
	public void glVertex3hv(ShortBuffer arg0) {
		gl2.glVertex3hv(arg0);
	}

	@Override
	public void glVertex3i(int arg0, int arg1, int arg2) {
		gl2.glVertex3i(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3iv(int[] arg0, int arg1) {
		gl2.glVertex3iv(arg0, arg1);
	}

	@Override
	public void glVertex3iv(IntBuffer arg0) {
		gl2.glVertex3iv(arg0);
	}

	@Override
	public void glVertex3s(short arg0, short arg1, short arg2) {
		gl2.glVertex3s(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3sv(short[] arg0, int arg1) {
		gl2.glVertex3sv(arg0, arg1);
	}

	@Override
	public void glVertex3sv(ShortBuffer arg0) {
		gl2.glVertex3sv(arg0);
	}

	@Override
	public void glVertex4d(double arg0, double arg1, double arg2, double arg3) {
		gl2.glVertex4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4dv(double[] arg0, int arg1) {
		gl2.glVertex4dv(arg0, arg1);
	}

	@Override
	public void glVertex4dv(DoubleBuffer arg0) {
		gl2.glVertex4dv(arg0);
	}

	@Override
	public void glVertex4f(float arg0, float arg1, float arg2, float arg3) {
		gl2.glVertex4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4fv(float[] arg0, int arg1) {
		gl2.glVertex4fv(arg0, arg1);
	}

	@Override
	public void glVertex4fv(FloatBuffer arg0) {
		gl2.glVertex4fv(arg0);
	}

	@Override
	public void glVertex4h(short arg0, short arg1, short arg2, short arg3) {
		gl2.glVertex4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4hv(short[] arg0, int arg1) {
		gl2.glVertex4hv(arg0, arg1);
	}

	@Override
	public void glVertex4hv(ShortBuffer arg0) {
		gl2.glVertex4hv(arg0);
	}

	@Override
	public void glVertex4i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertex4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4iv(int[] arg0, int arg1) {
		gl2.glVertex4iv(arg0, arg1);
	}

	@Override
	public void glVertex4iv(IntBuffer arg0) {
		gl2.glVertex4iv(arg0);
	}

	@Override
	public void glVertex4s(short arg0, short arg1, short arg2, short arg3) {
		gl2.glVertex4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4sv(short[] arg0, int arg1) {
		gl2.glVertex4sv(arg0, arg1);
	}

	@Override
	public void glVertex4sv(ShortBuffer arg0) {
		gl2.glVertex4sv(arg0);
	}

	@Override
	public void glVertexArrayParameteriAPPLE(int arg0, int arg1) {
		gl2.glVertexArrayParameteriAPPLE(arg0, arg1);
	}

	@Override
	public void glVertexArrayRangeAPPLE(int arg0, Buffer arg1) {
		gl2.glVertexArrayRangeAPPLE(arg0, arg1);
	}

	@Override
	public void glVertexArrayRangeNV(int arg0, Buffer arg1) {
		gl2.glVertexArrayRangeNV(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1d(int arg0, double arg1) {
		gl2.glVertexAttrib1d(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dARB(int arg0, double arg1) {
		gl2.glVertexAttrib1dARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib1dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib1dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dvARB(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib1dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1dvARB(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib1dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1f(int arg0, float arg1) {
		gl2.glVertexAttrib1f(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fARB(int arg0, float arg1) {
		gl2.glVertexAttrib1fARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fv(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib1fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1fv(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib1fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fvARB(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib1fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1fvARB(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib1fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1h(int arg0, short arg1) {
		gl2.glVertexAttrib1h(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1hv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib1hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1hv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib1hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1s(int arg0, short arg1) {
		gl2.glVertexAttrib1s(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1sARB(int arg0, short arg1) {
		gl2.glVertexAttrib1sARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1sv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib1sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1sv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib1sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1svARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib1svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1svARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib1svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2d(int arg0, double arg1, double arg2) {
		gl2.glVertexAttrib2d(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dARB(int arg0, double arg1, double arg2) {
		gl2.glVertexAttrib2dARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib2dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib2dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2dvARB(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib2dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dvARB(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib2dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2f(int arg0, float arg1, float arg2) {
		gl2.glVertexAttrib2f(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fARB(int arg0, float arg1, float arg2) {
		gl2.glVertexAttrib2fARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fv(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib2fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fv(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib2fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2fvARB(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib2fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fvARB(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib2fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2h(int arg0, short arg1, short arg2) {
		gl2.glVertexAttrib2h(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2hv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib2hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2hv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib2hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2s(int arg0, short arg1, short arg2) {
		gl2.glVertexAttrib2s(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sARB(int arg0, short arg1, short arg2) {
		gl2.glVertexAttrib2sARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib2sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib2sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2svARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib2svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2svARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib2svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3d(int arg0, double arg1, double arg2, double arg3) {
		gl2.glVertexAttrib3d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3dARB(int arg0, double arg1, double arg2, double arg3) {
		gl2.glVertexAttrib3dARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib3dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib3dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3dvARB(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib3dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3dvARB(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib3dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3f(int arg0, float arg1, float arg2, float arg3) {
		gl2.glVertexAttrib3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3fARB(int arg0, float arg1, float arg2, float arg3) {
		gl2.glVertexAttrib3fARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3fv(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib3fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3fv(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib3fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3fvARB(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib3fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3fvARB(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib3fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3h(int arg0, short arg1, short arg2, short arg3) {
		gl2.glVertexAttrib3h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3hv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib3hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3hv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib3hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3s(int arg0, short arg1, short arg2, short arg3) {
		gl2.glVertexAttrib3s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3sARB(int arg0, short arg1, short arg2, short arg3) {
		gl2.glVertexAttrib3sARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3sv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib3sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3sv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib3sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3svARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib3svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3svARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib3svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nbv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4Nbv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nbv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4Nbv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NbvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4NbvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NbvARB(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4NbvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Niv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4Niv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Niv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4Niv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NivARB(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4NivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NivARB(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4NivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nsv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4Nsv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nsv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4Nsv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NsvARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4NsvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NsvARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4NsvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nub(int arg0, byte arg1, byte arg2, byte arg3, byte arg4) {
		gl2.glVertexAttrib4Nub(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4NubARB(int arg0, byte arg1, byte arg2, byte arg3, byte arg4) {
		gl2.glVertexAttrib4NubARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4Nubv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4Nubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nubv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4Nubv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NubvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4NubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NubvARB(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4NubvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nuiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4Nuiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nuiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4Nuiv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NuivARB(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4NuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NuivARB(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4NuivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nusv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4Nusv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nusv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4Nusv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NusvARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4NusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NusvARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4NusvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4bv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4bv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4bv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4bv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4bvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4bvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4bvARB(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4bvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4d(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl2.glVertexAttrib4d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4dARB(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl2.glVertexAttrib4dARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib4dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib4dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4dvARB(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttrib4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4dvARB(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttrib4dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glVertexAttrib4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4fARB(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glVertexAttrib4fARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4fv(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib4fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4fv(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib4fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4fvARB(int arg0, float[] arg1, int arg2) {
		gl2.glVertexAttrib4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4fvARB(int arg0, FloatBuffer arg1) {
		gl2.glVertexAttrib4fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4h(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl2.glVertexAttrib4h(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4hv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4hv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4iv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4iv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4iv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ivARB(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ivARB(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4ivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4s(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl2.glVertexAttrib4s(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4sARB(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl2.glVertexAttrib4sARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4sv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4sv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4svARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4svARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ubv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4ubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ubv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4ubv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ubvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttrib4ubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ubvARB(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttrib4ubvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4uivARB(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttrib4uivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4uivARB(int arg0, IntBuffer arg1) {
		gl2.glVertexAttrib4uivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4usv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4usv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4usv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4usv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4usvARB(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttrib4usvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4usvARB(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttrib4usvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttribFormatNV(int arg0, int arg1, int arg2, boolean arg3, int arg4) {
		gl2.glVertexAttribFormatNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI1i(int arg0, int arg1) {
		gl2.glVertexAttribI1i(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1iEXT(int arg0, int arg1) {
		gl2.glVertexAttribI1iEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1iv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI1iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1iv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI1iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1ivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI1ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1ivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI1ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1ui(int arg0, int arg1) {
		gl2.glVertexAttribI1ui(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uiEXT(int arg0, int arg1) {
		gl2.glVertexAttribI1uiEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI1uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI1uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1uivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI1uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2i(int arg0, int arg1, int arg2) {
		gl2.glVertexAttribI2i(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iEXT(int arg0, int arg1, int arg2) {
		gl2.glVertexAttribI2iEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI2iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI2iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2ivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI2ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2ivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI2ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2ui(int arg0, int arg1, int arg2) {
		gl2.glVertexAttribI2ui(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiEXT(int arg0, int arg1, int arg2) {
		gl2.glVertexAttribI2uiEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI2uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2uivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI2uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI2uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3i(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribI3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3iEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribI3iEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3iv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI3iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3iv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI3iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3ivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI3ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3ivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI3ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3ui(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribI3ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3uiEXT(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribI3uiEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI3uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3uivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI3uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3uivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI3uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4bv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttribI4bv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4bv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttribI4bv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4bvEXT(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttribI4bvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4bvEXT(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttribI4bvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glVertexAttribI4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4iEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glVertexAttribI4iEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4iv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI4iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4iv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI4iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI4ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI4ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4sv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttribI4sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4sv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttribI4sv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4svEXT(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttribI4svEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4svEXT(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttribI4svEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ubv(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttribI4ubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ubv(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttribI4ubv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ubvEXT(int arg0, byte[] arg1, int arg2) {
		gl2.glVertexAttribI4ubvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ubvEXT(int arg0, ByteBuffer arg1) {
		gl2.glVertexAttribI4ubvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ui(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glVertexAttribI4ui(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl2.glVertexAttribI4uiEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI4uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4uivEXT(int arg0, int[] arg1, int arg2) {
		gl2.glVertexAttribI4uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4uivEXT(int arg0, IntBuffer arg1) {
		gl2.glVertexAttribI4uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4usv(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttribI4usv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4usv(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttribI4usv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4usvEXT(int arg0, short[] arg1, int arg2) {
		gl2.glVertexAttribI4usvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4usvEXT(int arg0, ShortBuffer arg1) {
		gl2.glVertexAttribI4usvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribIFormatNV(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribIFormatNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribIPointer(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glVertexAttribIPointer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribIPointerEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glVertexAttribIPointerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL1d(int arg0, double arg1) {
		gl2.glVertexAttribL1d(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttribL1dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL1dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttribL1dv(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1i64NV(int arg0, long arg1) {
		gl2.glVertexAttribL1i64NV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1i64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL1i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL1i64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL1i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1ui64NV(int arg0, long arg1) {
		gl2.glVertexAttribL1ui64NV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1ui64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL1ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL1ui64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL1ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL2d(int arg0, double arg1, double arg2) {
		gl2.glVertexAttribL2d(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttribL2dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttribL2dv(arg0, arg1);
	}

	@Override
	public void glVertexAttribL2i64NV(int arg0, long arg1, long arg2) {
		gl2.glVertexAttribL2i64NV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2i64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL2i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2i64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL2i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL2ui64NV(int arg0, long arg1, long arg2) {
		gl2.glVertexAttribL2ui64NV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2ui64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL2ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2ui64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL2ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL3d(int arg0, double arg1, double arg2, double arg3) {
		gl2.glVertexAttribL3d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribL3dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttribL3dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL3dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttribL3dv(arg0, arg1);
	}

	@Override
	public void glVertexAttribL3i64NV(int arg0, long arg1, long arg2, long arg3) {
		gl2.glVertexAttribL3i64NV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribL3i64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL3i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL3i64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL3i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL3ui64NV(int arg0, long arg1, long arg2, long arg3) {
		gl2.glVertexAttribL3ui64NV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribL3ui64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL3ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL3ui64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL3ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL4d(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl2.glVertexAttribL4d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL4dv(int arg0, double[] arg1, int arg2) {
		gl2.glVertexAttribL4dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL4dv(int arg0, DoubleBuffer arg1) {
		gl2.glVertexAttribL4dv(arg0, arg1);
	}

	@Override
	public void glVertexAttribL4i64NV(int arg0, long arg1, long arg2, long arg3, long arg4) {
		gl2.glVertexAttribL4i64NV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL4i64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL4i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL4i64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL4i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL4ui64NV(int arg0, long arg1, long arg2, long arg3, long arg4) {
		gl2.glVertexAttribL4ui64NV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL4ui64vNV(int arg0, long[] arg1, int arg2) {
		gl2.glVertexAttribL4ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL4ui64vNV(int arg0, LongBuffer arg1) {
		gl2.glVertexAttribL4ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribLFormatNV(int arg0, int arg1, int arg2, int arg3) {
		gl2.glVertexAttribLFormatNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribLPointer(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl2.glVertexAttribLPointer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribP1ui(int arg0, int arg1, boolean arg2, int arg3) {
		gl2.glVertexAttribP1ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP1uiv(int arg0, int arg1, boolean arg2, int[] arg3, int arg4) {
		gl2.glVertexAttribP1uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribP1uiv(int arg0, int arg1, boolean arg2, IntBuffer arg3) {
		gl2.glVertexAttribP1uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP2ui(int arg0, int arg1, boolean arg2, int arg3) {
		gl2.glVertexAttribP2ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP2uiv(int arg0, int arg1, boolean arg2, int[] arg3, int arg4) {
		gl2.glVertexAttribP2uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribP2uiv(int arg0, int arg1, boolean arg2, IntBuffer arg3) {
		gl2.glVertexAttribP2uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP3ui(int arg0, int arg1, boolean arg2, int arg3) {
		gl2.glVertexAttribP3ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP3uiv(int arg0, int arg1, boolean arg2, int[] arg3, int arg4) {
		gl2.glVertexAttribP3uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribP3uiv(int arg0, int arg1, boolean arg2, IntBuffer arg3) {
		gl2.glVertexAttribP3uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP4ui(int arg0, int arg1, boolean arg2, int arg3) {
		gl2.glVertexAttribP4ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribP4uiv(int arg0, int arg1, boolean arg2, int[] arg3, int arg4) {
		gl2.glVertexAttribP4uiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribP4uiv(int arg0, int arg1, boolean arg2, IntBuffer arg3) {
		gl2.glVertexAttribP4uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribPointer(GLArrayData arg0) {
		gl2.glVertexAttribPointer(arg0);
	}

	@Override
	public void glVertexAttribPointer(int arg0, int arg1, int arg2, boolean arg3, int arg4, Buffer arg5) {
		gl2.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointer(int arg0, int arg1, int arg2, boolean arg3, int arg4, long arg5) {
		gl2.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointerARB(int arg0, int arg1, int arg2, boolean arg3, int arg4, Buffer arg5) {
		gl2.glVertexAttribPointerARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointerARB(int arg0, int arg1, int arg2, boolean arg3, int arg4, long arg5) {
		gl2.glVertexAttribPointerARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribs1hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glVertexAttribs1hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs1hv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glVertexAttribs1hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs2hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glVertexAttribs2hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs2hv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glVertexAttribs2hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs3hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glVertexAttribs3hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs3hv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glVertexAttribs3hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs4hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl2.glVertexAttribs4hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs4hv(int arg0, int arg1, ShortBuffer arg2) {
		gl2.glVertexAttribs4hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexBlendARB(int arg0) {
		gl2.glVertexBlendARB(arg0);
	}

	@Override
	public void glVertexFormatNV(int arg0, int arg1, int arg2) {
		gl2.glVertexFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexP2ui(int arg0, int arg1) {
		gl2.glVertexP2ui(arg0, arg1);
	}

	@Override
	public void glVertexP2uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexP2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexP2uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexP2uiv(arg0, arg1);
	}

	@Override
	public void glVertexP3ui(int arg0, int arg1) {
		gl2.glVertexP3ui(arg0, arg1);
	}

	@Override
	public void glVertexP3uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexP3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexP3uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexP3uiv(arg0, arg1);
	}

	@Override
	public void glVertexP4ui(int arg0, int arg1) {
		gl2.glVertexP4ui(arg0, arg1);
	}

	@Override
	public void glVertexP4uiv(int arg0, int[] arg1, int arg2) {
		gl2.glVertexP4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexP4uiv(int arg0, IntBuffer arg1) {
		gl2.glVertexP4uiv(arg0, arg1);
	}

	@Override
	public void glVertexPointer(GLArrayData arg0) {
		gl2.glVertexPointer(arg0);
	}

	@Override
	public void glVertexPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glVertexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexPointer(int arg0, int arg1, int arg2, long arg3) {
		gl2.glVertexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightPointerEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glVertexWeightPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightPointerEXT(int arg0, int arg1, int arg2, long arg3) {
		gl2.glVertexWeightPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightfEXT(float arg0) {
		gl2.glVertexWeightfEXT(arg0);
	}

	@Override
	public void glVertexWeightfvEXT(float[] arg0, int arg1) {
		gl2.glVertexWeightfvEXT(arg0, arg1);
	}

	@Override
	public void glVertexWeightfvEXT(FloatBuffer arg0) {
		gl2.glVertexWeightfvEXT(arg0);
	}

	@Override
	public void glVertexWeighth(short arg0) {
		gl2.glVertexWeighth(arg0);
	}

	@Override
	public void glVertexWeighthv(short[] arg0, int arg1) {
		gl2.glVertexWeighthv(arg0, arg1);
	}

	@Override
	public void glVertexWeighthv(ShortBuffer arg0) {
		gl2.glVertexWeighthv(arg0);
	}

	@Override
	public int glVideoCaptureNV(int arg0, int[] arg1, int arg2, long[] arg3, int arg4) {
		return gl2.glVideoCaptureNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public int glVideoCaptureNV(int arg0, IntBuffer arg1, LongBuffer arg2) {
		return gl2.glVideoCaptureNV(arg0, arg1, arg2);
	}

	@Override
	public void glVideoCaptureStreamParameterdvNV(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl2.glVideoCaptureStreamParameterdvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterdvNV(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl2.glVideoCaptureStreamParameterdvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVideoCaptureStreamParameterfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl2.glVideoCaptureStreamParameterfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl2.glVideoCaptureStreamParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVideoCaptureStreamParameterivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl2.glVideoCaptureStreamParameterivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl2.glVideoCaptureStreamParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glViewport(int arg0, int arg1, int arg2, int arg3) {
		gl2.glViewport(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glViewportArrayv(int arg0, int arg1, float[] arg2, int arg3) {
		gl2.glViewportArrayv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glViewportArrayv(int arg0, int arg1, FloatBuffer arg2) {
		gl2.glViewportArrayv(arg0, arg1, arg2);
	}

	@Override
	public void glViewportIndexedf(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl2.glViewportIndexedf(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glViewportIndexedfv(int arg0, float[] arg1, int arg2) {
		gl2.glViewportIndexedfv(arg0, arg1, arg2);
	}

	@Override
	public void glViewportIndexedfv(int arg0, FloatBuffer arg1) {
		gl2.glViewportIndexedfv(arg0, arg1);
	}

	@Override
	public void glWeightPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl2.glWeightPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glWeightbvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glWeightbvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightbvARB(int arg0, ByteBuffer arg1) {
		gl2.glWeightbvARB(arg0, arg1);
	}

	@Override
	public void glWeightdvARB(int arg0, double[] arg1, int arg2) {
		gl2.glWeightdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightdvARB(int arg0, DoubleBuffer arg1) {
		gl2.glWeightdvARB(arg0, arg1);
	}

	@Override
	public void glWeightfvARB(int arg0, float[] arg1, int arg2) {
		gl2.glWeightfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightfvARB(int arg0, FloatBuffer arg1) {
		gl2.glWeightfvARB(arg0, arg1);
	}

	@Override
	public void glWeightivARB(int arg0, int[] arg1, int arg2) {
		gl2.glWeightivARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightivARB(int arg0, IntBuffer arg1) {
		gl2.glWeightivARB(arg0, arg1);
	}

	@Override
	public void glWeightsvARB(int arg0, short[] arg1, int arg2) {
		gl2.glWeightsvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightsvARB(int arg0, ShortBuffer arg1) {
		gl2.glWeightsvARB(arg0, arg1);
	}

	@Override
	public void glWeightubvARB(int arg0, byte[] arg1, int arg2) {
		gl2.glWeightubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightubvARB(int arg0, ByteBuffer arg1) {
		gl2.glWeightubvARB(arg0, arg1);
	}

	@Override
	public void glWeightuivARB(int arg0, int[] arg1, int arg2) {
		gl2.glWeightuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightuivARB(int arg0, IntBuffer arg1) {
		gl2.glWeightuivARB(arg0, arg1);
	}

	@Override
	public void glWeightusvARB(int arg0, short[] arg1, int arg2) {
		gl2.glWeightusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightusvARB(int arg0, ShortBuffer arg1) {
		gl2.glWeightusvARB(arg0, arg1);
	}

	@Override
	public void glWindowPos2d(double arg0, double arg1) {
		gl2.glWindowPos2d(arg0, arg1);
	}

	@Override
	public void glWindowPos2dv(double[] arg0, int arg1) {
		gl2.glWindowPos2dv(arg0, arg1);
	}

	@Override
	public void glWindowPos2dv(DoubleBuffer arg0) {
		gl2.glWindowPos2dv(arg0);
	}

	@Override
	public void glWindowPos2f(float arg0, float arg1) {
		gl2.glWindowPos2f(arg0, arg1);
	}

	@Override
	public void glWindowPos2fv(float[] arg0, int arg1) {
		gl2.glWindowPos2fv(arg0, arg1);
	}

	@Override
	public void glWindowPos2fv(FloatBuffer arg0) {
		gl2.glWindowPos2fv(arg0);
	}

	@Override
	public void glWindowPos2i(int arg0, int arg1) {
		gl2.glWindowPos2i(arg0, arg1);
	}

	@Override
	public void glWindowPos2iv(int[] arg0, int arg1) {
		gl2.glWindowPos2iv(arg0, arg1);
	}

	@Override
	public void glWindowPos2iv(IntBuffer arg0) {
		gl2.glWindowPos2iv(arg0);
	}

	@Override
	public void glWindowPos2s(short arg0, short arg1) {
		gl2.glWindowPos2s(arg0, arg1);
	}

	@Override
	public void glWindowPos2sv(short[] arg0, int arg1) {
		gl2.glWindowPos2sv(arg0, arg1);
	}

	@Override
	public void glWindowPos2sv(ShortBuffer arg0) {
		gl2.glWindowPos2sv(arg0);
	}

	@Override
	public void glWindowPos3d(double arg0, double arg1, double arg2) {
		gl2.glWindowPos3d(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3dv(double[] arg0, int arg1) {
		gl2.glWindowPos3dv(arg0, arg1);
	}

	@Override
	public void glWindowPos3dv(DoubleBuffer arg0) {
		gl2.glWindowPos3dv(arg0);
	}

	@Override
	public void glWindowPos3f(float arg0, float arg1, float arg2) {
		gl2.glWindowPos3f(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3fv(float[] arg0, int arg1) {
		gl2.glWindowPos3fv(arg0, arg1);
	}

	@Override
	public void glWindowPos3fv(FloatBuffer arg0) {
		gl2.glWindowPos3fv(arg0);
	}

	@Override
	public void glWindowPos3i(int arg0, int arg1, int arg2) {
		gl2.glWindowPos3i(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3iv(int[] arg0, int arg1) {
		gl2.glWindowPos3iv(arg0, arg1);
	}

	@Override
	public void glWindowPos3iv(IntBuffer arg0) {
		gl2.glWindowPos3iv(arg0);
	}

	@Override
	public void glWindowPos3s(short arg0, short arg1, short arg2) {
		gl2.glWindowPos3s(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3sv(short[] arg0, int arg1) {
		gl2.glWindowPos3sv(arg0, arg1);
	}

	@Override
	public void glWindowPos3sv(ShortBuffer arg0) {
		gl2.glWindowPos3sv(arg0);
	}

	@Override
	public void glWriteMaskEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl2.glWriteMaskEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public boolean hasGLSL() {
		return gl2.hasGLSL();
	}

	@Override
	public boolean isExtensionAvailable(String arg0) {
		return gl2.isExtensionAvailable(arg0);
	}

	@Override
	public boolean isFunctionAvailable(String arg0) {
		return gl2.isFunctionAvailable(arg0);
	}

	@Override
	public boolean isGL() {
		return gl2.isGL();
	}

	@Override
	public boolean isGL2() {
		return gl2.isGL2();
	}

	@Override
	public boolean isGL2ES1() {
		return gl2.isGL2ES1();
	}

	@Override
	public boolean isGL2ES2() {
		return gl2.isGL2ES2();
	}

	@Override
	public boolean isGL2GL3() {
		return gl2.isGL2GL3();
	}

	@Override
	public boolean isGL3() {
		return gl2.isGL3();
	}

	@Override
	public boolean isGL3bc() {
		return gl2.isGL3bc();
	}

	@Override
	public boolean isGL4() {
		return gl2.isGL4();
	}

	@Override
	public boolean isGL4bc() {
		return gl2.isGL4bc();
	}

	@Override
	public boolean isGLES() {
		return gl2.isGLES();
	}

	@Override
	public boolean isGLES1() {
		return gl2.isGLES1();
	}

	@Override
	public boolean isGLES2() {
		return gl2.isGLES2();
	}

	@Override
	public boolean isGLES2Compatible() {
		return gl2.isGLES2Compatible();
	}

	@Override
	public boolean isNPOTTextureAvailable() {
		return gl2.isNPOTTextureAvailable();
	}

	@Override
	public void setSwapInterval(int arg0) {
		gl2.setSwapInterval(arg0);
	}

	@Override
	public int getBoundFramebuffer(int target) {
		return gl2.getBoundFramebuffer(target);
	}

	@Override
	public int getDefaultDrawFramebuffer() {
		return gl2.getDefaultDrawFramebuffer();
	}

	@Override
	public int getDefaultReadFramebuffer() {
		return gl2.getDefaultReadFramebuffer();
	}

	@Override
	public int getMaxRenderbufferSamples() {
		return gl2.getMaxRenderbufferSamples();
	}

	@Override
	public void glVertexAttribIPointer(int index, int size, int type, int stride, long pointer_buffer_offset) {
		gl2.glVertexAttribIPointer(index, size, type, stride, pointer_buffer_offset);
	}

	@Override
	public void glVertexAttribLPointer(int index, int size, int type, int stride, long pointer_buffer_offset) {
		gl2.glVertexAttribLPointer(index, size, type, stride, pointer_buffer_offset);
	}

	@Override
	public boolean hasBasicFBOSupport() {
		return gl2.hasBasicFBOSupport();
	}

	@Override
	public boolean hasFullFBOSupport() {
		return gl2.hasFullFBOSupport();
	}

	@Override
	public boolean isTextureFormatBGRA8888Available() {
		return gl2.isTextureFormatBGRA8888Available();
	}

}
