package net.vladitandlplayer.lithos.misc;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

import java.util.List;

public abstract class Model {
    protected static float[] Positions;
    protected static float[] UVs;

    // Constructor to initialize lists
    protected Model(float[] positions, float[] uvs) {
        Positions = positions;
        UVs = uvs;
    }

    // Getters for positions and UVs
    public static float[] GetPositions() {
        return Positions;
    }

    public static float[] GetUVs() {
        return UVs;
    }

    //Note: This function assumes you bind the shader
    public static void render(RenderType renderType, MultiBufferSource multiBufferSource, int color,
                              int light, Runnable shaderUpdater) {
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);

        if (Positions.length/3 != UVs.length/2) {return;}

        int quadCount = Positions.length/12; // 3 floats per vertex × 4 vertices per quad = 12 floats per quad

        shaderUpdater.run();
        for (int i = 0; i < quadCount; i++) {
            vertexConsumer.addVertex(Positions[i*12], Positions[i*12+1], Positions[i*12+2]).setUv(UVs[i*8], UVs[i*8+1]).setLight(light).setColor(color);
            vertexConsumer.addVertex(Positions[i*12+3], Positions[i*12+4], Positions[i*12+5]).setUv(UVs[i*8+2], UVs[i*8+3]).setLight(light).setColor(color);
            vertexConsumer.addVertex(Positions[i*12+6], Positions[i*12+7], Positions[i*12+8]).setUv(UVs[i*8+4], UVs[i*8+5]).setLight(light).setColor(color);
            vertexConsumer.addVertex(Positions[i*12+9], Positions[i*12+10], Positions[i*12+11]).setUv(UVs[i*8+6], UVs[i*8+7]).setLight(light).setColor(color);

        }
    }
}
