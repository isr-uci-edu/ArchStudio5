package org.archstudio.bna.ui.jogl.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLException;

import org.archstudio.sysutils.Disposable;

import com.google.common.io.ByteStreams;

public final class GL2ES2Shader implements Disposable {

	public static GL2ES2Shader create(GL2ES2 gl, int type, URL url) throws GLException {
		return new GL2ES2Shader(gl, type, url);
	}

	private static void dispose(GL2ES2Shader shader) throws GLException {
		shader.gl.glDeleteShader(shader.shader);
	}

	private final GL2ES2 gl;
	private final int type;
	private final URL url;
	private final int shader;

	private GL2ES2Shader(GL2ES2 gl, int type, URL url) throws GLException {
		this.gl = gl;
		this.type = type;
		this.url = url;
		this.shader = load();
	}

	private int load() {

		// verify capability to compile shaders
		{
			byte[] compiler = new byte[1];
			gl.glGetBooleanv(GL2ES2.GL_SHADER_COMPILER, compiler, 0);
			if (compiler[0] != GL.GL_TRUE) {
				throw new GLException("Cannot compile shaders.");
			}
		}

		// obtain shader source
		String source;
		try {
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			source = new String(ByteStreams.toByteArray(connection.getInputStream())).replaceAll("\r\n?", "\n");
			String glVersion = gl.glGetString(GL.GL_VERSION);
			String glslVersion = gl.glGetString(GL2ES2.GL_SHADING_LANGUAGE_VERSION);
			String version;
			Matcher versionMatcher = Pattern.compile("[^0-9]*([0-9\\.]+)").matcher(glslVersion);
			if (versionMatcher.find()) {
				version = versionMatcher.group(1).replace(".", "");
			}
			else {
				throw new GLException("Unrecognized GLSL Version: " + glslVersion);
			}
			source = "#version " + version //
					+ "\n// GL Version: " + glVersion //
					+ "\n// GLSL Version: " + glslVersion //
					+ "\n\n" + source;
		}
		catch (IOException e) {
			throw new GLException(e);
		}

		// create shader
		int shader = gl.glCreateShader(type);
		if (shader == 0) {
			throw new GLException("Cannot create shader.");
		}
		try {
			// compile shader
			gl.glShaderSource(shader, 1, new String[] { source }, new int[] { source.length() }, 0);
			gl.glCompileShader(shader);
			int[] compiled = new int[1];
			gl.glGetShaderiv(shader, GL2ES2.GL_COMPILE_STATUS, compiled, 0);
			if (compiled[0] != GL.GL_TRUE) {
				int[] logLength = new int[1];
				gl.glGetShaderiv(shader, GL2ES2.GL_INFO_LOG_LENGTH, logLength, 0);
				byte[] logBytes = new byte[logLength[0]];
				if (logLength[0] > 0) {
					gl.glGetShaderInfoLog(shader, logBytes.length, logLength, 0, logBytes, 0);
				}
				throw new GLException("URL:\n" + url + "\nCompiler error:\n" + new String(logBytes, 0, logLength[0])
						+ "\nSource:\n" + source);
			}
		}
		catch (GLException e) {
			dispose(this);
			throw e;
		}

		return shader;
	}

	@Override
	public void dispose() {
		try {
			dispose(this);
		}
		catch (Exception e) {
		}
	}

	public int getShader() {
		return shader;
	}

}
