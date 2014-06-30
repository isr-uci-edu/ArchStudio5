// Many GPU drivers refuse to compile the shader if #version is different from the drivers internal GLSL version.
// #version 130

#if __VERSION__ >= 130
	#define varying in
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

uniform int uniform_stipple;

// Incoming varying data sent from the vertex shader
varying vec4 varying_color;
varying float varying_stipple_offset;

void main (void)
{
	int bit = int(floor(varying_stipple_offset)) % 16;
	bool on = ((uniform_stipple >> bit) & 1) != 0;
	if (on) {
		gl_FragColor = varying_color;
	} else {
		discard;
	}
}
