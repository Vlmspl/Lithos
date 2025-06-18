package net.vladitandlplayer.lithos;

import foundry.veil.api.client.render.MatrixStack;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.vladitandlplayer.lithos.scene.ClientScene;
import net.vladitandlplayer.lithos.scene.SceneObject;
import net.vladitandlplayer.lithos.scene.SceneObjectRenderer;
import net.vladitandlplayer.lithos.scene.SceneObjectRendererRegistry;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;

import java.util.List;

public class ModRenderer {
    public static final ResourceLocation DEBUG_SHADER = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");
    public static final ResourceLocation DEBUG_RENDER_TYPE = ResourceLocation.fromNamespaceAndPath("lithos",
            "debug");

    public static Matrix4f getViewMatrix(Camera camera) {
        Quaternionf rotation = new Quaternionf(camera.rotation()); // Copy instead of mutating
        Matrix4f viewMatrix = new Matrix4f();

        rotation.conjugate(); // Now safe to modify
        viewMatrix.identity().rotate(rotation);

        viewMatrix.translate(
                (float) -camera.getPosition().x,
                (float) -camera.getPosition().y,
                (float) -camera.getPosition().z
        );

        return viewMatrix;
    }

    public static void renderScene(LevelRenderer levelRenderer, MultiBufferSource.BufferSource bufferSource,
                                      MatrixStack matrixStack, Matrix4fc projectionMatrix, Matrix4fc frustumMatrix,
                                      Camera camera, DeltaTracker deltaTracker) {
        Matrix4f viewMatrix = getViewMatrix(camera);

        List<SceneObject> objects = ClientScene.getAllObjects();

        for (SceneObject object : objects) {
            SceneObjectRenderer<SceneObject> renderer = SceneObjectRendererRegistry.getRenderer(object);

            if (renderer == null) {
                throw new IllegalStateException("No renderer registered for " + object.getClass());
            }

            renderer.render(object, bufferSource, matrixStack, projectionMatrix, viewMatrix, camera);
        }
    }
}
