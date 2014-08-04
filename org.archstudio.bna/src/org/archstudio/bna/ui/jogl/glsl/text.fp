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

uniform sampler2D uniform_texture;

// Incoming varying data sent from the vertex shader
varying vec4 varying_texture_position;

void main (void)
{
	mgl_FragColor.rgba = texture2D(uniform_texture, varying_texture_position.xy).rgba;
	mgl_FragColor.a = 1.0;
}