package org.archstudio.bna.utils;

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
import javax.media.opengl.GL2ES3;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GL3;
import javax.media.opengl.GL3ES3;
import javax.media.opengl.GL3bc;
import javax.media.opengl.GL4;
import javax.media.opengl.GL4ES3;
import javax.media.opengl.GL4bc;
import javax.media.opengl.GLArrayData;
import javax.media.opengl.GLBufferStorage;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLES1;
import javax.media.opengl.GLES2;
import javax.media.opengl.GLES3;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLUniformData;

import com.jogamp.common.nio.PointerBuffer;

@SuppressWarnings("deprecation")
public class GL2Delegate implements GL2 {

	protected final GL2 gl;

	public GL2Delegate(GL2 gl) {
		this.gl = gl;
	}

	@Override
	public int getBoundBuffer(int arg0) {
		return gl.getBoundBuffer(arg0);
	}

	@Override
	public int getBoundFramebuffer(int arg0) {
		return gl.getBoundFramebuffer(arg0);
	}

	@Override
	public GLBufferStorage getBufferStorage(int arg0) {
		return gl.getBufferStorage(arg0);
	}

	@Override
	public GLContext getContext() {
		return gl.getContext();
	}

	@Override
	public int getDefaultDrawFramebuffer() {
		return gl.getDefaultDrawFramebuffer();
	}

	@Override
	public int getDefaultReadBuffer() {
		return gl.getDefaultReadBuffer();
	}

	@Override
	public int getDefaultReadFramebuffer() {
		return gl.getDefaultReadFramebuffer();
	}

	@Override
	public GL getDownstreamGL() throws GLException {
		return gl.getDownstreamGL();
	}

	@Override
	public Object getExtension(String arg0) {
		return gl.getExtension(arg0);
	}

	@Override
	public GL getGL() throws GLException {
		return gl.getGL();
	}

	@Override
	public GL2 getGL2() throws GLException {
		return gl.getGL2();
	}

	@Override
	public GL2ES1 getGL2ES1() throws GLException {
		return gl.getGL2ES1();
	}

	@Override
	public GL2ES2 getGL2ES2() throws GLException {
		return gl.getGL2ES2();
	}

	@Override
	public GL2ES3 getGL2ES3() throws GLException {
		return gl.getGL2ES3();
	}

	@Override
	public GL2GL3 getGL2GL3() throws GLException {
		return gl.getGL2GL3();
	}

	@Override
	public GL3 getGL3() throws GLException {
		return gl.getGL3();
	}

	@Override
	public GL3ES3 getGL3ES3() throws GLException {
		return gl.getGL3ES3();
	}

	@Override
	public GL3bc getGL3bc() throws GLException {
		return gl.getGL3bc();
	}

	@Override
	public GL4 getGL4() throws GLException {
		return gl.getGL4();
	}

	@Override
	public GL4ES3 getGL4ES3() throws GLException {
		return gl.getGL4ES3();
	}

	@Override
	public GL4bc getGL4bc() throws GLException {
		return gl.getGL4bc();
	}

	@Override
	public GLES1 getGLES1() throws GLException {
		return gl.getGLES1();
	}

	@Override
	public GLES2 getGLES2() throws GLException {
		return gl.getGLES2();
	}

	@Override
	public GLES3 getGLES3() throws GLException {
		return gl.getGLES3();
	}

	@Override
	public GLProfile getGLProfile() {
		return gl.getGLProfile();
	}

	@Override
	public int getMaxRenderbufferSamples() {
		return gl.getMaxRenderbufferSamples();
	}

	@Override
	public Object getPlatformGLExtensions() {
		return gl.getPlatformGLExtensions();
	}

	@Override
	public GL getRootGL() throws GLException {
		return gl.getRootGL();
	}

	@Override
	public int getSwapInterval() {
		return gl.getSwapInterval();
	}

	@Override
	public void glAccum(int arg0, float arg1) {
		gl.glAccum(arg0, arg1);
	}

	@Override
	public void glActiveStencilFaceEXT(int arg0) {
		gl.glActiveStencilFaceEXT(arg0);
	}

	@Override
	public void glActiveTexture(int arg0) {
		gl.glActiveTexture(arg0);
	}

