#version 120

#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D texture; // texture to blur
uniform vec2 resolution; // = textureSize(texture, 0); // <-- doesn't work on a Mac
uniform int size; // blur size
uniform vec2 direction; // direction of blur
uniform float alpha;

void main(void)
{
	vec2 location = gl_FragCoord.xy;
	vec4 color = texture2D(texture, location/resolution);
	float total = 1;
	for(int offset=1; offset<size; offset++){
		float intensity = exp(float(-offset) * offset / 2 / size / size);
		//float intensity = float(size - offset) / size;
		total += 2 * intensity;
		color += texture2D(texture, (location - float(offset) * direction)/resolution) * intensity;
		color += texture2D(texture, (location + float(offset) * direction)/resolution) * intensity;
	}
	gl_FragColor = color / total;
	gl_FragColor.a *= alpha;
}