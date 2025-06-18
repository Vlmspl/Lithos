package net.vladitandlplayer.lithos;

import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.platform.VeilEventPlatform;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.vladitandlplayer.lithos.item.ModItems;
import net.vladitandlplayer.lithos.scene.ClientScene;
import net.vladitandlplayer.lithos.scene.SceneObjectRendererRegistry;
import net.vladitandlplayer.lithos.scene.objects.CubeObject;
import net.vladitandlplayer.lithos.scene.renderers.CubeObjectRenderer;

public class LithosClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AccessoriesRendererRegistry.registerNoRenderer(ModItems.IRON_BRACELET.asItem());

		SceneObjectRendererRegistry.register(CubeObject.class, CubeObjectRenderer.class);

		CubeObject cubeObject = new CubeObject();
		cubeObject.position.set(0, 100, 0);
		ClientScene.putClientObject(1, cubeObject);

		VeilEventPlatform.INSTANCE.onVeilRenderLevelStage(((stage, levelRenderer,
						bufferSource, matrixStack,
						frustumMatrix, projectionMatrix, i,
						renderTickCounter, camera, frustum) -> {
			if (stage == VeilRenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) {
				ModRenderer.renderScene(levelRenderer, bufferSource, matrixStack, projectionMatrix, frustumMatrix, camera, renderTickCounter);
			}
		}));

	}
}