#version 120

#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D texture; // texture to blur
uniform vec2 resolution; // = textureSize(texture, 0); // <-- doesn't work on a Mac
uniform int size; // blur size
vec2 maxxy = vec2((resolution.x - 1.0) / resolution.x, (resolution.y - 1.0)/resolution.y);

vec2 clampxy(float x, float y){
	return vec2(clamp(x/resolution.x, 0.0, maxxy.x), clamp(y/resolution.y, 0.0, maxxy.y));
}

void main(void)
{
	vec4 color = vec4(0.0); // resulting color
	float total = 1;
	color += texture2D(texture, clampxy(gl_FragCoord.x, gl_FragCoord.y));
	for(int offset=1; offset<=size; offset++){
		float intensity = exp(- offset * offset / 2 / size / size);
		total += 2 * intensity;
		color += texture2D(texture, clampxy(gl_FragCoord.x, gl_FragCoord.y - offset)) * intensity;
		color += texture2D(texture, clampxy(gl_FragCoord.x, gl_FragCoord.y + offset)) * intensity;
	}	
	gl_FragColor = color / total;
}