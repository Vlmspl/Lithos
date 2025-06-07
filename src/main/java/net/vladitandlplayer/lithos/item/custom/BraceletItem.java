package net.vladitandlplayer.lithos.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class BraceletItem extends Item {
    public BraceletItem(float manaLoss, float maxManaUsageHoldOut, float maxManaUsageSingle) {
        super(new Properties().stacksTo(1));
    }
}
