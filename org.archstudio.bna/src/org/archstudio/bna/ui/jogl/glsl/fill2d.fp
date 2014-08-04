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

varying vec4 varying_rgba;

void main (void)
{
	mgl_FragColor = varying_rgba;
}