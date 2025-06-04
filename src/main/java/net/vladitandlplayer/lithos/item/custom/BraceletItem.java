package net.vladitandlplayer.lithos.item.custom;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;


public class BraceletItem extends TrinketItem {
    public BraceletItem(float manaLoss, float maxManaUsageHoldOut, float maxManaUsageSingle) {
        super(new Properties().stacksTo(1));
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return true;
    }
}
