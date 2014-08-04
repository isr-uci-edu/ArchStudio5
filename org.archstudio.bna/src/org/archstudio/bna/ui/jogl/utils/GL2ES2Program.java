package org.archstudio.bna.ui.jogl.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLException;

import org.archstudio.sysutils.Disposable;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jogamp.common.nio.Buffers;

public final class GL2ES2Program implements Disposable {

	private static int[] vbo;

	public static GL2ES2Program create(GL2ES2 gl, GL2ES2Shader... shaders) {
		return new GL2ES2Program(gl, shaders);
	}

	private final GL2ES2 gl;
	private final List<GL2ES2Shader> shaders;
	private int program;
	private final int maxAttributes;
	private final Map<String, Integer> attributes = Maps.newHashMap();
	private int openAttribute = 1; // 0 is reserved
	private boolean valid = false;

	private GL2ES2Program(GL2ES2 gl, GL2ES2Shader... shaders) {
		this.gl = gl;
		this.shaders = Lists.newArrayList(Arrays.asList(shaders));
		{
			int[] maxAttributes = new int[1];
			gl.glGetIntegerv(GL2ES2.GL_MAX_VERTEX_ATTRIBS, maxAttributes, 0);
			this.maxAttributes = maxAttributes[0];
		}
	}

	public void bindAttribute(String name, int size) throws GLException {
		if (attributes.get(name) != null) {
			throw new GLException("Attribute already bound: " + name);
		}
		if (openAttribute + size >= maxAttributes) {
			throw new GLException("Exceeded max attributes.");
		}
		attributes.put(name, openAttribute);
		openAttribute += size;
	}

	public void link() throws GLException {
		dispose();

		program = gl.glCreateProgram();
		if (program == 0) {
			throw new GLException("Cannot create program.");
		}
		try {
			for (GL2ES2Shader shader : shaders) {
				gl.glAttachShader(program, shader.getShader());
			}
			for (Entry<String, Integer> entry : attributes.entrySet()) {
				gl.glBindAttribLocation(program, entry.getValue(), entry.getKey());
			}

			gl.glLinkProgram(program);
			int[] linked = new int[1];
			gl.glGetProgramiv(program, GL2ES2.GL_LINK_STATUS, linked, 0);
			if (linked[0] != GL.GL_TRUE) {
				int[] logLength = new int[1];
				gl.glGetProgramiv(program, GL2ES2.GL_INFO_LOG_LENGTH, logLength, 0);
				byte[] logBytes = new byte[logLength[0]];
				if (logLength[0] > 0) {
					gl.glGetProgramInfoLog(program, logLength[0], (int[]) null, 0, logBytes, 0);
				}
				throw new GLException("Link error:\n" + new String(logBytes));
			}
			gl.glValidateProgram(program);
			int[] valid = new int[1];
			gl.glGetProgramiv(program, GL2ES2.GL_VALIDATE_STATUS, valid, 0);
			if (valid[0] != GL.GL_TRUE) {
				int[] logLength = new int[1];
				gl.glGetProgramiv(program, GL2ES2.GL_INFO_LOG_LENGTH, logLength, 0);
				byte[] logBytes = new byte[logLength[0]];
				if (logLength[0] > 0) {
					gl.glGetProgramInfoLog(program, logLength[0], (int[]) null, 0, logBytes, 0);
				}
				System.err.println(new String(logBytes));
				throw new GLException("Validation error:\n" + new String(logBytes));
			}

			this.valid = true;
		}
		catch (Exception e) {
			if (program != 0) {
				gl.glDeleteProgram(program);
				program = 0;
			}
		}
	}

	@Override
	public void dispose() {
		valid = false;
		if (vbo != null) {
			gl.glDeleteBuffers(vbo.length, vbo, 0);
			vbo = null;
		}
		if (program != 0) {
			int[] count = new int[1];
			int[] shaders = new int[1];
			while (true) {
				gl.glGetAttachedShaders(program, 1, count, 0, shaders, 0);
				if (count[0] == 0) {
					break;
				}
				gl.glDetachShader(program, shaders[0]);
			}
			gl.glDeleteProgram(program);
			program = 0;
		}
	}

	public int getAttribute(String name) {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		Integer value = attributes.get(name);
		if (value == null) {
			throw new GLException("Attribute not bound: " + name);
		}
		return value;
	}

	public int getUniform(String name) throws GLException {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		int value = gl.glGetUniformLocation(program, name);
		if (value == -1) {
			throw new GLException("Uniform not recognized: " + name);
		}
		return value;
	}

	public void use() throws GLException {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		int[] value = new int[1];
		gl.glGetProgramiv(program, GL2ES2.GL_VALIDATE_STATUS, value, 0);
		if (value[0] != GL.GL_TRUE) {
			link();
		}
		gl.glUseProgram(program);
		if (vbo == null || vbo.length < openAttribute) {
			if (vbo != null) {
				gl.glDeleteBuffers(vbo.length, vbo, 0);
			}
			vbo = new int[openAttribute];
			gl.glGenBuffers(vbo.length, vbo, 0);
		}
	}

	public void bindBufferData(int target, String name, int numberOfUnits, FloatBuffer values, int usage, int unitSize,
			boolean normalized) {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		Preconditions.checkPositionIndex(values.position() + numberOfUnits * unitSize, values.limit());
		int attribute = getAttribute(name);
		boolean refereshedVBO = false;
		while (true) {
			try {
				gl.glBindBuffer(target, vbo[attribute - 1]);
				gl.glBufferData(target, Buffers.SIZEOF_FLOAT * numberOfUnits * unitSize, values, usage);
				if (gl.glGetError() == GL.GL_INVALID_OPERATION) {
					throw new GLException("GL-Error 0x502");
				}
				gl.glVertexAttribPointer(attribute, unitSize, GL.GL_FLOAT, normalized, 0, 0);
				gl.glEnableVertexAttribArray(attribute);
				gl.glBindBuffer(target, 0);
				return;
			}
			catch (GLException e) {
				if (!refereshedVBO) {
					refereshedVBO = true;
					gl.glDeleteBuffers(1, vbo, attribute - 1);
					gl.glGenBuffers(1, vbo, attribute - 1);
					continue;
				}
				throw e;
			}
		}
	}

	public void bindBufferData(int target, String name, int numberOfUnits, IntBuffer values, int usage, int unitSize,
			boolean normalized) {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		Preconditions.checkPositionIndex(values.position() + numberOfUnits * unitSize, values.limit());
		int attribute = getAttribute(name);
		boolean refereshedVBO = false;
		while (true) {
			try {
				gl.glBindBuffer(target, vbo[attribute - 1]);
				gl.glBufferData(target, Buffers.SIZEOF_INT * numberOfUnits * unitSize, values, usage);
				if (gl.glGetError() == GL.GL_INVALID_OPERATION) {
					throw new GLException("GL-Error 0x502");
				}
				gl.glVertexAttribPointer(attribute, unitSize, GL2ES2.GL_INT, normalized, 0, 0);
				gl.glEnableVertexAttribArray(attribute);
				gl.glBindBuffer(target, 0);
				return;
			}
			catch (GLException e) {
				if (!refereshedVBO) {
					refereshedVBO = true;
					gl.glDeleteBuffers(1, vbo, attribute - 1);
					gl.glGenBuffers(1, vbo, attribute - 1);
					continue;
				}
				throw e;
			}
		}
	}

	public void done() throws GLException {
		if (!valid) {
			throw new GLException("Program not linked.");
		}

		for (int attribute : attributes.values()) {
			gl.glDisableVertexAttribArray(attribute);
		}
		gl.glUseProgram(0);
	}

}
