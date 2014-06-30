// Many GPU drivers refuse to compile the shader if #version is different from the drivers internal GLSL version.
// #version 130

#if __VERSION__ >= 130
	#define varying in
	out vec4 mgl_FragColor;
	#define texture2D texture
	#define gl_FragColor mgl_FragColor
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

// Incoming varying data sent from the vertex shader
varying vec4 varying_color;

void main (void)
{
	gl_FragColor = varying_color;
}