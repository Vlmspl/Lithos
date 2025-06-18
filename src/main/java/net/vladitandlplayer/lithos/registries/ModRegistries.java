package net.vladitandlplayer.lithos.registries;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.vladitandlplayer.lithos.Lithos;
import net.vladitandlplayer.lithos.scene.SceneObject;
import net.vladitandlplayer.lithos.spells.actions.Action;
import net.vladitandlplayer.lithos.spells.elements.Element;

public class ModRegistries {

    public static final Registry<Element> ELEMENTS = FabricRegistryBuilder.createSimple(Element.class,
            ResourceLocation.fromNamespaceAndPath(Lithos.MOD_ID, "elements")).buildAndRegister();


    public static final Registry<Action> ACTIONS = FabricRegistryBuilder.createSimple(Action.class,
            ResourceLocation.fromNamespaceAndPath(Lithos.MOD_ID, "actions")).buildAndRegister();

    public static final Registry<SceneObject> SCENE_OBJECTS = FabricRegistryBuilder.createSimple(SceneObject.class,
            ResourceLocation.fromNamespaceAndPath(Lithos.MOD_ID, "scene_objects")).buildAndRegister();
}
