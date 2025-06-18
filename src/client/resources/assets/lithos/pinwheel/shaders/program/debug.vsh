#version 330 core

layout(location = 0) in vec3 inPos;
layout(location = 1) in vec4 inColor;

uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;
uniform mat4 ModelMatrix;

out vec4 vertColor;

void main() {
    gl_Position = ProjectionMatrix * ViewMatrix * ModelMatrix * vec4(inPos, 1.0);
    vertColor = inColor;
}
