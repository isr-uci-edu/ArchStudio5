// Many GPU drivers refuse to compile the shader if #version is different from the drivers internal GLSL version.
// #version 130

#if __VERSION__ >= 130
	#define varying in
	#define texture2D texture
	out vec4 mgl_FragColor;
#else
	#define mgl_FragColor gl_FragColor	
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

uniform vec4 uniform_rgba;
uniform int uniform_stipple;

// Incoming varying data sent from the vertex shader
varying float varying_stipple_offset;

void main (void)
{
	int bit = int(floor(varying_stipple_offset)) % 16;
	bool on = ((uniform_stipple >> bit) & 1) != 0;
	if (on) {
		mgl_FragColor = uniform_rgba;
	} else {
		discard;
	}
}
