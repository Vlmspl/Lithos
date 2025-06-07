package net.vladitandlplayer.lithos.renderers.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.client.Transformation;
import io.wispforest.accessories.api.client.rendering.ClientTransformationUtils;
import io.wispforest.accessories.api.components.AccessoriesDataComponents;
import io.wispforest.accessories.api.components.AccessoryRenderTransformations;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BraceletRenderer implements AccessoryRenderer {

    private static final ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("lithos", "iron_bracelet_3d"), "inventory");
    private BakedModel braceletModel;

    private BakedModel getBraceletModel() {
        if (braceletModel == null) {
            braceletModel = Minecraft.getInstance().getModelManager().getModel(MODEL_LOCATION);
        }
        return braceletModel;
    }

    @Override
    public <M extends LivingEntity> void render(ItemStack itemStack, SlotReference slotReference, PoseStack poseStack,
                                                EntityModel<M> entityModel, MultiBufferSource multiBufferSource, int light,
                                                float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
                                                float netHeadYaw, float headPitch) {

        if (!(entityModel instanceof HumanoidModel<?> humanoidModel)) return;
        AccessoryRenderTransformations transformations = itemStack.getOrDefault(AccessoriesDataComponents.RENDER_TRANSFORMATIONS, AccessoryRenderTransformations.EMPTY);

        Consumer<PoseStack> renderBracelet = (stack) -> {
            var vertexConsumer = multiBufferSource.getBuffer(RenderType.entitySolid(getBraceletModel().getParticleIcon().atlasLocation()));


            for (Direction direction : List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)) {
                var quads = getBraceletModel().getQuads(null, direction, RandomSource.create());

                for (BakedQuad quad : quads) {
                    vertexConsumer.putBulkData(poseStack.last(), quad, 1f, 1f, 1f, 1f, light, OverlayTexture.NO_OVERLAY);
                }
            }
        };

        ClientTransformationUtils.transformStack(transformations.transformations(), poseStack, slotReference.entity(), humanoidModel, () -> {
            poseStack.pushPose();
            poseStack.translate(0.0, 1.5, 0.0);  // Raise it a bit
            poseStack.scale(1.5f, 1.5f, 1.5f);  // Normal scale
            renderBracelet.accept(poseStack);
            poseStack.popPose();

        });
    }

    @Override
    public boolean shouldRender(boolean isRendering) {
        return true;
    }

    @Override
    public boolean shouldRenderInFirstPerson(HumanoidArm arm, ItemStack stack, SlotReference reference) {
        return arm == HumanoidArm.RIGHT;
    }
}