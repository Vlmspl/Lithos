package net.vladitandlplayer.lithos.scene;

import java.util.*;

public class ClientScene {

    private static final Map<Integer, SceneObject> serverObjects = new HashMap<>();
    private static final Map<Integer, SceneObject> clientObjects = new HashMap<>();

    public static void putClientObject(int id, SceneObject object) {
        clientObjects.put(id, object);
    }

    public static void removeClientObject(int id) {
        clientObjects.remove(id);
    }

    // Get a concatenated list of all objects without IDs
    public static List<SceneObject> getAllObjects() {
        List<SceneObject> combined = new ArrayList<>(serverObjects.values());
        combined.addAll(clientObjects.values());
        return combined;
    }
}
