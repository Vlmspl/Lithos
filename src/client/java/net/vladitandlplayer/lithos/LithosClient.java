package net.vladitandlplayer.lithos;

import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.platform.VeilEventPlatform;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.vladitandlplayer.lithos.item.ModItems;
import net.vladitandlplayer.lithos.renderers.ModRenderer;
import net.vladitandlplayer.lithos.renderers.accessories.BraceletRenderer;

public class LithosClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AccessoriesRendererRegistry.registerRenderer(ModItems.IRON_BRACELET.asItem(), BraceletRenderer::new);

		VeilEventPlatform.INSTANCE.onVeilRenderLevelStage(((stage, levelRenderer,
						bufferSource, matrixStack,
						frustumMatrix, projectionMatrix, i,
						renderTickCounter, camera, frustum) -> {
			if (stage == VeilRenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) {
				ModRenderer.renderTest(levelRenderer, bufferSource, matrixStack, frustumMatrix, projectionMatrix, camera);
			}
		}));

	}
}