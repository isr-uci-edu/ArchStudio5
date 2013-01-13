#version 110

#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D texture; // texture to blur
uniform int size; // blur size
vec2 resolution;
vec2 maxxy;

vec2 clampxy(float x, float y){
	return vec2(clamp(x/resolution.x, 0.0, maxxy.x), clamp(y/resolution.y, 0.0, maxxy.y));
}

void main(void)
{
	vec4 color = vec4(0.0); // resulting color
	resolution = textureSize2D(texture, 0);
	maxxy = vec2((resolution.x - 1.0) / resolution.x, (resolution.y - 1.0)/resolution.y);
	int c = size;
	float total = 1;

	color += texture2D(texture, clampxy(gl_FragCoord.x, gl_FragCoord.y));
	for(int x=1; x<=size; x++){
		float y = exp(- x * x / 2 / c / c);
		total += 2 * y;
		color += texture2D(texture, clampxy(gl_FragCoord.x - x, gl_FragCoord.y)) * y;
		color += texture2D(texture, clampxy(gl_FragCoord.x + x, gl_FragCoord.y)) * y;
	}	

	gl_FragColor = color / total;
}