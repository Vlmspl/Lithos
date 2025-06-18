package net.vladitandlplayer.lithos.scene;

import foundry.veil.api.client.render.MatrixStack;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public interface SceneObjectRenderer<T extends SceneObject> {

    void render(T object, MultiBufferSource.BufferSource bufferSource, MatrixStack matrixStack,
                Matrix4fc projectionMatrix, Matrix4f viewMatrix, Camera camera);
}
