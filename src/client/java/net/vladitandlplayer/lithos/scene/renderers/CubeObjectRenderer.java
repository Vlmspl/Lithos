package net.vladitandlplayer.lithos.scene.renderers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import foundry.veil.api.client.render.MatrixStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.rendertype.VeilRenderType;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.vladitandlplayer.lithos.scene.SceneObjectRenderer;
import net.vladitandlplayer.lithos.scene.objects.CubeObject;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public class CubeObjectRenderer implements SceneObjectRenderer<CubeObject> {
    public static final ResourceLocation DEBUG_SHADER = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");
    public static final ResourceLocation DEBUG_RENDER_TYPE = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");

    @Override
    public void render(CubeObject object, MultiBufferSource.BufferSource bufferSource, MatrixStack matrixStack, Matrix4fc projectionMatrix, Matrix4f viewMatrix, Camera camera) {
        Matrix4f modelMatrix = CreateModelMatrix(object.position, object.rotation, object.scale);

        ShaderProgram program = VeilRenderSystem.setShader(DEBUG_SHADER);
        if (program == null) {return;}

        RenderType debug_render_type = VeilRenderType.get(DEBUG_RENDER_TYPE);
        if (debug_render_type == null) {return;}
        VertexConsumer buffer = bufferSource.getBuffer(debug_render_type);

        program.bind();

        program.getUniform("ProjectionMatrix").setMatrix(projectionMatrix);
        program.getUniform("ViewMatrix").setMatrix(viewMatrix);
        program.getUniform("ModelMatrix").setMatrix(modelMatrix);

        // Colors (example: solid red with full alpha)
        int color = 0xFFFF00FF; // RRGGBBAA

        //Front
        buffer.addVertex(0, 1, 1).setColor(color);
        buffer.addVertex(0, 1, 0).setColor(color);
        buffer.addVertex(0, 0, 0).setColor(color);
        buffer.addVertex(0, 0, 1).setColor(color);

        //Left
        buffer.addVertex(0, 1, 0).setColor(color);
        buffer.addVertex(1, 1, 0).setColor(color);
        buffer.addVertex(1, 0, 0).setColor(color);
        buffer.addVertex(0, 0, 0).setColor(color);

        //Back
        buffer.addVertex(1, 1, 0).setColor(color);
        buffer.addVertex(1, 1, 1).setColor(color);
        buffer.addVertex(1, 0, 1).setColor(color);
        buffer.addVertex(1, 0, 0).setColor(color);

        //Right
        buffer.addVertex(1, 1, 1).setColor(color);
        buffer.addVertex(0, 1, 1).setColor(color);
        buffer.addVertex(0, 0, 1).setColor(color);
        buffer.addVertex(1, 0, 1).setColor(color);

        //Top
        buffer.addVertex(0, 1, 1).setColor(color);
        buffer.addVertex(1, 1, 1).setColor(color);
        buffer.addVertex(1, 1, 0).setColor(color);
        buffer.addVertex(0, 1, 0).setColor(color);

        //Bottom
        buffer.addVertex(0, 0, 1).setColor(color);
        buffer.addVertex(1, 0, 1).setColor(color);
        buffer.addVertex(1, 0, 0).setColor(color);
        buffer.addVertex(0, 0, 0).setColor(color);
    }

    private static Matrix4f CreateModelMatrix(Vector3f position, Vector3f rotationDegrees, Vector3f scale) {
        return new Matrix4f()
                .identity()
                .translate(position)
                .rotateXYZ(
                        (float) Math.toRadians(rotationDegrees.x),
                        (float) Math.toRadians(rotationDegrees.y),
                        (float) Math.toRadians(rotationDegrees.z)
                )
                .scale(scale);
    }

}
