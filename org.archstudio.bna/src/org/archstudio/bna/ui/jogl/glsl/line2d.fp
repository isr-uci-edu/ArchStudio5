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

uniform vec2 uniform_rgb_offsets[3];
uniform vec4 uniform_screen_dimension;
uniform int uniform_stipple;
uniform float uniform_width;

// Incoming varying data sent from the vertex shader
varying vec4 varying_screen_pixel;
varying float varying_stipple_offset;

void main (void)
{
	int bit = int(round(varying_stipple_offset)) % 16;
	bool bit_on = ((uniform_stipple >> bit) & 1) != 0;
	
	if (bit_on) {
		vec4 coord = gl_FragCoord; // in screen pixel coordinates already
		vec4 point = varying_screen_pixel;

		bool red_on = distance(point.xy, coord.xy + uniform_rgb_offsets[0]) <= uniform_width / 2;
		bool green_on = distance(point.xy, coord.xy + uniform_rgb_offsets[1]) <= uniform_width / 2;
		bool blue_on = distance(point.xy, coord.xy + uniform_rgb_offsets[2]) <= uniform_width / 2;

		mgl_FragColor = vec4(red_on ? 1 : 0, green_on ? 1 : 0, blue_on ? 1 : 0, 1);
		
		return;
	}
	discard;
}