	@Override
	public ByteBuffer glAllocateMemoryNV(int arg0, float arg1, float arg2, float arg3) {
		return gl.glAllocateMemoryNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glAlphaFunc(int arg0, float arg1) {
		gl.glAlphaFunc(arg0, arg1);
	}

	@Override
	public void glApplyTextureEXT(int arg0) {
		gl.glApplyTextureEXT(arg0);
	}

	@Override
	public boolean glAreTexturesResident(int arg0, int[] arg1, int arg2, byte[] arg3, int arg4) {
		return gl.glAreTexturesResident(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public boolean glAreTexturesResident(int arg0, IntBuffer arg1, ByteBuffer arg2) {
		return gl.glAreTexturesResident(arg0, arg1, arg2);
	}

	@Override
	public void glArrayElement(int arg0) {
		gl.glArrayElement(arg0);
	}

	@Override
	public void glAttachObjectARB(int arg0, int arg1) {
		gl.glAttachObjectARB(arg0, arg1);
	}

	@Override
	public void glAttachShader(int arg0, int arg1) {
		gl.glAttachShader(arg0, arg1);
	}

	@Override
	public void glBegin(int arg0) {
		gl.glBegin(arg0);
	}

	@Override
	public void glBeginConditionalRender(int arg0, int arg1) {
		gl.glBeginConditionalRender(arg0, arg1);
	}

	@Override
	public void glBeginConditionalRenderNVX(int arg0) {
		gl.glBeginConditionalRenderNVX(arg0);
	}

	@Override
	public void glBeginOcclusionQueryNV(int arg0) {
		gl.glBeginOcclusionQueryNV(arg0);
	}

	@Override
	public void glBeginPerfMonitorAMD(int arg0) {
		gl.glBeginPerfMonitorAMD(arg0);
	}

	@Override
	public void glBeginQuery(int arg0, int arg1) {
		gl.glBeginQuery(arg0, arg1);
	}

	@Override
	public void glBeginTransformFeedback(int arg0) {
		gl.glBeginTransformFeedback(arg0);
	}

	@Override
	public void glBeginVertexShaderEXT() {
		gl.glBeginVertexShaderEXT();
	}

	@Override
	public void glBeginVideoCaptureNV(int arg0) {
		gl.glBeginVideoCaptureNV(arg0);
	}

	@Override
	public void glBindAttribLocation(int arg0, int arg1, String arg2) {
		gl.glBindAttribLocation(arg0, arg1, arg2);
	}

	@Override
	public void glBindBuffer(int arg0, int arg1) {
		gl.glBindBuffer(arg0, arg1);
	}

	@Override
	public void glBindBufferBase(int arg0, int arg1, int arg2) {
		gl.glBindBufferBase(arg0, arg1, arg2);
	}

	@Override
	public void glBindBufferRange(int arg0, int arg1, int arg2, long arg3, long arg4) {
		gl.glBindBufferRange(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBindFragDataLocation(int arg0, int arg1, String arg2) {
		gl.glBindFragDataLocation(arg0, arg1, arg2);
	}

	@Override
	public void glBindFramebuffer(int arg0, int arg1) {
		gl.glBindFramebuffer(arg0, arg1);
	}

	@Override
	public int glBindLightParameterEXT(int arg0, int arg1) {
		return gl.glBindLightParameterEXT(arg0, arg1);
	}

	@Override
	public int glBindMaterialParameterEXT(int arg0, int arg1) {
		return gl.glBindMaterialParameterEXT(arg0, arg1);
	}

	@Override
	public void glBindMultiTextureEXT(int arg0, int arg1, int arg2) {
		gl.glBindMultiTextureEXT(arg0, arg1, arg2);
	}

	@Override
	public int glBindParameterEXT(int arg0) {
		return gl.glBindParameterEXT(arg0);
	}

	@Override
	public void glBindProgramARB(int arg0, int arg1) {
		gl.glBindProgramARB(arg0, arg1);
	}

	@Override
	public void glBindRenderbuffer(int arg0, int arg1) {
		gl.glBindRenderbuffer(arg0, arg1);
	}

	@Override
	public int glBindTexGenParameterEXT(int arg0, int arg1, int arg2) {
		return gl.glBindTexGenParameterEXT(arg0, arg1, arg2);
	}

	@Override
	public void glBindTexture(int arg0, int arg1) {
		gl.glBindTexture(arg0, arg1);
	}

	@Override
	public int glBindTextureUnitParameterEXT(int arg0, int arg1) {
		return gl.glBindTextureUnitParameterEXT(arg0, arg1);
	}

	@Override
	public void glBindTransformFeedbackNV(int arg0, int arg1) {
		gl.glBindTransformFeedbackNV(arg0, arg1);
	}

	@Override
	public void glBindVertexArray(int arg0) {
		gl.glBindVertexArray(arg0);
	}

	@Override
	public void glBindVertexShaderEXT(int arg0) {
		gl.glBindVertexShaderEXT(arg0);
	}

	@Override
	public void glBindVideoCaptureStreamBufferNV(int arg0, int arg1, int arg2, long arg3) {
		gl.glBindVideoCaptureStreamBufferNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBindVideoCaptureStreamTextureNV(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glBindVideoCaptureStreamTextureNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, byte[] arg6, int arg7) {
		gl.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, ByteBuffer arg6) {
		gl.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glBitmap(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, long arg6) {
		gl.glBitmap(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glBlendColor(float arg0, float arg1, float arg2, float arg3) {
		gl.glBlendColor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendEquation(int arg0) {
		gl.glBlendEquation(arg0);
	}

	@Override
	public void glBlendEquationIndexedAMD(int arg0, int arg1) {
		gl.glBlendEquationIndexedAMD(arg0, arg1);
	}

	@Override
	public void glBlendEquationSeparate(int arg0, int arg1) {
		gl.glBlendEquationSeparate(arg0, arg1);
	}

	@Override
	public void glBlendEquationSeparateIndexedAMD(int arg0, int arg1, int arg2) {
		gl.glBlendEquationSeparateIndexedAMD(arg0, arg1, arg2);
	}

	@Override
	public void glBlendFunc(int arg0, int arg1) {
		gl.glBlendFunc(arg0, arg1);
	}

	@Override
	public void glBlendFuncIndexedAMD(int arg0, int arg1, int arg2) {
		gl.glBlendFuncIndexedAMD(arg0, arg1, arg2);
	}

	@Override
	public void glBlendFuncSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl.glBlendFuncSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendFuncSeparateINGR(int arg0, int arg1, int arg2, int arg3) {
		gl.glBlendFuncSeparateINGR(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBlendFuncSeparateIndexedAMD(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glBlendFuncSeparateIndexedAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glBlitFramebuffer(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9) {
		gl.glBlitFramebuffer(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glBufferAddressRangeNV(int arg0, int arg1, long arg2, long arg3) {
		gl.glBufferAddressRangeNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBufferData(int arg0, long arg1, Buffer arg2, int arg3) {
		gl.glBufferData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glBufferParameteri(int arg0, int arg1, int arg2) {
		gl.glBufferParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glBufferSubData(int arg0, long arg1, long arg2, Buffer arg3) {
		gl.glBufferSubData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glCallList(int arg0) {
		gl.glCallList(arg0);
	}

	@Override
	public void glCallLists(int arg0, int arg1, Buffer arg2) {
		gl.glCallLists(arg0, arg1, arg2);
	}

	@Override
	public int glCheckFramebufferStatus(int arg0) {
		return gl.glCheckFramebufferStatus(arg0);
	}

	@Override
	public int glCheckNamedFramebufferStatusEXT(int arg0, int arg1) {
		return gl.glCheckNamedFramebufferStatusEXT(arg0, arg1);
	}

	@Override
	public void glClampColor(int arg0, int arg1) {
		gl.glClampColor(arg0, arg1);
	}

	@Override
	public void glClear(int arg0) {
		gl.glClear(arg0);
	}

	@Override
	public void glClearAccum(float arg0, float arg1, float arg2, float arg3) {
		gl.glClearAccum(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfi(int arg0, int arg1, float arg2, int arg3) {
		gl.glClearBufferfi(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glClearBufferfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glClearBufferfv(arg0, arg1, arg2);
	}

	@Override
	public void glClearBufferiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glClearBufferiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glClearBufferiv(arg0, arg1, arg2);
	}

	@Override
	public void glClearBufferuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glClearBufferuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearBufferuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glClearBufferuiv(arg0, arg1, arg2);
	}

	@Override
	public void glClearColor(float arg0, float arg1, float arg2, float arg3) {
		gl.glClearColor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearColorIi(int arg0, int arg1, int arg2, int arg3) {
		gl.glClearColorIi(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearColorIui(int arg0, int arg1, int arg2, int arg3) {
		gl.glClearColorIui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glClearDepth(double arg0) {
		gl.glClearDepth(arg0);
	}

	@Override
	public void glClearDepthf(float arg0) {
		gl.glClearDepthf(arg0);
	}

	@Override
	public void glClearIndex(float arg0) {
		gl.glClearIndex(arg0);
	}

	@Override
	public void glClearNamedBufferDataEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glClearNamedBufferDataEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glClearNamedBufferSubDataEXT(int arg0, int arg1, int arg2, int arg3, long arg4, long arg5, Buffer arg6) {
		gl.glClearNamedBufferSubDataEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glClearStencil(int arg0) {
		gl.glClearStencil(arg0);
	}

	@Override
	public void glClientActiveTexture(int arg0) {
		gl.glClientActiveTexture(arg0);
	}

	@Override
	public void glClientAttribDefaultEXT(int arg0) {
		gl.glClientAttribDefaultEXT(arg0);
	}

	@Override
	public void glClipPlane(int arg0, double[] arg1, int arg2) {
		gl.glClipPlane(arg0, arg1, arg2);
	}

	@Override
	public void glClipPlane(int arg0, DoubleBuffer arg1) {
		gl.glClipPlane(arg0, arg1);
	}

	@Override
	public void glClipPlanef(int arg0, float[] arg1, int arg2) {
		gl.glClipPlanef(arg0, arg1, arg2);
	}

	@Override
	public void glClipPlanef(int arg0, FloatBuffer arg1) {
		gl.glClipPlanef(arg0, arg1);
	}

	@Override
	public void glColor3b(byte arg0, byte arg1, byte arg2) {
		gl.glColor3b(arg0, arg1, arg2);
	}

	@Override
	public void glColor3bv(byte[] arg0, int arg1) {
		gl.glColor3bv(arg0, arg1);
	}

	@Override
	public void glColor3bv(ByteBuffer arg0) {
		gl.glColor3bv(arg0);
	}

	@Override
	public void glColor3d(double arg0, double arg1, double arg2) {
		gl.glColor3d(arg0, arg1, arg2);
	}

	@Override
	public void glColor3dv(double[] arg0, int arg1) {
		gl.glColor3dv(arg0, arg1);
	}

	@Override
	public void glColor3dv(DoubleBuffer arg0) {
		gl.glColor3dv(arg0);
	}

	@Override
	public void glColor3f(float arg0, float arg1, float arg2) {
		gl.glColor3f(arg0, arg1, arg2);
	}

	@Override
	public void glColor3fv(float[] arg0, int arg1) {
		gl.glColor3fv(arg0, arg1);
	}

	@Override
	public void glColor3fv(FloatBuffer arg0) {
		gl.glColor3fv(arg0);
	}

	@Override
	public void glColor3h(short arg0, short arg1, short arg2) {
		gl.glColor3h(arg0, arg1, arg2);
	}

	@Override
	public void glColor3hv(short[] arg0, int arg1) {
		gl.glColor3hv(arg0, arg1);
	}

	@Override
	public void glColor3hv(ShortBuffer arg0) {
		gl.glColor3hv(arg0);
	}

	@Override
	public void glColor3i(int arg0, int arg1, int arg2) {
		gl.glColor3i(arg0, arg1, arg2);
	}

	@Override
	public void glColor3iv(int[] arg0, int arg1) {
		gl.glColor3iv(arg0, arg1);
	}

	@Override
	public void glColor3iv(IntBuffer arg0) {
		gl.glColor3iv(arg0);
	}

	@Override
	public void glColor3s(short arg0, short arg1, short arg2) {
		gl.glColor3s(arg0, arg1, arg2);
	}

	@Override
	public void glColor3sv(short[] arg0, int arg1) {
		gl.glColor3sv(arg0, arg1);
	}

	@Override
	public void glColor3sv(ShortBuffer arg0) {
		gl.glColor3sv(arg0);
	}

	@Override
	public void glColor3ub(byte arg0, byte arg1, byte arg2) {
		gl.glColor3ub(arg0, arg1, arg2);
	}

	@Override
	public void glColor3ubv(byte[] arg0, int arg1) {
		gl.glColor3ubv(arg0, arg1);
	}

	@Override
	public void glColor3ubv(ByteBuffer arg0) {
		gl.glColor3ubv(arg0);
	}

	@Override
	public void glColor3ui(int arg0, int arg1, int arg2) {
		gl.glColor3ui(arg0, arg1, arg2);
	}

	@Override
	public void glColor3uiv(int[] arg0, int arg1) {
		gl.glColor3uiv(arg0, arg1);
	}

	@Override
	public void glColor3uiv(IntBuffer arg0) {
		gl.glColor3uiv(arg0);
	}

	@Override
	public void glColor3us(short arg0, short arg1, short arg2) {
		gl.glColor3us(arg0, arg1, arg2);
	}

	@Override
	public void glColor3usv(short[] arg0, int arg1) {
		gl.glColor3usv(arg0, arg1);
	}

	@Override
	public void glColor3usv(ShortBuffer arg0) {
		gl.glColor3usv(arg0);
	}

	@Override
	public void glColor4b(byte arg0, byte arg1, byte arg2, byte arg3) {
		gl.glColor4b(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4bv(byte[] arg0, int arg1) {
		gl.glColor4bv(arg0, arg1);
	}

	@Override
	public void glColor4bv(ByteBuffer arg0) {
		gl.glColor4bv(arg0);
	}

	@Override
	public void glColor4d(double arg0, double arg1, double arg2, double arg3) {
		gl.glColor4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4dv(double[] arg0, int arg1) {
		gl.glColor4dv(arg0, arg1);
	}

	@Override
	public void glColor4dv(DoubleBuffer arg0) {
		gl.glColor4dv(arg0);
	}

	@Override
	public void glColor4f(float arg0, float arg1, float arg2, float arg3) {
		gl.glColor4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4fv(float[] arg0, int arg1) {
		gl.glColor4fv(arg0, arg1);
	}

	@Override
	public void glColor4fv(FloatBuffer arg0) {
		gl.glColor4fv(arg0);
	}

	@Override
	public void glColor4h(short arg0, short arg1, short arg2, short arg3) {
		gl.glColor4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4hv(short[] arg0, int arg1) {
		gl.glColor4hv(arg0, arg1);
	}

	@Override
	public void glColor4hv(ShortBuffer arg0) {
		gl.glColor4hv(arg0);
	}

	@Override
	public void glColor4i(int arg0, int arg1, int arg2, int arg3) {
		gl.glColor4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4iv(int[] arg0, int arg1) {
		gl.glColor4iv(arg0, arg1);
	}

	@Override
	public void glColor4iv(IntBuffer arg0) {
		gl.glColor4iv(arg0);
	}

	@Override
	public void glColor4s(short arg0, short arg1, short arg2, short arg3) {
		gl.glColor4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4sv(short[] arg0, int arg1) {
		gl.glColor4sv(arg0, arg1);
	}

	@Override
	public void glColor4sv(ShortBuffer arg0) {
		gl.glColor4sv(arg0);
	}

	@Override
	public void glColor4ub(byte arg0, byte arg1, byte arg2, byte arg3) {
		gl.glColor4ub(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4ubv(byte[] arg0, int arg1) {
		gl.glColor4ubv(arg0, arg1);
	}

	@Override
	public void glColor4ubv(ByteBuffer arg0) {
		gl.glColor4ubv(arg0);
	}

	@Override
	public void glColor4ui(int arg0, int arg1, int arg2, int arg3) {
		gl.glColor4ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4uiv(int[] arg0, int arg1) {
		gl.glColor4uiv(arg0, arg1);
	}

	@Override
	public void glColor4uiv(IntBuffer arg0) {
		gl.glColor4uiv(arg0);
	}

	@Override
	public void glColor4us(short arg0, short arg1, short arg2, short arg3) {
		gl.glColor4us(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColor4usv(short[] arg0, int arg1) {
		gl.glColor4usv(arg0, arg1);
	}

	@Override
	public void glColor4usv(ShortBuffer arg0) {
		gl.glColor4usv(arg0);
	}

	@Override
	public void glColorFormatNV(int arg0, int arg1, int arg2) {
		gl.glColorFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glColorMask(boolean arg0, boolean arg1, boolean arg2, boolean arg3) {
		gl.glColorMask(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorMaskIndexed(int arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
		gl.glColorMaskIndexed(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glColorMaski(int arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4) {
		gl.glColorMaski(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glColorMaterial(int arg0, int arg1) {
		gl.glColorMaterial(arg0, arg1);
	}

	@Override
	public void glColorPointer(GLArrayData arg0) {
		gl.glColorPointer(arg0);
	}

	@Override
	public void glColorPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorPointer(int arg0, int arg1, int arg2, long arg3) {
		gl.glColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glColorSubTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glColorSubTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTable(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glColorTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTable(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glColorTable(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glColorTableParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glColorTableParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorTableParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glColorTableParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glColorTableParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glColorTableParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glColorTableParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glColorTableParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glCompileShader(int arg0) {
		gl.glCompileShader(arg0);
	}

	@Override
	public void glCompileShaderARB(int arg0) {
		gl.glCompileShaderARB(arg0);
	}

	@Override
	public void glCompileShaderIncludeARB(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl.glCompileShaderIncludeARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCompileShaderIncludeARB(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl.glCompileShaderIncludeARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glCompressedMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glCompressedMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl.glCompressedMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedMultiTexImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl.glCompressedMultiTexImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glCompressedMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl.glCompressedMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, int arg10, Buffer arg11) {
		gl.glCompressedMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glCompressedTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glCompressedTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl.glCompressedTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl.glCompressedTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, long arg7) {
		gl.glCompressedTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl.glCompressedTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			long arg8) {
		gl.glCompressedTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glCompressedTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl.glCompressedTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCompressedTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl.glCompressedTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, long arg8) {
		gl.glCompressedTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, Buffer arg10) {
		gl.glCompressedTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glCompressedTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, long arg10) {
		gl.glCompressedTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glCompressedTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glCompressedTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, Buffer arg8) {
		gl.glCompressedTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCompressedTextureImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl.glCompressedTextureImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glCompressedTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCompressedTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, Buffer arg9) {
		gl.glCompressedTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCompressedTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9, int arg10, Buffer arg11) {
		gl.glCompressedTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl.glConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glConvolutionParameterf(int arg0, int arg1, float arg2) {
		gl.glConvolutionParameterf(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glConvolutionParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glConvolutionParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glConvolutionParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameteri(int arg0, int arg1, int arg2) {
		gl.glConvolutionParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glConvolutionParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glConvolutionParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glConvolutionParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glConvolutionParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glCopyBufferSubData(int arg0, int arg1, long arg2, long arg3, long arg4) {
		gl.glCopyBufferSubData(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyColorSubTable(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glCopyColorSubTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyColorTable(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glCopyColorTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyConvolutionFilter1D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glCopyConvolutionFilter1D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyConvolutionFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glCopyConvolutionFilter2D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glCopyImageSubDataNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
		gl.glCopyImageSubDataNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13,
				arg14);
	}

	@Override
	public void glCopyMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl.glCopyMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl.glCopyMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glCopyMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl.glCopyMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9) {
		gl.glCopyMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCopyPathNV(int arg0, int arg1) {
		gl.glCopyPathNV(arg0, arg1);
	}

	@Override
	public void glCopyPixels(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glCopyPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glCopyTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glCopyTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl.glCopyTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glCopyTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glCopyTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl.glCopyTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8) {
		gl.glCopyTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl.glCopyTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCopyTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8) {
		gl.glCopyTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glCopyTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCopyTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8) {
		gl.glCopyTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glCopyTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			int arg7, int arg8, int arg9) {
		gl.glCopyTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glCoverFillPathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, float[] arg6,
			int arg7) {
		gl.glCoverFillPathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCoverFillPathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5,
			FloatBuffer arg6) {
		gl.glCoverFillPathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCoverFillPathNV(int arg0, int arg1) {
		gl.glCoverFillPathNV(arg0, arg1);
	}

	@Override
	public void glCoverStrokePathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5,
			float[] arg6, int arg7) {
		gl.glCoverStrokePathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glCoverStrokePathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5,
			FloatBuffer arg6) {
		gl.glCoverStrokePathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glCoverStrokePathNV(int arg0, int arg1) {
		gl.glCoverStrokePathNV(arg0, arg1);
	}

	@Override
	public int glCreateProgram() {
		return gl.glCreateProgram();
	}

	@Override
	public int glCreateProgramObjectARB() {
		return gl.glCreateProgramObjectARB();
	}

	@Override
	public int glCreateShader(int arg0) {
		return gl.glCreateShader(arg0);
	}

	@Override
	public int glCreateShaderObjectARB(int arg0) {
		return gl.glCreateShaderObjectARB(arg0);
	}

	@Override
	public long glCreateSyncFromCLeventARB(long arg0, long arg1, int arg2) {
		return gl.glCreateSyncFromCLeventARB(arg0, arg1, arg2);
	}

	@Override
	public void glCullFace(int arg0) {
		gl.glCullFace(arg0);
	}

	@Override
	public void glCullParameterdvEXT(int arg0, double[] arg1, int arg2) {
		gl.glCullParameterdvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glCullParameterdvEXT(int arg0, DoubleBuffer arg1) {
		gl.glCullParameterdvEXT(arg0, arg1);
	}

	@Override
	public void glCullParameterfvEXT(int arg0, float[] arg1, int arg2) {
		gl.glCullParameterfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glCullParameterfvEXT(int arg0, FloatBuffer arg1) {
		gl.glCullParameterfvEXT(arg0, arg1);
	}

	@Override
	public void glCurrentPaletteMatrix(int arg0) {
		gl.glCurrentPaletteMatrix(arg0);
	}

	@Override
	public void glDebugMessageControl(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5, boolean arg6) {
		gl.glDebugMessageControl(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glDebugMessageControl(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4, boolean arg5) {
		gl.glDebugMessageControl(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDebugMessageEnableAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, boolean arg5) {
		gl.glDebugMessageEnableAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDebugMessageEnableAMD(int arg0, int arg1, int arg2, IntBuffer arg3, boolean arg4) {
		gl.glDebugMessageEnableAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDebugMessageInsert(int arg0, int arg1, int arg2, int arg3, int arg4, String arg5) {
		gl.glDebugMessageInsert(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDebugMessageInsertAMD(int arg0, int arg1, int arg2, int arg3, String arg4) {
		gl.glDebugMessageInsertAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDeleteBuffers(int arg0, int[] arg1, int arg2) {
		gl.glDeleteBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteBuffers(int arg0, IntBuffer arg1) {
		gl.glDeleteBuffers(arg0, arg1);
	}

	@Override
	public void glDeleteFencesAPPLE(int arg0, int[] arg1, int arg2) {
		gl.glDeleteFencesAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFencesAPPLE(int arg0, IntBuffer arg1) {
		gl.glDeleteFencesAPPLE(arg0, arg1);
	}

	@Override
	public void glDeleteFencesNV(int arg0, int[] arg1, int arg2) {
		gl.glDeleteFencesNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFencesNV(int arg0, IntBuffer arg1) {
		gl.glDeleteFencesNV(arg0, arg1);
	}

	@Override
	public void glDeleteFramebuffers(int arg0, int[] arg1, int arg2) {
		gl.glDeleteFramebuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteFramebuffers(int arg0, IntBuffer arg1) {
		gl.glDeleteFramebuffers(arg0, arg1);
	}

	@Override
	public void glDeleteLists(int arg0, int arg1) {
		gl.glDeleteLists(arg0, arg1);
	}

	@Override
	public void glDeleteNamedStringARB(int arg0, String arg1) {
		gl.glDeleteNamedStringARB(arg0, arg1);
	}

	@Override
	public void glDeleteNamesAMD(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glDeleteNamesAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDeleteNamesAMD(int arg0, int arg1, IntBuffer arg2) {
		gl.glDeleteNamesAMD(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteObjectARB(int arg0) {
		gl.glDeleteObjectARB(arg0);
	}

	@Override
	public void glDeleteOcclusionQueriesNV(int arg0, int[] arg1, int arg2) {
		gl.glDeleteOcclusionQueriesNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteOcclusionQueriesNV(int arg0, IntBuffer arg1) {
		gl.glDeleteOcclusionQueriesNV(arg0, arg1);
	}

	@Override
	public void glDeletePathsNV(int arg0, int arg1) {
		gl.glDeletePathsNV(arg0, arg1);
	}

	@Override
	public void glDeletePerfMonitorsAMD(int arg0, int[] arg1, int arg2) {
		gl.glDeletePerfMonitorsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glDeletePerfMonitorsAMD(int arg0, IntBuffer arg1) {
		gl.glDeletePerfMonitorsAMD(arg0, arg1);
	}

	@Override
	public void glDeleteProgram(int arg0) {
		gl.glDeleteProgram(arg0);
	}

	@Override
	public void glDeleteProgramsARB(int arg0, int[] arg1, int arg2) {
		gl.glDeleteProgramsARB(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteProgramsARB(int arg0, IntBuffer arg1) {
		gl.glDeleteProgramsARB(arg0, arg1);
	}

	@Override
	public void glDeleteQueries(int arg0, int[] arg1, int arg2) {
		gl.glDeleteQueries(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteQueries(int arg0, IntBuffer arg1) {
		gl.glDeleteQueries(arg0, arg1);
	}

	@Override
	public void glDeleteRenderbuffers(int arg0, int[] arg1, int arg2) {
		gl.glDeleteRenderbuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteRenderbuffers(int arg0, IntBuffer arg1) {
		gl.glDeleteRenderbuffers(arg0, arg1);
	}

	@Override
	public void glDeleteShader(int arg0) {
		gl.glDeleteShader(arg0);
	}

	@Override
	public void glDeleteTextures(int arg0, int[] arg1, int arg2) {
		gl.glDeleteTextures(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteTextures(int arg0, IntBuffer arg1) {
		gl.glDeleteTextures(arg0, arg1);
	}

	@Override
	public void glDeleteTransformFeedbacksNV(int arg0, int[] arg1, int arg2) {
		gl.glDeleteTransformFeedbacksNV(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteTransformFeedbacksNV(int arg0, IntBuffer arg1) {
		gl.glDeleteTransformFeedbacksNV(arg0, arg1);
	}

	@Override
	public void glDeleteVertexArrays(int arg0, int[] arg1, int arg2) {
		gl.glDeleteVertexArrays(arg0, arg1, arg2);
	}

	@Override
	public void glDeleteVertexArrays(int arg0, IntBuffer arg1) {
		gl.glDeleteVertexArrays(arg0, arg1);
	}

	@Override
	public void glDeleteVertexShaderEXT(int arg0) {
		gl.glDeleteVertexShaderEXT(arg0);
	}

	@Override
	public void glDepthBoundsEXT(double arg0, double arg1) {
		gl.glDepthBoundsEXT(arg0, arg1);
	}

	@Override
	public void glDepthFunc(int arg0) {
		gl.glDepthFunc(arg0);
	}

	@Override
	public void glDepthMask(boolean arg0) {
		gl.glDepthMask(arg0);
	}

	@Override
	public void glDepthRange(double arg0, double arg1) {
		gl.glDepthRange(arg0, arg1);
	}

	@Override
	public void glDepthRangef(float arg0, float arg1) {
		gl.glDepthRangef(arg0, arg1);
	}

	@Override
	public void glDetachObjectARB(int arg0, int arg1) {
		gl.glDetachObjectARB(arg0, arg1);
	}

	@Override
	public void glDetachShader(int arg0, int arg1) {
		gl.glDetachShader(arg0, arg1);
	}

	@Override
	public void glDisable(int arg0) {
		gl.glDisable(arg0);
	}

	@Override
	public void glDisableClientState(int arg0) {
		gl.glDisableClientState(arg0);
	}

	@Override
	public void glDisableClientStateIndexedEXT(int arg0, int arg1) {
		gl.glDisableClientStateIndexedEXT(arg0, arg1);
	}

	@Override
	public void glDisableClientStateiEXT(int arg0, int arg1) {
		gl.glDisableClientStateiEXT(arg0, arg1);
	}

	@Override
	public void glDisableIndexed(int arg0, int arg1) {
		gl.glDisableIndexed(arg0, arg1);
	}

	@Override
	public void glDisableVariantClientStateEXT(int arg0) {
		gl.glDisableVariantClientStateEXT(arg0);
	}

	@Override
	public void glDisableVertexArrayAttribEXT(int arg0, int arg1) {
		gl.glDisableVertexArrayAttribEXT(arg0, arg1);
	}

	@Override
	public void glDisableVertexArrayEXT(int arg0, int arg1) {
		gl.glDisableVertexArrayEXT(arg0, arg1);
	}

	@Override
	public void glDisableVertexAttribAPPLE(int arg0, int arg1) {
		gl.glDisableVertexAttribAPPLE(arg0, arg1);
	}

	@Override
	public void glDisableVertexAttribArray(int arg0) {
		gl.glDisableVertexAttribArray(arg0);
	}

	@Override
	public void glDisableVertexAttribArrayARB(int arg0) {
		gl.glDisableVertexAttribArrayARB(arg0);
	}

	@Override
	public void glDisablei(int arg0, int arg1) {
		gl.glDisablei(arg0, arg1);
	}

	@Override
	public void glDrawArrays(int arg0, int arg1, int arg2) {
		gl.glDrawArrays(arg0, arg1, arg2);
	}

	@Override
	public void glDrawArraysInstanced(int arg0, int arg1, int arg2, int arg3) {
		gl.glDrawArraysInstanced(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawBuffer(int arg0) {
		gl.glDrawBuffer(arg0);
	}

	@Override
	public void glDrawBuffers(int arg0, int[] arg1, int arg2) {
		gl.glDrawBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glDrawBuffers(int arg0, IntBuffer arg1) {
		gl.glDrawBuffers(arg0, arg1);
	}

	@Override
	public void glDrawBuffersATI(int arg0, int[] arg1, int arg2) {
		gl.glDrawBuffersATI(arg0, arg1, arg2);
	}

	@Override
	public void glDrawBuffersATI(int arg0, IntBuffer arg1) {
		gl.glDrawBuffersATI(arg0, arg1);
	}

	@Override
	public void glDrawElements(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glDrawElements(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawElements(int arg0, int arg1, int arg2, long arg3) {
		gl.glDrawElements(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glDrawElementsInstanced(int arg0, int arg1, int arg2, Buffer arg3, int arg4) {
		gl.glDrawElementsInstanced(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawElementsInstanced(int arg0, int arg1, int arg2, long arg3, int arg4) {
		gl.glDrawElementsInstanced(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawPixels(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glDrawPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawPixels(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glDrawPixels(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glDrawRangeElements(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glDrawRangeElements(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawRangeElements(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glDrawRangeElements(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glDrawTextureNV(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5, float arg6,
			float arg7, float arg8, float arg9, float arg10) {
		gl.glDrawTextureNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glDrawTransformFeedbackNV(int arg0, int arg1) {
		gl.glDrawTransformFeedbackNV(arg0, arg1);
	}

	@Override
	public void glEdgeFlag(boolean arg0) {
		gl.glEdgeFlag(arg0);
	}

	@Override
	public void glEdgeFlagFormatNV(int arg0) {
		gl.glEdgeFlagFormatNV(arg0);
	}

	@Override
	public void glEdgeFlagPointer(int arg0, Buffer arg1) {
		gl.glEdgeFlagPointer(arg0, arg1);
	}

	@Override
	public void glEdgeFlagPointer(int arg0, long arg1) {
		gl.glEdgeFlagPointer(arg0, arg1);
	}

	@Override
	public void glEdgeFlagv(byte[] arg0, int arg1) {
		gl.glEdgeFlagv(arg0, arg1);
	}

	@Override
	public void glEdgeFlagv(ByteBuffer arg0) {
		gl.glEdgeFlagv(arg0);
	}

	@Override
	public void glEnable(int arg0) {
		gl.glEnable(arg0);
	}

	@Override
	public void glEnableClientState(int arg0) {
		gl.glEnableClientState(arg0);
	}

	@Override
	public void glEnableClientStateIndexedEXT(int arg0, int arg1) {
		gl.glEnableClientStateIndexedEXT(arg0, arg1);
	}

	@Override
	public void glEnableClientStateiEXT(int arg0, int arg1) {
		gl.glEnableClientStateiEXT(arg0, arg1);
	}

	@Override
	public void glEnableIndexed(int arg0, int arg1) {
		gl.glEnableIndexed(arg0, arg1);
	}

	@Override
	public void glEnableVariantClientStateEXT(int arg0) {
		gl.glEnableVariantClientStateEXT(arg0);
	}

	@Override
	public void glEnableVertexArrayAttribEXT(int arg0, int arg1) {
		gl.glEnableVertexArrayAttribEXT(arg0, arg1);
	}

	@Override
	public void glEnableVertexArrayEXT(int arg0, int arg1) {
		gl.glEnableVertexArrayEXT(arg0, arg1);
	}

	@Override
	public void glEnableVertexAttribAPPLE(int arg0, int arg1) {
		gl.glEnableVertexAttribAPPLE(arg0, arg1);
	}

	@Override
	public void glEnableVertexAttribArray(int arg0) {
		gl.glEnableVertexAttribArray(arg0);
	}

	@Override
	public void glEnableVertexAttribArrayARB(int arg0) {
		gl.glEnableVertexAttribArrayARB(arg0);
	}

	@Override
	public void glEnablei(int arg0, int arg1) {
		gl.glEnablei(arg0, arg1);
	}

	@Override
	public void glEnd() {
		gl.glEnd();
	}

	@Override
	public void glEndConditionalRender() {
		gl.glEndConditionalRender();
	}

	@Override
	public void glEndConditionalRenderNVX() {
		gl.glEndConditionalRenderNVX();
	}

	@Override
	public void glEndList() {
		gl.glEndList();
	}

	@Override
	public void glEndOcclusionQueryNV() {
		gl.glEndOcclusionQueryNV();
	}

	@Override
	public void glEndPerfMonitorAMD(int arg0) {
		gl.glEndPerfMonitorAMD(arg0);
	}

	@Override
	public void glEndQuery(int arg0) {
		gl.glEndQuery(arg0);
	}

	@Override
	public void glEndTransformFeedback() {
		gl.glEndTransformFeedback();
	}

	@Override
	public void glEndVertexShaderEXT() {
		gl.glEndVertexShaderEXT();
	}

	@Override
	public void glEndVideoCaptureNV(int arg0) {
		gl.glEndVideoCaptureNV(arg0);
	}

	@Override
	public void glEvalCoord1d(double arg0) {
		gl.glEvalCoord1d(arg0);
	}

	@Override
	public void glEvalCoord1dv(double[] arg0, int arg1) {
		gl.glEvalCoord1dv(arg0, arg1);
	}

	@Override
	public void glEvalCoord1dv(DoubleBuffer arg0) {
		gl.glEvalCoord1dv(arg0);
	}

	@Override
	public void glEvalCoord1f(float arg0) {
		gl.glEvalCoord1f(arg0);
	}

	@Override
	public void glEvalCoord1fv(float[] arg0, int arg1) {
		gl.glEvalCoord1fv(arg0, arg1);
	}

	@Override
	public void glEvalCoord1fv(FloatBuffer arg0) {
		gl.glEvalCoord1fv(arg0);
	}

	@Override
	public void glEvalCoord2d(double arg0, double arg1) {
		gl.glEvalCoord2d(arg0, arg1);
	}

	@Override
	public void glEvalCoord2dv(double[] arg0, int arg1) {
		gl.glEvalCoord2dv(arg0, arg1);
	}

	@Override
	public void glEvalCoord2dv(DoubleBuffer arg0) {
		gl.glEvalCoord2dv(arg0);
	}

	@Override
	public void glEvalCoord2f(float arg0, float arg1) {
		gl.glEvalCoord2f(arg0, arg1);
	}

	@Override
	public void glEvalCoord2fv(float[] arg0, int arg1) {
		gl.glEvalCoord2fv(arg0, arg1);
	}

	@Override
	public void glEvalCoord2fv(FloatBuffer arg0) {
		gl.glEvalCoord2fv(arg0);
	}

	@Override
	public void glEvalMapsNV(int arg0, int arg1) {
		gl.glEvalMapsNV(arg0, arg1);
	}

	@Override
	public void glEvalMesh1(int arg0, int arg1, int arg2) {
		gl.glEvalMesh1(arg0, arg1, arg2);
	}

	@Override
	public void glEvalMesh2(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glEvalMesh2(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glEvalPoint1(int arg0) {
		gl.glEvalPoint1(arg0);
	}

	@Override
	public void glEvalPoint2(int arg0, int arg1) {
		gl.glEvalPoint2(arg0, arg1);
	}

	@Override
	public void glExtractComponentEXT(int arg0, int arg1, int arg2) {
		gl.glExtractComponentEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFeedbackBuffer(int arg0, int arg1, FloatBuffer arg2) {
		gl.glFeedbackBuffer(arg0, arg1, arg2);
	}

	@Override
	public void glFinish() {
		gl.glFinish();
	}

	@Override
	public void glFinishFenceAPPLE(int arg0) {
		gl.glFinishFenceAPPLE(arg0);
	}

	@Override
	public void glFinishFenceNV(int arg0) {
		gl.glFinishFenceNV(arg0);
	}

	@Override
	public void glFinishObjectAPPLE(int arg0, int arg1) {
		gl.glFinishObjectAPPLE(arg0, arg1);
	}

	@Override
	public void glFinishTextureSUNX() {
		gl.glFinishTextureSUNX();
	}

	@Override
	public void glFlush() {
		gl.glFlush();
	}

	@Override
	public void glFlushMappedBufferRange(int arg0, long arg1, long arg2) {
		gl.glFlushMappedBufferRange(arg0, arg1, arg2);
	}

	@Override
	public void glFlushMappedNamedBufferRangeEXT(int arg0, long arg1, long arg2) {
		gl.glFlushMappedNamedBufferRangeEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFlushPixelDataRangeNV(int arg0) {
		gl.glFlushPixelDataRangeNV(arg0);
	}

	@Override
	public void glFlushVertexArrayRangeAPPLE(int arg0, Buffer arg1) {
		gl.glFlushVertexArrayRangeAPPLE(arg0, arg1);
	}

	@Override
	public void glFlushVertexArrayRangeNV() {
		gl.glFlushVertexArrayRangeNV();
	}

	@Override
	public void glFogCoordFormatNV(int arg0, int arg1) {
		gl.glFogCoordFormatNV(arg0, arg1);
	}

	@Override
	public void glFogCoordPointer(int arg0, int arg1, Buffer arg2) {
		gl.glFogCoordPointer(arg0, arg1, arg2);
	}

	@Override
	public void glFogCoordPointer(int arg0, int arg1, long arg2) {
		gl.glFogCoordPointer(arg0, arg1, arg2);
	}

	@Override
	public void glFogCoordd(double arg0) {
		gl.glFogCoordd(arg0);
	}

	@Override
	public void glFogCoorddv(double[] arg0, int arg1) {
		gl.glFogCoorddv(arg0, arg1);
	}

	@Override
	public void glFogCoorddv(DoubleBuffer arg0) {
		gl.glFogCoorddv(arg0);
	}

	@Override
	public void glFogCoordf(float arg0) {
		gl.glFogCoordf(arg0);
	}

	@Override
	public void glFogCoordfv(float[] arg0, int arg1) {
		gl.glFogCoordfv(arg0, arg1);
	}

	@Override
	public void glFogCoordfv(FloatBuffer arg0) {
		gl.glFogCoordfv(arg0);
	}

	@Override
	public void glFogCoordh(short arg0) {
		gl.glFogCoordh(arg0);
	}

	@Override
	public void glFogCoordhv(short[] arg0, int arg1) {
		gl.glFogCoordhv(arg0, arg1);
	}

	@Override
	public void glFogCoordhv(ShortBuffer arg0) {
		gl.glFogCoordhv(arg0);
	}

	@Override
	public void glFogf(int arg0, float arg1) {
		gl.glFogf(arg0, arg1);
	}

	@Override
	public void glFogfv(int arg0, float[] arg1, int arg2) {
		gl.glFogfv(arg0, arg1, arg2);
	}

	@Override
	public void glFogfv(int arg0, FloatBuffer arg1) {
		gl.glFogfv(arg0, arg1);
	}

	@Override
	public void glFogi(int arg0, int arg1) {
		gl.glFogi(arg0, arg1);
	}

	@Override
	public void glFogiv(int arg0, int[] arg1, int arg2) {
		gl.glFogiv(arg0, arg1, arg2);
	}

	@Override
	public void glFogiv(int arg0, IntBuffer arg1) {
		gl.glFogiv(arg0, arg1);
	}

	@Override
	public void glFrameTerminatorGREMEDY() {
		gl.glFrameTerminatorGREMEDY();
	}

	@Override
	public void glFramebufferDrawBufferEXT(int arg0, int arg1) {
		gl.glFramebufferDrawBufferEXT(arg0, arg1);
	}

	@Override
	public void glFramebufferDrawBuffersEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glFramebufferDrawBuffersEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferDrawBuffersEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glFramebufferDrawBuffersEXT(arg0, arg1, arg2);
	}

	@Override
	public void glFramebufferReadBufferEXT(int arg0, int arg1) {
		gl.glFramebufferReadBufferEXT(arg0, arg1);
	}

	@Override
	public void glFramebufferRenderbuffer(int arg0, int arg1, int arg2, int arg3) {
		gl.glFramebufferRenderbuffer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTexture1D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTexture1D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTexture2D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTexture2D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTexture3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glFramebufferTexture3D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glFramebufferTextureARB(int arg0, int arg1, int arg2, int arg3) {
		gl.glFramebufferTextureARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTextureEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glFramebufferTextureEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glFramebufferTextureFaceARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTextureFaceARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureFaceEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTextureFaceEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayer(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTextureLayer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayerARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTextureLayerARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFramebufferTextureLayerEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glFramebufferTextureLayerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glFreeMemoryNV(ByteBuffer arg0) {
		gl.glFreeMemoryNV(arg0);
	}

	@Override
	public void glFrontFace(int arg0) {
		gl.glFrontFace(arg0);
	}

	@Override
	public void glFrustum(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) {
		gl.glFrustum(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glFrustumf(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) {
		gl.glFrustumf(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGenBuffers(int arg0, int[] arg1, int arg2) {
		gl.glGenBuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenBuffers(int arg0, IntBuffer arg1) {
		gl.glGenBuffers(arg0, arg1);
	}

	@Override
	public void glGenFencesAPPLE(int arg0, int[] arg1, int arg2) {
		gl.glGenFencesAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glGenFencesAPPLE(int arg0, IntBuffer arg1) {
		gl.glGenFencesAPPLE(arg0, arg1);
	}

	@Override
	public void glGenFencesNV(int arg0, int[] arg1, int arg2) {
		gl.glGenFencesNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenFencesNV(int arg0, IntBuffer arg1) {
		gl.glGenFencesNV(arg0, arg1);
	}

	@Override
	public void glGenFramebuffers(int arg0, int[] arg1, int arg2) {
		gl.glGenFramebuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenFramebuffers(int arg0, IntBuffer arg1) {
		gl.glGenFramebuffers(arg0, arg1);
	}

	@Override
	public int glGenLists(int arg0) {
		return gl.glGenLists(arg0);
	}

	@Override
	public void glGenNamesAMD(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGenNamesAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGenNamesAMD(int arg0, int arg1, IntBuffer arg2) {
		gl.glGenNamesAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGenOcclusionQueriesNV(int arg0, int[] arg1, int arg2) {
		gl.glGenOcclusionQueriesNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenOcclusionQueriesNV(int arg0, IntBuffer arg1) {
		gl.glGenOcclusionQueriesNV(arg0, arg1);
	}

	@Override
	public int glGenPathsNV(int arg0) {
		return gl.glGenPathsNV(arg0);
	}

	@Override
	public void glGenPerfMonitorsAMD(int arg0, int[] arg1, int arg2) {
		gl.glGenPerfMonitorsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGenPerfMonitorsAMD(int arg0, IntBuffer arg1) {
		gl.glGenPerfMonitorsAMD(arg0, arg1);
	}

	@Override
	public void glGenProgramsARB(int arg0, int[] arg1, int arg2) {
		gl.glGenProgramsARB(arg0, arg1, arg2);
	}

	@Override
	public void glGenProgramsARB(int arg0, IntBuffer arg1) {
		gl.glGenProgramsARB(arg0, arg1);
	}

	@Override
	public void glGenQueries(int arg0, int[] arg1, int arg2) {
		gl.glGenQueries(arg0, arg1, arg2);
	}

	@Override
	public void glGenQueries(int arg0, IntBuffer arg1) {
		gl.glGenQueries(arg0, arg1);
	}

	@Override
	public void glGenRenderbuffers(int arg0, int[] arg1, int arg2) {
		gl.glGenRenderbuffers(arg0, arg1, arg2);
	}

	@Override
	public void glGenRenderbuffers(int arg0, IntBuffer arg1) {
		gl.glGenRenderbuffers(arg0, arg1);
	}

	@Override
	public int glGenSymbolsEXT(int arg0, int arg1, int arg2, int arg3) {
		return gl.glGenSymbolsEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGenTextures(int arg0, int[] arg1, int arg2) {
		gl.glGenTextures(arg0, arg1, arg2);
	}

	@Override
	public void glGenTextures(int arg0, IntBuffer arg1) {
		gl.glGenTextures(arg0, arg1);
	}

	@Override
	public void glGenTransformFeedbacksNV(int arg0, int[] arg1, int arg2) {
		gl.glGenTransformFeedbacksNV(arg0, arg1, arg2);
	}

	@Override
	public void glGenTransformFeedbacksNV(int arg0, IntBuffer arg1) {
		gl.glGenTransformFeedbacksNV(arg0, arg1);
	}

	@Override
	public void glGenVertexArrays(int arg0, int[] arg1, int arg2) {
		gl.glGenVertexArrays(arg0, arg1, arg2);
	}

	@Override
	public void glGenVertexArrays(int arg0, IntBuffer arg1) {
		gl.glGenVertexArrays(arg0, arg1);
	}

	@Override
	public int glGenVertexShadersEXT(int arg0) {
		return gl.glGenVertexShadersEXT(arg0);
	}

	@Override
	public void glGenerateMipmap(int arg0) {
		gl.glGenerateMipmap(arg0);
	}

	@Override
	public void glGenerateMultiTexMipmapEXT(int arg0, int arg1) {
		gl.glGenerateMultiTexMipmapEXT(arg0, arg1);
	}

	@Override
	public void glGenerateTextureMipmapEXT(int arg0, int arg1) {
		gl.glGenerateTextureMipmapEXT(arg0, arg1);
	}

	@Override
	public void glGetActiveAttrib(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6, int[] arg7,
			int arg8, byte[] arg9, int arg10) {
		gl.glGetActiveAttrib(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveAttrib(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl.glGetActiveAttrib(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniform(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveUniform(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl.glGetActiveUniform(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformARB(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl.glGetActiveUniformARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetActiveUniformARB(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			ByteBuffer arg6) {
		gl.glGetActiveUniformARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformBlockName(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl.glGetActiveUniformBlockName(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformBlockName(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl.glGetActiveUniformBlockName(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformBlockiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetActiveUniformBlockiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformBlockiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetActiveUniformBlockiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetActiveUniformName(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl.glGetActiveUniformName(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformName(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl.glGetActiveUniformName(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetActiveUniformsiv(int arg0, int arg1, int[] arg2, int arg3, int arg4, int[] arg5, int arg6) {
		gl.glGetActiveUniformsiv(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetActiveUniformsiv(int arg0, int arg1, IntBuffer arg2, int arg3, IntBuffer arg4) {
		gl.glGetActiveUniformsiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetAttachedObjectsARB(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetAttachedObjectsARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetAttachedObjectsARB(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl.glGetAttachedObjectsARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetAttachedShaders(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetAttachedShaders(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetAttachedShaders(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl.glGetAttachedShaders(arg0, arg1, arg2, arg3);
	}

	@Override
	public int glGetAttribLocation(int arg0, String arg1) {
		return gl.glGetAttribLocation(arg0, arg1);
	}

	@Override
	public void glGetBooleanIndexedv(int arg0, int arg1, byte[] arg2, int arg3) {
		gl.glGetBooleanIndexedv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBooleanIndexedv(int arg0, int arg1, ByteBuffer arg2) {
		gl.glGetBooleanIndexedv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleani_v(int arg0, int arg1, byte[] arg2, int arg3) {
		gl.glGetBooleani_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBooleani_v(int arg0, int arg1, ByteBuffer arg2) {
		gl.glGetBooleani_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleanv(int arg0, byte[] arg1, int arg2) {
		gl.glGetBooleanv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBooleanv(int arg0, ByteBuffer arg1) {
		gl.glGetBooleanv(arg0, arg1);
	}

	@Override
	public int glGetBoundBuffer(int arg0) {
		return gl.glGetBoundBuffer(arg0);
	}

	@Override
	public void glGetBufferParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetBufferParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBufferParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetBufferParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetBufferParameterui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetBufferParameterui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetBufferParameterui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetBufferParameterui64vNV(arg0, arg1, arg2);
	}

	@Override
	public long glGetBufferSize(int arg0) {
		return gl.glGetBufferSize(arg0);
	}

	@Override
	public void glGetBufferSubData(int arg0, long arg1, long arg2, Buffer arg3) {
		gl.glGetBufferSubData(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetClipPlane(int arg0, double[] arg1, int arg2) {
		gl.glGetClipPlane(arg0, arg1, arg2);
	}

	@Override
	public void glGetClipPlane(int arg0, DoubleBuffer arg1) {
		gl.glGetClipPlane(arg0, arg1);
	}

	@Override
	public void glGetClipPlanef(int arg0, float[] arg1, int arg2) {
		gl.glGetClipPlanef(arg0, arg1, arg2);
	}

	@Override
	public void glGetClipPlanef(int arg0, FloatBuffer arg1) {
		gl.glGetClipPlanef(arg0, arg1);
	}

	@Override
	public void glGetColorTable(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetColorTable(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTable(int arg0, int arg1, int arg2, long arg3) {
		gl.glGetColorTable(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetColorTableParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetColorTableParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetColorTableParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetColorTableParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetColorTableParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetColorTableParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedMultiTexImageEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetCompressedMultiTexImageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetCompressedTexImage(int arg0, int arg1, Buffer arg2) {
		gl.glGetCompressedTexImage(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedTexImage(int arg0, int arg1, long arg2) {
		gl.glGetCompressedTexImage(arg0, arg1, arg2);
	}

	@Override
	public void glGetCompressedTextureImageEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetCompressedTextureImageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionFilter(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetConvolutionFilter(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionFilter(int arg0, int arg1, int arg2, long arg3) {
		gl.glGetConvolutionFilter(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetConvolutionParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetConvolutionParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetConvolutionParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetConvolutionParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetConvolutionParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetConvolutionParameteriv(arg0, arg1, arg2);
	}

	@Override
	public int glGetDebugMessageLog(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, int[] arg6,
			int arg7, int[] arg8, int arg9, int[] arg10, int arg11, byte[] arg12, int arg13) {
		return gl.glGetDebugMessageLog(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12,
				arg13);
	}

	@Override
	public int glGetDebugMessageLog(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, IntBuffer arg4, IntBuffer arg5,
			IntBuffer arg6, ByteBuffer arg7) {
		return gl.glGetDebugMessageLog(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public int glGetDebugMessageLogAMD(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, int[] arg6,
			int arg7, int[] arg8, int arg9, byte[] arg10, int arg11) {
		return gl.glGetDebugMessageLogAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public int glGetDebugMessageLogAMD(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, IntBuffer arg4,
			IntBuffer arg5, ByteBuffer arg6) {
		return gl.glGetDebugMessageLogAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetDoubleIndexedvEXT(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetDoubleIndexedvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetDoubleIndexedvEXT(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetDoubleIndexedvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublei_vEXT(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetDoublei_vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetDoublei_vEXT(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetDoublei_vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublev(int arg0, double[] arg1, int arg2) {
		gl.glGetDoublev(arg0, arg1, arg2);
	}

	@Override
	public void glGetDoublev(int arg0, DoubleBuffer arg1) {
		gl.glGetDoublev(arg0, arg1);
	}

	@Override
	public int glGetError() {
		return gl.glGetError();
	}

	@Override
	public void glGetFenceivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetFenceivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFenceivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetFenceivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatIndexedvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetFloatIndexedvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFloatIndexedvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetFloatIndexedvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloati_vEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetFloati_vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFloati_vEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetFloati_vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatv(int arg0, float[] arg1, int arg2) {
		gl.glGetFloatv(arg0, arg1, arg2);
	}

	@Override
	public void glGetFloatv(int arg0, FloatBuffer arg1) {
		gl.glGetFloatv(arg0, arg1);
	}

	@Override
	public int glGetFragDataLocation(int arg0, String arg1) {
		return gl.glGetFragDataLocation(arg0, arg1);
	}

	@Override
	public void glGetFramebufferAttachmentParameteriv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetFramebufferAttachmentParameteriv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetFramebufferAttachmentParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFramebufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetFramebufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetFramebufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetFramebufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public int glGetGraphicsResetStatus() {
		return gl.glGetGraphicsResetStatus();
	}

	@Override
	public int glGetHandleARB(int arg0) {
		return gl.glGetHandleARB(arg0);
	}

	@Override
	public void glGetHistogram(int arg0, boolean arg1, int arg2, int arg3, Buffer arg4) {
		gl.glGetHistogram(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetHistogram(int arg0, boolean arg1, int arg2, int arg3, long arg4) {
		gl.glGetHistogram(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetHistogramParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetHistogramParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetHistogramParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetHistogramParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetHistogramParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetHistogramParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetHistogramParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetHistogramParameteriv(arg0, arg1, arg2);
	}

	@Override
	public long glGetImageHandleNV(int arg0, int arg1, boolean arg2, int arg3, int arg4) {
		return gl.glGetImageHandleNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetInfoLogARB(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetInfoLogARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetInfoLogARB(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetInfoLogARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerIndexedv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetIntegerIndexedv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerIndexedv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetIntegerIndexedv(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegeri_v(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetIntegeri_v(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegeri_v(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetIntegeri_v(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64i_vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetIntegerui64i_vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetIntegerui64i_vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetIntegerui64i_vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64vNV(int arg0, long[] arg1, int arg2) {
		gl.glGetIntegerui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerui64vNV(int arg0, LongBuffer arg1) {
		gl.glGetIntegerui64vNV(arg0, arg1);
	}

	@Override
	public void glGetIntegerv(int arg0, int[] arg1, int arg2) {
		gl.glGetIntegerv(arg0, arg1, arg2);
	}

	@Override
	public void glGetIntegerv(int arg0, IntBuffer arg1) {
		gl.glGetIntegerv(arg0, arg1);
	}

	@Override
	public void glGetInternalformativ(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetInternalformativ(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetInternalformativ(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glGetInternalformativ(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetInvariantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl.glGetInvariantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl.glGetInvariantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetInvariantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetInvariantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetInvariantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetInvariantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetInvariantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetInvariantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetInvariantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLightfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetLightfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLightfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetLightfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetLightiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetLightiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLightiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetLightiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl.glGetLocalConstantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl.glGetLocalConstantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetLocalConstantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetLocalConstantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetLocalConstantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetLocalConstantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetLocalConstantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetLocalConstantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapAttribParameterfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetMapAttribParameterfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMapAttribParameterfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetMapAttribParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapAttribParameterivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMapAttribParameterivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMapAttribParameterivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMapAttribParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapControlPointsNV(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5, Buffer arg6) {
		gl.glGetMapControlPointsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetMapParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetMapParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetMapParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetMapParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetMapParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapdv(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetMapdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapdv(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetMapdv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMapiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetMapiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMapiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetMapiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMaterialfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetMaterialfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMaterialfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetMaterialfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMaterialiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetMaterialiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMaterialiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetMaterialiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMinmax(int arg0, boolean arg1, int arg2, int arg3, Buffer arg4) {
		gl.glGetMinmax(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMinmax(int arg0, boolean arg1, int arg2, int arg3, long arg4) {
		gl.glGetMinmax(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMinmaxParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetMinmaxParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMinmaxParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetMinmaxParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMinmaxParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetMinmaxParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMinmaxParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetMinmaxParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetMultiTexEnvfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetMultiTexEnvfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexEnvfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetMultiTexEnvfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexEnvivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMultiTexEnvivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexEnvivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMultiTexEnvivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGendvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glGetMultiTexGendvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGendvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glGetMultiTexGendvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGenfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetMultiTexGenfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGenfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetMultiTexGenfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexGenivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMultiTexGenivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexGenivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMultiTexGenivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexImageEXT(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glGetMultiTexImageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl.glGetMultiTexLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl.glGetMultiTexLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetMultiTexLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetMultiTexLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glGetMultiTexLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMultiTexParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMultiTexParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetMultiTexParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetMultiTexParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultiTexParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetMultiTexParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetMultiTexParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetMultiTexParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultisamplefvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetMultisamplefvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetMultisamplefvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetMultisamplefvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetNamedBufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedBufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetNamedBufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferParameterui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetNamedBufferParameterui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedBufferParameterui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetNamedBufferParameterui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedBufferSubDataEXT(int arg0, long arg1, long arg2, Buffer arg3) {
		gl.glGetNamedBufferSubDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedFramebufferAttachmentParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetNamedFramebufferAttachmentParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedFramebufferAttachmentParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetNamedFramebufferAttachmentParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedFramebufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetNamedFramebufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedFramebufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetNamedFramebufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedProgramLocalParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetNamedProgramLocalParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetNamedProgramLocalParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetNamedProgramLocalParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetNamedProgramLocalParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterdvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glGetNamedProgramLocalParameterdvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterdvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glGetNamedProgramLocalParameterdvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramLocalParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetNamedProgramLocalParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramLocalParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetNamedProgramLocalParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramStringEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetNamedProgramStringEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedProgramivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetNamedProgramivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedProgramivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetNamedProgramivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedRenderbufferParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetNamedRenderbufferParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetNamedRenderbufferParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetNamedRenderbufferParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetNamedStringARB(int arg0, String arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl.glGetNamedStringARB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetNamedStringARB(int arg0, String arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl.glGetNamedStringARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedStringivARB(int arg0, String arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetNamedStringivARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetNamedStringivARB(int arg0, String arg1, int arg2, IntBuffer arg3) {
		gl.glGetNamedStringivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectLabel(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5, int arg6) {
		gl.glGetObjectLabel(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetObjectLabel(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl.glGetObjectLabel(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetObjectParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetObjectParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetObjectParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetObjectParameterivAPPLE(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetObjectParameterivAPPLE(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetObjectParameterivAPPLE(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetObjectParameterivAPPLE(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetObjectParameterivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetObjectParameterivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetObjectParameterivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetObjectPtrLabel(Buffer arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetObjectPtrLabel(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetObjectPtrLabel(Buffer arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetObjectPtrLabel(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetOcclusionQueryivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetOcclusionQueryivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetOcclusionQueryivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetOcclusionQueryivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetOcclusionQueryuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetOcclusionQueryuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetOcclusionQueryuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetOcclusionQueryuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathColorGenfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetPathColorGenfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathColorGenfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetPathColorGenfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathColorGenivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetPathColorGenivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathColorGenivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetPathColorGenivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathCommandsNV(int arg0, byte[] arg1, int arg2) {
		gl.glGetPathCommandsNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathCommandsNV(int arg0, ByteBuffer arg1) {
		gl.glGetPathCommandsNV(arg0, arg1);
	}

	@Override
	public void glGetPathCoordsNV(int arg0, float[] arg1, int arg2) {
		gl.glGetPathCoordsNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathCoordsNV(int arg0, FloatBuffer arg1) {
		gl.glGetPathCoordsNV(arg0, arg1);
	}

	@Override
	public void glGetPathDashArrayNV(int arg0, float[] arg1, int arg2) {
		gl.glGetPathDashArrayNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathDashArrayNV(int arg0, FloatBuffer arg1) {
		gl.glGetPathDashArrayNV(arg0, arg1);
	}

	@Override
	public float glGetPathLengthNV(int arg0, int arg1, int arg2) {
		return gl.glGetPathLengthNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathMetricRangeNV(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl.glGetPathMetricRangeNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetPathMetricRangeNV(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl.glGetPathMetricRangeNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPathMetricsNV(int arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5, float[] arg6, int arg7) {
		gl.glGetPathMetricsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetPathMetricsNV(int arg0, int arg1, int arg2, Buffer arg3, int arg4, int arg5, FloatBuffer arg6) {
		gl.glGetPathMetricsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetPathParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetPathParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetPathParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetPathParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetPathParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathSpacingNV(int arg0, int arg1, int arg2, Buffer arg3, int arg4, float arg5, float arg6,
			int arg7, float[] arg8, int arg9) {
		gl.glGetPathSpacingNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glGetPathSpacingNV(int arg0, int arg1, int arg2, Buffer arg3, int arg4, float arg5, float arg6,
			int arg7, FloatBuffer arg8) {
		gl.glGetPathSpacingNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glGetPathTexGenfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetPathTexGenfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathTexGenfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetPathTexGenfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPathTexGenivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetPathTexGenivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPathTexGenivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetPathTexGenivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetPerfMonitorCounterDataAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6) {
		gl.glGetPerfMonitorCounterDataAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetPerfMonitorCounterDataAMD(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4) {
		gl.glGetPerfMonitorCounterDataAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorCounterInfoAMD(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetPerfMonitorCounterInfoAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPerfMonitorCounterStringAMD(int arg0, int arg1, int arg2, int[] arg3, int arg4, byte[] arg5,
			int arg6) {
		gl.glGetPerfMonitorCounterStringAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetPerfMonitorCounterStringAMD(int arg0, int arg1, int arg2, IntBuffer arg3, ByteBuffer arg4) {
		gl.glGetPerfMonitorCounterStringAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorCountersAMD(int arg0, int[] arg1, int arg2, int[] arg3, int arg4, int arg5, int[] arg6,
			int arg7) {
		gl.glGetPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetPerfMonitorCountersAMD(int arg0, IntBuffer arg1, IntBuffer arg2, int arg3, IntBuffer arg4) {
		gl.glGetPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorGroupStringAMD(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetPerfMonitorGroupStringAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetPerfMonitorGroupStringAMD(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetPerfMonitorGroupStringAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPerfMonitorGroupsAMD(int[] arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetPerfMonitorGroupsAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetPerfMonitorGroupsAMD(IntBuffer arg0, int arg1, IntBuffer arg2) {
		gl.glGetPerfMonitorGroupsAMD(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapfv(int arg0, float[] arg1, int arg2) {
		gl.glGetPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapfv(int arg0, FloatBuffer arg1) {
		gl.glGetPixelMapfv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapfv(int arg0, long arg1) {
		gl.glGetPixelMapfv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, int[] arg1, int arg2) {
		gl.glGetPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, IntBuffer arg1) {
		gl.glGetPixelMapuiv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapuiv(int arg0, long arg1) {
		gl.glGetPixelMapuiv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapusv(int arg0, long arg1) {
		gl.glGetPixelMapusv(arg0, arg1);
	}

	@Override
	public void glGetPixelMapusv(int arg0, short[] arg1, int arg2) {
		gl.glGetPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelMapusv(int arg0, ShortBuffer arg1) {
		gl.glGetPixelMapusv(arg0, arg1);
	}

	@Override
	public void glGetPixelTransformParameterfvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetPixelTransformParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPixelTransformParameterfvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetPixelTransformParameterfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetPixelTransformParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetPixelTransformParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetPixelTransformParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetPixelTransformParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetPointeri_vEXT(int arg0, int arg1, PointerBuffer arg2) {
		gl.glGetPointeri_vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetPolygonStipple(byte[] arg0, int arg1) {
		gl.glGetPolygonStipple(arg0, arg1);
	}

	@Override
	public void glGetPolygonStipple(ByteBuffer arg0) {
		gl.glGetPolygonStipple(arg0);
	}

	@Override
	public void glGetPolygonStipple(long arg0) {
		gl.glGetPolygonStipple(arg0);
	}

	@Override
	public void glGetProgramBinary(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5, Buffer arg6) {
		gl.glGetProgramBinary(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glGetProgramBinary(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3, Buffer arg4) {
		gl.glGetProgramBinary(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetProgramEnvParameterIivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramEnvParameterIivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterIivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramEnvParameterIivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterIuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramEnvParameterIuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterIuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramEnvParameterIuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetProgramEnvParameterdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetProgramEnvParameterdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramEnvParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetProgramEnvParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramEnvParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetProgramEnvParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramInfoLog(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetProgramInfoLog(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetProgramInfoLog(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetProgramInfoLog(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramLocalParameterIivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramLocalParameterIivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterIuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramLocalParameterIuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterIuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramLocalParameterIuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetProgramLocalParameterdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetProgramLocalParameterdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramLocalParameterfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetProgramLocalParameterfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramLocalParameterfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetProgramLocalParameterfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramStringARB(int arg0, int arg1, Buffer arg2) {
		gl.glGetProgramStringARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramSubroutineParameteruivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramSubroutineParameteruivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramSubroutineParameteruivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramSubroutineParameteruivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetProgramivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetProgramivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetProgramivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetProgramivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjecti64vEXT(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetQueryObjecti64vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjecti64vEXT(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetQueryObjecti64vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetQueryObjectiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetQueryObjectiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectui64vEXT(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetQueryObjectui64vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectui64vEXT(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetQueryObjectui64vEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryObjectuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetQueryObjectuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryObjectuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetQueryObjectuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetQueryiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetQueryiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetQueryiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetQueryiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetRenderbufferParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetRenderbufferParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetRenderbufferParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetRenderbufferParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glGetSeparableFilter(int arg0, int arg1, int arg2, Buffer arg3, Buffer arg4, Buffer arg5) {
		gl.glGetSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetSeparableFilter(int arg0, int arg1, int arg2, long arg3, long arg4, long arg5) {
		gl.glGetSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderInfoLog(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetShaderInfoLog(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderInfoLog(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetShaderInfoLog(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderPrecisionFormat(int arg0, int arg1, int[] arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderPrecisionFormat(int arg0, int arg1, IntBuffer arg2, IntBuffer arg3) {
		gl.glGetShaderPrecisionFormat(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderSource(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetShaderSource(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderSource(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetShaderSource(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderSourceARB(int arg0, int arg1, int[] arg2, int arg3, byte[] arg4, int arg5) {
		gl.glGetShaderSourceARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetShaderSourceARB(int arg0, int arg1, IntBuffer arg2, ByteBuffer arg3) {
		gl.glGetShaderSourceARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetShaderiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetShaderiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetShaderiv(arg0, arg1, arg2);
	}

	@Override
	public String glGetString(int arg0) {
		return gl.glGetString(arg0);
	}

	@Override
	public String glGetStringi(int arg0, int arg1) {
		return gl.glGetStringi(arg0, arg1);
	}

	@Override
	public void glGetTexEnvfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetTexEnvfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexEnvfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetTexEnvfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexEnviv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetTexEnviv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexEnviv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetTexEnviv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGendv(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetTexGendv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGendv(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetTexGendv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGenfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetTexGenfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGenfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetTexGenfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexGeniv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetTexGeniv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexGeniv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetTexGeniv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexImage(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glGetTexImage(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexImage(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glGetTexImage(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameterfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetTexLevelParameterfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameterfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetTexLevelParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexLevelParameteriv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetTexLevelParameteriv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTexLevelParameteriv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetTexLevelParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetTexParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetTexParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetTexParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetTexParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetTexParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetTexParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetTexParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetTexParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTexParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetTexParameteriv(arg0, arg1, arg2);
	}

	@Override
	public long glGetTextureHandleNV(int arg0) {
		return gl.glGetTextureHandleNV(arg0);
	}

	@Override
	public void glGetTextureImageEXT(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glGetTextureImageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl.glGetTextureLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterfvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl.glGetTextureLevelParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glGetTextureLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetTextureLevelParameterivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glGetTextureLevelParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetTextureParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetTextureParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetTextureParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetTextureParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetTextureParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetTextureParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetTextureParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetTextureParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetTextureParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetTextureParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public long glGetTextureSamplerHandleNV(int arg0, int arg1) {
		return gl.glGetTextureSamplerHandleNV(arg0, arg1);
	}

	@Override
	public void glGetTransformFeedbackVarying(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6,
			int[] arg7, int arg8, byte[] arg9, int arg10) {
		gl.glGetTransformFeedbackVarying(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glGetTransformFeedbackVarying(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4,
			IntBuffer arg5, ByteBuffer arg6) {
		gl.glGetTransformFeedbackVarying(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public int glGetUniformBlockIndex(int arg0, String arg1) {
		return gl.glGetUniformBlockIndex(arg0, arg1);
	}

	@Override
	public int glGetUniformBufferSizeEXT(int arg0, int arg1) {
		return gl.glGetUniformBufferSizeEXT(arg0, arg1);
	}

	@Override
	public void glGetUniformIndices(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl.glGetUniformIndices(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetUniformIndices(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl.glGetUniformIndices(arg0, arg1, arg2, arg3);
	}

	@Override
	public int glGetUniformLocation(int arg0, String arg1) {
		return gl.glGetUniformLocation(arg0, arg1);
	}

	@Override
	public int glGetUniformLocationARB(int arg0, String arg1) {
		return gl.glGetUniformLocationARB(arg0, arg1);
	}

	@Override
	public long glGetUniformOffsetEXT(int arg0, int arg1) {
		return gl.glGetUniformOffsetEXT(arg0, arg1);
	}

	@Override
	public void glGetUniformfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetUniformfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetUniformfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetUniformfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetUniformfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetUniformiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetUniformiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetUniformivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetUniformivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetUniformui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetUniformuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetUniformuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetUniformuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetUniformuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantBooleanvEXT(int arg0, int arg1, byte[] arg2, int arg3) {
		gl.glGetVariantBooleanvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantBooleanvEXT(int arg0, int arg1, ByteBuffer arg2) {
		gl.glGetVariantBooleanvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantFloatvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetVariantFloatvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantFloatvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetVariantFloatvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVariantIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVariantIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVariantIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVariantIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexArrayIntegeri_vEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetVertexArrayIntegeri_vEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVertexArrayIntegeri_vEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetVertexArrayIntegeri_vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexArrayIntegervEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexArrayIntegervEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexArrayIntegervEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexArrayIntegervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexArrayPointeri_vEXT(int arg0, int arg1, int arg2, PointerBuffer arg3) {
		gl.glGetVertexArrayPointeri_vEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexArrayPointervEXT(int arg0, int arg1, PointerBuffer arg2) {
		gl.glGetVertexArrayPointervEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribIiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribIivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribIuivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribIuivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribIuivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribLi64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetVertexAttribLi64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribLi64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetVertexAttribLi64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribLui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glGetVertexAttribLui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribLui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glGetVertexAttribLui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribdv(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetVertexAttribdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribdv(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetVertexAttribdv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribdvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glGetVertexAttribdvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribdvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glGetVertexAttribdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetVertexAttribfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetVertexAttribfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribfvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetVertexAttribfvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribfvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetVertexAttribfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetVertexAttribivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVertexAttribivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVertexAttribivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVertexAttribivARB(arg0, arg1, arg2);
	}

	@Override
	public void glGetVideoCaptureStreamdvNV(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glGetVideoCaptureStreamdvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamdvNV(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glGetVideoCaptureStreamdvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureStreamfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetVideoCaptureStreamfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetVideoCaptureStreamfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureStreamivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetVideoCaptureStreamivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetVideoCaptureStreamivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetVideoCaptureStreamivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetVideoCaptureivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetVideoCaptureivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetVideoCaptureivNV(arg0, arg1, arg2);
	}

	@Override
	public void glGetnColorTable(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glGetnColorTable(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnCompressedTexImage(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glGetnCompressedTexImage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnConvolutionFilter(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glGetnConvolutionFilter(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnHistogram(int arg0, boolean arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glGetnHistogram(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnMapdv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glGetnMapdv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapdv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glGetnMapdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMapfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetnMapfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetnMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMapiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetnMapiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnMapiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetnMapiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnMinmax(int arg0, boolean arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glGetnMinmax(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnPixelMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glGetnPixelMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glGetnPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPixelMapuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glGetnPixelMapuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glGetnPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPixelMapusv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glGetnPixelMapusv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnPixelMapusv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glGetnPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPolygonStipple(int arg0, byte[] arg1, int arg2) {
		gl.glGetnPolygonStipple(arg0, arg1, arg2);
	}

	@Override
	public void glGetnPolygonStipple(int arg0, ByteBuffer arg1) {
		gl.glGetnPolygonStipple(arg0, arg1);
	}

	@Override
	public void glGetnSeparableFilter(int arg0, int arg1, int arg2, int arg3, Buffer arg4, int arg5, Buffer arg6,
			Buffer arg7) {
		gl.glGetnSeparableFilter(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glGetnTexImage(int arg0, int arg1, int arg2, int arg3, int arg4, Buffer arg5) {
		gl.glGetnTexImage(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glGetnUniformdv(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glGetnUniformdv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformdv(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glGetnUniformdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformfv(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glGetnUniformfv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformfv(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glGetnUniformfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetnUniformiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetnUniformiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glGetnUniformuiv(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glGetnUniformuiv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glGetnUniformuiv(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glGetnUniformuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glHint(int arg0, int arg1) {
		gl.glHint(arg0, arg1);
	}

	@Override
	public void glHintPGI(int arg0, int arg1) {
		gl.glHintPGI(arg0, arg1);
	}

	@Override
	public void glHistogram(int arg0, int arg1, int arg2, boolean arg3) {
		gl.glHistogram(arg0, arg1, arg2, arg3);
	}

	@Override
	public long glImportSyncEXT(int arg0, long arg1, int arg2) {
		return gl.glImportSyncEXT(arg0, arg1, arg2);
	}

	@Override
	public void glIndexFormatNV(int arg0, int arg1) {
		gl.glIndexFormatNV(arg0, arg1);
	}

	@Override
	public void glIndexFuncEXT(int arg0, float arg1) {
		gl.glIndexFuncEXT(arg0, arg1);
	}

	@Override
	public void glIndexMask(int arg0) {
		gl.glIndexMask(arg0);
	}

	@Override
	public void glIndexMaterialEXT(int arg0, int arg1) {
		gl.glIndexMaterialEXT(arg0, arg1);
	}

	@Override
	public void glIndexPointer(int arg0, int arg1, Buffer arg2) {
		gl.glIndexPointer(arg0, arg1, arg2);
	}

	@Override
	public void glIndexd(double arg0) {
		gl.glIndexd(arg0);
	}

	@Override
	public void glIndexdv(double[] arg0, int arg1) {
		gl.glIndexdv(arg0, arg1);
	}

	@Override
	public void glIndexdv(DoubleBuffer arg0) {
		gl.glIndexdv(arg0);
	}

	@Override
	public void glIndexf(float arg0) {
		gl.glIndexf(arg0);
	}

	@Override
	public void glIndexfv(float[] arg0, int arg1) {
		gl.glIndexfv(arg0, arg1);
	}

	@Override
	public void glIndexfv(FloatBuffer arg0) {
		gl.glIndexfv(arg0);
	}

	@Override
	public void glIndexi(int arg0) {
		gl.glIndexi(arg0);
	}

	@Override
	public void glIndexiv(int[] arg0, int arg1) {
		gl.glIndexiv(arg0, arg1);
	}

	@Override
	public void glIndexiv(IntBuffer arg0) {
		gl.glIndexiv(arg0);
	}

	@Override
	public void glIndexs(short arg0) {
		gl.glIndexs(arg0);
	}

	@Override
	public void glIndexsv(short[] arg0, int arg1) {
		gl.glIndexsv(arg0, arg1);
	}

	@Override
	public void glIndexsv(ShortBuffer arg0) {
		gl.glIndexsv(arg0);
	}

	@Override
	public void glIndexub(byte arg0) {
		gl.glIndexub(arg0);
	}

	@Override
	public void glIndexubv(byte[] arg0, int arg1) {
		gl.glIndexubv(arg0, arg1);
	}

	@Override
	public void glIndexubv(ByteBuffer arg0) {
		gl.glIndexubv(arg0);
	}

	@Override
	public void glInitNames() {
		gl.glInitNames();
	}

	@Override
	public void glInsertComponentEXT(int arg0, int arg1, int arg2) {
		gl.glInsertComponentEXT(arg0, arg1, arg2);
	}

	@Override
	public void glInterleavedArrays(int arg0, int arg1, Buffer arg2) {
		gl.glInterleavedArrays(arg0, arg1, arg2);
	}

	@Override
	public void glInterleavedArrays(int arg0, int arg1, long arg2) {
		gl.glInterleavedArrays(arg0, arg1, arg2);
	}

	@Override
	public void glInterpolatePathsNV(int arg0, int arg1, int arg2, float arg3) {
		gl.glInterpolatePathsNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean glIsBuffer(int arg0) {
		return gl.glIsBuffer(arg0);
	}

	@Override
	public boolean glIsBufferResidentNV(int arg0) {
		return gl.glIsBufferResidentNV(arg0);
	}

	@Override
	public boolean glIsEnabled(int arg0) {
		return gl.glIsEnabled(arg0);
	}

	@Override
	public boolean glIsEnabledIndexed(int arg0, int arg1) {
		return gl.glIsEnabledIndexed(arg0, arg1);
	}

	@Override
	public boolean glIsEnabledi(int arg0, int arg1) {
		return gl.glIsEnabledi(arg0, arg1);
	}

	@Override
	public boolean glIsFenceAPPLE(int arg0) {
		return gl.glIsFenceAPPLE(arg0);
	}

	@Override
	public boolean glIsFenceNV(int arg0) {
		return gl.glIsFenceNV(arg0);
	}

	@Override
	public boolean glIsFramebuffer(int arg0) {
		return gl.glIsFramebuffer(arg0);
	}

	@Override
	public boolean glIsImageHandleResidentNV(long arg0) {
		return gl.glIsImageHandleResidentNV(arg0);
	}

	@Override
	public boolean glIsList(int arg0) {
		return gl.glIsList(arg0);
	}

	@Override
	public boolean glIsNameAMD(int arg0, int arg1) {
		return gl.glIsNameAMD(arg0, arg1);
	}

	@Override
	public boolean glIsNamedBufferResidentNV(int arg0) {
		return gl.glIsNamedBufferResidentNV(arg0);
	}

	@Override
	public boolean glIsNamedStringARB(int arg0, String arg1) {
		return gl.glIsNamedStringARB(arg0, arg1);
	}

	@Override
	public boolean glIsOcclusionQueryNV(int arg0) {
		return gl.glIsOcclusionQueryNV(arg0);
	}

	@Override
	public boolean glIsPBOPackBound() {
		return gl.glIsPBOPackBound();
	}

	@Override
	public boolean glIsPBOUnpackBound() {
		return gl.glIsPBOUnpackBound();
	}

	@Override
	public boolean glIsPathNV(int arg0) {
		return gl.glIsPathNV(arg0);
	}

	@Override
	public boolean glIsPointInFillPathNV(int arg0, int arg1, float arg2, float arg3) {
		return gl.glIsPointInFillPathNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean glIsPointInStrokePathNV(int arg0, float arg1, float arg2) {
		return gl.glIsPointInStrokePathNV(arg0, arg1, arg2);
	}

	@Override
	public boolean glIsProgram(int arg0) {
		return gl.glIsProgram(arg0);
	}

	@Override
	public boolean glIsProgramARB(int arg0) {
		return gl.glIsProgramARB(arg0);
	}

	@Override
	public boolean glIsQuery(int arg0) {
		return gl.glIsQuery(arg0);
	}

	@Override
	public boolean glIsRenderbuffer(int arg0) {
		return gl.glIsRenderbuffer(arg0);
	}

	@Override
	public boolean glIsShader(int arg0) {
		return gl.glIsShader(arg0);
	}

	@Override
	public boolean glIsTexture(int arg0) {
		return gl.glIsTexture(arg0);
	}

	@Override
	public boolean glIsTextureHandleResidentNV(long arg0) {
		return gl.glIsTextureHandleResidentNV(arg0);
	}

	@Override
	public boolean glIsTransformFeedbackNV(int arg0) {
		return gl.glIsTransformFeedbackNV(arg0);
	}

	@Override
	public boolean glIsVBOArrayBound() {
		return gl.glIsVBOArrayBound();
	}

	@Override
	public boolean glIsVBOElementArrayBound() {
		return gl.glIsVBOElementArrayBound();
	}

	@Override
	public boolean glIsVariantEnabledEXT(int arg0, int arg1) {
		return gl.glIsVariantEnabledEXT(arg0, arg1);
	}

	@Override
	public boolean glIsVertexArray(int arg0) {
		return gl.glIsVertexArray(arg0);
	}

	@Override
	public boolean glIsVertexAttribEnabledAPPLE(int arg0, int arg1) {
		return gl.glIsVertexAttribEnabledAPPLE(arg0, arg1);
	}

	@Override
	public void glLightModelf(int arg0, float arg1) {
		gl.glLightModelf(arg0, arg1);
	}

	@Override
	public void glLightModelfv(int arg0, float[] arg1, int arg2) {
		gl.glLightModelfv(arg0, arg1, arg2);
	}

	@Override
	public void glLightModelfv(int arg0, FloatBuffer arg1) {
		gl.glLightModelfv(arg0, arg1);
	}

	@Override
	public void glLightModeli(int arg0, int arg1) {
		gl.glLightModeli(arg0, arg1);
	}

	@Override
	public void glLightModeliv(int arg0, int[] arg1, int arg2) {
		gl.glLightModeliv(arg0, arg1, arg2);
	}

	@Override
	public void glLightModeliv(int arg0, IntBuffer arg1) {
		gl.glLightModeliv(arg0, arg1);
	}

	@Override
	public void glLightf(int arg0, int arg1, float arg2) {
		gl.glLightf(arg0, arg1, arg2);
	}

	@Override
	public void glLightfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glLightfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glLightfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glLightfv(arg0, arg1, arg2);
	}

	@Override
	public void glLighti(int arg0, int arg1, int arg2) {
		gl.glLighti(arg0, arg1, arg2);
	}

	@Override
	public void glLightiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glLightiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glLightiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glLightiv(arg0, arg1, arg2);
	}

	@Override
	public void glLineStipple(int arg0, short arg1) {
		gl.glLineStipple(arg0, arg1);
	}

	@Override
	public void glLineWidth(float arg0) {
		gl.glLineWidth(arg0);
	}

	@Override
	public void glLinkProgram(int arg0) {
		gl.glLinkProgram(arg0);
	}

	@Override
	public void glLinkProgramARB(int arg0) {
		gl.glLinkProgramARB(arg0);
	}

	@Override
	public void glListBase(int arg0) {
		gl.glListBase(arg0);
	}

	@Override
	public void glLoadIdentity() {
		gl.glLoadIdentity();
	}

	@Override
	public void glLoadMatrixd(double[] arg0, int arg1) {
		gl.glLoadMatrixd(arg0, arg1);
	}

	@Override
	public void glLoadMatrixd(DoubleBuffer arg0) {
		gl.glLoadMatrixd(arg0);
	}

	@Override
	public void glLoadMatrixf(float[] arg0, int arg1) {
		gl.glLoadMatrixf(arg0, arg1);
	}

	@Override
	public void glLoadMatrixf(FloatBuffer arg0) {
		gl.glLoadMatrixf(arg0);
	}

	@Override
	public void glLoadName(int arg0) {
		gl.glLoadName(arg0);
	}

	@Override
	public void glLoadTransposeMatrixd(double[] arg0, int arg1) {
		gl.glLoadTransposeMatrixd(arg0, arg1);
	}

	@Override
	public void glLoadTransposeMatrixd(DoubleBuffer arg0) {
		gl.glLoadTransposeMatrixd(arg0);
	}

	@Override
	public void glLoadTransposeMatrixf(float[] arg0, int arg1) {
		gl.glLoadTransposeMatrixf(arg0, arg1);
	}

	@Override
	public void glLoadTransposeMatrixf(FloatBuffer arg0) {
		gl.glLoadTransposeMatrixf(arg0);
	}

	@Override
	public void glLockArraysEXT(int arg0, int arg1) {
		gl.glLockArraysEXT(arg0, arg1);
	}

	@Override
	public void glLogicOp(int arg0) {
		gl.glLogicOp(arg0);
	}

	@Override
	public void glMakeBufferNonResidentNV(int arg0) {
		gl.glMakeBufferNonResidentNV(arg0);
	}

	@Override
	public void glMakeBufferResidentNV(int arg0, int arg1) {
		gl.glMakeBufferResidentNV(arg0, arg1);
	}

	@Override
	public void glMakeImageHandleNonResidentNV(long arg0) {
		gl.glMakeImageHandleNonResidentNV(arg0);
	}

	@Override
	public void glMakeImageHandleResidentNV(long arg0, int arg1) {
		gl.glMakeImageHandleResidentNV(arg0, arg1);
	}

	@Override
	public void glMakeNamedBufferNonResidentNV(int arg0) {
		gl.glMakeNamedBufferNonResidentNV(arg0);
	}

	@Override
	public void glMakeNamedBufferResidentNV(int arg0, int arg1) {
		gl.glMakeNamedBufferResidentNV(arg0, arg1);
	}

	@Override
	public void glMakeTextureHandleNonResidentNV(long arg0) {
		gl.glMakeTextureHandleNonResidentNV(arg0);
	}

	@Override
	public void glMakeTextureHandleResidentNV(long arg0) {
		gl.glMakeTextureHandleResidentNV(arg0);
	}

	@Override
	public void glMap1d(int arg0, double arg1, double arg2, int arg3, int arg4, double[] arg5, int arg6) {
		gl.glMap1d(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMap1d(int arg0, double arg1, double arg2, int arg3, int arg4, DoubleBuffer arg5) {
		gl.glMap1d(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMap1f(int arg0, float arg1, float arg2, int arg3, int arg4, float[] arg5, int arg6) {
		gl.glMap1f(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMap1f(int arg0, float arg1, float arg2, int arg3, int arg4, FloatBuffer arg5) {
		gl.glMap1f(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMap2d(int arg0, double arg1, double arg2, int arg3, int arg4, double arg5, double arg6, int arg7,
			int arg8, double[] arg9, int arg10) {
		gl.glMap2d(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMap2d(int arg0, double arg1, double arg2, int arg3, int arg4, double arg5, double arg6, int arg7,
			int arg8, DoubleBuffer arg9) {
		gl.glMap2d(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMap2f(int arg0, float arg1, float arg2, int arg3, int arg4, float arg5, float arg6, int arg7,
			int arg8, float[] arg9, int arg10) {
		gl.glMap2f(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMap2f(int arg0, float arg1, float arg2, int arg3, int arg4, float arg5, float arg6, int arg7,
			int arg8, FloatBuffer arg9) {
		gl.glMap2f(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public ByteBuffer glMapBuffer(int arg0, int arg1) {
		return gl.glMapBuffer(arg0, arg1);
	}

	@Override
	public ByteBuffer glMapBufferRange(int arg0, long arg1, long arg2, int arg3) {
		return gl.glMapBufferRange(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapControlPointsNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7, Buffer arg8) {
		gl.glMapControlPointsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glMapGrid1d(int arg0, double arg1, double arg2) {
		gl.glMapGrid1d(arg0, arg1, arg2);
	}

	@Override
	public void glMapGrid1f(int arg0, float arg1, float arg2) {
		gl.glMapGrid1f(arg0, arg1, arg2);
	}

	@Override
	public void glMapGrid2d(int arg0, double arg1, double arg2, int arg3, double arg4, double arg5) {
		gl.glMapGrid2d(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMapGrid2f(int arg0, float arg1, float arg2, int arg3, float arg4, float arg5) {
		gl.glMapGrid2f(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public ByteBuffer glMapNamedBufferEXT(int arg0, int arg1) {
		return gl.glMapNamedBufferEXT(arg0, arg1);
	}

	@Override
	public ByteBuffer glMapNamedBufferRangeEXT(int arg0, long arg1, long arg2, int arg3) {
		return gl.glMapNamedBufferRangeEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glMapParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glMapParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glMapParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glMapParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMapParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glMapParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public ByteBuffer glMapTexture2DINTEL(int arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6) {
		return gl.glMapTexture2DINTEL(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public ByteBuffer glMapTexture2DINTEL(int arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4) {
		return gl.glMapTexture2DINTEL(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMapVertexAttrib1dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5,
			double[] arg6, int arg7) {
		gl.glMapVertexAttrib1dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMapVertexAttrib1dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5,
			DoubleBuffer arg6) {
		gl.glMapVertexAttrib1dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMapVertexAttrib1fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float[] arg6,
			int arg7) {
		gl.glMapVertexAttrib1fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMapVertexAttrib1fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5,
			FloatBuffer arg6) {
		gl.glMapVertexAttrib1fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMapVertexAttrib2dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5, double arg6,
			double arg7, int arg8, int arg9, double[] arg10, int arg11) {
		gl.glMapVertexAttrib2dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glMapVertexAttrib2dAPPLE(int arg0, int arg1, double arg2, double arg3, int arg4, int arg5, double arg6,
			double arg7, int arg8, int arg9, DoubleBuffer arg10) {
		gl.glMapVertexAttrib2dAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMapVertexAttrib2fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float arg6,
			float arg7, int arg8, int arg9, float[] arg10, int arg11) {
		gl.glMapVertexAttrib2fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glMapVertexAttrib2fAPPLE(int arg0, int arg1, float arg2, float arg3, int arg4, int arg5, float arg6,
			float arg7, int arg8, int arg9, FloatBuffer arg10) {
		gl.glMapVertexAttrib2fAPPLE(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMaterialf(int arg0, int arg1, float arg2) {
		gl.glMaterialf(arg0, arg1, arg2);
	}

	@Override
	public void glMaterialfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glMaterialfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMaterialfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glMaterialfv(arg0, arg1, arg2);
	}

	@Override
	public void glMateriali(int arg0, int arg1, int arg2) {
		gl.glMateriali(arg0, arg1, arg2);
	}

	@Override
	public void glMaterialiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glMaterialiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMaterialiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glMaterialiv(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixFrustumEXT(int arg0, double arg1, double arg2, double arg3, double arg4, double arg5,
			double arg6) {
		gl.glMatrixFrustumEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMatrixIndexPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glMatrixIndexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixIndexubvARB(int arg0, byte[] arg1, int arg2) {
		gl.glMatrixIndexubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexubvARB(int arg0, ByteBuffer arg1) {
		gl.glMatrixIndexubvARB(arg0, arg1);
	}

	@Override
	public void glMatrixIndexuivARB(int arg0, int[] arg1, int arg2) {
		gl.glMatrixIndexuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexuivARB(int arg0, IntBuffer arg1) {
		gl.glMatrixIndexuivARB(arg0, arg1);
	}

	@Override
	public void glMatrixIndexusvARB(int arg0, short[] arg1, int arg2) {
		gl.glMatrixIndexusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixIndexusvARB(int arg0, ShortBuffer arg1) {
		gl.glMatrixIndexusvARB(arg0, arg1);
	}

	@Override
	public void glMatrixLoadIdentityEXT(int arg0) {
		gl.glMatrixLoadIdentityEXT(arg0);
	}

	@Override
	public void glMatrixLoadTransposedEXT(int arg0, double[] arg1, int arg2) {
		gl.glMatrixLoadTransposedEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadTransposedEXT(int arg0, DoubleBuffer arg1) {
		gl.glMatrixLoadTransposedEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoadTransposefEXT(int arg0, float[] arg1, int arg2) {
		gl.glMatrixLoadTransposefEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadTransposefEXT(int arg0, FloatBuffer arg1) {
		gl.glMatrixLoadTransposefEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoaddEXT(int arg0, double[] arg1, int arg2) {
		gl.glMatrixLoaddEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoaddEXT(int arg0, DoubleBuffer arg1) {
		gl.glMatrixLoaddEXT(arg0, arg1);
	}

	@Override
	public void glMatrixLoadfEXT(int arg0, float[] arg1, int arg2) {
		gl.glMatrixLoadfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixLoadfEXT(int arg0, FloatBuffer arg1) {
		gl.glMatrixLoadfEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMode(int arg0) {
		gl.glMatrixMode(arg0);
	}

	@Override
	public void glMatrixMultTransposedEXT(int arg0, double[] arg1, int arg2) {
		gl.glMatrixMultTransposedEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultTransposedEXT(int arg0, DoubleBuffer arg1) {
		gl.glMatrixMultTransposedEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultTransposefEXT(int arg0, float[] arg1, int arg2) {
		gl.glMatrixMultTransposefEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultTransposefEXT(int arg0, FloatBuffer arg1) {
		gl.glMatrixMultTransposefEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultdEXT(int arg0, double[] arg1, int arg2) {
		gl.glMatrixMultdEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultdEXT(int arg0, DoubleBuffer arg1) {
		gl.glMatrixMultdEXT(arg0, arg1);
	}

	@Override
	public void glMatrixMultfEXT(int arg0, float[] arg1, int arg2) {
		gl.glMatrixMultfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMatrixMultfEXT(int arg0, FloatBuffer arg1) {
		gl.glMatrixMultfEXT(arg0, arg1);
	}

	@Override
	public void glMatrixOrthoEXT(int arg0, double arg1, double arg2, double arg3, double arg4, double arg5, double arg6) {
		gl.glMatrixOrthoEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glMatrixPopEXT(int arg0) {
		gl.glMatrixPopEXT(arg0);
	}

	@Override
	public void glMatrixPushEXT(int arg0) {
		gl.glMatrixPushEXT(arg0);
	}

	@Override
	public void glMatrixRotatedEXT(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl.glMatrixRotatedEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMatrixRotatefEXT(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glMatrixRotatefEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMatrixScaledEXT(int arg0, double arg1, double arg2, double arg3) {
		gl.glMatrixScaledEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixScalefEXT(int arg0, float arg1, float arg2, float arg3) {
		gl.glMatrixScalefEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixTranslatedEXT(int arg0, double arg1, double arg2, double arg3) {
		gl.glMatrixTranslatedEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMatrixTranslatefEXT(int arg0, float arg1, float arg2, float arg3) {
		gl.glMatrixTranslatefEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMinmax(int arg0, int arg1, boolean arg2) {
		gl.glMinmax(arg0, arg1, arg2);
	}

	@Override
	public void glMultMatrixd(double[] arg0, int arg1) {
		gl.glMultMatrixd(arg0, arg1);
	}

	@Override
	public void glMultMatrixd(DoubleBuffer arg0) {
		gl.glMultMatrixd(arg0);
	}

	@Override
	public void glMultMatrixf(float[] arg0, int arg1) {
		gl.glMultMatrixf(arg0, arg1);
	}

	@Override
	public void glMultMatrixf(FloatBuffer arg0) {
		gl.glMultMatrixf(arg0);
	}

	@Override
	public void glMultTransposeMatrixd(double[] arg0, int arg1) {
		gl.glMultTransposeMatrixd(arg0, arg1);
	}

	@Override
	public void glMultTransposeMatrixd(DoubleBuffer arg0) {
		gl.glMultTransposeMatrixd(arg0);
	}

	@Override
	public void glMultTransposeMatrixf(float[] arg0, int arg1) {
		gl.glMultTransposeMatrixf(arg0, arg1);
	}

	@Override
	public void glMultTransposeMatrixf(FloatBuffer arg0) {
		gl.glMultTransposeMatrixf(arg0);
	}

	@Override
	public void glMultiDrawArrays(int arg0, int[] arg1, int arg2, int[] arg3, int arg4, int arg5) {
		gl.glMultiDrawArrays(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glMultiDrawArrays(int arg0, IntBuffer arg1, IntBuffer arg2, int arg3) {
		gl.glMultiDrawArrays(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiDrawArraysIndirectAMD(int arg0, Buffer arg1, int arg2, int arg3) {
		gl.glMultiDrawArraysIndirectAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiDrawElements(int arg0, IntBuffer arg1, int arg2, PointerBuffer arg3, int arg4) {
		gl.glMultiDrawElements(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiDrawElementsIndirectAMD(int arg0, int arg1, Buffer arg2, int arg3, int arg4) {
		gl.glMultiDrawElementsIndirectAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexBufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glMultiTexBufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord1bOES(int arg0, byte arg1) {
		gl.glMultiTexCoord1bOES(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1bvOES(int arg0, byte[] arg1, int arg2) {
		gl.glMultiTexCoord1bvOES(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1bvOES(int arg0, ByteBuffer arg1) {
		gl.glMultiTexCoord1bvOES(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1d(int arg0, double arg1) {
		gl.glMultiTexCoord1d(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1dv(int arg0, double[] arg1, int arg2) {
		gl.glMultiTexCoord1dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1dv(int arg0, DoubleBuffer arg1) {
		gl.glMultiTexCoord1dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1f(int arg0, float arg1) {
		gl.glMultiTexCoord1f(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1fv(int arg0, float[] arg1, int arg2) {
		gl.glMultiTexCoord1fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1fv(int arg0, FloatBuffer arg1) {
		gl.glMultiTexCoord1fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1h(int arg0, short arg1) {
		gl.glMultiTexCoord1h(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1hv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord1hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1hv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord1hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1i(int arg0, int arg1) {
		gl.glMultiTexCoord1i(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1iv(int arg0, int[] arg1, int arg2) {
		gl.glMultiTexCoord1iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1iv(int arg0, IntBuffer arg1) {
		gl.glMultiTexCoord1iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1s(int arg0, short arg1) {
		gl.glMultiTexCoord1s(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord1sv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord1sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord1sv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord1sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2bOES(int arg0, byte arg1, byte arg2) {
		gl.glMultiTexCoord2bOES(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2bvOES(int arg0, byte[] arg1, int arg2) {
		gl.glMultiTexCoord2bvOES(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2bvOES(int arg0, ByteBuffer arg1) {
		gl.glMultiTexCoord2bvOES(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2d(int arg0, double arg1, double arg2) {
		gl.glMultiTexCoord2d(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2dv(int arg0, double[] arg1, int arg2) {
		gl.glMultiTexCoord2dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2dv(int arg0, DoubleBuffer arg1) {
		gl.glMultiTexCoord2dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2f(int arg0, float arg1, float arg2) {
		gl.glMultiTexCoord2f(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2fv(int arg0, float[] arg1, int arg2) {
		gl.glMultiTexCoord2fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2fv(int arg0, FloatBuffer arg1) {
		gl.glMultiTexCoord2fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2h(int arg0, short arg1, short arg2) {
		gl.glMultiTexCoord2h(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2hv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord2hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2hv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord2hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2i(int arg0, int arg1, int arg2) {
		gl.glMultiTexCoord2i(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2iv(int arg0, int[] arg1, int arg2) {
		gl.glMultiTexCoord2iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2iv(int arg0, IntBuffer arg1) {
		gl.glMultiTexCoord2iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord2s(int arg0, short arg1, short arg2) {
		gl.glMultiTexCoord2s(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2sv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord2sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord2sv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord2sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3bOES(int arg0, byte arg1, byte arg2, byte arg3) {
		gl.glMultiTexCoord3bOES(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3bvOES(int arg0, byte[] arg1, int arg2) {
		gl.glMultiTexCoord3bvOES(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3bvOES(int arg0, ByteBuffer arg1) {
		gl.glMultiTexCoord3bvOES(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3d(int arg0, double arg1, double arg2, double arg3) {
		gl.glMultiTexCoord3d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3dv(int arg0, double[] arg1, int arg2) {
		gl.glMultiTexCoord3dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3dv(int arg0, DoubleBuffer arg1) {
		gl.glMultiTexCoord3dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3f(int arg0, float arg1, float arg2, float arg3) {
		gl.glMultiTexCoord3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3fv(int arg0, float[] arg1, int arg2) {
		gl.glMultiTexCoord3fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3fv(int arg0, FloatBuffer arg1) {
		gl.glMultiTexCoord3fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3h(int arg0, short arg1, short arg2, short arg3) {
		gl.glMultiTexCoord3h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3hv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord3hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3hv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord3hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3i(int arg0, int arg1, int arg2, int arg3) {
		gl.glMultiTexCoord3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3iv(int arg0, int[] arg1, int arg2) {
		gl.glMultiTexCoord3iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3iv(int arg0, IntBuffer arg1) {
		gl.glMultiTexCoord3iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord3s(int arg0, short arg1, short arg2, short arg3) {
		gl.glMultiTexCoord3s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexCoord3sv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord3sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord3sv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord3sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4bOES(int arg0, byte arg1, byte arg2, byte arg3, byte arg4) {
		gl.glMultiTexCoord4bOES(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4bvOES(int arg0, byte[] arg1, int arg2) {
		gl.glMultiTexCoord4bvOES(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4bvOES(int arg0, ByteBuffer arg1) {
		gl.glMultiTexCoord4bvOES(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4d(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl.glMultiTexCoord4d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4dv(int arg0, double[] arg1, int arg2) {
		gl.glMultiTexCoord4dv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4dv(int arg0, DoubleBuffer arg1) {
		gl.glMultiTexCoord4dv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glMultiTexCoord4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4fv(int arg0, float[] arg1, int arg2) {
		gl.glMultiTexCoord4fv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4fv(int arg0, FloatBuffer arg1) {
		gl.glMultiTexCoord4fv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4h(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl.glMultiTexCoord4h(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4hv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord4hv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4hv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord4hv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glMultiTexCoord4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4iv(int arg0, int[] arg1, int arg2) {
		gl.glMultiTexCoord4iv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4iv(int arg0, IntBuffer arg1) {
		gl.glMultiTexCoord4iv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoord4s(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl.glMultiTexCoord4s(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexCoord4sv(int arg0, short[] arg1, int arg2) {
		gl.glMultiTexCoord4sv(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexCoord4sv(int arg0, ShortBuffer arg1) {
		gl.glMultiTexCoord4sv(arg0, arg1);
	}

	@Override
	public void glMultiTexCoordPointerEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glMultiTexCoordPointerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl.glMultiTexEnvfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnvfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glMultiTexEnvfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glMultiTexEnvfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnviEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glMultiTexEnviEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexEnvivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glMultiTexEnvivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexEnvivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glMultiTexEnvivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGendEXT(int arg0, int arg1, int arg2, double arg3) {
		gl.glMultiTexGendEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGendvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glMultiTexGendvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGendvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glMultiTexGendvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl.glMultiTexGenfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glMultiTexGenfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGenfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glMultiTexGenfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGeniEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glMultiTexGeniEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexGenivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glMultiTexGenivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexGenivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glMultiTexGenivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl.glMultiTexImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glMultiTexImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl.glMultiTexImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMultiTexImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl.glMultiTexImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glMultiTexParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glMultiTexParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glMultiTexParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glMultiTexParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl.glMultiTexParameterfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glMultiTexParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glMultiTexParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameteriEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glMultiTexParameteriEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glMultiTexParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glMultiTexParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glMultiTexParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glMultiTexRenderbufferEXT(int arg0, int arg1, int arg2) {
		gl.glMultiTexRenderbufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glMultiTexSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glMultiTexSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glMultiTexSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl.glMultiTexSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glMultiTexSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, Buffer arg11) {
		gl.glMultiTexSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glNamedBufferDataEXT(int arg0, long arg1, Buffer arg2, int arg3) {
		gl.glNamedBufferDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedBufferSubDataEXT(int arg0, long arg1, long arg2, Buffer arg3) {
		gl.glNamedBufferSubDataEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedCopyBufferSubDataEXT(int arg0, int arg1, long arg2, long arg3, long arg4) {
		gl.glNamedCopyBufferSubDataEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferParameteriEXT(int arg0, int arg1, int arg2) {
		gl.glNamedFramebufferParameteriEXT(arg0, arg1, arg2);
	}

	@Override
	public void glNamedFramebufferRenderbufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glNamedFramebufferRenderbufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedFramebufferTexture1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glNamedFramebufferTexture1DEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTexture2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glNamedFramebufferTexture2DEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTexture3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glNamedFramebufferTexture3DEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedFramebufferTextureEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glNamedFramebufferTextureEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedFramebufferTextureFaceEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glNamedFramebufferTextureFaceEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedFramebufferTextureLayerEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glNamedFramebufferTextureLayerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4dEXT(int arg0, int arg1, int arg2, double arg3, double arg4, double arg5,
			double arg6) {
		gl.glNamedProgramLocalParameter4dEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameter4dvEXT(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glNamedProgramLocalParameter4dvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4dvEXT(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glNamedProgramLocalParameter4dvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameter4fEXT(int arg0, int arg1, int arg2, float arg3, float arg4, float arg5,
			float arg6) {
		gl.glNamedProgramLocalParameter4fEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameter4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glNamedProgramLocalParameter4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameter4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glNamedProgramLocalParameter4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameterI4iEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glNamedProgramLocalParameterI4iEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameterI4ivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glNamedProgramLocalParameterI4ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameterI4ivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glNamedProgramLocalParameterI4ivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameterI4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glNamedProgramLocalParameterI4uiEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glNamedProgramLocalParameterI4uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glNamedProgramLocalParameterI4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParameterI4uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glNamedProgramLocalParameterI4uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl.glNamedProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl.glNamedProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParametersI4ivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glNamedProgramLocalParametersI4ivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParametersI4ivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glNamedProgramLocalParametersI4ivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramLocalParametersI4uivEXT(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glNamedProgramLocalParametersI4uivEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedProgramLocalParametersI4uivEXT(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glNamedProgramLocalParametersI4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedProgramStringEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glNamedProgramStringEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedRenderbufferStorageEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glNamedRenderbufferStorageEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glNamedRenderbufferStorageMultisampleCoverageEXT(int arg0, int arg1, int arg2, int arg3, int arg4,
			int arg5) {
		gl.glNamedRenderbufferStorageMultisampleCoverageEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glNamedRenderbufferStorageMultisampleEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glNamedRenderbufferStorageMultisampleEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNamedStringARB(int arg0, int arg1, String arg2, int arg3, String arg4) {
		gl.glNamedStringARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glNewList(int arg0, int arg1) {
		gl.glNewList(arg0, arg1);
	}

	@Override
	public void glNormal3b(byte arg0, byte arg1, byte arg2) {
		gl.glNormal3b(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3bv(byte[] arg0, int arg1) {
		gl.glNormal3bv(arg0, arg1);
	}

	@Override
	public void glNormal3bv(ByteBuffer arg0) {
		gl.glNormal3bv(arg0);
	}

	@Override
	public void glNormal3d(double arg0, double arg1, double arg2) {
		gl.glNormal3d(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3dv(double[] arg0, int arg1) {
		gl.glNormal3dv(arg0, arg1);
	}

	@Override
	public void glNormal3dv(DoubleBuffer arg0) {
		gl.glNormal3dv(arg0);
	}

	@Override
	public void glNormal3f(float arg0, float arg1, float arg2) {
		gl.glNormal3f(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3fv(float[] arg0, int arg1) {
		gl.glNormal3fv(arg0, arg1);
	}

	@Override
	public void glNormal3fv(FloatBuffer arg0) {
		gl.glNormal3fv(arg0);
	}

	@Override
	public void glNormal3h(short arg0, short arg1, short arg2) {
		gl.glNormal3h(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3hv(short[] arg0, int arg1) {
		gl.glNormal3hv(arg0, arg1);
	}

	@Override
	public void glNormal3hv(ShortBuffer arg0) {
		gl.glNormal3hv(arg0);
	}

	@Override
	public void glNormal3i(int arg0, int arg1, int arg2) {
		gl.glNormal3i(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3iv(int[] arg0, int arg1) {
		gl.glNormal3iv(arg0, arg1);
	}

	@Override
	public void glNormal3iv(IntBuffer arg0) {
		gl.glNormal3iv(arg0);
	}

	@Override
	public void glNormal3s(short arg0, short arg1, short arg2) {
		gl.glNormal3s(arg0, arg1, arg2);
	}

	@Override
	public void glNormal3sv(short[] arg0, int arg1) {
		gl.glNormal3sv(arg0, arg1);
	}

	@Override
	public void glNormal3sv(ShortBuffer arg0) {
		gl.glNormal3sv(arg0);
	}

	@Override
	public void glNormalFormatNV(int arg0, int arg1) {
		gl.glNormalFormatNV(arg0, arg1);
	}

	@Override
	public void glNormalPointer(GLArrayData arg0) {
		gl.glNormalPointer(arg0);
	}

	@Override
	public void glNormalPointer(int arg0, int arg1, Buffer arg2) {
		gl.glNormalPointer(arg0, arg1, arg2);
	}

	@Override
	public void glNormalPointer(int arg0, int arg1, long arg2) {
		gl.glNormalPointer(arg0, arg1, arg2);
	}

	@Override
	public void glObjectLabel(int arg0, int arg1, int arg2, byte[] arg3, int arg4) {
		gl.glObjectLabel(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glObjectLabel(int arg0, int arg1, int arg2, ByteBuffer arg3) {
		gl.glObjectLabel(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glObjectPtrLabel(Buffer arg0, int arg1, byte[] arg2, int arg3) {
		gl.glObjectPtrLabel(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glObjectPtrLabel(Buffer arg0, int arg1, ByteBuffer arg2) {
		gl.glObjectPtrLabel(arg0, arg1, arg2);
	}

	@Override
	public int glObjectPurgeableAPPLE(int arg0, int arg1, int arg2) {
		return gl.glObjectPurgeableAPPLE(arg0, arg1, arg2);
	}

	@Override
	public int glObjectUnpurgeableAPPLE(int arg0, int arg1, int arg2) {
		return gl.glObjectUnpurgeableAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glOrtho(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) {
		gl.glOrtho(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glOrthof(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) {
		gl.glOrthof(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glPNTrianglesfATI(int arg0, float arg1) {
		gl.glPNTrianglesfATI(arg0, arg1);
	}

	@Override
	public void glPNTrianglesiATI(int arg0, int arg1) {
		gl.glPNTrianglesiATI(arg0, arg1);
	}

	@Override
	public void glPassThrough(float arg0) {
		gl.glPassThrough(arg0);
	}

	@Override
	public void glPathColorGenNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glPathColorGenNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPathColorGenNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glPathColorGenNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathCommandsNV(int arg0, int arg1, byte[] arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glPathCommandsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glPathCommandsNV(int arg0, int arg1, ByteBuffer arg2, int arg3, int arg4, Buffer arg5) {
		gl.glPathCommandsNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glPathCoordsNV(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glPathCoordsNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathCoverDepthFuncNV(int arg0) {
		gl.glPathCoverDepthFuncNV(arg0);
	}

	@Override
	public void glPathDashArrayNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glPathDashArrayNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathDashArrayNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glPathDashArrayNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathFogGenNV(int arg0) {
		gl.glPathFogGenNV(arg0);
	}

	@Override
	public void glPathGlyphRangeNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			float arg8) {
		gl.glPathGlyphRangeNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glPathGlyphsNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, Buffer arg6, int arg7,
			int arg8, float arg9) {
		gl.glPathGlyphsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glPathParameterfNV(int arg0, int arg1, float arg2) {
		gl.glPathParameterfNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathParameterfvNV(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glPathParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathParameterfvNV(int arg0, int arg1, FloatBuffer arg2) {
		gl.glPathParameterfvNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathParameteriNV(int arg0, int arg1, int arg2) {
		gl.glPathParameteriNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathParameterivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glPathParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathParameterivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glPathParameterivNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathStencilDepthOffsetNV(float arg0, float arg1) {
		gl.glPathStencilDepthOffsetNV(arg0, arg1);
	}

	@Override
	public void glPathStencilFuncNV(int arg0, int arg1, int arg2) {
		gl.glPathStencilFuncNV(arg0, arg1, arg2);
	}

	@Override
	public void glPathStringNV(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glPathStringNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPathSubCommandsNV(int arg0, int arg1, int arg2, int arg3, byte[] arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl.glPathSubCommandsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glPathSubCommandsNV(int arg0, int arg1, int arg2, int arg3, ByteBuffer arg4, int arg5, int arg6,
			Buffer arg7) {
		gl.glPathSubCommandsNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glPathSubCoordsNV(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glPathSubCoordsNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPathTexGenNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glPathTexGenNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPathTexGenNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glPathTexGenNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPauseTransformFeedbackNV() {
		gl.glPauseTransformFeedbackNV();
	}

	@Override
	public void glPixelDataRangeNV(int arg0, int arg1, Buffer arg2) {
		gl.glPixelDataRangeNV(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glPixelMapfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapfv(int arg0, int arg1, long arg2) {
		gl.glPixelMapfv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glPixelMapuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapuiv(int arg0, int arg1, long arg2) {
		gl.glPixelMapuiv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, long arg2) {
		gl.glPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glPixelMapusv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelMapusv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glPixelMapusv(arg0, arg1, arg2);
	}

	@Override
	public void glPixelStoref(int arg0, float arg1) {
		gl.glPixelStoref(arg0, arg1);
	}

	@Override
	public void glPixelStorei(int arg0, int arg1) {
		gl.glPixelStorei(arg0, arg1);
	}

	@Override
	public void glPixelTransferf(int arg0, float arg1) {
		gl.glPixelTransferf(arg0, arg1);
	}

	@Override
	public void glPixelTransferi(int arg0, int arg1) {
		gl.glPixelTransferi(arg0, arg1);
	}

	@Override
	public void glPixelTransformParameterfEXT(int arg0, int arg1, float arg2) {
		gl.glPixelTransformParameterfEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameterfvEXT(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glPixelTransformParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelTransformParameterfvEXT(int arg0, int arg1, FloatBuffer arg2) {
		gl.glPixelTransformParameterfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameteriEXT(int arg0, int arg1, int arg2) {
		gl.glPixelTransformParameteriEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelTransformParameterivEXT(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glPixelTransformParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPixelTransformParameterivEXT(int arg0, int arg1, IntBuffer arg2) {
		gl.glPixelTransformParameterivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glPixelZoom(float arg0, float arg1) {
		gl.glPixelZoom(arg0, arg1);
	}

	@Override
	public boolean glPointAlongPathNV(int arg0, int arg1, int arg2, float arg3, float[] arg4, int arg5, float[] arg6,
			int arg7, float[] arg8, int arg9, float[] arg10, int arg11) {
		return gl.glPointAlongPathNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public boolean glPointAlongPathNV(int arg0, int arg1, int arg2, float arg3, FloatBuffer arg4, FloatBuffer arg5,
			FloatBuffer arg6, FloatBuffer arg7) {
		return gl.glPointAlongPathNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glPointParameterf(int arg0, float arg1) {
		gl.glPointParameterf(arg0, arg1);
	}

	@Override
	public void glPointParameterfv(int arg0, float[] arg1, int arg2) {
		gl.glPointParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glPointParameterfv(int arg0, FloatBuffer arg1) {
		gl.glPointParameterfv(arg0, arg1);
	}

	@Override
	public void glPointParameteri(int arg0, int arg1) {
		gl.glPointParameteri(arg0, arg1);
	}

	@Override
	public void glPointParameteriv(int arg0, int[] arg1, int arg2) {
		gl.glPointParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glPointParameteriv(int arg0, IntBuffer arg1) {
		gl.glPointParameteriv(arg0, arg1);
	}

	@Override
	public void glPointSize(float arg0) {
		gl.glPointSize(arg0);
	}

	@Override
	public void glPolygonMode(int arg0, int arg1) {
		gl.glPolygonMode(arg0, arg1);
	}

	@Override
	public void glPolygonOffset(float arg0, float arg1) {
		gl.glPolygonOffset(arg0, arg1);
	}

	@Override
	public void glPolygonStipple(byte[] arg0, int arg1) {
		gl.glPolygonStipple(arg0, arg1);
	}

	@Override
	public void glPolygonStipple(ByteBuffer arg0) {
		gl.glPolygonStipple(arg0);
	}

	@Override
	public void glPolygonStipple(long arg0) {
		gl.glPolygonStipple(arg0);
	}

	@Override
	public void glPopAttrib() {
		gl.glPopAttrib();
	}

	@Override
	public void glPopClientAttrib() {
		gl.glPopClientAttrib();
	}

	@Override
	public void glPopDebugGroup() {
		gl.glPopDebugGroup();
	}

	@Override
	public void glPopMatrix() {
		gl.glPopMatrix();
	}

	@Override
	public void glPopName() {
		gl.glPopName();
	}

	@Override
	public void glPrimitiveRestartIndex(int arg0) {
		gl.glPrimitiveRestartIndex(arg0);
	}

	@Override
	public void glPrimitiveRestartIndexNV(int arg0) {
		gl.glPrimitiveRestartIndexNV(arg0);
	}

	@Override
	public void glPrimitiveRestartNV() {
		gl.glPrimitiveRestartNV();
	}

	@Override
	public void glPrioritizeTextures(int arg0, int[] arg1, int arg2, float[] arg3, int arg4) {
		gl.glPrioritizeTextures(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPrioritizeTextures(int arg0, IntBuffer arg1, FloatBuffer arg2) {
		gl.glPrioritizeTextures(arg0, arg1, arg2);
	}

	@Override
	public void glProgramBinary(int arg0, int arg1, Buffer arg2, int arg3) {
		gl.glProgramBinary(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramBufferParametersIivNV(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glProgramBufferParametersIivNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersIivNV(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glProgramBufferParametersIivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramBufferParametersIuivNV(int arg0, int arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glProgramBufferParametersIuivNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersIuivNV(int arg0, int arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glProgramBufferParametersIuivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramBufferParametersfvNV(int arg0, int arg1, int arg2, int arg3, float[] arg4, int arg5) {
		gl.glProgramBufferParametersfvNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramBufferParametersfvNV(int arg0, int arg1, int arg2, int arg3, FloatBuffer arg4) {
		gl.glProgramBufferParametersfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParameter4dARB(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl.glProgramEnvParameter4dARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameter4dvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glProgramEnvParameter4dvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameter4dvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glProgramEnvParameter4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameter4fARB(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl.glProgramEnvParameter4fARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameter4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glProgramEnvParameter4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameter4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glProgramEnvParameter4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameterI4iNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glProgramEnvParameterI4iNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameterI4ivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glProgramEnvParameterI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameterI4ivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glProgramEnvParameterI4ivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameterI4uiNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glProgramEnvParameterI4uiNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramEnvParameterI4uivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glProgramEnvParameterI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParameterI4uivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glProgramEnvParameterI4uivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramEnvParameters4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glProgramEnvParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParameters4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glProgramEnvParameters4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParametersI4ivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramEnvParametersI4ivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParametersI4ivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramEnvParametersI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramEnvParametersI4uivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramEnvParametersI4uivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramEnvParametersI4uivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramEnvParametersI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4dARB(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
		gl.glProgramLocalParameter4dARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameter4dvARB(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glProgramLocalParameter4dvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4dvARB(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glProgramLocalParameter4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameter4fARB(int arg0, int arg1, float arg2, float arg3, float arg4, float arg5) {
		gl.glProgramLocalParameter4fARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameter4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glProgramLocalParameter4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameter4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glProgramLocalParameter4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameterI4iNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glProgramLocalParameterI4iNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameterI4ivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glProgramLocalParameterI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameterI4ivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glProgramLocalParameterI4ivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameterI4uiNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glProgramLocalParameterI4uiNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramLocalParameterI4uivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glProgramLocalParameterI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParameterI4uivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glProgramLocalParameterI4uivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParameters4fvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glProgramLocalParameters4fvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParametersI4ivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramLocalParametersI4ivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParametersI4ivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramLocalParametersI4ivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramLocalParametersI4uivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramLocalParametersI4uivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramLocalParametersI4uivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramLocalParametersI4uivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramParameteriARB(int arg0, int arg1, int arg2) {
		gl.glProgramParameteriARB(arg0, arg1, arg2);
	}

	@Override
	public void glProgramStringARB(int arg0, int arg1, int arg2, String arg3) {
		gl.glProgramStringARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramSubroutineParametersuivNV(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glProgramSubroutineParametersuivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramSubroutineParametersuivNV(int arg0, int arg1, IntBuffer arg2) {
		gl.glProgramSubroutineParametersuivNV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1uiEXT(int arg0, int arg1, int arg2) {
		gl.glProgramUniform1uiEXT(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniform1uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramUniform1uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform1uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramUniform1uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2uiEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glProgramUniform2uiEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform2uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramUniform2uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform2uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramUniform2uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform3uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glProgramUniform3uiEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramUniform3uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform3uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramUniform3uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniform4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glProgramUniform4uiEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniform4uivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glProgramUniform4uivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniform4uivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glProgramUniform4uivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniformHandleui64NV(int arg0, int arg1, long arg2) {
		gl.glProgramUniformHandleui64NV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniformHandleui64vNV(int arg0, int arg1, int arg2, long[] arg3, int arg4) {
		gl.glProgramUniformHandleui64vNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformHandleui64vNV(int arg0, int arg1, int arg2, LongBuffer arg3) {
		gl.glProgramUniformHandleui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramUniformMatrix2x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix2x3fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix2x3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix2x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix2x4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix2x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix2x4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix3x2fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix3x2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix3x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix3x4fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix3x4fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix3x4fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix4x2fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x2fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix4x2fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformMatrix4x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, float[] arg4, int arg5) {
		gl.glProgramUniformMatrix4x3fvEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glProgramUniformMatrix4x3fvEXT(int arg0, int arg1, int arg2, boolean arg3, FloatBuffer arg4) {
		gl.glProgramUniformMatrix4x3fvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformui64NV(int arg0, int arg1, long arg2) {
		gl.glProgramUniformui64NV(arg0, arg1, arg2);
	}

	@Override
	public void glProgramUniformui64vNV(int arg0, int arg1, int arg2, long[] arg3, int arg4) {
		gl.glProgramUniformui64vNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glProgramUniformui64vNV(int arg0, int arg1, int arg2, LongBuffer arg3) {
		gl.glProgramUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glProgramVertexLimitNV(int arg0, int arg1) {
		gl.glProgramVertexLimitNV(arg0, arg1);
	}

	@Override
	public void glProvokingVertexEXT(int arg0) {
		gl.glProvokingVertexEXT(arg0);
	}

	@Override
	public void glPushAttrib(int arg0) {
		gl.glPushAttrib(arg0);
	}

	@Override
	public void glPushClientAttrib(int arg0) {
		gl.glPushClientAttrib(arg0);
	}

	@Override
	public void glPushClientAttribDefaultEXT(int arg0) {
		gl.glPushClientAttribDefaultEXT(arg0);
	}

	@Override
	public void glPushDebugGroup(int arg0, int arg1, int arg2, byte[] arg3, int arg4) {
		gl.glPushDebugGroup(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glPushDebugGroup(int arg0, int arg1, int arg2, ByteBuffer arg3) {
		gl.glPushDebugGroup(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glPushMatrix() {
		gl.glPushMatrix();
	}

	@Override
	public void glPushName(int arg0) {
		gl.glPushName(arg0);
	}

	@Override
	public int glQueryMatrixxOES(int[] arg0, int arg1, int[] arg2, int arg3) {
		return gl.glQueryMatrixxOES(arg0, arg1, arg2, arg3);
	}

	@Override
	public int glQueryMatrixxOES(IntBuffer arg0, IntBuffer arg1) {
		return gl.glQueryMatrixxOES(arg0, arg1);
	}

	@Override
	public void glRasterPos2d(double arg0, double arg1) {
		gl.glRasterPos2d(arg0, arg1);
	}

	@Override
	public void glRasterPos2dv(double[] arg0, int arg1) {
		gl.glRasterPos2dv(arg0, arg1);
	}

	@Override
	public void glRasterPos2dv(DoubleBuffer arg0) {
		gl.glRasterPos2dv(arg0);
	}

	@Override
	public void glRasterPos2f(float arg0, float arg1) {
		gl.glRasterPos2f(arg0, arg1);
	}

	@Override
	public void glRasterPos2fv(float[] arg0, int arg1) {
		gl.glRasterPos2fv(arg0, arg1);
	}

	@Override
	public void glRasterPos2fv(FloatBuffer arg0) {
		gl.glRasterPos2fv(arg0);
	}

	@Override
	public void glRasterPos2i(int arg0, int arg1) {
		gl.glRasterPos2i(arg0, arg1);
	}

	@Override
	public void glRasterPos2iv(int[] arg0, int arg1) {
		gl.glRasterPos2iv(arg0, arg1);
	}

	@Override
	public void glRasterPos2iv(IntBuffer arg0) {
		gl.glRasterPos2iv(arg0);
	}

	@Override
	public void glRasterPos2s(short arg0, short arg1) {
		gl.glRasterPos2s(arg0, arg1);
	}

	@Override
	public void glRasterPos2sv(short[] arg0, int arg1) {
		gl.glRasterPos2sv(arg0, arg1);
	}

	@Override
	public void glRasterPos2sv(ShortBuffer arg0) {
		gl.glRasterPos2sv(arg0);
	}

	@Override
	public void glRasterPos3d(double arg0, double arg1, double arg2) {
		gl.glRasterPos3d(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3dv(double[] arg0, int arg1) {
		gl.glRasterPos3dv(arg0, arg1);
	}

	@Override
	public void glRasterPos3dv(DoubleBuffer arg0) {
		gl.glRasterPos3dv(arg0);
	}

	@Override
	public void glRasterPos3f(float arg0, float arg1, float arg2) {
		gl.glRasterPos3f(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3fv(float[] arg0, int arg1) {
		gl.glRasterPos3fv(arg0, arg1);
	}

	@Override
	public void glRasterPos3fv(FloatBuffer arg0) {
		gl.glRasterPos3fv(arg0);
	}

	@Override
	public void glRasterPos3i(int arg0, int arg1, int arg2) {
		gl.glRasterPos3i(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3iv(int[] arg0, int arg1) {
		gl.glRasterPos3iv(arg0, arg1);
	}

	@Override
	public void glRasterPos3iv(IntBuffer arg0) {
		gl.glRasterPos3iv(arg0);
	}

	@Override
	public void glRasterPos3s(short arg0, short arg1, short arg2) {
		gl.glRasterPos3s(arg0, arg1, arg2);
	}

	@Override
	public void glRasterPos3sv(short[] arg0, int arg1) {
		gl.glRasterPos3sv(arg0, arg1);
	}

	@Override
	public void glRasterPos3sv(ShortBuffer arg0) {
		gl.glRasterPos3sv(arg0);
	}

	@Override
	public void glRasterPos4d(double arg0, double arg1, double arg2, double arg3) {
		gl.glRasterPos4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4dv(double[] arg0, int arg1) {
		gl.glRasterPos4dv(arg0, arg1);
	}

	@Override
	public void glRasterPos4dv(DoubleBuffer arg0) {
		gl.glRasterPos4dv(arg0);
	}

	@Override
	public void glRasterPos4f(float arg0, float arg1, float arg2, float arg3) {
		gl.glRasterPos4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4fv(float[] arg0, int arg1) {
		gl.glRasterPos4fv(arg0, arg1);
	}

	@Override
	public void glRasterPos4fv(FloatBuffer arg0) {
		gl.glRasterPos4fv(arg0);
	}

	@Override
	public void glRasterPos4i(int arg0, int arg1, int arg2, int arg3) {
		gl.glRasterPos4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4iv(int[] arg0, int arg1) {
		gl.glRasterPos4iv(arg0, arg1);
	}

	@Override
	public void glRasterPos4iv(IntBuffer arg0) {
		gl.glRasterPos4iv(arg0);
	}

	@Override
	public void glRasterPos4s(short arg0, short arg1, short arg2, short arg3) {
		gl.glRasterPos4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRasterPos4sv(short[] arg0, int arg1) {
		gl.glRasterPos4sv(arg0, arg1);
	}

	@Override
	public void glRasterPos4sv(ShortBuffer arg0) {
		gl.glRasterPos4sv(arg0);
	}

	@Override
	public void glReadBuffer(int arg0) {
		gl.glReadBuffer(arg0);
	}

	@Override
	public void glReadPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glReadPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glReadPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl.glReadPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glReadnPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl.glReadnPixels(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glRectd(double arg0, double arg1, double arg2, double arg3) {
		gl.glRectd(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectdv(double[] arg0, int arg1, double[] arg2, int arg3) {
		gl.glRectdv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectdv(DoubleBuffer arg0, DoubleBuffer arg1) {
		gl.glRectdv(arg0, arg1);
	}

	@Override
	public void glRectf(float arg0, float arg1, float arg2, float arg3) {
		gl.glRectf(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectfv(float[] arg0, int arg1, float[] arg2, int arg3) {
		gl.glRectfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectfv(FloatBuffer arg0, FloatBuffer arg1) {
		gl.glRectfv(arg0, arg1);
	}

	@Override
	public void glRecti(int arg0, int arg1, int arg2, int arg3) {
		gl.glRecti(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectiv(int[] arg0, int arg1, int[] arg2, int arg3) {
		gl.glRectiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectiv(IntBuffer arg0, IntBuffer arg1) {
		gl.glRectiv(arg0, arg1);
	}

	@Override
	public void glRects(short arg0, short arg1, short arg2, short arg3) {
		gl.glRects(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectsv(short[] arg0, int arg1, short[] arg2, int arg3) {
		gl.glRectsv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRectsv(ShortBuffer arg0, ShortBuffer arg1) {
		gl.glRectsv(arg0, arg1);
	}

	@Override
	public void glReleaseShaderCompiler() {
		gl.glReleaseShaderCompiler();
	}

	@Override
	public int glRenderMode(int arg0) {
		return gl.glRenderMode(arg0);
	}

	@Override
	public void glRenderbufferStorage(int arg0, int arg1, int arg2, int arg3) {
		gl.glRenderbufferStorage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRenderbufferStorageMultisample(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glRenderbufferStorageMultisample(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glRenderbufferStorageMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glRenderbufferStorageMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glResetHistogram(int arg0) {
		gl.glResetHistogram(arg0);
	}

	@Override
	public void glResetMinmax(int arg0) {
		gl.glResetMinmax(arg0);
	}

	@Override
	public void glResumeTransformFeedbackNV() {
		gl.glResumeTransformFeedbackNV();
	}

	@Override
	public void glRotated(double arg0, double arg1, double arg2, double arg3) {
		gl.glRotated(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glRotatef(float arg0, float arg1, float arg2, float arg3) {
		gl.glRotatef(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSampleCoverage(float arg0, boolean arg1) {
		gl.glSampleCoverage(arg0, arg1);
	}

	@Override
	public void glSampleMaskIndexedNV(int arg0, int arg1) {
		gl.glSampleMaskIndexedNV(arg0, arg1);
	}

	@Override
	public void glScaled(double arg0, double arg1, double arg2) {
		gl.glScaled(arg0, arg1, arg2);
	}

	@Override
	public void glScalef(float arg0, float arg1, float arg2) {
		gl.glScalef(arg0, arg1, arg2);
	}

	@Override
	public void glScissor(int arg0, int arg1, int arg2, int arg3) {
		gl.glScissor(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSecondaryColor3b(byte arg0, byte arg1, byte arg2) {
		gl.glSecondaryColor3b(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3bv(byte[] arg0, int arg1) {
		gl.glSecondaryColor3bv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3bv(ByteBuffer arg0) {
		gl.glSecondaryColor3bv(arg0);
	}

	@Override
	public void glSecondaryColor3d(double arg0, double arg1, double arg2) {
		gl.glSecondaryColor3d(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3dv(double[] arg0, int arg1) {
		gl.glSecondaryColor3dv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3dv(DoubleBuffer arg0) {
		gl.glSecondaryColor3dv(arg0);
	}

	@Override
	public void glSecondaryColor3f(float arg0, float arg1, float arg2) {
		gl.glSecondaryColor3f(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3fv(float[] arg0, int arg1) {
		gl.glSecondaryColor3fv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3fv(FloatBuffer arg0) {
		gl.glSecondaryColor3fv(arg0);
	}

	@Override
	public void glSecondaryColor3h(short arg0, short arg1, short arg2) {
		gl.glSecondaryColor3h(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3hv(short[] arg0, int arg1) {
		gl.glSecondaryColor3hv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3hv(ShortBuffer arg0) {
		gl.glSecondaryColor3hv(arg0);
	}

	@Override
	public void glSecondaryColor3i(int arg0, int arg1, int arg2) {
		gl.glSecondaryColor3i(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3iv(int[] arg0, int arg1) {
		gl.glSecondaryColor3iv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3iv(IntBuffer arg0) {
		gl.glSecondaryColor3iv(arg0);
	}

	@Override
	public void glSecondaryColor3s(short arg0, short arg1, short arg2) {
		gl.glSecondaryColor3s(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3sv(short[] arg0, int arg1) {
		gl.glSecondaryColor3sv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3sv(ShortBuffer arg0) {
		gl.glSecondaryColor3sv(arg0);
	}

	@Override
	public void glSecondaryColor3ub(byte arg0, byte arg1, byte arg2) {
		gl.glSecondaryColor3ub(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3ubv(byte[] arg0, int arg1) {
		gl.glSecondaryColor3ubv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3ubv(ByteBuffer arg0) {
		gl.glSecondaryColor3ubv(arg0);
	}

	@Override
	public void glSecondaryColor3ui(int arg0, int arg1, int arg2) {
		gl.glSecondaryColor3ui(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3uiv(int[] arg0, int arg1) {
		gl.glSecondaryColor3uiv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3uiv(IntBuffer arg0) {
		gl.glSecondaryColor3uiv(arg0);
	}

	@Override
	public void glSecondaryColor3us(short arg0, short arg1, short arg2) {
		gl.glSecondaryColor3us(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColor3usv(short[] arg0, int arg1) {
		gl.glSecondaryColor3usv(arg0, arg1);
	}

	@Override
	public void glSecondaryColor3usv(ShortBuffer arg0) {
		gl.glSecondaryColor3usv(arg0);
	}

	@Override
	public void glSecondaryColorFormatNV(int arg0, int arg1, int arg2) {
		gl.glSecondaryColorFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glSecondaryColorPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glSecondaryColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSecondaryColorPointer(int arg0, int arg1, int arg2, long arg3) {
		gl.glSecondaryColorPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSelectBuffer(int arg0, IntBuffer arg1) {
		gl.glSelectBuffer(arg0, arg1);
	}

	@Override
	public void glSelectPerfMonitorCountersAMD(int arg0, boolean arg1, int arg2, int arg3, int[] arg4, int arg5) {
		gl.glSelectPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glSelectPerfMonitorCountersAMD(int arg0, boolean arg1, int arg2, int arg3, IntBuffer arg4) {
		gl.glSelectPerfMonitorCountersAMD(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glSeparableFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6, Buffer arg7) {
		gl.glSeparableFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glSeparableFilter2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6, long arg7) {
		gl.glSeparableFilter2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glSetFenceAPPLE(int arg0) {
		gl.glSetFenceAPPLE(arg0);
	}

	@Override
	public void glSetFenceNV(int arg0, int arg1) {
		gl.glSetFenceNV(arg0, arg1);
	}

	@Override
	public void glSetInvariantEXT(int arg0, int arg1, Buffer arg2) {
		gl.glSetInvariantEXT(arg0, arg1, arg2);
	}

	@Override
	public void glSetLocalConstantEXT(int arg0, int arg1, Buffer arg2) {
		gl.glSetLocalConstantEXT(arg0, arg1, arg2);
	}

	@Override
	public void glSetMultisamplefvAMD(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glSetMultisamplefvAMD(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glSetMultisamplefvAMD(int arg0, int arg1, FloatBuffer arg2) {
		gl.glSetMultisamplefvAMD(arg0, arg1, arg2);
	}

	@Override
	public void glShadeModel(int arg0) {
		gl.glShadeModel(arg0);
	}

	@Override
	public void glShaderBinary(int arg0, int[] arg1, int arg2, int arg3, Buffer arg4, int arg5) {
		gl.glShaderBinary(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glShaderBinary(int arg0, IntBuffer arg1, int arg2, Buffer arg3, int arg4) {
		gl.glShaderBinary(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderOp1EXT(int arg0, int arg1, int arg2) {
		gl.glShaderOp1EXT(arg0, arg1, arg2);
	}

	@Override
	public void glShaderOp2EXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glShaderOp2EXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glShaderOp3EXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glShaderOp3EXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSource(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl.glShaderSource(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSource(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl.glShaderSource(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glShaderSourceARB(int arg0, int arg1, String[] arg2, int[] arg3, int arg4) {
		gl.glShaderSourceARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glShaderSourceARB(int arg0, int arg1, String[] arg2, IntBuffer arg3) {
		gl.glShaderSourceARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilClearTagEXT(int arg0, int arg1) {
		gl.glStencilClearTagEXT(arg0, arg1);
	}

	@Override
	public void glStencilFillPathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, int arg6,
			float[] arg7, int arg8) {
		gl.glStencilFillPathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glStencilFillPathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, int arg6,
			FloatBuffer arg7) {
		gl.glStencilFillPathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glStencilFillPathNV(int arg0, int arg1, int arg2) {
		gl.glStencilFillPathNV(arg0, arg1, arg2);
	}

	@Override
	public void glStencilFunc(int arg0, int arg1, int arg2) {
		gl.glStencilFunc(arg0, arg1, arg2);
	}

	@Override
	public void glStencilFuncSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl.glStencilFuncSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilMask(int arg0) {
		gl.glStencilMask(arg0);
	}

	@Override
	public void glStencilMaskSeparate(int arg0, int arg1) {
		gl.glStencilMaskSeparate(arg0, arg1);
	}

	@Override
	public void glStencilOp(int arg0, int arg1, int arg2) {
		gl.glStencilOp(arg0, arg1, arg2);
	}

	@Override
	public void glStencilOpSeparate(int arg0, int arg1, int arg2, int arg3) {
		gl.glStencilOpSeparate(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glStencilOpValueAMD(int arg0, int arg1) {
		gl.glStencilOpValueAMD(arg0, arg1);
	}

	@Override
	public void glStencilStrokePathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, int arg6,
			float[] arg7, int arg8) {
		gl.glStencilStrokePathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glStencilStrokePathInstancedNV(int arg0, int arg1, Buffer arg2, int arg3, int arg4, int arg5, int arg6,
			FloatBuffer arg7) {
		gl.glStencilStrokePathInstancedNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glStencilStrokePathNV(int arg0, int arg1, int arg2) {
		gl.glStencilStrokePathNV(arg0, arg1, arg2);
	}

	@Override
	public void glStringMarkerGREMEDY(int arg0, Buffer arg1) {
		gl.glStringMarkerGREMEDY(arg0, arg1);
	}

	@Override
	public void glSwizzleEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glSwizzleEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glSyncTextureINTEL(int arg0) {
		gl.glSyncTextureINTEL(arg0);
	}

	@Override
	public void glTessellationFactorAMD(float arg0) {
		gl.glTessellationFactorAMD(arg0);
	}

	@Override
	public void glTessellationModeAMD(int arg0) {
		gl.glTessellationModeAMD(arg0);
	}

	@Override
	public boolean glTestFenceAPPLE(int arg0) {
		return gl.glTestFenceAPPLE(arg0);
	}

	@Override
	public boolean glTestFenceNV(int arg0) {
		return gl.glTestFenceNV(arg0);
	}

	@Override
	public boolean glTestObjectAPPLE(int arg0, int arg1) {
		return gl.glTestObjectAPPLE(arg0, arg1);
	}

	@Override
	public void glTexBuffer(int arg0, int arg1, int arg2) {
		gl.glTexBuffer(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord1bOES(byte arg0) {
		gl.glTexCoord1bOES(arg0);
	}

	@Override
	public void glTexCoord1bvOES(byte[] arg0, int arg1) {
		gl.glTexCoord1bvOES(arg0, arg1);
	}

	@Override
	public void glTexCoord1bvOES(ByteBuffer arg0) {
		gl.glTexCoord1bvOES(arg0);
	}

	@Override
	public void glTexCoord1d(double arg0) {
		gl.glTexCoord1d(arg0);
	}

	@Override
	public void glTexCoord1dv(double[] arg0, int arg1) {
		gl.glTexCoord1dv(arg0, arg1);
	}

	@Override
	public void glTexCoord1dv(DoubleBuffer arg0) {
		gl.glTexCoord1dv(arg0);
	}

	@Override
	public void glTexCoord1f(float arg0) {
		gl.glTexCoord1f(arg0);
	}

	@Override
	public void glTexCoord1fv(float[] arg0, int arg1) {
		gl.glTexCoord1fv(arg0, arg1);
	}

	@Override
	public void glTexCoord1fv(FloatBuffer arg0) {
		gl.glTexCoord1fv(arg0);
	}

	@Override
	public void glTexCoord1h(short arg0) {
		gl.glTexCoord1h(arg0);
	}

	@Override
	public void glTexCoord1hv(short[] arg0, int arg1) {
		gl.glTexCoord1hv(arg0, arg1);
	}

	@Override
	public void glTexCoord1hv(ShortBuffer arg0) {
		gl.glTexCoord1hv(arg0);
	}

	@Override
	public void glTexCoord1i(int arg0) {
		gl.glTexCoord1i(arg0);
	}

	@Override
	public void glTexCoord1iv(int[] arg0, int arg1) {
		gl.glTexCoord1iv(arg0, arg1);
	}

	@Override
	public void glTexCoord1iv(IntBuffer arg0) {
		gl.glTexCoord1iv(arg0);
	}

	@Override
	public void glTexCoord1s(short arg0) {
		gl.glTexCoord1s(arg0);
	}

	@Override
	public void glTexCoord1sv(short[] arg0, int arg1) {
		gl.glTexCoord1sv(arg0, arg1);
	}

	@Override
	public void glTexCoord1sv(ShortBuffer arg0) {
		gl.glTexCoord1sv(arg0);
	}

	@Override
	public void glTexCoord2bOES(byte arg0, byte arg1) {
		gl.glTexCoord2bOES(arg0, arg1);
	}

	@Override
	public void glTexCoord2bvOES(byte[] arg0, int arg1) {
		gl.glTexCoord2bvOES(arg0, arg1);
	}

	@Override
	public void glTexCoord2bvOES(ByteBuffer arg0) {
		gl.glTexCoord2bvOES(arg0);
	}

	@Override
	public void glTexCoord2d(double arg0, double arg1) {
		gl.glTexCoord2d(arg0, arg1);
	}

	@Override
	public void glTexCoord2dv(double[] arg0, int arg1) {
		gl.glTexCoord2dv(arg0, arg1);
	}

	@Override
	public void glTexCoord2dv(DoubleBuffer arg0) {
		gl.glTexCoord2dv(arg0);
	}

	@Override
	public void glTexCoord2f(float arg0, float arg1) {
		gl.glTexCoord2f(arg0, arg1);
	}

	@Override
	public void glTexCoord2fv(float[] arg0, int arg1) {
		gl.glTexCoord2fv(arg0, arg1);
	}

	@Override
	public void glTexCoord2fv(FloatBuffer arg0) {
		gl.glTexCoord2fv(arg0);
	}

	@Override
	public void glTexCoord2h(short arg0, short arg1) {
		gl.glTexCoord2h(arg0, arg1);
	}

	@Override
	public void glTexCoord2hv(short[] arg0, int arg1) {
		gl.glTexCoord2hv(arg0, arg1);
	}

	@Override
	public void glTexCoord2hv(ShortBuffer arg0) {
		gl.glTexCoord2hv(arg0);
	}

	@Override
	public void glTexCoord2i(int arg0, int arg1) {
		gl.glTexCoord2i(arg0, arg1);
	}

	@Override
	public void glTexCoord2iv(int[] arg0, int arg1) {
		gl.glTexCoord2iv(arg0, arg1);
	}

	@Override
	public void glTexCoord2iv(IntBuffer arg0) {
		gl.glTexCoord2iv(arg0);
	}

	@Override
	public void glTexCoord2s(short arg0, short arg1) {
		gl.glTexCoord2s(arg0, arg1);
	}

	@Override
	public void glTexCoord2sv(short[] arg0, int arg1) {
		gl.glTexCoord2sv(arg0, arg1);
	}

	@Override
	public void glTexCoord2sv(ShortBuffer arg0) {
		gl.glTexCoord2sv(arg0);
	}

	@Override
	public void glTexCoord3bOES(byte arg0, byte arg1, byte arg2) {
		gl.glTexCoord3bOES(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3bvOES(byte[] arg0, int arg1) {
		gl.glTexCoord3bvOES(arg0, arg1);
	}

	@Override
	public void glTexCoord3bvOES(ByteBuffer arg0) {
		gl.glTexCoord3bvOES(arg0);
	}

	@Override
	public void glTexCoord3d(double arg0, double arg1, double arg2) {
		gl.glTexCoord3d(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3dv(double[] arg0, int arg1) {
		gl.glTexCoord3dv(arg0, arg1);
	}

	@Override
	public void glTexCoord3dv(DoubleBuffer arg0) {
		gl.glTexCoord3dv(arg0);
	}

	@Override
	public void glTexCoord3f(float arg0, float arg1, float arg2) {
		gl.glTexCoord3f(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3fv(float[] arg0, int arg1) {
		gl.glTexCoord3fv(arg0, arg1);
	}

	@Override
	public void glTexCoord3fv(FloatBuffer arg0) {
		gl.glTexCoord3fv(arg0);
	}

	@Override
	public void glTexCoord3h(short arg0, short arg1, short arg2) {
		gl.glTexCoord3h(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3hv(short[] arg0, int arg1) {
		gl.glTexCoord3hv(arg0, arg1);
	}

	@Override
	public void glTexCoord3hv(ShortBuffer arg0) {
		gl.glTexCoord3hv(arg0);
	}

	@Override
	public void glTexCoord3i(int arg0, int arg1, int arg2) {
		gl.glTexCoord3i(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3iv(int[] arg0, int arg1) {
		gl.glTexCoord3iv(arg0, arg1);
	}

	@Override
	public void glTexCoord3iv(IntBuffer arg0) {
		gl.glTexCoord3iv(arg0);
	}

	@Override
	public void glTexCoord3s(short arg0, short arg1, short arg2) {
		gl.glTexCoord3s(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoord3sv(short[] arg0, int arg1) {
		gl.glTexCoord3sv(arg0, arg1);
	}

	@Override
	public void glTexCoord3sv(ShortBuffer arg0) {
		gl.glTexCoord3sv(arg0);
	}

	@Override
	public void glTexCoord4bOES(byte arg0, byte arg1, byte arg2, byte arg3) {
		gl.glTexCoord4bOES(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4bvOES(byte[] arg0, int arg1) {
		gl.glTexCoord4bvOES(arg0, arg1);
	}

	@Override
	public void glTexCoord4bvOES(ByteBuffer arg0) {
		gl.glTexCoord4bvOES(arg0);
	}

	@Override
	public void glTexCoord4d(double arg0, double arg1, double arg2, double arg3) {
		gl.glTexCoord4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4dv(double[] arg0, int arg1) {
		gl.glTexCoord4dv(arg0, arg1);
	}

	@Override
	public void glTexCoord4dv(DoubleBuffer arg0) {
		gl.glTexCoord4dv(arg0);
	}

	@Override
	public void glTexCoord4f(float arg0, float arg1, float arg2, float arg3) {
		gl.glTexCoord4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4fv(float[] arg0, int arg1) {
		gl.glTexCoord4fv(arg0, arg1);
	}

	@Override
	public void glTexCoord4fv(FloatBuffer arg0) {
		gl.glTexCoord4fv(arg0);
	}

	@Override
	public void glTexCoord4h(short arg0, short arg1, short arg2, short arg3) {
		gl.glTexCoord4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4hv(short[] arg0, int arg1) {
		gl.glTexCoord4hv(arg0, arg1);
	}

	@Override
	public void glTexCoord4hv(ShortBuffer arg0) {
		gl.glTexCoord4hv(arg0);
	}

	@Override
	public void glTexCoord4i(int arg0, int arg1, int arg2, int arg3) {
		gl.glTexCoord4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4iv(int[] arg0, int arg1) {
		gl.glTexCoord4iv(arg0, arg1);
	}

	@Override
	public void glTexCoord4iv(IntBuffer arg0) {
		gl.glTexCoord4iv(arg0);
	}

	@Override
	public void glTexCoord4s(short arg0, short arg1, short arg2, short arg3) {
		gl.glTexCoord4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoord4sv(short[] arg0, int arg1) {
		gl.glTexCoord4sv(arg0, arg1);
	}

	@Override
	public void glTexCoord4sv(ShortBuffer arg0) {
		gl.glTexCoord4sv(arg0);
	}

	@Override
	public void glTexCoordFormatNV(int arg0, int arg1, int arg2) {
		gl.glTexCoordFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glTexCoordPointer(GLArrayData arg0) {
		gl.glTexCoordPointer(arg0);
	}

	@Override
	public void glTexCoordPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glTexCoordPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexCoordPointer(int arg0, int arg1, int arg2, long arg3) {
		gl.glTexCoordPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnvf(int arg0, int arg1, float arg2) {
		gl.glTexEnvf(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnvfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glTexEnvfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnvfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glTexEnvfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnvi(int arg0, int arg1, int arg2) {
		gl.glTexEnvi(arg0, arg1, arg2);
	}

	@Override
	public void glTexEnviv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glTexEnviv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexEnviv(int arg0, int arg1, IntBuffer arg2) {
		gl.glTexEnviv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGend(int arg0, int arg1, double arg2) {
		gl.glTexGend(arg0, arg1, arg2);
	}

	@Override
	public void glTexGendv(int arg0, int arg1, double[] arg2, int arg3) {
		gl.glTexGendv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGendv(int arg0, int arg1, DoubleBuffer arg2) {
		gl.glTexGendv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGenf(int arg0, int arg1, float arg2) {
		gl.glTexGenf(arg0, arg1, arg2);
	}

	@Override
	public void glTexGenfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glTexGenfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGenfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glTexGenfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexGeni(int arg0, int arg1, int arg2) {
		gl.glTexGeni(arg0, arg1, arg2);
	}

	@Override
	public void glTexGeniv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glTexGeniv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexGeniv(int arg0, int arg1, IntBuffer arg2) {
		gl.glTexGeniv(arg0, arg1, arg2);
	}

	@Override
	public void glTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl.glTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, long arg7) {
		gl.glTexImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8) {
		gl.glTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, long arg8) {
		gl.glTexImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexImage2DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			boolean arg6) {
		gl.glTexImage2DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			Buffer arg9) {
		gl.glTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTexImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
			long arg9) {
		gl.glTexImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTexImage3DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7) {
		gl.glTexImage3DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTexParameterIiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glTexParameterIiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterIiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glTexParameterIiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterIuiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glTexParameterIuiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterIuiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glTexParameterIuiv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterf(int arg0, int arg1, float arg2) {
		gl.glTexParameterf(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameterfv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glTexParameterfv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameterfv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glTexParameterfv(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameteri(int arg0, int arg1, int arg2) {
		gl.glTexParameteri(arg0, arg1, arg2);
	}

	@Override
	public void glTexParameteriv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glTexParameteriv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexParameteriv(int arg0, int arg1, IntBuffer arg2) {
		gl.glTexParameteriv(arg0, arg1, arg2);
	}

	@Override
	public void glTexRenderbufferNV(int arg0, int arg1) {
		gl.glTexRenderbufferNV(arg0, arg1);
	}

	@Override
	public void glTexStorage1D(int arg0, int arg1, int arg2, int arg3) {
		gl.glTexStorage1D(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTexStorage2D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glTexStorage2D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTexStorage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glTexStorage3D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTexStorageSparseAMD(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glTexStorageSparseAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6) {
		gl.glTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexSubImage1D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, long arg6) {
		gl.glTexSubImage1D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl.glTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			long arg8) {
		gl.glTexSubImage2D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl.glTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTexSubImage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, long arg10) {
		gl.glTexSubImage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTextureBarrierNV() {
		gl.glTextureBarrierNV();
	}

	@Override
	public void glTextureBufferEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glTextureBufferEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureBufferRangeEXT(int arg0, int arg1, int arg2, int arg3, long arg4, long arg5) {
		gl.glTextureBufferRangeEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTextureImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			Buffer arg8) {
		gl.glTextureImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTextureImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl.glTextureImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTextureImage2DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			int arg6, boolean arg7) {
		gl.glTextureImage2DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureImage2DMultisampleNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
		gl.glTextureImage2DMultisampleNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTextureImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, Buffer arg10) {
		gl.glTextureImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	}

	@Override
	public void glTextureImage3DMultisampleCoverageNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			int arg6, int arg7, boolean arg8) {
		gl.glTextureImage3DMultisampleCoverageNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	@Override
	public void glTextureImage3DMultisampleNV(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6,
			boolean arg7) {
		gl.glTextureImage3DMultisampleNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureLightEXT(int arg0) {
		gl.glTextureLightEXT(arg0);
	}

	@Override
	public void glTextureMaterialEXT(int arg0, int arg1) {
		gl.glTextureMaterialEXT(arg0, arg1);
	}

	@Override
	public void glTextureNormalEXT(int arg0) {
		gl.glTextureNormalEXT(arg0);
	}

	@Override
	public void glTextureParameterIivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glTextureParameterIivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterIivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glTextureParameterIivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterIuivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glTextureParameterIuivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterIuivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glTextureParameterIuivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterfEXT(int arg0, int arg1, int arg2, float arg3) {
		gl.glTextureParameterfEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterfvEXT(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glTextureParameterfvEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterfvEXT(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glTextureParameterfvEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameteriEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glTextureParameteriEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureParameterivEXT(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glTextureParameterivEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureParameterivEXT(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glTextureParameterivEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTextureRangeAPPLE(int arg0, int arg1, Buffer arg2) {
		gl.glTextureRangeAPPLE(arg0, arg1, arg2);
	}

	@Override
	public void glTextureRenderbufferEXT(int arg0, int arg1, int arg2) {
		gl.glTextureRenderbufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glTextureStorage1D(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glTextureStorage1D(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTextureStorage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glTextureStorage2D(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glTextureStorage3D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		gl.glTextureStorage3D(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glTextureStorageSparseAMD(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		gl.glTextureStorageSparseAMD(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureSubImage1DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7) {
		gl.glTextureSubImage1DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glTextureSubImage2DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, Buffer arg9) {
		gl.glTextureSubImage2DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@Override
	public void glTextureSubImage3DEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7,
			int arg8, int arg9, int arg10, Buffer arg11) {
		gl.glTextureSubImage3DEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
	}

	@Override
	public void glTransformFeedbackVaryings(int arg0, int arg1, String[] arg2, int arg3) {
		gl.glTransformFeedbackVaryings(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTransformPathNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glTransformPathNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glTransformPathNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glTransformPathNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glTranslated(double arg0, double arg1, double arg2) {
		gl.glTranslated(arg0, arg1, arg2);
	}

	@Override
	public void glTranslatef(float arg0, float arg1, float arg2) {
		gl.glTranslatef(arg0, arg1, arg2);
	}

	@Override
	public void glUniform(GLUniformData arg0) {
		gl.glUniform(arg0);
	}

	@Override
	public void glUniform1f(int arg0, float arg1) {
		gl.glUniform1f(arg0, arg1);
	}

	@Override
	public void glUniform1fARB(int arg0, float arg1) {
		gl.glUniform1fARB(arg0, arg1);
	}

	@Override
	public void glUniform1fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform1fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1fv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform1fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform1fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform1fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1i(int arg0, int arg1) {
		gl.glUniform1i(arg0, arg1);
	}

	@Override
	public void glUniform1iARB(int arg0, int arg1) {
		gl.glUniform1iARB(arg0, arg1);
	}

	@Override
	public void glUniform1iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform1iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1iv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform1iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform1ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform1ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform1ui(int arg0, int arg1) {
		gl.glUniform1ui(arg0, arg1);
	}

	@Override
	public void glUniform1uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform1uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform1uiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2f(int arg0, float arg1, float arg2) {
		gl.glUniform2f(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fARB(int arg0, float arg1, float arg2) {
		gl.glUniform2fARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2fv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform2fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform2fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform2fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2i(int arg0, int arg1, int arg2) {
		gl.glUniform2i(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2iARB(int arg0, int arg1, int arg2) {
		gl.glUniform2iARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform2iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2iv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform2iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform2ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform2ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2ui(int arg0, int arg1, int arg2) {
		gl.glUniform2ui(arg0, arg1, arg2);
	}

	@Override
	public void glUniform2uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform2uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform2uiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3f(int arg0, float arg1, float arg2, float arg3) {
		gl.glUniform3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fARB(int arg0, float arg1, float arg2, float arg3) {
		gl.glUniform3fARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform3fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform3fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform3fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3i(int arg0, int arg1, int arg2, int arg3) {
		gl.glUniform3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iARB(int arg0, int arg1, int arg2, int arg3) {
		gl.glUniform3iARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform3iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3iv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform3iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform3ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform3ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform3ui(int arg0, int arg1, int arg2, int arg3) {
		gl.glUniform3ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform3uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform3uiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glUniform4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4fARB(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glUniform4fARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4fv(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4fv(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform4fv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4fvARB(int arg0, int arg1, float[] arg2, int arg3) {
		gl.glUniform4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4fvARB(int arg0, int arg1, FloatBuffer arg2) {
		gl.glUniform4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glUniform4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4iARB(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glUniform4iARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4iv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform4iv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4iv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform4iv(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4ivARB(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform4ivARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4ivARB(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform4ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glUniform4ui(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glUniform4ui(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniform4uiv(int arg0, int arg1, int[] arg2, int arg3) {
		gl.glUniform4uiv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniform4uiv(int arg0, int arg1, IntBuffer arg2) {
		gl.glUniform4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glUniformBlockBinding(int arg0, int arg1, int arg2) {
		gl.glUniformBlockBinding(arg0, arg1, arg2);
	}

	@Override
	public void glUniformBufferEXT(int arg0, int arg1, int arg2) {
		gl.glUniformBufferEXT(arg0, arg1, arg2);
	}

	@Override
	public void glUniformHandleui64NV(int arg0, long arg1) {
		gl.glUniformHandleui64NV(arg0, arg1);
	}

	@Override
	public void glUniformHandleui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glUniformHandleui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformHandleui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glUniformHandleui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glUniformMatrix2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix2fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix2fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2x3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix2x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2x3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix2x3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix2x4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix2x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix2x4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix2x4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix3fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix3fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3x2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix3x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3x2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix3x2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix3x4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix3x4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix3x4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix3x4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix4fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix4fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4fvARB(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix4fvARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4fvARB(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix4fvARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4x2fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix4x2fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4x2fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix4x2fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformMatrix4x3fv(int arg0, int arg1, boolean arg2, float[] arg3, int arg4) {
		gl.glUniformMatrix4x3fv(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glUniformMatrix4x3fv(int arg0, int arg1, boolean arg2, FloatBuffer arg3) {
		gl.glUniformMatrix4x3fv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformui64NV(int arg0, long arg1) {
		gl.glUniformui64NV(arg0, arg1);
	}

	@Override
	public void glUniformui64vNV(int arg0, int arg1, long[] arg2, int arg3) {
		gl.glUniformui64vNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glUniformui64vNV(int arg0, int arg1, LongBuffer arg2) {
		gl.glUniformui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glUnlockArraysEXT() {
		gl.glUnlockArraysEXT();
	}

	@Override
	public boolean glUnmapBuffer(int arg0) {
		return gl.glUnmapBuffer(arg0);
	}

	@Override
	public boolean glUnmapNamedBufferEXT(int arg0) {
		return gl.glUnmapNamedBufferEXT(arg0);
	}

	@Override
	public void glUnmapTexture2DINTEL(int arg0, int arg1) {
		gl.glUnmapTexture2DINTEL(arg0, arg1);
	}

	@Override
	public void glUseProgram(int arg0) {
		gl.glUseProgram(arg0);
	}

	@Override
	public void glUseProgramObjectARB(int arg0) {
		gl.glUseProgramObjectARB(arg0);
	}

	@Override
	public void glVDPAUFiniNV() {
		gl.glVDPAUFiniNV();
	}

	@Override
	public void glVDPAUGetSurfaceivNV(long arg0, int arg1, int arg2, int[] arg3, int arg4, int[] arg5, int arg6) {
		gl.glVDPAUGetSurfaceivNV(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glVDPAUGetSurfaceivNV(long arg0, int arg1, int arg2, IntBuffer arg3, IntBuffer arg4) {
		gl.glVDPAUGetSurfaceivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVDPAUInitNV(Buffer arg0, Buffer arg1) {
		gl.glVDPAUInitNV(arg0, arg1);
	}

	@Override
	public void glVDPAUIsSurfaceNV(long arg0) {
		gl.glVDPAUIsSurfaceNV(arg0);
	}

	@Override
	public void glVDPAUMapSurfacesNV(int arg0, PointerBuffer arg1) {
		gl.glVDPAUMapSurfacesNV(arg0, arg1);
	}

	@Override
	public long glVDPAURegisterOutputSurfaceNV(Buffer arg0, int arg1, int arg2, int[] arg3, int arg4) {
		return gl.glVDPAURegisterOutputSurfaceNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public long glVDPAURegisterOutputSurfaceNV(Buffer arg0, int arg1, int arg2, IntBuffer arg3) {
		return gl.glVDPAURegisterOutputSurfaceNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public long glVDPAURegisterVideoSurfaceNV(Buffer arg0, int arg1, int arg2, int[] arg3, int arg4) {
		return gl.glVDPAURegisterVideoSurfaceNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public long glVDPAURegisterVideoSurfaceNV(Buffer arg0, int arg1, int arg2, IntBuffer arg3) {
		return gl.glVDPAURegisterVideoSurfaceNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVDPAUSurfaceAccessNV(long arg0, int arg1) {
		gl.glVDPAUSurfaceAccessNV(arg0, arg1);
	}

	@Override
	public void glVDPAUUnmapSurfacesNV(int arg0, PointerBuffer arg1) {
		gl.glVDPAUUnmapSurfacesNV(arg0, arg1);
	}

	@Override
	public void glVDPAUUnregisterSurfaceNV(long arg0) {
		gl.glVDPAUUnregisterSurfaceNV(arg0);
	}

	@Override
	public void glValidateProgram(int arg0) {
		gl.glValidateProgram(arg0);
	}

	@Override
	public void glValidateProgramARB(int arg0) {
		gl.glValidateProgramARB(arg0);
	}

	@Override
	public void glVariantPointerEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glVariantPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVariantPointerEXT(int arg0, int arg1, int arg2, long arg3) {
		gl.glVariantPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVariantbvEXT(int arg0, byte[] arg1, int arg2) {
		gl.glVariantbvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantbvEXT(int arg0, ByteBuffer arg1) {
		gl.glVariantbvEXT(arg0, arg1);
	}

	@Override
	public void glVariantdvEXT(int arg0, double[] arg1, int arg2) {
		gl.glVariantdvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantdvEXT(int arg0, DoubleBuffer arg1) {
		gl.glVariantdvEXT(arg0, arg1);
	}

	@Override
	public void glVariantfvEXT(int arg0, float[] arg1, int arg2) {
		gl.glVariantfvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantfvEXT(int arg0, FloatBuffer arg1) {
		gl.glVariantfvEXT(arg0, arg1);
	}

	@Override
	public void glVariantivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVariantivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantivEXT(int arg0, IntBuffer arg1) {
		gl.glVariantivEXT(arg0, arg1);
	}

	@Override
	public void glVariantsvEXT(int arg0, short[] arg1, int arg2) {
		gl.glVariantsvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantsvEXT(int arg0, ShortBuffer arg1) {
		gl.glVariantsvEXT(arg0, arg1);
	}

	@Override
	public void glVariantubvEXT(int arg0, byte[] arg1, int arg2) {
		gl.glVariantubvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantubvEXT(int arg0, ByteBuffer arg1) {
		gl.glVariantubvEXT(arg0, arg1);
	}

	@Override
	public void glVariantuivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVariantuivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantuivEXT(int arg0, IntBuffer arg1) {
		gl.glVariantuivEXT(arg0, arg1);
	}

	@Override
	public void glVariantusvEXT(int arg0, short[] arg1, int arg2) {
		gl.glVariantusvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVariantusvEXT(int arg0, ShortBuffer arg1) {
		gl.glVariantusvEXT(arg0, arg1);
	}

	@Override
	public void glVertex2bOES(byte arg0) {
		gl.glVertex2bOES(arg0);
	}

	@Override
	public void glVertex2bvOES(byte[] arg0, int arg1) {
		gl.glVertex2bvOES(arg0, arg1);
	}

	@Override
	public void glVertex2bvOES(ByteBuffer arg0) {
		gl.glVertex2bvOES(arg0);
	}

	@Override
	public void glVertex2d(double arg0, double arg1) {
		gl.glVertex2d(arg0, arg1);
	}

	@Override
	public void glVertex2dv(double[] arg0, int arg1) {
		gl.glVertex2dv(arg0, arg1);
	}

	@Override
	public void glVertex2dv(DoubleBuffer arg0) {
		gl.glVertex2dv(arg0);
	}

	@Override
	public void glVertex2f(float arg0, float arg1) {
		gl.glVertex2f(arg0, arg1);
	}

	@Override
	public void glVertex2fv(float[] arg0, int arg1) {
		gl.glVertex2fv(arg0, arg1);
	}

	@Override
	public void glVertex2fv(FloatBuffer arg0) {
		gl.glVertex2fv(arg0);
	}

	@Override
	public void glVertex2h(short arg0, short arg1) {
		gl.glVertex2h(arg0, arg1);
	}

	@Override
	public void glVertex2hv(short[] arg0, int arg1) {
		gl.glVertex2hv(arg0, arg1);
	}

	@Override
	public void glVertex2hv(ShortBuffer arg0) {
		gl.glVertex2hv(arg0);
	}

	@Override
	public void glVertex2i(int arg0, int arg1) {
		gl.glVertex2i(arg0, arg1);
	}

	@Override
	public void glVertex2iv(int[] arg0, int arg1) {
		gl.glVertex2iv(arg0, arg1);
	}

	@Override
	public void glVertex2iv(IntBuffer arg0) {
		gl.glVertex2iv(arg0);
	}

	@Override
	public void glVertex2s(short arg0, short arg1) {
		gl.glVertex2s(arg0, arg1);
	}

	@Override
	public void glVertex2sv(short[] arg0, int arg1) {
		gl.glVertex2sv(arg0, arg1);
	}

	@Override
	public void glVertex2sv(ShortBuffer arg0) {
		gl.glVertex2sv(arg0);
	}

	@Override
	public void glVertex3bOES(byte arg0, byte arg1) {
		gl.glVertex3bOES(arg0, arg1);
	}

	@Override
	public void glVertex3bvOES(byte[] arg0, int arg1) {
		gl.glVertex3bvOES(arg0, arg1);
	}

	@Override
	public void glVertex3bvOES(ByteBuffer arg0) {
		gl.glVertex3bvOES(arg0);
	}

	@Override
	public void glVertex3d(double arg0, double arg1, double arg2) {
		gl.glVertex3d(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3dv(double[] arg0, int arg1) {
		gl.glVertex3dv(arg0, arg1);
	}

	@Override
	public void glVertex3dv(DoubleBuffer arg0) {
		gl.glVertex3dv(arg0);
	}

	@Override
	public void glVertex3f(float arg0, float arg1, float arg2) {
		gl.glVertex3f(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3fv(float[] arg0, int arg1) {
		gl.glVertex3fv(arg0, arg1);
	}

	@Override
	public void glVertex3fv(FloatBuffer arg0) {
		gl.glVertex3fv(arg0);
	}

	@Override
	public void glVertex3h(short arg0, short arg1, short arg2) {
		gl.glVertex3h(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3hv(short[] arg0, int arg1) {
		gl.glVertex3hv(arg0, arg1);
	}

	@Override
	public void glVertex3hv(ShortBuffer arg0) {
		gl.glVertex3hv(arg0);
	}

	@Override
	public void glVertex3i(int arg0, int arg1, int arg2) {
		gl.glVertex3i(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3iv(int[] arg0, int arg1) {
		gl.glVertex3iv(arg0, arg1);
	}

	@Override
	public void glVertex3iv(IntBuffer arg0) {
		gl.glVertex3iv(arg0);
	}

	@Override
	public void glVertex3s(short arg0, short arg1, short arg2) {
		gl.glVertex3s(arg0, arg1, arg2);
	}

	@Override
	public void glVertex3sv(short[] arg0, int arg1) {
		gl.glVertex3sv(arg0, arg1);
	}

	@Override
	public void glVertex3sv(ShortBuffer arg0) {
		gl.glVertex3sv(arg0);
	}

	@Override
	public void glVertex4bOES(byte arg0, byte arg1, byte arg2) {
		gl.glVertex4bOES(arg0, arg1, arg2);
	}

	@Override
	public void glVertex4bvOES(byte[] arg0, int arg1) {
		gl.glVertex4bvOES(arg0, arg1);
	}

	@Override
	public void glVertex4bvOES(ByteBuffer arg0) {
		gl.glVertex4bvOES(arg0);
	}

	@Override
	public void glVertex4d(double arg0, double arg1, double arg2, double arg3) {
		gl.glVertex4d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4dv(double[] arg0, int arg1) {
		gl.glVertex4dv(arg0, arg1);
	}

	@Override
	public void glVertex4dv(DoubleBuffer arg0) {
		gl.glVertex4dv(arg0);
	}

	@Override
	public void glVertex4f(float arg0, float arg1, float arg2, float arg3) {
		gl.glVertex4f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4fv(float[] arg0, int arg1) {
		gl.glVertex4fv(arg0, arg1);
	}

	@Override
	public void glVertex4fv(FloatBuffer arg0) {
		gl.glVertex4fv(arg0);
	}

	@Override
	public void glVertex4h(short arg0, short arg1, short arg2, short arg3) {
		gl.glVertex4h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4hv(short[] arg0, int arg1) {
		gl.glVertex4hv(arg0, arg1);
	}

	@Override
	public void glVertex4hv(ShortBuffer arg0) {
		gl.glVertex4hv(arg0);
	}

	@Override
	public void glVertex4i(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertex4i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4iv(int[] arg0, int arg1) {
		gl.glVertex4iv(arg0, arg1);
	}

	@Override
	public void glVertex4iv(IntBuffer arg0) {
		gl.glVertex4iv(arg0);
	}

	@Override
	public void glVertex4s(short arg0, short arg1, short arg2, short arg3) {
		gl.glVertex4s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertex4sv(short[] arg0, int arg1) {
		gl.glVertex4sv(arg0, arg1);
	}

	@Override
	public void glVertex4sv(ShortBuffer arg0) {
		gl.glVertex4sv(arg0);
	}

	@Override
	public void glVertexArrayBindVertexBufferEXT(int arg0, int arg1, int arg2, long arg3, int arg4) {
		gl.glVertexArrayBindVertexBufferEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayColorOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glVertexArrayColorOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexArrayEdgeFlagOffsetEXT(int arg0, int arg1, int arg2, long arg3) {
		gl.glVertexArrayEdgeFlagOffsetEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexArrayFogCoordOffsetEXT(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glVertexArrayFogCoordOffsetEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayIndexOffsetEXT(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glVertexArrayIndexOffsetEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayMultiTexCoordOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			long arg6) {
		gl.glVertexArrayMultiTexCoordOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glVertexArrayNormalOffsetEXT(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glVertexArrayNormalOffsetEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayParameteriAPPLE(int arg0, int arg1) {
		gl.glVertexArrayParameteriAPPLE(arg0, arg1);
	}

	@Override
	public void glVertexArrayRangeAPPLE(int arg0, Buffer arg1) {
		gl.glVertexArrayRangeAPPLE(arg0, arg1);
	}

	@Override
	public void glVertexArrayRangeNV(int arg0, Buffer arg1) {
		gl.glVertexArrayRangeNV(arg0, arg1);
	}

	@Override
	public void glVertexArraySecondaryColorOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glVertexArraySecondaryColorOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexArrayTexCoordOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glVertexArrayTexCoordOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexArrayVertexAttribBindingEXT(int arg0, int arg1, int arg2) {
		gl.glVertexArrayVertexAttribBindingEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexArrayVertexAttribFormatEXT(int arg0, int arg1, int arg2, int arg3, boolean arg4, int arg5) {
		gl.glVertexArrayVertexAttribFormatEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexArrayVertexAttribIFormatEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexArrayVertexAttribIFormatEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayVertexAttribIOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5,
			long arg6) {
		gl.glVertexArrayVertexAttribIOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Override
	public void glVertexArrayVertexAttribLFormatEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexArrayVertexAttribLFormatEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexArrayVertexAttribOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5,
			int arg6, long arg7) {
		gl.glVertexArrayVertexAttribOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
	}

	@Override
	public void glVertexArrayVertexBindingDivisorEXT(int arg0, int arg1, int arg2) {
		gl.glVertexArrayVertexBindingDivisorEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexArrayVertexOffsetEXT(int arg0, int arg1, int arg2, int arg3, int arg4, long arg5) {
		gl.glVertexArrayVertexOffsetEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttrib1d(int arg0, double arg1) {
		gl.glVertexAttrib1d(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dARB(int arg0, double arg1) {
		gl.glVertexAttrib1dARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dv(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib1dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1dv(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib1dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1dvARB(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib1dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1dvARB(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib1dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1f(int arg0, float arg1) {
		gl.glVertexAttrib1f(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fARB(int arg0, float arg1) {
		gl.glVertexAttrib1fARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fv(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib1fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1fv(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib1fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1fvARB(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib1fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1fvARB(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib1fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1h(int arg0, short arg1) {
		gl.glVertexAttrib1h(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1hv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib1hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1hv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib1hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1s(int arg0, short arg1) {
		gl.glVertexAttrib1s(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1sARB(int arg0, short arg1) {
		gl.glVertexAttrib1sARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1sv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib1sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1sv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib1sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib1svARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib1svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib1svARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib1svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2d(int arg0, double arg1, double arg2) {
		gl.glVertexAttrib2d(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dARB(int arg0, double arg1, double arg2) {
		gl.glVertexAttrib2dARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dv(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib2dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dv(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib2dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2dvARB(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib2dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2dvARB(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib2dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2f(int arg0, float arg1, float arg2) {
		gl.glVertexAttrib2f(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fARB(int arg0, float arg1, float arg2) {
		gl.glVertexAttrib2fARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fv(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib2fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fv(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib2fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2fvARB(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib2fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2fvARB(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib2fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2h(int arg0, short arg1, short arg2) {
		gl.glVertexAttrib2h(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2hv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib2hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2hv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib2hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2s(int arg0, short arg1, short arg2) {
		gl.glVertexAttrib2s(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sARB(int arg0, short arg1, short arg2) {
		gl.glVertexAttrib2sARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib2sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2sv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib2sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib2svARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib2svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib2svARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib2svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3d(int arg0, double arg1, double arg2, double arg3) {
		gl.glVertexAttrib3d(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3dARB(int arg0, double arg1, double arg2, double arg3) {
		gl.glVertexAttrib3dARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3dv(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib3dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3dv(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib3dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3dvARB(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib3dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3dvARB(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib3dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3f(int arg0, float arg1, float arg2, float arg3) {
		gl.glVertexAttrib3f(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3fARB(int arg0, float arg1, float arg2, float arg3) {
		gl.glVertexAttrib3fARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3fv(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib3fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3fv(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib3fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3fvARB(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib3fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3fvARB(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib3fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3h(int arg0, short arg1, short arg2, short arg3) {
		gl.glVertexAttrib3h(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3hv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib3hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3hv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib3hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3s(int arg0, short arg1, short arg2, short arg3) {
		gl.glVertexAttrib3s(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3sARB(int arg0, short arg1, short arg2, short arg3) {
		gl.glVertexAttrib3sARB(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttrib3sv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib3sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3sv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib3sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib3svARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib3svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib3svARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib3svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nbv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4Nbv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nbv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4Nbv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NbvARB(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4NbvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NbvARB(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4NbvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Niv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4Niv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Niv(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4Niv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NivARB(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4NivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NivARB(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4NivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nsv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4Nsv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nsv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4Nsv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NsvARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4NsvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NsvARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4NsvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nub(int arg0, byte arg1, byte arg2, byte arg3, byte arg4) {
		gl.glVertexAttrib4Nub(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4NubARB(int arg0, byte arg1, byte arg2, byte arg3, byte arg4) {
		gl.glVertexAttrib4NubARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4Nubv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4Nubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nubv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4Nubv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NubvARB(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4NubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NubvARB(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4NubvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nuiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4Nuiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nuiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4Nuiv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NuivARB(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4NuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NuivARB(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4NuivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4Nusv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4Nusv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4Nusv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4Nusv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4NusvARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4NusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4NusvARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4NusvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4bv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4bv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4bv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4bv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4bvARB(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4bvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4bvARB(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4bvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4d(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl.glVertexAttrib4d(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4dARB(int arg0, double arg1, double arg2, double arg3, double arg4) {
		gl.glVertexAttrib4dARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4dv(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib4dv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4dv(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib4dv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4dvARB(int arg0, double[] arg1, int arg2) {
		gl.glVertexAttrib4dvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4dvARB(int arg0, DoubleBuffer arg1) {
		gl.glVertexAttrib4dvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4f(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glVertexAttrib4f(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4fARB(int arg0, float arg1, float arg2, float arg3, float arg4) {
		gl.glVertexAttrib4fARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4fv(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib4fv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4fv(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib4fv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4fvARB(int arg0, float[] arg1, int arg2) {
		gl.glVertexAttrib4fvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4fvARB(int arg0, FloatBuffer arg1) {
		gl.glVertexAttrib4fvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4h(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl.glVertexAttrib4h(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4hv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4hv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4hv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4iv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4iv(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4iv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ivARB(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4ivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ivARB(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4ivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4s(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl.glVertexAttrib4s(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4sARB(int arg0, short arg1, short arg2, short arg3, short arg4) {
		gl.glVertexAttrib4sARB(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttrib4sv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4sv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4sv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4svARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4svARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4svARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4svARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ubv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4ubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ubv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4ubv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4ubvARB(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttrib4ubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4ubvARB(int arg0, ByteBuffer arg1) {
		gl.glVertexAttrib4ubvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4uiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4uiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4uivARB(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttrib4uivARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4uivARB(int arg0, IntBuffer arg1) {
		gl.glVertexAttrib4uivARB(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4usv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4usv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4usv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4usv(arg0, arg1);
	}

	@Override
	public void glVertexAttrib4usvARB(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttrib4usvARB(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttrib4usvARB(int arg0, ShortBuffer arg1) {
		gl.glVertexAttrib4usvARB(arg0, arg1);
	}

	@Override
	public void glVertexAttribFormatNV(int arg0, int arg1, int arg2, boolean arg3, int arg4) {
		gl.glVertexAttribFormatNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI1i(int arg0, int arg1) {
		gl.glVertexAttribI1i(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1iEXT(int arg0, int arg1) {
		gl.glVertexAttribI1iEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1iv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI1iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1iv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI1iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1ivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI1ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1ivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI1ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1ui(int arg0, int arg1) {
		gl.glVertexAttribI1ui(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uiEXT(int arg0, int arg1) {
		gl.glVertexAttribI1uiEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI1uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1uiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI1uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI1uivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI1uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI1uivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI1uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2i(int arg0, int arg1, int arg2) {
		gl.glVertexAttribI2i(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iEXT(int arg0, int arg1, int arg2) {
		gl.glVertexAttribI2iEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI2iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2iv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI2iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2ivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI2ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2ivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI2ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2ui(int arg0, int arg1, int arg2) {
		gl.glVertexAttribI2ui(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiEXT(int arg0, int arg1, int arg2) {
		gl.glVertexAttribI2uiEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI2uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI2uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI2uivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI2uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI2uivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI2uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3i(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribI3i(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3iEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribI3iEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3iv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI3iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3iv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI3iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3ivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI3ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3ivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI3ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3ui(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribI3ui(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3uiEXT(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribI3uiEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribI3uiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI3uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3uiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI3uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI3uivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI3uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI3uivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI3uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4bv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttribI4bv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4bv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttribI4bv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4bvEXT(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttribI4bvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4bvEXT(int arg0, ByteBuffer arg1) {
		gl.glVertexAttribI4bvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4i(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexAttribI4i(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4iEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexAttribI4iEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4iv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI4iv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4iv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI4iv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI4ivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI4ivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4sv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttribI4sv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4sv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttribI4sv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4svEXT(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttribI4svEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4svEXT(int arg0, ShortBuffer arg1) {
		gl.glVertexAttribI4svEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ubv(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttribI4ubv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ubv(int arg0, ByteBuffer arg1) {
		gl.glVertexAttribI4ubv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ubvEXT(int arg0, byte[] arg1, int arg2) {
		gl.glVertexAttribI4ubvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4ubvEXT(int arg0, ByteBuffer arg1) {
		gl.glVertexAttribI4ubvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4ui(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexAttribI4ui(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4uiEXT(int arg0, int arg1, int arg2, int arg3, int arg4) {
		gl.glVertexAttribI4uiEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribI4uiv(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI4uiv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4uiv(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI4uiv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4uivEXT(int arg0, int[] arg1, int arg2) {
		gl.glVertexAttribI4uivEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4uivEXT(int arg0, IntBuffer arg1) {
		gl.glVertexAttribI4uivEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4usv(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttribI4usv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4usv(int arg0, ShortBuffer arg1) {
		gl.glVertexAttribI4usv(arg0, arg1);
	}

	@Override
	public void glVertexAttribI4usvEXT(int arg0, short[] arg1, int arg2) {
		gl.glVertexAttribI4usvEXT(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribI4usvEXT(int arg0, ShortBuffer arg1) {
		gl.glVertexAttribI4usvEXT(arg0, arg1);
	}

	@Override
	public void glVertexAttribIFormatNV(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribIFormatNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribIPointer(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glVertexAttribIPointer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribIPointer(int arg0, int arg1, int arg2, int arg3, long arg4) {
		gl.glVertexAttribIPointer(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribIPointerEXT(int arg0, int arg1, int arg2, int arg3, Buffer arg4) {
		gl.glVertexAttribIPointerEXT(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL1i64NV(int arg0, long arg1) {
		gl.glVertexAttribL1i64NV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1i64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL1i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL1i64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL1i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1ui64NV(int arg0, long arg1) {
		gl.glVertexAttribL1ui64NV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL1ui64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL1ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL1ui64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL1ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL2i64NV(int arg0, long arg1, long arg2) {
		gl.glVertexAttribL2i64NV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2i64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL2i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2i64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL2i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL2ui64NV(int arg0, long arg1, long arg2) {
		gl.glVertexAttribL2ui64NV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2ui64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL2ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL2ui64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL2ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL3i64NV(int arg0, long arg1, long arg2, long arg3) {
		gl.glVertexAttribL3i64NV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribL3i64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL3i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL3i64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL3i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL3ui64NV(int arg0, long arg1, long arg2, long arg3) {
		gl.glVertexAttribL3ui64NV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribL3ui64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL3ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL3ui64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL3ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL4i64NV(int arg0, long arg1, long arg2, long arg3, long arg4) {
		gl.glVertexAttribL4i64NV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL4i64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL4i64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL4i64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL4i64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribL4ui64NV(int arg0, long arg1, long arg2, long arg3, long arg4) {
		gl.glVertexAttribL4ui64NV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVertexAttribL4ui64vNV(int arg0, long[] arg1, int arg2) {
		gl.glVertexAttribL4ui64vNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribL4ui64vNV(int arg0, LongBuffer arg1) {
		gl.glVertexAttribL4ui64vNV(arg0, arg1);
	}

	@Override
	public void glVertexAttribLFormatNV(int arg0, int arg1, int arg2, int arg3) {
		gl.glVertexAttribLFormatNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribParameteriAMD(int arg0, int arg1, int arg2) {
		gl.glVertexAttribParameteriAMD(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribPointer(GLArrayData arg0) {
		gl.glVertexAttribPointer(arg0);
	}

	@Override
	public void glVertexAttribPointer(int arg0, int arg1, int arg2, boolean arg3, int arg4, Buffer arg5) {
		gl.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointer(int arg0, int arg1, int arg2, boolean arg3, int arg4, long arg5) {
		gl.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointerARB(int arg0, int arg1, int arg2, boolean arg3, int arg4, Buffer arg5) {
		gl.glVertexAttribPointerARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribPointerARB(int arg0, int arg1, int arg2, boolean arg3, int arg4, long arg5) {
		gl.glVertexAttribPointerARB(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glVertexAttribs1hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glVertexAttribs1hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs1hv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glVertexAttribs1hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs2hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glVertexAttribs2hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs2hv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glVertexAttribs2hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs3hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glVertexAttribs3hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs3hv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glVertexAttribs3hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexAttribs4hv(int arg0, int arg1, short[] arg2, int arg3) {
		gl.glVertexAttribs4hv(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexAttribs4hv(int arg0, int arg1, ShortBuffer arg2) {
		gl.glVertexAttribs4hv(arg0, arg1, arg2);
	}

	@Override
	public void glVertexBlendARB(int arg0) {
		gl.glVertexBlendARB(arg0);
	}

	@Override
	public void glVertexFormatNV(int arg0, int arg1, int arg2) {
		gl.glVertexFormatNV(arg0, arg1, arg2);
	}

	@Override
	public void glVertexPointer(GLArrayData arg0) {
		gl.glVertexPointer(arg0);
	}

	@Override
	public void glVertexPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glVertexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexPointer(int arg0, int arg1, int arg2, long arg3) {
		gl.glVertexPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightPointerEXT(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glVertexWeightPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightPointerEXT(int arg0, int arg1, int arg2, long arg3) {
		gl.glVertexWeightPointerEXT(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVertexWeightfEXT(float arg0) {
		gl.glVertexWeightfEXT(arg0);
	}

	@Override
	public void glVertexWeightfvEXT(float[] arg0, int arg1) {
		gl.glVertexWeightfvEXT(arg0, arg1);
	}

	@Override
	public void glVertexWeightfvEXT(FloatBuffer arg0) {
		gl.glVertexWeightfvEXT(arg0);
	}

	@Override
	public void glVertexWeighth(short arg0) {
		gl.glVertexWeighth(arg0);
	}

	@Override
	public void glVertexWeighthv(short[] arg0, int arg1) {
		gl.glVertexWeighthv(arg0, arg1);
	}

	@Override
	public void glVertexWeighthv(ShortBuffer arg0) {
		gl.glVertexWeighthv(arg0);
	}

	@Override
	public int glVideoCaptureNV(int arg0, int[] arg1, int arg2, long[] arg3, int arg4) {
		return gl.glVideoCaptureNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public int glVideoCaptureNV(int arg0, IntBuffer arg1, LongBuffer arg2) {
		return gl.glVideoCaptureNV(arg0, arg1, arg2);
	}

	@Override
	public void glVideoCaptureStreamParameterdvNV(int arg0, int arg1, int arg2, double[] arg3, int arg4) {
		gl.glVideoCaptureStreamParameterdvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterdvNV(int arg0, int arg1, int arg2, DoubleBuffer arg3) {
		gl.glVideoCaptureStreamParameterdvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVideoCaptureStreamParameterfvNV(int arg0, int arg1, int arg2, float[] arg3, int arg4) {
		gl.glVideoCaptureStreamParameterfvNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterfvNV(int arg0, int arg1, int arg2, FloatBuffer arg3) {
		gl.glVideoCaptureStreamParameterfvNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glVideoCaptureStreamParameterivNV(int arg0, int arg1, int arg2, int[] arg3, int arg4) {
		gl.glVideoCaptureStreamParameterivNV(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void glVideoCaptureStreamParameterivNV(int arg0, int arg1, int arg2, IntBuffer arg3) {
		gl.glVideoCaptureStreamParameterivNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glViewport(int arg0, int arg1, int arg2, int arg3) {
		gl.glViewport(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glWeightPathsNV(int arg0, int arg1, int[] arg2, int arg3, float[] arg4, int arg5) {
		gl.glWeightPathsNV(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public void glWeightPathsNV(int arg0, int arg1, IntBuffer arg2, FloatBuffer arg3) {
		gl.glWeightPathsNV(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glWeightPointer(int arg0, int arg1, int arg2, Buffer arg3) {
		gl.glWeightPointer(arg0, arg1, arg2, arg3);
	}

	@Override
	public void glWeightbvARB(int arg0, byte[] arg1, int arg2) {
		gl.glWeightbvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightbvARB(int arg0, ByteBuffer arg1) {
		gl.glWeightbvARB(arg0, arg1);
	}

	@Override
	public void glWeightdvARB(int arg0, double[] arg1, int arg2) {
		gl.glWeightdvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightdvARB(int arg0, DoubleBuffer arg1) {
		gl.glWeightdvARB(arg0, arg1);
	}

	@Override
	public void glWeightfvARB(int arg0, float[] arg1, int arg2) {
		gl.glWeightfvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightfvARB(int arg0, FloatBuffer arg1) {
		gl.glWeightfvARB(arg0, arg1);
	}

	@Override
	public void glWeightivARB(int arg0, int[] arg1, int arg2) {
		gl.glWeightivARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightivARB(int arg0, IntBuffer arg1) {
		gl.glWeightivARB(arg0, arg1);
	}

	@Override
	public void glWeightsvARB(int arg0, short[] arg1, int arg2) {
		gl.glWeightsvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightsvARB(int arg0, ShortBuffer arg1) {
		gl.glWeightsvARB(arg0, arg1);
	}

	@Override
	public void glWeightubvARB(int arg0, byte[] arg1, int arg2) {
		gl.glWeightubvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightubvARB(int arg0, ByteBuffer arg1) {
		gl.glWeightubvARB(arg0, arg1);
	}

	@Override
	public void glWeightuivARB(int arg0, int[] arg1, int arg2) {
		gl.glWeightuivARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightuivARB(int arg0, IntBuffer arg1) {
		gl.glWeightuivARB(arg0, arg1);
	}

	@Override
	public void glWeightusvARB(int arg0, short[] arg1, int arg2) {
		gl.glWeightusvARB(arg0, arg1, arg2);
	}

	@Override
	public void glWeightusvARB(int arg0, ShortBuffer arg1) {
		gl.glWeightusvARB(arg0, arg1);
	}

	@Override
	public void glWindowPos2d(double arg0, double arg1) {
		gl.glWindowPos2d(arg0, arg1);
	}

	@Override
	public void glWindowPos2dv(double[] arg0, int arg1) {
		gl.glWindowPos2dv(arg0, arg1);
	}

	@Override
	public void glWindowPos2dv(DoubleBuffer arg0) {
		gl.glWindowPos2dv(arg0);
	}

	@Override
	public void glWindowPos2f(float arg0, float arg1) {
		gl.glWindowPos2f(arg0, arg1);
	}

	@Override
	public void glWindowPos2fv(float[] arg0, int arg1) {
		gl.glWindowPos2fv(arg0, arg1);
	}

	@Override
	public void glWindowPos2fv(FloatBuffer arg0) {
		gl.glWindowPos2fv(arg0);
	}

	@Override
	public void glWindowPos2i(int arg0, int arg1) {
		gl.glWindowPos2i(arg0, arg1);
	}

	@Override
	public void glWindowPos2iv(int[] arg0, int arg1) {
		gl.glWindowPos2iv(arg0, arg1);
	}

	@Override
	public void glWindowPos2iv(IntBuffer arg0) {
		gl.glWindowPos2iv(arg0);
	}

	@Override
	public void glWindowPos2s(short arg0, short arg1) {
		gl.glWindowPos2s(arg0, arg1);
	}

	@Override
	public void glWindowPos2sv(short[] arg0, int arg1) {
		gl.glWindowPos2sv(arg0, arg1);
	}

	@Override
	public void glWindowPos2sv(ShortBuffer arg0) {
		gl.glWindowPos2sv(arg0);
	}

	@Override
	public void glWindowPos3d(double arg0, double arg1, double arg2) {
		gl.glWindowPos3d(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3dv(double[] arg0, int arg1) {
		gl.glWindowPos3dv(arg0, arg1);
	}

	@Override
	public void glWindowPos3dv(DoubleBuffer arg0) {
		gl.glWindowPos3dv(arg0);
	}

	@Override
	public void glWindowPos3f(float arg0, float arg1, float arg2) {
		gl.glWindowPos3f(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3fv(float[] arg0, int arg1) {
		gl.glWindowPos3fv(arg0, arg1);
	}

	@Override
	public void glWindowPos3fv(FloatBuffer arg0) {
		gl.glWindowPos3fv(arg0);
	}

	@Override
	public void glWindowPos3i(int arg0, int arg1, int arg2) {
		gl.glWindowPos3i(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3iv(int[] arg0, int arg1) {
		gl.glWindowPos3iv(arg0, arg1);
	}

	@Override
	public void glWindowPos3iv(IntBuffer arg0) {
		gl.glWindowPos3iv(arg0);
	}

	@Override
	public void glWindowPos3s(short arg0, short arg1, short arg2) {
		gl.glWindowPos3s(arg0, arg1, arg2);
	}

	@Override
	public void glWindowPos3sv(short[] arg0, int arg1) {
		gl.glWindowPos3sv(arg0, arg1);
	}

	@Override
	public void glWindowPos3sv(ShortBuffer arg0) {
		gl.glWindowPos3sv(arg0);
	}

	@Override
	public void glWriteMaskEXT(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		gl.glWriteMaskEXT(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	@Override
	public boolean hasBasicFBOSupport() {
		return gl.hasBasicFBOSupport();
	}

	@Override
	public boolean hasFullFBOSupport() {
		return gl.hasFullFBOSupport();
	}

	@Override
	public boolean hasGLSL() {
		return gl.hasGLSL();
	}

	@Override
	public boolean isExtensionAvailable(String arg0) {
		return gl.isExtensionAvailable(arg0);
	}

	@Override
	public boolean isFunctionAvailable(String arg0) {
		return gl.isFunctionAvailable(arg0);
	}

	@Override
	public boolean isGL() {
		return gl.isGL();
	}

	@Override
	public boolean isGL2() {
		return gl.isGL2();
	}

	@Override
	public boolean isGL2ES1() {
		return gl.isGL2ES1();
	}

	@Override
	public boolean isGL2ES2() {
		return gl.isGL2ES2();
	}

	@Override
	public boolean isGL2ES3() {
		return gl.isGL2ES3();
	}

	@Override
	public boolean isGL2GL3() {
		return gl.isGL2GL3();
	}

	@Override
	public boolean isGL3() {
		return gl.isGL3();
	}

	@Override
	public boolean isGL3ES3() {
		return gl.isGL3ES3();
	}

	@Override
	public boolean isGL3bc() {
		return gl.isGL3bc();
	}

	@Override
	public boolean isGL3core() {
		return gl.isGL3core();
	}

	@Override
	public boolean isGL4() {
		return gl.isGL4();
	}

	@Override
	public boolean isGL4ES3() {
		return gl.isGL4ES3();
	}

	@Override
	public boolean isGL4bc() {
		return gl.isGL4bc();
	}

	@Override
	public boolean isGL4core() {
		return gl.isGL4core();
	}

	@Override
	public boolean isGLES() {
		return gl.isGLES();
	}

	@Override
	public boolean isGLES1() {
		return gl.isGLES1();
	}

	@Override
	public boolean isGLES2() {
		return gl.isGLES2();
	}

	@Override
	public boolean isGLES2Compatible() {
		return gl.isGLES2Compatible();
	}

	@Override
	public boolean isGLES3() {
		return gl.isGLES3();
	}

	@Override
	public boolean isGLES3Compatible() {
		return gl.isGLES3Compatible();
	}

	@Override
	public boolean isGLcore() {
		return gl.isGLcore();
	}

	@Override
	public boolean isNPOTTextureAvailable() {
		return gl.isNPOTTextureAvailable();
	}

	@Override
	public boolean isPBOPackBound() {
		return gl.isPBOPackBound();
	}

	@Override
	public boolean isPBOUnpackBound() {
		return gl.isPBOUnpackBound();
	}

	@Override
	public boolean isTextureFormatBGRA8888Available() {
		return gl.isTextureFormatBGRA8888Available();
	}

	@Override
	public boolean isVBOArrayBound() {
		return gl.isVBOArrayBound();
	}

	@Override
	public boolean isVBOElementArrayBound() {
		return gl.isVBOElementArrayBound();
	}

	@Override
	public GLBufferStorage mapBuffer(int arg0, int arg1) throws GLException {
		return gl.mapBuffer(arg0, arg1);
	}

	@Override
	public GLBufferStorage mapBufferRange(int arg0, long arg1, long arg2, int arg3) throws GLException {
		return gl.mapBufferRange(arg0, arg1, arg2, arg3);
	}

	@Override
	public GLBufferStorage mapNamedBuffer(int arg0, int arg1) throws GLException {
		return gl.mapNamedBuffer(arg0, arg1);
	}

	@Override
	public GLBufferStorage mapNamedBufferRange(int arg0, long arg1, long arg2, int arg3) throws GLException {
		return gl.mapNamedBufferRange(arg0, arg1, arg2, arg3);
	}

	@Override
	public void setSwapInterval(int arg0) {
		gl.setSwapInterval(arg0);
	}

}
