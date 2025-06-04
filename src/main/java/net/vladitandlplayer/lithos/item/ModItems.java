package net.vladitandlplayer.lithos.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.vladitandlplayer.lithos.Lithos;
import net.vladitandlplayer.lithos.item.custom.BraceletItem;

public class ModItems {
    public static final Item IRON_BRACELET = registerItem("iron_bracelet", new BraceletItem( 50f, 1f, 15f));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Lithos.MOD_ID, name),
                item);
    }

    public static void register() {
        Lithos.LOGGER.info("Loading Mod Items");
    }
}
