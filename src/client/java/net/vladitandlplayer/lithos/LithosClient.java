package net.vladitandlplayer.lithos;

import foundry.veil.api.event.VeilRenderLevelStageEvent;
import foundry.veil.platform.VeilEventPlatform;
import net.fabricmc.api.ClientModInitializer;
import net.vladitandlplayer.lithos.renderers.ModRenderer;

public class LithosClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

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