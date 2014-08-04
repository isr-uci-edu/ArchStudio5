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

uniform sampler2D uniform_texture; // texture to blur
uniform vec2 uniform_resolution; // = textureSize(texture, 0); // <-- doesn't work on a Mac
uniform int uniform_size; // blur size
uniform vec2 uniform_direction; // blur direction
uniform float uniform_alpha; // blur alpha

void main(void)
{
	vec2 location = gl_FragCoord.xy;
	vec4 color = texture2D(uniform_texture, location / uniform_resolution);
	float total = 1;
	for(int offset = 1; offset < uniform_size; offset++){
		float intensity = exp(float(-offset) * offset / 2 / uniform_size / uniform_size);
		total += 2 * intensity;
		color += texture2D(uniform_texture, (location - float(offset) * uniform_direction) / uniform_resolution) * intensity;
		color += texture2D(uniform_texture, (location + float(offset) * uniform_direction) / uniform_resolution) * intensity;
	}
	mgl_FragColor = color / total;
	mgl_FragColor.a *= uniform_alpha;
}