package net.vladitandlplayer.lithos.scene;

import java.util.HashMap;
import java.util.Map;

public class SceneObjectRendererRegistry {

    // Map from SceneObject subclass to its SceneObjectRenderer class
    private static final Map<Class<? extends SceneObject>, Class<? extends SceneObjectRenderer<?>>> RENDERERS = new HashMap<>();

    // Register renderer class for a SceneObject subclass
    public static <T extends SceneObject> void register(Class<T> sceneObjectClass, Class<? extends SceneObjectRenderer<T>> rendererClass) {
        RENDERERS.put(sceneObjectClass, rendererClass);
    }

    // Get a renderer instance for a given SceneObject instance
    @SuppressWarnings("unchecked")
    public static <T extends SceneObject> SceneObjectRenderer<T> getRenderer(T object) {
        Class<? extends SceneObjectRenderer<?>> rendererClass = RENDERERS.get(object.getClass());
        if (rendererClass == null) {
            throw new IllegalStateException("No renderer registered for SceneObject class: " + object.getClass());
        }

        try {
            return (SceneObjectRenderer<T>) rendererClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate renderer: " + rendererClass, e);
        }
    }
}