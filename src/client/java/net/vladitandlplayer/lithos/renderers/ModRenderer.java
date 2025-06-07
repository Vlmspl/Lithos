package net.vladitandlplayer.lithos.renderers;

import com.mojang.blaze3d.vertex.*;
import foundry.veil.api.client.render.MatrixStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.rendertype.VeilRenderType;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;

public class ModRenderer {
    public static final ResourceLocation DEBUG_SHADER = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");
    public static final ResourceLocation DEBUG_RENDER_TYPE = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");

    public static Matrix4f getViewMatrix(Camera camera) {
        Quaternionf rotation = camera.rotation(); // Quaternionf
        Matrix4f viewMatrix = new Matrix4f();

        // Convert quaternion to rotation matrix and invert
        rotation.conjugate(); // invert quaternion (equivalent to invert rotation)
        viewMatrix.identity().rotate(rotation);

        // Translate by negative camera position
        viewMatrix.translate((float) -camera.getPosition().x, (float) -camera.getPosition().y, (float) -camera.getPosition().z);

        return viewMatrix;
    }

    public static void renderTest(LevelRenderer levelRenderer, MultiBufferSource.BufferSource bufferSource,
                                  MatrixStack matrixStack, Matrix4fc frustumMatrix, Matrix4fc projectionMatrix,
                                  Camera camera) {

        ShaderProgram program = VeilRenderSystem.setShader(DEBUG_SHADER);
        if (program == null) {return;}

        RenderType debug_render_type = VeilRenderType.get(DEBUG_RENDER_TYPE);
        if (debug_render_type == null) {return;}
        VertexConsumer buffer = bufferSource.getBuffer(debug_render_type);

        program.bind();
        program.getUniform("ProjectionMatrix").setMatrix(projectionMatrix);
        program.getUniform("ViewMatrix").setMatrix(getViewMatrix(camera));

        buffer.addVertex(0.2f, 80f, -1.5f).setColor(0xFF4A90E2);  // Top-left
        buffer.addVertex(0.2f, 79.4f, -1.5f).setColor(0xFFF8E71C); // Bottom-left
        buffer.addVertex(0.8f, 80f, -1.5f).setColor(0xFF50E3C2);  // Top-right
        buffer.addVertex(0.8f, 79.4f, -1.5f).setColor(0xFFB8E986); // Bottom-right
    }
}
